package com.miracle.astree.node;

import com.miracle.astree.visitor.MiracleASTreeVisitor;

import java.util.Objects;

public class MiracleASTreeTypename extends MiracleASTreeNode {
    private final String basetype;
    private final int dimension;

    /**
     * No exception presented here because the parser rule has prevented
     * such error.
     */
    public MiracleASTreeTypename(String basetype, int dimension) {
        this.basetype = basetype;
        this.dimension = dimension;
    }

    public MiracleASTreeTypename(String basetype) {
        this.basetype = basetype;
        this.dimension = 0;
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

    public int getDimension() {
        return dimension;
    }

    public boolean equals(MiracleASTreeTypename e) {
        return e.basetype.equals(basetype) && Objects.equals(dimension, e.dimension);
    }

    @Override
    public String toString() {
        StringBuilder tmp = new StringBuilder(basetype);
        for (int i = 1; i <= dimension; i++) {
            tmp.append("[]");
        }
        return tmp.toString();
    }
}
