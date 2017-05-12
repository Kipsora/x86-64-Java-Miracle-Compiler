package com.miracle.astree.visitor;

import com.miracle.astree.MiracleASTree;
import com.miracle.astree.expression.*;
import com.miracle.astree.statement.MiracleASTreeBlock;
import com.miracle.astree.statement.MiracleASTreeIteration;
import com.miracle.astree.statement.MiracleASTreeReturn;
import com.miracle.astree.statement.MiracleASTreeSelection;
import com.miracle.astree.statement.declaration.MiracleASTreeClassDeclaration;
import com.miracle.astree.statement.declaration.MiracleASTreeDeclaration;
import com.miracle.astree.statement.declaration.MiracleASTreeFunctionDeclaration;
import com.miracle.astree.statement.declaration.MiracleASTreeVariableDeclaration;
import com.miracle.astree.type.MiracleASTreeArrayType;
import com.miracle.astree.type.MiracleASTreeBaseType;
import com.miracle.astree.type.MiracleASTreeFunctionType;
import com.miracle.astree.type.MiracleASTreeVariableType;
import com.miracle.exception.MiracleExceptionContainer;
import com.miracle.symbol.MiracleSymbolTable;
import org.antlr.v4.runtime.atn.SemanticContext;

public class MiracleASTreeSemanticChecker extends MiracleASTreeBaseVisitor {
    private final MiracleExceptionContainer exceptionContainer;
    private MiracleSymbolTable symbolTable;
    public MiracleASTreeSemanticChecker(MiracleExceptionContainer exceptionContainer, MiracleSymbolTable symbolTable) {
        this.exceptionContainer = exceptionContainer;
        this.symbolTable = symbolTable;
    }

    private void checkType(MiracleASTreeVariableType type) {
        String identifier;
        if (type instanceof MiracleASTreeArrayType) {
            identifier = ((MiracleASTreeArrayType) type).baseType.identifier;
        } else {
            identifier = ((MiracleASTreeBaseType) type).identifier;
        }
        if (symbolTable.getBuiltinType(identifier) == null) {
            MiracleASTreeDeclaration declaration = symbolTable.getIncludeAncestor(identifier);
            if (declaration == null || !(declaration instanceof MiracleASTreeClassDeclaration)) {
                this.exceptionContainer.add("cannot find class whose name is \"" + identifier + "\"");
            }
        }
    }

    @Override
    public void visit(MiracleASTree miracleASTree) {
        miracleASTree.declarations.forEach((element) -> element.accept(this));
        if (symbolTable.get("main") == null || !(symbolTable.get("main") instanceof MiracleASTreeFunctionDeclaration)) {
            exceptionContainer.add("the main function is not existed");
        }
        MiracleASTreeFunctionDeclaration main = (MiracleASTreeFunctionDeclaration) symbolTable.get("main");
        if (!main.returnType.isSameType(MiracleSymbolTable.__builtin_int)) {
            exceptionContainer.add("the type of the return value of the main function must be int");
        }
        if (!main.parameters.isEmpty()) {
            exceptionContainer.add("the main function cannot have any parameter");
        }
    }

    @Override
    public void visit(MiracleASTreeClassDeclaration miracleASTreeClassDeclaration) {
        symbolTable = miracleASTreeClassDeclaration.scope;
        miracleASTreeClassDeclaration.variableDeclarations.forEach(element -> element.accept(this));
        miracleASTreeClassDeclaration.functionDeclarations.forEach(element -> element.accept(this));
        symbolTable = symbolTable.getParentSymbolTable();
    }

    @Override
    public void visit(MiracleASTreeVariableDeclaration miracleASTreeVariableDeclaration) {
        checkType(miracleASTreeVariableDeclaration.type);
        if (!symbolTable.put(miracleASTreeVariableDeclaration.identifier, miracleASTreeVariableDeclaration)) {
            this.exceptionContainer.add("duplicated declarations of identifier \""
                    + miracleASTreeVariableDeclaration.identifier + "\"");
        }
    }

    @Override
    public void visit(MiracleASTreeFunctionDeclaration miracleASTreeFunctionDeclaration) {
        if (!symbolTable.put(miracleASTreeFunctionDeclaration.identifier, miracleASTreeFunctionDeclaration)) {
            this.exceptionContainer.add("duplicated declarations of identifier \""
                    + miracleASTreeFunctionDeclaration.identifier + "\"");
        }

        checkType(miracleASTreeFunctionDeclaration.returnType);

        symbolTable = miracleASTreeFunctionDeclaration.scope;
        miracleASTreeFunctionDeclaration.parameters.forEach(element -> {
            if (!symbolTable.put(element.identifier, element)) {
                this.exceptionContainer.add("duplicated parameter declarations of identifier \""
                        + element.identifier + "\"");
            }
            checkType(element.type);
        });
        if (miracleASTreeFunctionDeclaration.body != null) {
            miracleASTreeFunctionDeclaration.body.forEach(element -> element.accept(this));
        }
        symbolTable = symbolTable.getParentSymbolTable();
    }


    @Override
    public void visit(MiracleASTreeSelection miracleASTreeSelection) {
        miracleASTreeSelection.branchTrue.accept(this);
        if (miracleASTreeSelection.branchFalse != null) {
            miracleASTreeSelection.branchFalse.accept(this);
        }
    }

    @Override
    public void visit(MiracleASTreeIteration miracleASTreeIteration) {
        miracleASTreeIteration.body.accept(this);
    }

    @Override
    public void visit(MiracleASTreeBlock miracleASTreeBlock) {
        symbolTable = miracleASTreeBlock.scope;
        miracleASTreeBlock.statements.forEach(element -> element.accept(this));
        symbolTable = symbolTable.getParentSymbolTable();
    }

    @Override
    public void visit(MiracleASTreeBinaryExpression miracleASTreeBinaryExpression) {
        miracleASTreeBinaryExpression.left.accept(this);
        miracleASTreeBinaryExpression.right.accept(this);
        if (miracleASTreeBinaryExpression.left.type == null || miracleASTreeBinaryExpression.right.type == null) {
            return;
        }
        if (!miracleASTreeBinaryExpression.operator.equals(MiracleASTreeBinaryExpression.OPERATOR.DOT)) {
            if (!(miracleASTreeBinaryExpression.left.type instanceof MiracleASTreeVariableType)) {
                exceptionContainer.add("cannot take operator \""
                        + miracleASTreeBinaryExpression.operator.toString()
                        + "\" with type "
                        + miracleASTreeBinaryExpression.left.type.toPrintableString());
                return;
            }
            if (!(miracleASTreeBinaryExpression.right.type instanceof MiracleASTreeVariableType)) {
                exceptionContainer.add("cannot take operator \""
                        + miracleASTreeBinaryExpression.operator.toString()
                        + "\" with type "
                        + miracleASTreeBinaryExpression.left.type.toPrintableString());
                return;
            }
            MiracleASTreeVariableType leftType = (MiracleASTreeVariableType) miracleASTreeBinaryExpression.left.type;
            MiracleASTreeVariableType rightType = (MiracleASTreeVariableType) miracleASTreeBinaryExpression.right.type;
            if (!leftType.isSameType(rightType)) {
                exceptionContainer.add("cannot take binary expression between two different types(\""
                        + leftType.toPrintableString() + "\" and \""
                        + rightType.toPrintableString() + "\")");
                return;
            }
            if (miracleASTreeBinaryExpression.operator.equals(MiracleASTreeBinaryExpression.OPERATOR.SUB) ||
                    miracleASTreeBinaryExpression.operator.equals(MiracleASTreeBinaryExpression.OPERATOR.MUL) ||
                    miracleASTreeBinaryExpression.operator.equals(MiracleASTreeBinaryExpression.OPERATOR.DIV) ||
                    miracleASTreeBinaryExpression.operator.equals(MiracleASTreeBinaryExpression.OPERATOR.MOD) ||
                    miracleASTreeBinaryExpression.operator.equals(MiracleASTreeBinaryExpression.OPERATOR.AND) ||
                    miracleASTreeBinaryExpression.operator.equals(MiracleASTreeBinaryExpression.OPERATOR.OR) ||
                    miracleASTreeBinaryExpression.operator.equals(MiracleASTreeBinaryExpression.OPERATOR.SHL) ||
                    miracleASTreeBinaryExpression.operator.equals(MiracleASTreeBinaryExpression.OPERATOR.SHR)) {
                if (!leftType.isSameType(MiracleSymbolTable.__builtin_int)) {
                    exceptionContainer.add("cannot take operator \""
                            + miracleASTreeBinaryExpression.operator.toString()
                            + "\" with type "
                            + miracleASTreeBinaryExpression.left.type.toPrintableString());
                    return;
                }
                if (!rightType.isSameType(MiracleSymbolTable.__builtin_int)) {
                    exceptionContainer.add("cannot take operator \""
                            + miracleASTreeBinaryExpression.operator.toString()
                            + "\" with type "
                            + miracleASTreeBinaryExpression.right.type.toPrintableString());
                    return;
                }
                miracleASTreeBinaryExpression.type = MiracleSymbolTable.__builtin_int;
            } else if (miracleASTreeBinaryExpression.operator.equals(MiracleASTreeBinaryExpression.OPERATOR.ADD)) {
                if (!leftType.isSameType(MiracleSymbolTable.__builtin_int) &&
                        !leftType.isSameType(MiracleSymbolTable.__builtin_string)) {
                    exceptionContainer.add("cannot take operator \""
                            + miracleASTreeBinaryExpression.operator.toString()
                            + "\" with type "
                            + miracleASTreeBinaryExpression.left.type.toPrintableString());
                    return;
                }
                if (!rightType.isSameType(MiracleSymbolTable.__builtin_int) &&
                        !rightType.isSameType(MiracleSymbolTable.__builtin_string)) {
                    exceptionContainer.add("cannot take operator \""
                            + miracleASTreeBinaryExpression.operator.toString()
                            + "\" with type "
                            + miracleASTreeBinaryExpression.right.type.toPrintableString());
                    return;
                }
                miracleASTreeBinaryExpression.type = miracleASTreeBinaryExpression.left.type;
            } else if (miracleASTreeBinaryExpression.operator.equals(MiracleASTreeBinaryExpression.OPERATOR.LT) ||
                    miracleASTreeBinaryExpression.operator.equals(MiracleASTreeBinaryExpression.OPERATOR.RT) ||
                    miracleASTreeBinaryExpression.operator.equals(MiracleASTreeBinaryExpression.OPERATOR.LEQ) ||
                    miracleASTreeBinaryExpression.operator.equals(MiracleASTreeBinaryExpression.OPERATOR.REQ) ||
                    miracleASTreeBinaryExpression.operator.equals(MiracleASTreeBinaryExpression.OPERATOR.EQL) ||
                    miracleASTreeBinaryExpression.operator.equals(MiracleASTreeBinaryExpression.OPERATOR.NEQ)) {
                if (!leftType.isSameType(MiracleSymbolTable.__builtin_int) &&
                        !leftType.isSameType(MiracleSymbolTable.__builtin_string)) {
                    exceptionContainer.add("cannot take operator \""
                            + miracleASTreeBinaryExpression.operator.toString()
                            + "\" with type "
                            + miracleASTreeBinaryExpression.left.type.toPrintableString());
                    return;
                }
                if (!rightType.isSameType(MiracleSymbolTable.__builtin_int) &&
                        !rightType.isSameType(MiracleSymbolTable.__builtin_string)) {
                    exceptionContainer.add("cannot take operator \""
                            + miracleASTreeBinaryExpression.operator.toString()
                            + "\" with type "
                            + miracleASTreeBinaryExpression.right.type.toPrintableString());
                    return;
                }
                miracleASTreeBinaryExpression.type = MiracleSymbolTable.__builtin_bool;
            } else if (miracleASTreeBinaryExpression.operator.equals(MiracleASTreeBinaryExpression.OPERATOR.XOR)) {
                if (!leftType.isSameType(MiracleSymbolTable.__builtin_int) &&
                        !leftType.isSameType(MiracleSymbolTable.__builtin_bool)) {
                    exceptionContainer.add("cannot take operator \""
                            + miracleASTreeBinaryExpression.operator.toString()
                            + "\" with type "
                            + miracleASTreeBinaryExpression.left.type.toPrintableString());
                    return;
                }
                if (!rightType.isSameType(MiracleSymbolTable.__builtin_int) &&
                        !rightType.isSameType(MiracleSymbolTable.__builtin_bool)) {
                    exceptionContainer.add("cannot take operator \""
                            + miracleASTreeBinaryExpression.operator.toString()
                            + "\" with type "
                            + miracleASTreeBinaryExpression.right.type.toPrintableString());
                    return;
                }
                miracleASTreeBinaryExpression.type = miracleASTreeBinaryExpression.left.type;
            } else if (miracleASTreeBinaryExpression.operator.equals(MiracleASTreeBinaryExpression.OPERATOR.CONJ) ||
                    miracleASTreeBinaryExpression.operator.equals(MiracleASTreeBinaryExpression.OPERATOR.DISJ)) {
                if (!leftType.isSameType(MiracleSymbolTable.__builtin_bool)) {
                    exceptionContainer.add("cannot take operator \""
                            + miracleASTreeBinaryExpression.operator.toString()
                            + "\" with type "
                            + miracleASTreeBinaryExpression.left.type.toPrintableString());
                    return;
                }
                if (!rightType.isSameType(MiracleSymbolTable.__builtin_bool)) {
                    exceptionContainer.add("cannot take operator \""
                            + miracleASTreeBinaryExpression.operator.toString()
                            + "\" with type "
                            + miracleASTreeBinaryExpression.right.type.toPrintableString());
                    return;
                }
                miracleASTreeBinaryExpression.type = miracleASTreeBinaryExpression.left.type;
            }
        } else {
            MiracleASTreeClassDeclaration classDeclaration =
                    (MiracleASTreeClassDeclaration) symbolTable.getIncludeAncestor(
                            miracleASTreeBinaryExpression.left.type.toPrintableString()
                    );
            if (classDeclaration == null) {
                exceptionContainer.add("cannot take operator \""
                        + miracleASTreeBinaryExpression.operator.toString()
                        + "\" with type "
                        + miracleASTreeBinaryExpression.left.type.toPrintableString());
                return;
            } else if (classDeclaration.get(((MiracleASTreeVariable) miracleASTreeBinaryExpression.right).identifier) == null) {
                exceptionContainer.add("class \""
                        + miracleASTreeBinaryExpression.left.type.toPrintableString()
                        + "\" has no member named \""
                        + ((MiracleASTreeVariable) miracleASTreeBinaryExpression.right).identifier
                        + "\"");
                return;
            }
            miracleASTreeBinaryExpression.type = classDeclaration.getType();
        }
    }

    @Override
    public void visit(MiracleASTreePrefixExpression miracleASTreePrefixExpression) {
        miracleASTreePrefixExpression.expression.accept(this);
        if (!(miracleASTreePrefixExpression.expression.type instanceof MiracleASTreeVariableType)) {
            exceptionContainer.add("cannot take operator \""
                    + miracleASTreePrefixExpression.operator.toString()
                    + "\" with type "
                    + miracleASTreePrefixExpression.expression.type.toPrintableString());
            return;
        }
        MiracleASTreeVariableType type = (MiracleASTreeVariableType) miracleASTreePrefixExpression.expression.type;
        if (miracleASTreePrefixExpression.operator.equals(MiracleASTreePrefixExpression.OPERATOR.INC) ||
                miracleASTreePrefixExpression.operator.equals(MiracleASTreePrefixExpression.OPERATOR.DEC) ||
                miracleASTreePrefixExpression.operator.equals(MiracleASTreePrefixExpression.OPERATOR.NEGATIVE) ||
                miracleASTreePrefixExpression.operator.equals(MiracleASTreePrefixExpression.OPERATOR.POSITIVE) ||
                miracleASTreePrefixExpression.operator.equals(MiracleASTreePrefixExpression.OPERATOR.REV)) {
            if (!type.isSameType(MiracleSymbolTable.__builtin_int)) {
                exceptionContainer.add("cannot take operator \""
                        + miracleASTreePrefixExpression.operator.toString()
                        + "\" with type "
                        + miracleASTreePrefixExpression.expression.type.toPrintableString());
                return;
            }
        } else {
            if (!type.isSameType(MiracleSymbolTable.__builtin_bool)) {
                exceptionContainer.add("cannot take operator \""
                        + miracleASTreePrefixExpression.operator.toString()
                        + "\" with type "
                        + miracleASTreePrefixExpression.expression.type.toPrintableString());
                return;
            }
        }
        miracleASTreePrefixExpression.type = miracleASTreePrefixExpression.expression.type;
    }

    @Override
    public void visit(MiracleASTreeSuffixExpression miracleASTreeSuffixExpression) {
        miracleASTreeSuffixExpression.expression.accept(this);
        if (!(miracleASTreeSuffixExpression.expression.type instanceof MiracleASTreeVariableType)) {
            exceptionContainer.add("cannot take operator \""
                    + miracleASTreeSuffixExpression.operator.toString()
                    + "\" with type "
                    + miracleASTreeSuffixExpression.expression.type.toPrintableString());
            return;
        }
        MiracleASTreeVariableType type = (MiracleASTreeVariableType) miracleASTreeSuffixExpression.expression.type;
        if (!type.isSameType(MiracleSymbolTable.__builtin_int)) {
            exceptionContainer.add("cannot take operator \""
                    + miracleASTreeSuffixExpression.operator.toString()
                    + "\" with type "
                    + miracleASTreeSuffixExpression.expression.type.toPrintableString());
            return;
        }
        miracleASTreeSuffixExpression.type = miracleASTreeSuffixExpression.expression.type;
    }

    @Override
    public void visit(MiracleASTreeVariable miracleASTreeVariable) {
        if (!symbolTable.containIncludeAncestor(miracleASTreeVariable.identifier)) {
            exceptionContainer.add("identifier \"" + miracleASTreeVariable.identifier + "\" was not in the scope");
            return;
        }
        miracleASTreeVariable.type = symbolTable.getIncludeAncestor(miracleASTreeVariable.identifier).getType();
    }

    @Override
    public void visit(MiracleASTreeCall miracleASTreeCall) {
        miracleASTreeCall.function.accept(this);
        miracleASTreeCall.parameters.forEach(element -> element.accept(this));
        if (miracleASTreeCall.function.type == null) {
            return;
        }
        if (!(miracleASTreeCall.function.type instanceof MiracleASTreeFunctionType)) {
            exceptionContainer.add("only function can be called");
            return;
        } else {
            miracleASTreeCall.type = ((MiracleASTreeFunctionType) miracleASTreeCall.function.type).returnType;
        }
        if (!((MiracleASTreeFunctionType) miracleASTreeCall.function.type).match(miracleASTreeCall.parameters)) {
            StringBuilder builder = new StringBuilder();
            builder.append("the origin type of the function is \"")
                    .append(miracleASTreeCall.function.type.toPrintableString())
                    .append("\", but the parameter type is \"(");
            for (int i = 0; i < miracleASTreeCall.parameters.size(); i++) {
                if (i != 0) {
                    builder.append(",");
                }
                if (miracleASTreeCall.parameters.get(i).type == null) {
                    return;
                }
                builder.append(miracleASTreeCall.parameters.get(i).type.toPrintableString());
            }
            builder.append(")");
            exceptionContainer.add(builder.toString());
        }
    }
}
