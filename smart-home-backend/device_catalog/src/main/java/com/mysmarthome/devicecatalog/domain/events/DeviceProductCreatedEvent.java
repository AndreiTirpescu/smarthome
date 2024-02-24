package com.mysmarthome.devicecatalog.domain.events;

import com.mysmarthome.domain.IDomainEvent;

public record DeviceProductCreatedEvent(String deviceProductId, String name, String typeCode) implements IDomainEvent {
    @Override
    public String key() {
        return "device_product_created";
    }
}
