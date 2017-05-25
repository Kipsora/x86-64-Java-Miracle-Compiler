package com.miracle.astree.statement.expression;

import com.miracle.astree.visitor.Visitor;
import com.miracle.cstree.SourcePosition;

import java.util.Collections;
import java.util.List;

public class Call extends Expression {
    public final Expression function;
    public final List<Expression> parameters;

    public Call(Expression function,
                List<Expression> parameters,
                SourcePosition startPosition) {
        super(startPosition);
        this.function = function;
        this.parameters = Collections.unmodifiableList(parameters);
    }

    @Override
    public void accept(Visitor visitor) {
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