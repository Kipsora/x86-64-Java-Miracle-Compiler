package com.miracle.astree.declaration;

import com.miracle.astree.type.MiracleASTreeBaseType;
import com.miracle.astree.type.MiracleASTreeType;
import com.miracle.astree.visitor.MiracleASTreeVisitor;

import java.util.Collections;
import java.util.List;

public class MiracleASTreeClassDeclaration extends MiracleASTreeDeclaration {
    private final MiracleASTreeBaseType type;
    public final List<MiracleASTreeFunctionDeclaration> functionDeclarations;
    public final List<MiracleASTreeVariableDeclaration> variableDeclarations;

    public MiracleASTreeClassDeclaration(String identifier,
                                         List<MiracleASTreeFunctionDeclaration> functionDeclarations,
                                         List<MiracleASTreeVariableDeclaration> variableDeclarations) {
        super(identifier);
        this.functionDeclarations = Collections.unmodifiableList(functionDeclarations);
        this.variableDeclarations = Collections.unmodifiableList(variableDeclarations);
        this.type = new MiracleASTreeBaseType(identifier);
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
