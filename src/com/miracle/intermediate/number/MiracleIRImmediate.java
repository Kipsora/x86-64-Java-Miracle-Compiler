package com.miracle.intermediate.number;

public class MiracleIRImmediate extends MiracleIRNumber {
    public final int value;
    public final int size;

    public MiracleIRImmediate(int value, int size) {
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
