package com.miracle.astree.visitor;

import com.miracle.astree.ASTree;
import com.miracle.astree.base.TypeNode;
import com.miracle.astree.statement.declaration.ClassDeclaration;
import com.miracle.astree.statement.declaration.FunctionDeclaration;
import com.miracle.astree.statement.declaration.VariableDeclaration;
import com.miracle.exception.ExceptionContainer;
import com.miracle.symbol.*;

public class MemberFetcher extends BaseASTreeVisitor {
    private final ExceptionContainer exceptionContainer;
    private SymbolClassType classType;

    public MemberFetcher(ExceptionContainer exceptionContainer) {
        this.exceptionContainer = exceptionContainer;
    }

    @Override
    public void visit(ASTree astree) {
        astree.declarations.forEach((element) -> {
            if (element instanceof ClassDeclaration) {
                element.accept(this);
            }
            if (element instanceof FunctionDeclaration) {
                element.accept(this);
            }
        });
    }

    @Override
    public void visit(ClassDeclaration classDeclaration) {
        classDeclaration.functionDeclarations.forEach(element -> {
            element.setMemberFrom(classDeclaration);
            element.accept(this);
            classDeclaration.getSymbol().addMethod(element.identifier, element.getSymbol());
        });
        classDeclaration.variableDeclarations.forEach(element -> {
            element.setMemberFrom(classDeclaration);
            element.accept(this);
            classDeclaration.getSymbol().addVariable(element.identifier, element.typenode.getType());
        });

        if (classDeclaration.constructorDeclaration != null) {
            classDeclaration.constructorDeclaration.setMemberFrom(classDeclaration);
            classDeclaration.getSymbol().addMethod("", classDeclaration.constructorDeclaration.getSymbol());
            if (!classDeclaration.identifier.equals(classDeclaration.constructorDeclaration.identifier)) {
                exceptionContainer.add("invalid constructor declaration of class `" + classDeclaration.identifier + "`",
                        classDeclaration.constructorDeclaration.identifierPosition);
            }
        }
    }

    @Override
    public void visit(VariableDeclaration variableDeclaration) {
        variableDeclaration.typenode.accept(this);
        if (!variableDeclaration.getScope().put(variableDeclaration)) {
            this.exceptionContainer.add("duplicate declarations of identifier \""
                            + variableDeclaration.identifier + "\"",
                    variableDeclaration.identifierPosition
            );
        }
    }

    @Override
    public void visit(FunctionDeclaration functionDeclaration) {
        functionDeclaration.returnType.accept(this);
        functionDeclaration.parameters.forEach(element -> {
            element.typenode.accept(this);
            functionDeclaration.getSymbol().addParameter(element.identifier, element.typenode.getType());
        });
        if (!functionDeclaration.getScope().put(functionDeclaration)) {
            exceptionContainer.add("duplicate declarations of identifier \""
                            + functionDeclaration.identifier + "\"",
                    functionDeclaration.identifierPosition
            );
        }
        if (functionDeclaration.returnType.getType() != null) {
            functionDeclaration.getSymbol().setReturnType(functionDeclaration.returnType.getType());
        }
    }

    @Override
    public void visit(TypeNode typeNode) {
        Symbol result = typeNode.getScope().resolve(typeNode.typename);
        if (result == null) {
            exceptionContainer.add("cannot find identifier \"" + typeNode.typename + "\"",
                    typeNode.startPosition);
        } else if (result instanceof SymbolPrimitiveType) {
            if (typeNode.dimension == 0) {
                typeNode.setType((SymbolVariableType) result);
            } else {
                typeNode.setType(new SymbolArrayType(
                        (SymbolBaseType) result,
                        typeNode.dimension
                ));
            }
        } else if (result instanceof ClassDeclaration) {
            if (typeNode.dimension == 0) {
                typeNode.setType(((ClassDeclaration) result).getSymbol());
            } else {
                typeNode.setType(new SymbolArrayType(
                        ((ClassDeclaration) result).getSymbol(),
                        typeNode.dimension
                ));
            }
        } else {
            exceptionContainer.add("cannot find class `" + typeNode.typename + "`",
                    typeNode.startPosition);
        }
    }
}
