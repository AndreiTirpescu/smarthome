package com.mysmarthome.devicecatalog.infrastructure.repositories;

import com.mysmarthome.devicecatalog.domain.aggregate.Device;
import com.mysmarthome.devicecatalog.domain.infrastructure.IDeviceRepository;
import com.mysmarthome.devicecatalog.domain.valueobjects.DeviceId;
import com.mysmarthome.domain.PagedView;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
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
    public Optional<Device> byTypeCode(String typeCode) {
        return springDataRepository.findByTypeCode(typeCode);
    }

    @Override
    public boolean existsByTypeCode(String typeCode) {
        return springDataRepository.existsByTypeCode(typeCode);
    }

    @Override
    public Device save(Device device) {
        return springDataRepository.save(device);
    }

    @Override
    public PagedView<Device> findAllPaged(int pageNumber, int pageSize) {
        var paged = springDataRepository.findAll(PageRequest.of(pageNumber, pageSize));

        return new PagedView<>(paged.stream().toList(), paged.getTotalElements(), paged.getTotalPages());
    }
}
