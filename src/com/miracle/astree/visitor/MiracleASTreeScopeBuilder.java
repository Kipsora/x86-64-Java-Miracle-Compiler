package com.miracle.astree.visitor;

import com.miracle.astree.MiracleASTree;
import com.miracle.astree.base.MiracleASTreeTypeNode;
import com.miracle.astree.statement.*;
import com.miracle.astree.statement.declaration.MiracleASTreeClassDeclaration;
import com.miracle.astree.statement.declaration.MiracleASTreeFunctionDeclaration;
import com.miracle.astree.statement.declaration.MiracleASTreeVariableDeclaration;
import com.miracle.astree.statement.expression.*;
import com.miracle.astree.statement.expression.constant.MiracleASTreeBooleanConstant;
import com.miracle.astree.statement.expression.constant.MiracleASTreeIntegerConstant;
import com.miracle.astree.statement.expression.constant.MiracleASTreeNullConstant;
import com.miracle.astree.statement.expression.constant.MiracleASTreeStringConstant;
import com.miracle.symbol.MiracleSymbolTable;

public class MiracleASTreeScopeBuilder implements MiracleASTreeVisitor {
    private MiracleSymbolTable scope;

    @Override
    public void visit(MiracleASTreeFunctionDeclaration functionDeclaration) {
        functionDeclaration.setScope(scope);
        functionDeclaration.returnType.accept(this);
        scope = new MiracleSymbolTable(scope);
        functionDeclaration.parameters.forEach(element -> element.accept(this));
        functionDeclaration.body.forEach(element -> element.accept(this));
        scope = scope.getParentSymbolTable();
    }

    @Override
    public void visit(MiracleASTreeClassDeclaration classDeclaration) {
        classDeclaration.setScope(scope);
        scope = new MiracleSymbolTable(scope);
        classDeclaration.functionDeclarations.forEach(element -> element.accept(this));
        classDeclaration.variableDeclarations.forEach(element -> element.accept(this));
        scope = scope.getParentSymbolTable();
    }

    @Override
    public void visit(MiracleASTree astree) {
        astree.setScope(scope = new MiracleSymbolTable(null));
        astree.declarations.forEach(element -> element.accept(this));
    }

    @Override
    public void visit(MiracleASTreeVariableDeclaration variableDeclaration) {
        variableDeclaration.setScope(scope);
        variableDeclaration.typenode.accept(this);
    }

    @Override
    public void visit(MiracleASTreeBlock block) {
        block.setScope(scope);
        scope = new MiracleSymbolTable(scope);
        block.statements.forEach(element -> element.accept(this));
        scope = scope.getParentSymbolTable();
    }

    @Override
    public void visit(MiracleASTreeSelection selection) {
        selection.setScope(scope);
        if (selection.branchTrue != null) {
            if (selection.branchTrue instanceof MiracleASTreeBlock) {
                selection.branchTrue.accept(this);
            } else {
                scope = new MiracleSymbolTable(scope);
                selection.branchTrue.accept(this);
                scope = scope.getParentSymbolTable();
            }
        }
        if (selection.branchFalse != null) {
            if (selection.branchFalse instanceof MiracleASTreeBlock) {
                selection.branchFalse.accept(this);
            } else {
                scope = new MiracleSymbolTable(scope);
                selection.branchFalse.accept(this);
                scope = scope.getParentSymbolTable();
            }
        }
    }

    @Override
    public void visit(MiracleASTreeIteration iteration) {
        iteration.setScope(scope);
        if (iteration.body != null) {
            if (iteration.body instanceof MiracleASTreeBlock) {
                iteration.body.accept(this);
            } else {
                scope = new MiracleSymbolTable(scope);
                iteration.body.accept(this);
                scope = scope.getParentSymbolTable();
            }
        }
    }

    @Override
    public void visit(MiracleASTreeBreak breakLiteral) {
        breakLiteral.setScope(scope);
    }

    @Override
    public void visit(MiracleASTreeContinue continueLiteral) {
        continueLiteral.setScope(scope);
    }

    @Override
    public void visit(MiracleASTreeReturn returnLiteral) {
        returnLiteral.setScope(scope);
        returnLiteral.expression.accept(this);
    }

    @Override
    public void visit(MiracleASTreeVariable variable) {
        variable.setScope(scope);
    }

    @Override
    public void visit(MiracleASTreeCall call) {
        call.setScope(scope);
        call.function.accept(this);
        call.parameters.forEach(element -> element.accept(this));
    }

    @Override
    public void visit(MiracleASTreeSubscript subscript) {
        subscript.setScope(scope);
        subscript.base.accept(this);
        subscript.coordinate.accept(this);
    }

    @Override
    public void visit(MiracleASTreeBinaryExpression binaryExpression) {
        binaryExpression.setScope(scope);
        binaryExpression.left.accept(this);
        binaryExpression.right.accept(this);
    }

    @Override
    public void visit(MiracleASTreePrefixExpression prefixExpression) {
        prefixExpression.setScope(scope);
        prefixExpression.expression.accept(this);
    }

    @Override
    public void visit(MiracleASTreeSuffixExpression suffixExpression) {
        suffixExpression.setScope(scope);
        suffixExpression.expression.accept(this);
    }

    @Override
    public void visit(MiracleASTreeNew newNode) {
        newNode.setScope(scope);
        newNode.variableType.accept(this);
        newNode.expressions.forEach(element -> element.accept(this));
    }

    @Override
    public void visit(MiracleASTreeStringConstant stringConstant) {
        stringConstant.setScope(scope);
    }

    @Override
    public void visit(MiracleASTreeIntegerConstant integerConstant) {
        integerConstant.setScope(scope);
    }

    @Override
    public void visit(MiracleASTreeBooleanConstant booleanConstant) {
        booleanConstant.setScope(scope);
    }

    @Override
    public void visit(MiracleASTreeNullConstant nullConstant) {
        nullConstant.setScope(scope);
    }

    @Override
    public void visit(MiracleASTreeThis thisNode) {
        thisNode.setScope(scope);
    }

    @Override
    public void visit(MiracleASTreeField field) {
        field.setScope(scope);
        field.expression.accept(this);
    }

    @Override
    public void visit(MiracleASTreeTypeNode typeNode) {
        typeNode.setScope(scope);
    }
}
