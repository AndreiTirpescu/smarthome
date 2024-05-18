package com.mysmarthome.userprofiling.infrastructure.handlers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mysmarthome.domain.Event;
import com.mysmarthome.userprofiling.infrastructure.application.AutoCreateUserProfile;
import com.mysmarthome.userprofiling.infrastructure.application.AutoCreateUserProfileHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserProfileEventListener {

    public static final String USER_CREATED_EVENT = "user_created_event";

    private final AutoCreateUserProfileHandler autoCreateUserProfileHandler;

    @RabbitListener(queues = "${userprofiling.listener.queue}")
    public void onSmartHomeEventReceived(String serialized) {
        try {
            var event = Event.fromJson(serialized);

            if (USER_CREATED_EVENT.equalsIgnoreCase(event.key())) {
                log.info("Received user created event");
                var identityId = event.identity();
                var email = new Gson().fromJson(event.payload(), JsonObject.class).get("email").getAsString();
                autoCreateUserProfileHandler.handle(new AutoCreateUserProfile(identityId, email));
            }

        } catch (Exception ex) {
            log.error("Failed to autocreate profile from event {} ex = {}", serialized, ex.getMessage());
        }
    }

}
