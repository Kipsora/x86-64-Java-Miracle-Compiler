package com.miracle.astree.statement.declaration;

import com.miracle.astree.statement.MiracleASTreeStatement;
import com.miracle.cstree.MiracleSourcePosition;
import com.miracle.symbol.type.MiracleType;

public abstract class MiracleASTreeDeclaration extends MiracleASTreeStatement {
    public final String identifier;
    public final MiracleSourcePosition identifierPosition;

    public MiracleASTreeDeclaration(String identifier,
                                    MiracleSourcePosition startPosition,
                                    MiracleSourcePosition identifierPosition) {
        super(startPosition);
        this.identifier = identifier;
        this.identifierPosition = identifierPosition;
    }

    public abstract MiracleType getType();
}
