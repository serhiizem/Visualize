package com.algorithms.exceptions;

import com.algorithms.generation.GenerationStrategy;

public class InvocationException extends RuntimeException {
    /*public InvocationException(GenerationStrategy algorithm,
                               Throwable cause) {
        super("There has been an error during an invocation of the " +
                "sorting method of class " + algorithm.getClass().getSimpleName() +
                ". Please, check whether method " + " you are trying to " +
                "invoke exists in the requested class or has not been declared private", cause);
    }
    */
    public InvocationException(GenerationStrategy generationAlgorithm,
                               Throwable cause) {
        this(generationAlgorithm.getClass().getSimpleName(), cause);
    }

    public InvocationException(String targetClassName,
                               Throwable cause) {
        super("There has been an error during an invocation of the " +
                "the filler method of class " + targetClassName +
                ". Please, check if a filler method you are trying to invoke has " +
                "not been declared private. This error may also occur if there has " +
                "been an error during an actual work of the given method. ", cause);
    }
}
