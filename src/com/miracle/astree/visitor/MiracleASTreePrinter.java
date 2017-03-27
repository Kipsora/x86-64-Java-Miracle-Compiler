package com.miracle.astree.visitor;

import com.miracle.astree.node.MiracleASTreeNode;
import com.miracle.astree.node.MiracleASTreeRoot;
import com.miracle.astree.node.statement.declaration.MiracleASTreeFunction;

public class MiracleASTreePrinter extends MiracleASTreeBaseVisitor {
    int level = 0;

    @Override
    public void enter() {
        level++;
        for (int i = 1; i < level; i++) System.out.print("  ");
        System.out.println('(');
    }

    @Override
    public void exit() {
        for (int i = 1; i < level; i++) System.out.print("  ");
        System.out.println(')');
        level--;
    }

    @Override
    public void visit(MiracleASTreeRoot miracleASTreeRoot) {
        for (int i = 1; i <= level; i++) System.out.print("  ");
        System.out.println("ROOT");
        for (MiracleASTreeNode entry : miracleASTreeRoot.getChildren()) {
            entry.accept(this);
        }
    }

    @Override
    public void visit(MiracleASTreeFunction miracleASTreeFunction) {
        for (int i = 1; i <= level; i++) System.out.print("  ");
        System.out.println("function " + miracleASTreeFunction.getIdentifier());
        for (int i = 1; i <= level; i++) System.out.print("  ");
        System.out.println("decorator: " + miracleASTreeFunction.getDecorator());
        for (int i = 1; i <= level; i++) System.out.print("  ");
        System.out.println("return type: " + miracleASTreeFunction.getType().toString());
    }
}
