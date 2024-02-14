package com.mysmarthome.identityandaccess.application.config;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.UUID;

public class GenerateKeysApplication {

    public static void main(String[] args) {
        KeyPairGenerator generator = null;
        try {
            generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(2048);
            KeyPair pair = generator.generateKeyPair();

            System.out.println(UUID.randomUUID().toString());
            System.out.println(Base64.getEncoder().encodeToString(pair.getPublic().getEncoded()));
            System.out.println(Base64.getEncoder().encodeToString(pair.getPrivate().getEncoded()));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }
}
