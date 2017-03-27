package com.miracle.astree.node.statement.declaration;

public abstract class MiracleASTreeMemberDeclaration extends MiracleASTreeDeclaration {
    private final String decorator;

    MiracleASTreeMemberDeclaration(String identifier) {
        super(identifier);
        this.decorator = null;
    }

    MiracleASTreeMemberDeclaration(String decorator, String identifier) {
        super(identifier);
        this.decorator = decorator;
    }

    public String getDecorator() {
        return decorator;
    }
}
