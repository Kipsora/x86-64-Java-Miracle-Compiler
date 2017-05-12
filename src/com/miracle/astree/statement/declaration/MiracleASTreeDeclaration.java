package com.miracle.astree.statement.declaration;

import com.miracle.astree.statement.MiracleASTreeStatement;
import com.miracle.astree.type.MiracleASTreeType;

public abstract class MiracleASTreeDeclaration extends MiracleASTreeStatement {
    public final String identifier;

    public MiracleASTreeDeclaration(String identifier) {
        this.identifier = identifier;
    }

    public abstract MiracleASTreeType getType();
}
