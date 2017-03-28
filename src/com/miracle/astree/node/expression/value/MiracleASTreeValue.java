package com.miracle.astree.node.expression.value;

import com.miracle.astree.node.MiracleASTreeTypename;
import com.miracle.astree.node.expression.MiracleASTreeExpression;

public abstract class MiracleASTreeValue extends MiracleASTreeExpression {
    protected MiracleASTreeValue(MiracleASTreeTypename type, boolean mutable) {
        super(type, "value", mutable);
    }
}
