package com.algorithms.entity;

public class Entry {

    private String name;
    private Long value;

    public Entry(String name, Long value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Long getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
