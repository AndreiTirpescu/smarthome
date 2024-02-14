package com.mysmarthome.identityandaccess.infrastructure.token;

import lombok.SneakyThrows;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.UUID;

public record RSASecureTokenKey(UUID id, String privateKey, String publicKey) {
    @SneakyThrows
    public KeyPair keyPair() {
        KeyFactory kf = KeyFactory.getInstance("RSA");

        PKCS8EncodedKeySpec keySpecPKCS8 = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey));
        X509EncodedKeySpec keySpecX509 = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKey));

        return new KeyPair(kf.generatePublic(keySpecX509), kf.generatePrivate(keySpecPKCS8));
    }
}
