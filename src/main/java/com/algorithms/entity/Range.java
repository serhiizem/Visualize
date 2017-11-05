package com.algorithms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class is used to produce objects that encapsulate information
 * regarding boundaries and the size of the array which is to be generated
 * by any implementation of {@link com.algorithms.generation.GenerationStrategy}
 * class
 *
 * @author  Zemlianiy
 * @version 1.0
 * @since   1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Range {
    private int arraySize;
    private int minValue;
    private int maxValue;
}
