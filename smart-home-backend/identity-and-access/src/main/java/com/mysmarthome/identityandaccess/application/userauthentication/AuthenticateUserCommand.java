package com.mysmarthome.identityandaccess.application.userauthentication;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record AuthenticateUserCommand(@Email String email, @NotEmpty String password) {
}
