package com.miracle.scanner.listener;

import com.miracle.astree.MiracleASTree;
import com.miracle.astree.node.MiracleASTreeNode;
import com.miracle.astree.node.MiracleASTreeRoot;
import com.miracle.astree.node.expression.MiracleASTreeExpression;
import com.miracle.astree.node.expression.MiracleASTreeExpressionFactory;
import com.miracle.astree.node.expression.multiary.MiracleASTreeCallExpression;
import com.miracle.astree.node.expression.multiary.MiracleASTreeNewExpression;
import com.miracle.astree.node.expression.value.MiracleASTreeConstant;
import com.miracle.astree.node.expression.value.MiracleASTreeFunction;
import com.miracle.astree.node.statement.MiracleASTreeBlock;
import com.miracle.astree.node.statement.MiracleASTreeSelection;
import com.miracle.astree.node.statement.MiracleASTreeStatement;
import com.miracle.astree.node.statement.control.MiracleASTreeBreak;
import com.miracle.astree.node.statement.control.MiracleASTreeContinue;
import com.miracle.astree.node.statement.control.MiracleASTreeReturn;
import com.miracle.astree.node.statement.declaration.*;
import com.miracle.astree.node.statement.iteration.MiracleASTreeFor;
import com.miracle.astree.node.statement.iteration.MiracleASTreeWhile;
import com.miracle.cstree.MiracleParser;
import com.miracle.exceptions.MiracleExceptionArguments;
import com.miracle.exceptions.MiracleExceptionCallVariable;
import com.miracle.exceptions.MiracleExceptionMember;
import com.miracle.exceptions.MiracleExceptionUndefinedIdentifier;
import com.miracle.scanner.environment.MiracleEnvironmentReader;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class MiracleASTreeBuilder extends MiracleScopeChecker {
    private Stack<List<MiracleASTreeNode>> path = new Stack<>();

    public MiracleASTreeBuilder() {
        super(new MiracleEnvironmentReader());
        path.push(new LinkedList<>());
    }

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
        MiracleASTreeVariableDeclaration node = new MiracleASTreeVariableDeclaration(decorator,
                ctx.IDENTIFIER().getText(), type, value);
        path.peek().add(node);
        super.exitVariableDeclarationStatement(ctx);
        MiracleEnvironmentReader.declare(ctx.IDENTIFIER().getText(), true, node);
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
        for (int i = 0, j = 0; i < ctx.getChildCount() && j < 3; i++) {
            if (ctx.getChild(i).getText().equals(";") || ctx.getChild(i).getText().equals(")")) {
                if (!ctx.getChild(i - 1).getText().equals("(") && !ctx.getChild(i - 1).getText().equals(";")
                        && ctx.getChild(i).getText().equals(";")) {
                    System.out.println(ctx.getChild(i - 1).getText() + " " + ctx.getChild(i).getText());
                    node[j] = (MiracleASTreeExpression) children.get(consumed++);
                }
                j++;
            }
            if (ctx.getChild(i).getText().equals(")") && !ctx.getChild(i - 1).getText().equals(";")) {
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

        if (ctx.expression() == null) {
            path.peek().add(new MiracleASTreeReturn());
        } else {
            path.peek().add(new MiracleASTreeReturn((MiracleASTreeExpression) children.get(0)));
        }
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
        if (ctx.IDENTIFIER() != null) {                                              // custom types
            String identifier = ctx.IDENTIFIER().getText();
            if (!MiracleEnvironmentReader.contain(identifier)) {
                throw new MiracleExceptionUndefinedIdentifier(identifier);
            }
            path.peek().add(new MiracleASTreeTypename(identifier));
        } else if (ctx.getChildCount() == 1) {
            path.peek().add(new MiracleASTreeTypename(ctx.getChild(0).getText())); // built-in types
        } else {
            path.peek().add(new MiracleASTreeTypename(ctx.typename().getText(),      // array type
                    ctx.getChildCount() - 1));
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
            path.peek().add(new MiracleASTreeConstant(new MiracleASTreeTypename("emptyobj"), "null"));
        } else {
            path.peek().add(new MiracleASTreeConstant(new MiracleASTreeTypename("boolean"),
                    ctx.getText()));
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
        String id = ctx.IDENTIFIER().getText();
        if (!MiracleEnvironmentReader.contain(id)) {
            throw new MiracleExceptionUndefinedIdentifier(id);
        }
        MiracleASTreeVariableDeclaration tmp = (MiracleASTreeVariableDeclaration) MiracleEnvironmentReader.get(id);
        path.peek().add(tmp.toValue());
        super.exitVariableExpression(ctx);
    }

    @Override
    public void enterSuffixExpression(MiracleParser.SuffixExpressionContext ctx) {
        super.enterSuffixExpression(ctx);
        path.push(new LinkedList<>());
    }

    @Override
    public void exitSuffixExpression(MiracleParser.SuffixExpressionContext ctx) {
        List<MiracleASTreeNode> children = path.pop();
        path.peek().add(MiracleASTreeExpressionFactory.getInstance(ctx.operator.getText(),
                (MiracleASTreeExpression) children.get(0)));
        super.exitSuffixExpression(ctx);
    }

    @Override
    public void enterPrefixExpression(MiracleParser.PrefixExpressionContext ctx) {
        super.enterPrefixExpression(ctx);
        path.push(new LinkedList<>());
    }

    @Override
    public void exitPrefixExpression(MiracleParser.PrefixExpressionContext ctx) {
        List<MiracleASTreeNode> children = path.pop();
        path.peek().add(MiracleASTreeExpressionFactory.getInstance((MiracleASTreeExpression) children.get(0),
                ctx.operator.getText()));
        super.exitPrefixExpression(ctx);
    }

    @Override
    public void enterNewExpression(MiracleParser.NewExpressionContext ctx) {
        super.enterNewExpression(ctx);
        path.push(new LinkedList<>());
    }

    @Override
    public void exitNewExpression(MiracleParser.NewExpressionContext ctx) {
        List<MiracleASTreeNode> children = path.pop();
        path.peek().add(new MiracleASTreeNewExpression((MiracleASTreeTypename) children.get(0)));
        super.exitNewExpression(ctx);
    }

    @Override
    public void enterMultDivExpression(MiracleParser.MultDivExpressionContext ctx) {
        super.enterMultDivExpression(ctx);
        path.push(new LinkedList<>());
    }

    @Override
    public void exitMultDivExpression(MiracleParser.MultDivExpressionContext ctx) {
        List<MiracleASTreeNode> children = path.pop();
        path.peek().add(MiracleASTreeExpressionFactory.getInstance((MiracleASTreeExpression) children.get(0),
                ctx.operator.getText(), (MiracleASTreeExpression) children.get(1)));
        super.exitMultDivExpression(ctx);
    }

    @Override
    public void enterAddSubExpression(MiracleParser.AddSubExpressionContext ctx) {
        super.enterAddSubExpression(ctx);
        path.push(new LinkedList<>());
    }

    @Override
    public void exitAddSubExpression(MiracleParser.AddSubExpressionContext ctx) {
        List<MiracleASTreeNode> children = path.pop();
        path.peek().add(MiracleASTreeExpressionFactory.getInstance((MiracleASTreeExpression) children.get(0),
                ctx.operator.getText(), (MiracleASTreeExpression) children.get(1)));
        super.exitAddSubExpression(ctx);
    }

    @Override
    public void enterShlShrExpression(MiracleParser.ShlShrExpressionContext ctx) {
        super.enterShlShrExpression(ctx);
        path.push(new LinkedList<>());
    }

    @Override
    public void exitShlShrExpression(MiracleParser.ShlShrExpressionContext ctx) {
        List<MiracleASTreeNode> children = path.pop();
        path.peek().add(MiracleASTreeExpressionFactory.getInstance((MiracleASTreeExpression) children.get(0),
                ctx.operator.getText(), (MiracleASTreeExpression) children.get(1)));
        super.exitShlShrExpression(ctx);
    }

    @Override
    public void enterCompareExpression(MiracleParser.CompareExpressionContext ctx) {
        super.enterCompareExpression(ctx);
        path.push(new LinkedList<>());
    }

    @Override
    public void exitCompareExpression(MiracleParser.CompareExpressionContext ctx) {
        List<MiracleASTreeNode> children = path.pop();
        path.peek().add(MiracleASTreeExpressionFactory.getInstance((MiracleASTreeExpression) children.get(0),
                ctx.operator.getText(), (MiracleASTreeExpression) children.get(1)));
        super.exitCompareExpression(ctx);
    }

    @Override
    public void enterEqualityExpression(MiracleParser.EqualityExpressionContext ctx) {
        super.enterEqualityExpression(ctx);
        path.push(new LinkedList<>());
    }

    @Override
    public void exitEqualityExpression(MiracleParser.EqualityExpressionContext ctx) {
        List<MiracleASTreeNode> children = path.pop();
        path.peek().add(MiracleASTreeExpressionFactory.getInstance((MiracleASTreeExpression) children.get(0),
                ctx.operator.getText(), (MiracleASTreeExpression) children.get(1)));
        super.exitEqualityExpression(ctx);
    }

    @Override
    public void enterAndExpression(MiracleParser.AndExpressionContext ctx) {
        super.enterAndExpression(ctx);
        path.push(new LinkedList<>());
    }

    @Override
    public void exitAndExpression(MiracleParser.AndExpressionContext ctx) {
        List<MiracleASTreeNode> children = path.pop();
        path.peek().add(MiracleASTreeExpressionFactory.getInstance((MiracleASTreeExpression) children.get(0),
                "&", (MiracleASTreeExpression) children.get(1)));
        super.exitAndExpression(ctx);
    }

    @Override
    public void enterXorExpression(MiracleParser.XorExpressionContext ctx) {
        super.enterXorExpression(ctx);
        path.push(new LinkedList<>());
    }

    @Override
    public void exitXorExpression(MiracleParser.XorExpressionContext ctx) {
        List<MiracleASTreeNode> children = path.pop();
        path.peek().add(MiracleASTreeExpressionFactory.getInstance((MiracleASTreeExpression) children.get(0),
                "^", (MiracleASTreeExpression) children.get(1)));
        super.exitXorExpression(ctx);
    }

    @Override
    public void enterOrExpression(MiracleParser.OrExpressionContext ctx) {
        super.enterOrExpression(ctx);
        path.push(new LinkedList<>());
    }

    @Override
    public void exitOrExpression(MiracleParser.OrExpressionContext ctx) {
        List<MiracleASTreeNode> children = path.pop();
        path.peek().add(MiracleASTreeExpressionFactory.getInstance((MiracleASTreeExpression) children.get(0),
                "|", (MiracleASTreeExpression) children.get(1)));
        super.exitOrExpression(ctx);
    }

    @Override
    public void enterLogicAndExpression(MiracleParser.LogicAndExpressionContext ctx) {
        super.enterLogicAndExpression(ctx);
        path.push(new LinkedList<>());
    }

    @Override
    public void exitLogicAndExpression(MiracleParser.LogicAndExpressionContext ctx) {
        List<MiracleASTreeNode> children = path.pop();
        path.peek().add(MiracleASTreeExpressionFactory.getInstance((MiracleASTreeExpression) children.get(0),
                "&&", (MiracleASTreeExpression) children.get(1)));
        super.exitLogicAndExpression(ctx);
    }

    @Override
    public void enterLogicOrExpression(MiracleParser.LogicOrExpressionContext ctx) {
        super.enterLogicOrExpression(ctx);
        path.push(new LinkedList<>());
    }

    @Override
    public void exitLogicOrExpression(MiracleParser.LogicOrExpressionContext ctx) {
        List<MiracleASTreeNode> children = path.pop();
        path.peek().add(MiracleASTreeExpressionFactory.getInstance((MiracleASTreeExpression) children.get(0),
                "||", (MiracleASTreeExpression) children.get(1)));
        super.exitLogicOrExpression(ctx);
    }

    @Override
    public void enterAssignExpression(MiracleParser.AssignExpressionContext ctx) {
        super.enterAssignExpression(ctx);
        path.push(new LinkedList<>());
    }

    @Override
    public void exitAssignExpression(MiracleParser.AssignExpressionContext ctx) {
        List<MiracleASTreeNode> children = path.pop();
        path.peek().add(MiracleASTreeExpressionFactory.getInstance((MiracleASTreeExpression) children.get(0),
                "=", (MiracleASTreeExpression) children.get(1)));
        super.exitAssignExpression(ctx);
    }

    @Override
    public void enterMemberExpression(MiracleParser.MemberExpressionContext ctx) {
        super.enterMemberExpression(ctx);
        path.push(new LinkedList<>());
    }

    @Override
    public void exitMemberExpression(MiracleParser.MemberExpressionContext ctx) {
        MiracleASTreeExpression left = (MiracleASTreeExpression) path.pop().get(0);
        MiracleASTreeTypename type = left.getType();
        String identifier = ctx.IDENTIFIER().getText();
        if (type.getTypenameType().equals(MiracleASTreeTypename.TYPE.TN_FUNC)
                || type.getDimension() != 0) {
            throw new MiracleExceptionMember(type.toString(), identifier);
        }
        if (!MiracleEnvironmentReader.get(type.toString()).getDeclarationType()
                .equals(MiracleASTreeDeclaration.DECTYPE.DEC_CLASS)) {
            throw new MiracleExceptionMember(type.toString(), identifier);
        }
        MiracleASTreeClassDeclaration declaration = (MiracleASTreeClassDeclaration) MiracleEnvironmentReader.get(type.toString());
        if (!declaration.contain(identifier)) {
            throw new MiracleExceptionMember(type.toString(), identifier);
        }
        path.peek().add(declaration.getMember(identifier).toValue());
        super.exitMemberExpression(ctx);
    }

    @Override
    public void enterFunctionCallExpression(MiracleParser.FunctionCallExpressionContext ctx) {
        super.enterFunctionCallExpression(ctx);
        path.push(new LinkedList<>());
    }

    @Override
    public void exitFunctionCallExpression(MiracleParser.FunctionCallExpressionContext ctx) {
        List<MiracleASTreeNode> children = path.pop();
        MiracleASTreeExpression head = (MiracleASTreeExpression) children.get(0);
        MiracleASTreeTypename orgtype = head.getType();
        if (orgtype.getTypenameType().equals(MiracleASTreeTypename.TYPE.TN_VAR)) {
            throw new MiracleExceptionCallVariable();
        }
        List<MiracleASTreeTypename> argtype = new LinkedList<>();
        List<MiracleASTreeExpression> args = new LinkedList<>();
        for (int i = 1; i < children.size(); i++) {
            argtype.add(((MiracleASTreeExpression) children.get(i)).getType());
            args.add(((MiracleASTreeExpression) children.get(i)));
        }
        if (!orgtype.match(argtype)) {
            throw new MiracleExceptionArguments(MiracleASTreeTypename.getArgStyle(orgtype.getArguments()),
                    MiracleASTreeTypename.getArgStyle(argtype));
        }
        path.peek().add(new MiracleASTreeCallExpression((MiracleASTreeFunction) head, args));
        super.exitFunctionCallExpression(ctx);
    }

    @Override
    public void enterSubscriptExpression(MiracleParser.SubscriptExpressionContext ctx) {
        super.enterSubscriptExpression(ctx);
        path.push(new LinkedList<>());
    }

    @Override
    public void exitSubscriptExpression(MiracleParser.SubscriptExpressionContext ctx) {
        List<MiracleASTreeNode> children = path.pop();
        path.peek().add(MiracleASTreeExpressionFactory.getInstance((MiracleASTreeExpression) children.get(0),
                "[]", (MiracleASTreeExpression) children.get(1)));
        super.exitSubscriptExpression(ctx);
    }
}
