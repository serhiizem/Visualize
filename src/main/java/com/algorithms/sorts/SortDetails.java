package com.algorithms.sorts;

public class SortDetails {

    private Integer[] array;
    private String sortType;

    public SortDetails() {
    }

    public SortDetails(Integer[] array, String sortType) {
        this.array = array;
        this.sortType = sortType;
    }

    public Integer[] getArray() {
        return array;
    }

    public void setArray(Integer[] array) {
        this.array = array;
    }

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }
}
