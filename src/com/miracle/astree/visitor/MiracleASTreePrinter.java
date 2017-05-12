package com.miracle.astree.visitor;

import com.miracle.astree.MiracleASTree;
import com.miracle.astree.statement.declaration.MiracleASTreeClassDeclaration;
import com.miracle.astree.statement.declaration.MiracleASTreeFunctionDeclaration;
import com.miracle.astree.statement.declaration.MiracleASTreeVariableDeclaration;
import com.miracle.astree.expression.*;
import com.miracle.astree.statement.*;
import com.miracle.astree.type.MiracleASTreeArrayType;
import com.miracle.astree.type.MiracleASTreeBaseType;
import com.miracle.astree.type.MiracleASTreeFunctionType;

public class MiracleASTreePrinter extends MiracleASTreeBaseVisitor {
    private int indent;

    public void println(String message) {
        for (int i = 1; i <= indent; i++) {
            System.out.print("    ");
        }
        System.out.println(message);
    }

    public void print(String message) {
        for (int i = 1; i <= indent; i++) {
            System.out.print("    ");
        }
        System.out.print(message);
    }

    @Override
    public void visit(MiracleASTree miracleASTree) {
        println("[Miracle Program]");
        indent++;
        miracleASTree.declarations.forEach((element) -> element.accept(this));
        indent--;
    }

    @Override
    public void visit(MiracleASTreeNew miracleASTreeNew) {
        System.out.print("new ");
        miracleASTreeNew.type.accept(this);
        System.out.print(" ");
        miracleASTreeNew.expressions.forEach((element) -> {
            if (element == null) {
                System.out.print("[]");
            } else {
                System.out.print('[');
                element.accept(this);
                System.out.print(']');
            }
        });
    }

    @Override
    public void visit(MiracleASTreeCall miracleASTreeCall) {
        System.out.print("call ");
        miracleASTreeCall.function.accept(this);
        System.out.print("(");
        for (int i = 0; i < miracleASTreeCall.parameters.size(); i++) {
            if (i > 0) {
                System.out.print(", ");
            }
            miracleASTreeCall.parameters.get(i).accept(this);
        }
        System.out.print(')');
    }

    @Override
    public void visit(MiracleASTreeBlock miracleASTreeBlock) {
        println("[Block]");
        indent++;
        miracleASTreeBlock.statements.forEach((element) -> {
            if (!(element instanceof MiracleASTreeVariableDeclaration)) {
                print("");
                element.accept(this);
                System.out.println();
            } else {
                element.accept(this);
            }
        });
        indent--;
    }

    @Override
    public void visit(MiracleASTreeBreak miracleASTreeBreak) {
        println("break");
    }

    @Override
    public void visit(MiracleASTreeReturn miracleASTreeReturn) {
        System.out.print("return ");
        if (miracleASTreeReturn.expression != null) {
            miracleASTreeReturn.expression.accept(this);
        }
    }

    @Override
    public void visit(MiracleASTreeBaseType miracleASTreeBaseType) {
        System.out.print(miracleASTreeBaseType.identifier);
    }

    @Override
    public void visit(MiracleASTreeConstant miracleASTreeConstant) {
        System.out.print(miracleASTreeConstant.getValue());
    }

    @Override
    public void visit(MiracleASTreeContinue miracleASTreeContinue) {
        println("continue");
    }

    @Override
    public void visit(MiracleASTreeVariable miracleASTreeVariable) {
        System.out.print(miracleASTreeVariable.identifier);
    }

    @Override
    public void visit(MiracleASTreeArrayType miracleASTreeArrayType) {
        System.out.print(miracleASTreeArrayType.baseType.identifier);
        for (int i = 0; i < miracleASTreeArrayType.dimension; i++) {
            System.out.print("[]");
        }
    }

    @Override
    public void visit(MiracleASTreeIteration miracleASTreeIteration) {
        print("for init: ");
        if (miracleASTreeIteration.initializeExpression != null) {
            miracleASTreeIteration.initializeExpression.accept(this);
        }
        println("");
        print("for condition: ");
        if (miracleASTreeIteration.conditionExpression != null) {
            miracleASTreeIteration.conditionExpression.accept(this);
        }
        println("");
        print("for increment: ");
        if (miracleASTreeIteration.incrementExpression != null) {
            miracleASTreeIteration.incrementExpression.accept(this);
        }
        println("");
        indent++;
        miracleASTreeIteration.body.accept(this);
        indent--;
    }

    @Override
    public void visit(MiracleASTreeSelection miracleASTreeSelection) {
        print("if (");
        miracleASTreeSelection.expression.accept(this);
        println(") then");
        indent++;
        miracleASTreeSelection.branchTrue.accept(this);
        indent--;
        if (miracleASTreeSelection.branchFalse != null) {
            println("else");
            indent++;
            miracleASTreeSelection.branchFalse.accept(this);
            indent--;
        }
    }

    @Override
    public void visit(MiracleASTreeSubscript miracleASTreeSubscript) {
        miracleASTreeSubscript.base.accept(this);
        System.out.print('[');
        miracleASTreeSubscript.coordinate.accept(this);
        System.out.print(']');
    }

    @Override
    public void visit(MiracleASTreeFunctionType miracleASTreeFunctionType) {
        super.visit(miracleASTreeFunctionType);
    }

    @Override
    public void visit(MiracleASTreeBinaryExpression miracleASTreeBinaryExpression) {
        System.out.print(miracleASTreeBinaryExpression.operator.toString());
        System.out.print(" ");
        miracleASTreeBinaryExpression.left.accept(this);
        System.out.print(" ");
        miracleASTreeBinaryExpression.right.accept(this);
    }

    @Override
    public void visit(MiracleASTreeClassDeclaration miracleASTreeClassDeclaration) {
        println("[Class Declaration]");
        indent++;
        println("identifier: " + miracleASTreeClassDeclaration.identifier);
        indent++;
        miracleASTreeClassDeclaration.variableDeclarations.forEach((element) ->
                element.accept(this));
        miracleASTreeClassDeclaration.functionDeclarations.forEach((element) ->
                element.accept(this));
        println("");
        indent--;
        indent--;
    }

    @Override
    public void visit(MiracleASTreePrefixExpression miracleASTreePrefixExpression) {
        System.out.print("p" + miracleASTreePrefixExpression.operator.toString() + " ");
        miracleASTreePrefixExpression.expression.accept(this);
    }

    @Override
    public void visit(MiracleASTreeSuffixExpression miracleASTreeSuffixExpression) {
        System.out.print("s" + miracleASTreeSuffixExpression.operator.toString() + " ");
        miracleASTreeSuffixExpression.expression.accept(this);
    }

    @Override
    public void visit(MiracleASTreeFunctionDeclaration miracleASTreeFunctionDeclaration) {
        println("[Function Declaration]");
        indent++;
        println("identifier: " + miracleASTreeFunctionDeclaration.identifier);
        print("returnType: ");
        miracleASTreeFunctionDeclaration.returnType.accept(this);
        println("");
        println("body:");
        indent++;
        miracleASTreeFunctionDeclaration.parameters.forEach((element) -> element.accept(this));
        miracleASTreeFunctionDeclaration.body.forEach((element) -> {
            if (!(element instanceof MiracleASTreeVariableDeclaration)) {
                print("");
                element.accept(this);
                System.out.println();
            } else {
                element.accept(this);
            }
        });
        indent--;
        indent--;
    }

    @Override
    public void visit(MiracleASTreeVariableDeclaration miracleASTreeVariableDeclaration) {
        println("[Variable Declaration]");
        indent++;
        print("type: ");
        miracleASTreeVariableDeclaration.type.accept(this);
        println("");
        println("identifier: " + miracleASTreeVariableDeclaration.identifier);
        if (miracleASTreeVariableDeclaration.expression != null) {
            print("init: ");
            miracleASTreeVariableDeclaration.expression.accept(this);
            println("");
        }
        indent--;
    }
}
