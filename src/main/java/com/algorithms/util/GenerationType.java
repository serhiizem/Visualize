package com.algorithms.util;

public enum GenerationType {

    ASC_ORDER("Array sorted in ascending order"),
    ASC_ORDER_PLUS_RAND("Array sorted in ascending order and a random element at the end"),
    DESC_ORDER("Array sorted in descending order"),
    RANDOM_ORDER("Array filled with randomly distributed values");

    private String orderType;

    GenerationType(final String orderType) {
        this.orderType = orderType;
    }

    public String getOrderType() {
        return this.orderType;
    }
}
