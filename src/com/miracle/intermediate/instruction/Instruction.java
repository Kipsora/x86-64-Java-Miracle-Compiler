package com.miracle.intermediate.instruction;

import com.miracle.intermediate.Node;
import com.miracle.intermediate.number.Register;

import java.util.Map;
import java.util.Set;

public abstract class Instruction extends Node {
    public abstract void rename(Map map);

    /**
     * @return a rename contain registers to be allocated which stores source data
     * in the instruction, and the second is whether it is forced to be allocated
     * to a specific register
     */
    public abstract Set<Register> getUsedRegisters();

    public abstract Set<String> getDeprecatedRegisters();
}
