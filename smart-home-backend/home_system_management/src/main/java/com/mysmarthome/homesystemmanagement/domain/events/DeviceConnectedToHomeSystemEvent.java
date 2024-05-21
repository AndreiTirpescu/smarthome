package com.mysmarthome.homesystemmanagement.domain.events;

import com.mysmarthome.domain.IDomainEvent;

public record DeviceConnectedToHomeSystemEvent(String homeSystemId,
                                               String deviceCatalogId,
                                               String identityId) implements IDomainEvent {
    @Override
    public String key() {
        return "device_connected_to_home_system";
    }
}
