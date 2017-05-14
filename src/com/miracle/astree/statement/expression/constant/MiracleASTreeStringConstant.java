package com.miracle.astree.statement.expression.constant;

import com.miracle.astree.visitor.MiracleASTreeVisitor;
import com.miracle.cstree.MiracleSourcePosition;

public class MiracleASTreeStringConstant extends MiracleASTreeConstant {
    public final String value;

    public MiracleASTreeStringConstant(String value, MiracleSourcePosition startPosition) {
        super(startPosition);
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.visit(this);
    }
}
