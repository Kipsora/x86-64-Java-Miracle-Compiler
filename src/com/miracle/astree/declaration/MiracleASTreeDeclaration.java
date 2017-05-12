package com.miracle.astree.declaration;

import com.miracle.astree.MiracleASTreeNode;
import com.miracle.astree.type.MiracleASTreeType;

public abstract class MiracleASTreeDeclaration extends MiracleASTreeNode {
    public final String identifier;

    public MiracleASTreeDeclaration(String identifier) {
        this.identifier = identifier;
    }

    public abstract MiracleASTreeType getType();
}
