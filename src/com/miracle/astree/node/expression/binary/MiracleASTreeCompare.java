package com.miracle.astree.node.expression.binary;

import com.miracle.astree.node.expression.MiracleASTreeExpression;
import com.miracle.astree.visitor.MiracleASTreeVisitor;

/**
 * Created by kipsora on 17-3-26.
 */
public class MiracleASTreeCompare extends MiracleASTreeBinaryExpression {
    MiracleASTreeCompare(MiracleASTreeExpression left, OPERATOR operator, MiracleASTreeExpression right) {
        super("boolean", left, operator.toString(), right);
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
