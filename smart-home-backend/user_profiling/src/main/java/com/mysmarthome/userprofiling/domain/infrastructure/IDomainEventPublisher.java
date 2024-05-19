package com.mysmarthome.userprofiling.domain.infrastructure;

import com.mysmarthome.userprofiling.domain.aggregate.UserProfile;

public interface IDomainEventPublisher {
    void publishDomainEventsFor(UserProfile profile);
}
