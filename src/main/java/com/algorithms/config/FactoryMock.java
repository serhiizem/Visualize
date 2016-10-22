package com.algorithms.config;

import com.algorithms.sorts.BubbleSort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FactoryMock {

    @Bean
    public BubbleSort getBubbleSort() {
        return new BubbleSort();
    }
}