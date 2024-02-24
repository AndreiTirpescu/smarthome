package com.mysmarthome.devicecatalog.infrastructure.repositories;

import com.mysmarthome.devicecatalog.domain.infrastructure.IDeviceEventsRepository;
import com.mysmarthome.devicecatalog.domain.valueobjects.DeviceId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeviceEventRepository implements IDeviceEventsRepository {

    private final SpringDataDeviceEvents springDataDeviceEvents;

    @Override
    public boolean existsEventByDeviceIdAndCode(DeviceId id, int code) {
        return springDataDeviceEvents.existsByDeviceIdAndCode(id, code);
    }
}
