package com.miracle.astree.node.expression.multiary;

import com.miracle.astree.node.expression.MiracleASTreeExpression;
import com.miracle.astree.visitor.MiracleASTreeVisitor;

public class MiracleASTreeMemberExpression extends MiracleASTreeExpression {
    protected MiracleASTreeMemberExpression(String operator, boolean mutable) {
        super(operator, mutable);
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {

    }

    @Override
    public String getType() {
        return null;
    }
}
