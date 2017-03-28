package com.miracle.astree.node.statement.declaration;

import com.miracle.astree.visitor.MiracleASTreeVisitor;

import java.util.List;

public class MiracleASTreeClassDeclaration extends MiracleASTreeDeclaration {
    private final String extend;
    private final List<MiracleASTreeMemberDeclaration> children;

    public MiracleASTreeClassDeclaration(String identifier, String extend,
                                         List<MiracleASTreeMemberDeclaration> children) {
        super(identifier);
        this.extend = extend;
        this.children = children;
    }

    public String getExtend() {
        return extend;
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.enter();
        visitor.visit(this);
        visitor.exit();
    }

    public List<MiracleASTreeMemberDeclaration> getChildren() {
        return children;
    }
}
