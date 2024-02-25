package com.mysmarthome.devicecatalog.domain.events;

import com.mysmarthome.domain.IDomainEvent;

public record DeviceEventRemovedForProductEvent(String deviceId, Integer code) implements IDomainEvent {
    @Override
    public String key() {
        return "device_event_removed";
    }
}
