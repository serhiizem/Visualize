package com.algorithms.exceptions;

/**
 * Done
 */
//TODO: common exception
public class NoSuchParentMethodException extends RuntimeException {
    public NoSuchParentMethodException(Object targetClass, String methodName, Throwable cause) {
        this("You are trying to get a method " + methodName + " which does not exist " +
                "in the requested class " + targetClass.getClass().getSimpleName(), cause);
    }

    public NoSuchParentMethodException(String message, Throwable cause) {
        super(message, cause);
    }
}
