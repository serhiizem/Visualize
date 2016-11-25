package com.algorithms.service;

import com.algorithms.entity.Range;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public interface Writing {
    void generateStatistics() throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException, IOException;
}
