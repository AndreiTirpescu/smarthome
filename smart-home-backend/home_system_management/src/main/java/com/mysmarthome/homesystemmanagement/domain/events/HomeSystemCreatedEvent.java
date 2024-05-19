package com.mysmarthome.homesystemmanagement.domain.events;

import com.mysmarthome.domain.IDomainEvent;

public record HomeSystemCreatedEvent(String homeSystemId, String identityId, String name) implements IDomainEvent {
    @Override
    public String key() {
        return "home_system_created";
    }
}
