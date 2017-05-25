package com.miracle.intermediate.number;

import com.miracle.MiracleOption;

public class StaticString extends DirectRegister {
    public final String value;

    public StaticString(String identifier, String value) {
        super(identifier, MiracleOption.POINTER_SIZE);
        this.value = value;
    }
}
