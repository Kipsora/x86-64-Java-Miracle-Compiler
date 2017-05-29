package com.miracle.intermediate.visitor;

import com.miracle.intermediate.Root;
import com.miracle.intermediate.instruction.*;
import com.miracle.intermediate.instruction.arithmetic.BinaryArithmetic;
import com.miracle.intermediate.instruction.arithmetic.UnaryArithmetic;
import com.miracle.intermediate.instruction.fork.BinaryBranch;
import com.miracle.intermediate.instruction.fork.Jump;
import com.miracle.intermediate.instruction.fork.Return;
import com.miracle.intermediate.instruction.fork.UnaryBranch;
import com.miracle.intermediate.structure.BasicBlock;
import com.miracle.intermediate.structure.Function;

public interface IRVisitor {
    void visit(Root ir);

    void visit(BinaryArithmetic binaryArithmetic);

    void visit(Move move);

    void visit(Function function);

    void visit(BasicBlock block);

    void visit(Call call);

    void visit(UnaryArithmetic prefixArithmetic);

    void visit(UnaryBranch unaryBranch);

    void visit(Return irReturn);

    void visit(Jump jump);

    void visit(Compare compare);

    void visit(HeapAllocate allocate);

    void visit(BinaryBranch binaryBranch);

    void visit(PhiInstruction phiInstruction);
}
