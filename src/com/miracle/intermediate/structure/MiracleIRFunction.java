package com.miracle.intermediate.structure;

import com.miracle.intermediate.MiracleIRRegisterBuffer;
import com.miracle.intermediate.number.MiracleIRDirectRegister;
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

    public final MiracleIRRegisterBuffer buffer = new MiracleIRRegisterBuffer();

    private MiracleIRBasicBlock entryBasicBlock;

    public MiracleIRFunction(String identifier,
                             MiracleSymbolFunctionType type) {
        this.identifier = identifier;
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
            this.parameters.add(new MiracleIRDirectRegister(
                    argName.get(i),
                    argType.get(i).getRegisterSize()
            ));
        }
        if (type.getMemberFrom() != null) {
            this.selfRegister = buffer.require("this", type.getMemberFrom().getRegisterSize());
        } else {
            this.selfRegister = null;
        }
        this.entryBasicBlock = new MiracleIRBasicBlock("__" + identifier + ".entry");
    }

    public MiracleIRBasicBlock getEntryBasicBlock() {
        return entryBasicBlock;
    }

    public void accept(MiracleIRVisitor visitor) {
        visitor.visit(this);
    }
}
