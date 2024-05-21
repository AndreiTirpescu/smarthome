package com.mysmarthome.homesystemmanagement.domain.model;

import com.mysmarthome.homesystemmanagement.domain.aggregate.HomeSystem;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Setter(AccessLevel.PRIVATE)
@Getter
@Entity(name = "homeSystemDevice")
@Table(name = "devices", schema = "home_system_management")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String deviceCatalogId;

    @ManyToOne
    private HomeSystem homeSystem;

    private Long currentValue;

    private String deviceName;

    private Long connectedAt;

    private Long valueUpdatedAt;

    private Device(String deviceCatalogId, HomeSystem homeSystem, Long currentValue, String deviceName, Long connectedAt, Long valueUpdatedAt) {
        this.deviceCatalogId = deviceCatalogId;
        this.homeSystem = homeSystem;
        this.currentValue = currentValue;
        this.deviceName = deviceName;
        this.connectedAt = connectedAt;
        this.valueUpdatedAt = valueUpdatedAt;
    }

    public static Device newlyConnectedDevice(HomeSystem homeSystem, String deviceCatalogId, String deviceName) {
        return new Device(deviceCatalogId, homeSystem, null, deviceName,
                Instant.now().toEpochMilli(), Instant.now().toEpochMilli());
    }

    public String homeSystemId() {
        return homeSystem.getHomeSystemId().toString();
    }
}
