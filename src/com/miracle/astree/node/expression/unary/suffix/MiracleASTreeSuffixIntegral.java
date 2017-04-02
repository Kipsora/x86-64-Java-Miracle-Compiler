package com.miracle.astree.node.expression.unary.suffix;

import com.miracle.astree.node.expression.MiracleASTreeExpression;
import com.miracle.astree.node.expression.unary.MiracleASTreeUnaryExpression;
import com.miracle.astree.visitor.MiracleASTreeVisitor;
import com.miracle.exceptions.MiracleExceptionLeftValue;
import com.miracle.exceptions.MiracleExceptionSpecialExpression;

import static com.miracle.scanner.listener.MiracleRuntimeMaintainer.MiracleASTreeINT;

public class MiracleASTreeSuffixIntegral extends MiracleASTreeUnaryExpression {
    public MiracleASTreeSuffixIntegral(OPERATOR operator, MiracleASTreeExpression node) {
        super(operator.toString(), node);
        if (!node.getMutable()) {
            throw new MiracleExceptionLeftValue();
        }
        if (!node.getType().equals(MiracleASTreeINT)) {
            throw new MiracleExceptionSpecialExpression("suffix " + operator.toString(),
                    "int", node.getType().toString());
        }
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.visit(this);
    }

    public enum OPERATOR {
        ADD, SUB;

        @Override
        public String toString() {
            return this.equals(ADD) ? "++" : "--";
        }
    }
}
