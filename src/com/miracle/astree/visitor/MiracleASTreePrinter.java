package com.miracle.astree.visitor;

import com.miracle.astree.node.MiracleASTreeNode;
import com.miracle.astree.node.MiracleASTreeRoot;
import com.miracle.astree.node.expression.MiracleASTreeExpression;
import com.miracle.astree.node.expression.multiary.MiracleASTreeNewExpression;
import com.miracle.astree.node.expression.value.MiracleASTreeConstant;
import com.miracle.astree.node.expression.value.MiracleASTreeVariable;
import com.miracle.astree.node.statement.MiracleASTreeStatement;
import com.miracle.astree.node.statement.declaration.MiracleASTreeClassDeclaration;
import com.miracle.astree.node.statement.declaration.MiracleASTreeFunctionDeclaration;
import com.miracle.astree.node.statement.declaration.MiracleASTreeMemberDeclaration;
import com.miracle.astree.node.statement.declaration.MiracleASTreeVariableDeclaration;

public class MiracleASTreePrinter extends MiracleASTreeBaseVisitor {
    private int level = 0;

    private void smartPrintln(String s) {
        for (int i = 1; i <= level; i++) System.out.print("  ");
        System.out.println(s);
    }

    private void smartPrint(String s) {
        for (int i = 1; i <= level; i++) System.out.print("  ");
        System.out.print(s);
    }

    @Override
    public void enter() {
        smartPrintln("(");
        level++;
    }

    @Override
    public void exit() {
        level--;
        smartPrintln(")");
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
        if (miracleASTreeFunctionDeclaration.getDecorator() == null) {
            smartPrintln("function " + miracleASTreeFunctionDeclaration.getIdentifier());
        } else {
            smartPrintln(miracleASTreeFunctionDeclaration.getDecorator() + " function " +
                    miracleASTreeFunctionDeclaration.getIdentifier());
        }
        smartPrintln("return type: " + miracleASTreeFunctionDeclaration.getType().toString());
        if (miracleASTreeFunctionDeclaration.getArguments().size() > 0) {
            smartPrintln("arguments: ");
            for (MiracleASTreeVariableDeclaration entry : miracleASTreeFunctionDeclaration.getArguments()) {
                entry.accept(this);
            }
        }
        smartPrintln("statements: ");
        for (MiracleASTreeStatement entry : miracleASTreeFunctionDeclaration.getBody()) {
            entry.accept(this);
        }
    }

    @Override
    public void visit(MiracleASTreeConstant miracleASTreeConstant) {
        System.out.print(miracleASTreeConstant.getValue());
    }

    @Override
    public void visit(MiracleASTreeVariable miracleASTreeVariable) {
        System.out.print(miracleASTreeVariable.getIdentifier());
    }

    @Override
    public void visit(MiracleASTreeVariableDeclaration miracleASTreeVariableDeclaration) {
        smartPrintln("var declaration");
        smartPrintln("var id: " + miracleASTreeVariableDeclaration.getIdentifier());
        smartPrintln("var deco: " + miracleASTreeVariableDeclaration.getDecorator());
        smartPrintln("var type: " + miracleASTreeVariableDeclaration.getType().toString());
        if (miracleASTreeVariableDeclaration.getInitExpression() == null) {
            smartPrintln("var exp: null");
        } else {
            smartPrint("var exp: ");
            miracleASTreeVariableDeclaration.getInitExpression().accept(this);
        }
    }

    @Override
    public void visit(MiracleASTreeClassDeclaration miracleASTreeClassDeclaration) {
        smartPrintln("class delcaration");
        smartPrintln("class id: " + miracleASTreeClassDeclaration.getIdentifier());
        smartPrintln("class extend from: " + miracleASTreeClassDeclaration.getExtend());
        smartPrintln("class content: ");
        for (MiracleASTreeMemberDeclaration entry : miracleASTreeClassDeclaration.getChildren()) {
            entry.accept(this);
        }
    }

    @Override
    public void visit(MiracleASTreeNewExpression miracleASTreeNewExpression) {
        smartPrint("new ");
        for (MiracleASTreeExpression size : miracleASTreeNewExpression.getSize()) {
            System.out.print("[");
            size.accept(this);
            System.out.print("]");
        }
        System.out.println();
    }

}
