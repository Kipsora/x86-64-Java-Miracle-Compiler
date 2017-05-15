package com.miracle.astree.statement.expression.constant;

import com.miracle.astree.visitor.MiracleASTreeVisitor;
import com.miracle.cstree.MiracleSourcePosition;

public class MiracleASTreeBooleanConstant extends MiracleASTreeConstant {
    public final String value;

    public MiracleASTreeBooleanConstant(String value,
                                        MiracleSourcePosition startPosition) {
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
