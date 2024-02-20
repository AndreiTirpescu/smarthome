package com.mysmarthome.devicecatalog.domain.infrastructure;


import com.mysmarthome.devicecatalog.domain.aggregate.Device;
import com.mysmarthome.devicecatalog.domain.valueobjects.DeviceId;

import java.util.Optional;

public interface IDeviceRepository {
    DeviceId nextIdentity();

    Optional<Device> byId(DeviceId id);

    Device save(Device device);
}
