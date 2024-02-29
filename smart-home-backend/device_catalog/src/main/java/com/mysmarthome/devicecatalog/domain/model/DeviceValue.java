package com.mysmarthome.devicecatalog.domain.model;

import com.mysmarthome.devicecatalog.domain.aggregate.Device;
import com.mysmarthome.devicecatalog.domain.exceptions.DeviceCatalogExceptionCode;
import com.mysmarthome.devicecatalog.domain.valueobjects.DeviceId;
import com.mysmarthome.devicecatalog.domain.valueobjects.ValueRange;
import com.mysmarthome.devicecatalog.domain.valueobjects.ValueType;
import com.mysmarthome.exceptions.SmartHomeException;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter(AccessLevel.PRIVATE)
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Table(name = "device_values", schema = "devices")
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type", discriminatorType = DiscriminatorType.STRING)
public class DeviceValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Device device;

    @NotNull
    private String label;

    private int code;

    @NotNull
    private String icon;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "devices.device_value_types", insertable = false, updatable = false)
    private ValueType type;

    private ValueRange range;

    DeviceValue(Device device, @NotNull String label, int code, @NotNull String icon, ValueType type, ValueRange range) {
        this.device = device;
        this.label = label;
        this.code = code;
        this.icon = icon;
        this.type = type;
        this.range = range;
    }

    public static DeviceValue valueFor(Device device, String label, int code, String icon, ValueRange range, ValueType type) {
        switch (type) {
            case RANGE -> {
                return new RangeValue(device, label, code, icon, range);
            }
            case SWITCH -> {
                return new SwitchValue(device, label, code, icon);
            }
            default -> throw new SmartHomeException(DeviceCatalogExceptionCode.DeviceCatalogUnknownDeviceValueType);
        }
    }

    public DeviceId deviceId() {
        return device.getId();
    }

    public int code() {
        return code;
    }

    public String label() {
        return label;
    }


    public String icon() {
        return icon;
    }

    public ValueType type() {
        return type;
    }

    public ValueRange range() {
        return range;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeviceValue that = (DeviceValue) o;

        if (code != that.code) return false;
        return device.getId().equals(that.device.getId());
    }

    @Override
    public int hashCode() {
        int result = device.getId().hashCode();
        result = 31 * result + code;
        return result;
    }

    public void purge() {
        device = null;
    }
}
