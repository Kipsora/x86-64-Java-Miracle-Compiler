package com.miracle.symbol;

public abstract class SymbolBaseType extends SymbolVariableType {
    public SymbolBaseType(Category category, int size) {
        super(category, size);
    }

    public boolean isSameType(SymbolBaseType type) {
        if (this.category.equals(Category.NULL)) {
            return !type.category.equals(Category.BUILTIN);
        }
        if (type.category.equals(Category.NULL)) {
            return !this.category.equals(Category.BUILTIN);
        }
        return this == type;
    }
}
