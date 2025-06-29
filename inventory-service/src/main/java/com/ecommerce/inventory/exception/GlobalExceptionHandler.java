package com.ecommerce.inventory.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InventoryNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(InventoryNotFoundException exception) {
        Map<String, Object> error = new HashMap<>();
        error.put("error", exception.getMessage());
        error.put("status", "404");
        error.put("timestamp", String.valueOf(System.currentTimeMillis()));
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidInventoryDataException.class)
    public ResponseEntity<?> handleInvalidInventoryDataException(InvalidInventoryDataException exception) {
        Map<String, Object> error = new HashMap<>();
        error.put("error", exception.getMessage());
        error.put("status", "400");
        error.put("timestamp", String.valueOf(System.currentTimeMillis()));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(InternalServerException.class)
    public ResponseEntity<?> handleInternalServerException(InternalServerException ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now());
        error.put("error",  ex.getMessage());
        error.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
