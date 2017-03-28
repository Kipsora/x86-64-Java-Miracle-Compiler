package com.miracle.scanner.listener;

import com.miracle.astree.MiracleASTree;
import com.miracle.astree.node.MiracleASTreeNode;
import com.miracle.astree.node.MiracleASTreeRoot;
import com.miracle.astree.node.MiracleASTreeTypename;
import com.miracle.astree.node.expression.MiracleASTreeExpression;
import com.miracle.astree.node.expression.value.MiracleASTreeConstant;
import com.miracle.astree.node.expression.value.MiracleASTreeVariable;
import com.miracle.astree.node.statement.MiracleASTreeBlock;
import com.miracle.astree.node.statement.MiracleASTreeSelection;
import com.miracle.astree.node.statement.MiracleASTreeStatement;
import com.miracle.astree.node.statement.control.MiracleASTreeBreak;
import com.miracle.astree.node.statement.control.MiracleASTreeContinue;
import com.miracle.astree.node.statement.control.MiracleASTreeReturn;
import com.miracle.astree.node.statement.declaration.MiracleASTreeClassDeclaration;
import com.miracle.astree.node.statement.declaration.MiracleASTreeFunctionDeclaration;
import com.miracle.astree.node.statement.declaration.MiracleASTreeMemberDeclaration;
import com.miracle.astree.node.statement.declaration.MiracleASTreeVariableDeclaration;
import com.miracle.astree.node.statement.iteration.MiracleASTreeFor;
import com.miracle.astree.node.statement.iteration.MiracleASTreeWhile;
import com.miracle.cstree.MiracleParser;
import com.miracle.scanner.environment.identifier.MiracleIdentifierVariable;
import com.miracle.scanner.environment.manager.MiracleEnvironmentReader;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class MiracleASTreeBuilder extends MiracleScopeChecker {
    public MiracleASTreeBuilder() {
        super(new MiracleEnvironmentReader());
        path.push(new LinkedList<>());
    }

    private Stack<List<MiracleASTreeNode>> path = new Stack<>();

    public MiracleASTree getTree() {
        return new MiracleASTree(new MiracleASTreeRoot(path.peek()));
    }

    @Override
    public void enterClassDeclarationStatement(MiracleParser.ClassDeclarationStatementContext ctx) {
        super.enterClassDeclarationStatement(ctx);
        path.push(new LinkedList<>());
    }

    @Override
    public void exitClassDeclarationStatement(MiracleParser.ClassDeclarationStatementContext ctx) {
        LinkedList<MiracleASTreeMemberDeclaration> tmp = new LinkedList<>();
        for (MiracleASTreeNode entry : path.pop()) {
            tmp.add((MiracleASTreeMemberDeclaration) entry);
        }
        if (ctx.IDENTIFIER().size() > 1) {
            path.peek().add(new MiracleASTreeClassDeclaration(ctx.IDENTIFIER(0).getText(),
                    ctx.IDENTIFIER(1).getText(), tmp));
        } else {
            path.peek().add(new MiracleASTreeClassDeclaration(ctx.IDENTIFIER(0).getText(),
                    null, tmp));
        }
        super.exitClassDeclarationStatement(ctx);
    }

    @Override
    public void enterFunctionDeclarationStatement(MiracleParser.FunctionDeclarationStatementContext ctx) {
        super.enterFunctionDeclarationStatement(ctx);
        path.push(new LinkedList<>());
    }

    @Override
    public void exitFunctionDeclarationStatement(MiracleParser.FunctionDeclarationStatementContext ctx) {
        String decorator = null;
        if (ctx.DECORATOR() != null) {
            decorator = ctx.DECORATOR().getText();
        }
        List<MiracleASTreeNode> children = path.pop();
        List<MiracleASTreeVariableDeclaration> arguments = new LinkedList<>();
        List<MiracleASTreeStatement> body = new LinkedList<>();
        MiracleASTreeTypename type = (MiracleASTreeTypename) children.get(0);
        for (int i = 1; i < children.size(); i++) {
            if (i < ctx.IDENTIFIER().size()) {
                arguments.add(new MiracleASTreeVariableDeclaration(ctx.IDENTIFIER(i).getText(),
                        (MiracleASTreeTypename) children.get(i)));
            } else {
                body.add((MiracleASTreeStatement) children.get(i));
            }
        }
        path.peek().add(new MiracleASTreeFunctionDeclaration(decorator, type,
                ctx.IDENTIFIER(0).getText(), arguments, body));
        super.exitFunctionDeclarationStatement(ctx);
    }

    @Override
    public void enterVariableDeclarationStatement(MiracleParser.VariableDeclarationStatementContext ctx) {
        super.enterVariableDeclarationStatement(ctx);
        path.push(new LinkedList<>());
    }

    @Override
    public void exitVariableDeclarationStatement(MiracleParser.VariableDeclarationStatementContext ctx) {
        String decorator = null;
        if (ctx.DECORATOR() != null) {
            decorator = ctx.DECORATOR().getText();
        }
        MiracleASTreeTypename type = (MiracleASTreeTypename) path.peek().get(0);
        MiracleASTreeExpression value = null;
        if (path.peek().size() > 1) {
            value = (MiracleASTreeExpression) path.peek().get(1);
        }
        path.pop();
        path.peek().add(new MiracleASTreeVariableDeclaration(decorator, ctx.IDENTIFIER().getText(), type, value));
        super.exitVariableDeclarationStatement(ctx);
        if (ctx.DECORATOR() != null) {
            MiracleEnvironmentReader.declare(ctx.IDENTIFIER().getText(),
                    new MiracleIdentifierVariable(ctx.DECORATOR().getText(), true, type));
        } else {
            MiracleEnvironmentReader.declare(ctx.IDENTIFIER().getText(),
                    new MiracleIdentifierVariable(null, true, type));
        }
    }

    @Override
    public void enterBlockStatement(MiracleParser.BlockStatementContext ctx) {
        super.enterBlockStatement(ctx);
        path.push(new LinkedList<>());
    }

    @Override
    public void exitBlockStatement(MiracleParser.BlockStatementContext ctx) {
        LinkedList<MiracleASTreeStatement> statement = new LinkedList<>();
        for (MiracleASTreeNode entry : path.pop()) {
            statement.add((MiracleASTreeStatement) (entry));
        }
        path.pop();
        path.peek().add(new MiracleASTreeBlock(statement));
        super.exitBlockStatement(ctx);
    }

    @Override
    public void enterSelectionStatement(MiracleParser.SelectionStatementContext ctx) {
        super.enterSelectionStatement(ctx);
        path.push(new LinkedList<>());
    }

    @Override
    public void exitSelectionStatement(MiracleParser.SelectionStatementContext ctx) {
        List<MiracleASTreeNode> children = path.pop();
        if (children.size() > 2) {
            path.peek().add(new MiracleASTreeSelection((MiracleASTreeExpression) children.get(0),
                    (MiracleASTreeStatement) children.get(1), (MiracleASTreeStatement) children.get(2)));
        } else {
            path.peek().add(new MiracleASTreeSelection((MiracleASTreeExpression) children.get(0),
                    (MiracleASTreeStatement) children.get(1), null));
        }
        super.exitSelectionStatement(ctx);
    }

    @Override
    public void enterForStatement(MiracleParser.ForStatementContext ctx) {
        super.enterForStatement(ctx);
        path.push(new LinkedList<>());
    }

    @Override
    public void exitForStatement(MiracleParser.ForStatementContext ctx) {
        List<MiracleASTreeNode> children = path.pop();
        MiracleASTreeExpression[] node = new MiracleASTreeExpression[3];
        MiracleASTreeStatement statement = null;
        int consumed = 0;
        for (int i = 0, j = 0; i < ctx.getChildCount(); i++) {
            if (ctx.getChild(i).getText().equals(";") || ctx.getChild(i).getText().equals(")")) {
                if (!ctx.getChild(i - 1).getText().equals("(") && !ctx.getChild(i).getText().equals(";")) {
                    node[j] = (MiracleASTreeExpression) children.get(consumed++);
                }
                j++;
            }
            if (ctx.getChild(i).getText().equals(")")) {
                statement = (MiracleASTreeStatement) children.get(consumed++);
            }
        }
        assert statement != null;
        path.peek().add(new MiracleASTreeFor(node[0], node[1], node[2], statement));
        super.exitForStatement(ctx);
    }

    @Override
    public void enterWhileStatement(MiracleParser.WhileStatementContext ctx) {
        super.enterWhileStatement(ctx);
        path.push(new LinkedList<>());
    }

    @Override
    public void exitWhileStatement(MiracleParser.WhileStatementContext ctx) {
        List<MiracleASTreeNode> children = path.pop();
        MiracleASTreeExpression expression = (MiracleASTreeExpression) children.get(0);
        MiracleASTreeStatement statement = (MiracleASTreeStatement) children.get(1);
        path.peek().add(new MiracleASTreeWhile(expression, statement));
        super.exitWhileStatement(ctx);
    }

    @Override
    public void enterContinueStatement(MiracleParser.ContinueStatementContext ctx) {
        super.enterContinueStatement(ctx);
        path.push(new LinkedList<>());
    }

    @Override
    public void exitContinueStatement(MiracleParser.ContinueStatementContext ctx) {
        path.pop();
        path.peek().add(new MiracleASTreeContinue());
        super.exitContinueStatement(ctx);
    }

    @Override
    public void enterBreakStatement(MiracleParser.BreakStatementContext ctx) {
        super.enterBreakStatement(ctx);
        path.push(new LinkedList<>());
    }

    @Override
    public void exitBreakStatement(MiracleParser.BreakStatementContext ctx) {
        path.pop();
        path.peek().add(new MiracleASTreeBreak());
        super.exitBreakStatement(ctx);
    }

    @Override
    public void enterReturnStatement(MiracleParser.ReturnStatementContext ctx) {
        super.enterReturnStatement(ctx);
        path.push(new LinkedList<>());
    }

    @Override
    public void exitReturnStatement(MiracleParser.ReturnStatementContext ctx) {
        List<MiracleASTreeNode> children = path.pop();
        path.peek().add(new MiracleASTreeReturn((MiracleASTreeExpression) children.get(0)));
        super.exitReturnStatement(ctx);
    }

    @Override
    public void enterTypename(MiracleParser.TypenameContext ctx) {
        super.enterTypename(ctx);
        path.push(new LinkedList<>());
    }

    @Override
    public void exitTypename(MiracleParser.TypenameContext ctx) {
        List<MiracleASTreeNode> children = path.pop();
        if (ctx.getChildCount() == 1) {
            path.peek().add(new MiracleASTreeTypename(ctx.getChild(0).getText()));
        }
        if (ctx.IDENTIFIER() != null) {

        }
        super.exitTypename(ctx);
    }

    @Override
    public void enterConstant(MiracleParser.ConstantContext ctx) {
        super.enterConstant(ctx);
        path.push(new LinkedList<>());
    }

    @Override
    public void exitConstant(MiracleParser.ConstantContext ctx) {
        path.pop();
        if (ctx.INTEGER() != null) {
            path.peek().add(new MiracleASTreeConstant(new MiracleASTreeTypename("int"),
                    ctx.INTEGER().getText()));
        } else if (ctx.STRING() != null) {
            path.peek().add(new MiracleASTreeConstant(new MiracleASTreeTypename("string"),
                    ctx.STRING().getText()));
        } else if (ctx.getText().equals("null")) {
            path.peek().add(new MiracleASTreeConstant(new MiracleASTreeTypename("emptyobj"),
                    ctx.STRING().getText()));
        } else {
            path.peek().add(new MiracleASTreeConstant(new MiracleASTreeTypename("boolean"),
                    ctx.STRING().getText()));
        }
        super.exitConstant(ctx);
    }

    @Override
    public void enterVariableExpression(MiracleParser.VariableExpressionContext ctx) {
        super.enterVariableExpression(ctx);
        path.push(new LinkedList<>());
    }

    @Override
    public void exitVariableExpression(MiracleParser.VariableExpressionContext ctx) {
        path.pop();
        path.peek().add(new MiracleASTreeVariable());
        super.exitVariableExpression(ctx);
    }
}
