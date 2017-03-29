package com.miracle.astree.node.expression.value;

import com.miracle.astree.node.expression.MiracleASTreeExpression;
import com.miracle.astree.node.statement.declaration.MiracleASTreeTypename;

public abstract class MiracleASTreeValue extends MiracleASTreeExpression {
    MiracleASTreeValue(MiracleASTreeTypename type, boolean mutable) {
        super(type, "value", mutable);
    }
}
