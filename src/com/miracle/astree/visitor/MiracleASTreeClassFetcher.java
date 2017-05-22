package com.miracle.astree.visitor;

import com.miracle.astree.MiracleASTree;
import com.miracle.astree.statement.declaration.MiracleASTreeClassDeclaration;
import com.miracle.exception.MiracleExceptionContainer;
import com.miracle.symbol.MiracleSymbolTable;

public class MiracleASTreeClassFetcher extends MiracleASTreeBaseVisitor {
    private final MiracleExceptionContainer exceptionContainer;
    private MiracleSymbolTable symbolTable;

    public MiracleASTreeClassFetcher(MiracleExceptionContainer exceptionContainer) {
        this.exceptionContainer = exceptionContainer;
    }

    @Override
    public void visit(MiracleASTree astree) {
        astree.declarations.forEach((element) -> element.accept(this));
    }

    @Override
    public void visit(MiracleASTreeClassDeclaration classDeclaration) {
        symbolTable = classDeclaration.getScope();
        if (!symbolTable.put(classDeclaration)) {
            this.exceptionContainer.add("duplicate declarations of identifier \""
                            + classDeclaration.identifier + "\"",
                    classDeclaration.identifierPosition
            );
        }
        classDeclaration.functionDeclarations.forEach(element -> element.accept(this));
        if (classDeclaration.constructorDeclaration != null) {
            classDeclaration.constructorDeclaration.accept(this);
        }
    }
}
