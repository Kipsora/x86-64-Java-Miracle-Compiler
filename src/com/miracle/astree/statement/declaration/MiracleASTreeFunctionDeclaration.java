package com.miracle.astree.statement.declaration;

import com.miracle.astree.base.MiracleASTreeTypeNode;
import com.miracle.astree.statement.MiracleASTreeStatement;
import com.miracle.astree.visitor.MiracleASTreeVisitor;
import com.miracle.cstree.MiracleSourcePosition;
import com.miracle.symbol.MiracleSymbolFunctionType;

import java.util.Collections;
import java.util.List;

public class MiracleASTreeFunctionDeclaration extends MiracleASTreeMemberDeclaration {
    public final MiracleASTreeTypeNode returnType;
    public final List<MiracleASTreeVariableDeclaration> parameters;
    public final List<MiracleASTreeStatement> body;
    private final MiracleSymbolFunctionType symbol;

    public MiracleASTreeFunctionDeclaration(String identifier,
                                            MiracleASTreeTypeNode returnType,
                                            List<MiracleASTreeVariableDeclaration> parameters,
                                            List<MiracleASTreeStatement> body,
                                            MiracleSourcePosition startPosition,
                                            MiracleSourcePosition identifierPosition) {
        super(identifier, startPosition, identifierPosition);
        this.returnType = returnType;
        this.parameters = Collections.unmodifiableList(parameters);
        if (body != null) {
            this.body = Collections.unmodifiableList(body);
        } else {
            this.body = null;
        }
        this.symbol = new MiracleSymbolFunctionType();
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.visit(this);
    }

    public MiracleSymbolFunctionType getSymbol() {
        return symbol;
    }

    @Override
    public void setMemberFrom(MiracleASTreeClassDeclaration memberFrom) {

        super.setMemberFrom(memberFrom);
        this.symbol.setMemberFrom(memberFrom.getSymbol());
    }
}
