package com.mysmarthome.identityandaccess.domain.exceptions;

import com.mysmarthome.exceptions.SmartHomeExceptionDetails;

public final class DomainExceptionCode {
    public static final SmartHomeExceptionDetails IamInvalidUserId = new SmartHomeExceptionDetails("IAM_INVALID_USER_ID", "Invalid user id");
    public static final SmartHomeExceptionDetails IamInvalidUserEmail = new SmartHomeExceptionDetails("IAM_INVALID_USER_EMAIL", "A user must have a valid email address");
    public static final SmartHomeExceptionDetails IamInvalidPlainTextPassword = new SmartHomeExceptionDetails("IAM_INVALID_PASSWORD", "A password must contain at least 8 alphanumeric characters and at least one symbol");
    public static final SmartHomeExceptionDetails IamInvalidEncodedPassword = new SmartHomeExceptionDetails("IAM_INVALID_ENCODED_PASSWORD", "Password is null or empty");
}
