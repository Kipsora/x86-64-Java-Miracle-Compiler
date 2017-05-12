package com.miracle.astree.type;

import com.miracle.astree.visitor.MiracleASTreeVisitor;

public class MiracleASTreeBaseType extends MiracleASTreeVariableType {
    public final String identifier;

    public MiracleASTreeBaseType(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public int getDimension() {
        return 0;
    }

    @Override
    public MiracleASTreeBaseType getBaseType() {
        return this;
    }

    public boolean equals(MiracleASTreeBaseType o) {
        return identifier.equals(o.identifier);
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.visit(this);
    }
}
