package com.miracle.astree.node.expression.binary;

import com.miracle.astree.node.expression.MiracleASTreeExpression;
import com.miracle.astree.node.expression.value.MiracleASTreeValue;
import com.miracle.astree.visitor.MiracleASTreeVisitor;

public class MiracleASTreeMember extends MiracleASTreeBinaryExpression {
    public MiracleASTreeMember(MiracleASTreeExpression left, MiracleASTreeValue right) {
        super(right.getType(), left, ".", right, left.getMutable() && right.getMutable());
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.enter();
        visitor.visit(this);
        visitor.exit();
    }
}
