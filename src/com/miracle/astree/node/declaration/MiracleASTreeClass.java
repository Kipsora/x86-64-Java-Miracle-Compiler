package com.miracle.astree.node.declaration;

import com.miracle.astree.visitor.MiracleASTreeVisitor;

import java.util.List;

public class MiracleASTreeClass extends MiracleASTreeDeclaration {
    private final String extend;
    private final List<MiracleASTreeDeclaration> children;

    public MiracleASTreeClass(String identifier, List<MiracleASTreeDeclaration> children) {
        super(identifier);
        this.extend = null;
        this.children = children;
    }

    public MiracleASTreeClass(String identifier, String extend, List<MiracleASTreeDeclaration> children) {
        super(identifier);
        this.extend = extend;
        this.children = children;
    }

    public String getExtend() {
        return extend;
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.visit(this);
    }

    public List<MiracleASTreeDeclaration> getChildren() {
        return children;
    }
}
