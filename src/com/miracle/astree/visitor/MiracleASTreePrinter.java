package com.miracle.astree.visitor;

import com.miracle.astree.MiracleASTree;
import com.miracle.astree.statement.*;
import com.miracle.astree.statement.declaration.MiracleASTreeClassDeclaration;
import com.miracle.astree.statement.declaration.MiracleASTreeFunctionDeclaration;
import com.miracle.astree.statement.declaration.MiracleASTreeVariableDeclaration;
import com.miracle.astree.statement.expression.*;
import com.miracle.astree.statement.expression.constant.MiracleASTreeStringConstant;

public class MiracleASTreePrinter extends MiracleASTreeBaseVisitor {
    private int indent;

    private boolean isIndent(Object element) {
        return !(element instanceof MiracleASTreeVariableDeclaration) &&
                !(element instanceof MiracleASTreeSelection) &&
                !(element instanceof MiracleASTreeIteration) &&
                !(element instanceof MiracleASTreeBreak) &&
                !(element instanceof MiracleASTreeContinue) &&
                !(element instanceof MiracleASTreeBlock);
    }

    private void println(String message) {
        for (int i = 1; i <= indent; i++) {
            System.out.print("    ");
        }
        System.out.println(message);
    }

    private void print(String message) {
        for (int i = 1; i <= indent; i++) {
            System.out.print("    ");
        }
        System.out.print(message);
    }

    @Override
    public void visit(MiracleASTree astree) {
        println("[Miracle Program]");
        indent++;
        astree.declarations.forEach((element) -> element.accept(this));
        indent--;
    }

    @Override
    public void visit(MiracleASTreeNew newNode) {
        System.out.print("new " + newNode.variableType.type.toPrintableString() + " ");
        newNode.expressions.forEach((element) -> {
            if (element == null) {
                System.out.print("[]");
            } else {
                System.out.print('[' + element.toPrintableString() + "]");
            }
        });
    }

    @Override
    public void visit(MiracleASTreeCall call) {
        System.out.print("call ");
        System.out.print(call.function.toPrintableString());
        System.out.print("(");
        for (int i = 0; i < call.parameters.size(); i++) {
            if (i > 0) {
                System.out.print(", ");
            }
            System.out.print(call.parameters.get(i).toPrintableString());
        }
        System.out.print(')');
    }

    @Override
    public void visit(MiracleASTreeBlock block) {
        println("[Block]");
        indent++;
        block.statements.forEach((element) -> {
            if (isIndent(element)) {
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
    public void visit(MiracleASTreeBreak breakLiteral) {
        println("break");
    }

    @Override
    public void visit(MiracleASTreeReturn returnLiteral) {
        System.out.print("return ");
        if (returnLiteral.expression != null) {
            System.out.print(returnLiteral.expression.toPrintableString());
        }
    }

    @Override
    public void visit(MiracleASTreeStringConstant stringConstant) {
        System.out.print(stringConstant.getValue());
    }

    @Override
    public void visit(MiracleASTreeContinue continueLiteral) {
        println("continue");
    }

    @Override
    public void visit(MiracleASTreeVariable variable) {
        System.out.print(variable.identifier);
    }

    @Override
    public void visit(MiracleASTreeIteration iteration) {
        if (iteration.initializeExpression != null) {
            print("for init: ");
            System.out.println(iteration.incrementExpression.toPrintableString());
        }
        if (iteration.conditionExpression != null) {
            print("for condition: ");
            System.out.println(iteration.conditionExpression.toPrintableString());
        }
        if (iteration.incrementExpression != null) {
            print("for increment: ");
            System.out.println(iteration.incrementExpression.toPrintableString());
        }
        indent++;
        if (iteration.body instanceof MiracleASTreeBreak) {
            iteration.body.accept(this);
        } else {
            if (isIndent(iteration.body)) {
                print("");
                iteration.body.accept(this);
                System.out.println();
            } else {
                iteration.body.accept(this);
            }
        }
        indent--;
    }

    @Override
    public void visit(MiracleASTreeSelection selection) {
        println("if (" + selection.expression.toPrintableString() + ") then");
        indent++;
        if (selection.branchTrue != null) {
            if (isIndent(selection.branchTrue)) {
                print("");
                selection.branchTrue.accept(this);
                System.out.println();
            } else {
                selection.branchTrue.accept(this);
            }
        }
        indent--;
        if (selection.branchFalse != null) {
            println("else");
            indent++;
            if (isIndent(selection.branchFalse)) {
                print("");
                selection.branchFalse.accept(this);
                System.out.println();
            } else {
                selection.branchFalse.accept(this);
            }
            indent--;
        }
    }

    @Override
    public void visit(MiracleASTreeSubscript subscript) {
        subscript.base.accept(this);
        System.out.print('[');
        subscript.coordinate.accept(this);
        System.out.print(']');
    }

    @Override
    public void visit(MiracleASTreeBinaryExpression binaryExpression) {
        System.out.print(binaryExpression.toPrintableString());
    }

    @Override
    public void visit(MiracleASTreeClassDeclaration classDeclaration) {
        println("[Class Declaration]");
        indent++;
        println("identifier: " + classDeclaration.identifier);
        indent++;
        classDeclaration.variableDeclarations.forEach((element) ->
                element.accept(this));
        classDeclaration.functionDeclarations.forEach((element) ->
                element.accept(this));
        println("");
        indent--;
        indent--;
    }

    @Override
    public void visit(MiracleASTreePrefixExpression prefixExpression) {
        System.out.print(prefixExpression.toPrintableString());
    }

    @Override
    public void visit(MiracleASTreeSuffixExpression suffixExpression) {
        System.out.print(suffixExpression.toPrintableString());
    }

    @Override
    public void visit(MiracleASTreeFunctionDeclaration functionDeclaration) {
        println("[Function Declaration]");
        indent++;
        println("identifier: " + functionDeclaration.identifier);
        println("returnType: " + functionDeclaration.returnType.type.toPrintableString());
        println("parameter:");
        indent++;
        functionDeclaration.parameters.forEach((element) -> element.accept(this));
        indent--;
        println("body:");
        indent++;
        if (functionDeclaration.body != null) {
            functionDeclaration.body.forEach((element) -> {
                if (!(element instanceof MiracleASTreeVariableDeclaration) &&
                        !(element instanceof MiracleASTreeIteration) &&
                        !(element instanceof MiracleASTreeSelection) &&
                        !(element instanceof MiracleASTreeBreak) &&
                        !(element instanceof MiracleASTreeContinue)) {
                    print("");
                    element.accept(this);
                    System.out.println();
                } else {
                    element.accept(this);
                }
            });
        }
        indent--;
        indent--;
    }

    @Override
    public void visit(MiracleASTreeVariableDeclaration variableDeclaration) {
        println("[Variable Declaration]");
        indent++;
        println("variableType: " + variableDeclaration.typenode.type.toPrintableString());
        println("identifier: " + variableDeclaration.identifier);
        if (variableDeclaration.expression != null) {
            print("init: ");
            variableDeclaration.expression.accept(this);
            println("");
        }
        indent--;
    }
}
