package com.miracle.astree.node.declaration;

import com.miracle.astree.node.MiracleASTreeNode;

public abstract class MiracleASTreeDeclaration extends MiracleASTreeNode {
    protected final String identifier;

    MiracleASTreeDeclaration(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }
}
