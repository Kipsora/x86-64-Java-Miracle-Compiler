package com.miracle.astree.visitor;

import com.miracle.astree.node.MiracleASTreeRoot;
import com.miracle.astree.node.expression.binary.*;
import com.miracle.astree.node.expression.multiary.MiracleASTreeCallExpression;
import com.miracle.astree.node.expression.multiary.MiracleASTreeNewExpression;
import com.miracle.astree.node.expression.unary.prefix.MiracleASTreeNegate;
import com.miracle.astree.node.expression.unary.prefix.MiracleASTreePrefixIntegral;
import com.miracle.astree.node.expression.unary.suffix.MiracleASTreeSuffixIntegral;
import com.miracle.astree.node.expression.value.MiracleASTreeArray;
import com.miracle.astree.node.expression.value.MiracleASTreeConstant;
import com.miracle.astree.node.expression.value.MiracleASTreeFunction;
import com.miracle.astree.node.expression.value.MiracleASTreeVariable;
import com.miracle.astree.node.statement.MiracleASTreeBlock;
import com.miracle.astree.node.statement.MiracleASTreeSelection;
import com.miracle.astree.node.statement.control.MiracleASTreeBreak;
import com.miracle.astree.node.statement.control.MiracleASTreeContinue;
import com.miracle.astree.node.statement.control.MiracleASTreeReturn;
import com.miracle.astree.node.statement.declaration.MiracleASTreeClassDeclaration;
import com.miracle.astree.node.statement.declaration.MiracleASTreeFunctionDeclaration;
import com.miracle.astree.node.statement.declaration.MiracleASTreeTypename;
import com.miracle.astree.node.statement.declaration.MiracleASTreeVariableDeclaration;
import com.miracle.astree.node.statement.iteration.MiracleASTreeFor;
import com.miracle.astree.node.statement.iteration.MiracleASTreeWhile;

public class MiracleASTreeBaseVisitor implements MiracleASTreeVisitor {
    @Override
    public void enter() {

    }

    @Override
    public void exit() {

    }

    @Override
    public void visit(MiracleASTreeClassDeclaration miracleASTreeClassDeclaration) {

    }

    @Override
    public void visit(MiracleASTreeVariableDeclaration miracleASTreeVariableDeclaration) {

    }

    @Override
    public void visit(MiracleASTreeRoot miracleASTreeRoot) {

    }

    @Override
    public void visit(MiracleASTreeAssign miracleASTreeAssign) {

    }

    @Override
    public void visit(MiracleASTreeStringConcat miracleASTreeStringConcat) {

    }

    @Override
    public void visit(MiracleASTreeBinaryIntegral miracleASTreeBinaryIntegral) {

    }

    @Override
    public void visit(MiracleASTreeCompare miracleASTreeCompare) {

    }

    @Override
    public void visit(MiracleASTreeBinaryLogic miracleASTreeBinaryLogic) {

    }

    @Override
    public void visit(MiracleASTreePrefixIntegral miracleASTreePrefixIntegral) {

    }

    @Override
    public void visit(MiracleASTreeNegate miracleASTreeNegate) {

    }

    @Override
    public void visit(MiracleASTreeSuffixIntegral miracleASTreeSuffixIntegral) {

    }

    @Override
    public void visit(MiracleASTreeNewExpression miracleASTreeNewExpression) {

    }

    @Override
    public void visit(MiracleASTreeFunctionDeclaration miracleASTreeFunctionDeclaration) {

    }

    @Override
    public void visit(MiracleASTreeTypename miracleASTreeTypename) {

    }

    @Override
    public void visit(MiracleASTreeSubscript miracleASTreeSubscript) {

    }

    @Override
    public void visit(MiracleASTreeArray miracleASTreeArray) {

    }

    @Override
    public void visit(MiracleASTreeMember miracleASTreeMember) {

    }

    @Override
    public void visit(MiracleASTreeBlock miracleASTreeBlock) {

    }

    @Override
    public void visit(MiracleASTreeSelection miracleASTreeSelection) {

    }

    @Override
    public void visit(MiracleASTreeFor miracleASTreeFor) {

    }

    @Override
    public void visit(MiracleASTreeWhile miracleASTreeWhile) {

    }

    @Override
    public void visit(MiracleASTreeContinue miracleASTreeContinue) {

    }

    @Override
    public void visit(MiracleASTreeBreak miracleASTreeBreak) {

    }

    @Override
    public void visit(MiracleASTreeReturn miracleASTreeReturn) {

    }

    @Override
    public void visit(MiracleASTreeConstant miracleASTreeConstant) {

    }

    @Override
    public void visit(MiracleASTreeVariable miracleASTreeVariable) {

    }

    @Override
    public void visit(MiracleASTreeCallExpression miracleASTreeCallExpression) {

    }

    @Override
    public void visit(MiracleASTreeFunction miracleASTreeFunction) {

    }
}
