package com.miracle.astree.statement.declaration;

import com.miracle.astree.base.TypeNode;
import com.miracle.astree.statement.expression.Expression;
import com.miracle.astree.visitor.Visitor;
import com.miracle.cstree.SourcePosition;
import com.miracle.intermediate.number.Register;

public class VariableDeclaration extends MemberDeclaration {
    public final Expression expression;
    public final TypeNode typenode;

    private Register address;

    public VariableDeclaration(String identifier,
                               TypeNode typenode,
                               Expression expression,
                               SourcePosition startPosition,
                               SourcePosition identifierPosition) {
        super(identifier, startPosition, identifierPosition);
        this.typenode = typenode;
        this.expression = expression;
    }

    public Register getAddress() {
        return address;
    }

    public void setAddress(Register address) {
        this.address = address;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
