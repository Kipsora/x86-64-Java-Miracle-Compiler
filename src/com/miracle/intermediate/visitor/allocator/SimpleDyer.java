package com.miracle.intermediate.visitor.allocator;

import com.miracle.intermediate.number.*;
import com.miracle.intermediate.number.Number;

import java.util.*;

public class SimpleDyer implements GraphDyer {
    @Override
    public Map<Number, Register> color(InterferenceGraph graph) {
        Map<Number, Register> map = new HashMap<>();
        List<VirtualRegister> vertices = new LinkedList<>(graph.vertices);
        graph.preColor.forEach((key, value) -> {
            vertices.remove(key);
            graph.forbidden.get(key).forEach(element -> graph.forbidden.get(element).remove(key));
            graph.forbidden.get(key).clear();
            map.put(key, value);
        });
        vertices.sort((x, y) -> graph.forbidden.get(y).size() - graph.forbidden.get(x).size());
        vertices.forEach(node -> {
            Set<String> allColor = new HashSet<>(graph.color);
            graph.forbidden.get(node).forEach(another -> {
                if (map.containsKey(another) && map.get(another) instanceof PhysicalRegister) {
                    allColor.remove(((PhysicalRegister) map.get(another)).indexName);
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
