package com.algorithms.exceptions;

import com.algorithms.sorts.Sorting;

import java.lang.reflect.Method;

public class QueueDeactivatingInvocationException extends InvocationException{
    public QueueDeactivatingInvocationException(Sorting targetClass,
                                                Method method,
                                                Throwable cause) {
        super(targetClass.getClass().getSimpleName(), method.getName(), cause);
    }
}
