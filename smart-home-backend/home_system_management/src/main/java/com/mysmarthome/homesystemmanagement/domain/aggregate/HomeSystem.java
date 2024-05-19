package com.mysmarthome.homesystemmanagement.domain.aggregate;

import com.mysmarthome.domain.AggregateRoot;
import com.mysmarthome.exceptions.SmartHomeException;
import com.mysmarthome.homesystemmanagement.domain.events.HomeSystemCreatedEvent;
import com.mysmarthome.homesystemmanagement.domain.exceptions.HomeSystemExceptionCode;
import com.mysmarthome.homesystemmanagement.domain.model.Device;
import com.mysmarthome.homesystemmanagement.domain.valueobjects.Address;
import com.mysmarthome.homesystemmanagement.domain.valueobjects.HomeSystemId;
import com.mysmarthome.homesystemmanagement.domain.valueobjects.Identity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
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
@Table(name = "home_systems", schema = "home_system_management")
public class HomeSystem extends AggregateRoot {

    @EmbeddedId
    private HomeSystemId homeSystemId;

    @Embedded
    private Identity identity;

    private String name;

    private String description;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "homeSystem", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Device> devices;

    private HomeSystem(HomeSystemId homeSystemId, Identity identity, String name, String description, Address address) {
        this.homeSystemId = homeSystemId;
        this.identity = identity;

        if (name == null || name.isEmpty()) {
            throw new SmartHomeException(HomeSystemExceptionCode.HomeSystemMustHaveAName);
        }

        this.name = name;
        this.description = description;
        this.address = address;
    }

    public static HomeSystem connectNewHomeSystem(HomeSystemId id, String identityId, String name) {
        var homeSystem = new HomeSystem(id, new Identity(identityId), name, "", Address.emptyAddress());
        homeSystem.publishDomainEvent(new HomeSystemCreatedEvent(id.toString(), identityId, homeSystem.getName()));

        return homeSystem;
    }
}
