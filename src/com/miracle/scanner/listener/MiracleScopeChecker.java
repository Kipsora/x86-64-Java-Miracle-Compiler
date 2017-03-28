package com.miracle.scanner.listener;

import com.miracle.cstree.MiracleParser;
import com.miracle.exceptions.MiracleExceptionStatementScope;
import com.miracle.scanner.environment.MiracleEnvironmentManager;

public abstract class MiracleScopeChecker extends MiracleRuntimeMaintainer {
    MiracleScopeChecker(MiracleEnvironmentManager environment) {
        super(environment);
    }

    @Override
    public void enterContinueStatement(MiracleParser.ContinueStatementContext ctx) {
        if (!MiracleEnvironmentManager.inScope(MiracleEnvironmentManager.ScopeType.SCOPE_ITER)) {
            throw new MiracleExceptionStatementScope("continue", "iteration");
        }
    }

    @Override
    public void enterBreakStatement(MiracleParser.BreakStatementContext ctx) {
        if (!MiracleEnvironmentManager.inScope(MiracleEnvironmentManager.ScopeType.SCOPE_ITER)) {
            throw new MiracleExceptionStatementScope("break", "iteration");
        }
    }

    @Override
    public void enterReturnStatement(MiracleParser.ReturnStatementContext ctx) {
        if (!MiracleEnvironmentManager.inScope(MiracleEnvironmentManager.ScopeType.SCOPE_FUNC)) {
            throw new MiracleExceptionStatementScope("return", "function");
        }
    }
}
