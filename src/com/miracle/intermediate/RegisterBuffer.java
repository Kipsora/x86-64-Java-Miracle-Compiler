package com.miracle.intermediate;

import com.miracle.intermediate.number.StackRegister;

import java.util.HashSet;
import java.util.Set;

public class RegisterBuffer {
    private final Set<String> physical = new HashSet<>();
    private final Set<StackRegister> stack = new HashSet<>();
    private int spillSize;

    public Set<String> getPhysicalRegisters() {
        return physical;
    }

    public RegisterBuffer enroll(String register) {
        physical.add(register);
        return this;
    }

    public void enroll(StackRegister register) {
        if (register.isOffsetSettled()) return;
        stack.add(register);
        spillSize += register.size;
        register.setOffset(spillSize);
    }

    public int getSpillSize() {
        return spillSize;
    }
}
