package com.miracle.astree.visitor;

import com.miracle.astree.MiracleASTree;
import com.miracle.astree.statement.declaration.MiracleASTreeClassDeclaration;
import com.miracle.astree.statement.declaration.MiracleASTreeFunctionDeclaration;
import com.miracle.astree.statement.declaration.MiracleASTreeVariableDeclaration;
import com.miracle.exception.MiracleExceptionContainer;
import com.miracle.symbol.MiracleSymbolTable;

public class MiracleASTreeMemberFetcher extends MiracleASTreeBaseVisitor {
    private final MiracleExceptionContainer exceptionContainer;
    private final MiracleSymbolTable globalSymbolTable;
    private MiracleSymbolTable symbolTable;

    public MiracleASTreeMemberFetcher(MiracleExceptionContainer exceptionContainer, MiracleSymbolTable symbolTable) {
        this.exceptionContainer = exceptionContainer;
        this.symbolTable = symbolTable;
        this.globalSymbolTable = symbolTable;
    }

    @Override
    public void visit(MiracleASTree astree) {
        astree.declarations.forEach((element) -> {
            if (element instanceof MiracleASTreeClassDeclaration) {
                element.accept(this);
            }
            if (element instanceof MiracleASTreeFunctionDeclaration) {
                element.accept(this);
            }
        });
    }

    @Override
    public void visit(MiracleASTreeClassDeclaration classDeclaration) {
        symbolTable = classDeclaration.getScope();
        classDeclaration.functionDeclarations.forEach(element -> element.accept(this));
        classDeclaration.variableDeclarations.forEach(element -> element.accept(this));

        if (classDeclaration.constructorDeclaration != null) {
            classDeclaration.constructorDeclaration.accept(this);
            String type = classDeclaration.constructorDeclaration.returnType.type.toClassTypeString();
            if (!type.equals(classDeclaration.identifier)) {
                exceptionContainer.add("invalid constructor declaration of class `" + classDeclaration.identifier + "`",
                        classDeclaration.constructorDeclaration.returnType.startPosition);
            }
        }
        symbolTable = symbolTable.getParentSymbolTable();
    }

    @Override
    public void visit(MiracleASTreeVariableDeclaration variableDeclaration) {
        if (!symbolTable.put(variableDeclaration.identifier, variableDeclaration)) {
            this.exceptionContainer.add("duplicate declarations of identifier \""
                            + variableDeclaration.identifier + "\"",
                    variableDeclaration.identifierPosition
            );
        }
    }

    @Override
    public void visit(MiracleASTreeFunctionDeclaration functionDeclaration) {
        if (functionDeclaration.identifier != null) {
            if (!symbolTable.put(functionDeclaration.identifier, functionDeclaration)) {
                this.exceptionContainer.add("duplicate declarations of identifier \""
                                + functionDeclaration.identifier + "\"",
                        functionDeclaration.identifierPosition
                );
            }
        }
    }
}
