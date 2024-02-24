package com.mysmarthome.devicecatalog.infrastructure.repositories;

import com.mysmarthome.devicecatalog.domain.aggregate.Device;
import com.mysmarthome.devicecatalog.domain.infrastructure.IDeviceRepository;
import com.mysmarthome.devicecatalog.domain.valueobjects.DeviceId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DeviceRepository implements IDeviceRepository {

    private final SpringDataDeviceRepository springDataRepository;

    @Override
    public DeviceId nextIdentity() {
        return new DeviceId(UUID.randomUUID());
    }

    @Override
    public Optional<Device> byId(DeviceId id) {
        return springDataRepository.findById(id);
    }

    @Override
    public boolean existsByTypeCode(String typeCode) {
        return springDataRepository.existsByTypeCode(typeCode);
    }

    @Override
    public Device save(Device device) {
        return springDataRepository.save(device);
    }
}
