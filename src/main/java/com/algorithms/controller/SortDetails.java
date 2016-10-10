package com.algorithms.controller;

public class SortDetails {

    private String sort;

    public SortDetails() {
    }

    public SortDetails(String sort) {
        this.sort = sort;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "SortDetails{" +
                "sort='" + sort + '\'' +
                '}';
    }
}
