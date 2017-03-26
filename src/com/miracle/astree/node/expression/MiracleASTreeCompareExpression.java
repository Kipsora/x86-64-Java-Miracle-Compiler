package com.miracle.astree.node.expression;

import com.miracle.astree.visitor.MiracleASTreeVisitor;
import com.miracle.exceptions.MiracleExceptionExpressionTypeError;

public class MiracleASTreeCompareExpression extends MiracleASTreeExpression {
    private final OPERATOR operator;

    protected MiracleASTreeCompareExpression(MiracleASTreeExpression left,
                                             OPERATOR operator, MiracleASTreeExpression right) {
        super("boolean");
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

    public enum OPERATOR {
        L, LEQ, R, REQ;
        @Override
        public String toString() {
            if (this.equals(L)) {
                return "<";
            } else if (this.equals(LEQ)) {
                return "<=";
            } else if (this.equals(R)) {
                return ">";
            } else {
                return ">=";
            }
        }
    }
}
