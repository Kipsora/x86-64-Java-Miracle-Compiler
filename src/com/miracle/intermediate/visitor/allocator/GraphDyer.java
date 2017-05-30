package com.miracle.intermediate.visitor.allocator;

import com.miracle.intermediate.number.*;
import com.miracle.intermediate.number.Number;

import java.util.Map;
import java.util.Optional;

public interface GraphDyer {
    Map<Number, Register> color(InterferenceGraph graph);
}
