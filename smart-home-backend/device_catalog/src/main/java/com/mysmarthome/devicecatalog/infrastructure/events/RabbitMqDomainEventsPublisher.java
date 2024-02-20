package com.mysmarthome.devicecatalog.infrastructure.events;

import com.mysmarthome.devicecatalog.domain.aggregate.Device;
import com.mysmarthome.devicecatalog.domain.infrastructure.IDomainEventPublisher;
import com.mysmarthome.domain.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RabbitMqDomainEventsPublisher implements IDomainEventPublisher {

    private final DeviceCatalogDomainEventsConfiguration configuration;

    private final RabbitTemplate amqp;

    @Override
    public void publishDomainEventsFor(Device device) {
        device.events()
                .stream()
                .map(evt -> Event.from(evt).withApplication("devicecatalog"))
                .forEach(this::amqpSend);

        device.clearEvents();
    }

    private void amqpSend(Event evt) {
        amqp.send(configuration.topic, configuration.routingKey, new Message(evt.toJson().getBytes()));
    }
}
