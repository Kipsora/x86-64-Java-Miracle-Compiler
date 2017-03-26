package com.miracle.scanner.listener;

import com.miracle.cstree.MiracleParser;
import com.miracle.exceptions.MiracleExceptionStatement;
import com.miracle.exceptions.MiracleExceptionStatementScope;
import com.miracle.scanner.environment.manager.MiracleEnvironmentManager;

public abstract class MiracleScopeChecker extends MiracleRuntimeMaintainer {
    MiracleScopeChecker(MiracleEnvironmentManager environment) {
        super(environment);
    }

    @Override
    public void exitFunctionDeclarationStatement(MiracleParser.FunctionDeclarationStatementContext ctx) {
        if (MiracleEnvironmentManager.inScope(MiracleEnvironmentManager.ScopeType.SCOPE_BLOCK)) {
            throw new MiracleExceptionStatement("function declaration");
        }
        super.exitFunctionDeclarationStatement(ctx);
    }

    @Override
    public void enterClassDeclarationStatement(MiracleParser.ClassDeclarationStatementContext ctx) {
        if (!MiracleEnvironmentManager.getCurrentScopeType().equals(MiracleEnvironmentManager.ScopeType.SCOPE_GLOBAL)) {
            throw new MiracleExceptionStatementScope("class", "global");
        }
        super.enterClassDeclarationStatement(ctx);
    }

    @Override
    public void enterForStatement(MiracleParser.ForStatementContext ctx) {
        if (!MiracleEnvironmentManager.inScope(MiracleEnvironmentManager.ScopeType.SCOPE_FUNC)) {
            throw new MiracleExceptionStatementScope("for-loop", "function");
        }
        super.enterForStatement(ctx);
    }

    @Override
    public void enterWhileStatement(MiracleParser.WhileStatementContext ctx) {
        if (!MiracleEnvironmentManager.inScope(MiracleEnvironmentManager.ScopeType.SCOPE_FUNC)) {
            throw new MiracleExceptionStatementScope("while-loop", "function");
        }
        super.enterWhileStatement(ctx);
    }


    @Override
    public void enterSelectionStatement(MiracleParser.SelectionStatementContext ctx) {
        if (!MiracleEnvironmentManager.inScope(MiracleEnvironmentManager.ScopeType.SCOPE_FUNC)) {
            if (ctx.statement(1) != null) {
                throw new MiracleExceptionStatementScope("if-else", "function");
            } else {
                throw new MiracleExceptionStatementScope("if", "function");
            }
        }
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
