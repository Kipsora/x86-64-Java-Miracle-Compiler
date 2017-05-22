package com.miracle.astree.statement.declaration;

import com.miracle.astree.visitor.MiracleASTreeVisitor;
import com.miracle.cstree.MiracleSourcePosition;
import com.miracle.symbol.MiracleSymbolClassType;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MiracleASTreeClassDeclaration extends MiracleASTreeDeclaration {
    public final List<MiracleASTreeVariableDeclaration> variableDeclarations;
    public final List<MiracleASTreeFunctionDeclaration> functionDeclarations;
    public final MiracleASTreeFunctionDeclaration constructorDeclaration;

    private final Map<String, MiracleASTreeMemberDeclaration> map;
    private final MiracleSymbolClassType symbol;

    public MiracleASTreeClassDeclaration(String identifier,
                                         List<MiracleASTreeVariableDeclaration> variableDeclarations,
                                         List<MiracleASTreeFunctionDeclaration> functionDeclarations,
                                         MiracleASTreeFunctionDeclaration constructorDeclaration,
                                         MiracleSourcePosition startPosition,
                                         MiracleSourcePosition identifierPosition) {
        super(identifier, startPosition, identifierPosition);
        this.constructorDeclaration = constructorDeclaration;
        if (variableDeclarations != null) {
            this.variableDeclarations = Collections.unmodifiableList(variableDeclarations);
        } else {
            this.variableDeclarations = null;
        }
        if (functionDeclarations != null) {
            this.functionDeclarations = Collections.unmodifiableList(functionDeclarations);
        } else {
            this.functionDeclarations = null;
        }
        this.map = Collections.unmodifiableMap(new HashMap<String, MiracleASTreeMemberDeclaration>() {{
            if (variableDeclarations != null) {
                variableDeclarations.forEach(element -> put(element.identifier, element));
            }
            if (functionDeclarations != null) {
                functionDeclarations.forEach(element -> put(element.identifier, element));
            }
        }});
        this.symbol = new MiracleSymbolClassType(identifier);
    }

    public MiracleASTreeMemberDeclaration get(String name) {
        return map.getOrDefault(name, null);
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.visit(this);
    }

    public MiracleSymbolClassType getSymbol() {
        return symbol;
    }
}
