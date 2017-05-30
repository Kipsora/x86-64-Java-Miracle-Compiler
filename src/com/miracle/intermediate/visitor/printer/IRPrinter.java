package com.miracle.intermediate.visitor.printer;

import com.miracle.intermediate.visitor.IRVisitor;

import java.io.IOException;

public interface IRPrinter extends IRVisitor {
    String getOutput() throws IOException;
}
