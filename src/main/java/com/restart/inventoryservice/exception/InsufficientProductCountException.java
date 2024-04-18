package com.restart.inventoryservice.exception;

public class InsufficientProductCountException extends RuntimeException {

    public InsufficientProductCountException() {
        super("Product count is insufficient");
    }

    public InsufficientProductCountException(String message) {
        super(message);
    }

    public InsufficientProductCountException(String message, Throwable cause) {
        super(message, cause);
    }
}