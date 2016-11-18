package com.algorithms.exceptions;

public class RequestedArraySizeException extends RuntimeException {
    public RequestedArraySizeException() {}

    public RequestedArraySizeException(String message) {
        super(message);
    }
}
