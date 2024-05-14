package com.mysmarthome.homesystemmanagement.domain.valueobjects;

import com.mysmarthome.exceptions.SmartHomeException;
import com.mysmarthome.homesystemmanagement.domain.exceptions.HomeSystemExceptionCode;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Setter(AccessLevel.PRIVATE)
@Getter
@Embeddable
public class HomeSystemId implements Serializable {

    private UUID id;

    public HomeSystemId(UUID id) {
        this.id = id;
    }

    public HomeSystemId(String id) {
        try {
            this.id = UUID.fromString(id);
        } catch (RuntimeException ex) {
            throw new SmartHomeException(HomeSystemExceptionCode.InvalidHomeSystemId);
        }
    }
    @Override
    public String toString() {
        return id.toString();
    }
}
