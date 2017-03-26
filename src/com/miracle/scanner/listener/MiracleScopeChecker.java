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

    @Override
    public void enterBlockStatement(MiracleParser.BlockStatementContext ctx) {
        if (!MiracleEnvironmentManager.inScope(MiracleEnvironmentManager.ScopeType.SCOPE_FUNC)
                && !MiracleEnvironmentManager.getCurrentScopeType().equals(MiracleEnvironmentManager.ScopeType.SCOPE_CLASS)) {
            throw new MiracleExceptionStatement("block");
        }
        super.enterBlockStatement(ctx);
    }

    @Override
    public void enterConstantExpression(MiracleParser.ConstantExpressionContext ctx) {
        if (!MiracleEnvironmentManager.inScope(MiracleEnvironmentManager.ScopeType.SCOPE_FUNC)
                && !MiracleEnvironmentManager.getCurrentScopeType().equals(MiracleEnvironmentManager.ScopeType.SCOPE_VAR)) {
            /**
             * TODO: ADD DETAILED EXPLANATION HERE
             */
            throw new MiracleExceptionStatement("expression");
        }
    }

    @Override
    public void enterVariableExpression(MiracleParser.VariableExpressionContext ctx) {
        if (!MiracleEnvironmentManager.inScope(MiracleEnvironmentManager.ScopeType.SCOPE_FUNC)
                && !MiracleEnvironmentManager.getCurrentScopeType().equals(MiracleEnvironmentManager.ScopeType.SCOPE_VAR)) {
            /**
             * TODO: ADD DETAILED EXPLANATION HERE
             */
            throw new MiracleExceptionStatement("expression");
        }
    }

    @Override
    public void enterBraceExpression(MiracleParser.BraceExpressionContext ctx) {
        if (!MiracleEnvironmentManager.inScope(MiracleEnvironmentManager.ScopeType.SCOPE_FUNC)
                && !MiracleEnvironmentManager.getCurrentScopeType().equals(MiracleEnvironmentManager.ScopeType.SCOPE_VAR)) {
            /**
             * TODO: ADD DETAILED EXPLANATION HERE
             */
            throw new MiracleExceptionStatement("expression");
        }
    }

    @Override
    public void enterFunctionCallExpression(MiracleParser.FunctionCallExpressionContext ctx) {
        if (!MiracleEnvironmentManager.inScope(MiracleEnvironmentManager.ScopeType.SCOPE_FUNC)
                && !MiracleEnvironmentManager.getCurrentScopeType().equals(MiracleEnvironmentManager.ScopeType.SCOPE_VAR)) {
            /**
             * TODO: ADD DETAILED EXPLANATION HERE
             */
            throw new MiracleExceptionStatement("expression");
        }
    }

    @Override
    public void enterSubscriptExpression(MiracleParser.SubscriptExpressionContext ctx) {
        if (!MiracleEnvironmentManager.inScope(MiracleEnvironmentManager.ScopeType.SCOPE_FUNC)
                && !MiracleEnvironmentManager.getCurrentScopeType().equals(MiracleEnvironmentManager.ScopeType.SCOPE_VAR)) {
            /**
             * TODO: ADD DETAILED EXPLANATION HERE
             */
            throw new MiracleExceptionStatement("expression");
        }
    }

    @Override
    public void enterMemberExpression(MiracleParser.MemberExpressionContext ctx) {
        if (!MiracleEnvironmentManager.inScope(MiracleEnvironmentManager.ScopeType.SCOPE_FUNC)
                && !MiracleEnvironmentManager.getCurrentScopeType().equals(MiracleEnvironmentManager.ScopeType.SCOPE_VAR)) {
            /**
             * TODO: ADD DETAILED EXPLANATION HERE
             */
            throw new MiracleExceptionStatement("expression");
        }
    }

    @Override
    public void enterSuffixExpression(MiracleParser.SuffixExpressionContext ctx) {
        if (!MiracleEnvironmentManager.inScope(MiracleEnvironmentManager.ScopeType.SCOPE_FUNC)
                && !MiracleEnvironmentManager.getCurrentScopeType().equals(MiracleEnvironmentManager.ScopeType.SCOPE_VAR)) {
            /**
             * TODO: ADD DETAILED EXPLANATION HERE
             */
            throw new MiracleExceptionStatement("expression");
        }
    }

    @Override
    public void enterUnaryExpression(MiracleParser.UnaryExpressionContext ctx) {
        if (!MiracleEnvironmentManager.inScope(MiracleEnvironmentManager.ScopeType.SCOPE_FUNC)
                && !MiracleEnvironmentManager.getCurrentScopeType().equals(MiracleEnvironmentManager.ScopeType.SCOPE_VAR)) {
            /**
             * TODO: ADD DETAILED EXPLANATION HERE
             */
            throw new MiracleExceptionStatement("expression");
        }
    }

    @Override
    public void enterNewExpression(MiracleParser.NewExpressionContext ctx) {
        if (!MiracleEnvironmentManager.inScope(MiracleEnvironmentManager.ScopeType.SCOPE_FUNC)
                && !MiracleEnvironmentManager.getCurrentScopeType().equals(MiracleEnvironmentManager.ScopeType.SCOPE_VAR)) {
            /**
             * TODO: ADD DETAILED EXPLANATION HERE
             */
            throw new MiracleExceptionStatement("expression");
        }
    }

    @Override
    public void enterMultDivExpression(MiracleParser.MultDivExpressionContext ctx) {
        if (!MiracleEnvironmentManager.inScope(MiracleEnvironmentManager.ScopeType.SCOPE_FUNC)
                && !MiracleEnvironmentManager.getCurrentScopeType().equals(MiracleEnvironmentManager.ScopeType.SCOPE_VAR)) {
            /**
             * TODO: ADD DETAILED EXPLANATION HERE
             */
            throw new MiracleExceptionStatement("expression");
        }
    }

    @Override
    public void enterAddSubExpression(MiracleParser.AddSubExpressionContext ctx) {
        if (!MiracleEnvironmentManager.inScope(MiracleEnvironmentManager.ScopeType.SCOPE_FUNC)
                && !MiracleEnvironmentManager.getCurrentScopeType().equals(MiracleEnvironmentManager.ScopeType.SCOPE_VAR)) {
            /**
             * TODO: ADD DETAILED EXPLANATION HERE
             */
            throw new MiracleExceptionStatement("expression");
        }
    }

    @Override
    public void enterShlShrExpression(MiracleParser.ShlShrExpressionContext ctx) {
        if (!MiracleEnvironmentManager.inScope(MiracleEnvironmentManager.ScopeType.SCOPE_FUNC)
                && !MiracleEnvironmentManager.getCurrentScopeType().equals(MiracleEnvironmentManager.ScopeType.SCOPE_VAR)) {
            /**
             * TODO: ADD DETAILED EXPLANATION HERE
             */
            throw new MiracleExceptionStatement("expression");
        }
    }

    @Override
    public void enterCompareExpression(MiracleParser.CompareExpressionContext ctx) {
        if (!MiracleEnvironmentManager.inScope(MiracleEnvironmentManager.ScopeType.SCOPE_FUNC)
                && !MiracleEnvironmentManager.getCurrentScopeType().equals(MiracleEnvironmentManager.ScopeType.SCOPE_VAR)) {
            /**
             * TODO: ADD DETAILED EXPLANATION HERE
             */
            throw new MiracleExceptionStatement("expression");
        }
    }

    @Override
    public void enterEqualityExpression(MiracleParser.EqualityExpressionContext ctx) {
        if (!MiracleEnvironmentManager.inScope(MiracleEnvironmentManager.ScopeType.SCOPE_FUNC)
                && !MiracleEnvironmentManager.getCurrentScopeType().equals(MiracleEnvironmentManager.ScopeType.SCOPE_VAR)) {
            /**
             * TODO: ADD DETAILED EXPLANATION HERE
             */
            throw new MiracleExceptionStatement("expression");
        }
    }

    @Override
    public void enterAndExpression(MiracleParser.AndExpressionContext ctx) {
        if (!MiracleEnvironmentManager.inScope(MiracleEnvironmentManager.ScopeType.SCOPE_FUNC)
                && !MiracleEnvironmentManager.getCurrentScopeType().equals(MiracleEnvironmentManager.ScopeType.SCOPE_VAR)) {
            /**
             * TODO: ADD DETAILED EXPLANATION HERE
             */
            throw new MiracleExceptionStatement("expression");
        }
    }

    @Override
    public void enterXorExpression(MiracleParser.XorExpressionContext ctx) {
        if (!MiracleEnvironmentManager.inScope(MiracleEnvironmentManager.ScopeType.SCOPE_FUNC)
                && !MiracleEnvironmentManager.getCurrentScopeType().equals(MiracleEnvironmentManager.ScopeType.SCOPE_VAR)) {
            throw new MiracleExceptionStatement("\"^\" expression");
        }
    }

    @Override
    public void enterOrExpression(MiracleParser.OrExpressionContext ctx) {
        if (!MiracleEnvironmentManager.inScope(MiracleEnvironmentManager.ScopeType.SCOPE_FUNC)
                && !MiracleEnvironmentManager.getCurrentScopeType().equals(MiracleEnvironmentManager.ScopeType.SCOPE_VAR)) {
            throw new MiracleExceptionStatement("\"+\" expression");
        }
    }

    @Override
    public void enterLogicAndExpression(MiracleParser.LogicAndExpressionContext ctx) {
        if (!MiracleEnvironmentManager.inScope(MiracleEnvironmentManager.ScopeType.SCOPE_FUNC)
                && !MiracleEnvironmentManager.getCurrentScopeType().equals(MiracleEnvironmentManager.ScopeType.SCOPE_VAR)) {
            throw new MiracleExceptionStatement("\"&&\" expression");
        }
    }

    @Override
    public void enterLogicOrExpression(MiracleParser.LogicOrExpressionContext ctx) {
        if (!MiracleEnvironmentManager.inScope(MiracleEnvironmentManager.ScopeType.SCOPE_FUNC)
                && !MiracleEnvironmentManager.getCurrentScopeType().equals(MiracleEnvironmentManager.ScopeType.SCOPE_VAR)) {
            throw new MiracleExceptionStatement("\"||\" expression");
        }
    }

    @Override
    public void enterAssignExpression(MiracleParser.AssignExpressionContext ctx) {
        if (!MiracleEnvironmentManager.inScope(MiracleEnvironmentManager.ScopeType.SCOPE_FUNC)) {
            throw new MiracleExceptionStatement("assignment expression");
        }
    }
}
