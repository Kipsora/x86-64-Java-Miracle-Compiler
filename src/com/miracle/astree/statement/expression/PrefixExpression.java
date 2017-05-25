package com.miracle.astree.statement.expression;

import com.miracle.astree.visitor.Visitor;
import com.miracle.cstree.SourcePosition;

public class PrefixExpression extends Expression {
    public final OPERATOR operator;
    public final SourcePosition operatorPosition;
    public final Expression expression;

    public PrefixExpression(OPERATOR operator,
                            Expression expression,
                            SourcePosition startPosition,
                            SourcePosition operatorPosition) {
        super(startPosition);
        this.operator = operator;
        this.expression = expression;
        this.operatorPosition = operatorPosition;
    }

    @Override
    public void accept(Visitor visitor) {
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
