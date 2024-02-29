package com.mysmarthome.devicecatalog.domain.events;

import com.mysmarthome.domain.IDomainEvent;

public record DeviceValueRemovedEvent(String deviceProductId, Integer code) implements IDomainEvent {
    @Override
    public String key() {
        return "device_value_removed";
    }
}
