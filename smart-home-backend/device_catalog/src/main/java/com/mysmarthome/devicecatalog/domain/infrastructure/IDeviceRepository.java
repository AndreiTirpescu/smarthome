package com.mysmarthome.devicecatalog.domain.infrastructure;


import com.mysmarthome.devicecatalog.domain.aggregate.Device;
import com.mysmarthome.devicecatalog.domain.valueobjects.DeviceId;
import com.mysmarthome.domain.PagedView;

import java.util.Optional;

public interface IDeviceRepository {
    DeviceId nextIdentity();

    Optional<Device> byId(DeviceId id);

    Optional<Device> byTypeCode(String typeCode);

    boolean existsByTypeCode(String typeCode);

    Device save(Device device);

    PagedView<Device> findAllPaged(int pageNumber, int pageSize);
}
