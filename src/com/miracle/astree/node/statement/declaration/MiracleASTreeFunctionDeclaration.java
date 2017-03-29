package com.miracle.astree.node.statement.declaration;

import com.miracle.astree.node.expression.value.MiracleASTreeValue;
import com.miracle.astree.node.statement.MiracleASTreeStatement;
import com.miracle.astree.visitor.MiracleASTreeVisitor;

import java.util.LinkedList;
import java.util.List;

public class MiracleASTreeFunctionDeclaration extends MiracleASTreeMemberDeclaration {
    private final List<MiracleASTreeStatement> body;
    private final MiracleASTreeTypename type;
    private final MiracleASTreeTypename rettype;
    private final List<MiracleASTreeVariableDeclaration> arguments;

    public MiracleASTreeFunctionDeclaration(String decorator, MiracleASTreeTypename rettype, String identifier,
                                            List<MiracleASTreeVariableDeclaration> arguments,
                                            List<MiracleASTreeStatement> body) {
        super(decorator, identifier);
        this.body = body;
        this.arguments = arguments;
        this.rettype = rettype;
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

    public List<MiracleASTreeStatement> getBody() {
        return body;
    }

    public MiracleASTreeTypename getType() {
        return type;
    }

    @Override
    public MiracleASTreeValue toValue() {
        return null;
    }

    public List<MiracleASTreeVariableDeclaration> getArguments() {
        return arguments;
    }

    public MiracleASTreeTypename getRetType() {
        return rettype;
    }

    @Override
    public DECTYPE getDeclarationType() {
        return DECTYPE.DEC_FUNC;
    }


}
