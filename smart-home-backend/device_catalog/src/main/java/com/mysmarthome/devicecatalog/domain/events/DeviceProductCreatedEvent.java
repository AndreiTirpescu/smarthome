package com.mysmarthome.devicecatalog.domain.events;

import com.mysmarthome.domain.IDomainEvent;

public record DeviceProductCreatedEvent(String deviceProductId, String name) implements IDomainEvent {
}
