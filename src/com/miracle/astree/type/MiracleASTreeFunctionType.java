package com.miracle.astree.type;

import com.miracle.astree.visitor.MiracleASTreeVisitor;

import java.util.Collections;
import java.util.List;

public class MiracleASTreeFunctionType extends MiracleASTreeType {
    public final MiracleASTreeVariableType returnType;
    public final List<MiracleASTreeVariableType> parameters;

    public MiracleASTreeFunctionType(MiracleASTreeVariableType returnType,
                                     List<MiracleASTreeVariableType> parameters) {
        this.returnType = returnType;
        this.parameters = Collections.unmodifiableList(parameters);
    }

    public boolean equals(MiracleASTreeFunctionType o) {
        return returnType.equals(o.returnType) &&
                parameters.equals(o.parameters);
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.visit(this);
    }
}
