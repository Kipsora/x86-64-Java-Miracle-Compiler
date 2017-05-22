package com.miracle.symbol;

public abstract class MiracleSymbolVariableType extends MiracleSymbolType {
    public MiracleSymbolVariableType(Category category, int size) {
        super(category, size);
    }

    public abstract String getName();

    public abstract int getMemorySize();
}
