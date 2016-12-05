package com.algorithms.exceptions;

public class NonExistingArrayException extends RuntimeException {
    public NonExistingArrayException(String message) {
        super(message);
    }

    public NonExistingArrayException(String message, Throwable cause) {
        super(message, cause);
    }
}
