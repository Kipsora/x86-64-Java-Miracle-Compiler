package com.miracle.symbol;

public abstract class SymbolVariableType extends SymbolType {
    public SymbolVariableType(Category category, int size) {
        super(category, size);
    }

    public abstract String getName();

    public abstract int getMemorySize();
}
