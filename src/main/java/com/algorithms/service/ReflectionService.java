package com.algorithms.service;

import com.algorithms.annotations.Filler;
import com.algorithms.annotations.Sorter;
import com.algorithms.entity.Range;
import com.algorithms.entity.SortRepresentation;
import com.algorithms.exceptions.ElapsedTimeInvocationException;
import com.algorithms.exceptions.FillerInvocationException;
import com.algorithms.exceptions.GenerationStrategyInstantiationException;
import com.algorithms.exceptions.NoSuchParentMethodException;
import com.algorithms.exceptions.QueueDeactivatingInvocationException;
import com.algorithms.exceptions.SortingAlgorithmInstantiationException;
import com.algorithms.exceptions.SortingMethodInvocationException;
import com.algorithms.generation.GenerationStrategy;
import com.algorithms.sorts.Sorting;
import com.algorithms.util.Queue;
import org.reflections.Reflections;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReflectionService {

    private Reflections reflections = new Reflections();

    public List<Class<?>> getSubclassesOfType(Class<?> targetClass) {
        return reflections.getSubTypesOf(targetClass)
                .stream().collect(Collectors.toList());
    }

    public List<Method> getAllMethodsOfClassWithTheGivenAnnotation(Class<?> targetClass,
                                                                    Class<? extends Annotation> annotation) {
        return this.getSubclassesOfType(targetClass)
                .stream().map(Class::getDeclaredMethods)
                .flatMap(Arrays::stream)
                .filter(method -> method.isAnnotationPresent(annotation))
                .collect(Collectors.toList());
    }

    public Long getElapsedTimeForTheGivenSort(Sorting sortingAlgorithmObject) {
        Method elapsedTimeGetter = null;
        try {
            elapsedTimeGetter =
                    this.getSubclassMethodForGivenInstance(
                            "getElapsedTime", sortingAlgorithmObject);
            return (Long) elapsedTimeGetter.invoke(sortingAlgorithmObject);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new ElapsedTimeInvocationException(sortingAlgorithmObject, elapsedTimeGetter, e);
        }
    }

    private Method getSubclassMethodForGivenInstance(String methodName,
                                                    Object instance,
                                                    Class... args) {
        Method requestedMethod;
        try {
            requestedMethod = instance.getClass().getSuperclass()
                    .getMethod(methodName, args);
            return requestedMethod;
        } catch (NoSuchMethodException e) {
            throw new NoSuchParentMethodException(instance, methodName, e);
        }
    }

    public Sorting instantiateSortingAlgorithm(Class sortingClass,
                                               Queue<SortRepresentation> queue) {
        try {
            return (Sorting) sortingClass.getConstructor(Queue.class)
                    .newInstance(queue);
        } catch (InstantiationException | IllegalAccessException |
                NoSuchMethodException | InvocationTargetException e) {
            throw new SortingAlgorithmInstantiationException(sortingClass, e);
        }
    }

    public void preventQueueToBeFilledByTheSort(Sorting sortingObject) {
        Method setAnalysed = null;
        try {
            setAnalysed =
                    this.getSubclassMethodForGivenInstance(
                            "setAnalysed", sortingObject, Boolean.class);
            setAnalysed.invoke(sortingObject, true);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new QueueDeactivatingInvocationException(sortingObject, setAnalysed, e);
        }
    }

    public void sortArrayWithTheGivenAlgorithm(Comparable[] arrayToSort,
                                               Sorting sortingObject) {
        Method[] declaredMethods = sortingObject.getClass().getDeclaredMethods();
        for (Method method : declaredMethods) {
            Sorter sorterAnnotation = method.getAnnotation(Sorter.class);
            if (sorterAnnotation != null) {
                this.invokeSortingMethodOnArray(sortingObject, method, arrayToSort);
            }
        }
    }

    private void invokeSortingMethodOnArray(Sorting sortingObject,
                                           Method method,
                                           Comparable[] arrayToSort) {
        try {
            method.invoke(sortingObject, new Object[]{ arrayToSort });
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new SortingMethodInvocationException(sortingObject, method, e);
        }
    }

    public Comparable[] createArrayFromRangeUsingGivenStrategy(Range range,
                                                               Class strategyClass) {
        Comparable[] generatedArray = null;
        GenerationStrategy generationStrategy;
        Method[] declaredMethods = strategyClass.getDeclaredMethods();
        for (Method method : declaredMethods) {
            Filler fillerAnnotation = method.getAnnotation(Filler.class);
            if (fillerAnnotation != null) {
                generationStrategy =
                        this.instantiateStrategy(strategyClass);
                generatedArray =
                        this.invokeGenerationMethodOnRange(generationStrategy, method, range);
            }
        }
        return generatedArray;
    }

    private GenerationStrategy instantiateStrategy(Class<?> strategyClass) {
        try {
            return (GenerationStrategy) strategyClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new GenerationStrategyInstantiationException(strategyClass, e);
        }
    }

    private Comparable[] invokeGenerationMethodOnRange(GenerationStrategy generationStrategy,
                                                      Method method, Range range) {
        try {
            return (Comparable[]) method.invoke(generationStrategy,
                    range.getArraySize(), range.getMinValue(), range.getMaxValue());
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new FillerInvocationException(generationStrategy, method, e);
        }
    }
}
