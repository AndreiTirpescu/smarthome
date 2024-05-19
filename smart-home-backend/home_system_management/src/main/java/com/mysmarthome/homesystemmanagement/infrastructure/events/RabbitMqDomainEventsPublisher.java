package com.mysmarthome.homesystemmanagement.infrastructure.events;

import com.mysmarthome.domain.Event;
import com.mysmarthome.homesystemmanagement.domain.aggregate.HomeSystem;
import com.mysmarthome.homesystemmanagement.domain.infrastructure.IDomainEventsPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component("homesystemmanagementpublisher")
@RequiredArgsConstructor
@Slf4j
public class RabbitMqDomainEventsPublisher implements IDomainEventsPublisher {
    private static final String APPLICATION_NAME = "homesystemmanagement";

    private final HomeSystemDomainEventConfiguration configuration;

    private final RabbitTemplate amqp;

    @Override
    public void publishDomainEventsFor(HomeSystem homeSystem) {
        homeSystem.events()
                .stream()
                .map(evt -> Event.from(evt).withIdentity(homeSystem.getIdentity().id()))
                .forEach(evt1 -> amqp.send(
                        configuration.topic,
                        configuration.routingKey,
                        new Message(evt1.withApplication(APPLICATION_NAME).toJson().getBytes())
                ));

        homeSystem.clearEvents();
    }
}
