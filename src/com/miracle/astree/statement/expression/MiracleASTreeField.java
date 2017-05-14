package com.miracle.astree.statement.expression;

import com.miracle.Miracle;
import com.miracle.astree.visitor.MiracleASTreeVisitor;
import com.miracle.cstree.MiracleSourcePosition;

public class MiracleASTreeField extends MiracleASTreeExpression {
    public final MiracleASTreeExpression expression;
    public final MiracleSourcePosition identifierPosition;
    public final MiracleSourcePosition operatorPosition;
    public final String identifier;

    public MiracleASTreeField(MiracleASTreeExpression expression,
                              String identifier,
                              MiracleSourcePosition startPosition,
                              MiracleSourcePosition operatorPosition,
                              MiracleSourcePosition identifierPosition) {
        super(startPosition);
        this.expression = expression;
        this.identifier = identifier;
        this.operatorPosition = operatorPosition;
        this.identifierPosition = identifierPosition;
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toPrintableString() {
        return null;
    }
}
