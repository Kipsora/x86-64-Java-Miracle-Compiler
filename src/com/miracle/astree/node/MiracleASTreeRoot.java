package com.miracle.astree.node;

import com.miracle.astree.visitor.MiracleASTreeVisitor;

import java.util.List;

public class MiracleASTreeRoot extends MiracleASTreeNode {
    private final List<MiracleASTreeNode> children;

    public MiracleASTreeRoot(List<MiracleASTreeNode> children) {
        this.children = children;
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.enter();
        visitor.visit(this);
        visitor.exit();
    }

    public List<MiracleASTreeNode> getChildren() {
        return children;
    }
}
