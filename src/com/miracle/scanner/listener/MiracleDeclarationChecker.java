package com.miracle.scanner.listener;

import com.miracle.cstree.MiracleParser;
import com.miracle.scanner.environment.manager.MiracleEnvironmentLoader;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

public class MiracleDeclarationChecker extends MiracleScopeChecker {
    public MiracleDeclarationChecker() {
        super(new MiracleEnvironmentLoader());
    }

    @Override
    public void enterVariableDeclarationStatement(MiracleParser.VariableDeclarationStatementContext ctx) {
        MiracleEnvironmentLoader.declareVariable(ctx.IDENTIFIER().getText());
        super.enterVariableDeclarationStatement(ctx);
    }

    @Override
    public void enterClassDeclarationStatement(MiracleParser.ClassDeclarationStatementContext ctx) {
        MiracleEnvironmentLoader.declareClass(ctx.IDENTIFIER(0).getText());
        super.enterClassDeclarationStatement(ctx);
    }

    @Override
    public void enterFunctionDeclarationStatement(MiracleParser.FunctionDeclarationStatementContext ctx) {
        List<TerminalNode> tmp = ctx.IDENTIFIER();
        MiracleEnvironmentLoader.declareFunction(ctx.IDENTIFIER(0).getText());
        super.enterFunctionDeclarationStatement(ctx);
        for (int i = 1; i < tmp.size(); i++) {
            MiracleEnvironmentLoader.declareVariable(tmp.get(i).getText());
        }
    }
}
