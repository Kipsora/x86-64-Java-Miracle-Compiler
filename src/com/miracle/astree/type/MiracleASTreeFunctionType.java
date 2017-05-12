package com.miracle.astree.type;

import com.miracle.astree.expression.MiracleASTreeExpression;
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

    public boolean match(List<MiracleASTreeExpression> parameters) {
        if (parameters.size() != this.parameters.size()) {
            return false;
        }
        for (int i = 0; i < this.parameters.size(); i++) {
            if (parameters.get(i).type == null || !(parameters.get(i).type instanceof MiracleASTreeVariableType)) {
                return false;
            }
            if (!this.parameters.get(i).isSameType((MiracleASTreeVariableType) parameters.get(i).type)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toPrintableString() {
        StringBuilder builder = new StringBuilder();
        builder.append(returnType.toPrintableString());
        builder.append("(");
        for (int i = 0; i < parameters.size(); i++) {
            if (i != 0) {
                builder.append(",");
            }
            builder.append(parameters.get(i).toPrintableString());
        }
        builder.append(")");
        return builder.toString();
    }
}
