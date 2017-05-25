package com.miracle.intermediate.number;

public class Immediate extends Number {
    public final int value;
    public final int size;

    public Immediate(int value, int size) {
        this.value = value;
        this.size = size;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public int getNumberSize() {
        return size;
    }
}
