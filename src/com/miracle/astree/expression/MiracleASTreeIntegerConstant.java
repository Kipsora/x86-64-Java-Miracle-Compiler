package com.miracle.astree.expression;

import com.miracle.astree.MiracleASTreeNode;
import com.miracle.astree.visitor.MiracleASTreeVisitor;

public class MiracleASTreeIntegerConstant extends MiracleASTreeConstant {
    public final int value;

    public MiracleASTreeIntegerConstant(String value) {
        this.value = Integer.valueOf(value);
    }

    @Override
    public String getValue() {
        return String.valueOf(value);
    }
}
