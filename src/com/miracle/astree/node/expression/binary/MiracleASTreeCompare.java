package com.miracle.astree.node.expression.binary;

import com.miracle.astree.node.expression.MiracleASTreeExpression;
import com.miracle.astree.node.statement.declaration.MiracleASTreeTypename;
import com.miracle.astree.visitor.MiracleASTreeVisitor;
import com.miracle.exceptions.MiracleExceptionCompareExpression;

import static com.miracle.scanner.listener.MiracleRuntimeMaintainer.MiracleASTreeBOOLEAN;
import static com.miracle.scanner.listener.MiracleRuntimeMaintainer.MiracleASTreeINT;

public class MiracleASTreeCompare extends MiracleASTreeArithmetic {
    public MiracleASTreeCompare(MiracleASTreeExpression left, OPERATOR operator, MiracleASTreeExpression right) {
        super(MiracleASTreeBOOLEAN, left, operator.toString(), right);
        if (!left.getType().equals(right.getType())) {
            throw new MiracleExceptionCompareExpression(left.getType().toString(), right.getType().toString());
        }
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.visit(this);
    }

    public enum OPERATOR {
        L, LEQ, R, REQ, EQ, NEQ;

        @Override
        public String toString() {
            if (this.equals(L)) {
                return "<";
            } else if (this.equals(R)) {
                return ">";
            } else if (this.equals(LEQ)) {
                return "<=";
            } else if (this.equals(REQ)) {
                return ">=";
            } else if (this.equals(EQ)) {
                return "==";
            } else {
                return "!=";
            }
        }
    }
}
