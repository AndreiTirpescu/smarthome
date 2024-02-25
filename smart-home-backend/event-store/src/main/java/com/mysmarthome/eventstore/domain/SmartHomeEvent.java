package com.mysmarthome.eventstore.domain;

import com.mysmarthome.domain.Event;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(schema = "events", name = "events")
public class SmartHomeEvent {
    @Id
    private UUID id;

    @NotNull
    private String key;

    @NotNull
    private Long createdAt;

    private String identity;

    @NotNull
    private String version;

    @NotNull
    private String application;

    @NotNull
    private String payload;

    public static SmartHomeEvent fromEventWrapper(Event wrapper) {

        return new SmartHomeEvent(UUID.fromString(wrapper.id()), wrapper.key(), wrapper.createdAt(), wrapper.identity(),
                wrapper.version(), wrapper.application(), wrapper.payload());
    }
}
