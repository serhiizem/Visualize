package com.algorithms.exceptions;

public class InvocationException extends RuntimeException {
    public InvocationException(String targetClassName, String methodName, Throwable cause) {
        this("There has been an error during an invocation of method " + methodName +
                " of class " + targetClassName + ". Please, check whether " +
                "requested method has not been declared private. This error " +
                "may also occur if there has been an error during an actual " +
                "work of the given method.", cause);
    }

    public InvocationException(String message, Throwable cause) {
        super(message, cause);
    }
}
