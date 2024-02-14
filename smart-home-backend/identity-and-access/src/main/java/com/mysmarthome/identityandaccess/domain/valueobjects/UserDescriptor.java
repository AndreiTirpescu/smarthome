package com.mysmarthome.identityandaccess.domain.valueobjects;

public record UserDescriptor(UserId id, UserRole role, UserStatus status) {
}
