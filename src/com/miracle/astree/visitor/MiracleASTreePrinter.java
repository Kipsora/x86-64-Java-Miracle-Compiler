package com.miracle.astree.visitor;

import com.miracle.astree.MiracleASTree;
import com.miracle.astree.expression.MiracleASTreeCall;
import com.miracle.astree.expression.MiracleASTreeNew;
import com.miracle.astree.statement.MiracleASTreeBlock;
import com.miracle.cstree.parser.MiracleBaseListener;

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
        print("[Miracle Program]");
    }

    @Override
    public void visit(MiracleASTreeNew miracleASTreeNew) {
        System.out.print("new " + miracleASTreeNew.type.getBaseType().identifier + " ");
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
        System.out.print("call (");
        miracleASTreeCall.function.accept(this);
        System.out.print(") with parameter (");
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
        miracleASTreeBlock.statements.forEach();
        indent--;
    }
}
