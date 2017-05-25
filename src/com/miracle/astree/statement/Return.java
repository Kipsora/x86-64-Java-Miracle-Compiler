package com.miracle.astree.statement;

import com.miracle.astree.statement.declaration.FunctionDeclaration;
import com.miracle.astree.statement.expression.Expression;
import com.miracle.astree.visitor.Visitor;
import com.miracle.cstree.SourcePosition;

public class Return extends Statement {
    public final Expression expression;
    private FunctionDeclaration function;

    public Return(Expression expression,
                  SourcePosition startPosition) {
        super(startPosition);
        this.expression = expression;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public FunctionDeclaration getFunction() {
        return function;
    }

    public void setFunction(FunctionDeclaration function) {
        this.function = function;
    }
}
