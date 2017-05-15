package com.miracle.astree.statement.expression;

import com.miracle.astree.visitor.MiracleASTreeVisitor;
import com.miracle.cstree.MiracleSourcePosition;

import java.util.Collections;
import java.util.List;

public class MiracleASTreeCall extends MiracleASTreeExpression {
    public final MiracleASTreeExpression function;
    public final List<MiracleASTreeExpression> parameters;

    public MiracleASTreeCall(MiracleASTreeExpression function,
                             List<MiracleASTreeExpression> parameters,
                             MiracleSourcePosition startPosition) {
        super(startPosition);
        this.function = function;
        this.parameters = Collections.unmodifiableList(parameters);
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toPrintableString() {
        StringBuilder builder = new StringBuilder();
        builder.append(function.toPrintableString()).append("(");
        for (int i = 0; i < parameters.size(); i++) {
            if (i != 0) {
                builder.append(",");
            }
            builder.append("[");
            builder.append(parameters.get(i).toPrintableString());
            builder.append("]");
        }
        builder.append(")");
        return builder.toString();
    }
}