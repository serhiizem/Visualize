package com.algorithms.sorts;

/**
 * Defines a behaviour for all the concrete sorting
 * algorithm implementations
 *
 * @see BubbleSortReverse
 * @see BubbleSort
 * @see SelectionSort
 * @see InsertionSort
 * @see MergeSort
 * @see QuickSort
 * @see JavaSort
 *
 * @author  Zemlianiy
 * @version 1.0
 * @since
 */
public interface Sorting {

    void sort(Comparable[] array);

    static void swap(Comparable[] array, int i, int j) {
        Comparable temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    static boolean isLess(Comparable i, Comparable j) {
        return i.compareTo(j) < 0;
    }
}