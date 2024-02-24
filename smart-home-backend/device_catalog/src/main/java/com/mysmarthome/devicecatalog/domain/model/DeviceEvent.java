package com.mysmarthome.devicecatalog.domain.model;

import com.mysmarthome.devicecatalog.domain.aggregate.Device;
import com.mysmarthome.devicecatalog.domain.valueobjects.DeviceId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Getter(AccessLevel.PRIVATE)
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "device_events", schema = "devices")
public class DeviceEvent implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Device device;

    private String name;

    private int code;

    private DeviceEvent(Device device, String name, int code) {
        this.device = device;
        this.name = name;
        this.code = code;
    }

    public static DeviceEvent eventFor(Device device, String name, int code) {
        return new DeviceEvent(device, name, code);
    }

    public DeviceId deviceId() {
        return device.getId();
    }

    public String name() {
        return name;
    }

    public int code() {
        return code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeviceEvent that = (DeviceEvent) o;

        if (code != that.code) return false;
        return Objects.equals(device.getId(), that.device.getId());
    }

    @Override
    public int hashCode() {
        int result = device != null ? device.getId().hashCode() : 0;
        result = 31 * result + code;
        return result;
    }
}
