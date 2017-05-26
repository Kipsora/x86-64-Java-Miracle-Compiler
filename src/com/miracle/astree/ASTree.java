package com.miracle.astree;

import com.miracle.astree.base.Node;
import com.miracle.astree.base.TypeNode;
import com.miracle.astree.statement.*;
import com.miracle.astree.statement.declaration.ClassDeclaration;
import com.miracle.astree.statement.declaration.Declaration;
import com.miracle.astree.statement.declaration.FunctionDeclaration;
import com.miracle.astree.statement.declaration.VariableDeclaration;
import com.miracle.astree.statement.expression.*;
import com.miracle.astree.statement.expression.constant.BooleanConstant;
import com.miracle.astree.statement.expression.constant.IntegerConstant;
import com.miracle.astree.statement.expression.constant.NullConstant;
import com.miracle.astree.statement.expression.constant.StringConstant;
import com.miracle.astree.visitor.ASTreeVisitor;
import com.miracle.cstree.SourcePosition;
import com.miracle.cstree.parser.MiracleBaseListener;
import com.miracle.cstree.parser.MiracleParser;
import com.miracle.exception.ExceptionContainer;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ASTree extends Node {
    public final List<Declaration> declarations;

    ASTree(List<Declaration> declarations) {
        this.declarations = Collections.unmodifiableList(declarations);
    }

    @Override
    public void accept(ASTreeVisitor ASTreeVisitor) {
        ASTreeVisitor.visit(this);
    }

    public static class Builder extends MiracleBaseListener {
        private final ExceptionContainer exceptionContainer;
        private ASTree root;
        private ParseTreeProperty<Object> property = new ParseTreeProperty<>();

        public Builder(ExceptionContainer exceptionContainer) {
            this.exceptionContainer = exceptionContainer;
        }

        public ASTree build() {
            return root;
        }

        @Override
        public void exitMiracle(MiracleParser.MiracleContext ctx) {
            List<Declaration> declarations = new LinkedList<>();

            List<ParseTree> children = ctx.children;
            for (int i = 0, childrenSize = children.size(); i < childrenSize; i++) {
                ParseTree element = children.get(i);
                if (property.get(element) != null) {
                    declarations.add((Declaration) property.get(element));
                }
            }

            root = new ASTree(declarations);
        }

        @Override
        public void exitClassDeclarationStatement(MiracleParser.ClassDeclarationStatementContext ctx) {
            List<VariableDeclaration> variableDeclarations = new LinkedList<>();
            List<FunctionDeclaration> functionDeclarations = new LinkedList<>();
            FunctionDeclaration constructorDeclaration = null;

            List<MiracleParser.VariableDeclarationStatementContext> variableDeclarationStatement = ctx.variableDeclarationStatement();
            for (int i = 0, size = variableDeclarationStatement.size(); i < size; i++) {
                MiracleParser.VariableDeclarationStatementContext element = variableDeclarationStatement.get(i);
                variableDeclarations.add((VariableDeclaration) property.get(element));
            }

            List<MiracleParser.FunctionDeclarationStatementContext> functionDeclarationStatement = ctx.functionDeclarationStatement();
            for (int i = 0, size = functionDeclarationStatement.size(); i < size; i++) {
                MiracleParser.FunctionDeclarationStatementContext element = functionDeclarationStatement.get(i);
                if (element.returnType == null) {
                    if (constructorDeclaration != null) {
                        exceptionContainer.add("multiple declarations of constructors in class `" + ctx.IDENTIFIER().getText() + " `are found",
                                new SourcePosition(element.typename(0)));
                    } else {
                        constructorDeclaration = (FunctionDeclaration) property.get(element);
                    }
                } else {
                    functionDeclarations.add((FunctionDeclaration) property.get(element));
                }
            }

            property.put(ctx, new ClassDeclaration(
                    ctx.IDENTIFIER().getText(),
                    variableDeclarations,
                    functionDeclarations,
                    constructorDeclaration,
                    new SourcePosition(ctx),
                    new SourcePosition(ctx.IDENTIFIER().getSymbol())
            ));
        }

        @Override
        public void exitFunctionDeclarationStatement(MiracleParser.FunctionDeclarationStatementContext ctx) {
            List<Statement> body = new LinkedList<>();
            List<VariableDeclaration> parameters = new LinkedList<>();

            List<MiracleParser.StatementContext> statement = ctx.statement();
            for (int i = 0, statementSize = statement.size(); i < statementSize; i++) {
                MiracleParser.StatementContext element = statement.get(i);
                if (property.get(element) != null) { // empty statement
                    body.add((Statement) property.get(element));
                }
            }

            for (int i = 1, size = ctx.IDENTIFIER().size(); i < size; i++) {
                parameters.add(new VariableDeclaration(
                        ctx.IDENTIFIER(i).getText(),
                        (TypeNode) property.get(ctx.typename(i)),
                        null,
                        new SourcePosition(ctx.typename(i)),
                        new SourcePosition(ctx.IDENTIFIER(i).getSymbol())
                ));
            }
            if (ctx.returnType == null) {
                property.put(ctx, new FunctionDeclaration(
                        ctx.IDENTIFIER(0).getText(),
                        null,
                        parameters,
                        body,
                        new SourcePosition(ctx),
                        null
                ));
            } else {
                property.put(ctx, new FunctionDeclaration(
                        ctx.IDENTIFIER().get(0).getText(),
                        (TypeNode) property.get(ctx.typename(0)),
                        parameters,
                        body,
                        new SourcePosition(ctx),
                        new SourcePosition(ctx.IDENTIFIER(0).getSymbol())
                ));
            }
        }

        @Override
        public void exitVariableDeclarationStatement(MiracleParser.VariableDeclarationStatementContext ctx) {
            TerminalNode identifier = ctx.IDENTIFIER();
            if (ctx.expression() != null) {
                property.put(ctx, new VariableDeclaration(
                        identifier.getText(),
                        (TypeNode) property.get(ctx.typename()),
                        (Expression) property.get(ctx.expression()),
                        new SourcePosition(ctx),
                        new SourcePosition(identifier.getSymbol())
                ));
            } else {
                property.put(ctx, new VariableDeclaration(
                        identifier.getText(),
                        (TypeNode) property.get(ctx.typename()),
                        null,
                        new SourcePosition(ctx),
                        new SourcePosition(identifier.getSymbol())
                ));
            }
        }

        @Override
        public void exitTypename(MiracleParser.TypenameContext ctx) {
            property.put(ctx, new TypeNode(
                    ctx.basetype().getText(),
                    (ctx.getChildCount() - 1) >> 1,
                    new SourcePosition(ctx.basetype())
            ));
        }

        @Override
        public void exitBlockStatement(MiracleParser.BlockStatementContext ctx) {
            List<Statement> statements = new LinkedList<>();

            List<MiracleParser.StatementContext> statement = ctx.statement();
            for (int i = 0, statementSize = statement.size(); i < statementSize; i++) {
                MiracleParser.StatementContext element = statement.get(i);
                if (property.get(element) != null) {
                    statements.add((Statement) property.get(element));
                }
            }

            property.put(ctx, new Block(
                    statements,
                    new SourcePosition(ctx)
            ));
        }

        @Override
        public void exitSelectionStatement(MiracleParser.SelectionStatementContext ctx) {
            Statement trueStatement = (Statement) property.get(ctx.statement(0));
            Statement falseStatement = (ctx.statement().size() > 1 ? (Statement) property.get(ctx.statement(1)) : null);
            if (trueStatement != null && !(trueStatement instanceof Block)) {
                Statement finalTrueStatement = trueStatement;
                trueStatement = new Block(new LinkedList<Statement>() {
                    private static final long serialVersionUID = -694271397082132527L;

                    {
                        add(finalTrueStatement);
                    }
                }, trueStatement.position);
            }
            if (falseStatement != null && !(falseStatement instanceof Block)) {
                Statement finalFalseStatement = falseStatement;
                falseStatement = new Block(new LinkedList<Statement>() {
                    private static final long serialVersionUID = -694271397082132527L;

                    {
                        add(finalFalseStatement);
                    }
                }, falseStatement.position);
            }
            property.put(ctx, new Selection(
                    (Expression) property.get(ctx.expression()),
                    trueStatement,
                    falseStatement,
                    new SourcePosition(ctx)
            ));
        }

        @Override
        public void exitForStatement(MiracleParser.ForStatementContext ctx) {
            Expression[] node = new Expression[3];
            for (int i = 0, j = 0, size = ctx.getChildCount(); i < size && j < 3; i++) {
                if (ctx.getChild(i).getText().equals(";") || ctx.getChild(i).getText().equals(")")) {
                    if (!ctx.getChild(i - 1).getText().equals(";")
                            && !ctx.getChild(i - 1).getText().equals("(")) {
                        node[j] = (Expression) property.get(ctx.children.get(i - 1));
                    }
                    j++;
                }
            }
            Statement statement = (Statement) property.get(ctx.statement());
            if (statement != null && !(statement instanceof Block)) {
                Statement finalStatement = statement;
                statement = new Block(new LinkedList<Statement>() {
                    private static final long serialVersionUID = 6113359493637144909L;

                    {
                        add(finalStatement);
                    }
                }, statement.position);
            }
            property.put(ctx, new Iteration(
                    node[0],
                    node[1],
                    node[2],
                    statement,
                    new SourcePosition(ctx)
            ));
        }

        @Override
        public void exitWhileStatement(MiracleParser.WhileStatementContext ctx) {
            property.put(ctx, new Iteration(null,
                    (Expression) property.get(ctx.expression()),
                    null,
                    (Statement) property.get(ctx.statement()),
                    new SourcePosition(ctx)
            ));
        }

        @Override
        public void exitBreakStatement(MiracleParser.BreakStatementContext ctx) {
            property.put(ctx, new Break(new SourcePosition(ctx)));
        }

        @Override
        public void exitContinueStatement(MiracleParser.ContinueStatementContext ctx) {
            property.put(ctx, new Continue(new SourcePosition(ctx)));
        }

        @Override
        public void exitReturnStatement(MiracleParser.ReturnStatementContext ctx) {
            property.put(ctx, new ReturnStatement(ctx.expression() == null ? null :
                    (Expression) property.get(ctx.expression()),
                    new SourcePosition(ctx)
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
            property.put(ctx, new Variable(
                    ctx.IDENTIFIER().getText(),
                    new SourcePosition(ctx)
            ));
        }

        @Override
        public void exitBraceExpression(MiracleParser.BraceExpressionContext ctx) {
            property.put(ctx, property.get(ctx.expression()));
        }

        @Override
        public void exitFunctionCallExpression(MiracleParser.FunctionCallExpressionContext ctx) {
            List<Expression> parameters = new LinkedList<>();
            for (int i = 1, size = ctx.expression().size(); i < size; i++) {
                parameters.add((Expression) property.get(ctx.expression(i)));
            }
            property.put(ctx, new CallExpression(
                    (Expression) property.get(ctx.expression(0)),
                    parameters,
                    new SourcePosition(ctx)
            ));
        }

        @Override
        public void exitSubscriptExpression(MiracleParser.SubscriptExpressionContext ctx) {
            property.put(ctx, new Subscript(
                    (Expression) property.get(ctx.expression(0)),
                    (Expression) property.get(ctx.expression(1)),
                    new SourcePosition(ctx)
            ));
        }

        @Override
        public void exitBinaryExpression(MiracleParser.BinaryExpressionContext ctx) {
            BinaryExpression.OPERATOR operator;
            switch (ctx.operator.getText()) {
                case "+":
                    operator = BinaryExpression.OPERATOR.ADD;
                    break;
                case "-":
                    operator = BinaryExpression.OPERATOR.SUB;
                    break;
                case "*":
                    operator = BinaryExpression.OPERATOR.MUL;
                    break;
                case "/":
                    operator = BinaryExpression.OPERATOR.DIV;
                    break;
                case "%":
                    operator = BinaryExpression.OPERATOR.MOD;
                    break;
                case "<<":
                    operator = BinaryExpression.OPERATOR.SHL;
                    break;
                case ">>":
                    operator = BinaryExpression.OPERATOR.SHR;
                    break;
                case "<":
                    operator = BinaryExpression.OPERATOR.LT;
                    break;
                case ">":
                    operator = BinaryExpression.OPERATOR.RT;
                    break;
                case "<=":
                    operator = BinaryExpression.OPERATOR.LEQ;
                    break;
                case ">=":
                    operator = BinaryExpression.OPERATOR.REQ;
                    break;
                case "==":
                    operator = BinaryExpression.OPERATOR.EQL;
                    break;
                case "!=":
                    operator = BinaryExpression.OPERATOR.NEQ;
                    break;
                case "&":
                    operator = BinaryExpression.OPERATOR.AND;
                    break;
                case "|":
                    operator = BinaryExpression.OPERATOR.OR;
                    break;
                case "^":
                    operator = BinaryExpression.OPERATOR.XOR;
                    break;
                case "&&":
                    operator = BinaryExpression.OPERATOR.CONJ;
                    break;
                case "||":
                    operator = BinaryExpression.OPERATOR.DISJ;
                    break;
                case "=":
                    operator = BinaryExpression.OPERATOR.ASS;
                    break;
                default:
                    throw new RuntimeException("unsupported operator");
            }
            property.put(ctx, new BinaryExpression(
                    (Expression) property.get(ctx.expression(0)),
                    operator,
                    (Expression) property.get(ctx.expression(1)),
                    new SourcePosition(ctx),
                    new SourcePosition(ctx.operator)
            ));
        }

        @Override
        public void exitPrefixExpression(MiracleParser.PrefixExpressionContext ctx) {
            PrefixExpression.OPERATOR operator;
            switch (ctx.operator.getText()) {
                case "!":
                    operator = PrefixExpression.OPERATOR.NEGATE;
                    break;
                case "~":
                    operator = PrefixExpression.OPERATOR.REV;
                    break;
                case "+":
                    operator = PrefixExpression.OPERATOR.POSITIVE;
                    break;
                case "-":
                    operator = PrefixExpression.OPERATOR.NEGATIVE;
                    break;
                case "++":
                    operator = PrefixExpression.OPERATOR.INC;
                    break;
                case "--":
                    operator = PrefixExpression.OPERATOR.DEC;
                    break;
                default:
                    throw new RuntimeException("unsupported operator");
            }
            property.put(ctx, new PrefixExpression(
                    operator,
                    (Expression) property.get(ctx.expression()),
                    new SourcePosition(ctx),
                    new SourcePosition(ctx.operator)
            ));
        }

        @Override
        public void exitSuffixExpression(MiracleParser.SuffixExpressionContext ctx) {
            SuffixExpression.OPERATOR operator;
            switch (ctx.operator.getText()) {
                case "++":
                    operator = SuffixExpression.OPERATOR.INC;
                    break;
                case "--":
                    operator = SuffixExpression.OPERATOR.DEC;
                    break;
                default:
                    throw new RuntimeException("unsupported operator");
            }
            property.put(ctx, new SuffixExpression(
                    (Expression) property.get(ctx.expression()),
                    operator,
                    new SourcePosition(ctx),
                    new SourcePosition(ctx.operator)
            ));
        }

        @Override
        public void exitNewExpression(MiracleParser.NewExpressionContext ctx) {
            List<Expression> expressions = new LinkedList<>();
            for (int i = 0, size = ctx.getChildCount(); i < size; i++) {
                if (ctx.getChild(i).getText().equals("]")) {
                    if (ctx.getChild(i - 1).getText().equals("[")) {
                        expressions.add(null);
                    } else {
                        expressions.add((Expression) property.get(ctx.getChild(i - 1)));
                    }
                }
            }
            property.put(ctx, new New(
                    (TypeNode) property.get(ctx.typename()),
                    expressions,
                    new SourcePosition(ctx)
            ));
        }

        @Override
        public void exitIntegerConstant(MiracleParser.IntegerConstantContext ctx) {
            property.put(ctx, new IntegerConstant(ctx.INTEGER().getText(),
                    new SourcePosition(ctx)));
        }

        @Override
        public void exitBoolConstant(MiracleParser.BoolConstantContext ctx) {
            property.put(ctx, new BooleanConstant(
                    ctx.getText(),
                    new SourcePosition(ctx)
            ));
        }

        @Override
        public void exitStringConstant(MiracleParser.StringConstantContext ctx) {
            property.put(ctx, new StringConstant(
                    ctx.getText(),
                    new SourcePosition(ctx)
            ));
        }

        @Override
        public void exitNullConstant(MiracleParser.NullConstantContext ctx) {
            property.put(ctx, new NullConstant(new SourcePosition(ctx)));
        }

        @Override
        public void exitStatement(MiracleParser.StatementContext ctx) {
            property.put(ctx, property.get(ctx.getChild(0)));
        }

        @Override
        public void exitThisExpression(MiracleParser.ThisExpressionContext ctx) {
            property.put(ctx, new This(new SourcePosition(ctx)));
        }

        @Override
        public void exitFieldExpression(MiracleParser.FieldExpressionContext ctx) {
            property.put(ctx, new Field(
                    (Expression) property.get(ctx.expression()),
                    ctx.IDENTIFIER().getText(),
                    new SourcePosition(ctx),
                    new SourcePosition(ctx.operator),
                    new SourcePosition(ctx.IDENTIFIER().getSymbol())
            ));
        }
    }
}
