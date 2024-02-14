package com.mysmarthome.identityandaccess.application.userregistration;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record CreateUserCommand(@NotEmpty @Email String email, @NotEmpty String password) {
}
