package com.miracle.intermediate;

import com.miracle.intermediate.number.PhysicalRegister;
import com.miracle.intermediate.number.StackRegister;

import java.util.HashSet;
import java.util.Set;

public class RegisterBuffer {
    private final Set<PhysicalRegister> physical = new HashSet<>();
    private final Set<StackRegister> stack = new HashSet<>();
    private int spillSize;

    public Set<PhysicalRegister> getPhysicalRegisters() {
        return physical;
    }

    public RegisterBuffer enroll(PhysicalRegister register) {
        physical.add(register);
        return this;
    }

    public void enroll(StackRegister register) {
        if (stack.contains(register)) return;
        stack.add(register);
        spillSize += register.size;
        register.setOffset(spillSize);
    }

    public int getSpillSize() {
        return spillSize;
    }
}
