package com.miracle.astree.node.expression.unary.prefix;

import com.miracle.astree.node.expression.MiracleASTreeExpression;
import com.miracle.astree.node.expression.unary.MiracleASTreeUnaryExpression;
import com.miracle.astree.visitor.MiracleASTreeVisitor;
import com.miracle.exceptions.MiracleExceptionSpecialExpression;

public class MiracleASTreePrefixIntegral extends MiracleASTreeUnaryExpression {
    public MiracleASTreePrefixIntegral(OPERATOR op, MiracleASTreeExpression node) {
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
        ADD, SUB, REV, NEG, POS;

        @Override
        public String toString() {
            if (this.equals(ADD)) {
                return "++";
            } else if (this.equals(SUB)) {
                return "--";
            } else if (this.equals(REV)) {
                return "~";
            } else if (this.equals(NEG)) {
                return "-";
            } else {
                return "+";
            }
        }
    }
}
