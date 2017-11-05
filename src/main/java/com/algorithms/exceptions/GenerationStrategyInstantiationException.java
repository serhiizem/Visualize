package com.algorithms.exceptions;

public class GenerationStrategyInstantiationException extends AlgorithmInstantiationException {
    public GenerationStrategyInstantiationException(Class strategyClass, Throwable cause) {
        this("Class " + strategyClass.getSimpleName() + "failed to be instantiated." +
                "Please, check whether the class you are trying to instantiate " +
                "has a nullary constructor or you are not trying to instantiate " +
                "an abstract class or interface. Initial cause: ", cause);
    }

    public GenerationStrategyInstantiationException(String message, Throwable cause) {
        super(message, cause);
    }
}
