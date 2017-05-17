package com.miracle.astree.statement.expression;

import com.miracle.astree.statement.MiracleASTreeStatement;
import com.miracle.cstree.MiracleSourcePosition;
import com.miracle.symbol.MiracleSymbolType;

public abstract class MiracleASTreeExpression extends MiracleASTreeStatement {
    private MiracleSymbolType resultType;
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

    public MiracleSymbolType getResultType() {
        return resultType;
    }

    public void setResultType(MiracleSymbolType resultType) {
        this.resultType = resultType;
    }

    public abstract String toPrintableString();
}
