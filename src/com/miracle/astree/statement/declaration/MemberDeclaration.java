package com.miracle.astree.statement.declaration;

import com.miracle.cstree.MiracleSourcePosition;

public abstract class MemberDeclaration extends Declaration {
    protected ClassDeclaration memberFrom;

    public MemberDeclaration(String identifier,
                             MiracleSourcePosition startPosition,
                             MiracleSourcePosition identifierPosition) {
        super(identifier, startPosition, identifierPosition);
    }

    public ClassDeclaration getMemberFrom() {
        return memberFrom;
    }

    public void setMemberFrom(ClassDeclaration memberFrom) {
        if (this.memberFrom != null) {
            throw new RuntimeException("member from settled");
        }
        this.memberFrom = memberFrom;
    }
}
