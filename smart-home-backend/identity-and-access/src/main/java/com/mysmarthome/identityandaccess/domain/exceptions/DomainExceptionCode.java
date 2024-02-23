package com.mysmarthome.identityandaccess.domain.exceptions;

import com.mysmarthome.exceptions.SmartHomeExceptionDetails;

public final class DomainExceptionCode {
    public static final SmartHomeExceptionDetails IamInvalidUserId = new SmartHomeExceptionDetails("IAM_INVALID_USER_ID", "Invalid user identityId");
    public static final SmartHomeExceptionDetails IamInvalidUserEmail = new SmartHomeExceptionDetails("IAM_INVALID_USER_EMAIL", "A user must have a valid email address");
    public static final SmartHomeExceptionDetails IamInvalidPlainTextPassword = new SmartHomeExceptionDetails("IAM_INVALID_PASSWORD", "A password must contain at least 8 alphanumeric characters and at least one symbol");
    public static final SmartHomeExceptionDetails IamInvalidEncodedPassword = new SmartHomeExceptionDetails("IAM_INVALID_ENCODED_PASSWORD", "Password is null or empty");
    public static final SmartHomeExceptionDetails IamInactiveUserCannotActivate = new SmartHomeExceptionDetails("IAM_INACTIVE_USER_CANNOT_ACTIVATE", "Your user has been deactivated, please contact the administrator");
    public static final SmartHomeExceptionDetails IamUserAlreadyActive = new SmartHomeExceptionDetails("IAM_USER_ALREADY_ACTIVE", "Your account is already active");
    public static final SmartHomeExceptionDetails IamInvalidActivationToken = new SmartHomeExceptionDetails("IAM_INVALID_ACTIVATION_TOKEN", "Invalid activationToken used for activation");
    public static final SmartHomeExceptionDetails IamActivationTokenExpired = new SmartHomeExceptionDetails("IAM_ACTIVATION_TOKEN_EXPIRED", "Activation activationToken expired");
}
