package com.algorithms.exceptions;

import com.algorithms.generation.GenerationStrategy;

public class FillerInvocationException extends InvocationException {
    public FillerInvocationException(GenerationStrategy fillerObject,
                                     String name, ReflectiveOperationException e) {
        super(fillerObject, e);
    }
}
