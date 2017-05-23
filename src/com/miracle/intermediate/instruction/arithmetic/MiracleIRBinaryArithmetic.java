package com.miracle.intermediate.instruction.arithmetic;

import com.miracle.intermediate.instruction.MiracleIRInstruction;
import com.miracle.intermediate.number.MiracleIRNumber;
import com.miracle.intermediate.number.MiracleIRRegister;
import com.miracle.intermediate.visitor.MiracleIRVisitor;

public class MiracleIRBinaryArithmetic extends MiracleIRInstruction {
    public final MiracleIRRegister target;
    public final MiracleIRNumber source;
    public final Types operator;

    public MiracleIRBinaryArithmetic(Types operator,
                                     MiracleIRRegister target,
                                     MiracleIRNumber source) {
        this.operator = operator;
        this.target = target;
        this.source = source;
    }

    @Override
    public void accept(MiracleIRVisitor visitor) {
        visitor.visit(this);
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
