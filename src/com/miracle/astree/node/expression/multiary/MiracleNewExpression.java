package com.miracle.astree.node.expression.multiary;

import com.miracle.astree.node.expression.MiracleASTreeExpression;
import com.miracle.astree.visitor.MiracleASTreeVisitor;

import java.util.List;

public class MiracleNewExpression extends MiracleASTreeExpression {
    private final int dimension;
    private final List<Integer> size;
    protected MiracleNewExpression(String type, int dimension, List<Integer> size) {
        super("new", type, true);
        this.dimension = dimension;
        this.size = size;
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {

    }
}
