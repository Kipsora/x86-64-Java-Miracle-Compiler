package com.miracle.astree.node.statement.declaration;

import com.miracle.astree.node.expression.MiracleASTreeExpression;
import com.miracle.astree.node.expression.value.MiracleASTreeValue;
import com.miracle.astree.node.expression.value.MiracleASTreeVariable;
import com.miracle.astree.visitor.MiracleASTreeVisitor;
import com.miracle.exceptions.MiracleExceptionType;

public class MiracleASTreeVariableDeclaration extends MiracleASTreeMemberDeclaration {
    private final MiracleASTreeTypename type;
    private final MiracleASTreeExpression expression;

    public MiracleASTreeVariableDeclaration(String identifier, MiracleASTreeTypename type) {
        super(identifier);
        this.type = type;
        this.expression = null;
    }

    public MiracleASTreeVariableDeclaration(String identifier, MiracleASTreeTypename type, MiracleASTreeExpression expression) {
        super(identifier);
        this.type = type;
        this.expression = expression;
        if (expression.getType().equals("emptyobj")) {
            if (type.getDimension() == 1 && (type.getBasetype().equals("int")
                    || type.getBasetype().equals("string"))
                    || type.getBasetype().equals("boolean")) {
                throw new MiracleExceptionType("assignment", type.toString(),
                        expression.getType().toString());
            }
        } else if (!expression.getType().equals(type)) {
            throw new MiracleExceptionType("assignment", type.toString(),
                    expression.getType().toString());
        }
    }

    public MiracleASTreeVariableDeclaration(String decorator, String identifier, MiracleASTreeTypename type) {
        super(decorator, identifier);
        this.type = type;
        this.expression = null;
    }

    public MiracleASTreeVariableDeclaration(String decorator, String identifier, MiracleASTreeTypename type, MiracleASTreeExpression expression) {
        super(decorator, identifier);
        this.type = type;
        this.expression = expression;
        if (expression.getType().equals(new MiracleASTreeTypename("emptyobj"))) {
            if (type.getDimension() == 1 && (type.getBasetype().equals("int")
                    || type.getBasetype().equals("string"))
                    || type.getBasetype().equals("boolean")) {
                throw new MiracleExceptionType("assignment", type.toString(),
                        expression.getType().toString());
            }
        } else if (!expression.getType().equals(type)) {
            throw new MiracleExceptionType("assignment", type.toString(),
                    expression.getType().toString());
        }
    }

    public MiracleASTreeTypename getType() {
        return type;
    }

    @Override
    public MiracleASTreeValue toValue() {
        return new MiracleASTreeVariable(this);
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.enter();
        visitor.visit(this);
        visitor.exit();
    }

    public MiracleASTreeExpression getInitExpression() {
        return expression;
    }

    @Override
    public DECTYPE getDeclarationType() {
        return DECTYPE.DEC_VAR;
    }
}
