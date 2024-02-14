package com.mysmarthome.exceptions;

public class SmartHomeException extends RuntimeException {
    private final SmartHomeExceptionDetails details;

    public SmartHomeException(SmartHomeExceptionDetails details) {
        super(details.debugMessage());
        this.details = details;
    }

    public String code() {
        return details.code();
    }

    public String debugMessage() {
        return details.debugMessage();
    }
}
