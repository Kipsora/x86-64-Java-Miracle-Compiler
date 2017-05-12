package com.miracle.astree.type;

import com.miracle.astree.visitor.MiracleASTreeVisitor;

public class MiracleASTreeBaseType extends MiracleASTreeVariableType {
    public final String identifier;

    public MiracleASTreeBaseType(String identifier) {
        this.identifier = identifier;
    }

    public boolean isSameType(MiracleASTreeBaseType type) {
        return identifier.equals(type.identifier);
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toPrintableString() {
        return identifier;
    }
}
