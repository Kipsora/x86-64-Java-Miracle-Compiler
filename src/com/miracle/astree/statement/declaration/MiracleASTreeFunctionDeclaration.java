package com.miracle.astree.statement.declaration;

import com.miracle.astree.base.MiracleASTreeTypeNode;
import com.miracle.astree.statement.MiracleASTreeStatement;
import com.miracle.astree.visitor.MiracleASTreeVisitor;
import com.miracle.cstree.MiracleSourcePosition;
import com.miracle.symbol.MiracleSymbolTable;
import com.miracle.symbol.type.MiracleFunctionType;
import com.miracle.symbol.type.MiracleType;
import com.miracle.symbol.type.MiracleVariableType;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class MiracleASTreeFunctionDeclaration extends MiracleASTreeMemberDeclaration {
    public final MiracleASTreeTypeNode returnType;
    public final List<MiracleASTreeVariableDeclaration> parameters;
    public final List<MiracleASTreeStatement> body;
    private final MiracleFunctionType type;
    private MiracleSymbolTable scope;

    public MiracleASTreeFunctionDeclaration(String identifier,
                                            MiracleASTreeTypeNode returnType,
                                            List<MiracleASTreeVariableDeclaration> parameters) {
        super(identifier, null, null);
        this.returnType = returnType;
        this.parameters = Collections.unmodifiableList(parameters);
        this.body = null;
        List<MiracleVariableType> argtype = new LinkedList<>();
        for (int i = 0, parametersSize = parameters.size(); i < parametersSize; i++) {
            MiracleASTreeVariableDeclaration element = parameters.get(i);
            argtype.add((MiracleVariableType) element.getType());
        }
        this.type = new MiracleFunctionType(returnType.type, argtype);
    }

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
        List<MiracleVariableType> argtype = new LinkedList<>();
        for (int i = 0, parametersSize = parameters.size(); i < parametersSize; i++) {
            MiracleASTreeVariableDeclaration element = parameters.get(i);
            argtype.add((MiracleVariableType) element.getType());
        }
        this.type = new MiracleFunctionType(returnType.type, argtype);
    }

    public MiracleSymbolTable getScope() {
        return scope;
    }

    public void setScope(MiracleSymbolTable scope) {
        this.scope = scope;
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public MiracleType getType() {
        return type;
    }
}
