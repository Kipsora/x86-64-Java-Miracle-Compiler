package com.miracle.intermediate.structure;

import com.miracle.intermediate.MiracleIRRegisterBuffer;
import com.miracle.intermediate.number.MiracleIRDirectRegister;
import com.miracle.intermediate.number.MiracleIRVirtualRegister;
import com.miracle.intermediate.visitor.MiracleIRVisitor;
import com.miracle.symbol.MiracleSymbolFunctionType;
import com.miracle.symbol.MiracleSymbolVariableType;

import java.util.LinkedList;
import java.util.List;

import static com.miracle.symbol.MiracleSymbolTable.__builtin_void;

public class MiracleIRFunction {
    public final String identifier;
    public final List<MiracleIRDirectRegister> parameters;
    public final MiracleIRDirectRegister retRegister;
    public final MiracleIRDirectRegister selfRegister;

    private MiracleIRRegisterBuffer buffer;

    private MiracleIRBasicBlock entryBasicBlock;
    private MiracleIRBasicBlock exitBasicBlock;

    public MiracleIRFunction(String identifier,
                             MiracleSymbolFunctionType type) {
        this.identifier = identifier;
        this.buffer = new MiracleIRRegisterBuffer();
        if (type.getReturnType() != null && !type.getReturnType().isSameType(__builtin_void)) {
            this.retRegister = buffer.require(".retval", type.getReturnType().getRegisterSize());
        } else {
            this.retRegister = null;
        }
        this.parameters = new LinkedList<>();
        List<String> argName = type.getArgName();
        List<MiracleSymbolVariableType> argType = type.getArgType();
        for (int i = 0, size = argName.size(); i < size; i++) {
            if (argType.get(i) == null) break;
            this.parameters.add(new MiracleIRVirtualRegister(
                    argName.get(i),
                    argType.get(i).getRegisterSize()
            ));
        }
        if (type.getMemberFrom() != null) {
            this.selfRegister = buffer.require("this", type.getMemberFrom().getRegisterSize());
        } else {
            this.selfRegister = null;
        }
        this.entryBasicBlock = new MiracleIRBasicBlock("__" + identifier + ".entry", this, true, false);
        this.exitBasicBlock = new MiracleIRBasicBlock("__" + identifier + ".exit", this, false, true);
    }

    public MiracleIRBasicBlock getEntryBasicBlock() {
        return entryBasicBlock;
    }

    public void accept(MiracleIRVisitor visitor) {
        visitor.visit(this);
    }

    public MiracleIRBasicBlock getExitBasicBlock() {
        return exitBasicBlock;
    }

    public MiracleIRRegisterBuffer getBuffer() {
        return buffer;
    }

    public void setBuffer(MiracleIRRegisterBuffer buffer) {
        this.buffer = buffer;
    }
}
