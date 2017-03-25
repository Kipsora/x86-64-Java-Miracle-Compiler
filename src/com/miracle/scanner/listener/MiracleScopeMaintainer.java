package com.miracle.scanner.listener;

import com.miracle.cstree.MiracleBaseListener;
import com.miracle.cstree.MiracleParser;
import com.miracle.scanner.environment.MiracleEnvironmentManager;

public class MiracleScopeMaintainer extends MiracleBaseListener{
    private final MiracleEnvironmentManager environment;

    MiracleScopeMaintainer(MiracleEnvironmentManager environment) {
        this.environment = environment;
    }

    @Override
    public void enterMiracle(MiracleParser.MiracleContext ctx) {
        this.environment.initScope();
    }

    @Override
    public void exitMiracle(MiracleParser.MiracleContext ctx) {
        this.environment.exitScope();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        this.environment.exitScope();
    }

    @Override
    public void enterClassDeclarationStatement(MiracleParser.ClassDeclarationStatementContext ctx) {
        environment.enterScope(MiracleEnvironmentManager.ScopeType.SCOPE_CLASS);
    }

    @Override
    public void exitClassDeclarationStatement(MiracleParser.ClassDeclarationStatementContext ctx) {
        environment.exitScope();
    }

    @Override
    public void enterForStatement(MiracleParser.ForStatementContext ctx) {
        environment.enterScope(MiracleEnvironmentManager.ScopeType.SCOPE_ITER);
    }

    @Override
    public void exitForStatement(MiracleParser.ForStatementContext ctx) {
        environment.exitScope();
    }

    @Override
    public void enterWhileStatement(MiracleParser.WhileStatementContext ctx) {
        environment.enterScope(MiracleEnvironmentManager.ScopeType.SCOPE_ITER);
    }

    @Override
    public void exitWhileStatement(MiracleParser.WhileStatementContext ctx) {
        environment.exitScope();
    }

    @Override
    public void enterVariableDeclarationStatement(MiracleParser.VariableDeclarationStatementContext ctx) {
        environment.enterScope(MiracleEnvironmentManager.ScopeType.SCOPE_VAR);
    }

    @Override
    public void exitVariableDeclarationStatement(MiracleParser.VariableDeclarationStatementContext ctx) {
        environment.exitScope();
    }

    @Override
    public void enterBlockStatement(MiracleParser.BlockStatementContext ctx) {
        environment.enterScope(MiracleEnvironmentManager.ScopeType.SCOPE_BLOCK);
    }

    @Override
    public void exitBlockStatement(MiracleParser.BlockStatementContext ctx) {
        environment.exitScope();
    }

    @Override
    public void enterFunctionDeclarationStatement(MiracleParser.FunctionDeclarationStatementContext ctx) {
        environment.enterScope(MiracleEnvironmentManager.ScopeType.SCOPE_FUNC);
    }

    @Override
    public void exitFunctionDeclarationStatement(MiracleParser.FunctionDeclarationStatementContext ctx) {
        environment.exitScope();
    }
}
