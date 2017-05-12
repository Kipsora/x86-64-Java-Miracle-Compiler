package com.miracle.astree.expression;

import com.miracle.astree.visitor.MiracleASTreeVisitor;

public class MiracleASTreeSuffixExpression extends MiracleASTreeExpression {
    public final OPERATOR operator;
    public final MiracleASTreeExpression expression;

    public MiracleASTreeSuffixExpression(MiracleASTreeExpression expression, OPERATOR operator) {
        this.operator = operator;
        this.expression = expression;
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.visit(this);
    }

    public enum OPERATOR {
        INC, DEC;

        @Override
        public String toString() {
            if (this.equals(INC)) {
                return "++";
            } else {
                return "--";
            }
        }
    }
}
