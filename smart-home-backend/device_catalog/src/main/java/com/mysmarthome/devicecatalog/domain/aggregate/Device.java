package com.mysmarthome.devicecatalog.domain.aggregate;

import com.mysmarthome.devicecatalog.domain.events.DeviceEventAddedForProductEvent;
import com.mysmarthome.devicecatalog.domain.events.DeviceEventRemovedForProductEvent;
import com.mysmarthome.devicecatalog.domain.events.DeviceProductCreatedEvent;
import com.mysmarthome.devicecatalog.domain.events.DeviceValueConfiguredEvent;
import com.mysmarthome.devicecatalog.domain.events.DeviceValueRemovedEvent;
import com.mysmarthome.devicecatalog.domain.model.DeviceEvent;
import com.mysmarthome.devicecatalog.domain.model.DeviceValue;
import com.mysmarthome.devicecatalog.domain.valueobjects.DeviceId;
import com.mysmarthome.devicecatalog.domain.valueobjects.ValueRange;
import com.mysmarthome.devicecatalog.domain.valueobjects.ValueType;
import com.mysmarthome.domain.AggregateRoot;
import com.mysmarthome.exceptions.SmartHomeException;
import jakarta.persistence.CascadeType;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import static com.mysmarthome.devicecatalog.domain.exceptions.DeviceCatalogExceptionCode.DeviceCatalogDeviceValueNotFoundByCode;
import static com.mysmarthome.devicecatalog.domain.exceptions.DeviceCatalogExceptionCode.DeviceCatalogEventTypeAlreadyExists;
import static com.mysmarthome.devicecatalog.domain.exceptions.DeviceCatalogExceptionCode.DeviceCatalogEventTypeDoesNotExistForDevice;
import static com.mysmarthome.devicecatalog.domain.exceptions.DeviceCatalogExceptionCode.DeviceCatalogValueTypeAlreadyExists;

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

    @OneToMany(mappedBy = "device", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DeviceEvent> deviceEvents;

    @OneToMany(mappedBy = "device", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DeviceValue> deviceValues;

    private Device(DeviceId id, String name, String typeCode, String shortDescription, String description, String imageUrl) {
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
        boolean deviceCodeExists = deviceEvents.stream().anyMatch(event -> event.code() == code);

        if (deviceCodeExists) {
            throw new SmartHomeException(DeviceCatalogEventTypeAlreadyExists);
        }

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

    public DeviceValue addDeviceValue(String label, int code, String icon, ValueType type, ValueRange range) {
        boolean deviceCodeExists = deviceValues.stream().anyMatch(val -> val.code() == code);

        if (deviceCodeExists) {
            throw new SmartHomeException(DeviceCatalogValueTypeAlreadyExists);
        }

        var toBeAdded = DeviceValue.valueFor(this, label, code, icon, range, type);

        deviceValues.add(toBeAdded);
        publishDomainEvent(new DeviceValueConfiguredEvent(id.toString(), label, code, type.name()));

        return toBeAdded;
    }

    public void removeValueByCode(int code) {
        var devValue = deviceValues.stream().filter(val -> val.code() == code).findFirst()
                .orElseThrow(() -> new SmartHomeException(DeviceCatalogDeviceValueNotFoundByCode));

        devValue.purge();
        deviceValues.remove(devValue);

        publishDomainEvent(new DeviceValueRemovedEvent(id.toString(), code));
    }
}
