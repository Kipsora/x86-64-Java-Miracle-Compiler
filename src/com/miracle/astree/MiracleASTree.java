package com.miracle.astree;

import com.miracle.astree.node.MiracleASTreeNode;
import com.miracle.astree.visitor.MiracleASTreeVisitor;

public class MiracleASTree {
    private MiracleASTreeNode root;

    public void visit(MiracleASTreeVisitor visitor) {
        root.accept(visitor);
    }
}
