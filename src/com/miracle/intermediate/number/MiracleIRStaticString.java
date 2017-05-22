package com.miracle.intermediate.number;

public class MiracleIRStaticString extends MiracleIRVirtualRegister {
    public final String value;

    public MiracleIRStaticString(String identifier, String value) {
        super(identifier);
        this.value = value;
    }
}
