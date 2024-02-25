package com.mysmarthome.devicecatalog.domain.aggregate;

import com.mysmarthome.devicecatalog.domain.events.DeviceEventAddedForProductEvent;
import com.mysmarthome.devicecatalog.domain.events.DeviceEventRemovedForProductEvent;
import com.mysmarthome.devicecatalog.domain.events.DeviceProductCreatedEvent;
import com.mysmarthome.devicecatalog.domain.model.DeviceEvent;
import com.mysmarthome.devicecatalog.domain.valueobjects.DeviceId;
import com.mysmarthome.domain.AggregateRoot;
import com.mysmarthome.exceptions.SmartHomeException;
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

import static com.mysmarthome.devicecatalog.domain.exceptions.DeviceCatalogExceptionCode.DeviceCatalogEventTypeDoesNotExistForDevice;

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

    private String typeCode;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "device", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DeviceEvent> deviceEvents;

    public Device(DeviceId id, String name, String typeCode, String shortDescription, String description, String imageUrl) {
        this.id = id;
        this.name = name;
        this.typeCode = typeCode;
        this.shortDescription = shortDescription;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public static Device newDeviceType(DeviceId id, String name, String typeCode, String shortDescription, String description, String imageUrl) {
        var device = new Device(id, name, typeCode, shortDescription, description, imageUrl);

        device.publishDomainEvent(new DeviceProductCreatedEvent(id.toString(), name, typeCode));

        return device;
    }

    public DeviceEvent addEventType(String name, int code) {
        var toBeAdded = DeviceEvent.eventFor(this, name, code);

        deviceEvents.add(toBeAdded);

        publishDomainEvent(new DeviceEventAddedForProductEvent(id.toString(), name));

        return toBeAdded;
    }

    public void removeEventType(Integer eventType) {
        var event = deviceEvents.stream().filter(evt -> eventType.equals(evt.code())).findFirst()
                        .orElseThrow(() -> new SmartHomeException(DeviceCatalogEventTypeDoesNotExistForDevice));
        event.purge();
        deviceEvents.remove(event);

        publishDomainEvent(new DeviceEventRemovedForProductEvent(id.toString(), eventType));
    }
}
