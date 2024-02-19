package com.mysmarthome.devicecatalog.domain.aggregate;

import com.mysmarthome.devicecatalog.domain.events.DeviceProductCreatedEvent;
import com.mysmarthome.devicecatalog.domain.valueobjects.DeviceId;
import com.mysmarthome.domain.AggregateRoot;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Setter(AccessLevel.PRIVATE)
@Getter
@Entity
@Table(name = "devices", schema = "device_catalog")
public class Device extends AggregateRoot {

    @EmbeddedId
    private DeviceId deviceId;

    private String name;

    private String description;

    private Device(DeviceId deviceId, String name, String description) {
        this.deviceId = deviceId;
        this.name = name;
        this.description = description;
    }

    public static Device newDeviceType(DeviceId id, String name, String description) {
        var device = new Device(id, name, description);

        device.publishDomainEvent(new DeviceProductCreatedEvent(id.toString(), name));

        return device;
    }
}
