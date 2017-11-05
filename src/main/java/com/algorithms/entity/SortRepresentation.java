package com.algorithms.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Class responsible for the representation of intermediate results
 * of the sorting process
 *
 * @author Zemlianiy
 * @version 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class SortRepresentation {
    private Comparable[] intermediateResult;
}
