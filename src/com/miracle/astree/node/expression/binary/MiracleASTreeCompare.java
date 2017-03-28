package com.miracle.astree.node.expression.binary;

import com.miracle.astree.node.MiracleASTreeTypename;
import com.miracle.astree.node.expression.MiracleASTreeExpression;
import com.miracle.astree.visitor.MiracleASTreeVisitor;
import com.miracle.exceptions.MiracleExceptionCompareExpression;

public class MiracleASTreeCompare extends MiracleASTreeBinaryExpression {
    public MiracleASTreeCompare(MiracleASTreeExpression left, OPERATOR operator, MiracleASTreeExpression right) {
        super(new MiracleASTreeTypename("boolean"), left, operator.toString(), right);
        if (!left.getType().equals(right.getType())) {
            throw new MiracleExceptionCompareExpression(left.getType().toString(), right.getType().toString());
        }
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.enter();
        visitor.visit(this);
        visitor.exit();
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
