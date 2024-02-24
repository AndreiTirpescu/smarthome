package com.mysmarthome.devicecatalog.application.exceptions;

import com.mysmarthome.exceptions.SmartHomeExceptionDetails;

public class DeviceCatalogApplicationExceptionCode {
    public static final SmartHomeExceptionDetails DeviceCatalogDeviceNotFoundById = new SmartHomeExceptionDetails("DEVICE_CATALOG_DEVICE_NOT_FOUND_BY_ID", "Device not found by id");
    public static final SmartHomeExceptionDetails DeviceCatalogDeviceTypeCodeAlreadyUsed = new SmartHomeExceptionDetails("DEVICE_CATALOG_DEVICE_TYPE_CODE_ALREADY_USED", "Device type code already used");
    public static final SmartHomeExceptionDetails DeviceCatalogDeviceEventCodeAlreadyUsed = new SmartHomeExceptionDetails("DEVICE_CATALOG_DEVICE_EVENT_CODE_ALREADY_USED", "Device event code already used");
}
