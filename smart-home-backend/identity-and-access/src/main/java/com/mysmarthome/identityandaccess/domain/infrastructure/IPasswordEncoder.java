package com.mysmarthome.identityandaccess.domain.infrastructure;

import com.mysmarthome.identityandaccess.domain.valueobjects.EncodedPassword;
import com.mysmarthome.identityandaccess.domain.valueobjects.PlainTextPassword;

public interface IPasswordEncoder {
    EncodedPassword encodePassword(PlainTextPassword fromPlainText);

    boolean matches(String plainTextPassword, EncodedPassword withEncoded);
}
