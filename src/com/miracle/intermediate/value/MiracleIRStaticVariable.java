package com.miracle.intermediate.value;

import com.miracle.astree.statement.declaration.MiracleASTreeVariableDeclaration;
import com.miracle.intermediate.visitor.MiracleIRVisitor;

public class MiracleIRStaticVariable extends MiracleIRMemory {
    public final String identifier;
    public final int size;
    public final MiracleASTreeVariableDeclaration declaration;

    public MiracleIRStaticVariable(String identifier, int size, MiracleASTreeVariableDeclaration declaration) {
        this.identifier = identifier;
        this.size = size;
        this.declaration = declaration;
    }

    public void accept(MiracleIRVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return "[rel " + identifier + "]";
    }
}
