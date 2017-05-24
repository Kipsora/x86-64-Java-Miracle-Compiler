package com.miracle.intermediate;

import com.miracle.astree.statement.declaration.MiracleASTreeVariableDeclaration;
import com.miracle.intermediate.number.MiracleIRRegister;
import com.miracle.intermediate.number.MiracleIRVirtualRegister;

import java.util.HashMap;
import java.util.Map;

public class MiracleIRRegisterBuffer {
    private final Map<MiracleIRRegister, Integer> set = new HashMap<>();
    private int totalSize;

    public Map<MiracleIRRegister, Integer> getRegisters() {
        return set;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void enroll(MiracleIRRegister register) {
        set.put(register, totalSize);
        totalSize += register.getNumberSize();
    }

    public MiracleIRVirtualRegister require(MiracleASTreeVariableDeclaration declaration) {
        MiracleIRVirtualRegister register = new MiracleIRVirtualRegister(declaration);
        set.put(register, totalSize);
        totalSize += register.getNumberSize();
        return register;
    }

    public MiracleIRVirtualRegister require(String name, int size) {
        MiracleIRVirtualRegister register = new MiracleIRVirtualRegister(name, size);
        set.put(register, totalSize);
        totalSize += register.getNumberSize();
        return register;
    }
}
