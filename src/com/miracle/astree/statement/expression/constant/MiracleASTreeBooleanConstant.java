package com.miracle.astree.statement.expression.constant;

import com.miracle.astree.visitor.MiracleASTreeVisitor;
import com.miracle.cstree.MiracleSourcePosition;

public class MiracleASTreeBooleanConstant extends MiracleASTreeConstant {
    public final boolean value;

    public MiracleASTreeBooleanConstant(String value,
                                        MiracleSourcePosition startPosition) {
        super(startPosition);
        this.value = Boolean.valueOf(value);
    }

    @Override
    public String getValue() {
        return String.valueOf(value);
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.visit(this);
    }
}
