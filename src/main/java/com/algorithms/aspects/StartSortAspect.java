package com.algorithms.aspects;

import com.algorithms.util.SortRepresentation;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class StartSortAspect {

    private SortRepresentation sortRepresentation;

    @Autowired
    public StartSortAspect(SortRepresentation sortRepresentation) {
        this.sortRepresentation = sortRepresentation;
    }

    @Before("execution(void sort())")
    public void startSort() {
        sortRepresentation.setSortStarted(true);
    }

    @After("execution(void sort())")
    public void finishSort() {
        sortRepresentation.setSortStarted(false);
    }
}
