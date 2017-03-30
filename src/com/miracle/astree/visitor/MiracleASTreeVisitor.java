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

public interface MiracleASTreeVisitor {
    void enter();

    void exit();

    void visit(MiracleASTreeClassDeclaration miracleASTreeClassDeclaration);

    void visit(MiracleASTreeVariableDeclaration miracleASTreeVariableDeclaration);

    void visit(MiracleASTreeRoot miracleASTreeRoot);

    void visit(MiracleASTreeAssign miracleASTreeAssign);

    void visit(MiracleASTreeStringConcat miracleASTreeStringConcat);

    void visit(MiracleASTreeBinaryIntegral miracleASTreeBinaryIntegral);

    void visit(MiracleASTreeCompare miracleASTreeCompare);

    void visit(MiracleASTreeBinaryLogic miracleASTreeBinaryLogic);

    void visit(MiracleASTreePrefixIntegral miracleASTreePrefixIntegral);

    void visit(MiracleASTreeNegate miracleASTreeNegate);

    void visit(MiracleASTreeSuffixIntegral miracleASTreeSuffixIntegral);

    void visit(MiracleASTreeNewExpression miracleASTreeNewExpression);

    void visit(MiracleASTreeFunctionDeclaration miracleASTreeFunctionDeclaration);

    void visit(MiracleASTreeMember miracleASTreeMember);

    void visit(MiracleASTreeBlock miracleASTreeBlock);

    void visit(MiracleASTreeSelection miracleASTreeSelection);

    void visit(MiracleASTreeFor miracleASTreeFor);

    void visit(MiracleASTreeWhile miracleASTreeWhile);

    void visit(MiracleASTreeContinue miracleASTreeContinue);

    void visit(MiracleASTreeBreak miracleASTreeBreak);

    void visit(MiracleASTreeReturn miracleASTreeReturn);

    void visit(MiracleASTreeConstant miracleASTreeConstant);

    void visit(MiracleASTreeVariable miracleASTreeVariable);

    void visit(MiracleASTreeCallExpression miracleASTreeCallExpression);

    void visit(MiracleASTreeFunction miracleASTreeFunction);

    void visit(MiracleASTreeTypename miracleASTreeTypename);

    void visit(MiracleASTreeSubscript miracleASTreeSubscript);

    void visit(MiracleASTreeArray miracleASTreeArray);
}
