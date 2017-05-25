package com.miracle.astree.statement.declaration;

import com.miracle.astree.statement.Statement;
import com.miracle.cstree.MiracleSourcePosition;
import com.miracle.symbol.Symbol;

public abstract class Declaration extends Statement implements Symbol {
    public final String identifier;
    public final MiracleSourcePosition identifierPosition;

    public Declaration(String identifier,
                       MiracleSourcePosition startPosition,
                       MiracleSourcePosition identifierPosition) {
        super(startPosition);
        this.identifier = identifier;
        this.identifierPosition = identifierPosition;
    }
}
