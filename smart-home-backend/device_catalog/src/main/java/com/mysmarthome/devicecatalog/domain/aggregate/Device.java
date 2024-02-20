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
@Table(name = "devices", schema = "devices")
public class Device extends AggregateRoot {

    @EmbeddedId
    private DeviceId id;

    private String name;

    private String description;

    private String imageUrl;

    private Device(DeviceId id, String name, String description, String imageUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public static Device newDeviceType(DeviceId id, String name, String description, String imageUrl) {
        var device = new Device(id, name, description, imageUrl);

        device.publishDomainEvent(new DeviceProductCreatedEvent(id.toString(), name));

        return device;
    }
}
