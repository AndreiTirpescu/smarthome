package com.mysmarthome.devicecatalog.domain.events;

import com.mysmarthome.domain.IDomainEvent;

public record DeviceEventAddedForProduct(String deviceId, String eventName) implements IDomainEvent {
    @Override
    public String key() {
        return "device_event_added";
    }
}
