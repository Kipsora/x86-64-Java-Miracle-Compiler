package com.miracle.astree.statement.expression.constant;

import com.miracle.astree.statement.expression.MiracleASTreeExpression;
import com.miracle.cstree.MiracleSourcePosition;

public abstract class MiracleASTreeConstant extends MiracleASTreeExpression {
    public MiracleASTreeConstant(MiracleSourcePosition startPosition) {
        super(startPosition);
    }

    public abstract String getValue();

    @Override
    public String toPrintableString() {
        return getValue();
    }
}
