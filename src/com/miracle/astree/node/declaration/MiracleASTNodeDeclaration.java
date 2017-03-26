package com.miracle.astree.node.declaration;

import com.miracle.astree.node.MiracleASTreeNode;

public abstract class MiracleASTNodeDeclaration extends MiracleASTreeNode {
    private final String identifier;

    protected MiracleASTNodeDeclaration(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }
}