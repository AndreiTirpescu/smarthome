package com.mysmarthome.devicecatalog.domain.exceptions;

import com.mysmarthome.exceptions.SmartHomeExceptionDetails;

public class DeviceCatalogExceptionCode {
    public static final SmartHomeExceptionDetails DeviceCatalogInvalidDeviceId = new SmartHomeExceptionDetails("DEVICE_CATALOG_INVALID_ID", "Invalid device deviceProductId");
    public static final SmartHomeExceptionDetails DeviceCatalogEventTypeDoesNotExistForDevice = new SmartHomeExceptionDetails("DEVICE_CATALOG_EVENT_TYPE_DOES_NOT_EXIST", "Device does not have the required event type");
}
