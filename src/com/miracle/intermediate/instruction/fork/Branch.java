package com.miracle.intermediate.instruction.fork;

import com.miracle.intermediate.number.Number;
import com.miracle.intermediate.number.Register;
import com.miracle.intermediate.structure.BasicBlock;
import com.miracle.intermediate.visitor.Visitor;

import java.util.Map;

public class Branch extends Fork {
    private Number expression;
    public final BasicBlock branchTrue;
    public final BasicBlock branchFalse;

    public Branch(Number expression,
                  BasicBlock branchTrue,
                  BasicBlock branchFalse) {
        this.expression = expression;
        this.branchTrue = branchTrue;
        this.branchFalse = branchFalse;
    }

    public Number getExpression() {
        return expression;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void map(Map<Register, Register> map) {
        if (expression instanceof Register) {
            expression = map.get(expression);
        }
    }
}
