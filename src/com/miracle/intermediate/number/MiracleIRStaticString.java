package com.miracle.intermediate.number;

import com.miracle.MiracleOption;

public class MiracleIRStaticString extends MiracleIRDirectRegister {
    public final String value;

    public MiracleIRStaticString(String identifier, String value) {
        super(identifier, MiracleOption.POINTER_SIZE);
        this.value = value;
    }
}
