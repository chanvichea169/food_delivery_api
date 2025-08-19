package com.vicheaCoder.food_delivery_api.exception;

import jakarta.persistence.EntityExistsException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handle database unique constraint violations
    @ExceptionHandler({ConstraintViolationException.class, DataIntegrityViolationException.class, EntityExistsException.class})
    public ResponseEntity<ApiExceptionError> handleConstraintViolation(Exception ex) {
        ApiExceptionError error = ApiExceptionError.builder()
                .message("Duplicate value not allowed")
                .details("The provided value already exists in the system")
                .status(HttpStatus.CONFLICT.value()) // 409 instead of 500
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    // Handle validation errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiExceptionError> handleValidation(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();

        ApiExceptionError error = ApiExceptionError.builder()
                .message("Validation Error")
                .details(message)
                .status(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // Handle all other exceptions (fallback)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiExceptionError> handleException(Exception ex) {
        ApiExceptionError error = ApiExceptionError.builder()
                .message("Internal Server Error")
                .details(ex.getMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Handle resource not found
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiExceptionError> handleNotFound(ResourceNotFoundException ex) {
        ApiExceptionError error = ApiExceptionError.builder()
                .message("Resource Not Found")
                .details(ex.getMessage())
                .status(HttpStatus.NOT_FOUND.value()) // 404
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

}
