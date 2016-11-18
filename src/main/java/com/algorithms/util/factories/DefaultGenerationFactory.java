package com.algorithms.util.factories;

import com.algorithms.entity.GenerationType;
import com.algorithms.exceptions.NoSuchAlgorithmException;
import com.algorithms.generation.GenerationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class DefaultGenerationFactory implements GenerationFactory {

    @Qualifier("ascendingGeneration") private GenerationStrategy ascendingGeneration;
    @Qualifier("descendingGeneration") private GenerationStrategy descendingGeneration;
    @Qualifier("randomGeneration") private GenerationStrategy randomGeneration;

    @Autowired
    public DefaultGenerationFactory(GenerationStrategy ascendingGeneration,
                                    GenerationStrategy descendingGeneration,
                                    GenerationStrategy randomGeneration) {
        this.ascendingGeneration = ascendingGeneration;
        this.descendingGeneration = descendingGeneration;
        this.randomGeneration = randomGeneration;
    }

    @Override
    public GenerationStrategy getGenerationAlgorithm(GenerationType type) {
        switch (type) {
            case ASC_ORDER:
                return ascendingGeneration;
            case DESC_ORDER:
                return descendingGeneration;
            case RANDOM_ORDER:
                return randomGeneration;
        }
        throw new NoSuchAlgorithmException("Algorithm was not specified!");
    }
}
