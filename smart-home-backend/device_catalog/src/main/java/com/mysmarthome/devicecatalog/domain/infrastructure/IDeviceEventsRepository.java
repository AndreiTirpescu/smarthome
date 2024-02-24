package com.mysmarthome.devicecatalog.domain.infrastructure;

import com.mysmarthome.devicecatalog.domain.valueobjects.DeviceId;

public interface IDeviceEventsRepository {
    boolean existsEventByDeviceIdAndCode(DeviceId id, int code);
}
