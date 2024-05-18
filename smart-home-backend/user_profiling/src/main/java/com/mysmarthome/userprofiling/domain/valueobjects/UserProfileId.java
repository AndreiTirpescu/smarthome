package com.mysmarthome.userprofiling.domain.valueobjects;

import com.mysmarthome.exceptions.SmartHomeException;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

import static com.mysmarthome.userprofiling.domain.exceptions.DomainExceptionCodes.InvalidProfileId;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Setter(AccessLevel.PRIVATE)
@Getter
@Embeddable
public class UserProfileId {
    private UUID id;

    public UserProfileId(UUID id) {
        this.id = id;
    }

    public UserProfileId(String id) {
        try {
            this.id = UUID.fromString(id);
        } catch (RuntimeException ex) {
            throw new SmartHomeException(InvalidProfileId);
        }
    }

    @Override
    public String toString() {
        return id.toString();
    }
}
