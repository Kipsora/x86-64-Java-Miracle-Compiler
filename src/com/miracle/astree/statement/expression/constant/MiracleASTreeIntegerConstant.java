package com.miracle.astree.statement.expression.constant;

import com.miracle.astree.visitor.MiracleASTreeVisitor;
import com.miracle.cstree.MiracleSourcePosition;

public class MiracleASTreeIntegerConstant extends MiracleASTreeConstant {
    public final int value;

    public MiracleASTreeIntegerConstant(String value,
                                        MiracleSourcePosition startPosition) {
        super(startPosition);
        this.value = Integer.valueOf(value);
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
