package com.miracle.astree.statement.expression.constant;

import com.miracle.astree.statement.expression.Expression;
import com.miracle.cstree.SourcePosition;

public abstract class Constant extends Expression {
    public Constant(SourcePosition startPosition) {
        super(startPosition);
    }

    public abstract String getValue();

    @Override
    public String toPrintableString() {
        return getValue();
    }
}
