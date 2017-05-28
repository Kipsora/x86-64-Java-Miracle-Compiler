package com.miracle.intermediate.instruction.fork;

import com.miracle.intermediate.number.Number;
import com.miracle.intermediate.number.OffsetRegister;
import com.miracle.intermediate.number.Register;
import com.miracle.intermediate.structure.BasicBlock;
import com.miracle.intermediate.visitor.IRVisitor;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UnaryBranch extends Fork {
    public final BasicBlock branchTrue;
    public final BasicBlock branchFalse;
    private Number expression;

    public UnaryBranch(Number expression,
                       BasicBlock branchTrue,
                       BasicBlock branchFalse) {
        this.expression = expression;
        this.branchTrue = branchTrue;
        this.branchFalse = branchFalse;
    }

    public Number getExpression() {
        return expression;
    }

    public void setExpression(Number expression) {
        this.expression = expression;
    }

    @Override
    public void accept(IRVisitor IRVisitor) {
        IRVisitor.visit(this);
    }

    @Override
    public void rename(Map<Number, Register> map) {
        if (map.containsKey(expression)) expression = map.get(expression);
        if (expression instanceof OffsetRegister) {
            ((OffsetRegister) expression).map(map);
        }
    }

    @Override
    public Set<Number> getUseNumbers() {
        Set<Number> set = new HashSet<>();
        addToSet(expression, set);
        return set;
    }

    @Override
    public Set<Number> getDefNumbers() {
        return Collections.emptySet();
    }
}
