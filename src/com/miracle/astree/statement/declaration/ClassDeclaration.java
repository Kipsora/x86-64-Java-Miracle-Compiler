package com.miracle.astree.statement.declaration;

import com.miracle.astree.visitor.ASTreeVisitor;
import com.miracle.cstree.SourcePosition;
import com.miracle.symbol.SymbolClassType;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassDeclaration extends Declaration {
    public final List<VariableDeclaration> variableDeclarations;
    public final List<FunctionDeclaration> functionDeclarations;
    public final FunctionDeclaration constructorDeclaration;

    private final Map<String, MemberDeclaration> map;
    private final SymbolClassType symbol;

    public ClassDeclaration(String identifier,
                            List<VariableDeclaration> variableDeclarations,
                            List<FunctionDeclaration> functionDeclarations,
                            FunctionDeclaration constructorDeclaration,
                            SourcePosition startPosition,
                            SourcePosition identifierPosition) {
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
        this.map = Collections.unmodifiableMap(new HashMap<String, MemberDeclaration>() {{
            if (variableDeclarations != null) {
                variableDeclarations.forEach(element -> put(element.identifier, element));
            }
            if (functionDeclarations != null) {
                functionDeclarations.forEach(element -> put(element.identifier, element));
            }
        }});
        this.symbol = new SymbolClassType(identifier);
    }

    public MemberDeclaration get(String name) {
        return map.getOrDefault(name, null);
    }

    @Override
    public void accept(ASTreeVisitor ASTreeVisitor) {
        ASTreeVisitor.visit(this);
    }

    public SymbolClassType getSymbol() {
        return symbol;
    }
}
