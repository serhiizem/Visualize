package com.algorithms.sorts;

public class InsertionSort extends Sortable implements Sorting {

    public InsertionSort(Integer[] array) {
        super(array);
    }

    @Override
    public void sort() {

        for(int x = 1; x < array.length; x++) {

//            5, 10, 4, 8, 3
            Integer temp = array[x];
            int j = x;
            while ((j > 0) && array[j - 1] > temp) {
                array[j] = array[j - 1];
                j--;
            }
            array[j] = temp;
        }
    }

    public Integer[] getResult() {
        return array;
    }
}
