package com.algorithms.exceptions;

public class SortingAlgorithmInstantiationException extends AlgorithmInstantiationException {
    public SortingAlgorithmInstantiationException(Class targetClass, Throwable cause) {
        this(targetClass.getSimpleName(), cause);
    }

    public SortingAlgorithmInstantiationException(String targetClassName, Throwable cause) {
        super("Class " + targetClassName + "failed to be instantiated." +
                "Please check whether a correct parameter type has been specified for the" +
                "constructor, nor you are not trying to instantiate " +
                "an abstract class or interface. The following may also occur if there is " +
                "no such method under the requested class. Initial cause: ", cause);
    }
}
