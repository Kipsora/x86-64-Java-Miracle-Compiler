package com.miracle.astree.statement.declaration;

import com.miracle.astree.statement.MiracleASTreeStatement;
import com.miracle.astree.type.MiracleASTreeFunctionType;
import com.miracle.astree.type.MiracleASTreeVariableType;
import com.miracle.astree.visitor.MiracleASTreeVisitor;
import com.miracle.astree.type.MiracleASTreeType;
import com.miracle.symbol.MiracleSymbolTable;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class MiracleASTreeFunctionDeclaration extends MiracleASTreeMemberDeclaration {
    private final MiracleASTreeFunctionType type;
    public final MiracleASTreeVariableType returnType;
    public final List<MiracleASTreeVariableDeclaration> parameters;
    public final List<MiracleASTreeStatement> body;
    public MiracleSymbolTable scope;

    public MiracleASTreeFunctionDeclaration(String identifier,
                                            MiracleASTreeVariableType returnType,
                                            List<MiracleASTreeVariableDeclaration> parameters,
                                            List<MiracleASTreeStatement> body) {
        super(identifier);
        this.returnType = returnType;
        this.parameters = Collections.unmodifiableList(parameters);
        if (body != null) {
            this.body = Collections.unmodifiableList(body);
        } else {
            this.body = null;
        }
        List<MiracleASTreeVariableType> argtype = new LinkedList<>();
        parameters.forEach((element) -> argtype.add((MiracleASTreeVariableType) element.getType()));
        this.type = new MiracleASTreeFunctionType(returnType, argtype);
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public MiracleASTreeType getType() {
        return type;
    }
}
