package com.miracle.astree.node.expression.value;

import com.miracle.astree.node.MiracleASTreeTypename;
import com.miracle.astree.visitor.MiracleASTreeVisitor;

public class MiracleASTreeVariable extends MiracleASTreeValue {
    private final String identifier;

    public MiracleASTreeVariable(MiracleASTreeTypename type, String identifier, boolean mutable) {
        super(type, true);
        this.identifier = identifier;
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.visit(this);
    }

    public String getIdentifier() {
        return identifier;
    }
}
