package com.miracle.astree.visitor;

import com.miracle.astree.MiracleASTree;
import com.miracle.astree.statement.declaration.MiracleASTreeClassDeclaration;
import com.miracle.astree.statement.declaration.MiracleASTreeFunctionDeclaration;
import com.miracle.astree.statement.declaration.MiracleASTreeVariableDeclaration;
import com.miracle.astree.expression.*;
import com.miracle.astree.statement.*;
import com.miracle.astree.type.MiracleASTreeArrayType;
import com.miracle.astree.type.MiracleASTreeBaseType;
import com.miracle.astree.type.MiracleASTreeFunctionType;

public class MiracleASTreeBaseVisitor implements MiracleASTreeVisitor {
    @Override
    public void visit(MiracleASTreeFunctionDeclaration miracleASTreeFunctionDeclaration) {

    }

    @Override
    public void visit(MiracleASTreeClassDeclaration miracleASTreeClassDeclaration) {

    }

    @Override
    public void visit(MiracleASTree miracleASTree) {

    }

    @Override
    public void visit(MiracleASTreeVariableDeclaration miracleASTreeVariableDeclaration) {

    }

    @Override
    public void visit(MiracleASTreeBlock miracleASTreeBlock) {

    }

    @Override
    public void visit(MiracleASTreeSelection miracleASTreeSelection) {

    }

    @Override
    public void visit(MiracleASTreeIteration miracleASTreeIteration) {

    }

    @Override
    public void visit(MiracleASTreeBreak miracleASTreeBreak) {

    }

    @Override
    public void visit(MiracleASTreeContinue miracleASTreeContinue) {

    }

    @Override
    public void visit(MiracleASTreeReturn miracleASTreeReturn) {

    }

    @Override
    public void visit(MiracleASTreeVariable miracleASTreeVariable) {

    }

    @Override
    public void visit(MiracleASTreeCall miracleASTreeCall) {

    }

    @Override
    public void visit(MiracleASTreeSubscript miracleASTreeSubscript) {

    }

    @Override
    public void visit(MiracleASTreeBinaryExpression miracleASTreeBinaryExpression) {

    }

    @Override
    public void visit(MiracleASTreePrefixExpression miracleASTreePrefixExpression) {

    }

    @Override
    public void visit(MiracleASTreeSuffixExpression miracleASTreeSuffixExpression) {

    }

    @Override
    public void visit(MiracleASTreeNew miracleASTreeNew) {

    }

    @Override
    public void visit(MiracleASTreeConstant miracleASTreeConstant) {

    }

    @Override
    public void visit(MiracleASTreeFunctionType miracleASTreeFunctionType) {

    }

    @Override
    public void visit(MiracleASTreeBaseType miracleASTreeBaseType) {

    }

    @Override
    public void visit(MiracleASTreeArrayType miracleASTreeArrayType) {

    }
}
