package com.miracle.intermediate.number;

import com.miracle.intermediate.base.MiracleIRBase;

public abstract class MiracleIRNumber extends MiracleIRBase {
    final int value;

    MiracleIRNumber(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
