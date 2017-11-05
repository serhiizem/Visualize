package com.algorithms.exceptions;

import com.algorithms.sorts.Sorting;

import java.lang.reflect.Method;

public class ElapsedTimeInvocationException extends InvocationException {

    public ElapsedTimeInvocationException(Sorting targetClass,
                                          Method method,
                                          Throwable cause) {
        super(targetClass.getClass().getSimpleName(), method.getName(), cause);
    }
}
