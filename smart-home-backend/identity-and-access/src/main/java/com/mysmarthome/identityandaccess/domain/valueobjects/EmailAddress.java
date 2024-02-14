package com.mysmarthome.identityandaccess.domain.valueobjects;

import com.mysmarthome.exceptions.SmartHomeException;
import com.sanctionco.jmail.JMail;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.With;

import static com.mysmarthome.identityandaccess.domain.exceptions.DomainExceptionCode.IamInvalidUserEmail;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Setter(AccessLevel.PRIVATE)
@With
public class EmailAddress {

    private String email;

    public EmailAddress(String email) {
        if (JMail.isInvalid(email)) {
            throw new SmartHomeException(IamInvalidUserEmail);
        }

        this.email = email;
    }

    @Override
    public String toString() {
        return email;
    }
}
