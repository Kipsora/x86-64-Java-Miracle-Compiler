package com.miracle.astree.visitor;

import com.miracle.astree.MiracleASTree;
import com.miracle.astree.declaration.*;
import com.miracle.astree.expression.*;
import com.miracle.astree.statement.*;
import com.miracle.astree.type.MiracleASTreeArrayType;
import com.miracle.astree.type.MiracleASTreeBaseType;
import com.miracle.astree.type.MiracleASTreeFunctionType;
import com.miracle.astree.type.MiracleASTreeVariableType;

public interface MiracleASTreeVisitor {
    void visit(MiracleASTreeFunctionDeclaration miracleASTreeFunctionDeclaration);

    void visit(MiracleASTreeClassDeclaration miracleASTreeClassDeclaration);

    void visit(MiracleASTree miracleASTree);

    void visit(MiracleASTreeVariableDeclaration miracleASTreeVariableDeclaration);

    void visit(MiracleASTreeBlock miracleASTreeBlock);

    void visit(MiracleASTreeSelection miracleASTreeSelection);

    void visit(MiracleASTreeIteration miracleASTreeIteration);

    void visit(MiracleASTreeBreak miracleASTreeBreak);

    void visit(MiracleASTreeContinue miracleASTreeContinue);

    void visit(MiracleASTreeReturn miracleASTreeReturn);

    void visit(MiracleASTreeVariable miracleASTreeVariable);

    void visit(MiracleASTreeCall miracleASTreeCall);

    void visit(MiracleASTreeSubscript miracleASTreeSubscript);

    void visit(MiracleASTreeBinaryExpression miracleASTreeBinaryExpression);

    void visit(MiracleASTreePrefixExpression miracleASTreePrefixExpression);

    void visit(MiracleASTreeSuffixExpression miracleASTreeSuffixExpression);

    void visit(MiracleASTreeNew miracleASTreeNew);

    void visit(MiracleASTreeConstant miracleASTreeConstant);

    void visit(MiracleASTreeFunctionType miracleASTreeFunctionType);

    void visit(MiracleASTreeBaseType miracleASTreeBaseType);

    void visit(MiracleASTreeArrayType miracleASTreeArrayType);
}
