package com.miracle.scanner.listener;

import com.miracle.cstree.MiracleParser;
import com.miracle.scanner.environment.identifier.MiracleIdentifierClass;
import com.miracle.scanner.environment.identifier.MiracleIdentifierFunction;
import com.miracle.scanner.environment.identifier.MiracleIdentifierVariable;
import com.miracle.scanner.environment.manager.MiracleEnvironmentLoader;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.LinkedList;
import java.util.List;

public class MiracleDeclarationChecker extends MiracleScopeChecker {
    public MiracleDeclarationChecker() {
        super(new MiracleEnvironmentLoader());
    }

    @Override
    public void enterVariableDeclarationStatement(MiracleParser.VariableDeclarationStatementContext ctx) {
        if (ctx.DECORATOR() == null) {
            MiracleEnvironmentLoader.declare(ctx.IDENTIFIER().getText(),
                    new MiracleIdentifierVariable(null, true,
                            ctx.typename().getText()));
        } else {
            MiracleEnvironmentLoader.declare(ctx.IDENTIFIER().getText(),
                    new MiracleIdentifierVariable(ctx.DECORATOR().getText(), true,
                            ctx.typename().getText()));
        }
        super.enterVariableDeclarationStatement(ctx);
    }

    @Override
    public void enterClassDeclarationStatement(MiracleParser.ClassDeclarationStatementContext ctx) {
        if (ctx.IDENTIFIER(1) == null) {
            MiracleEnvironmentLoader.declare(ctx.IDENTIFIER(0).getText(),
                    new MiracleIdentifierClass(null));
        } else {
            /**
             * TODO: extension of a class should be inspected here.
             */
            MiracleEnvironmentLoader.declare(ctx.IDENTIFIER(0).getText(),
                    new MiracleIdentifierClass(ctx.IDENTIFIER(1).getText()));
        }
        super.enterClassDeclarationStatement(ctx);
    }

    @Override
    public void enterFunctionDeclarationStatement(MiracleParser.FunctionDeclarationStatementContext ctx) {
        List<TerminalNode> tmp = ctx.IDENTIFIER();
        LinkedList<MiracleIdentifierVariable> arguments = new LinkedList<>();
        for (int i = 1; i < tmp.size(); i++) {
            arguments.add(new MiracleIdentifierVariable(null, true, tmp.get(i).getText()));
        }
        MiracleEnvironmentLoader.declare(ctx.IDENTIFIER(0).getText(),
                new MiracleIdentifierFunction(ctx.typename(0).getText(), arguments));
        super.enterFunctionDeclarationStatement(ctx);
        for (int i = 1; i < tmp.size(); i++) {
            MiracleEnvironmentLoader.declare(tmp.get(i).getText(),
                    new MiracleIdentifierVariable(null, false, ctx.typename(i).getText()));
        }
    }
}
