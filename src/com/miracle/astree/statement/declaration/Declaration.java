package com.miracle.astree.statement.declaration;

import com.miracle.astree.statement.Statement;
import com.miracle.cstree.SourcePosition;
import com.miracle.symbol.Symbol;

public abstract class Declaration extends Statement implements Symbol {
    public final String identifier;
    public final SourcePosition identifierPosition;

    public Declaration(String identifier,
                       SourcePosition startPosition,
                       SourcePosition identifierPosition) {
        super(startPosition);
        this.identifier = identifier;
        this.identifierPosition = identifierPosition;
    }
}
