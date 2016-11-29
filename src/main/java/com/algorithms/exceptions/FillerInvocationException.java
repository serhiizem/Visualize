package com.algorithms.exceptions;

import com.algorithms.generation.GenerationStrategy;

import java.lang.reflect.Method;

/**
 * Done
 */
public class FillerInvocationException extends InvocationException {
    public FillerInvocationException(GenerationStrategy targetClass,
                                     Method method, Throwable cause) {
        super(targetClass.getClass().getSimpleName(), method.getName(), cause);
    }
}
