package com.miracle.intermediate.instruction.arithmetic;

import com.miracle.intermediate.instruction.Instruction;
import com.miracle.intermediate.number.Number;
import com.miracle.intermediate.number.Register;
import com.miracle.intermediate.visitor.Visitor;

import java.util.Map;

public class BinaryArithmetic extends Instruction {
    private Register target;
    private Number source;
    public final Types operator;

    public BinaryArithmetic(Types operator,
                            Register target,
                            Number source) {
        this.operator = operator;
        this.target = target;
        this.source = source;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void map(Map<Register, Register> map) {
        target = map.get(target);
        if (source instanceof Register) {
            source = map.get(source);
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
