package com.mysmarthome.exceptions;

public class SmartHomeExceptionDetails {
    private final String code;
    private final String debugMessage;

    public SmartHomeExceptionDetails(String code, String debugMessage) {
        this.code = code;
        this.debugMessage = debugMessage;
    }

    public String code() {
        return code;
    }

    public String debugMessage() {
        return debugMessage;
    }
}
