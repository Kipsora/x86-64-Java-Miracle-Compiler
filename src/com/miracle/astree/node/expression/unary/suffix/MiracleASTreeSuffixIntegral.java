package com.miracle.astree.node.expression.unary.suffix;

import com.miracle.astree.node.expression.MiracleASTreeExpression;
import com.miracle.astree.node.expression.unary.MiracleASTreeUnaryExpression;
import com.miracle.astree.visitor.MiracleASTreeVisitor;
import com.miracle.exceptions.MiracleExceptionSpecialExpression;

public class MiracleASTreeSuffixIntegral extends MiracleASTreeUnaryExpression {
    public MiracleASTreeSuffixIntegral(OPERATOR operator, MiracleASTreeExpression node) {
        super(operator.toString(), node);
        if (!node.getType().equals("int")) {
            throw new MiracleExceptionSpecialExpression("suffix " + operator.toString(),
                    "int", node.getType().toString());
        }
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.enter();
        visitor.visit(this);
        visitor.enter();
    }

    public enum OPERATOR {
        ADD, SUB;

        @Override
        public String toString() {
            return this.equals(ADD) ? "++" : "--";
        }
    }
}
