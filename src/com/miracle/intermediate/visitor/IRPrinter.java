package com.miracle.intermediate.visitor;

import java.io.IOException;

public interface IRPrinter extends IRVisitor {
    String getOutput() throws IOException;
}
