package com.miracle.astree.node.expression.binary;

import com.miracle.astree.node.expression.MiracleASTreeExpression;
import com.miracle.astree.visitor.MiracleASTreeVisitor;
import com.miracle.exceptions.MiracleExceptionSpecialExpression;

public class MiracleASTreeBinaryLogic extends MiracleASTreeBinaryExpression {
    public MiracleASTreeBinaryLogic(MiracleASTreeExpression left, OPERATOR op, MiracleASTreeExpression right) {
        super("boolean", left, op.toString(), right);
        if (!left.getType().equals("boolean")) {
            throw new MiracleExceptionSpecialExpression("logical", "boolean", left.getType());
        }
        if (!right.getType().equals("boolean")) {
            throw new MiracleExceptionSpecialExpression("logical", "boolean", right.getType());
        }
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.visit(this);
    }

    public enum OPERATOR {
        AND, OR, XOR;

        @Override
        public String toString() {
            if (this.equals(AND)) {
                return "&&";
            } else if (this.equals(OR)) {
                return "||";
            } else {
                return "^";
            }
        }
    }
}
