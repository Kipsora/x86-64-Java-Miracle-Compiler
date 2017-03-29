package com.miracle.astree.node.expression.value;

import com.miracle.astree.node.statement.declaration.MiracleASTreeTypename;
import com.miracle.astree.visitor.MiracleASTreeVisitor;

public class MiracleASTreeConstant extends MiracleASTreeValue {
    private final String value;

    public MiracleASTreeConstant(MiracleASTreeTypename type, String value) {
        super(type, false);
        this.value = value;
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.enter();
        visitor.visit(this);
        visitor.exit();
    }

    public String getValue() {
        return value;
    }
}
