package com.miracle.astree.node.expression.multiary;

import com.miracle.astree.node.expression.MiracleASTreeExpression;
import com.miracle.astree.node.statement.declaration.MiracleASTreeTypename;
import com.miracle.astree.visitor.MiracleASTreeVisitor;

import java.util.List;

public class MiracleASTreeNewExpression extends MiracleASTreeExpression {
    private final List<MiracleASTreeExpression> size;

    public MiracleASTreeNewExpression(MiracleASTreeTypename type, List<MiracleASTreeExpression> size) {
        super(type, "new", true);
        this.size = size;
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.visit(this);
        ;
    }

    public List<MiracleASTreeExpression> getSize() {
        return size;
    }
}
