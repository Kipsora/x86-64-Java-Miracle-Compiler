package com.miracle.astree.visitor;

import com.miracle.astree.MiracleASTree;
import com.miracle.astree.base.MiracleASTreeTypeNode;
import com.miracle.astree.statement.declaration.MiracleASTreeClassDeclaration;
import com.miracle.astree.statement.declaration.MiracleASTreeFunctionDeclaration;
import com.miracle.astree.statement.declaration.MiracleASTreeVariableDeclaration;
import com.miracle.exception.MiracleExceptionContainer;
import com.miracle.symbol.*;

public class MiracleASTreeMemberFetcher extends MiracleASTreeBaseVisitor {
    private final MiracleExceptionContainer exceptionContainer;

    public MiracleASTreeMemberFetcher(MiracleExceptionContainer exceptionContainer) {
        this.exceptionContainer = exceptionContainer;
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
        classDeclaration.functionDeclarations.forEach(element -> {
            element.accept(this);
            classDeclaration.getSymbol().addMethod(element.identifier, element.getSymbol());
        });
        classDeclaration.variableDeclarations.forEach(element -> {
            element.accept(this);
            classDeclaration.getSymbol().addVariable(element.identifier, element.typenode.getType());
        });

        if (classDeclaration.constructorDeclaration != null) {
            classDeclaration.constructorDeclaration.accept(this);
            MiracleSymbolVariableType type = classDeclaration.constructorDeclaration.returnType.getType();
            if (!(type instanceof MiracleSymbolClassType)) {
                exceptionContainer.add("invalid constructor declaration of class `" + classDeclaration.identifier + "`",
                        classDeclaration.constructorDeclaration.returnType.startPosition);
            } else if (!type.isSameType(classDeclaration.getSymbol())) {
                exceptionContainer.add("invalid constructor declaration of class `" + classDeclaration.identifier + "`",
                        classDeclaration.constructorDeclaration.returnType.startPosition);
            }
        }
    }

    @Override
    public void visit(MiracleASTreeVariableDeclaration variableDeclaration) {
        variableDeclaration.typenode.accept(this);
        if (!variableDeclaration.getScope().put(variableDeclaration)) {
            this.exceptionContainer.add("duplicate declarations of identifier \""
                            + variableDeclaration.identifier + "\"",
                    variableDeclaration.identifierPosition
            );
        }
    }

    @Override
    public void visit(MiracleASTreeFunctionDeclaration functionDeclaration) {
        functionDeclaration.returnType.accept(this);
        functionDeclaration.parameters.forEach(element -> {
            element.typenode.accept(this);
            functionDeclaration.getSymbol().addParameter(element.identifier, element.typenode.getType());
        });
        if (functionDeclaration.identifier != null) {
            if (!functionDeclaration.getScope().put(functionDeclaration)) {
                exceptionContainer.add("duplicate declarations of identifier \""
                                + functionDeclaration.identifier + "\"",
                        functionDeclaration.identifierPosition
                );
            }
        }
        if (functionDeclaration.returnType.getType() != null) {
            functionDeclaration.getSymbol().setReturnType(functionDeclaration.returnType.getType());
        }
    }

    @Override
    public void visit(MiracleASTreeTypeNode typeNode) {
        MiracleSymbol result = typeNode.getScope().get(typeNode.typename);
        if (result == null) {
            exceptionContainer.add("cannot find identifier \"" + typeNode.typename + "\"",
                    typeNode.startPosition);
        } else if (result instanceof MiracleSymbolPrimitiveType) {
            if (typeNode.dimension == 0) {
                typeNode.setType((MiracleSymbolVariableType) result);
            } else {
                typeNode.setType(new MiracleSymbolArrayType(
                        (MiracleSymbolBaseType) result,
                        typeNode.dimension
                ));
            }
        } else if (result instanceof MiracleASTreeClassDeclaration) {
            if (typeNode.dimension == 0) {
                typeNode.setType(((MiracleASTreeClassDeclaration) result).getSymbol());
            } else {
                typeNode.setType(new MiracleSymbolArrayType(
                        ((MiracleASTreeClassDeclaration) result).getSymbol(),
                        typeNode.dimension
                ));
            }
        } else {
            exceptionContainer.add("cannot find class `" + typeNode.typename + "`",
                    typeNode.startPosition);
        }
    }
}
