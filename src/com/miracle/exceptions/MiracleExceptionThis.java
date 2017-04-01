package com.miracle.exceptions;

import com.miracle.astree.node.statement.declaration.MiracleASTreeTypename;

public class MiracleExceptionThis extends MiracleException {
    @Override
    public String toString() {
        return "unexpected keyword \"this\".";
    }
}
