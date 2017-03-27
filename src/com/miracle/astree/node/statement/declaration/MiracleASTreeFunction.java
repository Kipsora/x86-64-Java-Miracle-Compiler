package com.miracle.astree.node.statement.declaration;

import com.miracle.astree.node.MiracleASTreeTypename;
import com.miracle.astree.node.statement.MiracleASTreeStatement;
import com.miracle.astree.visitor.MiracleASTreeVisitor;

import java.util.List;

public class MiracleASTreeFunction extends MiracleASTreeMemberDeclaration {
    private final List<MiracleASTreeVariable> arguments;
    private final List<MiracleASTreeStatement> body;
    private final MiracleASTreeTypename type;

    public MiracleASTreeFunction(MiracleASTreeTypename type, String identifier,
                                 List<MiracleASTreeVariable> arguments, List<MiracleASTreeStatement> body) {
        super(identifier);
        this.arguments = arguments;
        this.body = body;
        this.type = type;
    }

    public MiracleASTreeFunction(String decorator, MiracleASTreeTypename type, String identifier,
                                 List<MiracleASTreeVariable> arguments, List<MiracleASTreeStatement> body) {
        super(decorator, identifier);
        this.arguments = arguments;
        this.body = body;
        this.type = type;
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.enter();
        visitor.visit(this);
        visitor.exit();
    }

    public List<MiracleASTreeVariable> getArguments() {
        return arguments;
    }

    public List<MiracleASTreeStatement> getBody() {
        return body;
    }

    public MiracleASTreeTypename getType() {
        return type;
    }
}
