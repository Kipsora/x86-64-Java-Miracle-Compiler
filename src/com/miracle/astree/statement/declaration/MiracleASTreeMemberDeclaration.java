package com.miracle.astree.statement.declaration;

import com.miracle.cstree.MiracleSourcePosition;

public abstract class MiracleASTreeMemberDeclaration extends MiracleASTreeDeclaration {
    protected MiracleASTreeClassDeclaration memberFrom;

    public MiracleASTreeMemberDeclaration(String identifier,
                                          MiracleSourcePosition startPosition,
                                          MiracleSourcePosition identifierPosition) {
        super(identifier, startPosition, identifierPosition);
    }

    public MiracleASTreeClassDeclaration getMemberFrom() {
        return memberFrom;
    }

    public void setMemberFrom(MiracleASTreeClassDeclaration memberFrom) {
        if (this.memberFrom != null) {
            throw new RuntimeException("member from settled");
        }
        this.memberFrom = memberFrom;
    }
}
