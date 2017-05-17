package com.miracle.symbol;

public class MiracleSymbolNullType extends MiracleSymbolType {
    public static final MiracleSymbolNullType INSTANCE = new MiracleSymbolNullType();

    private MiracleSymbolNullType() {
        super(Category.NULL, 0);
    }

    @Override
    public boolean isSameType(MiracleSymbolType type) {
        return this == type ||
                (type.category != Category.BUILTIN &&
                        type.category != Category.METHOD);
    }

    @Override
    public String toPrintableString() {
        return "null";
    }
}
