package com.mysmarthome.identityandaccess.domain.valueobjects;


import com.mysmarthome.exceptions.SmartHomeException;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.With;

import static com.mysmarthome.identityandaccess.domain.exceptions.DomainExceptionCode.IamInvalidEncodedPassword;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Setter(AccessLevel.PRIVATE)
@With
public class EncodedPassword {

    private String password;

    public EncodedPassword(String encodedPassword) {
        if (encodedPassword == null || encodedPassword.isBlank()) {
            throw new SmartHomeException(IamInvalidEncodedPassword);
        }

        this.password = encodedPassword;
    }

    @Override
    public String toString() {
        return password;
    }
}
