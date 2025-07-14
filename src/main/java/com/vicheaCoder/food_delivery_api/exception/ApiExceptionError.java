package com.vicheaCoder.food_delivery_api.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ApiExceptionError {
    private String message;
    private String details;
    private LocalDateTime timestamp;
    private int status;
}
