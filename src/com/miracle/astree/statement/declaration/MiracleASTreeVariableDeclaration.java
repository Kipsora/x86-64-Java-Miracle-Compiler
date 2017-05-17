package com.miracle.astree.statement.declaration;

import com.miracle.astree.base.MiracleASTreeTypeNode;
import com.miracle.astree.statement.expression.MiracleASTreeExpression;
import com.miracle.astree.visitor.MiracleASTreeVisitor;
import com.miracle.cstree.MiracleSourcePosition;

public class MiracleASTreeVariableDeclaration extends MiracleASTreeMemberDeclaration {
    public final MiracleASTreeExpression expression;
    public final MiracleASTreeTypeNode typenode;
    public final boolean isMember;

    public MiracleASTreeVariableDeclaration(String identifier,
                                            MiracleASTreeTypeNode typenode,
                                            MiracleASTreeExpression expression,
                                            MiracleSourcePosition startPosition,
                                            MiracleSourcePosition identifierPosition,
                                            boolean isMember) {
        super(identifier, startPosition, identifierPosition);
        this.typenode = typenode;
        this.expression = expression;
        this.isMember = isMember;
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.visit(this);
    }
}
