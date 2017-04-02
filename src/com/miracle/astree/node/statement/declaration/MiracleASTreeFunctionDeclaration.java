package com.miracle.astree.node.statement.declaration;

import com.miracle.astree.node.expression.value.MiracleASTreeFunction;
import com.miracle.astree.node.expression.value.MiracleASTreeValue;
import com.miracle.astree.node.statement.MiracleASTreeStatement;
import com.miracle.astree.visitor.MiracleASTreeVisitor;

import java.util.LinkedList;
import java.util.List;

public class MiracleASTreeFunctionDeclaration extends MiracleASTreeMemberDeclaration {
    private MiracleASTreeTypename type;
    private final MiracleASTreeTypename rettype;
    private final List<MiracleASTreeVariableDeclaration> arguments;
    private List<MiracleASTreeStatement> body; // null represents built-in functions

    public MiracleASTreeFunctionDeclaration(MiracleASTreeTypename rettype, String identifier,
                                            List<MiracleASTreeVariableDeclaration> arguments) {
        super(identifier);
        this.rettype = rettype;
        this.arguments = arguments;
        List<MiracleASTreeTypename> tmp = new LinkedList<>();
        for (MiracleASTreeVariableDeclaration entry : arguments) {
            tmp.add(entry.getType());
        }
        this.type = new MiracleASTreeTypename(rettype.getBasetype(), rettype.getDimension(), tmp);
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.enter();
        visitor.visit(this);
        visitor.exit();
    }

    public void setBody(List<MiracleASTreeStatement> body) {
        this.body = body;
    }

    public List<MiracleASTreeStatement> getBody() {
        return body;
    }

    public MiracleASTreeTypename getType() {
        return type;
    }

    @Override
    public MiracleASTreeValue toValue() {
        return new MiracleASTreeFunction(this);
    }

    public List<MiracleASTreeVariableDeclaration> getArguments() {
        return arguments;
    }

    public MiracleASTreeTypename getReturnType() {
        return rettype;
    }

    @Override
    public DECTYPE getDeclarationType() {
        return DECTYPE.DEC_FUNC;
    }
}
