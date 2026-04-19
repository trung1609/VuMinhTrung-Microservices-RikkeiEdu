package com.trung.userservice.exception;

import com.trung.userservice.dto.ApiErrorResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return new ResponseEntity<>(
                new ApiErrorResponse(
                        Map.of("error", ex.getMessage()),
                        "Resource Not Found",
                        LocalDateTime.now(),
                        HttpStatus.NOT_FOUND.value()
                ),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(ResourceConflictException.class)
    public ResponseEntity<ApiErrorResponse> handleResourceConflictException(ResourceConflictException ex) {
        return new ResponseEntity<>(
                new ApiErrorResponse(
                        Map.of("error", ex.getMessage()),
                        "Resource Conflict",
                        LocalDateTime.now(),
                        HttpStatus.CONFLICT.value()
                ),
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(
                        java.util.stream.Collectors.toMap(
                                FieldError::getField,
                                DefaultMessageSourceResolvable::getDefaultMessage
                        )
                );

        return new ResponseEntity<>(
                new ApiErrorResponse(
                        errors,
                        "Validation Failed",
                        LocalDateTime.now(),
                        HttpStatus.BAD_REQUEST.value()
                ),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ApiErrorResponse> handleInvalidCredentialsException(InvalidCredentialsException ex) {
        return new ResponseEntity<>(
                new ApiErrorResponse(
                        Map.of("error", ex.getMessage()),
                        "Invalid Credentials",
                        LocalDateTime.now(),
                        HttpStatus.UNAUTHORIZED.value()
                ),
                HttpStatus.UNAUTHORIZED
        );
    }
}
