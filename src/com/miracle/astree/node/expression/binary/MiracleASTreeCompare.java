package com.miracle.astree.node.expression.binary;

import com.miracle.astree.node.MiracleASTreeTypename;
import com.miracle.astree.node.expression.MiracleASTreeExpression;
import com.miracle.astree.visitor.MiracleASTreeVisitor;

public class MiracleASTreeCompare extends MiracleASTreeBinaryExpression {
    public MiracleASTreeCompare(MiracleASTreeExpression left, OPERATOR operator, MiracleASTreeExpression right) {
        super(new MiracleASTreeTypename("boolean"), left, operator.toString(), right);
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
