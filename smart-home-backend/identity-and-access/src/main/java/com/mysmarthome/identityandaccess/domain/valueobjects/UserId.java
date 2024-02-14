package com.mysmarthome.identityandaccess.domain.valueobjects;

import com.mysmarthome.exceptions.SmartHomeException;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

import static com.mysmarthome.identityandaccess.domain.exceptions.DomainExceptionCode.IamInvalidUserId;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Setter(AccessLevel.PRIVATE)
@Getter
@Embeddable
public class UserId implements Serializable {

    private UUID id;

    public UserId(UUID id) {
        this.id = id;
    }

    public UserId(String id) {
        try {
            this.id = UUID.fromString(id);
        } catch (RuntimeException ex) {
            throw new SmartHomeException(IamInvalidUserId);
        }
    }

    @Override
    public String toString() {
        return id.toString();
    }
}
