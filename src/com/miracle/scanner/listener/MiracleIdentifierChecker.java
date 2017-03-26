package com.miracle.scanner.listener;

import com.miracle.cstree.MiracleParser;
import com.miracle.exceptions.MiracleExceptionIdentifierTypeError;
import com.miracle.exceptions.MiracleExceptionUndefinedIdentifier;
import com.miracle.scanner.environment.manager.MiracleEnvironmentReader;

public class MiracleIdentifierChecker extends MiracleRuntimeMaintainer {
    public MiracleIdentifierChecker() {
        super(new MiracleEnvironmentReader());
    }

    @Override
    public void enterVariableExpression(MiracleParser.VariableExpressionContext ctx) {
        if (!MiracleEnvironmentReader.contain(ctx.IDENTIFIER().getText())) {
            throw new MiracleExceptionUndefinedIdentifier(ctx.IDENTIFIER().getText());
        }
    }

    @Override
    public void enterTypename(MiracleParser.TypenameContext ctx) {
        if (ctx.IDENTIFIER() != null) {
            if (!MiracleEnvironmentReader.contain(ctx.IDENTIFIER().getText())) {
                throw new MiracleExceptionUndefinedIdentifier(ctx.IDENTIFIER().getText());
            } else {
                String typename = MiracleEnvironmentReader.get(ctx.IDENTIFIER().getText()).getIdentifierType();
                if (!typename.equals("class")) {
                    throw new MiracleExceptionIdentifierTypeError(ctx.IDENTIFIER().getText(), typename, "typename");
                }
            }
        }
    }
}
