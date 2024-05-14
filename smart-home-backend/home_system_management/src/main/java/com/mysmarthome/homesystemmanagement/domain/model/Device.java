package com.mysmarthome.homesystemmanagement.domain.model;

import com.mysmarthome.homesystemmanagement.domain.aggregate.HomeSystem;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Setter(AccessLevel.PRIVATE)
@Getter
@Entity(name = "homeSystemDevice")
@Table(name = "devices", schema = "home_system_management")
public class Device {
    @Id
    @GeneratedValue
    private Long id;

    private String deviceCatalogId;

    private String name;

    @ManyToOne
    private HomeSystem homeSystem;
}
