package com.miracle.symbol.type;

import com.miracle.symbol.MiracleSymbolTable;

public class MiracleBaseType extends MiracleVariableType {
    public final String identifier;

    public MiracleBaseType(String identifier) {
        this.identifier = identifier;
    }

    public boolean isSameType(MiracleBaseType type) {
        return identifier.equals(type.identifier);
    }

    @Override
    public String toClassTypeString() {
        return identifier;
    }

    @Override
    public String toPrintableString() {
        return identifier;
    }

    @Override
    public String toPrintableString(String identifier) {
        return this.identifier + " " + identifier;
    }

    @Override
    public MiracleBaseType getBaseType() {
        return this;
    }
}
