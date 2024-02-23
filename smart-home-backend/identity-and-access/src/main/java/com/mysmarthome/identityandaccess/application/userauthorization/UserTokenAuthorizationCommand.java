package com.mysmarthome.identityandaccess.application.userauthorization;

import jakarta.validation.constraints.NotEmpty;

public record UserTokenAuthorizationCommand(@NotEmpty String accessToken, @NotEmpty String allowedStatus) {
    public UserTokenAuthorizationCommand withAllowedStatus(String pendingActivation) {

        return new UserTokenAuthorizationCommand(accessToken, pendingActivation);
    }
}
