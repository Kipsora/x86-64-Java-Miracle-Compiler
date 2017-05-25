package com.miracle.astree.statement.expression;

import com.miracle.astree.visitor.Visitor;
import com.miracle.cstree.SourcePosition;

public class Variable extends Expression {
    public final String identifier;

    public Variable(String identifier, SourcePosition position) {
        super(position);
        this.identifier = identifier;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toPrintableString() {
        return identifier;
    }
}
