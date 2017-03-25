package com.miracle.scanner.listener;

import com.miracle.cstree.MiracleParser;
import com.miracle.exceptions.MiracleExceptionStatementScope;
import com.miracle.exceptions.MiracleExceptionUndefinedIdentifier;
import com.miracle.scanner.scope.MiracleScope;
import org.antlr.v4.runtime.tree.TerminalNode;

public class MiracleExpressionListener extends MiracleScopeListener {
    @Override
    public void enterExpressionStatement(MiracleParser.ExpressionStatementContext ctx) {
        if (!MiracleScope.inScope(MiracleScope.ScopeType.SCOPE_FUNC) && !MiracleScope.inScope(MiracleScope.ScopeType.SCOPE_VAR)) {
            throw new MiracleExceptionStatementScope(-1, -1, "expression", "either function or variable declaration");
        }
    }

    @Override
    public void enterVariableExpression(MiracleParser.VariableExpressionContext ctx) {
        if (!MiracleScope.containsIdentifier(ctx.IDENTIFIER().toString())) {
            TerminalNode tmp = ctx.IDENTIFIER();
            throw new MiracleExceptionUndefinedIdentifier(tmp.getSymbol(), tmp.toString());
        }
    }

    @Override
    public void enterVariableDeclarationStatement(MiracleParser.VariableDeclarationStatementContext ctx) {
        super.enterVariableDeclarationStatement(ctx);
    }
}
