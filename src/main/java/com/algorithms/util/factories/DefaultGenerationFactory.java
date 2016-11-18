package com.algorithms.util.factories;

import com.algorithms.entity.GenerationType;
import com.algorithms.exceptions.NoSuchAlgorithmException;
import com.algorithms.service.Generating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class DefaultGenerationFactory implements GenerationFactory {

    @Qualifier("ascendingGeneration") private Generating ascendingGeneration;

    @Autowired
    public DefaultGenerationFactory(Generating ascendingGeneration) {
        this.ascendingGeneration = ascendingGeneration;
    }

    @Override
    public Generating getGenerationAlgorithm(GenerationType type) {
        switch (type) {
            case ASC_ORDER:
                return ascendingGeneration;
            default:
                throw new NoSuchAlgorithmException("Algorithm was not specified!");
        }
    }
}
