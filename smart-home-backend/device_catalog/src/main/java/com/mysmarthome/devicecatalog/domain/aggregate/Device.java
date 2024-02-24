package com.mysmarthome.devicecatalog.domain.aggregate;

import com.mysmarthome.devicecatalog.domain.events.DeviceProductCreatedEvent;
import com.mysmarthome.devicecatalog.domain.valueobjects.DeviceEvent;
import com.mysmarthome.devicecatalog.domain.valueobjects.DeviceId;
import com.mysmarthome.domain.AggregateRoot;
import jakarta.persistence.CascadeType;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Setter(AccessLevel.PRIVATE)
@Getter
@Entity
@Table(name = "devices", schema = "devices")
public class Device extends AggregateRoot {

    @EmbeddedId
    private DeviceId id;

    private String name;

    private String shortDescription;

    private String description;

    private String imageUrl;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "device", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DeviceEvent> deviceEvents;

    public Device(DeviceId id, String name, String shortDescription, String description, String imageUrl) {
        this.id = id;
        this.name = name;
        this.shortDescription = shortDescription;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public static Device newDeviceType(DeviceId id, String name, String shortDescription, String description, String imageUrl) {
        var device = new Device(id, name, shortDescription, description, imageUrl);

        device.publishDomainEvent(new DeviceProductCreatedEvent(id.toString(), name));

        return device;
    }

    public DeviceEvent addEventType(String name, int code) {
        var toBeAdded = DeviceEvent.eventFor(this, name, code);

        deviceEvents.add(toBeAdded);

        publishDomainEvent(new DeviceProductCreatedEvent(id.toString(), name));

        return toBeAdded;
    }
}
