package com.miracle.astree.node.declaration;

import com.miracle.astree.visitor.MiracleASTreeVisitor;

import java.util.List;

public class MiracleASTreeFunction extends MiracleASTreeDeclaration {
    private final List<MiracleASTreeVariable> arguments;

    MiracleASTreeFunction(String identifier, List<MiracleASTreeVariable> arguments) {
        super(identifier);
        this.arguments = arguments;
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.visit(this);
    }

    public List<MiracleASTreeVariable> getArguments() {
        return arguments;
    }
}
