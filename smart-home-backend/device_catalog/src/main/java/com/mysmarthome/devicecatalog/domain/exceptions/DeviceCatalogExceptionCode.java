package com.mysmarthome.devicecatalog.domain.exceptions;

import com.mysmarthome.exceptions.SmartHomeExceptionDetails;

public class DeviceCatalogExceptionCode {
    public static final SmartHomeExceptionDetails DeviceCatalogInvalidDeviceId = new SmartHomeExceptionDetails("DEVICE_CATALOG_INVALID_ID", "Invalid device deviceProductId");
    public static final SmartHomeExceptionDetails DeviceCatalogEventTypeDoesNotExistForDevice = new SmartHomeExceptionDetails("DEVICE_CATALOG_EVENT_TYPE_DOES_NOT_EXIST", "Device does not have the required event type");
    public static final SmartHomeExceptionDetails DeviceCatalogEventTypeAlreadyExists = new SmartHomeExceptionDetails("DEVICE_CATALOG_EVENT_CODE_ALREADY_EXISTS", "Device event code already exists");
    public static final SmartHomeExceptionDetails DeviceCatalogValueTypeAlreadyExists = new SmartHomeExceptionDetails("DEVICE_CATALOG_VALUE_CODE_ALREADY_EXISTS", "Device value code already exists");
    public static final SmartHomeExceptionDetails DeviceCatalogInvalidDeviceValueRange = new SmartHomeExceptionDetails("DEVICE_CATALOG_DEVICE_VALUE_RANGE_INVALID", "Device value range is not valid");
    public static final SmartHomeExceptionDetails DeviceCatalogDeviceValueTypeRangeMissing = new SmartHomeExceptionDetails("DEVICE_CATALOG_DEVICE_VALUE_RANGE_MISSING", "A device value of type range must have a range set");
    public static final SmartHomeExceptionDetails DeviceCatalogDeviceValueNotFoundByCode = new SmartHomeExceptionDetails("DEVICE_CATALOG_DEVICE_VALUE_NOT_FOUND", "Device does not have the configured value code");
    public static final SmartHomeExceptionDetails DeviceCatalogUnknownDeviceValueType = new SmartHomeExceptionDetails("DEVICE_CATALOG_DEVICE_VALUE_UNKNOWN", "Unknown device value type");
}
