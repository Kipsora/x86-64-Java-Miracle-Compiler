package com.miracle.astree.expression;

public class MiracleASTreeStringConstant extends MiracleASTreeConstant {
    public final String value;

    public MiracleASTreeStringConstant(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
