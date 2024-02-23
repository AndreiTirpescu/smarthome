package com.mysmarthome.identityandaccess.application.userauthorization;

import jakarta.validation.constraints.NotEmpty;

public record UserRefreshTokenCommand(@NotEmpty String refreshToken) {
}
