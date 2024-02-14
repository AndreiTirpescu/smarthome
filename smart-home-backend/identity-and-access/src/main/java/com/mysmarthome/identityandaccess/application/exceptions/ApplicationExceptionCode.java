package com.mysmarthome.identityandaccess.application.exceptions;

import com.mysmarthome.exceptions.SmartHomeExceptionDetails;


public final class ApplicationExceptionCode {
    public static final SmartHomeExceptionDetails IamUserAlreadyExists = new SmartHomeExceptionDetails("IAM_USER_ALREADY_EXISTS_BY_EMAIL", "User already exists by given email");
    public static final SmartHomeExceptionDetails IamUserNotFoundByEmail = new SmartHomeExceptionDetails("IAM_USER_NOT_FOUND_BY_EMAIL", "User was not found by email address");
    public static final SmartHomeExceptionDetails IamInvalidUserCredentials = new SmartHomeExceptionDetails("IAM_INVALID_USER_CREDENTIALS", "Email or password is not valid");
    public static final SmartHomeExceptionDetails IamUnauthorizedException = new SmartHomeExceptionDetails("IAM_UNAUTHORIZED_EXCEPTION", "User not authorized exception");
    public static final SmartHomeExceptionDetails IamUserNotFoundById = new SmartHomeExceptionDetails("IAM_USER_NOT_FOUND_BY_ID", "User was not found by identifier");
    public static final SmartHomeExceptionDetails IamUserForbidden = new SmartHomeExceptionDetails("IAM_USER_FORBIDDEN", "You are not allowed to view this resource");
    public static final SmartHomeExceptionDetails IamUserAwaitingActivation = new SmartHomeExceptionDetails("IAM_USER_AWAITING_ACTIVATION", "You must activate your account before accessing the resource");
}
