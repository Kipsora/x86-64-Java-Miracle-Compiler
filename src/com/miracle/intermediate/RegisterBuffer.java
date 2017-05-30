package com.miracle.intermediate;

import com.miracle.intermediate.number.PhysicalRegister;
import com.miracle.intermediate.number.StackRegister;
import org.antlr.v4.parse.GrammarTreeVisitor;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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
        if (register.isOffsetSettled()) return;
        stack.add(register);
        spillSize += register.size;
        register.setOffset(spillSize);
    }

    public Set<PhysicalRegister> getCalleeSaveRegisters() {
        return physical.stream().filter(element -> !element.isCallerSave).collect(Collectors.toSet());
    }

    public Set<PhysicalRegister> getCallerSaveRegisters() {
        return physical.stream().filter(element -> element.isCallerSave).collect(Collectors.toSet());
    }

    public int getSpillSize() {
        return spillSize;
    }
}
