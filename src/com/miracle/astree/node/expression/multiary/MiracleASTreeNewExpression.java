package com.miracle.astree.node.expression.multiary;

import com.miracle.astree.node.expression.MiracleASTreeExpression;
import com.miracle.astree.visitor.MiracleASTreeVisitor;

import java.util.List;

public class MiracleASTreeNewExpression extends MiracleASTreeExpression {
    private final int dimension;
    private final List<Integer> size;
    private final String type;

    protected MiracleASTreeNewExpression(String type, int dimension, List<Integer> size) {
        super("new", true);
        this.dimension = dimension;
        this.size = size;
        StringBuilder tmp = new StringBuilder(type);
        for (int i = 0; i < dimension - 1; i++) {
            if (i < size.size()) {
                tmp.append("[").append(size.get(i)).append("]");
            } else {
                tmp.append("[]");
            }
        }
        this.type = tmp.toString();
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.visit(this);
    }

    public int getDimension() {
        return dimension;
    }

    public List<Integer> getSize() {
        return size;
    }

    @Override
    public String getType() {
        return type;
    }
}
