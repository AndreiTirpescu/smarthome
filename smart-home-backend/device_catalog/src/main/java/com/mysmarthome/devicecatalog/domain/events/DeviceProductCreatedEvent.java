package com.mysmarthome.devicecatalog.domain.events;

import com.mysmarthome.domain.IDomainEvent;

public record DeviceProductCreatedEvent(String id, String name) implements IDomainEvent {
}
