package com.miracle.astree.node.statement.declaration;

import com.miracle.astree.visitor.MiracleASTreeVisitor;

import java.util.HashMap;
import java.util.List;

public class MiracleASTreeClassDeclaration extends MiracleASTreeDeclaration {
    private final String extend;
    private final HashMap<String, MiracleASTreeMemberDeclaration> mapping;
    private List<MiracleASTreeMemberDeclaration> children;

    public MiracleASTreeClassDeclaration(String identifier, String extend) {
        super(identifier);
        this.extend = extend;
        this.mapping = new HashMap<>();
        this.children = children;
        for (MiracleASTreeMemberDeclaration entry : children) {
            this.mapping.put(entry.getIdentifier(), entry);
        }
    }

    public void setChildren(List<MiracleASTreeMemberDeclaration> children) {
        this.children = children;
    }

    public String getExtend() {
        return extend;
    }

    public boolean contain(String identifier) {
        return mapping.containsKey(identifier);
    }

    public MiracleASTreeMemberDeclaration getMember(String identifier) {
        return mapping.get(identifier);
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

    @Override
    public DECTYPE getDeclarationType() {
        return DECTYPE.DEC_CLASS;
    }
}
