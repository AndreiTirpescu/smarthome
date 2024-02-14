package com.mysmarthome.web.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErrorResponse(String key, Integer status, String debugMessage, List<ErrorDetails> details) {

    public ErrorResponse withDebugMessage(String debugMessage) {
        return new ErrorResponse(key, status, debugMessage, details);
    }
}