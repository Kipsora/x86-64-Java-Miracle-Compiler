package com.miracle.astree.statement.expression.constant;

import com.miracle.astree.visitor.Visitor;
import com.miracle.cstree.SourcePosition;

public class NullConstant extends Constant {
    public NullConstant(SourcePosition startPosition) {
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
