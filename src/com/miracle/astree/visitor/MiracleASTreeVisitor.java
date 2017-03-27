package com.miracle.astree.visitor;

import com.miracle.astree.node.MiracleASTreeRoot;
import com.miracle.astree.node.declaration.MiracleASTreeClass;
import com.miracle.astree.node.declaration.MiracleASTreeVariable;
import com.miracle.astree.node.expression.binary.*;
import com.miracle.astree.node.expression.unary.prefix.MiracleASTreePrefixIntegral;
import com.miracle.astree.node.expression.unary.prefix.MiracleASTreeNegate;

public interface MiracleASTreeVisitor {
    void visit(MiracleASTreeClass node);

    void visit(MiracleASTreeVariable node);

    void visit(MiracleASTreeRoot miracleASTreeRoot);

    void visit(MiracleASTreeAssign miracleASTreeAssign);

    void visit(MiracleASTreeStringConcat miracleASTreeStringConcat);

    void visit(MiracleASTreeBinaryIntegral miracleASTreeBinaryIntegral);

    void visit(MiracleASTreeCompare miracleASTreeCompare);

    void visit(MiracleASTreeBinaryLogic miracleASTreeBinaryLogic);

    void visit(MiracleASTreePrefixIntegral miracleASTreePrefixIntegral);

    void visit(MiracleASTreeNegate miracleASTreeNegate);
}
