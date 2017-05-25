package com.miracle.symbol;

public class SymbolNullType extends SymbolType {
    public static final SymbolNullType INSTANCE = new SymbolNullType();

    private SymbolNullType() {
        super(Category.NULL, 0);
    }

    @Override
    public boolean isSameType(SymbolType type) {
        return this == type ||
                (type.category != Category.BUILTIN &&
                        type.category != Category.METHOD);
    }

    @Override
    public String toPrintableString() {
        return "null";
    }
}
