package com.algorithms.controller;

public class RequestedArraySizeException extends RuntimeException {
    public RequestedArraySizeException() {}

    public RequestedArraySizeException(String message) {
        super(message);
    }
}
