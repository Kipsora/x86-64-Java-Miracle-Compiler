package com.miracle.astree.node.expression.binary;

import com.miracle.astree.node.expression.MiracleASTreeExpression;
import com.miracle.astree.node.statement.declaration.MiracleASTreeTypename;
import com.miracle.astree.visitor.MiracleASTreeVisitor;
import com.miracle.exceptions.MiracleExceptionSpecialExpression;

import static com.miracle.scanner.listener.MiracleRuntimeMaintainer.MiracleASTreeBOOLEAN;
import static com.miracle.scanner.listener.MiracleRuntimeMaintainer.MiracleASTreeINT;
import static com.miracle.scanner.listener.MiracleRuntimeMaintainer.MiracleASTreeSTRING;

public class MiracleASTreeBinaryLogic extends MiracleASTreeArithmetic {
    public MiracleASTreeBinaryLogic(MiracleASTreeExpression left, OPERATOR op, MiracleASTreeExpression right) {
        super(MiracleASTreeBOOLEAN, left, op.toString(), right);
        if (!left.getType().equals(MiracleASTreeBOOLEAN)) {
            throw new MiracleExceptionSpecialExpression("logical", "bool",
                    left.getType().toString());
        }
        if (!right.getType().equals(MiracleASTreeBOOLEAN)) {
            throw new MiracleExceptionSpecialExpression("logical", "bool",
                    right.getType().toString());
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
