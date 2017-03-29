package com.miracle.astree.visitor;

import com.miracle.astree.node.MiracleASTreeNode;
import com.miracle.astree.node.MiracleASTreeRoot;
import com.miracle.astree.node.expression.value.MiracleASTreeConstant;
import com.miracle.astree.node.expression.value.MiracleASTreeVariable;
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
        smartPrint("arguments: ");
        for (MiracleASTreeVariableDeclaration entry : miracleASTreeFunctionDeclaration.getArguments()) {
            entry.accept(this);
        }
        for (MiracleASTreeStatement entry : miracleASTreeFunctionDeclaration.getBody()) {
            entry.accept(this);
        }
    }

    @Override
    public void visit(MiracleASTreeConstant miracleASTreeConstant) {
        smartPrint("constant: " + miracleASTreeConstant.getValue());
    }

    @Override
    public void visit(MiracleASTreeVariable miracleASTreeVariable) {
        smartPrint("variable: " + miracleASTreeVariable.getIdentifier());
    }

    @Override
    public void visit(MiracleASTreeVariableDeclaration miracleASTreeVariableDeclaration) {
        smartPrint("var declaration");
        smartPrint("var id: " + miracleASTreeVariableDeclaration.getIdentifier());
        smartPrint("var deco: " + miracleASTreeVariableDeclaration.getDecorator());
        smartPrint("var type: " + miracleASTreeVariableDeclaration.getType().toString());
        if (miracleASTreeVariableDeclaration.getInitExpression() == null) {
            smartPrint("var exp: null");
        } else {
            smartPrint("var exp: ");
            miracleASTreeVariableDeclaration.getInitExpression().accept(this);
        }
    }

    @Override
    public void visit(MiracleASTreeClassDeclaration miracleASTreeClassDeclaration) {
        smartPrint("class delcaration");
        smartPrint("class id: " + miracleASTreeClassDeclaration.getIdentifier());
        smartPrint("class extend from: " + miracleASTreeClassDeclaration.getExtend());
        smartPrint("class content: ");
        for (MiracleASTreeMemberDeclaration entry : miracleASTreeClassDeclaration.getChildren()) {
            entry.accept(this);
        }
    }
}
