package com.mysmarthome.web.exceptions;

import com.mysmarthome.exceptions.SmartHomeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class CustomExceptionMapper {
    private static final String INVALID_FIELD_KEY = "invalid_field";
    private static final String SERVER_ERROR = "server_error";

    private final Map<String, ErrorResponse> exceptionMap;

    public CustomExceptionMapper(@Qualifier("exceptionMap") Map<String, ErrorResponse> exceptionMap) {
        this.exceptionMap = exceptionMap;
    }

    @ExceptionHandler(SmartHomeException.class)
    public ResponseEntity<ErrorResponse> handleDomainException(@NonNull SmartHomeException ex) {
        var errResponse = exceptionMap.get(ex.code().toLowerCase()).withDebugMessage(ex.debugMessage());

        return new ResponseEntity<>(errResponse, HttpStatus.valueOf(errResponse.status()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(@NonNull MethodArgumentNotValidException ex) {
        var details = ex.getFieldErrors().stream()
                .map(fieldError -> new ErrorDetails(fieldError.getField(), fieldError.getDefaultMessage()))
                .toList();

        return new ResponseEntity<>(
                new ErrorResponse(INVALID_FIELD_KEY, HttpStatus.BAD_REQUEST.value(), "Invalid input fields", details),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(@NonNull RuntimeException ex) {
        var details = new ArrayList<ErrorDetails>();

        log.error(ex.getMessage());

        return new ResponseEntity<>(
                new ErrorResponse(INVALID_FIELD_KEY, HttpStatus.INTERNAL_SERVER_ERROR.value(), "There was an issue with the server", details),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
