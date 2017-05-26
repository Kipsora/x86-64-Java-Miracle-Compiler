package com.miracle.astree.statement.expression.constant;

import com.miracle.astree.visitor.ASTreeVisitor;
import com.miracle.cstree.SourcePosition;

public class IntegerConstant extends Constant {
    public final String value;

    public IntegerConstant(String value,
                           SourcePosition startPosition) {
        super(startPosition);
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void accept(ASTreeVisitor ASTreeVisitor) {
        ASTreeVisitor.visit(this);
    }
}
