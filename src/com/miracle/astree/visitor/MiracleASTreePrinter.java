package com.miracle.astree.visitor;

import com.miracle.astree.MiracleASTree;
import com.miracle.astree.statement.*;
import com.miracle.astree.statement.declaration.MiracleASTreeClassDeclaration;
import com.miracle.astree.statement.declaration.MiracleASTreeFunctionDeclaration;
import com.miracle.astree.statement.declaration.MiracleASTreeVariableDeclaration;
import com.miracle.astree.statement.expression.*;
import com.miracle.astree.statement.expression.constant.MiracleASTreeStringConstant;

public class MiracleASTreePrinter extends MiracleASTreeBaseVisitor {
    private StringBuilder builder;
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
            builder.append("    ");
        }
        builder.append(message).append('\n');
    }

    private void print(String message) {
        for (int i = 1; i <= indent; i++) {
            builder.append("    ");
        }
        builder.append(message).append('\n');
    }

    @Override
    public void visit(MiracleASTree astree) {
        builder = new StringBuilder();
        println("[Miracle Program]");
        indent++;
        astree.declarations.forEach((element) -> element.accept(this));
        indent--;
    }

    @Override
    public void visit(MiracleASTreeNew newNode) {
        builder.append("new ").append(newNode.variableType.getType().toPrintableString()).append(" ");
        newNode.expressions.forEach((element) -> {
            if (element == null) {
                builder.append("[]");
            } else {
                builder.append('[').append(element.toPrintableString()).append("]");
            }
        });
    }

    @Override
    public void visit(MiracleASTreeCall call) {
        builder.append("call ");
        builder.append(call.function.toPrintableString());
        builder.append("(");
        for (int i = 0; i < call.parameters.size(); i++) {
            if (i > 0) {
                builder.append(", ");
            }
            builder.append(call.parameters.get(i).toPrintableString());
        }
        builder.append(')');
    }

    @Override
    public void visit(MiracleASTreeBlock block) {
        println("[Block]");
        indent++;
        block.statements.forEach((element) -> {
            if (isIndent(element)) {
                print("");
                element.accept(this);
                builder.append('\n');
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
        builder.append("return ");
        if (returnLiteral.expression != null) {
            builder.append(returnLiteral.expression.toPrintableString());
        }
    }

    @Override
    public void visit(MiracleASTreeStringConstant stringConstant) {
        builder.append(stringConstant.getValue());
    }

    @Override
    public void visit(MiracleASTreeContinue continueLiteral) {
        println("continue");
    }

    @Override
    public void visit(MiracleASTreeVariable variable) {
        builder.append(variable.identifier);
    }

    @Override
    public void visit(MiracleASTreeIteration iteration) {
        if (iteration.initializeExpression != null) {
            print("for init: ");
            builder.append(iteration.incrementExpression.toPrintableString()).append('\n');
        }
        if (iteration.conditionExpression != null) {
            print("for condition: ");
            builder.append(iteration.conditionExpression.toPrintableString()).append('\n');
        }
        if (iteration.incrementExpression != null) {
            print("for increment: ");
            builder.append(iteration.incrementExpression.toPrintableString()).append('\n');
        }
        if (iteration.body != null) {
            indent++;
            if (iteration.body instanceof MiracleASTreeBreak) {
                iteration.body.accept(this);
            } else {
                if (isIndent(iteration.body)) {
                    print("");
                    iteration.body.accept(this);
                    builder.append('\n');
                } else {
                    iteration.body.accept(this);
                }
            }
            indent--;
        }
    }

    @Override
    public void visit(MiracleASTreeSelection selection) {
        println("if (" + selection.expression.toPrintableString() + ") then");
        indent++;
        if (selection.branchTrue != null) {
            if (isIndent(selection.branchTrue)) {
                print("");
                selection.branchTrue.accept(this);
                builder.append('\n');
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
                builder.append('\n');
            } else {
                selection.branchFalse.accept(this);
            }
            indent--;
        }
    }

    @Override
    public void visit(MiracleASTreeSubscript subscript) {
        subscript.base.accept(this);
        builder.append('[');
        subscript.coordinate.accept(this);
        builder.append(']');
    }

    @Override
    public void visit(MiracleASTreeBinaryExpression binaryExpression) {
        builder.append(binaryExpression.toPrintableString());
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
        builder.append(prefixExpression.toPrintableString());
    }

    @Override
    public void visit(MiracleASTreeSuffixExpression suffixExpression) {
        builder.append(suffixExpression.toPrintableString());
    }

    @Override
    public void visit(MiracleASTreeFunctionDeclaration functionDeclaration) {
        println("[Function Declaration]");
        indent++;
        println("identifier: " + functionDeclaration.identifier);
        println("returnType: " + functionDeclaration.returnType.getType().toPrintableString());
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
                    builder.append('\n');
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
        println("variableType: " + variableDeclaration.typenode.getType().toPrintableString());
        println("identifier: " + variableDeclaration.identifier);
        if (variableDeclaration.expression != null) {
            print("init: ");
            variableDeclaration.expression.accept(this);
            println("");
        }
        indent--;
    }

    public String getOutput() {
        return builder.toString();
    }
}
