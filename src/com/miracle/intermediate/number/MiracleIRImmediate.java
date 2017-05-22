package com.miracle.intermediate.number;

public class MiracleIRImmediate extends MiracleIRNumber {
    public final int value;

    public MiracleIRImmediate(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
