package com.miracle.intermediate.instruction;

import com.miracle.intermediate.Node;
import com.miracle.intermediate.number.Register;

import java.util.Map;

public abstract class Instruction extends Node {
    public abstract void map(Map<Register, Register> map);
}
