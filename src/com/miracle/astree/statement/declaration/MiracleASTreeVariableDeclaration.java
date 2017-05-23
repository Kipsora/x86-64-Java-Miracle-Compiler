package com.miracle.astree.statement.declaration;

import com.miracle.astree.base.MiracleASTreeTypeNode;
import com.miracle.astree.statement.expression.MiracleASTreeExpression;
import com.miracle.astree.visitor.MiracleASTreeVisitor;
import com.miracle.cstree.MiracleSourcePosition;
import com.miracle.intermediate.number.MiracleIRRegister;

public class MiracleASTreeVariableDeclaration extends MiracleASTreeMemberDeclaration {
    public final MiracleASTreeExpression expression;
    public final MiracleASTreeTypeNode typenode;

    private MiracleIRRegister address;

    public MiracleASTreeVariableDeclaration(String identifier,
                                            MiracleASTreeTypeNode typenode,
                                            MiracleASTreeExpression expression,
                                            MiracleSourcePosition startPosition,
                                            MiracleSourcePosition identifierPosition) {
        super(identifier, startPosition, identifierPosition);
        this.typenode = typenode;
        this.expression = expression;
    }

    public MiracleIRRegister getAddress() {
        return address;
    }

    public void setAddress(MiracleIRRegister address) {
        this.address = address;
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.visit(this);
    }
}
