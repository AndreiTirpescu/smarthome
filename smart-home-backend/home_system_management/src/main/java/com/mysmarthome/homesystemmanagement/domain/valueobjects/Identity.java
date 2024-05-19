package com.mysmarthome.homesystemmanagement.domain.valueobjects;

import com.mysmarthome.exceptions.SmartHomeException;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

import static com.mysmarthome.homesystemmanagement.domain.exceptions.HomeSystemExceptionCode.InvalidIdentity;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Setter(AccessLevel.PRIVATE)
@Getter
@Embeddable
public class Identity implements Serializable {
    private String identityId;

    public Identity(String identityId) {
        try {
            this.identityId = UUID.fromString(identityId).toString();
        } catch (IllegalArgumentException e) {
            throw new SmartHomeException(InvalidIdentity);
        }
    }

    public String id() {
        return identityId;
    }
}