package com.mysmarthome.userprofiling.domain.valueobjects;

import com.mysmarthome.exceptions.SmartHomeException;
import com.sanctionco.jmail.JMail;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

import static com.mysmarthome.userprofiling.domain.exceptions.DomainExceptionCodes.InvalidIdentity;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Setter(AccessLevel.PRIVATE)
@Getter
@Embeddable
public class Identity implements Serializable {
    private String identityId;

    private String email;

    public Identity(String identityId, String email) {
        try {
            this.identityId = UUID.fromString(identityId).toString();
        } catch (IllegalArgumentException e) {
            throw new SmartHomeException(InvalidIdentity);
        }

        if (JMail.isInvalid(email)) {
            throw new SmartHomeException(InvalidIdentity);
        }

        this.email = email;
    }

    public String id() {
        return identityId.toString();
    }

    public String email() {
        return email;
    }
}
