package com.ecommerce.inventory.exception;

public class InvalidInventoryDataException extends RuntimeException {
    public InvalidInventoryDataException(String message) {
        super(message);
    }
}
