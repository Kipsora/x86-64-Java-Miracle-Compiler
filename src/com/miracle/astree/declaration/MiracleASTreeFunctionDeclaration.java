package com.miracle.astree.declaration;

import com.miracle.astree.statement.MiracleASTreeStatement;
import com.miracle.astree.type.MiracleASTreeFunctionType;
import com.miracle.astree.visitor.MiracleASTreeVisitor;
import com.miracle.astree.type.MiracleASTreeType;

import java.util.Collections;
import java.util.List;

public class MiracleASTreeFunctionDeclaration extends MiracleASTreeDeclaration{
    private final MiracleASTreeFunctionType type;
    public final MiracleASTreeType returnType;
    public final List<MiracleASTreeVariableDeclaration> parameters;
    public final List<MiracleASTreeStatement> body;

    public MiracleASTreeFunctionDeclaration(String identifier,
                                            MiracleASTreeType returnType,
                                            List<MiracleASTreeVariableDeclaration> parameters,
                                            List<MiracleASTreeStatement> body) {
        super(identifier);
        this.returnType = returnType;
        this.parameters = Collections.unmodifiableList(parameters);
        this.body = Collections.unmodifiableList(body);
        this.type = new MiracleASTreeFunctionType(returnType, parameters);
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
