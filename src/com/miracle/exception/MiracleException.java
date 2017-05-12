package com.miracle.exception;

public class MiracleException extends Error {
    private static final long serialVersionUID = 4815171501935957609L;
    private final String message;

    MiracleException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
