package com.miracle.astree.visitor;

import com.miracle.astree.ASTree;
import com.miracle.astree.statement.declaration.ClassDeclaration;
import com.miracle.exception.ExceptionContainer;
import com.miracle.symbol.SymbolTable;

public class ClassFetcher extends BaseVisitor {
    private final ExceptionContainer exceptionContainer;
    private SymbolTable symbolTable;

    public ClassFetcher(ExceptionContainer exceptionContainer) {
        this.exceptionContainer = exceptionContainer;
    }

    @Override
    public void visit(ASTree astree) {
        astree.declarations.forEach((element) -> element.accept(this));
    }

    @Override
    public void visit(ClassDeclaration classDeclaration) {
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
