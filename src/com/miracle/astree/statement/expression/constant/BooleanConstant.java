package com.miracle.astree.statement.expression.constant;

import com.miracle.astree.visitor.Visitor;
import com.miracle.cstree.MiracleSourcePosition;

public class BooleanConstant extends Constant {
    public final String value;

    public BooleanConstant(String value,
                           MiracleSourcePosition startPosition) {
        super(startPosition);
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
