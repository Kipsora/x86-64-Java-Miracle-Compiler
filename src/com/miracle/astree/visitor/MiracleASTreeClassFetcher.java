package com.miracle.astree.visitor;

import com.miracle.astree.MiracleASTree;
import com.miracle.astree.statement.declaration.MiracleASTreeClassDeclaration;
import com.miracle.exception.MiracleExceptionContainer;
import com.miracle.symbol.MiracleSymbolTable;

public class MiracleASTreeClassFetcher extends MiracleASTreeBaseVisitor {
    private final MiracleExceptionContainer exceptionContainer;
    private MiracleSymbolTable symbolTable;
    public MiracleASTreeClassFetcher(MiracleExceptionContainer exceptionContainer, MiracleSymbolTable symbolTable) {
        this.exceptionContainer = exceptionContainer;
        this.symbolTable = symbolTable;
    }

    @Override
    public void visit(MiracleASTree miracleASTree) {
        miracleASTree.declarations.forEach((element) -> element.accept(this));
    }

    @Override
    public void visit(MiracleASTreeClassDeclaration miracleASTreeClassDeclaration) {
        if (!symbolTable.put(miracleASTreeClassDeclaration.identifier, miracleASTreeClassDeclaration)) {
            this.exceptionContainer.add("duplicate declarations of identifier \"" + miracleASTreeClassDeclaration.identifier + "\"");
        }
    }
}
