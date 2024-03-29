package com.mysmarthome.identityandaccess.domain.events;

import com.mysmarthome.domain.IDomainEvent;

public record UserCreatedEvent(String identityId, String email) implements IDomainEvent {
    @Override
    public String key() {
        return "user_created_event";
    }
}
