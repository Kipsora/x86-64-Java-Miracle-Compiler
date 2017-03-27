package com.miracle.astree.node;

import com.miracle.astree.visitor.MiracleASTreeVisitor;

import java.util.List;
import java.util.Objects;

public class MiracleASTreeTypename extends MiracleASTreeNode {
    private final String basetype;
    private final List<Integer> dimension;

    /**
     * No exception presented here because the parser rule has prevented
     * such error.
     */
    public MiracleASTreeTypename(String basetype, List<Integer> dimension) {
        this.basetype = basetype;
        this.dimension = dimension;
    }

    public MiracleASTreeTypename(String basetype) {
        this.basetype = basetype;
        this.dimension = null;
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.enter();
        visitor.visit(this);
        visitor.exit();
    }

    public String getBasetype() {
        return basetype;
    }

    public List<Integer> getDimension() {
        return dimension;
    }

    public boolean equals(MiracleASTreeTypename e) {
        return e.basetype.equals(basetype) && Objects.equals(dimension, e.dimension);
    }

    @Override
    public String toString() {
        StringBuilder tmp = new StringBuilder(basetype);
        if (dimension != null) {
            for (int value : dimension) {
                if (value == 0) {
                    tmp.append("[]");
                } else {
                    tmp.append("[").append(value).append("]");
                }
            }
        }
        return tmp.toString();
    }
}
