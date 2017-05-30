package com.miracle.intermediate.instruction.fork;

import com.miracle.intermediate.number.Number;
import com.miracle.intermediate.number.OffsetRegister;
import com.miracle.intermediate.number.Register;
import com.miracle.intermediate.number.VirtualRegister;
import com.miracle.intermediate.structure.BasicBlock;
import com.miracle.intermediate.visitor.IRVisitor;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UnaryBranch extends Branch {
    private Number expression;

    public UnaryBranch(Number expression,
                       BasicBlock branchTrue,
                       BasicBlock branchFalse) {
        super(branchTrue, branchFalse);
        this.expression = expression;
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
    public void set(Map<Number, Register> map) {
        if (map.containsKey(expression)) expression = map.get(expression);
        if (expression instanceof OffsetRegister) {
            ((OffsetRegister) expression).set(map);
        }
    }

    @Override
    public void rename(Map<VirtualRegister, VirtualRegister> map) {
        if (expression instanceof VirtualRegister) {
            expression = map.get(expression);
        } else if (expression instanceof OffsetRegister) {
            ((OffsetRegister) expression).rename(map);
        }
    }

    @Override
    public Set<Number> getUseNumbers() {
        Set<Number> set = new HashSet<>();
        addToSet(expression, set, false);
        return set;
    }

    @Override
    public Set<Number> getDefNumbers() {
        return Collections.emptySet();
    }
}
