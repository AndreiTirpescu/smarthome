package com.mysmarthome.identityandaccess.application.useractivation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

public record ActivateUserCommand(@NotEmpty String activationToken, @JsonIgnore @Schema(hidden = true) String userId) {
    public ActivateUserCommand withUserId(String userId) {
        return new ActivateUserCommand(activationToken, userId);
    }
}
