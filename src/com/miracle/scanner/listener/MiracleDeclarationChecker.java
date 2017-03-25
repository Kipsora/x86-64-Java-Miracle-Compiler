package com.miracle.scanner.listener;

import com.miracle.cstree.MiracleParser;
import com.miracle.scanner.environment.MiracleIdentifierClass;
import com.miracle.scanner.environment.MiracleIdentifierFunction;
import com.miracle.scanner.environment.MiracleIdentifierVariable;
import com.miracle.scanner.environment.MiracleMutableEnvironmentManager;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.LinkedList;
import java.util.List;

public class MiracleDeclarationChecker extends MiracleScopeChecker {
    public MiracleDeclarationChecker() {
        super(new MiracleMutableEnvironmentManager());
    }

    @Override
    public void enterVariableDeclarationStatement(MiracleParser.VariableDeclarationStatementContext ctx) {
        if (ctx.expression() == null) {
            MiracleMutableEnvironmentManager.declare(ctx.IDENTIFIER().getText(),
                    new MiracleIdentifierVariable(ctx.typename().getText(), null));
        } else {
            MiracleMutableEnvironmentManager.declare(ctx.IDENTIFIER().getText(),
                    new MiracleIdentifierVariable(ctx.typename().getText(), ctx.expression().getText()));
        }
        super.enterVariableDeclarationStatement(ctx);
    }

    @Override
    public void enterClassDeclarationStatement(MiracleParser.ClassDeclarationStatementContext ctx) {
        if (ctx.IDENTIFIER(1) == null) {
            MiracleMutableEnvironmentManager.declare(ctx.IDENTIFIER(0).getText(),
                    new MiracleIdentifierClass(null));
        } else {
            MiracleMutableEnvironmentManager.declare(ctx.IDENTIFIER(0).getText(),
                    new MiracleIdentifierClass(ctx.IDENTIFIER(1).getText()));
        }
        super.enterClassDeclarationStatement(ctx);
    }

    @Override
    public void exitFunctionDeclarationStatement(MiracleParser.FunctionDeclarationStatementContext ctx) {
        List<TerminalNode> tmp = ctx.IDENTIFIER();
        LinkedList<MiracleIdentifierVariable> arguments = new LinkedList<>();
        for (int i = 1; i < tmp.size(); i++) {
            arguments.add(new MiracleIdentifierVariable(tmp.get(i).getText()));
        }
        MiracleMutableEnvironmentManager.declare(ctx.IDENTIFIER(0).getText(),
                new MiracleIdentifierFunction(ctx.typename(0).getText(), arguments));
        super.exitFunctionDeclarationStatement(ctx);
    }
}
