package com.miracle.symbol.type;

public class MiracleArrayType extends MiracleVariableType {
    public final MiracleBaseType baseType;
    public final int dimension;

    public MiracleArrayType(MiracleBaseType baseType,
                            int dimension) {
        this.baseType = baseType;
        this.dimension = dimension;
    }

    boolean isSameType(MiracleArrayType type) {
        return baseType.identifier.equals("null") ||
                type.baseType.identifier.equals("null") ||
                type.baseType.isSameType(this.baseType) &&
                        type.dimension == this.dimension;
    }

    @Override
    public String toClassTypeString() {
        return "$array";
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
    public String toPrintableString(String identifier) {
        StringBuilder builder = new StringBuilder();
        builder.append(baseType.toPrintableString()).append(' ');
        builder.append(identifier);
        for (int i = 0; i < dimension; i++) {
            builder.append("[]");
        }
        return builder.toString();
    }

    @Override
    public MiracleBaseType getBaseType() {
        return baseType;
    }

    public MiracleVariableType subscript() {
        if (dimension == 1) {
            return baseType;
        } else {
            return new MiracleArrayType(baseType, dimension - 1);
        }
    }
}
