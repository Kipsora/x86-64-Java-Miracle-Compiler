package com.miracle.intermediate.instruction.arithmetic;

import com.miracle.intermediate.instruction.Instruction;
import com.miracle.intermediate.number.*;
import com.miracle.intermediate.number.Number;
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
    public void set(Map<Number, Register> map) {
        target = map.getOrDefault(target, target);
        if (target instanceof OffsetRegister) {
            ((OffsetRegister) target).set(map);
        }
        if (map.containsKey(source)) source = map.get(source);
        if (source instanceof OffsetRegister) {
            ((OffsetRegister) source).set(map);
        }
    }

    @Override
    public void rename(Map<VirtualRegister, VirtualRegister> map) {
        if (target instanceof VirtualRegister) {
            target = map.get(target);
        } else if (target instanceof OffsetRegister) {
            ((OffsetRegister) target).rename(map);
        }
        if (source instanceof VirtualRegister) {
            source = map.get(source);
        } else if (source instanceof OffsetRegister) {
            ((OffsetRegister) source).rename(map);
        }
    }

    @Override
    public Set<Number> getUseNumbers() {
        Set<Number> set = new HashSet<>();
        addToSet(source, set, false);
        addToSet(target, set, false);
        return set;
    }

    @Override
    public Set<Number> getDefNumbers() {
        Set<Number> set = new HashSet<>();
        addToSet(target, set, true);
        return set;
    }

    public Number getSource() {
        return source;
    }

    public void setSource(VirtualRegister source) {
        this.source = source;
    }

    public Register getTarget() {
        return target;
    }

    public void setTarget(PhysicalRegister target) {
        this.target = target;
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
