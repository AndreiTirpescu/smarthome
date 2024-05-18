package com.mysmarthome.userprofiling.infrastructure.events;

import com.mysmarthome.domain.Event;
import com.mysmarthome.userprofiling.domain.aggregate.UserProfile;
import com.mysmarthome.userprofiling.domain.infrastructure.IDomainEventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component("profilingDomainEventPublisher")
public class RabbitMqDomainEventPublisher implements IDomainEventPublisher {
    private static final String APPLICATION_NAME = "userprofiling";

    private final UserProfilingDomainEventConfiguration configuration;

    private final RabbitTemplate amqp;

    @Override
    public void publishDomainEventsFor(UserProfile profile) {
        profile.events()
                .stream()
                .map(evt -> Event.from(evt).withIdentity(profile.getIdentity().id()))
                .forEach(evt1 -> amqp.send(
                        configuration.topic,
                        configuration.routingKey,
                        new Message(evt1.withApplication(APPLICATION_NAME).toJson().getBytes())
                ));

        profile.clearEvents();
    }

}
