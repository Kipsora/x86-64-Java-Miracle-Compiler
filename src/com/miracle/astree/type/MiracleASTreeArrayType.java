package com.miracle.astree.type;

import com.miracle.astree.visitor.MiracleASTreeVisitor;

public class MiracleASTreeArrayType extends MiracleASTreeVariableType {
    public final MiracleASTreeBaseType baseType;
    public final int dimension;

    public MiracleASTreeArrayType(MiracleASTreeBaseType baseType, int dimension) {
        this.baseType = baseType;
        this.dimension = dimension;
    }

    @Override
    public int getDimension() {
        return dimension;
    }

    @Override
    public MiracleASTreeBaseType getBaseType() {
        return baseType;
    }

    public boolean equals(MiracleASTreeArrayType o) {
        return o.baseType.equals(this.baseType) && o.dimension == this.dimension;
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.visit(this);
    }
}
