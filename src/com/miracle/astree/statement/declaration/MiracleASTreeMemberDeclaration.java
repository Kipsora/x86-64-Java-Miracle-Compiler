package com.miracle.astree.statement.declaration;

import com.miracle.cstree.MiracleSourcePosition;

public abstract class MiracleASTreeMemberDeclaration extends MiracleASTreeDeclaration {
    public MiracleASTreeMemberDeclaration(String identifier,
                                          MiracleSourcePosition startPosition,
                                          MiracleSourcePosition identifierPosition) {
        super(identifier, startPosition, identifierPosition);
    }
}
