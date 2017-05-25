package com.miracle.astree.statement.expression;

import com.miracle.astree.statement.Statement;
import com.miracle.cstree.MiracleSourcePosition;
import com.miracle.intermediate.number.Number;
import com.miracle.symbol.SymbolType;

public abstract class Expression extends Statement {
    private SymbolType resultType;
    private boolean isMutable;
    private Number resultAddress;

    public Expression(MiracleSourcePosition startPosition) {
        super(startPosition);
    }

    public void setMutable() {
        this.isMutable = true;
    }

    public boolean isMutable() {
        return isMutable;
    }

    public SymbolType getResultType() {
        return resultType;
    }

    public void setResultType(SymbolType resultType) {
        if (this.resultType != null) {
            throw new RuntimeException("return type already settled");
        }
        this.resultType = resultType;
    }

    public abstract String toPrintableString();

    public Number getResultNumber() {
        assert resultAddress != null;
        return resultAddress;
    }

    public void setResultNumber(Number address) {
        assert resultAddress != null;
        this.resultAddress = address;
    }
}
