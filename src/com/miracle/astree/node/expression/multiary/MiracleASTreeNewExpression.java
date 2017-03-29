package com.miracle.astree.node.expression.multiary;

import com.miracle.astree.node.expression.MiracleASTreeExpression;
import com.miracle.astree.node.statement.declaration.MiracleASTreeTypename;
import com.miracle.astree.visitor.MiracleASTreeVisitor;

public class MiracleASTreeNewExpression extends MiracleASTreeExpression {
    private final MiracleASTreeTypename type;

    public MiracleASTreeNewExpression(MiracleASTreeTypename type) {
        super(type, "new", true);
        this.type = type;
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.enter();
        visitor.visit(this);
        visitor.exit();
    }

    @Override
    public MiracleASTreeTypename getType() {
        return type;
    }
}
