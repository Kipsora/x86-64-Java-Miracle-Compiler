package com.miracle.astree.statement.expression;

import com.miracle.astree.visitor.Visitor;
import com.miracle.cstree.SourcePosition;

public class Field extends Expression {
    public final Expression expression;
    public final SourcePosition identifierPosition;
    public final SourcePosition operatorPosition;
    public final String identifier;

    public Field(Expression expression,
                 String identifier,
                 SourcePosition startPosition,
                 SourcePosition operatorPosition,
                 SourcePosition identifierPosition) {
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
