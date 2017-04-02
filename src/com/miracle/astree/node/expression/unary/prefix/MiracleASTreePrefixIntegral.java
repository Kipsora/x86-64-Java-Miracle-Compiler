package com.miracle.astree.node.expression.unary.prefix;

import com.miracle.astree.node.expression.MiracleASTreeExpression;
import com.miracle.astree.node.expression.unary.MiracleASTreeUnaryExpression;
import com.miracle.astree.visitor.MiracleASTreeVisitor;
import com.miracle.exceptions.MiracleExceptionSpecialExpression;

import static com.miracle.scanner.listener.MiracleRuntimeMaintainer.MiracleASTreeINT;

public class MiracleASTreePrefixIntegral extends MiracleASTreeUnaryExpression {
    public MiracleASTreePrefixIntegral(OPERATOR operator, MiracleASTreeExpression node) {
        super(operator.toString(), node);
        if (!node.getType().equals(MiracleASTreeINT)) {
            throw new MiracleExceptionSpecialExpression("prefix " + operator.toString(),
                    "int", node.getType().toString());
        }
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.visit(this);
    }

    public enum OPERATOR {
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
