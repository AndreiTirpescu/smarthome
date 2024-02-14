package com.mysmarthome.identityandaccess.domain.infrastructure;

public interface ITokenValidator {
    boolean isValid(String accessToken);
}
