package com.algorithms.util.factories;

import com.algorithms.generation.GenerationStrategy;
import com.algorithms.entity.GenerationType;
import com.algorithms.exceptions.NoSuchAlgorithmException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class DefaultGenerationFactory implements GenerationFactory {

    @Qualifier("ascendingGeneration") private GenerationStrategy ascendingGeneration;

    @Autowired
    public DefaultGenerationFactory(GenerationStrategy ascendingGeneration) {
        this.ascendingGeneration = ascendingGeneration;
    }

    @Override
    public GenerationStrategy getGenerationAlgorithm(GenerationType type) {
        switch (type) {
            case ASC_ORDER:
                return ascendingGeneration;
        }
        throw new NoSuchAlgorithmException("Algorithm was not specified!");
    }
}
