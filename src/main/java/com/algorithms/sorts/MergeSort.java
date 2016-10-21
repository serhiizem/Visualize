package com.algorithms.sorts;

public class MergeSort extends Sortable implements Sorting {

    public MergeSort(Integer[] array) {
        super(array);
    }

    @Override
    public void sort() {
        return;
    }

    public void sort(Integer[] arrayToSort) {

        Integer[] first = new Integer[arrayToSort.length / 2];
        Integer[] second = new Integer[arrayToSort.length - first.length];

        System.arraycopy(arrayToSort, 0, first, 0, first.length);
        System.arraycopy(arrayToSort, first.length, second, 0, second.length);

        sort(first);
        sort(second);
        mergeSort(first, second, arrayToSort);
    }

    private void mergeSort(Integer[] first, Integer[] second, Integer[] arrayToSort) {

        int iFirst = 0;
        int iSecond = 0;
        int j = 0;

        while (iFirst < first.length &&
                iSecond < second.length) {

            if(first[iFirst] < second[iSecond]) {
                arrayToSort[j] = first[iFirst];
                iFirst++;
            } else {
                arrayToSort[j] = second[iSecond];
                iSecond++;
            }
            j++;
        }
        System.arraycopy(first, iFirst, arrayToSort, j, first.length - iFirst);
        System.arraycopy(second, iSecond, arrayToSort, j, second.length - iSecond);
    }
}
