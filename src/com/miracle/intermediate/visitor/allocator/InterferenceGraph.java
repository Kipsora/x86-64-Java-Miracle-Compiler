package com.miracle.intermediate.visitor.allocator;

import com.miracle.intermediate.number.PhysicalRegister;
import com.miracle.intermediate.number.Register;
import com.miracle.intermediate.number.StackRegister;
import com.miracle.intermediate.number.VirtualRegister;

import java.util.*;

public class InterferenceGraph {
    public List<String> color = new LinkedList<String>() {{
        add("RBX");
        add("RSI");
        add("R12");
        add("RDI");
        add("R13");
        add("R8");
        add("R14");
        add("R9");
        add("R15");
        add("R10");
        add("R11");
    }};

    public final Set<Register> vertices;
    public final Map<Register, Set<Register>> forbidden;
    public final Map<Register, Object> preColor;

    InterferenceGraph() {
        vertices = new HashSet<>();
        forbidden = new HashMap<>();
        preColor = new HashMap<>();
    }

    public void addVertex(Register x) {
        vertices.add(x);
        forbidden.put(x, new HashSet<>());
    }

    public void setForbidden(Register x, Register y) {
        if (x == y) {
            return;
        }
        forbidden.get(x).add(y);
        forbidden.get(y).add(x);
    }

    public void addPreColor(Register register, Object color) {
        preColor.put(register, color);
    }
}
