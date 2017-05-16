package com.miracle.astree.visitor;

import com.miracle.astree.MiracleASTree;
import com.miracle.astree.statement.*;
import com.miracle.astree.statement.declaration.MiracleASTreeClassDeclaration;
import com.miracle.astree.statement.declaration.MiracleASTreeFunctionDeclaration;
import com.miracle.astree.statement.declaration.MiracleASTreeVariableDeclaration;
import com.miracle.astree.statement.expression.*;
import com.miracle.astree.statement.expression.constant.MiracleASTreeStringConstant;

import java.io.PrintStream;

public class MiracleASTreePrinter extends MiracleASTreeBaseVisitor {
    private final PrintStream ostream;
    private int indent;

    public MiracleASTreePrinter(PrintStream ostream) {
        this.ostream = ostream;
    }

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
            ostream.print("    ");
        }
        ostream.println(message);
    }

    private void print(String message) {
        for (int i = 1; i <= indent; i++) {
            ostream.print("    ");
        }
        ostream.print(message);
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
        ostream.print("new " + newNode.variableType.type.toPrintableString() + " ");
        newNode.expressions.forEach((element) -> {
            if (element == null) {
                ostream.print("[]");
            } else {
                ostream.print('[' + element.toPrintableString() + "]");
            }
        });
    }

    @Override
    public void visit(MiracleASTreeCall call) {
        ostream.print("call ");
        ostream.print(call.function.toPrintableString());
        ostream.print("(");
        for (int i = 0; i < call.parameters.size(); i++) {
            if (i > 0) {
                ostream.print(", ");
            }
            ostream.print(call.parameters.get(i).toPrintableString());
        }
        ostream.print(')');
    }

    @Override
    public void visit(MiracleASTreeBlock block) {
        println("[Block]");
        indent++;
        block.statements.forEach((element) -> {
            if (isIndent(element)) {
                print("");
                element.accept(this);
                ostream.println();
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
        ostream.print("return ");
        if (returnLiteral.expression != null) {
            ostream.print(returnLiteral.expression.toPrintableString());
        }
    }

    @Override
    public void visit(MiracleASTreeStringConstant stringConstant) {
        ostream.print(stringConstant.getValue());
    }

    @Override
    public void visit(MiracleASTreeContinue continueLiteral) {
        println("continue");
    }

    @Override
    public void visit(MiracleASTreeVariable variable) {
        ostream.print(variable.identifier);
    }

    @Override
    public void visit(MiracleASTreeIteration iteration) {
        if (iteration.initializeExpression != null) {
            print("for init: ");
            ostream.println(iteration.incrementExpression.toPrintableString());
        }
        if (iteration.conditionExpression != null) {
            print("for condition: ");
            ostream.println(iteration.conditionExpression.toPrintableString());
        }
        if (iteration.incrementExpression != null) {
            print("for increment: ");
            ostream.println(iteration.incrementExpression.toPrintableString());
        }
        if (iteration.body != null) {
            indent++;
            if (iteration.body instanceof MiracleASTreeBreak) {
                iteration.body.accept(this);
            } else {
                if (isIndent(iteration.body)) {
                    print("");
                    iteration.body.accept(this);
                    ostream.println();
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
                ostream.println();
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
                ostream.println();
            } else {
                selection.branchFalse.accept(this);
            }
            indent--;
        }
    }

    @Override
    public void visit(MiracleASTreeSubscript subscript) {
        subscript.base.accept(this);
        ostream.print('[');
        subscript.coordinate.accept(this);
        ostream.print(']');
    }

    @Override
    public void visit(MiracleASTreeBinaryExpression binaryExpression) {
        ostream.print(binaryExpression.toPrintableString());
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
        ostream.print(prefixExpression.toPrintableString());
    }

    @Override
    public void visit(MiracleASTreeSuffixExpression suffixExpression) {
        ostream.print(suffixExpression.toPrintableString());
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
                    ostream.println();
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
