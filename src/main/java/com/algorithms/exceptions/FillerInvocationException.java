package com.algorithms.exceptions;

public class FillerInvocationException extends RuntimeException {
    public FillerInvocationException(String message, ReflectiveOperationException e) {
        super(message, e);
    }
}
