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

    public final Set<VirtualRegister> vertices;
    public final Map<VirtualRegister, Set<VirtualRegister>> forbidden;
    public final Map<VirtualRegister, Register> preColor;

    InterferenceGraph() {
        vertices = new HashSet<>();
        forbidden = new HashMap<>();
        preColor = new HashMap<>();
    }

    public void addVertex(VirtualRegister x) {
        vertices.add(x);
        forbidden.put(x, new HashSet<>());
    }

    public void setForbidden(VirtualRegister x, VirtualRegister y) {
        if (x == y) {
            return;
        }
        forbidden.get(x).add(y);
        forbidden.get(y).add(x);
    }

    public void addPreColor(VirtualRegister register, StackRegister color) {
        preColor.put(register, color);
    }
}
