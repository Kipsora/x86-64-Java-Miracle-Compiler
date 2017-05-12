package com.miracle.astree.type;

import com.miracle.Miracle;
import com.miracle.astree.expression.MiracleASTreeVariable;

public abstract class MiracleASTreeVariableType extends MiracleASTreeType {

    public boolean isSameType(MiracleASTreeVariableType type) {
        if (this instanceof MiracleASTreeBaseType) {
            if (type instanceof MiracleASTreeBaseType) {
                return ((MiracleASTreeBaseType) this).isSameType((MiracleASTreeBaseType) type);
            }
        } else {
            if (type instanceof MiracleASTreeArrayType) {
                return ((MiracleASTreeArrayType) this).isSameType((MiracleASTreeArrayType) type);
            }
        }
        return false;
    }
}
