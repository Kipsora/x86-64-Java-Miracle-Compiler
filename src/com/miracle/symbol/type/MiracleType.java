package com.miracle.symbol.type;

import static com.miracle.symbol.MiracleSymbolTable.__builtin_null;
import static com.miracle.symbol.MiracleSymbolTable.isBuiltinType;

public abstract class MiracleType {
    public abstract String toClassTypeString();

    public abstract String toPrintableString();

    public abstract String toPrintableString(String identifier);

    public boolean isSameType(MiracleType type) {
        if (this.toPrintableString().equals("null")) {
            return !isBuiltinType(type);
        }
        if (type.toPrintableString().equals("null")) {
            return !isBuiltinType(this);
        }
        if (this instanceof MiracleBaseType) {
            if (type instanceof MiracleBaseType) {
                return ((MiracleBaseType) this).isSameType((MiracleBaseType) type);
            }
        } else if (this instanceof MiracleArrayType) {
            if (type instanceof MiracleArrayType) {
                return ((MiracleArrayType) this).isSameType((MiracleArrayType) type);
            }
        } else if (this instanceof MiracleFunctionType) {
            if (type instanceof MiracleFunctionType) {
                return ((MiracleFunctionType) this).isSameType((MiracleFunctionType) type);
            }
        }
        return false;
    }
}
