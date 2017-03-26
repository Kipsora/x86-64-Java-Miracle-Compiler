package com.miracle.astree.visitor;

import com.miracle.astree.node.declaration.MiracleASTreeClass;
import com.miracle.astree.node.declaration.MiracleASTreeVariable;
import com.miracle.astree.node.expression.MiracleASTreeBinaryExpression;
import com.miracle.astree.node.expression.MiracleASTreeCompareExpression;
import com.miracle.astree.node.expression.MiracleASTreeUnaryExpression;

public interface MiracleASTreeVisitor {
    void visit(MiracleASTreeClass node);

    void visit(MiracleASTreeVariable node);

    void visit(MiracleASTreeBinaryExpression miracleASTreeBinaryExpression);

    void visit(MiracleASTreeCompareExpression miracleASTreeCompareExpression);

    void visit(MiracleASTreeUnaryExpression miracleASTreeUnaryExpression);
}
