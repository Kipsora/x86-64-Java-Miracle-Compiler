package com.miracle.astree.node.statement.declaration;

import com.miracle.astree.node.expression.value.MiracleASTreeValue;

public abstract class MiracleASTreeMemberDeclaration extends MiracleASTreeDeclaration {
    MiracleASTreeMemberDeclaration(String identifier) {
        super(identifier);
    }

    public abstract MiracleASTreeTypename getType();

    public abstract MiracleASTreeValue toValue();
}
