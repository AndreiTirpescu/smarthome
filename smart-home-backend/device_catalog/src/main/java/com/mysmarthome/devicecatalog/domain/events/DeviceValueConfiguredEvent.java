package com.mysmarthome.devicecatalog.domain.events;

import com.mysmarthome.domain.IDomainEvent;

public record DeviceValueConfiguredEvent(String deviceProductId, String label, int code, String type) implements IDomainEvent {
    @Override
    public String key() {
        return "device_catalog_value_configured";
    }
}
