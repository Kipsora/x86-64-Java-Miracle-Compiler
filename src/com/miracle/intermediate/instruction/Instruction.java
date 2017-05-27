package com.miracle.intermediate.instruction;

import com.miracle.intermediate.Node;
import com.miracle.intermediate.number.Number;
import com.miracle.intermediate.number.OffsetRegister;
import com.miracle.intermediate.number.Register;

import java.util.Map;
import java.util.Set;

public abstract class Instruction extends Node {
    protected static void addToSet(Number register, Set<Register> set) {
        if (register instanceof Register) {
            set.add((Register) register);
            if (register instanceof OffsetRegister) {
                if (((OffsetRegister) register).getRawBase() != null) {
                    set.add(((OffsetRegister) register).getRawBase());
                }
                if (((OffsetRegister) register).getRawOffsetB() != null) {
                    set.add(((OffsetRegister) register).getRawOffsetB());
                }
            }
        }
    }

    public abstract void rename(Map<Register, Register> map);

    /**
     * @return a rename contain registers to be allocated which stores source data
     * in the instruction, and the second is whether it is forced to be allocated
     * to a specific register
     */
    public abstract Set<Register> getUseRegisters();

    public abstract Set<Register> getDefRegisters();
}
