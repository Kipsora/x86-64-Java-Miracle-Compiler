package com.miracle.scanner.listener;

import com.miracle.astree.MiracleASTree;
import com.miracle.astree.node.MiracleASTreeNode;
import com.miracle.astree.node.MiracleASTreeRoot;
import com.miracle.astree.node.expression.MiracleASTreeExpression;
import com.miracle.astree.node.expression.MiracleASTreeExpressionFactory;
import com.miracle.astree.node.expression.binary.MiracleASTreeField;
import com.miracle.astree.node.expression.multiary.MiracleASTreeCallExpression;
import com.miracle.astree.node.expression.multiary.MiracleASTreeNewExpression;
import com.miracle.astree.node.expression.value.MiracleASTreeConstant;
import com.miracle.astree.node.expression.value.MiracleASTreeThis;
import com.miracle.astree.node.statement.MiracleASTreeBlock;
import com.miracle.astree.node.statement.MiracleASTreeSelection;
import com.miracle.astree.node.statement.MiracleASTreeStatement;
import com.miracle.astree.node.statement.control.MiracleASTreeBreak;
import com.miracle.astree.node.statement.control.MiracleASTreeContinue;
import com.miracle.astree.node.statement.control.MiracleASTreeReturn;
import com.miracle.astree.node.statement.declaration.MiracleASTreeClassDeclaration;
import com.miracle.astree.node.statement.declaration.MiracleASTreeFunctionDeclaration;
import com.miracle.astree.node.statement.declaration.MiracleASTreeMemberDeclaration;
import com.miracle.astree.node.statement.declaration.MiracleASTreeTypename;
import com.miracle.astree.node.statement.declaration.MiracleASTreeVariableDeclaration;
import com.miracle.astree.node.statement.iteration.MiracleASTreeFor;
import com.miracle.astree.node.statement.iteration.MiracleASTreeIteration;
import com.miracle.astree.node.statement.iteration.MiracleASTreeWhile;
import com.miracle.cstree.MiracleParser;
import com.miracle.exceptions.MiracleExceptionArguments;
import com.miracle.exceptions.MiracleExceptionCallVariable;
import com.miracle.exceptions.MiracleExceptionMember;
import com.miracle.exceptions.MiracleExceptionNewBaseType;
import com.miracle.exceptions.MiracleExceptionNewSubscript;
import com.miracle.exceptions.MiracleExceptionNewType;
import com.miracle.exceptions.MiracleExceptionThis;
import com.miracle.exceptions.MiracleExceptionUndefinedIdentifier;
import com.miracle.scanner.MiracleEnvironmentManager;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class MiracleASTreeBuilder extends MiracleRuntimeMaintainer {
    private Stack<List<MiracleASTreeNode>> path = new Stack<>();
    private Stack<MiracleASTreeIteration> iterationBuffer = new Stack<>();
    private Stack<MiracleASTreeFunctionDeclaration> functionBuffer = new Stack<>();
    private Stack<MiracleASTreeClassDeclaration> classBuffer = new Stack<>();

    public MiracleASTreeBuilder() {
        path.push(new LinkedList<>());
    }

    public MiracleASTree getTree() {
        return new MiracleASTree(new MiracleASTreeRoot(path.peek()));
    }

    @Override
    public void enterClassDeclarationStatement(MiracleParser.ClassDeclarationStatementContext ctx) {
        super.enterClassDeclarationStatement(ctx);
        path.push(new LinkedList<>());
        classBuffer.push(MiracleEnvironmentManager.getClass(ctx.IDENTIFIER().getText()));
    }

    @Override
    public void exitClassDeclarationStatement(MiracleParser.ClassDeclarationStatementContext ctx) {
        List<MiracleASTreeNode> children = path.pop();
        List<MiracleASTreeMemberDeclaration> declaration = new LinkedList<>();
        children.forEach((key) -> declaration.add((MiracleASTreeMemberDeclaration) key));
        MiracleASTreeClassDeclaration node = classBuffer.pop();
        node.setChildren(declaration);
        path.peek().add(node);
        super.exitClassDeclarationStatement(ctx);
    }

    @Override
    public void enterFunctionDeclarationStatement(MiracleParser.FunctionDeclarationStatementContext ctx) {
        super.enterFunctionDeclarationStatement(ctx);
        path.push(new LinkedList<>());
        for (int i = 1; i < ctx.IDENTIFIER().size(); i++) {
            MiracleEnvironmentManager.fetchVariable(ctx.IDENTIFIER(i).getText());
        }
        functionBuffer.push(MiracleEnvironmentManager.getFunction(ctx.IDENTIFIER(0).getText()));
    }

    @Override
    public void exitFunctionDeclarationStatement(MiracleParser.FunctionDeclarationStatementContext ctx) {
        List<MiracleASTreeNode> children = path.pop();
        List<MiracleASTreeStatement> body = new LinkedList<>();
        for (int i = ctx.IDENTIFIER().size(); i < children.size(); i++) {
            body.add((MiracleASTreeStatement) children.get(i));
        }
        MiracleASTreeFunctionDeclaration node = functionBuffer.pop();
        node.setBody(body);
        path.peek().add(node);
        super.exitFunctionDeclarationStatement(ctx);
    }

    @Override
    public void enterVariableDeclarationStatement(MiracleParser.VariableDeclarationStatementContext ctx) {
        super.enterVariableDeclarationStatement(ctx);
        path.push(new LinkedList<>());
    }

    @Override
    public void exitVariableDeclarationStatement(MiracleParser.VariableDeclarationStatementContext ctx) {
        MiracleASTreeExpression value = null;
        if (ctx.expression() != null) {
            value = (MiracleASTreeExpression) path.peek().get(1);
        }
        path.pop();
        MiracleEnvironmentManager.fetchVariable(ctx.IDENTIFIER().getText());
        MiracleASTreeVariableDeclaration node = MiracleEnvironmentManager.getVariable(ctx.IDENTIFIER().getText());
        node.setExpression(value);
        path.peek().add(node);
        super.exitVariableDeclarationStatement(ctx);
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
        iterationBuffer.push(new MiracleASTreeFor());
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
                    node[j] = (MiracleASTreeExpression) children.get(consumed++);
                }
                j++;
            }
            if (ctx.getChild(i).getText().equals(")") && !ctx.getChild(i - 1).getText().equals(";")) {
                statement = (MiracleASTreeStatement) children.get(consumed++);
            }
        }
        assert statement != null;
        MiracleASTreeFor fornode = (MiracleASTreeFor) iterationBuffer.pop();
        fornode.setLeftExpression(node[0]);
        fornode.setMiddleExpression(node[1]);
        fornode.setRightExpression(node[2]);
        fornode.setStatement(statement);
        path.peek().add(fornode);
        super.exitForStatement(ctx);
    }

    @Override
    public void enterWhileStatement(MiracleParser.WhileStatementContext ctx) {
        super.enterWhileStatement(ctx);
        path.push(new LinkedList<>());
        iterationBuffer.push(new MiracleASTreeWhile());
    }

    @Override
    public void exitWhileStatement(MiracleParser.WhileStatementContext ctx) {
        List<MiracleASTreeNode> children = path.pop();
        MiracleASTreeExpression expression = (MiracleASTreeExpression) children.get(0);
        MiracleASTreeStatement statement = (MiracleASTreeStatement) children.get(1);
        MiracleASTreeWhile whilenode = (MiracleASTreeWhile) iterationBuffer.pop();
        whilenode.setExpression(expression);
        whilenode.setStatement(statement);
        path.peek().add(whilenode);
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
        path.peek().add(new MiracleASTreeBreak(iterationBuffer.peek()));
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
    public void enterConstant(MiracleParser.ConstantContext ctx) {
        super.enterConstant(ctx);
        path.push(new LinkedList<>());
    }

    @Override
    public void exitConstant(MiracleParser.ConstantContext ctx) {
        path.pop();
        if (ctx.INTEGER() != null) {
            path.peek().add(new MiracleASTreeConstant(MiracleASTreeINT,
                    ctx.INTEGER().getText()));
        } else if (ctx.STRING() != null) {
            path.peek().add(new MiracleASTreeConstant(MiracleASTreeSTRING,
                    ctx.STRING().getText()));
        } else if (ctx.getText().equals("null")) {
            path.peek().add(new MiracleASTreeConstant(new MiracleASTreeTypename("emptyobj"), "null"));
        } else {
            path.peek().add(new MiracleASTreeConstant(MiracleASTreeBOOLEAN,
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
        if (id.equals("this")) {
            if (MiracleEnvironmentManager.inMemberScope()) {
                path.peek().add(new MiracleASTreeThis(
                        new MiracleASTreeTypename(classBuffer.peek().getIdentifier()), true)
                );
            } else {
                throw new MiracleExceptionThis();
            }
        } else if (MiracleEnvironmentManager.containVariable(id)) {
            path.peek().add(MiracleEnvironmentManager.getVariable(id).toValue());
        } else if (MiracleEnvironmentManager.containFunction(id)) {
            path.peek().add(MiracleEnvironmentManager.getFunction(id).toValue());
        } else if (id.equals("print")) {
            path.peek().add(MiracleASTreePRINT.toValue());
        } else if (id.equals("println")) {
            path.peek().add(MiracleASTreePRINTLN.toValue());
        } else if (id.equals("getString")) {
            path.peek().add(MiracleASTreeGETSTRING.toValue());
        } else if (id.equals("getInt")) {
            path.peek().add(MiracleASTreeGETINT.toValue());
        } else if (id.equals("toString")) {
            path.peek().add(MiracleASTreeTOSTRING.toValue());
        } else {
            throw new MiracleExceptionUndefinedIdentifier(id);
        }
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
        path.peek().add(MiracleASTreeExpressionFactory.getInstance((MiracleASTreeExpression) children.get(0),
                ctx.operator.getText()));
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
        path.peek().add(MiracleASTreeExpressionFactory.getInstance(ctx.operator.getText(),
                (MiracleASTreeExpression) children.get(0)));
        super.exitPrefixExpression(ctx);
    }

    @Override
    public void enterNewExpression(MiracleParser.NewExpressionContext ctx) {
        super.enterNewExpression(ctx);
        path.push(new LinkedList<>());
    }

    @Override
    public void enterTypename(MiracleParser.TypenameContext ctx) {
        super.enterTypename(ctx);
        path.push(new LinkedList<>());
    }

    @Override
    public void exitTypename(MiracleParser.TypenameContext ctx) {
        path.pop();
        int dimension = 0;
        if (ctx.IDENTIFIER() != null) {
            String identifier = ctx.IDENTIFIER().getText();
            if (!MiracleASTreeBuiltinTYPE.contains(new MiracleASTreeTypename(identifier))) {
                MiracleEnvironmentManager.getClass(identifier);
            }
            for (int i = 1; i < ctx.getChildCount(); i++) {
                if (ctx.getChild(i).getText().equals("[")) {
                    dimension++;
                }
            }
            path.peek().add(new MiracleASTreeTypename(ctx.IDENTIFIER().getText(), dimension));
        } else {
            for (int i = 1; i < ctx.getChildCount(); i++) {                          // custom types
                if (ctx.getChild(i).getText().equals("[")) {
                    dimension++;
                }
            }
            path.peek().add(new MiracleASTreeTypename(ctx.BASETYPE().getText(), dimension));
        }
        super.exitTypename(ctx);
    }

    @Override
    public void exitNewExpression(MiracleParser.NewExpressionContext ctx) {
        List<MiracleASTreeNode> children = path.pop();
        List<MiracleASTreeExpression> size = new LinkedList<>();
        MiracleASTreeTypename type = (MiracleASTreeTypename) children.get(0);
        if (type.getDimension() != 0) {
            throw new MiracleExceptionNewType(type.toString());
        }
        int dimension = 0;
        for (int i = 0, j = 1, flag = 0; i < ctx.getChildCount(); i++) {
            if (ctx.getChild(i).getText().equals("[") && j < children.size()) {
                MiracleASTreeExpression expression = (MiracleASTreeExpression) children.get(j++);
                if (!expression.getType().equals(MiracleASTreeINT)) {
                    throw new MiracleExceptionNewSubscript(expression.getType().toString());
                }
                size.add(expression);
            }
            if (ctx.getChild(i).getText().equals("[")) {
                dimension++;
                if (!ctx.getChild(i + 1).getText().equals("]")) {
                    if (flag == 1) {
                        throw new MiracleExceptionNewSubscript("empty expression");
                    }
                } else {
                    flag = 1;
                }
            }
        }
        if (dimension == 0) {
            if (type.getBasetype().equals("int") || type.getBasetype().equals("boolean")
                    || type.getBasetype().equals("void") || type.getBasetype().equals("string")) {
                throw new MiracleExceptionNewBaseType();
            }
        }
        path.peek().add(new MiracleASTreeNewExpression(
                new MiracleASTreeTypename(
                        type.toString(),
                        dimension
                ),
                size
        ));
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
        if (type.getTypenameType().equals(MiracleASTreeTypename.TYPE.TN_FUNC)) {
            throw new MiracleExceptionMember(type.toString(), identifier);
        }
        if (MiracleEnvironmentManager.contain(type.toString())) {
            if (!MiracleEnvironmentManager.containClass(type.toString())) {
                throw new MiracleExceptionMember(type.toString(), identifier);
            }
            MiracleASTreeClassDeclaration declaration = MiracleEnvironmentManager.getClass(type.toString());
            if (!declaration.contain(identifier)) {
                throw new MiracleExceptionMember(type.toString(), identifier);
            }
            path.peek().add(new MiracleASTreeField(left, declaration.getMember(identifier).toValue()));
        } else {
            if (type.equals(MiracleASTreeSTRING)) {
                switch (identifier) {
                    case "substring":
                        path.peek().add(new MiracleASTreeField(left, MiracleASTreeSUBSTRING.toValue()));
                        break;
                    case "length":
                        path.peek().add(new MiracleASTreeField(left, MiracleASTreeLENGTH.toValue()));
                        break;
                    case "parseInt":
                        path.peek().add(new MiracleASTreeField(left, MiracleASTreePARSEINT.toValue()));
                        break;
                    case "ord":
                        path.peek().add(new MiracleASTreeField(left, MiracleASTreeORD.toValue()));
                        break;
                    default:
                        throw new MiracleExceptionMember(type.toString(), identifier);
                }
            } else if (type.getDimension() > 0) {
                if (identifier.equals("size")) {
                    path.peek().add(new MiracleASTreeField(left, MiracleASTreeSIZE.toValue()));
                } else {
                    throw new MiracleExceptionMember(type.toString(), identifier);
                }
            } else if (type.equals(MiracleASTreeINT)) {
                throw new MiracleExceptionMember(type.toString(), identifier);
            } else if (type.equals(MiracleASTreeBOOLEAN)) {
                throw new MiracleExceptionMember(type.toString(), identifier);
            } else if (type.equals(MiracleASTreeVOID)) {
                throw new MiracleExceptionMember(type.toString(), identifier);
            } else {
                throw new MiracleExceptionUndefinedIdentifier(type.toString());
            }
        }
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
        path.peek().add(new MiracleASTreeCallExpression(head, args));
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
