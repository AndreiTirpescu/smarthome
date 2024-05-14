package com.mysmarthome.homesystemmanagement.domain.aggregate;

import com.mysmarthome.domain.AggregateRoot;
import com.mysmarthome.exceptions.SmartHomeException;
import com.mysmarthome.homesystemmanagement.domain.events.HomeSystemCreatedEvent;
import com.mysmarthome.homesystemmanagement.domain.exceptions.HomeSystemExceptionCode;
import com.mysmarthome.homesystemmanagement.domain.model.Device;
import com.mysmarthome.homesystemmanagement.domain.valueobjects.HomeSystemId;
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

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Setter(AccessLevel.PRIVATE)
@Getter
@Entity
@Table(name = "home_systems", schema = "home_system_management")
public class HomeSystem extends AggregateRoot {

    @EmbeddedId
    private HomeSystemId homeSystemId;

    private String name;

    private String description;

    @OneToMany(mappedBy = "homeSystem", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Device> devices;

    private HomeSystem(HomeSystemId homeSystemId, String name, String description) {
        this.homeSystemId = homeSystemId;

        if (name == null || name.isEmpty()) {
            throw new SmartHomeException(HomeSystemExceptionCode.HomeSystemMustHaveAName);
        }

        this.name = name;
        this.description = description;
    }

    public static HomeSystem newSystem(HomeSystemId id, String name, String description) {
        var homeSystem = new HomeSystem(id, name, description);
        homeSystem.publishDomainEvent(new HomeSystemCreatedEvent(id.toString(), homeSystem.getName()));

        return homeSystem;
    }
}
