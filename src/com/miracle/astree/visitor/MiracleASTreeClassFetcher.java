package com.miracle.astree.visitor;

import com.miracle.astree.MiracleASTree;
import com.miracle.astree.statement.MiracleASTreeBlock;
import com.miracle.astree.statement.MiracleASTreeIteration;
import com.miracle.astree.statement.MiracleASTreeSelection;
import com.miracle.astree.statement.declaration.MiracleASTreeClassDeclaration;
import com.miracle.astree.statement.declaration.MiracleASTreeFunctionDeclaration;
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
    public void visit(MiracleASTree astree) {
        astree.declarations.forEach((element) -> element.accept(this));
    }

    @Override
    public void visit(MiracleASTreeClassDeclaration classDeclaration) {
        if (!symbolTable.put(classDeclaration.identifier, classDeclaration)) {
            this.exceptionContainer.add("duplicate declarations of identifier \""
                            + classDeclaration.identifier + "\"",
                    classDeclaration.identifierPosition
            );
        }
        classDeclaration.setScope(symbolTable = new MiracleSymbolTable(symbolTable));
        classDeclaration.functionDeclarations.forEach(element -> element.accept(this));
        if (classDeclaration.constructorDeclaration != null) {
            classDeclaration.constructorDeclaration.accept(this);
        }
        symbolTable = symbolTable.getParentSymbolTable();
    }

    @Override
    public void visit(MiracleASTreeFunctionDeclaration functionDeclaration) {
        functionDeclaration.setScope(symbolTable = new MiracleSymbolTable(symbolTable));
        if (functionDeclaration.body != null) {
            functionDeclaration.body.forEach(element -> element.accept(this));
        }
        symbolTable = symbolTable.getParentSymbolTable();
    }

    @Override
    public void visit(MiracleASTreeSelection selection) {
        if (selection.branchTrue != null) {
            selection.branchTrue.accept(this);
        }
        if (selection.branchFalse != null) {
            selection.branchFalse.accept(this);
        }
    }

    @Override
    public void visit(MiracleASTreeIteration iteration) {
        iteration.body.accept(this);
    }

    @Override
    public void visit(MiracleASTreeBlock block) {
        block.setScope(symbolTable = new MiracleSymbolTable(symbolTable));
        block.statements.forEach(element -> element.accept(this));
        symbolTable = symbolTable.getParentSymbolTable();
    }
}
