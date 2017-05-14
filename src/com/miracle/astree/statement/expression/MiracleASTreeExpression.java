package com.miracle.astree.statement.expression;

import com.miracle.astree.statement.MiracleASTreeStatement;
import com.miracle.cstree.MiracleSourcePosition;
import com.miracle.symbol.type.MiracleType;

public abstract class MiracleASTreeExpression extends MiracleASTreeStatement {
    private MiracleType resultType;
    private boolean isMutable;

    public MiracleASTreeExpression(MiracleSourcePosition startPosition) {
        super(startPosition);
    }

    public void setMutable() {
        this.isMutable = true;
    }

    public boolean isMutable() {
        return isMutable;
    }

    public MiracleType getResultType() {
        return resultType;
    }

    public void setResultType(MiracleType resultType) {
        this.resultType = resultType;
    }

    public abstract String toPrintableString();
}
