package com.miracle.exceptions;

import com.miracle.scanner.listener.MiracleRuntimeMaintainer;

public abstract class MiracleException extends Error {
    public abstract String toString();

    @Override
    public String getMessage() {
        return "Syntax error:" + String.valueOf(MiracleRuntimeMaintainer.getRow()) + ":"
                + String.valueOf(MiracleRuntimeMaintainer.getColumn()) + ": " + toString() + "\n";
    }
}