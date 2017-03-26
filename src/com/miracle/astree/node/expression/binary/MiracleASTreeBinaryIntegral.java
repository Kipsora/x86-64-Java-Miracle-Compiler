package com.miracle.astree.node.expression.binary;

import com.miracle.astree.node.expression.MiracleASTreeExpression;
import com.miracle.astree.visitor.MiracleASTreeVisitor;
import com.miracle.exceptions.MiracleExceptionSpecialExpression;

public class MiracleASTreeBinaryIntegral extends MiracleASTreeBinaryExpression {
    MiracleASTreeBinaryIntegral(MiracleASTreeExpression left, OPERATOR op, MiracleASTreeExpression right) {
        super("int", left, op.toString(), right);
        if (!left.getType().equals("int")) {
            throw new MiracleExceptionSpecialExpression("arithmetic", "int",
                    left.getText(), left.getType());
        }
        if (!right.getType().equals("int")) {
            throw new MiracleExceptionSpecialExpression("arithmetic", "int",
                    right.getText(), right.getType());
        }
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.visit(this);
    }

    public enum OPERATOR {
        ADD, SUB, MUL, DIV, AND, OR, XOR, MOD, SHL, SHR;

        @Override
        public String toString() {
            if (this.equals(ADD)) {
                return "+";
            } else if (this.equals(SUB)) {
                return "-";
            } else if (this.equals(MUL)) {
                return "*";
            } else if (this.equals(DIV)) {
                return "/";
            } else if (this.equals(AND)) {
                return "&";
            } else if (this.equals(OR)) {
                return "|";
            } else if (this.equals(XOR)) {
                return "^";
            } else if (this.equals(MOD)) {
                return "%";
            } else if (this.equals(SHL)) {
                return "<<";
            } else {
                return ">>";
            }
        }
    }
}
