package com.mysmarthome.identityandaccess.infrastructure.events;

import com.mysmarthome.identityandaccess.domain.aggregate.User;
import com.mysmarthome.identityandaccess.domain.infrastructure.IDomainEventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class KafkaDomainEventPublisher implements IDomainEventPublisher {
    @Override
    public void publishDomainEventsFor(User user) {
        user.clearEvents();
    }
}
