package com.miracle.astree;

import com.miracle.astree.base.MiracleASTreeNode;
import com.miracle.astree.base.MiracleASTreeTypeNode;
import com.miracle.astree.statement.*;
import com.miracle.astree.statement.declaration.MiracleASTreeClassDeclaration;
import com.miracle.astree.statement.declaration.MiracleASTreeDeclaration;
import com.miracle.astree.statement.declaration.MiracleASTreeFunctionDeclaration;
import com.miracle.astree.statement.declaration.MiracleASTreeVariableDeclaration;
import com.miracle.astree.statement.expression.*;
import com.miracle.astree.statement.expression.constant.MiracleASTreeBooleanConstant;
import com.miracle.astree.statement.expression.constant.MiracleASTreeIntegerConstant;
import com.miracle.astree.statement.expression.constant.MiracleASTreeNullConstant;
import com.miracle.astree.statement.expression.constant.MiracleASTreeStringConstant;
import com.miracle.astree.visitor.MiracleASTreeVisitor;
import com.miracle.cstree.MiracleSourcePosition;
import com.miracle.cstree.parser.MiracleBaseListener;
import com.miracle.cstree.parser.MiracleParser;
import com.miracle.exception.MiracleExceptionContainer;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class MiracleASTree extends MiracleASTreeNode {
    public final List<MiracleASTreeDeclaration> declarations;

    MiracleASTree(List<MiracleASTreeDeclaration> declarations) {
        this.declarations = Collections.unmodifiableList(declarations);
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.visit(this);
    }

    public static class Builder extends MiracleBaseListener {
        private final MiracleExceptionContainer exceptionContainer;
        private MiracleASTree root;
        private ParseTreeProperty<Object> property = new ParseTreeProperty<>();

        public Builder(MiracleExceptionContainer exceptionContainer) {
            this.exceptionContainer = exceptionContainer;
        }

        public MiracleASTree build() {
            return root;
        }

        @Override
        public void exitMiracle(MiracleParser.MiracleContext ctx) {
            List<MiracleASTreeDeclaration> declarations = new LinkedList<>();

            List<ParseTree> children = ctx.children;
            for (int i = 0, childrenSize = children.size(); i < childrenSize; i++) {
                ParseTree element = children.get(i);
                if (property.get(element) != null) {
                    declarations.add((MiracleASTreeDeclaration) property.get(element));
                }
            }

            root = new MiracleASTree(declarations);
        }

        @Override
        public void exitClassDeclarationStatement(MiracleParser.ClassDeclarationStatementContext ctx) {
            List<MiracleASTreeVariableDeclaration> variableDeclarations = new LinkedList<>();
            List<MiracleASTreeFunctionDeclaration> functionDeclarations = new LinkedList<>();
            MiracleASTreeFunctionDeclaration constructorDeclaration = null;

            List<MiracleParser.VariableDeclarationStatementContext> variableDeclarationStatement = ctx.variableDeclarationStatement();
            for (int i = 0, size = variableDeclarationStatement.size(); i < size; i++) {
                MiracleParser.VariableDeclarationStatementContext element = variableDeclarationStatement.get(i);
                variableDeclarations.add((MiracleASTreeVariableDeclaration) property.get(element));
            }

            List<MiracleParser.FunctionDeclarationStatementContext> functionDeclarationStatement = ctx.functionDeclarationStatement();
            for (int i = 0, size = functionDeclarationStatement.size(); i < size; i++) {
                MiracleParser.FunctionDeclarationStatementContext element = functionDeclarationStatement.get(i);
                if (element.returnType == null) {
                    if (constructorDeclaration != null) {
                        exceptionContainer.add("multiple declarations of constructors in class `" + ctx.IDENTIFIER().getText() + " `are found",
                                new MiracleSourcePosition(element.typename(0)));
                    } else {
                        constructorDeclaration = (MiracleASTreeFunctionDeclaration) property.get(element);
                    }
                } else {
                    functionDeclarations.add((MiracleASTreeFunctionDeclaration) property.get(element));
                }
            }

            property.put(ctx, new MiracleASTreeClassDeclaration(
                    ctx.IDENTIFIER().getText(),
                    variableDeclarations,
                    functionDeclarations,
                    constructorDeclaration,
                    new MiracleSourcePosition(ctx),
                    new MiracleSourcePosition(ctx.IDENTIFIER().getSymbol())
            ));
        }

        @Override
        public void exitFunctionDeclarationStatement(MiracleParser.FunctionDeclarationStatementContext ctx) {
            List<MiracleASTreeStatement> body = new LinkedList<>();
            List<MiracleASTreeVariableDeclaration> parameters = new LinkedList<>();

            List<MiracleParser.StatementContext> statement = ctx.statement();
            for (int i = 0, statementSize = statement.size(); i < statementSize; i++) {
                MiracleParser.StatementContext element = statement.get(i);
                if (property.get(element) != null) { // empty statement
                    body.add((MiracleASTreeStatement) property.get(element));
                }
            }

            for (int i = 1, size = ctx.IDENTIFIER().size(); i < size; i++) {
                parameters.add(new MiracleASTreeVariableDeclaration(
                        ctx.IDENTIFIER(i).getText(),
                        (MiracleASTreeTypeNode) property.get(ctx.typename(i)),
                        null,
                        new MiracleSourcePosition(ctx.typename(i)),
                        new MiracleSourcePosition(ctx.IDENTIFIER(i).getSymbol())
                ));
            }
            if (ctx.returnType == null) {
                property.put(ctx, new MiracleASTreeFunctionDeclaration(
                        ctx.IDENTIFIER(0).getText(),
                        null,
                        parameters,
                        body,
                        new MiracleSourcePosition(ctx),
                        null
                ));
            } else {
                property.put(ctx, new MiracleASTreeFunctionDeclaration(
                        ctx.IDENTIFIER().get(0).getText(),
                        (MiracleASTreeTypeNode) property.get(ctx.typename(0)),
                        parameters,
                        body,
                        new MiracleSourcePosition(ctx),
                        new MiracleSourcePosition(ctx.IDENTIFIER(0).getSymbol())
                ));
            }
        }

        @Override
        public void exitVariableDeclarationStatement(MiracleParser.VariableDeclarationStatementContext ctx) {
            TerminalNode identifier = ctx.IDENTIFIER();
            if (ctx.expression() != null) {
                property.put(ctx, new MiracleASTreeVariableDeclaration(
                        identifier.getText(),
                        (MiracleASTreeTypeNode) property.get(ctx.typename()),
                        (MiracleASTreeExpression) property.get(ctx.expression()),
                        new MiracleSourcePosition(ctx),
                        new MiracleSourcePosition(identifier.getSymbol())
                ));
            } else {
                property.put(ctx, new MiracleASTreeVariableDeclaration(
                        identifier.getText(),
                        (MiracleASTreeTypeNode) property.get(ctx.typename()),
                        null,
                        new MiracleSourcePosition(ctx),
                        new MiracleSourcePosition(identifier.getSymbol())
                ));
            }
        }

        @Override
        public void exitTypename(MiracleParser.TypenameContext ctx) {
            property.put(ctx, new MiracleASTreeTypeNode(
                    ctx.basetype().getText(),
                    (ctx.getChildCount() - 1) >> 1,
                    new MiracleSourcePosition(ctx.basetype())
            ));
        }

        @Override
        public void exitBlockStatement(MiracleParser.BlockStatementContext ctx) {
            List<MiracleASTreeStatement> statements = new LinkedList<>();

            List<MiracleParser.StatementContext> statement = ctx.statement();
            for (int i = 0, statementSize = statement.size(); i < statementSize; i++) {
                MiracleParser.StatementContext element = statement.get(i);
                if (property.get(element) != null) {
                    statements.add((MiracleASTreeStatement) property.get(element));
                }
            }

            property.put(ctx, new MiracleASTreeBlock(
                    statements,
                    new MiracleSourcePosition(ctx)
            ));
        }

        @Override
        public void exitSelectionStatement(MiracleParser.SelectionStatementContext ctx) {
            MiracleASTreeStatement trueStatement = (MiracleASTreeStatement) property.get(ctx.statement(0));
            MiracleASTreeStatement falseStatement = (ctx.statement().size() > 1 ? (MiracleASTreeStatement) property.get(ctx.statement(1)) : null);
            if (trueStatement != null && !(trueStatement instanceof MiracleASTreeBlock)) {
                MiracleASTreeStatement finalTrueStatement = trueStatement;
                trueStatement = new MiracleASTreeBlock(new LinkedList<MiracleASTreeStatement>() {
                    private static final long serialVersionUID = -694271397082132527L;

                    {
                        add(finalTrueStatement);
                    }
                }, trueStatement.position);
            }
            if (falseStatement != null && !(falseStatement instanceof MiracleASTreeBlock)) {
                MiracleASTreeStatement finalFalseStatement = falseStatement;
                falseStatement = new MiracleASTreeBlock(new LinkedList<MiracleASTreeStatement>() {
                    private static final long serialVersionUID = -694271397082132527L;

                    {
                        add(finalFalseStatement);
                    }
                }, falseStatement.position);
            }
            property.put(ctx, new MiracleASTreeSelection(
                    (MiracleASTreeExpression) property.get(ctx.expression()),
                    trueStatement,
                    falseStatement,
                    new MiracleSourcePosition(ctx)
            ));
        }

        @Override
        public void exitForStatement(MiracleParser.ForStatementContext ctx) {
            MiracleASTreeExpression[] node = new MiracleASTreeExpression[3];
            for (int i = 0, j = 0, size = ctx.getChildCount(); i < size && j < 3; i++) {
                if (ctx.getChild(i).getText().equals(";") || ctx.getChild(i).getText().equals(")")) {
                    if (!ctx.getChild(i - 1).getText().equals(";")
                            && !ctx.getChild(i - 1).getText().equals("(")) {
                        node[j] = (MiracleASTreeExpression) property.get(ctx.children.get(i - 1));
                    }
                    j++;
                }
            }
            MiracleASTreeStatement statement = (MiracleASTreeStatement) property.get(ctx.statement());
            if (statement != null && !(statement instanceof MiracleASTreeBlock)) {
                MiracleASTreeStatement finalStatement = statement;
                statement = new MiracleASTreeBlock(new LinkedList<MiracleASTreeStatement>() {
                    private static final long serialVersionUID = 6113359493637144909L;

                    {
                        add(finalStatement);
                    }
                }, statement.position);
            }
            property.put(ctx, new MiracleASTreeIteration(
                    node[0],
                    node[1],
                    node[2],
                    statement,
                    new MiracleSourcePosition(ctx)
            ));
        }

        @Override
        public void exitWhileStatement(MiracleParser.WhileStatementContext ctx) {
            property.put(ctx, new MiracleASTreeIteration(null,
                    (MiracleASTreeExpression) property.get(ctx.expression()),
                    null,
                    (MiracleASTreeStatement) property.get(ctx.statement()),
                    new MiracleSourcePosition(ctx)
            ));
        }

        @Override
        public void exitBreakStatement(MiracleParser.BreakStatementContext ctx) {
            property.put(ctx, new MiracleASTreeBreak(new MiracleSourcePosition(ctx)));
        }

        @Override
        public void exitContinueStatement(MiracleParser.ContinueStatementContext ctx) {
            property.put(ctx, new MiracleASTreeContinue(new MiracleSourcePosition(ctx)));
        }

        @Override
        public void exitReturnStatement(MiracleParser.ReturnStatementContext ctx) {
            property.put(ctx, new MiracleASTreeReturn(ctx.expression() == null ? null :
                    (MiracleASTreeExpression) property.get(ctx.expression()),
                    new MiracleSourcePosition(ctx)
            ));
        }

        @Override
        public void exitExpressionStatement(MiracleParser.ExpressionStatementContext ctx) {
            property.put(ctx, property.get(ctx.expression()));
        }

        @Override
        public void exitConstantExpression(MiracleParser.ConstantExpressionContext ctx) {
            property.put(ctx, property.get(ctx.constant()));
        }

        @Override
        public void exitVariableExpression(MiracleParser.VariableExpressionContext ctx) {
            property.put(ctx, new MiracleASTreeVariable(
                    ctx.IDENTIFIER().getText(),
                    new MiracleSourcePosition(ctx)
            ));
        }

        @Override
        public void exitBraceExpression(MiracleParser.BraceExpressionContext ctx) {
            property.put(ctx, property.get(ctx.expression()));
        }

        @Override
        public void exitFunctionCallExpression(MiracleParser.FunctionCallExpressionContext ctx) {
            List<MiracleASTreeExpression> parameters = new LinkedList<>();
            for (int i = 1, size = ctx.expression().size(); i < size; i++) {
                parameters.add((MiracleASTreeExpression) property.get(ctx.expression(i)));
            }
            property.put(ctx, new MiracleASTreeCall(
                    (MiracleASTreeExpression) property.get(ctx.expression(0)),
                    parameters,
                    new MiracleSourcePosition(ctx)
            ));
        }

        @Override
        public void exitSubscriptExpression(MiracleParser.SubscriptExpressionContext ctx) {
            property.put(ctx, new MiracleASTreeSubscript(
                    (MiracleASTreeExpression) property.get(ctx.expression(0)),
                    (MiracleASTreeExpression) property.get(ctx.expression(1)),
                    new MiracleSourcePosition(ctx)
            ));
        }

        @Override
        public void exitBinaryExpression(MiracleParser.BinaryExpressionContext ctx) {
            MiracleASTreeBinaryExpression.OPERATOR operator;
            switch (ctx.operator.getText()) {
                case "+":
                    operator = MiracleASTreeBinaryExpression.OPERATOR.ADD;
                    break;
                case "-":
                    operator = MiracleASTreeBinaryExpression.OPERATOR.SUB;
                    break;
                case "*":
                    operator = MiracleASTreeBinaryExpression.OPERATOR.MUL;
                    break;
                case "/":
                    operator = MiracleASTreeBinaryExpression.OPERATOR.DIV;
                    break;
                case "%":
                    operator = MiracleASTreeBinaryExpression.OPERATOR.MOD;
                    break;
                case "<<":
                    operator = MiracleASTreeBinaryExpression.OPERATOR.SHL;
                    break;
                case ">>":
                    operator = MiracleASTreeBinaryExpression.OPERATOR.SHR;
                    break;
                case "<":
                    operator = MiracleASTreeBinaryExpression.OPERATOR.LT;
                    break;
                case ">":
                    operator = MiracleASTreeBinaryExpression.OPERATOR.RT;
                    break;
                case "<=":
                    operator = MiracleASTreeBinaryExpression.OPERATOR.LEQ;
                    break;
                case ">=":
                    operator = MiracleASTreeBinaryExpression.OPERATOR.REQ;
                    break;
                case "==":
                    operator = MiracleASTreeBinaryExpression.OPERATOR.EQL;
                    break;
                case "!=":
                    operator = MiracleASTreeBinaryExpression.OPERATOR.NEQ;
                    break;
                case "&":
                    operator = MiracleASTreeBinaryExpression.OPERATOR.AND;
                    break;
                case "|":
                    operator = MiracleASTreeBinaryExpression.OPERATOR.OR;
                    break;
                case "^":
                    operator = MiracleASTreeBinaryExpression.OPERATOR.XOR;
                    break;
                case "&&":
                    operator = MiracleASTreeBinaryExpression.OPERATOR.CONJ;
                    break;
                case "||":
                    operator = MiracleASTreeBinaryExpression.OPERATOR.DISJ;
                    break;
                case "=":
                    operator = MiracleASTreeBinaryExpression.OPERATOR.ASS;
                    break;
                default:
                    throw new RuntimeException("unsupported operator");
            }
            property.put(ctx, new MiracleASTreeBinaryExpression(
                    (MiracleASTreeExpression) property.get(ctx.expression(0)),
                    operator,
                    (MiracleASTreeExpression) property.get(ctx.expression(1)),
                    new MiracleSourcePosition(ctx),
                    new MiracleSourcePosition(ctx.operator)
            ));
        }

        @Override
        public void exitPrefixExpression(MiracleParser.PrefixExpressionContext ctx) {
            MiracleASTreePrefixExpression.OPERATOR operator;
            switch (ctx.operator.getText()) {
                case "!":
                    operator = MiracleASTreePrefixExpression.OPERATOR.NEGATE;
                    break;
                case "~":
                    operator = MiracleASTreePrefixExpression.OPERATOR.REV;
                    break;
                case "+":
                    operator = MiracleASTreePrefixExpression.OPERATOR.POSITIVE;
                    break;
                case "-":
                    operator = MiracleASTreePrefixExpression.OPERATOR.NEGATIVE;
                    break;
                case "++":
                    operator = MiracleASTreePrefixExpression.OPERATOR.INC;
                    break;
                case "--":
                    operator = MiracleASTreePrefixExpression.OPERATOR.DEC;
                    break;
                default:
                    throw new RuntimeException("unsupported operator");
            }
            property.put(ctx, new MiracleASTreePrefixExpression(
                    operator,
                    (MiracleASTreeExpression) property.get(ctx.expression()),
                    new MiracleSourcePosition(ctx),
                    new MiracleSourcePosition(ctx.operator)
            ));
        }

        @Override
        public void exitSuffixExpression(MiracleParser.SuffixExpressionContext ctx) {
            MiracleASTreeSuffixExpression.OPERATOR operator;
            switch (ctx.operator.getText()) {
                case "++":
                    operator = MiracleASTreeSuffixExpression.OPERATOR.INC;
                    break;
                case "--":
                    operator = MiracleASTreeSuffixExpression.OPERATOR.DEC;
                    break;
                default:
                    throw new RuntimeException("unsupported operator");
            }
            property.put(ctx, new MiracleASTreeSuffixExpression(
                    (MiracleASTreeExpression) property.get(ctx.expression()),
                    operator,
                    new MiracleSourcePosition(ctx),
                    new MiracleSourcePosition(ctx.operator)
            ));
        }

        @Override
        public void exitNewExpression(MiracleParser.NewExpressionContext ctx) {
            List<MiracleASTreeExpression> expressions = new LinkedList<>();
            for (int i = 0, size = ctx.getChildCount(); i < size; i++) {
                if (ctx.getChild(i).getText().equals("]")) {
                    if (ctx.getChild(i - 1).getText().equals("[")) {
                        expressions.add(null);
                    } else {
                        expressions.add((MiracleASTreeExpression) property.get(ctx.getChild(i - 1)));
                    }
                }
            }
            property.put(ctx, new MiracleASTreeNew(
                    (MiracleASTreeTypeNode) property.get(ctx.typename()),
                    expressions,
                    new MiracleSourcePosition(ctx)
            ));
        }

        @Override
        public void exitIntegerConstant(MiracleParser.IntegerConstantContext ctx) {
            property.put(ctx, new MiracleASTreeIntegerConstant(ctx.INTEGER().getText(),
                    new MiracleSourcePosition(ctx)));
        }

        @Override
        public void exitBoolConstant(MiracleParser.BoolConstantContext ctx) {
            property.put(ctx, new MiracleASTreeBooleanConstant(
                    ctx.getText(),
                    new MiracleSourcePosition(ctx)
            ));
        }

        @Override
        public void exitStringConstant(MiracleParser.StringConstantContext ctx) {
            property.put(ctx, new MiracleASTreeStringConstant(
                    ctx.getText(),
                    new MiracleSourcePosition(ctx)
            ));
        }

        @Override
        public void exitNullConstant(MiracleParser.NullConstantContext ctx) {
            property.put(ctx, new MiracleASTreeNullConstant(new MiracleSourcePosition(ctx)));
        }

        @Override
        public void exitStatement(MiracleParser.StatementContext ctx) {
            property.put(ctx, property.get(ctx.getChild(0)));
        }

        @Override
        public void exitThisExpression(MiracleParser.ThisExpressionContext ctx) {
            property.put(ctx, new MiracleASTreeThis(new MiracleSourcePosition(ctx)));
        }

        @Override
        public void exitFieldExpression(MiracleParser.FieldExpressionContext ctx) {
            property.put(ctx, new MiracleASTreeField(
                    (MiracleASTreeExpression) property.get(ctx.expression()),
                    ctx.IDENTIFIER().getText(),
                    new MiracleSourcePosition(ctx),
                    new MiracleSourcePosition(ctx.operator),
                    new MiracleSourcePosition(ctx.IDENTIFIER().getSymbol())
            ));
        }
    }
}
