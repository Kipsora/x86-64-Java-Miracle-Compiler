package com.miracle.intermediate.number;

import com.miracle.MiracleOption;

public class StaticString extends IndirectRegister {
    public final String value;
    public final String name;

    public StaticString(String name, String value) {
        super(MiracleOption.POINTER_SIZE);
        this.value = value;
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
