package com.mysmarthome.devicecatalog.infrastructure.repositories;

import com.mysmarthome.devicecatalog.domain.aggregate.Device;
import com.mysmarthome.devicecatalog.domain.valueobjects.DeviceId;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringDataDeviceRepository extends PagingAndSortingRepository<Device, DeviceId> {

    Optional<Device> findById(DeviceId id);

    Device save(Device device);

    boolean existsByTypeCode(String typeCode);
}
