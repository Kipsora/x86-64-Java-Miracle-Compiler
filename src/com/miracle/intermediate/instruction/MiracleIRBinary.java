package com.miracle.intermediate.instruction;

import com.miracle.intermediate.value.MiracleIRRegister;
import com.miracle.intermediate.visitor.MiracleIRVisitor;

public class MiracleIRBinary extends MiracleIRInstruction {
    public final Types operator;
    public final MiracleIRRegister sourceA;
    public final MiracleIRRegister sourceB;
    public final MiracleIRRegister target;

    public MiracleIRBinary(MiracleIRRegister sourceA,
                           Types operator,
                           MiracleIRRegister sourceB,
                           MiracleIRRegister target) {
        this.operator = operator;
        this.sourceA = sourceA;
        this.sourceB = sourceB;
        this.target = target;
    }

    @Override
    public void accept(MiracleIRVisitor visitor) {
        visitor.visit(this);
    }

    public enum Types {
        ADD, SUB, MUL, DIV, MOD,
        SHL, SHR, AND, OR, XOR;

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
            } else if (this.equals(AND)) {
                return "and";
            } else if (this.equals(OR)) {
                return "or";
            } else {
                return "xor";
            }
        }
    }
}
