package com.miracle.intermediate.instruction.fork;

import com.miracle.intermediate.structure.BasicBlock;

public abstract class Branch extends Fork {
    private BasicBlock branchTrue;
    private BasicBlock branchFalse;

    Branch(BasicBlock branchTrue, BasicBlock branchFalse) {
        this.branchTrue = branchTrue;
        this.branchFalse = branchFalse;
    }

    public BasicBlock splitBlock(BasicBlock from, BasicBlock to, String name) {
        BasicBlock block = new BasicBlock(name, from.blockFrom, false, false);
        if (to == branchTrue) branchTrue = block;
        else branchFalse = block;
        from.removeSucc(to);
        from.addSuccBasicBlock(block);
        block.setFork(new Jump(to));
        block.addSuccBasicBlock(to);
        return block;
    }

    public BasicBlock getBranchTrue() {
        return branchTrue;
    }

    public BasicBlock getBranchFalse() {
        return branchFalse;
    }
}
