package com.miracle.astree.node.expression.value;

import com.miracle.astree.node.statement.declaration.MiracleASTreeTypename;
import com.miracle.astree.visitor.MiracleASTreeVisitor;

public class MiracleASTreeThis extends MiracleASTreeValue {
    public MiracleASTreeThis(MiracleASTreeTypename type, boolean mutable) {
        super(type, mutable);
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.enter();
        visitor.visit(this);
        visitor.exit();
    }
}
