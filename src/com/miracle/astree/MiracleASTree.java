package com.miracle.astree;

import com.miracle.astree.node.MiracleASTreeRoot;
import com.miracle.astree.visitor.MiracleASTreeVisitor;

public class MiracleASTree {
    private MiracleASTreeRoot root;

    public void visit(MiracleASTreeVisitor visitor) {
        root.accept(visitor);
    }

    public MiracleASTree(MiracleASTreeRoot root) {
        this.root = root;
    }
}
