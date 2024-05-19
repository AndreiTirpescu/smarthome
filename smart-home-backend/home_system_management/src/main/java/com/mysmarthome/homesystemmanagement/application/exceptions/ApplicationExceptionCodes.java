package com.mysmarthome.homesystemmanagement.application.exceptions;

import com.mysmarthome.exceptions.SmartHomeExceptionDetails;

public class ApplicationExceptionCodes {
    public static final SmartHomeExceptionDetails UserAlreadyHasAHomeSystem = new SmartHomeExceptionDetails("HOME_SYSTEM_ALREADY_EXISTS", "User already owns a homesystem");
}
