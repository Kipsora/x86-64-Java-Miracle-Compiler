package com.miracle.astree.visitor;

import com.miracle.astree.node.MiracleASTreeNode;
import com.miracle.astree.node.MiracleASTreeRoot;
import com.miracle.astree.node.statement.MiracleASTreeStatement;
import com.miracle.astree.node.statement.declaration.MiracleASTreeClass;
import com.miracle.astree.node.statement.declaration.MiracleASTreeFunction;
import com.miracle.astree.node.statement.declaration.MiracleASTreeMemberDeclaration;
import com.miracle.astree.node.statement.declaration.MiracleASTreeVariable;

public class MiracleASTreePrinter extends MiracleASTreeBaseVisitor {
    private int level = 0;

    private void smartPrint(String s) {
        for (int i = 1; i <= level; i++) System.out.print("  ");
        System.out.println(s);
    }
    @Override
    public void enter() {
        smartPrint("(");
        level++;
    }

    @Override
    public void exit() {
        level--;
        smartPrint(")");
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
        smartPrint("function " + miracleASTreeFunction.getIdentifier());
        smartPrint("decorator: " + miracleASTreeFunction.getDecorator());
        smartPrint("return type: " + miracleASTreeFunction.getType().toString());
        for (MiracleASTreeStatement entry : miracleASTreeFunction.getBody()) {
            entry.accept(this);
        }
    }

    @Override
    public void visit(MiracleASTreeVariable miracleASTreeVariable) {
        smartPrint("var declaration");
        smartPrint("var id: " + miracleASTreeVariable.getIdentifier());
        smartPrint("var deco: " + miracleASTreeVariable.getDecorator());
        smartPrint("var type: " + miracleASTreeVariable.getType().toString());
        if (miracleASTreeVariable.getValue() == null) {
            smartPrint("var exp: null");
        } else {
            smartPrint("var exp: ");
            miracleASTreeVariable.getValue().accept(this);
        }
    }

    @Override
    public void visit(MiracleASTreeClass miracleASTreeClass) {
        smartPrint("class delcaration");
        smartPrint("class id: " + miracleASTreeClass.getIdentifier());
        smartPrint("class content: ");
        for (MiracleASTreeMemberDeclaration entry : miracleASTreeClass.getChildren()) {
            entry.accept(this);
        }
    }
}
