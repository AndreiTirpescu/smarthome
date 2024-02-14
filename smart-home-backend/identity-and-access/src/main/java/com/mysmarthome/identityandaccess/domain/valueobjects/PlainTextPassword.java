package com.mysmarthome.identityandaccess.domain.valueobjects;


import com.mysmarthome.exceptions.SmartHomeException;
import lombok.AccessLevel;
import lombok.Setter;
import lombok.With;

import java.util.regex.Pattern;

import static com.mysmarthome.identityandaccess.domain.exceptions.DomainExceptionCode.IamInvalidPlainTextPassword;

@Setter(AccessLevel.PRIVATE)
@With
public class PlainTextPassword {

    private final String password;

    private static final Pattern PWD_REGEX = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%_*#?&])[A-Za-z\\d@_$!%*#?&]{8,}$");

    public PlainTextPassword(String password) {
        if (!PWD_REGEX.matcher(password).matches()) {
            throw new SmartHomeException(IamInvalidPlainTextPassword);
        }

        this.password = password;
    }

    @Override
    public String toString() {
        return password;
    }
}
