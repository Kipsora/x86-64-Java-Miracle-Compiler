package com.miracle.intermediate.instruction.fork;

import com.miracle.intermediate.number.Number;
import com.miracle.intermediate.number.Register;
import com.miracle.intermediate.structure.BasicBlock;
import com.miracle.intermediate.visitor.IRVisitor;

import java.util.Collections;
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

    @Override
    public void accept(IRVisitor IRVisitor) {
        IRVisitor.visit(this);
    }

    @Override
    public void rename(Map map) {
        if (expression instanceof Register) {
            expression = (Number) map.getOrDefault(expression, expression);
        }
    }

    @Override
    public Set<Register> getUsedRegisters() {
        if (expression instanceof Register) {
            return Collections.singleton((Register) expression);
        } else {
            return Collections.emptySet();
        }
    }

    @Override
    public Set<String> getDeprecatedRegisters() {
        return Collections.emptySet();
    }

}
