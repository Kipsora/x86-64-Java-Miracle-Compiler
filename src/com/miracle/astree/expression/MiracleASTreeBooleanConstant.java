package com.miracle.astree.expression;

public class MiracleASTreeBooleanConstant extends MiracleASTreeConstant {
    public final boolean value;

    public MiracleASTreeBooleanConstant(String value) {
        this.value = Boolean.valueOf(value);
    }

    @Override
    public String getValue() {
        return String.valueOf(value);
    }
}
