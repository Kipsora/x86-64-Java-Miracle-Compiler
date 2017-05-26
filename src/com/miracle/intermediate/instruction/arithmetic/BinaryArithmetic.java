package com.miracle.intermediate.instruction.arithmetic;

import com.miracle.intermediate.instruction.Instruction;
import com.miracle.intermediate.number.Number;
import com.miracle.intermediate.number.Register;
import com.miracle.intermediate.visitor.IRVisitor;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BinaryArithmetic extends Instruction {
    public final Types operator;
    private Register target;
    private Number source;

    public BinaryArithmetic(Types operator,
                            Register target,
                            Number source) {
        this.operator = operator;
        this.target = target;
        this.source = source;
    }

    @Override
    public void accept(IRVisitor IRVisitor) {
        IRVisitor.visit(this);
    }

    @Override
    public void rename(Map map) {
        target = (Register) map.getOrDefault(target, target);
        if (source instanceof Register) {
            source = (Number) map.getOrDefault(source, source);
        }
    }

    @Override
    public Set<Register> getUsedRegisters() {
        return new HashSet<Register>() {{
            add(target);
            if (source instanceof Register) {
                add((Register) source);
            }
        }};
    }

    @Override
    public Set<String> getDeprecatedRegisters() {
        if (operator.equals(Types.MOD) || operator.equals(Types.DIV)) {
            return new HashSet<String>() {{
                add("RAX");
                add("RDX");
            }};
        } else {
            return new HashSet<>();
        }
    }

    public Number getSource() {
        return source;
    }

    public Register getTarget() {
        return target;
    }

    public enum Types {
        ADD, SUB, MUL, DIV, MOD,
        SHL, SHR, XOR, AND, OR;

        @Override
        public String toString() {
            if (this.equals(ADD)) {
                return "add";
            } else if (this.equals(SUB)) {
                return "sub";
            } else if (this.equals(MUL)) {
                return "imul";
            } else if (this.equals(DIV)) {
                return "div";
            } else if (this.equals(MOD)) {
                return "mod";
            } else if (this.equals(SHL)) {
                return "shl";
            } else if (this.equals(SHR)) {
                return "sar";
            } else if (this.equals(XOR)) {
                return "xor";
            } else if (this.equals(AND)) {
                return "and";
            } else {
                return "or";
            }
        }
    }
}
