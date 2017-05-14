package com.miracle.astree.statement.expression.constant;

import com.miracle.astree.visitor.MiracleASTreeVisitor;
import com.miracle.cstree.MiracleSourcePosition;

public class MiracleASTreeNullConstant extends MiracleASTreeConstant {
    public MiracleASTreeNullConstant(MiracleSourcePosition startPosition) {
        super(startPosition);
    }

    @Override
    public String getValue() {
        return "null";
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.visit(this);
    }
}
