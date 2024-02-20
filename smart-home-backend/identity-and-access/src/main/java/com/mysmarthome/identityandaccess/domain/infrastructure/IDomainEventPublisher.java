package com.mysmarthome.identityandaccess.domain.infrastructure;

import com.mysmarthome.domain.Event;
import com.mysmarthome.domain.IDomainEvent;
import com.mysmarthome.identityandaccess.domain.aggregate.User;

public interface IDomainEventPublisher {
    void publishDomainEventsFor(User user);

    void publish(Event event);
}
