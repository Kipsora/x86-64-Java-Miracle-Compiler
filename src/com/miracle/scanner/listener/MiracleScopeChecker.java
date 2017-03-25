package com.miracle.scanner.listener;

import com.miracle.cstree.MiracleParser;
import com.miracle.exceptions.MiracleExceptionStatement;
import com.miracle.exceptions.MiracleExceptionStatementScope;
import com.miracle.scanner.environment.MiracleEnvironmentManager;

public class MiracleScopeChecker extends MiracleScopeMaintainer{
    public MiracleScopeChecker(MiracleEnvironmentManager environment) {
        super(environment);
    }

    @Override
    public void enterClassDeclarationStatement(MiracleParser.ClassDeclarationStatementContext ctx) {
        if (!MiracleEnvironmentManager.getCurrentScopeType().equals(MiracleEnvironmentManager.ScopeType.SCOPE_GLOBAL)) {
            throw new MiracleExceptionStatement(ctx.IDENTIFIER(0).getSymbol(), "class");
        }
        super.enterClassDeclarationStatement(ctx);
    }

    @Override
    public void enterForStatement(MiracleParser.ForStatementContext ctx) {
        if (!MiracleEnvironmentManager.inScope(MiracleEnvironmentManager.ScopeType.SCOPE_FUNC)) {
            throw new MiracleExceptionStatement(ctx.getToken(0, 0).getSymbol(), "for-loop");
        }
        super.enterForStatement(ctx);
    }

    @Override
    public void enterWhileStatement(MiracleParser.WhileStatementContext ctx) {
        if (!MiracleEnvironmentManager.inScope(MiracleEnvironmentManager.ScopeType.SCOPE_FUNC)) {
            throw new MiracleExceptionStatement(ctx.getToken(0, 0).getSymbol(), "while-loop");
        }
        super.enterWhileStatement(ctx);
    }


    @Override
    public void enterSelectionStatement(MiracleParser.SelectionStatementContext ctx) {
        if (!MiracleEnvironmentManager.inScope(MiracleEnvironmentManager.ScopeType.SCOPE_FUNC)) {
            if (ctx.statement(1) != null) {
                throw new MiracleExceptionStatement(ctx.getToken(0, 0).getSymbol(), "if-else");
            } else {
                throw new MiracleExceptionStatement(ctx.getToken(0, 0).getSymbol(), "if");
            }
        }
    }

    @Override
    public void enterContinueStatement(MiracleParser.ContinueStatementContext ctx) {
        if (!MiracleEnvironmentManager.inScope(MiracleEnvironmentManager.ScopeType.SCOPE_ITER)) {
            throw new MiracleExceptionStatementScope(ctx.getToken(0, 0).getSymbol(), "continue", "iteration");
        }
    }

    @Override
    public void enterBreakStatement(MiracleParser.BreakStatementContext ctx) {
        if (!MiracleEnvironmentManager.inScope(MiracleEnvironmentManager.ScopeType.SCOPE_ITER)) {
            throw new MiracleExceptionStatementScope(ctx.getToken(0, 0).getSymbol(), "break", "iteration");
        }
    }

    @Override
    public void enterReturnStatement(MiracleParser.ReturnStatementContext ctx) {
        if (!MiracleEnvironmentManager.inScope(MiracleEnvironmentManager.ScopeType.SCOPE_FUNC)) {
            throw new MiracleExceptionStatementScope(ctx.getToken(0, 0).getSymbol(), "return", "function");
        }
    }

    @Override
    public void enterBlockStatement(MiracleParser.BlockStatementContext ctx) {
        if (!MiracleEnvironmentManager.inScope(MiracleEnvironmentManager.ScopeType.SCOPE_FUNC)
                && !MiracleEnvironmentManager.getCurrentScopeType().equals(MiracleEnvironmentManager.ScopeType.SCOPE_CLASS)) {
            throw new MiracleExceptionStatement(ctx.getToken(0, 0).getSymbol(), "block");
        }
        super.enterBlockStatement(ctx);
    }


    @Override
    public void enterConstantExpression(MiracleParser.ConstantExpressionContext ctx) {
        if (!MiracleEnvironmentManager.inScope(MiracleEnvironmentManager.ScopeType.SCOPE_FUNC)
                && !MiracleEnvironmentManager.getCurrentScopeType().equals(MiracleEnvironmentManager.ScopeType.SCOPE_VAR)) {
            throw new MiracleExceptionStatement(ctx.getToken(0, 0).getSymbol(), "expression");
        }
    }

    @Override
    public void enterVariableExpression(MiracleParser.VariableExpressionContext ctx) {
        if (!MiracleEnvironmentManager.inScope(MiracleEnvironmentManager.ScopeType.SCOPE_FUNC)
                && !MiracleEnvironmentManager.getCurrentScopeType().equals(MiracleEnvironmentManager.ScopeType.SCOPE_VAR)) {
            throw new MiracleExceptionStatement(ctx.getToken(0, 0).getSymbol(), "expression");
        }
    }

    @Override
    public void enterBraceExpression(MiracleParser.BraceExpressionContext ctx) {
        if (!MiracleEnvironmentManager.inScope(MiracleEnvironmentManager.ScopeType.SCOPE_FUNC)
                && !MiracleEnvironmentManager.getCurrentScopeType().equals(MiracleEnvironmentManager.ScopeType.SCOPE_VAR)) {
            throw new MiracleExceptionStatement(ctx.getToken(0, 0).getSymbol(), "expression");
        }
    }

    @Override
    public void enterFunctionCallExpression(MiracleParser.FunctionCallExpressionContext ctx) {
        if (!MiracleEnvironmentManager.inScope(MiracleEnvironmentManager.ScopeType.SCOPE_FUNC)
                && !MiracleEnvironmentManager.getCurrentScopeType().equals(MiracleEnvironmentManager.ScopeType.SCOPE_VAR)) {
            throw new MiracleExceptionStatement(ctx.getToken(0, 0).getSymbol(), "expression");
        }
    }

    @Override
    public void enterSubscriptExpression(MiracleParser.SubscriptExpressionContext ctx) {
        if (!MiracleEnvironmentManager.inScope(MiracleEnvironmentManager.ScopeType.SCOPE_FUNC)
                && !MiracleEnvironmentManager.getCurrentScopeType().equals(MiracleEnvironmentManager.ScopeType.SCOPE_VAR)) {
            throw new MiracleExceptionStatement(ctx.getToken(0, 0).getSymbol(), "expression");
        }
    }

    @Override
    public void enterMemberExpression(MiracleParser.MemberExpressionContext ctx) {
        if (!MiracleEnvironmentManager.inScope(MiracleEnvironmentManager.ScopeType.SCOPE_FUNC)
                && !MiracleEnvironmentManager.getCurrentScopeType().equals(MiracleEnvironmentManager.ScopeType.SCOPE_VAR)) {
            throw new MiracleExceptionStatement(ctx.getToken(0, 0).getSymbol(), "expression");
        }
    }

    @Override
    public void enterSuffixExpression(MiracleParser.SuffixExpressionContext ctx) {
        if (!MiracleEnvironmentManager.inScope(MiracleEnvironmentManager.ScopeType.SCOPE_FUNC)
                && !MiracleEnvironmentManager.getCurrentScopeType().equals(MiracleEnvironmentManager.ScopeType.SCOPE_VAR)) {
            throw new MiracleExceptionStatement(ctx.getToken(0, 0).getSymbol(), "expression");
        }
    }

    @Override
    public void enterUnaryExpression(MiracleParser.UnaryExpressionContext ctx) {
        if (!MiracleEnvironmentManager.inScope(MiracleEnvironmentManager.ScopeType.SCOPE_FUNC)
                && !MiracleEnvironmentManager.getCurrentScopeType().equals(MiracleEnvironmentManager.ScopeType.SCOPE_VAR)) {
            throw new MiracleExceptionStatement(ctx.getToken(0, 0).getSymbol(), "expression");
        }
    }

    @Override
    public void enterNewExpression(MiracleParser.NewExpressionContext ctx) {
        if (!MiracleEnvironmentManager.inScope(MiracleEnvironmentManager.ScopeType.SCOPE_FUNC)
                && !MiracleEnvironmentManager.getCurrentScopeType().equals(MiracleEnvironmentManager.ScopeType.SCOPE_VAR)) {
            throw new MiracleExceptionStatement(ctx.getToken(0, 0).getSymbol(), "expression");
        }
    }

    @Override
    public void enterMultDivExpression(MiracleParser.MultDivExpressionContext ctx) {
        if (!MiracleEnvironmentManager.inScope(MiracleEnvironmentManager.ScopeType.SCOPE_FUNC)
                && !MiracleEnvironmentManager.getCurrentScopeType().equals(MiracleEnvironmentManager.ScopeType.SCOPE_VAR)) {
            throw new MiracleExceptionStatement(ctx.getToken(0, 0).getSymbol(), "expression");
        }
    }

    @Override
    public void exitFunctionDeclarationStatement(MiracleParser.FunctionDeclarationStatementContext ctx) {
        if (MiracleEnvironmentManager.inScope(MiracleEnvironmentManager.ScopeType.SCOPE_BLOCK)) {
            throw new MiracleExceptionStatement(ctx.getToken(0, 0).getSymbol(), "function declaration");
        }
        super.exitFunctionDeclarationStatement(ctx);
    }
}
