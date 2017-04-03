package com.miracle.astree.visitor;

import com.miracle.astree.node.MiracleASTreeNode;
import com.miracle.astree.node.MiracleASTreeRoot;
import com.miracle.astree.node.expression.MiracleASTreeExpression;
import com.miracle.astree.node.expression.binary.MiracleASTreeAssign;
import com.miracle.astree.node.expression.binary.MiracleASTreeBinaryIntegral;
import com.miracle.astree.node.expression.binary.MiracleASTreeBinaryLogic;
import com.miracle.astree.node.expression.binary.MiracleASTreeField;
import com.miracle.astree.node.expression.multiary.MiracleASTreeCallExpression;
import com.miracle.astree.node.expression.multiary.MiracleASTreeNewExpression;
import com.miracle.astree.node.expression.unary.prefix.MiracleASTreePrefixIntegral;
import com.miracle.astree.node.expression.unary.suffix.MiracleASTreeSuffixIntegral;
import com.miracle.astree.node.expression.value.MiracleASTreeConstant;
import com.miracle.astree.node.expression.value.MiracleASTreeFunction;
import com.miracle.astree.node.expression.value.MiracleASTreeThis;
import com.miracle.astree.node.expression.value.MiracleASTreeVariable;
import com.miracle.astree.node.statement.MiracleASTreeBlock;
import com.miracle.astree.node.statement.MiracleASTreeStatement;
import com.miracle.astree.node.statement.declaration.MiracleASTreeClassDeclaration;
import com.miracle.astree.node.statement.declaration.MiracleASTreeFunctionDeclaration;
import com.miracle.astree.node.statement.declaration.MiracleASTreeMemberDeclaration;
import com.miracle.astree.node.statement.declaration.MiracleASTreeVariableDeclaration;
import com.miracle.astree.node.statement.iteration.MiracleASTreeFor;
import com.miracle.exceptions.MiracleExceptionMember;
import com.miracle.exceptions.MiracleExceptionThis;

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
        level++;
    }

    @Override
    public void exit() {
        level--;
    }

    @Override
    public void visit(MiracleASTreeRoot miracleASTreeRoot) {
        smartPrintln("(");
        enter();
        smartPrintln("ROOT");
        for (MiracleASTreeNode entry : miracleASTreeRoot.getChildren()) {
            entry.accept(this);
        }
        exit();
        smartPrintln(")");
    }

    @Override
    public void visit(MiracleASTreeFunctionDeclaration miracleASTreeFunctionDeclaration) {
        smartPrintln("(");
        enter();
        smartPrintln("function " + miracleASTreeFunctionDeclaration.getIdentifier());
        smartPrintln("return type: " + miracleASTreeFunctionDeclaration.getReturnType().toString());
        if (miracleASTreeFunctionDeclaration.getArguments().size() > 0) {
            smartPrintln("arguments: ");
            for (MiracleASTreeVariableDeclaration entry : miracleASTreeFunctionDeclaration.getArguments()) {
                entry.accept(this);
            }
        }
        smartPrintln(miracleASTreeFunctionDeclaration.getBody().size() + " statements: ");
        for (MiracleASTreeStatement entry : miracleASTreeFunctionDeclaration.getBody()) {
            smartPrintln("(");
            entry.accept(this);
            smartPrintln(")");
        }
        exit();
        smartPrintln(")");
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
        smartPrintln("(");
        enter();
        smartPrintln("var declaration");
        smartPrintln("var id: " + miracleASTreeVariableDeclaration.getIdentifier());
        smartPrintln("var type: " + miracleASTreeVariableDeclaration.getType().toString());
        if (miracleASTreeVariableDeclaration.getInitExpression() == null) {
            smartPrintln("var exp: null");
        } else {
            smartPrint("var exp: ");
            miracleASTreeVariableDeclaration.getInitExpression().accept(this);
            System.out.println();
        }
        exit();
        smartPrintln(")");
    }

    @Override
    public void visit(MiracleASTreeClassDeclaration miracleASTreeClassDeclaration) {
        smartPrintln("(");
        enter();
        smartPrintln("class delcaration");
        smartPrintln("class id: " + miracleASTreeClassDeclaration.getIdentifier());
        smartPrintln("class content: ");
        for (MiracleASTreeMemberDeclaration entry : miracleASTreeClassDeclaration.getChildren()) {
            entry.accept(this);
        }
        exit();
        smartPrintln(")");
    }

    @Override
    public void visit(MiracleASTreeNewExpression miracleASTreeNewExpression) {
        System.out.print("new " + miracleASTreeNewExpression.getType().getBasetype() + " ");
        for (MiracleASTreeExpression size : miracleASTreeNewExpression.getSize()) {
            System.out.print("[");
            size.accept(this);
            System.out.print("]");
        }
        for (int i = 1; i <= miracleASTreeNewExpression.getType().getDimension() - miracleASTreeNewExpression.getSize().size(); i++) {
            System.out.print("[]");
        }
        System.out.println();
    }

    @Override
    public void visit(MiracleASTreeFunction miracleASTreeFunction) {
        System.out.print(miracleASTreeFunction.getDeclaration().getIdentifier());
    }

    @Override
    public void visit(MiracleASTreeCallExpression miracleASTreeCallExpression) {
        enter();
        System.out.print("call ");
        miracleASTreeCallExpression.getFunction().accept(this);
        System.out.print('(');
        boolean first = true;
        for (MiracleASTreeExpression exp : miracleASTreeCallExpression.getArguments()) {
            if (!first) {
                System.out.print(',');
            }
            first = false;
            exp.accept(this);
        }
        System.out.print(")");
        exit();
    }

    @Override
    public void visit(MiracleASTreeBlock miracleASTreeBlock) {
        enter();
        smartPrintln("(");
        for (MiracleASTreeStatement statement : miracleASTreeBlock.getStatement()) {
            smartPrint("");
            statement.accept(this);
        }
        smartPrintln(")");
        exit();
    }

    @Override
    public void visit(MiracleASTreeBinaryIntegral miracleASTreeBinaryIntegral) {
        System.out.print(miracleASTreeBinaryIntegral.getSymbol() + ' ');
        miracleASTreeBinaryIntegral.getLeft().accept(this);
        System.out.print(' ');
        miracleASTreeBinaryIntegral.getRight().accept(this);
    }

    @Override
    public void visit(MiracleASTreePrefixIntegral miracleASTreePrefixIntegral) {
        System.out.print(miracleASTreePrefixIntegral.getSymbol() + ' ');
        miracleASTreePrefixIntegral.getNode().accept(this);
    }

    @Override
    public void visit(MiracleASTreeSuffixIntegral miracleASTreeSuffixIntegral) {
        System.out.print(" " + miracleASTreeSuffixIntegral.getSymbol() + "' ");
        miracleASTreeSuffixIntegral.getNode().accept(this);
    }

    @Override
    public void visit(MiracleASTreeBinaryLogic miracleASTreeBinaryLogic) {
        System.out.print(miracleASTreeBinaryLogic.getSymbol() + ' ');
        miracleASTreeBinaryLogic.getLeft().accept(this);
        System.out.print(' ');
        miracleASTreeBinaryLogic.getRight().accept(this);
    }

    @Override
    public void visit(MiracleASTreeAssign miracleASTreeAssign) {
        smartPrint(miracleASTreeAssign.getSymbol() + ' ');
        miracleASTreeAssign.getLeft().accept(this);
        System.out.print(' ');
        miracleASTreeAssign.getRight().accept(this);
        System.out.println();
    }

    @Override
    public void visit(MiracleASTreeField miracleASTreeField) {
        System.out.print(". ");
        miracleASTreeField.getLeft().accept(this);
        System.out.print(" ");
        miracleASTreeField.getRight().accept(this);
    }

    @Override
    public void visit(MiracleASTreeThis miracleASTreeThis) {
        System.out.print("this");
    }

    @Override
    public void visit(MiracleASTreeFor miracleASTreeFor) {
        smartPrintln("for");
        if (miracleASTreeFor.getLeftExpression() != null) {
            smartPrint("  init: ");
            miracleASTreeFor.getLeftExpression().accept(this);
            System.out.println();
        }
        if (miracleASTreeFor.getMiddleExpression() != null) {
            smartPrint("  exp: ");
            miracleASTreeFor.getMiddleExpression().accept(this);
            System.out.println();
        }
        if (miracleASTreeFor.getRightExpression() != null) {
            smartPrint("  each: ");
            miracleASTreeFor.getRightExpression().accept(this);
            System.out.println();
        }
        smartPrintln("  statement:");
        if (miracleASTreeFor.getStatement() != null) {
            miracleASTreeFor.getStatement().accept(this);
        }
    }
}
