package com.miracle.astree.visitor;

import com.miracle.astree.MiracleASTree;
import com.miracle.astree.statement.MiracleASTreeBlock;
import com.miracle.astree.statement.MiracleASTreeIteration;
import com.miracle.astree.statement.MiracleASTreeSelection;
import com.miracle.astree.statement.declaration.MiracleASTreeClassDeclaration;
import com.miracle.astree.statement.declaration.MiracleASTreeFunctionDeclaration;
import com.miracle.symbol.MiracleSymbolTable;

public class MiracleASTreeScopeBuilder extends MiracleASTreeBaseVisitor {
    private MiracleSymbolTable symbolTable;

    public MiracleASTreeScopeBuilder(MiracleSymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }

    @Override
    public void visit(MiracleASTree miracleASTree) {
        miracleASTree.declarations.forEach(element -> element.accept(this));
    }

    @Override
    public void visit(MiracleASTreeClassDeclaration miracleASTreeClassDeclaration) {
        miracleASTreeClassDeclaration.scope = symbolTable = new MiracleSymbolTable(symbolTable);
        miracleASTreeClassDeclaration.functionDeclarations.forEach(element -> element.accept(this));
        symbolTable = symbolTable.getParentSymbolTable();
    }

    @Override
    public void visit(MiracleASTreeFunctionDeclaration miracleASTreeFunctionDeclaration) {
        miracleASTreeFunctionDeclaration.scope = symbolTable = new MiracleSymbolTable(symbolTable);
        symbolTable.setFunctionDeclaration(miracleASTreeFunctionDeclaration);
        if (miracleASTreeFunctionDeclaration.body != null) {
            miracleASTreeFunctionDeclaration.body.forEach(element -> element.accept(this));
        }
        symbolTable = symbolTable.getParentSymbolTable();
    }

    @Override
    public void visit(MiracleASTreeSelection miracleASTreeSelection) {
        miracleASTreeSelection.branchTrue.accept(this);
        if (miracleASTreeSelection.branchFalse != null) {
            miracleASTreeSelection.branchFalse.accept(this);
        }
    }

    @Override
    public void visit(MiracleASTreeIteration miracleASTreeIteration) {
        miracleASTreeIteration.body.accept(this);
    }

    @Override
    public void visit(MiracleASTreeBlock miracleASTreeBlock) {
        miracleASTreeBlock.scope = symbolTable = new MiracleSymbolTable(symbolTable);
        miracleASTreeBlock.statements.forEach(element -> element.accept(this));
        symbolTable = symbolTable.getParentSymbolTable();
    }
}
