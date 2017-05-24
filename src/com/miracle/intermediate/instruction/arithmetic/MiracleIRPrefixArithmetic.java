package com.miracle.intermediate.instruction.arithmetic;

import com.miracle.intermediate.instruction.MiracleIRInstruction;
import com.miracle.intermediate.number.MiracleIRDirectRegister;
import com.miracle.intermediate.visitor.MiracleIRVisitor;

public class MiracleIRPrefixArithmetic extends MiracleIRInstruction {
    public final MiracleIRDirectRegister target;
    public final Types operator;

    public MiracleIRPrefixArithmetic(MiracleIRDirectRegister target, Types operator) {
        this.target = target;
        this.operator = operator;
    }

    @Override
    public void accept(MiracleIRVisitor visitor) {
        visitor.visit(this);
    }

    public enum Types {
        ADD, SUB, REV, MINUS;

        @Override
        public String toString() {
            if (this.equals(ADD)) {
                return "inc";
            } else if (this.equals(SUB)) {
                return "dec";
            } else if (this.equals(REV)) {
                return "not";
            } else {
                return "neg";
            }
        }
    }
}
