package com.miracle.astree.base;

import com.miracle.astree.visitor.MiracleASTreeVisitor;

public abstract class MiracleASTreeNode {
    public abstract void accept(MiracleASTreeVisitor visitor);
}