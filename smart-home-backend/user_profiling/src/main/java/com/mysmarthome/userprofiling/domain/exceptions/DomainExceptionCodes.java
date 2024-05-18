package com.mysmarthome.userprofiling.domain.exceptions;

import com.mysmarthome.exceptions.SmartHomeExceptionDetails;

public class DomainExceptionCodes {
    public static final SmartHomeExceptionDetails InvalidProfileId = new SmartHomeExceptionDetails("USER_PROFILING_INVALID_PROFILE_ID", "Invalid profile id");
    public static final SmartHomeExceptionDetails InvalidIdentity = new SmartHomeExceptionDetails("USER_PROFILING_INVALID_IDENTITY", "Invalid identity for user");
    public static final SmartHomeExceptionDetails InvalidPersonaDetails = new SmartHomeExceptionDetails("USER_PROFILING_INVALID_PERSONA_DETAILS", "A user must have a firstName and a lastName");
    public static final SmartHomeExceptionDetails UnknownCountryCode = new SmartHomeExceptionDetails("USER_PROFILING_UNKNOWN_COUNTRY_CODES", "Unknown country code");
    public static final SmartHomeExceptionDetails InvalidTagException = new SmartHomeExceptionDetails("USER_PROFILING_INVALID_TAG", "Invalid profile tag");
}
