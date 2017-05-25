package com.miracle.symbol;

import com.miracle.MiracleOption;

import static com.miracle.symbol.SymbolTable.__builtin_int;

public class SymbolArrayType extends SymbolVariableType {
    public final SymbolBaseType baseType;
    public final int dimension;

    public SymbolArrayType(SymbolBaseType baseType,
                           int dimension) {
        super(Category.ARRAY, MiracleOption.POINTER_SIZE);
        this.baseType = baseType;
        this.dimension = dimension;
        addMethod("size", new SymbolFunctionType(__builtin_int, this) {{
            setAddress("@array.size");
        }});
    }

    public SymbolType subscript() {
        if (dimension > 1) {
            return new SymbolArrayType(baseType, dimension - 1);
        } else {
            return baseType;
        }
    }

    @Override
    public boolean isSameType(SymbolType type) {
        if (type.category == Category.NULL) return true;
        if (type.category != Category.ARRAY) return false;
        return dimension == ((SymbolArrayType) type).dimension &&
                baseType.isSameType(((SymbolArrayType) type).baseType);
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
