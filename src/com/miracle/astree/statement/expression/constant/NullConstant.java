package com.miracle.astree.statement.expression.constant;

import com.miracle.astree.visitor.Visitor;
import com.miracle.cstree.MiracleSourcePosition;

public class NullConstant extends Constant {
    public NullConstant(MiracleSourcePosition startPosition) {
        super(startPosition);
    }

    @Override
    public String getValue() {
        return "null";
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
