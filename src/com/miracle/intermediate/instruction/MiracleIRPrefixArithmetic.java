package com.miracle.intermediate.instruction;

import com.miracle.intermediate.number.MiracleIRVirtualRegister;
import com.miracle.intermediate.visitor.MiracleIRVisitor;

public class MiracleIRPrefixArithmetic extends MiracleIRInstruction {
    public final MiracleIRVirtualRegister target;
    public final Types operator;

    public MiracleIRPrefixArithmetic(MiracleIRVirtualRegister target, Types operator) {
        this.target = target;
        this.operator = operator;
    }

    @Override
    public void accept(MiracleIRVisitor visitor) {
        visitor.visit(this);
    }

    public enum Types {
        ADD, SUB, NEG, REV, MINUS;

        @Override
        public String toString() {
            if (this.equals(ADD)) {
                return "inc";
            } else if (this.equals(SUB)) {
                return "dec";
            } else if (this.equals(NEG)) {
                return "neg";
            } else {
                throw new RuntimeException("unsupported operator");
            }
        }
    }
}
