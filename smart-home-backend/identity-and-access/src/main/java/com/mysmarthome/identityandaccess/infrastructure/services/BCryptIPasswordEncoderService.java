package com.mysmarthome.identityandaccess.infrastructure.services;

import com.mysmarthome.identityandaccess.domain.infrastructure.IPasswordEncoder;
import com.mysmarthome.identityandaccess.domain.valueobjects.EncodedPassword;
import com.mysmarthome.identityandaccess.domain.valueobjects.PlainTextPassword;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BCryptIPasswordEncoderService implements IPasswordEncoder {

    @Override
    public EncodedPassword encodePassword(PlainTextPassword fromPlainText) {
        var bcryptEncoder = new BCryptPasswordEncoder();
        return new EncodedPassword(bcryptEncoder.encode(fromPlainText.toString()));
    }

    @Override
    public boolean matches(String plainTextPassword, EncodedPassword withEncoded) {
        return new BCryptPasswordEncoder().matches(plainTextPassword, withEncoded.toString());
    }
}
