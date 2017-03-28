package com.miracle.astree.node.expression.value;

import com.miracle.astree.node.MiracleASTreeTypename;
import com.miracle.astree.visitor.MiracleASTreeVisitor;

public class MiracleASTreeConstant extends MiracleASTreeValue {
    private final String value;

    public MiracleASTreeConstant(MiracleASTreeTypename type, String value) {
        super(type, false);
        this.value = value;
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.visit(this);
    }

    public String getValue() {
        return value;
    }
}
