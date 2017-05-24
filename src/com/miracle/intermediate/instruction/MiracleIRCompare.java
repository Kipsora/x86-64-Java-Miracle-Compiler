package com.miracle.intermediate.instruction;

import com.miracle.intermediate.number.MiracleIRNumber;
import com.miracle.intermediate.number.MiracleIRRegister;
import com.miracle.intermediate.visitor.MiracleIRVisitor;

public class MiracleIRCompare extends MiracleIRInstruction {
    public final MiracleIRNumber sourceA;
    public final MiracleIRNumber sourceB;
    public final MiracleIRRegister target;
    public final Types operator;

    public MiracleIRCompare(Types operator,
                            MiracleIRNumber sourceA,
                            MiracleIRNumber sourceB,
                            MiracleIRRegister target) {
        this.sourceA = sourceA;
        this.sourceB = sourceB;
        this.target = target;
        this.operator = operator;
    }

    @Override
    public void accept(MiracleIRVisitor visitor) {
        visitor.visit(this);
    }

    public enum Types {
        EQL, NEQ, LT, RT, LEQ, REQ;

        @Override
        public String toString() {
            if (this.equals(EQL)) {
                return "sete";
            } else if (this.equals(NEQ)) {
                return "setne";
            } else if (this.equals(LT)) {
                return "setl";
            } else if (this.equals(RT)) {
                return "setg";
            } else if (this.equals(LEQ)) {
                return "setle";
            } else {
                return "setge";
            }
        }
    }
}
