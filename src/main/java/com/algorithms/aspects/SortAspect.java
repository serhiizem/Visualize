package com.algorithms.aspects;

import com.algorithms.util.SortRepresentation;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

import static java.lang.System.currentTimeMillis;

@Aspect
@Component
public class SortAspect {

    private SortRepresentation sortRepresentation;

    @Autowired
    public SortAspect(SortRepresentation sortRepresentation) {
        this.sortRepresentation = sortRepresentation;
    }

    @Before("execution(void sort())")
    public void startSort() {
        sortRepresentation.setSortStarted(true);
        sortRepresentation.setStartTime(currentTimeMillis());
    }

    @After("execution(void sort())")
    public void finishSort() {
        Long elapsedTime = currentTimeMillis() - sortRepresentation.getStartTime();
        sortRepresentation.setElapsedTime(elapsedTime);
//        pauseExecutionForNumberOfSeconds(2);

        sortRepresentation.setSortStarted(false);
    }

    private void pauseExecutionForNumberOfSeconds(int secondsToPause) {
        try {
            TimeUnit.SECONDS.sleep(secondsToPause);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}