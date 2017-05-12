package com.miracle.astree.expression;

import com.miracle.astree.visitor.MiracleASTreeVisitor;

public class MiracleASTreePrefixExpression extends MiracleASTreeExpression {
    public final OPERATOR operator;
    public final MiracleASTreeExpression expression;

    public MiracleASTreePrefixExpression(OPERATOR operator, MiracleASTreeExpression expression) {
        this.operator = operator;
        this.expression = expression;
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.visit(this);
    }

    public enum OPERATOR {
        NEGATE, REV, POSITIVE, NEGATIVE, INC, DEC;

        @Override
        public String toString() {
            if (this.equals(NEGATE)) {
                return "!";
            } else if (this.equals(REV)) {
                return "~";
            } else if (this.equals(POSITIVE)) {
                return "+";
            } else if (this.equals(NEGATIVE)) {
                return "-";
            } else if (this.equals(INC)) {
                return "++";
            } else {
                return "--";
            }
        }
    }
}
