package com.algorithms.exceptions;

public class NoSuchAlgorithmException extends RuntimeException{

    public NoSuchAlgorithmException() {
    }

    public NoSuchAlgorithmException(String message) {
        super(message);
    }
}
