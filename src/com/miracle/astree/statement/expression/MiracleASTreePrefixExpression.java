package com.miracle.astree.statement.expression;

import com.miracle.astree.visitor.MiracleASTreeVisitor;
import com.miracle.cstree.MiracleSourcePosition;

public class MiracleASTreePrefixExpression extends MiracleASTreeExpression {
    public final OPERATOR operator;
    public final MiracleSourcePosition operatorPosition;
    public final MiracleASTreeExpression expression;

    public MiracleASTreePrefixExpression(OPERATOR operator,
                                         MiracleASTreeExpression expression,
                                         MiracleSourcePosition startPosition,
                                         MiracleSourcePosition operatorPosition) {
        super(startPosition);
        this.operator = operator;
        this.expression = expression;
        this.operatorPosition = operatorPosition;
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toPrintableString() {
        return "p" + operator.toString() + " " + expression.toPrintableString();
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
