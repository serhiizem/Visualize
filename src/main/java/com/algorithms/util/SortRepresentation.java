package com.algorithms.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Component
public class SortRepresentation {

    private static final Logger log = LoggerFactory.getLogger(SortRepresentation.class);

    private Integer[] intermediate;
    private Long elapsedTime;

    boolean sortStarted = false;

    public SortRepresentation() {
    }

    @PostConstruct
    public void checkSortRepresentationStatus() {
        log.info("SortRepresentation object constructed: {}", this);
    }

    public SortRepresentation(Integer[] intermediate, Long elapsedTime) {
        this.intermediate = intermediate;
        this.elapsedTime = elapsedTime;
    }

    public boolean isSortStarted() {
        return sortStarted;
    }

    public void setSortStarted(boolean sortStarted) {
        this.sortStarted = sortStarted;
    }

    public void setIntermediate(Integer[] intermediate) {
        this.intermediate = intermediate;
    }

    public Integer[] getIntermediate() {
        return intermediate;
    }

    public Long getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(Long elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    @Override
    public String toString() {
        return "SortRepresentation{" +
                "intermediate=" + Arrays.toString(intermediate) +
                ", elapsedTime=" + elapsedTime +
                '}';
    }
}
