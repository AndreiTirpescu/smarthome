package com.mysmarthome.devicecatalog.infrastructure.repositories;

import com.mysmarthome.devicecatalog.domain.model.DeviceEvent;
import com.mysmarthome.devicecatalog.domain.valueobjects.DeviceId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataDeviceEvents extends JpaRepository<DeviceEvent, Long> {

    @Query("select case when(count(result) > 0) then true else false end from DeviceEvent result where result.device.id = :deviceId and result.code = :code")
    boolean existsByDeviceIdAndCode(DeviceId deviceId, int code);
}
