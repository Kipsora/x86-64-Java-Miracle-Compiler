package com.miracle.astree.type;

import com.miracle.astree.visitor.MiracleASTreeVisitor;

public class MiracleASTreeArrayType extends MiracleASTreeVariableType {
    public final MiracleASTreeBaseType baseType;
    public final int dimension;

    public MiracleASTreeArrayType(MiracleASTreeBaseType baseType, int dimension) {
        this.baseType = baseType;
        this.dimension = dimension;
    }

    public boolean isSameType(MiracleASTreeArrayType type) {
        return type.baseType.isSameType(this.baseType) && type.dimension == this.dimension;
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.visit(this);
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
}
