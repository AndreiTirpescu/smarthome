package com.mysmarthome.devicecatalog.domain.valueobjects;

import com.mysmarthome.devicecatalog.domain.exceptions.DeviceCatalogExceptionCode;
import com.mysmarthome.exceptions.SmartHomeException;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Setter(AccessLevel.PRIVATE)
@Getter
@Embeddable
public class DeviceId implements Serializable {
    private UUID id;

    public DeviceId(UUID id) {
        this.id = id;
    }

    public DeviceId(String id) {
        try {
            this.id = UUID.fromString(id);
        } catch (RuntimeException ex) {
            throw new SmartHomeException(DeviceCatalogExceptionCode.DeviceCatalogInvalidDeviceId);
        }
    }

    @Override
    public String toString() {
        return id.toString();
    }
}
