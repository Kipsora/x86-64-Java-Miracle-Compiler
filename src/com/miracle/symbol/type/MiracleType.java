package com.miracle.symbol.type;

import static com.miracle.symbol.type.MiracleBaseType.*;

public abstract class MiracleType {

    public abstract String toClassTypeString();

    public abstract String toPrintableString();

    public abstract String toPrintableString(String identifier);

    public boolean isSameType(MiracleType type) {
        if (this instanceof MiracleBaseType) {
            if (type instanceof MiracleBaseType) {
                return ((MiracleBaseType) this).isSameType((MiracleBaseType) type);
            } if (type instanceof MiracleArrayType) {
                return ((MiracleBaseType) this).identifier.equals("null");
            }
        } else if (this instanceof MiracleArrayType) {
            if (type instanceof MiracleArrayType) {
                return ((MiracleArrayType) this).isSameType((MiracleArrayType) type);
            } else if (type instanceof MiracleBaseType) {
                return ((MiracleBaseType) type).identifier.equals("null");
            }
        } else if (this instanceof MiracleFunctionType) {
            if (type instanceof MiracleFunctionType) {
                return ((MiracleFunctionType) this).isSameType((MiracleFunctionType) type);
            }
        }
        return false;
    }
}
