package com.mysmarthome.identityandaccess.application.dtos;

import com.mysmarthome.identityandaccess.domain.valueobjects.UserId;
import com.mysmarthome.identityandaccess.domain.valueobjects.UserRole;
import com.mysmarthome.identityandaccess.domain.valueobjects.UserStatus;

public record TokenAuthorizationResponse(UserId id, UserRole role, UserStatus status) {
}
