package com.miracle.intermediate;

import com.miracle.intermediate.number.PhysicalRegister;
import com.miracle.intermediate.number.StackRegister;
import org.antlr.v4.parse.GrammarTreeVisitor;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RegisterBuffer {
    private final List<PhysicalRegister> physical = new LinkedList<>();
    private final Set<StackRegister> stack = new HashSet<>();
    private final Set<PhysicalRegister> physicalSet = new HashSet<>();
    private int spillSize;

    public List<PhysicalRegister> getPhysicalRegisters() {
        return physical;
    }

    public RegisterBuffer enroll(PhysicalRegister register) {
        if (physicalSet.contains(register)) return this;
        physical.add(register);
        physicalSet.add(register);
        return this;
    }

    public void enroll(StackRegister register) {
        if (register.isOffsetSettled()) return;
        stack.add(register);
        spillSize += register.size;
        register.setOffset(spillSize);
    }

    public List<PhysicalRegister> getCalleeSaveRegisters() {
        return physical.stream().filter(element -> !element.isCallerSave).collect(Collectors.toList());
    }

    public List<PhysicalRegister> getCallerSaveRegisters() {
        return physical.stream().filter(element -> element.isCallerSave).collect(Collectors.toList());
    }

    public int getSpillSize() {
        return spillSize;
    }
}
