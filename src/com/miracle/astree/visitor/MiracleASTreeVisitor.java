package com.miracle.astree.visitor;

import com.miracle.astree.node.declaration.MiracleASTreeClass;
import com.miracle.astree.node.declaration.MiracleASTreeVariable;
import com.miracle.astree.node.expression.binary.MiracleASTreeBinaryExpression;
import com.miracle.astree.node.expression.unary.MiracleASTreeUnaryExpression;

public interface MiracleASTreeVisitor {
    void visit(MiracleASTreeClass node);

    void visit(MiracleASTreeVariable node);

    void visit(MiracleASTreeBinaryExpression miracleASTreeBinaryExpression);

    void visit(MiracleASTreeUnaryExpression miracleASTreeUnaryExpression);
}
