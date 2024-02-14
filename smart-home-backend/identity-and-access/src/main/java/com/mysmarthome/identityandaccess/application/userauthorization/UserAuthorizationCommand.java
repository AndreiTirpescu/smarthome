package com.mysmarthome.identityandaccess.application.userauthorization;

import jakarta.validation.constraints.NotEmpty;

public record UserAuthorizationCommand(@NotEmpty String accessToken) {
}
