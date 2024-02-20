package com.mysmarthome.identityandaccess.domain.events;

import com.mysmarthome.domain.IDomainEvent;

public record UserAuthenticatedEvent(String identityId) implements IDomainEvent {
    @Override
    public String key() {
        return "user_login_event";
    }
}
