package com.mysmarthome.identityandaccess.domain.infrastructure;

import com.mysmarthome.identityandaccess.domain.aggregate.User;

public interface IDomainEventPublisher {
    void publishDomainEventsFor(User user);
}
