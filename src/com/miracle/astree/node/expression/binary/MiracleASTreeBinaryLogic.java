package com.miracle.astree.node.expression.binary;

import com.miracle.astree.node.expression.MiracleASTreeExpression;
import com.miracle.astree.node.statement.declaration.MiracleASTreeTypename;
import com.miracle.astree.visitor.MiracleASTreeVisitor;
import com.miracle.exceptions.MiracleExceptionSpecialExpression;

public class MiracleASTreeBinaryLogic extends MiracleASTreeBinaryExpression {
    public MiracleASTreeBinaryLogic(MiracleASTreeExpression left, OPERATOR op, MiracleASTreeExpression right) {
        super(new MiracleASTreeTypename("boolean"), left, op.toString(), right);
        if (!"boolean".equals(left.getType())) {
            throw new MiracleExceptionSpecialExpression("logical", "boolean",
                    left.getType().toString());
        }
        if (!"boolean".equals(right.getType())) {
            throw new MiracleExceptionSpecialExpression("logical", "boolean",
                    right.getType().toString());
        }
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.enter();
        visitor.visit(this);
        visitor.exit();
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
