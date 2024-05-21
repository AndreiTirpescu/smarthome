package com.mysmarthome.homesystemmanagement.application.exceptions;

import com.mysmarthome.exceptions.SmartHomeExceptionDetails;

public class ApplicationExceptionCodes {
    public static final SmartHomeExceptionDetails UserAlreadyHasAHomeSystem = new SmartHomeExceptionDetails("HOME_SYSTEM_ALREADY_EXISTS", "User already owns a homesystem");
    public static final SmartHomeExceptionDetails HomeSystemNotFoundById = new SmartHomeExceptionDetails("HOME_SYSTEM_NOT_FOUND_BY_ID", "Home system not found by id");
}
