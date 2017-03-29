package com.miracle.astree.node.statement.declaration;

import com.miracle.astree.node.statement.MiracleASTreeStatement;

public abstract class MiracleASTreeDeclaration extends MiracleASTreeStatement {
    protected final String identifier;

    MiracleASTreeDeclaration(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    public abstract DECTYPE getDeclarationType();

    public enum DECTYPE {
        DEC_FUNC, DEC_VAR, DEC_CLASS
    }
}
