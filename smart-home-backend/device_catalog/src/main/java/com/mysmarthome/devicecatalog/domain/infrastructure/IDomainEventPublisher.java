package com.mysmarthome.devicecatalog.domain.infrastructure;

import com.mysmarthome.devicecatalog.domain.aggregate.Device;

public interface IDomainEventPublisher {
    void publishDomainEventsFor(Device device);
}
