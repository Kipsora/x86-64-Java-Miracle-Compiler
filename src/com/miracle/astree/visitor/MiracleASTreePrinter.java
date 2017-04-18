package com.miracle.astree.visitor;

import com.miracle.astree.node.MiracleASTreeRoot;
import com.miracle.astree.node.expression.MiracleASTreeExpression;
import com.miracle.astree.node.expression.binary.*;
import com.miracle.astree.node.expression.multiary.MiracleASTreeCallExpression;
import com.miracle.astree.node.expression.multiary.MiracleASTreeNewExpression;
import com.miracle.astree.node.expression.unary.prefix.MiracleASTreeNegate;
import com.miracle.astree.node.expression.unary.prefix.MiracleASTreePrefixIntegral;
import com.miracle.astree.node.expression.unary.suffix.MiracleASTreeSuffixIntegral;
import com.miracle.astree.node.expression.value.MiracleASTreeConstant;
import com.miracle.astree.node.expression.value.MiracleASTreeFunction;
import com.miracle.astree.node.expression.value.MiracleASTreeThis;
import com.miracle.astree.node.expression.value.MiracleASTreeVariable;
import com.miracle.astree.node.statement.MiracleASTreeBlock;
import com.miracle.astree.node.statement.MiracleASTreeSelection;
import com.miracle.astree.node.statement.MiracleASTreeStatement;
import com.miracle.astree.node.statement.control.MiracleASTreeBreak;
import com.miracle.astree.node.statement.control.MiracleASTreeContinue;
import com.miracle.astree.node.statement.control.MiracleASTreeReturn;
import com.miracle.astree.node.statement.declaration.MiracleASTreeClassDeclaration;
import com.miracle.astree.node.statement.declaration.MiracleASTreeFunctionDeclaration;
import com.miracle.astree.node.statement.declaration.MiracleASTreeMemberDeclaration;
import com.miracle.astree.node.statement.declaration.MiracleASTreeVariableDeclaration;
import com.miracle.astree.node.statement.iteration.MiracleASTreeFor;
import com.miracle.astree.node.statement.iteration.MiracleASTreeWhile;

import java.util.List;

public class MiracleASTreePrinter extends MiracleASTreeBaseVisitor {
    private int scopeDepth = 0;

    @Override
    public void visit(MiracleASTreeRoot miracleASTreeRoot) {
        miracleASTreeRoot.getChildren().forEach((entry) -> {
            entry.accept(this);
            System.out.println();
            System.out.println();
            System.out.println();
        });
    }

    void print(String message) {
        for (int i = 1; i <= 4 * scopeDepth; i++) {
            System.out.print(' ');
        }
        System.out.print(message);
    }

    @Override
    public void visit(MiracleASTreeClassDeclaration miracleASTreeClassDeclaration) {
        System.out.print("DEC Class " + miracleASTreeClassDeclaration.getIdentifier());
        scopeDepth++;
        boolean first = true;
        for (MiracleASTreeMemberDeclaration entry : miracleASTreeClassDeclaration.getChildren()) {
            if (!first) System.out.println();
            System.out.println();
            print("");
            entry.accept(this);
            first = false;
        }
        scopeDepth--;
    }

    @Override
    public void visit(MiracleASTreeVariableDeclaration miracleASTreeVariableDeclaration) {
        System.out.print("DEC Var " + miracleASTreeVariableDeclaration.getIdentifier() + " OF Type "
                + miracleASTreeVariableDeclaration.getType().toString());
        if (miracleASTreeVariableDeclaration.getInitExpression() != null) {
            System.out.println();
            print("|-Init Expr: ");
            miracleASTreeVariableDeclaration.getInitExpression().accept(this);
        }
    }

    @Override
    public void visit(MiracleASTreeAssign miracleASTreeAssign) {
        System.out.print(miracleASTreeAssign.getSymbol() + " ");
        miracleASTreeAssign.getLeft().accept(this);
        System.out.print(" ");
        miracleASTreeAssign.getRight().accept(this);
    }

    @Override
    public void visit(MiracleASTreeStringConcat miracleASTreeStringConcat) {
        System.out.print("s" + miracleASTreeStringConcat.getSymbol() + " ");
        miracleASTreeStringConcat.getLeft().accept(this);
        System.out.print(" ");
        miracleASTreeStringConcat.getRight().accept(this);
    }

    @Override
    public void visit(MiracleASTreeBinaryIntegral miracleASTreeBinaryIntegral) {
        System.out.print(miracleASTreeBinaryIntegral.getSymbol() + " ");
        miracleASTreeBinaryIntegral.getLeft().accept(this);
        System.out.print(" ");
        miracleASTreeBinaryIntegral.getRight().accept(this);
    }

    @Override
    public void visit(MiracleASTreeCompare miracleASTreeCompare) {
        System.out.print(miracleASTreeCompare.getSymbol() + " ");
        miracleASTreeCompare.getLeft().accept(this);
        System.out.print(" ");
        miracleASTreeCompare.getRight().accept(this);
    }

    @Override
    public void visit(MiracleASTreeBinaryLogic miracleASTreeBinaryLogic) {
        System.out.print(miracleASTreeBinaryLogic.getSymbol() + " ");
        miracleASTreeBinaryLogic.getLeft().accept(this);
        System.out.print(" ");
        miracleASTreeBinaryLogic.getRight().accept(this);
    }

    @Override
    public void visit(MiracleASTreePrefixIntegral miracleASTreePrefixIntegral) {
        System.out.print("p" + miracleASTreePrefixIntegral.getSymbol() + " ");
        miracleASTreePrefixIntegral.getNode().accept(this);
    }

    @Override
    public void visit(MiracleASTreeNegate miracleASTreeNegate) {
        System.out.print(miracleASTreeNegate.getSymbol() + " ");
        miracleASTreeNegate.getNode().accept(this);
    }

    @Override
    public void visit(MiracleASTreeSuffixIntegral miracleASTreeSuffixIntegral) {
        System.out.print("s" + miracleASTreeSuffixIntegral.getSymbol() + " ");
        miracleASTreeSuffixIntegral.getNode().accept(this);
    }

    @Override
    public void visit(MiracleASTreeNewExpression miracleASTreeNewExpression) {
        System.out.print("new " + miracleASTreeNewExpression.getType().toString() + " ");
        List<MiracleASTreeExpression> children = miracleASTreeNewExpression.getSize();
        for (int i = 0; i < miracleASTreeNewExpression.getType().getDimension(); i++) {
            System.out.print('[');
            if (i < children.size()) {
                children.get(i).accept(this);
            }
            System.out.print(']');
        }
    }

    @Override
    public void visit(MiracleASTreeFunctionDeclaration miracleASTreeFunctionDeclaration) {
        System.out.println("DEC FUNC: " + miracleASTreeFunctionDeclaration.getIdentifier());
        print("|-RET TYPE: " + miracleASTreeFunctionDeclaration.getReturnType().toString() + "\n");
        print("|-FUN TYPE: " + miracleASTreeFunctionDeclaration.getType().toString());
        scopeDepth++;
        boolean first = true;
        for (MiracleASTreeStatement entry : miracleASTreeFunctionDeclaration.getBody()) {
            if (!first) {
                System.out.println();
            }
            first = false;
            System.out.println();
            print("");
            entry.accept(this);
        }
        scopeDepth--;
    }

    @Override
    public void visit(MiracleASTreeSubscript miracleASTreeSubscript) {
        System.out.print(miracleASTreeSubscript.getSymbol() + " ");
        miracleASTreeSubscript.getLeft().accept(this);
        System.out.print(" ");
        miracleASTreeSubscript.getRight().accept(this);
    }

    @Override
    public void visit(MiracleASTreeThis miracleASTreeThis) {
        System.out.print("this");
    }

    @Override
    public void visit(MiracleASTreeField miracleASTreeField) {
        System.out.print(miracleASTreeField.getSymbol() + " ");
        miracleASTreeField.getLeft().accept(this);
        System.out.print(" ");
        miracleASTreeField.getRight().accept(this);
    }

    @Override
    public void visit(MiracleASTreeBlock miracleASTreeBlock) {
        List<MiracleASTreeStatement> children = miracleASTreeBlock.getStatement();
        boolean first = true;
        for (MiracleASTreeStatement entry : children) {
            if (!first) {
                System.out.println();
                System.out.println();
                print("");
            }
            first = false;
            entry.accept(this);
        }
    }

    @Override
    public void visit(MiracleASTreeSelection miracleASTreeSelection) {
        System.out.print("if (");
        miracleASTreeSelection.getExpression().accept(this);
        System.out.println(")");
        print("|-True Block: \n");
        scopeDepth++;
        print("");
        miracleASTreeSelection.getTrueStatement().accept(this);
        scopeDepth--;
        if (miracleASTreeSelection.getFalseStatement() != null) {
            System.out.println();
            print("|-Else Block: \n");
            scopeDepth++;
            print("");
            miracleASTreeSelection.getFalseStatement().accept(this);
            scopeDepth--;
        }
    }

    @Override
    public void visit(MiracleASTreeFor miracleASTreeFor) {
        System.out.print("for init:(");
        if (miracleASTreeFor.getLeftExpression() != null) {
            miracleASTreeFor.getLeftExpression().accept(this);
        }
        System.out.print(") judge:(");
        if (miracleASTreeFor.getMiddleExpression() != null) {
            miracleASTreeFor.getMiddleExpression().accept(this);
        }
        System.out.print(") each:(");
        if (miracleASTreeFor.getRightExpression() != null) {
            miracleASTreeFor.getRightExpression().accept(this);
        }
        System.out.println(")");
        if (miracleASTreeFor.getStatement() != null) {
            scopeDepth++;
            print("");
            miracleASTreeFor.getStatement().accept(this);
            scopeDepth--;
        }
    }

    @Override
    public void visit(MiracleASTreeWhile miracleASTreeWhile) {
        System.out.print("while judge:(");
        miracleASTreeWhile.getExpression().accept(this);
        System.out.println(")");
        if (miracleASTreeWhile.getStatement() != null) {
            scopeDepth++;
            print("");
            miracleASTreeWhile.getStatement().accept(this);
            scopeDepth--;
        }
    }

    @Override
    public void visit(MiracleASTreeContinue miracleASTreeContinue) {
        System.out.print("continue");
    }

    @Override
    public void visit(MiracleASTreeBreak miracleASTreeBreak) {
        System.out.print("break");
    }

    @Override
    public void visit(MiracleASTreeReturn miracleASTreeReturn) {
        System.out.print("return (");
        miracleASTreeReturn.getExpression().accept(this);
        System.out.print(")");
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
    public void visit(MiracleASTreeCallExpression miracleASTreeCallExpression) {
        System.out.print(miracleASTreeCallExpression.getSymbol() + " ");
        miracleASTreeCallExpression.getFunction().accept(this);
        System.out.print(" (");
        List<MiracleASTreeExpression> children = miracleASTreeCallExpression.getArguments();
        for (int i = 0; i < children.size(); i++) {
            if (i > 0) {
                System.out.print(", ");
            }
            children.get(i).accept(this);
        }
        System.out.print(")");
    }

    @Override
    public void visit(MiracleASTreeFunction miracleASTreeFunction) {
        System.out.print(miracleASTreeFunction.getDeclaration().getIdentifier());
    }
}
