package com.miracle.astree.node.expression;

import com.miracle.astree.visitor.MiracleASTreeVisitor;
import com.miracle.exceptions.MiracleExceptionExpressionTypeError;

public class MiracleASTreeBinaryExpression extends MiracleASTreeExpression {
    private final OPERATOR operator;

    public MiracleASTreeBinaryExpression(MiracleASTreeExpression left, OPERATOR operator, MiracleASTreeExpression right) {
        super(left.getType());
        String leftType = left.getType();
        String rightType = right.getType();
        if (!leftType.equals(rightType)) {
            throw new MiracleExceptionExpressionTypeError(leftType, operator.toString(), rightType);
        }
        this.operator = operator;
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.visit(this);
    }

    public OPERATOR getOperator() {
        return operator;
    }

    public enum OPERATOR {
        ADD, SUB, MUL, DIV;

        @Override
        public String toString() {
            if (this.equals(ADD)) {
                return "+";
            } else if (this.equals(SUB)) {
                return "-";
            } else if (this.equals(MUL)) {
                return "*";
            } else {
                return "/";
            }
        }
    }
}
