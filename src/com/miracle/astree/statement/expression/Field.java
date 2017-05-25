package com.miracle.astree.statement.expression;

import com.miracle.astree.visitor.Visitor;
import com.miracle.cstree.MiracleSourcePosition;

public class Field extends Expression {
    public final Expression expression;
    public final MiracleSourcePosition identifierPosition;
    public final MiracleSourcePosition operatorPosition;
    public final String identifier;

    public Field(Expression expression,
                 String identifier,
                 MiracleSourcePosition startPosition,
                 MiracleSourcePosition operatorPosition,
                 MiracleSourcePosition identifierPosition) {
        super(startPosition);
        this.expression = expression;
        this.identifier = identifier;
        this.operatorPosition = operatorPosition;
        this.identifierPosition = identifierPosition;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toPrintableString() {
        return this.expression.toPrintableString() + "." + this.identifier;
    }
}
