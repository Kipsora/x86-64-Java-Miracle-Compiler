package com.miracle.astree.statement.declaration;

import com.miracle.astree.type.MiracleASTreeBaseType;
import com.miracle.astree.type.MiracleASTreeType;
import com.miracle.astree.visitor.MiracleASTreeVisitor;
import com.miracle.symbol.MiracleSymbolTable;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MiracleASTreeClassDeclaration extends MiracleASTreeDeclaration {
    public MiracleSymbolTable scope;
    public final MiracleASTreeBaseType type;
    public final List<MiracleASTreeVariableDeclaration> variableDeclarations;
    public final List<MiracleASTreeFunctionDeclaration> functionDeclarations;
    private final Map<String, MiracleASTreeMemberDeclaration> map;

    public MiracleASTreeClassDeclaration(String identifier,
                                         List<MiracleASTreeVariableDeclaration> variableDeclarations,
                                         List<MiracleASTreeFunctionDeclaration> functionDeclarations) {
        super(identifier);
        this.variableDeclarations = Collections.unmodifiableList(variableDeclarations);
        this.functionDeclarations = Collections.unmodifiableList(functionDeclarations);
        this.type = new MiracleASTreeBaseType(identifier);
        this.map = Collections.unmodifiableMap(new HashMap<String, MiracleASTreeMemberDeclaration>() {
            private static final long serialVersionUID = 4184390405285077379L;
            {
                variableDeclarations.forEach(element -> put(element.identifier, element));
                functionDeclarations.forEach(element -> put(element.identifier, element));
            }
        });
    }

    public MiracleASTreeMemberDeclaration get(String name) {
        return map.getOrDefault(name, null);
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
