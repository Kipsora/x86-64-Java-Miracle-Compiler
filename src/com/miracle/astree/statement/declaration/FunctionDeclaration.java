package com.miracle.astree.statement.declaration;

import com.miracle.astree.base.TypeNode;
import com.miracle.astree.statement.Statement;
import com.miracle.astree.visitor.Visitor;
import com.miracle.cstree.SourcePosition;
import com.miracle.symbol.SymbolFunctionType;

import java.util.Collections;
import java.util.List;

public class FunctionDeclaration extends MemberDeclaration {
    public final TypeNode returnType;
    public final List<VariableDeclaration> parameters;
    public final List<Statement> body;
    private final SymbolFunctionType symbol;

    public FunctionDeclaration(String identifier,
                               TypeNode returnType,
                               List<VariableDeclaration> parameters,
                               List<Statement> body,
                               SourcePosition startPosition,
                               SourcePosition identifierPosition) {
        super(identifier, startPosition, identifierPosition);
        this.returnType = returnType;
        this.parameters = Collections.unmodifiableList(parameters);
        if (body != null) {
            this.body = Collections.unmodifiableList(body);
        } else {
            this.body = null;
        }
        this.symbol = new SymbolFunctionType();
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public SymbolFunctionType getSymbol() {
        return symbol;
    }

    @Override
    public void setMemberFrom(ClassDeclaration memberFrom) {
        super.setMemberFrom(memberFrom);
        this.symbol.setMemberFrom(memberFrom.getSymbol());
    }
}
