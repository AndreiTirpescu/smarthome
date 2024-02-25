package com.mysmarthome.eventstore.infrastructure;

import com.google.gson.Gson;
import com.mysmarthome.domain.Event;
import com.mysmarthome.eventstore.domain.SmartHomeEvent;
import com.mysmarthome.eventstore.infrastructure.repositories.SmartHomeEventRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SmartHomeEventListener {

    private final SmartHomeEventRepository repository;

    @RabbitListener(queues = "${event.log.queue}")
    public void onSmartHomeEventReceived(String serialized) {
        try {
            log.info("Received event {}, storing", serialized);
            var event = SmartHomeEvent.fromEventWrapper(Event.fromJson(serialized));

            repository.save(event);
        } catch (RuntimeException ex) {
            log.error("Failed to store event {} ex = {}", serialized, ex.getMessage());
        }
    }
}
