package com.miracle.astree.node.expression.multiary;

import com.miracle.astree.node.MiracleASTreeTypename;
import com.miracle.astree.node.expression.MiracleASTreeExpression;
import com.miracle.astree.visitor.MiracleASTreeVisitor;

public class MiracleASTreeNewExpression extends MiracleASTreeExpression {
    private final MiracleASTreeTypename type;

    protected MiracleASTreeNewExpression(MiracleASTreeTypename type) {
        super(type, "new", true);
        this.type = type;
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public MiracleASTreeTypename getType() {
        return type;
    }
}
