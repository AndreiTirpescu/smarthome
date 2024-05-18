package com.mysmarthome.userprofiling.application.exceptions;

import com.mysmarthome.exceptions.SmartHomeExceptionDetails;

public class ApplicationExceptionCodes {
    public static final SmartHomeExceptionDetails ProfileNotFoundById = new SmartHomeExceptionDetails("USER_PROFILING_NOT_FOUND_BY_ID", "User profile not found by id.");
    public static final SmartHomeExceptionDetails ProfileNotFoundByTag = new SmartHomeExceptionDetails("USER_PROFILING_NOT_FOUND_BY_TAG", "User profile not found by tag");
    public static final SmartHomeExceptionDetails ProfileNotFoundByIdentity = new SmartHomeExceptionDetails("USER_PROFILING_NOT_FOUND_BY_IDENTITY", "User profile not found by identity");
}
