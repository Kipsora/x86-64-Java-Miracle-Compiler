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

public interface MiracleASTreeVisitor {
    void visit(MiracleASTreeFunctionDeclaration functionDeclaration);

    void visit(MiracleASTreeClassDeclaration classDeclaration);

    void visit(MiracleASTree astree);

    void visit(MiracleASTreeVariableDeclaration variableDeclaration);

    void visit(MiracleASTreeBlock block);

    void visit(MiracleASTreeSelection selection);

    void visit(MiracleASTreeIteration iteration);

    void visit(MiracleASTreeBreak breakLiteral);

    void visit(MiracleASTreeContinue continueLiteral);

    void visit(MiracleASTreeReturn returnLiteral);

    void visit(MiracleASTreeVariable variable);

    void visit(MiracleASTreeCall call);

    void visit(MiracleASTreeSubscript subscript);

    void visit(MiracleASTreeBinaryExpression binaryExpression);

    void visit(MiracleASTreePrefixExpression prefixExpression);

    void visit(MiracleASTreeSuffixExpression suffixExpression);

    void visit(MiracleASTreeNew newNode);

    void visit(MiracleASTreeStringConstant stringConstant);

    void visit(MiracleASTreeIntegerConstant integerConstant);

    void visit(MiracleASTreeBooleanConstant booleanConstant);

    void visit(MiracleASTreeNullConstant nullConstant);

    void visit(MiracleASTreeThis thisNode);

    void visit(MiracleASTreeField field);

    void visit(MiracleASTreeTypeNode typeNode);
}
