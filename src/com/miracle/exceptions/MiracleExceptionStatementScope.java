package com.miracle.exceptions;

import org.antlr.v4.runtime.Token;

/**
 * Created by kipsora on 17-3-25.
 */
public class MiracleExceptionStatementScope extends MiracleException {
    private String type;
    private String scope;

    public MiracleExceptionStatementScope(int line, int column, String type, String scope) {
        super(line, column);
        this.type = type;
        this.scope = scope;
    }
    public MiracleExceptionStatementScope(Token token, String type, String scope) {
        super(token);
        this.type = type;
        this.scope = scope;
    }

    @Override
    public String toString() {
        return type + " statement should be in a " + scope + " scope.";
    }
}
