package com.miracle.astree.statement.expression.constant;

import com.miracle.astree.visitor.Visitor;
import com.miracle.cstree.SourcePosition;

public class StringConstant extends Constant {
    public final String value;

    public StringConstant(String value, SourcePosition startPosition) {
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
