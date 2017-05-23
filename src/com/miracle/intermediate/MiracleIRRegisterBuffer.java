package com.miracle.intermediate;

import com.miracle.astree.statement.declaration.MiracleASTreeVariableDeclaration;
import com.miracle.intermediate.number.MiracleIRDirectRegister;
import com.miracle.intermediate.number.MiracleIRRegister;

import java.util.LinkedList;
import java.util.List;

public class MiracleIRRegisterBuffer {
    private final List<MiracleIRRegister> set = new LinkedList<>();

    public List<MiracleIRRegister> getRegisters() {
        return set;
    }

    public MiracleIRDirectRegister require(MiracleASTreeVariableDeclaration declaration) {
        MiracleIRDirectRegister register = new MiracleIRDirectRegister(declaration);
        set.add(register);
        return register;
    }

    public MiracleIRDirectRegister require(String name, int size) {
        MiracleIRDirectRegister register = new MiracleIRDirectRegister(name, size);
        set.add(register);
        return register;
    }
}
