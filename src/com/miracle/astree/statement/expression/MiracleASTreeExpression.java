package com.miracle.astree.statement.expression;

import com.miracle.astree.statement.MiracleASTreeStatement;
import com.miracle.cstree.MiracleSourcePosition;
import com.miracle.intermediate.number.MiracleIRNumber;
import com.miracle.symbol.MiracleSymbolType;

public abstract class MiracleASTreeExpression extends MiracleASTreeStatement {
    private MiracleSymbolType resultType;
    private boolean isMutable;
    private MiracleIRNumber resultAddress;

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
        if (this.resultType != null) {
            throw new RuntimeException("return type already settled");
        }
        this.resultType = resultType;
    }

    public abstract String toPrintableString();

    public MiracleIRNumber getResultNumber() {
        assert resultAddress != null;
        return resultAddress;
    }

    public void setResultNumber(MiracleIRNumber address) {
        assert resultAddress != null;
        this.resultAddress = address;
    }
}
