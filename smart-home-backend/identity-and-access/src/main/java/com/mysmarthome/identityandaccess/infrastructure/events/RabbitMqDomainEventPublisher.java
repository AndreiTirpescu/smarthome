package com.mysmarthome.identityandaccess.infrastructure.events;

import com.mysmarthome.domain.Event;
import com.mysmarthome.identityandaccess.domain.aggregate.User;
import com.mysmarthome.identityandaccess.domain.infrastructure.IDomainEventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RabbitMqDomainEventPublisher implements IDomainEventPublisher {

    private final IamDomainEventsConfiguration configuration;

    private final RabbitTemplate amqp;

    @Override
    public void publishDomainEventsFor(User user) {
        user.events()
                .stream()
                .map(evt -> Event.from(evt).withIdentity(user.getId().toString()).withApplication("identityandaccess"))
                .forEach(this::amqpSend);

        user.clearEvents();
    }

    @Override
    public void publish(Event event) {
        amqpSend(event);
    }

    private void amqpSend(Event evt) {
        amqp.send(configuration.topic, configuration.routingKey, new Message(evt.toJson().getBytes()));
    }
}
