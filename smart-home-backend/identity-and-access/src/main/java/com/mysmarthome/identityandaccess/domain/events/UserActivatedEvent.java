package com.mysmarthome.identityandaccess.domain.events;

import com.mysmarthome.domain.IDomainEvent;

public record UserActivatedEvent(String identityId, Long activatedAt) implements IDomainEvent {
    @Override
    public String key() {
        return "user_activated_event";
    }
}
