package com.miracle.astree.visitor;

import com.miracle.astree.node.MiracleASTreeNode;
import com.miracle.astree.node.MiracleASTreeRoot;
import com.miracle.astree.node.statement.MiracleASTreeStatement;
import com.miracle.astree.node.statement.declaration.MiracleASTreeClassDeclaration;
import com.miracle.astree.node.statement.declaration.MiracleASTreeFunctionDeclaration;
import com.miracle.astree.node.statement.declaration.MiracleASTreeMemberDeclaration;
import com.miracle.astree.node.statement.declaration.MiracleASTreeVariableDeclaration;

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
    public void visit(MiracleASTreeFunctionDeclaration miracleASTreeFunctionDeclaration) {
        smartPrint("function " + miracleASTreeFunctionDeclaration.getIdentifier());
        smartPrint("decorator: " + miracleASTreeFunctionDeclaration.getDecorator());
        smartPrint("return type: " + miracleASTreeFunctionDeclaration.getType().toString());
        for (MiracleASTreeStatement entry : miracleASTreeFunctionDeclaration.getBody()) {
            entry.accept(this);
        }
    }

    @Override
    public void visit(MiracleASTreeVariableDeclaration miracleASTreeVariableDeclaration) {
        smartPrint("var declaration");
        smartPrint("var id: " + miracleASTreeVariableDeclaration.getIdentifier());
        smartPrint("var deco: " + miracleASTreeVariableDeclaration.getDecorator());
        smartPrint("var type: " + miracleASTreeVariableDeclaration.getType().toString());
        if (miracleASTreeVariableDeclaration.getValue() == null) {
            smartPrint("var exp: null");
        } else {
            smartPrint("var exp: ");
            miracleASTreeVariableDeclaration.getValue().accept(this);
        }
    }

    @Override
    public void visit(MiracleASTreeClassDeclaration miracleASTreeClassDeclaration) {
        smartPrint("class delcaration");
        smartPrint("class id: " + miracleASTreeClassDeclaration.getIdentifier());
        smartPrint("class content: ");
        for (MiracleASTreeMemberDeclaration entry : miracleASTreeClassDeclaration.getChildren()) {
            entry.accept(this);
        }
    }
}
