package com.miracle.astree.node.statement.declaration;

import com.miracle.astree.node.MiracleASTreeNode;
import com.miracle.astree.visitor.MiracleASTreeVisitor;

import java.util.List;
import java.util.Objects;

public class MiracleASTreeTypename extends MiracleASTreeNode {
    private final String basetype;
    private final int dimension;
    private final List<MiracleASTreeTypename> arguments;

    public static String getArgStyle(List<MiracleASTreeTypename> arguments) {
        if (arguments == null) {
            return null;
        }
        StringBuilder tmp = new StringBuilder();
        tmp.append('(');
        boolean first = true;
        for (MiracleASTreeTypename entry : arguments) {
            if (!first) tmp.append(',');
            tmp.append(entry.toString());
            first = false;
        }
        tmp.append(')');
        return tmp.toString();
    }

    public MiracleASTreeTypename(String basetype, int dimension,
                                 List<MiracleASTreeTypename> arguments) {
        this.basetype = basetype;
        this.dimension = dimension;
        this.arguments = arguments;
    }

    public MiracleASTreeTypename(String basetype, List<MiracleASTreeTypename> arguments) {
        this.basetype = basetype;
        this.dimension = 0;
        this.arguments = arguments;
    }

    public MiracleASTreeTypename(String basetype, int dimension) {
        this.basetype = basetype;
        this.dimension = dimension;
        this.arguments = null;
    }

    public MiracleASTreeTypename(String basetype) {
        this.basetype = basetype;
        this.dimension = 0;
        this.arguments = null;
    }

    public List<MiracleASTreeTypename> getArguments() {
        return arguments;
    }

    public boolean match(List<MiracleASTreeTypename> arguments) {
        return Objects.equals(this.arguments, arguments);
    }

    public String getBasetype() {
        return basetype;
    }

    public int getDimension() {
        return dimension;
    }

    public boolean equals(MiracleASTreeTypename e) {
        return e.basetype.equals(basetype) && dimension == e.dimension
                && Objects.equals(arguments, e.arguments);
    }

    @Override
    public String toString() {
        StringBuilder tmp = new StringBuilder(basetype);
        for (int i = 1; i <= dimension; i++) {
            tmp.append("[]");
        }
        if (arguments != null) {
            tmp.append(getArgStyle(this.arguments));
        }
        return tmp.toString();
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.enter();
        visitor.visit(this);
        visitor.exit();
    }

    public TYPE getTypenameType() {
        if (arguments == null) {
            return TYPE.TN_VAR;
        } else {
            return TYPE.TN_FUNC;
        }
    }

    public enum TYPE {
        TN_FUNC, TN_VAR;

        @Override
        public String toString() {
            if (this.equals(TN_FUNC)) {
                return "function";
            } else {
                return "variable";
            }
        }
    }
}
