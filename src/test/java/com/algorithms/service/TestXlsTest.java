package com.algorithms.service;

import com.algorithms.entity.SortRepresentation;
import com.algorithms.util.Queue;
import org.junit.Before;
import org.junit.Test;

public class TestXlsTest {

    private Queue<SortRepresentation> sortRepresentationQueue;

    @Before
    public void setUp() throws Exception {
        sortRepresentationQueue = new Queue<>();
    }

    @Test
    public void test() {
        StatisticsService sortsStatisticsService =
                new StatisticsService(sortRepresentationQueue);

        sortsStatisticsService.invokeSorters();
    }

    @Test
    public void Scatter() {

    }
}