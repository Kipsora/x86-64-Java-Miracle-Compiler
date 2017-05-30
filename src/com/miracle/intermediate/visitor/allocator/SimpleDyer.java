package com.miracle.intermediate.visitor.allocator;

import com.miracle.intermediate.number.*;
import com.miracle.intermediate.number.Number;

import java.util.*;

public class SimpleDyer implements GraphDyer {
    @Override
    public Map<Number, Register> color(InterferenceGraph graph) {
        Map<Number, Register> map = new HashMap<>();
        Map<Number, String> adj = new HashMap<>();
        List<Register> vertices = new LinkedList<>(graph.vertices);
        graph.preColor.forEach((key, value) -> {
            vertices.remove(key);
            if (value instanceof StackRegister) {
                map.put(key, (Register) value);
            } else {
                adj.put(key, (String) value);
            }
        });
        vertices.sort((x, y) -> graph.forbidden.get(y).size() - graph.forbidden.get(x).size());
        vertices.forEach(node -> {
            List<String> allColor = new LinkedList<>(graph.color);
            graph.forbidden.get(node).forEach(another -> {
                if (map.containsKey(another) && map.get(another) instanceof PhysicalRegister) {
                    allColor.remove(((PhysicalRegister) map.get(another)).indexName);
                }
                if (adj.containsKey(another)) {
                    allColor.remove(adj.get(another));
                }
            });
            if (allColor.isEmpty()) {
                map.put(node, new StackRegister(node.size));
            } else {
                map.put(node, PhysicalRegister.getBy16BITName(allColor.iterator().next(), node.size));
            }
        });
        return map;
    }
}
