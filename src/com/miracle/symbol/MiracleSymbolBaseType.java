package com.miracle.symbol;

public abstract class MiracleSymbolBaseType extends MiracleSymbolVariableType {
    public MiracleSymbolBaseType(Category category, int size) {
        super(category, size);
    }

    public boolean isSameType(MiracleSymbolBaseType type) {
        if (this.category.equals(Category.NULL)) {
            return !type.category.equals(Category.BUILTIN);
        }
        if (type.category.equals(Category.NULL)) {
            return !this.category.equals(Category.BUILTIN);
        }
        return this == type;
    }
}
