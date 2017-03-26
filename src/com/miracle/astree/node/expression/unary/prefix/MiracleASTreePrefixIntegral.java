package com.miracle.astree.node.expression.unary.prefix;

import com.miracle.astree.node.expression.MiracleASTreeExpression;
import com.miracle.astree.visitor.MiracleASTreeVisitor;
import com.miracle.exceptions.MiracleExceptionSpecialExpression;

public class MiracleASTreePrefixIntegral extends MiracleASTreePrefixExpression {
    protected MiracleASTreePrefixIntegral(OPERATOR op, MiracleASTreeExpression node) {
        super(op.toString(), node);
        if (!node.getType().equals("int")) {
            throw new MiracleExceptionSpecialExpression("prefix " + op.toString(),
                    "int", node.getType());
        }
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.visit(this);
    }

    public enum OPERATOR{
        ADD, SUB, REV;

        @Override
        public String toString() {
            if (this.equals(ADD)) {
                return "++";
            } else if (this.equals(SUB)) {
                return "--";
            } else {
                return "~";
            }
        }
    }
}
