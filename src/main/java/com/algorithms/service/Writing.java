package com.algorithms.service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public interface Writing {
    void generateStatistics() throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException, IOException;
}
