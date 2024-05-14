package com.mysmarthome.homesystemmanagement.domain.exceptions;

import com.mysmarthome.exceptions.SmartHomeExceptionDetails;

public class HomeSystemExceptionCode {
    public static final SmartHomeExceptionDetails InvalidHomeSystemId = new SmartHomeExceptionDetails("HOME_SYSTEM_MANAGEMENT_INVALID_ID", "Invalid smart home id");
    public static final SmartHomeExceptionDetails HomeSystemMustHaveAName = new SmartHomeExceptionDetails("HOME_SYSTEM_EMPTY_NAME", "A home system must have a name");
}
