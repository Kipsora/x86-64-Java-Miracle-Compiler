package com.miracle.astree;

import com.miracle.astree.declaration.*;
import com.miracle.astree.expression.*;
import com.miracle.astree.statement.*;
import com.miracle.astree.type.MiracleASTreeArrayType;
import com.miracle.astree.type.MiracleASTreeBaseType;
import com.miracle.astree.type.MiracleASTreeType;
import com.miracle.astree.type.MiracleASTreeVariableType;
import com.miracle.astree.visitor.MiracleASTreeVisitor;
import com.miracle.cstree.parser.MiracleBaseListener;
import com.miracle.cstree.parser.MiracleParser;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

import java.util.LinkedList;
import java.util.List;

public class MiracleASTree extends MiracleASTreeNode {
    public final List<MiracleASTreeClassDeclaration> classDeclarations;
    public final List<MiracleASTreeFunctionDeclaration> functionDeclarations;
    public final List<MiracleASTreeVariableDeclaration> variableDeclarations;

    public MiracleASTree(List<MiracleASTreeClassDeclaration> classDeclarations,
                         List<MiracleASTreeFunctionDeclaration> functionDeclarations,
                         List<MiracleASTreeVariableDeclaration> variableDeclarations) {
        this.classDeclarations = classDeclarations;
        this.functionDeclarations = functionDeclarations;
        this.variableDeclarations = variableDeclarations;
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.visit(this);
    }

    public static class Builder extends MiracleBaseListener {
        private MiracleASTree root;
        private ParseTreeProperty<MiracleASTreeNode> property = new ParseTreeProperty<>();

        public MiracleASTree build() {
            return root;
        }

        @Override
        public void exitMiracle(MiracleParser.MiracleContext ctx) {
            List<MiracleASTreeClassDeclaration> classDeclarations = new LinkedList<>();
            List<MiracleASTreeVariableDeclaration> variableDeclarations = new LinkedList<>();
            List<MiracleASTreeFunctionDeclaration> functionDeclarations = new LinkedList<>();
            ctx.classDeclarationStatement().forEach((element) ->
                    classDeclarations.add((MiracleASTreeClassDeclaration) property.get(element))
            );
            ctx.functionDeclarationStatement().forEach((element) ->
                    functionDeclarations.add((MiracleASTreeFunctionDeclaration) property.get(element))
            );
            ctx.variableDeclarationStatement().forEach((element) ->
                    variableDeclarations.add((MiracleASTreeVariableDeclaration) property.get(element))
            );
            root = new MiracleASTree(classDeclarations, functionDeclarations, variableDeclarations);
        }

        @Override
        public void exitClassDeclarationStatement(MiracleParser.ClassDeclarationStatementContext ctx) {
            List<MiracleASTreeFunctionDeclaration> functionDeclarations = new LinkedList<>();
            List<MiracleASTreeVariableDeclaration> variableDeclarations = new LinkedList<>();
            ctx.constructorDeclarationStatement().forEach((element) ->
                    functionDeclarations.add((MiracleASTreeFunctionDeclaration) property.get(element))
            );
            ctx.functionDeclarationStatement().forEach((element) ->
                    functionDeclarations.add((MiracleASTreeFunctionDeclaration) property.get(element))
            );
            ctx.variableDeclarationStatement().forEach((element) ->
                    variableDeclarations.add((MiracleASTreeVariableDeclaration) property.get(element))
            );
            property.put(ctx, new MiracleASTreeClassDeclaration(ctx.IDENTIFIER().getText(),
                    functionDeclarations, variableDeclarations));
        }

        @Override
        public void exitFunctionDeclarationStatement(MiracleParser.FunctionDeclarationStatementContext ctx) {
            List<MiracleASTreeStatement> body = new LinkedList<>();
            List<MiracleASTreeVariableDeclaration> parameters = new LinkedList<>();
            ctx.statement().forEach((element) ->
                    body.add((MiracleASTreeStatement) property.get(element)));
            for (int i = 1; i < ctx.IDENTIFIER().size(); i++) {
                parameters.add(new MiracleASTreeVariableDeclaration(ctx.IDENTIFIER(i).getText(),
                        (MiracleASTreeVariableType) property.get(ctx.typename(i)),null));
            }
            property.put(ctx, new MiracleASTreeFunctionDeclaration(
                    ctx.IDENTIFIER().get(0).getText(),
                    (MiracleASTreeType) property.get(ctx.typename(0)),
                    parameters, body
            ));
        }

        @Override
        public void exitVariableDeclarationStatement(MiracleParser.VariableDeclarationStatementContext ctx) {
            property.put(ctx, new MiracleASTreeVariableDeclaration(ctx.IDENTIFIER().getText(),
                    (MiracleASTreeVariableType) property.get(ctx.typename()), null));
        }

        @Override
        public void exitConstructorDeclarationStatement(MiracleParser.ConstructorDeclarationStatementContext ctx) {
            List<MiracleASTreeStatement> body = new LinkedList<>();
            List<MiracleASTreeVariableDeclaration> parameters = new LinkedList<>();
            ctx.statement().forEach((element) ->
                    body.add((MiracleASTreeStatement) property.get(element)));
            property.put(ctx, new MiracleASTreeFunctionDeclaration(
                    null,
                    (MiracleASTreeType) property.get(ctx.typename()),
                    parameters, body
            ));
        }

        @Override
        public void exitTypename(MiracleParser.TypenameContext ctx) {
            MiracleASTreeBaseType baseType = new MiracleASTreeBaseType(
                    ctx.IDENTIFIER() == null ?
                            ctx.BASETYPE().getText() :
                            ctx.IDENTIFIER().getText()
            );
            if (ctx.getChildCount() == 1) {
                property.put(ctx, baseType);
            } else {
                property.put(ctx, new MiracleASTreeArrayType(
                        baseType,
                        (ctx.getChildCount() - 1) >> 1
                ));
            }
        }

        @Override
        public void exitBlockStatement(MiracleParser.BlockStatementContext ctx) {
            List<MiracleASTreeStatement> statements = new LinkedList<>();
            ctx.statement().forEach((element) ->
                    statements.add((MiracleASTreeStatement) property.get(element))
            );
            property.put(ctx, new MiracleASTreeBlock(statements));
        }

        @Override
        public void enterSelectionStatement(MiracleParser.SelectionStatementContext ctx) {
            property.put(ctx, new MiracleASTreeSelection(
                    (MiracleASTreeExpression) property.get(ctx.expression()),
                    (MiracleASTreeStatement) property.get(ctx.statement(0)),
                    ctx.statement().size() > 1 ? (MiracleASTreeStatement) property.get(ctx.statement(1)) : null)
            );
        }

        @Override
        public void exitForStatement(MiracleParser.ForStatementContext ctx) {
            MiracleASTreeExpression[] node = new MiracleASTreeExpression[3];
            for (int i = 0, j = 0; i < ctx.getChildCount(); i++) {
                if (ctx.getChild(i).getText().equals(";") || ctx.getChild(i).getText().equals(")")) {
                    if (!ctx.getChild(i - 1).getText().equals(";")
                            && !ctx.getChild(i - 1).getText().equals("(")) {
                        node[j] = (MiracleASTreeExpression) property.get(ctx.children.get(i - 1));
                    }
                    j++;
                }
            }
            property.put(ctx, new MiracleASTreeIteration(node[0], node[1], node[2],
                    (MiracleASTreeStatement) property.get(ctx.statement())));
        }

        @Override
        public void exitWhileStatement(MiracleParser.WhileStatementContext ctx) {
            property.put(ctx, new MiracleASTreeIteration(null,
                    (MiracleASTreeExpression) property.get(ctx.expression()),
                    null,
                    (MiracleASTreeStatement) property.get(ctx.statement()))
            );
        }

        @Override
        public void exitBreakStatement(MiracleParser.BreakStatementContext ctx) {
            property.put(ctx, new MiracleASTreeBreak());
        }

        @Override
        public void exitContinueStatement(MiracleParser.ContinueStatementContext ctx) {
            property.put(ctx, new MiracleASTreeContinue());
        }

        @Override
        public void exitReturnStatement(MiracleParser.ReturnStatementContext ctx) {
            property.put(ctx, new MiracleASTreeReturn(ctx.expression() == null ? null :
                    (MiracleASTreeExpression) property.get(ctx.expression()))
            );
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
            property.put(ctx, new MiracleASTreeVariable(ctx.IDENTIFIER().getText()));
        }

        @Override
        public void exitBraceExpression(MiracleParser.BraceExpressionContext ctx) {
            property.put(ctx, property.get(ctx.expression()));
        }

        @Override
        public void exitFunctionCallExpression(MiracleParser.FunctionCallExpressionContext ctx) {
            List<MiracleASTreeExpression> parameters = new LinkedList<>();
            for (int i = 1; i < ctx.expression().size(); i++) {
                parameters.add((MiracleASTreeExpression) property.get(ctx.expression(i)));
            }
            property.put(ctx, new MiracleASTreeCall(
                    (MiracleASTreeExpression) property.get(ctx.expression(0)),
                    parameters
            ));
        }

        @Override
        public void exitSubscriptExpression(MiracleParser.SubscriptExpressionContext ctx) {
            property.put(ctx, new MiracleASTreeSubscript(
                    (MiracleASTreeExpression) property.get(ctx.expression(0)),
                    (MiracleASTreeExpression) property.get(ctx.expression(1))
            ));
        }

        @Override
        public void exitBinaryExpression(MiracleParser.BinaryExpressionContext ctx) {
            MiracleASTreeBinaryExpression.OPERATOR operator = null;
            switch (ctx.operator.getText()) {
                case "+": operator = MiracleASTreeBinaryExpression.OPERATOR.ADD; break;
                case "-": operator = MiracleASTreeBinaryExpression.OPERATOR.SUB; break;
                case "*": operator = MiracleASTreeBinaryExpression.OPERATOR.MUL; break;
                case "/": operator = MiracleASTreeBinaryExpression.OPERATOR.DIV; break;
                case "%": operator = MiracleASTreeBinaryExpression.OPERATOR.MOD; break;
                case ".": operator = MiracleASTreeBinaryExpression.OPERATOR.DOT; break;
                case "<<": operator = MiracleASTreeBinaryExpression.OPERATOR.SHL; break;
                case ">>": operator = MiracleASTreeBinaryExpression.OPERATOR.SHR; break;
                case "<": operator = MiracleASTreeBinaryExpression.OPERATOR.LT; break;
                case ">": operator = MiracleASTreeBinaryExpression.OPERATOR.RT; break;
                case "<=": operator = MiracleASTreeBinaryExpression.OPERATOR.LEQ; break;
                case ">=": operator = MiracleASTreeBinaryExpression.OPERATOR.REQ; break;
                case "==": operator = MiracleASTreeBinaryExpression.OPERATOR.EQL; break;
                case "!=": operator = MiracleASTreeBinaryExpression.OPERATOR.NEQ; break;
                case "&": operator = MiracleASTreeBinaryExpression.OPERATOR.AND; break;
                case "|": operator = MiracleASTreeBinaryExpression.OPERATOR.OR; break;
                case "^": operator = MiracleASTreeBinaryExpression.OPERATOR.XOR; break;
                case "&&": operator = MiracleASTreeBinaryExpression.OPERATOR.CONJ; break;
                case "||": operator = MiracleASTreeBinaryExpression.OPERATOR.DISJ; break;
                case "=": operator = MiracleASTreeBinaryExpression.OPERATOR.ASS; break;
                default:
                    throw new RuntimeException("unsupported operator");
            }
            property.put(ctx, new MiracleASTreeBinaryExpression(
                    (MiracleASTreeExpression) property.get(ctx.expression(0)),
                    operator,
                    (MiracleASTreeExpression) property.get(ctx.expression(1))
            ));
        }

        @Override
        public void exitSuffixExpression(MiracleParser.SuffixExpressionContext ctx) {
            MiracleASTreePrefixExpression.OPERATOR operator = null;
            switch (ctx.operator.getText()) {
                case "!": operator = MiracleASTreePrefixExpression.OPERATOR.NEGATE; break;
                case "~": operator = MiracleASTreePrefixExpression.OPERATOR.REV; break;
                case "+": operator = MiracleASTreePrefixExpression.OPERATOR.POSITIVE; break;
                case "-": operator = MiracleASTreePrefixExpression.OPERATOR.NEGATIVE; break;
                case "++": operator = MiracleASTreePrefixExpression.OPERATOR.INC; break;
                case "--": operator = MiracleASTreePrefixExpression.OPERATOR.DEC; break;
                default:
                    throw new RuntimeException("unsupported operator");
            }
            property.put(ctx, new MiracleASTreePrefixExpression(operator,
                    (MiracleASTreeExpression) property.get(ctx.expression())
            ));
        }

        @Override
        public void enterSuffixExpression(MiracleParser.SuffixExpressionContext ctx) {
            MiracleASTreeSuffixExpression.OPERATOR operator = null;
            switch (ctx.operator.getText()) {
                case "++": operator = MiracleASTreeSuffixExpression.OPERATOR.INC; break;
                case "--": operator = MiracleASTreeSuffixExpression.OPERATOR.DEC; break;
                default:
                    throw new RuntimeException("unsupported operator");
            }
            property.put(ctx, new MiracleASTreeSuffixExpression(
                    (MiracleASTreeExpression) property.get(ctx.expression()),
                    operator
            ));
        }

        @Override
        public void exitNewExpression(MiracleParser.NewExpressionContext ctx) {
            MiracleASTreeVariableType type = (MiracleASTreeVariableType) property.get(ctx.typename());
            List<MiracleASTreeExpression> expressions = new LinkedList<>();
            for (int i = 0; i < ctx.getChildCount(); i++) {
                if (ctx.getChild(i).getText().equals("]")) {
                    if (ctx.getChild(i).getText().equals("[")) {
                        expressions.add(null);
                    } else {
                        expressions.add((MiracleASTreeExpression) property.get(ctx.getChild(i)));
                    }
                }
            }
            property.put(ctx, new MiracleASTreeNew(type, expressions));
        }

        @Override
        public void exitIntegerConstant(MiracleParser.IntegerConstantContext ctx) {
            property.put(ctx, new MiracleASTreeIntegerConstant(ctx.INTEGER().getText()));
        }

        @Override
        public void exitBoolConstant(MiracleParser.BoolConstantContext ctx) {
            property.put(ctx, new MiracleASTreeBooleanConstant(ctx.getText()));
        }

        @Override
        public void exitStringConstant(MiracleParser.StringConstantContext ctx) {
            property.put(ctx, new MiracleASTreeStringConstant(ctx.getText()));
        }

        @Override
        public void exitNullConstant(MiracleParser.NullConstantContext ctx) {
            property.put(ctx, new MiracleASTreeNull());
        }
    }
}
