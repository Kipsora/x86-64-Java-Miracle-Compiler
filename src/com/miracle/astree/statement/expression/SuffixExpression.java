package com.miracle.astree.statement.expression;

import com.miracle.astree.visitor.Visitor;
import com.miracle.cstree.MiracleSourcePosition;

public class SuffixExpression extends Expression {
    public final OPERATOR operator;
    public final Expression expression;
    public final MiracleSourcePosition operatorPosition;

    public SuffixExpression(Expression expression,
                            OPERATOR operator,
                            MiracleSourcePosition startPosition,
                            MiracleSourcePosition operatorPosition) {
        super(startPosition);
        this.operatorPosition = operatorPosition;
        this.operator = operator;
        this.expression = expression;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toPrintableString() {
        return "s" + operator.toString() + " " + expression.toPrintableString();
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
