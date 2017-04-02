package com.miracle.astree.node.expression.binary;

import com.miracle.astree.node.expression.MiracleASTreeExpression;
import com.miracle.astree.node.expression.unary.suffix.MiracleASTreeSuffixIntegral;
import com.miracle.astree.node.statement.declaration.MiracleASTreeTypename;
import com.miracle.astree.visitor.MiracleASTreeVisitor;
import com.miracle.exceptions.MiracleExceptionSubscript;
import com.miracle.exceptions.MiracleExpressionSubscriptContent;

import static com.miracle.scanner.listener.MiracleRuntimeMaintainer.MiracleASTreeINT;

public class MiracleASTreeSubscript extends MiracleASTreeBinaryExpression {
    public MiracleASTreeSubscript(MiracleASTreeExpression head, MiracleASTreeExpression content) {
        super(new MiracleASTreeTypename(head.getType().getBasetype(),
                        head.getType().getDimension() - 1),
                head, "subscript", content, head.getMutable());
        if (head.getType().getDimension() == 0) {
            throw new MiracleExceptionSubscript();
        }
        if (content.getType().equals(MiracleASTreeINT)) {
            throw new MiracleExpressionSubscriptContent(content.getType().toString());
        }
    }

    @Override
    public void accept(MiracleASTreeVisitor visitor) {
        visitor.visit(this);
    }
}
