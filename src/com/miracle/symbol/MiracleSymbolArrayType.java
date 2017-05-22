package com.miracle.symbol;

import com.miracle.MiracleOption;

import static com.miracle.symbol.MiracleSymbolTable.__builtin_int;

public class MiracleSymbolArrayType extends MiracleSymbolVariableType {
    public final MiracleSymbolBaseType baseType;
    public final int dimension;

    public MiracleSymbolArrayType(MiracleSymbolBaseType baseType,
                                  int dimension) {
        super(Category.ARRAY, MiracleOption.POINTER_SIZE);
        this.baseType = baseType;
        this.dimension = dimension;
        addMethod("size", new MiracleSymbolFunctionType(__builtin_int));
    }

    public MiracleSymbolType subscript() {
        if (dimension > 1) {
            return new MiracleSymbolArrayType(baseType, dimension - 1);
        } else {
            return baseType;
        }
    }

    @Override
    public boolean isSameType(MiracleSymbolType type) {
        if (type.category == Category.NULL) return true;
        if (type.category != Category.ARRAY) return false;
        return dimension == ((MiracleSymbolArrayType) type).dimension &&
                baseType.isSameType(((MiracleSymbolArrayType) type).baseType);
    }

    @Override
    public String toPrintableString() {
        StringBuilder builder = new StringBuilder();
        builder.append(baseType.toPrintableString());
        for (int i = 0; i < dimension; i++) {
            builder.append("[]");
        }
        return builder.toString();
    }

    @Override
    public String getName() {
        return "$array";
    }

    @Override
    public int getMemorySize() {
        return 0;
    }
}
