package com.miracle.astree.visitor;

import com.miracle.astree.ASTree;
import com.miracle.astree.base.TypeNode;
import com.miracle.astree.statement.*;
import com.miracle.astree.statement.declaration.ClassDeclaration;
import com.miracle.astree.statement.declaration.FunctionDeclaration;
import com.miracle.astree.statement.declaration.VariableDeclaration;
import com.miracle.astree.statement.expression.*;
import com.miracle.astree.statement.expression.constant.BooleanConstant;
import com.miracle.astree.statement.expression.constant.IntegerConstant;
import com.miracle.astree.statement.expression.constant.NullConstant;
import com.miracle.astree.statement.expression.constant.StringConstant;
import com.miracle.symbol.SymbolTable;

public class ScopeBuilder implements Visitor {
    private SymbolTable scope;

    @Override
    public void visit(FunctionDeclaration functionDeclaration) {
        functionDeclaration.setScope(scope);
        if (functionDeclaration.returnType != null) {
            functionDeclaration.returnType.accept(this);
        }
        scope = new SymbolTable(scope);
        functionDeclaration.parameters.forEach(element -> element.accept(this));
        functionDeclaration.body.forEach(element -> element.accept(this));
        scope = scope.getParentSymbolTable();
    }

    @Override
    public void visit(ClassDeclaration classDeclaration) {
        classDeclaration.setScope(scope);
        scope = new SymbolTable(scope);
        classDeclaration.functionDeclarations.forEach(element -> element.accept(this));
        classDeclaration.variableDeclarations.forEach(element -> element.accept(this));
        if (classDeclaration.constructorDeclaration != null) {
            classDeclaration.constructorDeclaration.accept(this);
        }
        scope = scope.getParentSymbolTable();
    }

    @Override
    public void visit(ASTree astree) {
        astree.setScope(scope = new SymbolTable(null));
        astree.declarations.forEach(element -> element.accept(this));
    }

    @Override
    public void visit(VariableDeclaration variableDeclaration) {
        variableDeclaration.setScope(scope);
        variableDeclaration.typenode.accept(this);
        if (variableDeclaration.expression != null) {
            variableDeclaration.expression.accept(this);
        }
    }

    @Override
    public void visit(Block block) {
        block.setScope(scope);
        scope = new SymbolTable(scope);
        block.statements.forEach(element -> element.accept(this));
        scope = scope.getParentSymbolTable();
    }

    @Override
    public void visit(Selection selection) {
        selection.setScope(scope);
        selection.expression.accept(this);
        if (selection.branchTrue != null) {
            if (selection.branchTrue instanceof Block) {
                selection.branchTrue.accept(this);
            } else {
                scope = new SymbolTable(scope);
                selection.branchTrue.accept(this);
                scope = scope.getParentSymbolTable();
            }
        }
        if (selection.branchFalse != null) {
            if (selection.branchFalse instanceof Block) {
                selection.branchFalse.accept(this);
            } else {
                scope = new SymbolTable(scope);
                selection.branchFalse.accept(this);
                scope = scope.getParentSymbolTable();
            }
        }
    }

    @Override
    public void visit(Iteration iteration) {
        iteration.setScope(scope);
        if (iteration.initializeExpression != null) {
            iteration.initializeExpression.accept(this);
        }
        if (iteration.conditionExpression != null) {
            iteration.conditionExpression.accept(this);
        }
        if (iteration.incrementExpression != null) {
            iteration.incrementExpression.accept(this);
        }
        if (iteration.body != null) {
            if (iteration.body instanceof Block) {
                iteration.body.accept(this);
            } else {
                scope = new SymbolTable(scope);
                iteration.body.accept(this);
                scope = scope.getParentSymbolTable();
            }
        }
    }

    @Override
    public void visit(Break breakLiteral) {
        breakLiteral.setScope(scope);
    }

    @Override
    public void visit(Continue continueLiteral) {
        continueLiteral.setScope(scope);
    }

    @Override
    public void visit(Return returnLiteral) {
        returnLiteral.setScope(scope);
        if (returnLiteral.expression != null) {
            returnLiteral.expression.accept(this);
        }
    }

    @Override
    public void visit(Variable variable) {
        variable.setScope(scope);
    }

    @Override
    public void visit(Call call) {
        call.setScope(scope);
        call.function.accept(this);
        call.parameters.forEach(element -> element.accept(this));
    }

    @Override
    public void visit(Subscript subscript) {
        subscript.setScope(scope);
        subscript.base.accept(this);
        subscript.coordinate.accept(this);
    }

    @Override
    public void visit(BinaryExpression binaryExpression) {
        binaryExpression.setScope(scope);
        binaryExpression.left.accept(this);
        binaryExpression.right.accept(this);
    }

    @Override
    public void visit(PrefixExpression prefixExpression) {
        prefixExpression.setScope(scope);
        prefixExpression.expression.accept(this);
    }

    @Override
    public void visit(SuffixExpression suffixExpression) {
        suffixExpression.setScope(scope);
        suffixExpression.expression.accept(this);
    }

    @Override
    public void visit(New newNode) {
        newNode.setScope(scope);
        newNode.variableType.accept(this);
        newNode.expressions.forEach(element -> {
            if (element != null) element.accept(this);
        });
    }

    @Override
    public void visit(StringConstant stringConstant) {
        stringConstant.setScope(scope);
    }

    @Override
    public void visit(IntegerConstant integerConstant) {
        integerConstant.setScope(scope);
    }

    @Override
    public void visit(BooleanConstant booleanConstant) {
        booleanConstant.setScope(scope);
    }

    @Override
    public void visit(NullConstant nullConstant) {
        nullConstant.setScope(scope);
    }

    @Override
    public void visit(This thisNode) {
        thisNode.setScope(scope);
    }

    @Override
    public void visit(Field field) {
        field.setScope(scope);
        field.expression.accept(this);
    }

    @Override
    public void visit(TypeNode typeNode) {
        typeNode.setScope(scope);
    }
}
