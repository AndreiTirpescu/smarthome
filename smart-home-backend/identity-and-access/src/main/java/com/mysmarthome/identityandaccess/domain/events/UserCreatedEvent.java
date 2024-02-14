package com.mysmarthome.identityandaccess.domain.events;

import com.mysmarthome.domain.IDomainEvent;

public record UserCreatedEvent(String id, String email) implements IDomainEvent {
}
