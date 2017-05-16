package com.miracle.astree.visitor;

import com.miracle.astree.MiracleASTree;
import com.miracle.astree.base.MiracleASTreeTypeNode;
import com.miracle.astree.statement.*;
import com.miracle.astree.statement.declaration.*;
import com.miracle.astree.statement.expression.*;
import com.miracle.astree.statement.expression.constant.MiracleASTreeBooleanConstant;
import com.miracle.astree.statement.expression.constant.MiracleASTreeIntegerConstant;
import com.miracle.astree.statement.expression.constant.MiracleASTreeNullConstant;
import com.miracle.astree.statement.expression.constant.MiracleASTreeStringConstant;
import com.miracle.cstree.MiracleSourcePosition;
import com.miracle.exception.MiracleExceptionContainer;
import com.miracle.symbol.MiracleSymbolTable;
import com.miracle.symbol.type.MiracleArrayType;
import com.miracle.symbol.type.MiracleBaseType;
import com.miracle.symbol.type.MiracleFunctionType;
import com.miracle.symbol.type.MiracleType;

import java.util.Stack;

import static com.miracle.symbol.MiracleSymbolTable.*;

public class MiracleASTreeSemanticAnalyser implements MiracleASTreeVisitor {
    private final MiracleExceptionContainer exceptionContainer;
    private MiracleSymbolTable symbolTable;

    private Stack<MiracleASTreeClassDeclaration> classStack = new Stack<>();
    private Stack<MiracleASTreeFunctionDeclaration> funcStack = new Stack<>();
    private Stack<MiracleASTreeIteration> loopStack = new Stack<>();

    public MiracleASTreeSemanticAnalyser(MiracleExceptionContainer exceptionContainer,
                                         MiracleSymbolTable symbolTable) {
        this.exceptionContainer = exceptionContainer;
        this.symbolTable = symbolTable;
    }

    @Override
    public void visit(MiracleASTreeFunctionDeclaration functionDeclaration) {
        funcStack.add(functionDeclaration);
        symbolTable = functionDeclaration.getScope();

        MiracleBaseType baseType = functionDeclaration.returnType.type.getBaseType();
        if (symbolTable == null || baseType.identifier == null) {
            System.out.println(functionDeclaration.identifier);
        }
        if (symbolTable.getClassIncludeAncestor(baseType.identifier) == null) {
            exceptionContainer.add("cannot find class `" + baseType.identifier + "`",
                    functionDeclaration.returnType.startPosition);
        }

        functionDeclaration.parameters.forEach(e -> e.accept(this));
        if (functionDeclaration.body != null) {
            functionDeclaration.body.forEach(e -> e.accept(this));
        }
        symbolTable = symbolTable.getParentSymbolTable();
        funcStack.pop();
    }

    @Override
    public void visit(MiracleASTreeClassDeclaration classDeclaration) {
        symbolTable = classDeclaration.getScope();
        classStack.push(classDeclaration);
        if (classDeclaration.constructorDeclaration != null) {
            classDeclaration.constructorDeclaration.accept(this);
        }
        if (classDeclaration.functionDeclarations != null) {
            classDeclaration.functionDeclarations.forEach(element -> element.accept(this));
        }
        if (classDeclaration.variableDeclarations != null) {
            classDeclaration.variableDeclarations.forEach(element -> element.accept(this));
        }
        classStack.pop();
        symbolTable = symbolTable.getParentSymbolTable();
    }

    @Override
    public void visit(MiracleASTree astree) {
        astree.declarations.forEach(element -> element.accept(this));
        MiracleASTreeFunctionDeclaration function = symbolTable.getFunction("main");
        if (function == null) {
            exceptionContainer.add("the main function is not found",
                    null);
        } else {
            if (!function.returnType.type.isSameType(__builtin_int)) {
                exceptionContainer.add("the main function must return `int`",
                        function.returnType.startPosition);
            }
            if (!function.parameters.isEmpty()) {
                exceptionContainer.add("the parameters of the main function must be empty",
                        function.parameters.get(0).startPosition);
            }
        }
    }

    @Override
    public void visit(MiracleASTreeVariableDeclaration variableDeclaration) {
        MiracleBaseType baseType = variableDeclaration.typenode.type.getBaseType();
        boolean flag = true;
        if (symbolTable.getClassIncludeAncestor(baseType.identifier) == null) {
            exceptionContainer.add("cannot find class `" + baseType.identifier + "`",
                    variableDeclaration.typenode.startPosition);
            flag = false;
        } else if (baseType.isSameType(__builtin_void)) {
            exceptionContainer.add("cannot declare a variable of type `void`",
                    variableDeclaration.typenode.startPosition);
            flag = false;
        }
        if (variableDeclaration.expression != null) {
            variableDeclaration.expression.accept(this);
            if (variableDeclaration.expression.getResultType() != null &&
                    !variableDeclaration.typenode.type.isSameType(variableDeclaration.expression.getResultType())) {
                exceptionContainer.add("the type of initialization expression differs from declaration",
                        variableDeclaration.expression.startPosition);
                flag = false;
            }
        }
        if (flag && !variableDeclaration.isMember) {
            if (!symbolTable.put(variableDeclaration.identifier, variableDeclaration)) {
                this.exceptionContainer.add("duplicate declarations of identifier \""
                                + variableDeclaration.identifier + "\"",
                        variableDeclaration.identifierPosition
                );
            }
        }
    }

    @Override
    public void visit(MiracleASTreeBlock block) {
        symbolTable = block.getScope();
        block.statements.forEach(element -> element.accept(this));
        symbolTable = symbolTable.getParentSymbolTable();
    }

    @Override
    public void visit(MiracleASTreeSelection selection) {
        selection.expression.accept(this);
        if (selection.expression.getResultType() != null &&
                !selection.expression.getResultType().isSameType(__builtin_bool)) {
            exceptionContainer.add("conditional expresson must be `bool`",
                    selection.expression.startPosition);
        }
        if (selection.branchTrue != null) {
            selection.branchTrue.accept(this);
        }
        if (selection.branchFalse != null) {
            selection.branchFalse.accept(this);
        }
    }

    @Override
    public void visit(MiracleASTreeIteration iteration) {
        loopStack.push(iteration);
        if (iteration.initializeExpression != null) {
            iteration.initializeExpression.accept(this);
        }
        if (iteration.conditionExpression != null) {
            iteration.conditionExpression.accept(this);
            if (iteration.conditionExpression.getResultType() != null &&
                    !iteration.conditionExpression.getResultType().isSameType(__builtin_bool)) {
                exceptionContainer.add("conditional expression must be `bool`",
                        iteration.conditionExpression.startPosition);
            }
        }
        if (iteration.incrementExpression != null) {
            iteration.incrementExpression.accept(this);
        }
        if (iteration.body != null) {
            iteration.body.accept(this);
        }
        loopStack.pop();
    }

    @Override
    public void visit(MiracleASTreeBreak breakLiteral) {
        if (loopStack.empty()) {
            exceptionContainer.add("break literal must be in iteration statment",
                    breakLiteral.startPosition);
        } else {
            breakLiteral.setIteration(loopStack.peek());
        }
    }

    @Override
    public void visit(MiracleASTreeContinue continueLiteral) {
        if (loopStack.empty()) {
            exceptionContainer.add("break literal must be in iteration statment",
                    continueLiteral.startPosition);
        } else {
            continueLiteral.setIteration(loopStack.peek());
        }
    }

    @Override
    public void visit(MiracleASTreeReturn returnLiteral) {
        if (funcStack.empty()) {
            exceptionContainer.add("return literal must be in function statment",
                    returnLiteral.startPosition);
        } else {
            MiracleASTreeFunctionDeclaration function = funcStack.peek();
            if (function.identifier == null) {
                if (returnLiteral.expression != null) {
                    returnLiteral.expression.accept(this);
                    if (returnLiteral.expression.getResultType() != null &&
                            !returnLiteral.expression.getResultType().isSameType(__builtin_void)) {
                        exceptionContainer.add("cannot return any value in constructor declaration",
                                returnLiteral.expression.startPosition);
                    } else {
                        returnLiteral.setFunction(function);
                    }
                } else {
                    returnLiteral.setFunction(function);
                }

            } else {
                if (returnLiteral.expression != null) {
                    returnLiteral.expression.accept(this);
                    if (returnLiteral.expression.getResultType() != null &&
                            !returnLiteral.expression.getResultType().isSameType(function.returnType.type)) {
                        exceptionContainer.add("the return value differs from declaration",
                                returnLiteral.expression.startPosition);
                    } else {
                        returnLiteral.setFunction(function);
                    }
                } else {
                    if (!function.returnType.type.isSameType(__builtin_void)) {
                        exceptionContainer.add("the return value differs from declaration",
                                returnLiteral.startPosition);
                    } else {
                        returnLiteral.setFunction(function);
                    }
                }
            }
        }
    }

    @Override
    public void visit(MiracleASTreeVariable variable) {
        MiracleASTreeDeclaration declaration = symbolTable.getIncludeAncestor(variable.identifier);
        if (declaration == null) {
            exceptionContainer.add("cannot find identifier named \"" + variable.identifier + "\"",
                    variable.startPosition);
        } else if (declaration instanceof MiracleASTreeClassDeclaration) {
            exceptionContainer.add("identifier `" + variable.identifier + "` is not expected here",
                    variable.startPosition);
        } else if (declaration instanceof MiracleASTreeFunctionDeclaration) {
            variable.setResultType(declaration.getType());
        } else {
            variable.setMutable();
            variable.setResultType(((MiracleASTreeVariableDeclaration) declaration).typenode.type);
        }
    }

    @Override
    public void visit(MiracleASTreeCall call) {
        call.function.accept(this);
        if (!(call.function.getResultType() instanceof MiracleFunctionType)) {
            exceptionContainer.add("expression is not a function, thus not callable",
                    call.startPosition);
        } else {
            MiracleFunctionType type = (MiracleFunctionType) call.function.getResultType();
            if (type != null) {
                if (call.parameters.size() != type.parameters.size()) {
                    exceptionContainer.add("function needs " + String.valueOf(type.parameters.size()) + " parameter(s), but found " + String.valueOf(call.parameters.size()) + " parameter(s)",
                            call.startPosition);
                } else {
                    for (int i = 0; i < type.parameters.size(); i++) {
                        MiracleASTreeExpression node = call.parameters.get(i);
                        node.accept(this);
                        MiracleType argType = node.getResultType();
                        if (argType != null && !argType.isSameType(type.parameters.get(i))) {
                            exceptionContainer.add("function needs parameter of type `" + type.parameters.get(i).toPrintableString() + "`, but `" + argType.toPrintableString() + "` was found",
                                    node.startPosition);
                        }
                    }
                    call.setResultType(type.returnType);
                }
            }
        }
    }

    @Override
    public void visit(MiracleASTreeSubscript subscript) {
        subscript.base.accept(this);
        MiracleType type = subscript.base.getResultType();
        if (type != null) {
            if (!(type instanceof MiracleArrayType)) {
                exceptionContainer.add("subscription can only be applied to array object",
                        subscript.base.startPosition);
            } else {
                subscript.setResultType(((MiracleArrayType) type).subscript());
                if (subscript.base.isMutable()) {
                    subscript.setMutable();
                }
            }
        }
        subscript.coordinate.accept(this);
        if (subscript.coordinate.getResultType() != null &&
                !subscript.coordinate.getResultType().isSameType(__builtin_int)) {
            exceptionContainer.add("expected `int`, but `" + subscript.coordinate.getResultType().toPrintableString() + "\" was found",
                    subscript.coordinate.startPosition);
        }
    }

    @Override
    public void visit(MiracleASTreeBinaryExpression binaryExpression) {
        binaryExpression.left.accept(this);
        binaryExpression.right.accept(this);
        MiracleType lType = binaryExpression.left.getResultType();
        MiracleType rType = binaryExpression.right.getResultType();
        if (lType == null || rType == null) return;
        if (!lType.isSameType(rType)) {
            exceptionContainer.add("no match for operator `" + binaryExpression.operator + "` (operands are `" + lType.toPrintableString() + "` and `" + rType.toPrintableString() + "`)",
                    binaryExpression.operatorPosition);
            return;
        }
        boolean flag = true;
        switch (binaryExpression.operator) {
            case DIV:
            case AND:
            case MUL:
            case MOD:
            case SHL:
            case SHR:
            case SUB:
            case OR:
                if (!lType.isSameType(__builtin_int)) {
                    exceptionContainer.add("no match for operator `" + binaryExpression.operator + "` (operands are `" + lType.toPrintableString() + "` and `" + rType.toPrintableString() + "`)",
                            binaryExpression.left.startPosition);
                    flag = false;
                }
                if (!rType.isSameType(__builtin_int)) {
                    exceptionContainer.add("no match for operator `" + binaryExpression.operator + "` (operands are `" + lType.toPrintableString() + "` and `" + rType.toPrintableString() + "`)",
                            binaryExpression.right.startPosition);
                    flag = false;
                }
                if (flag) binaryExpression.setResultType(lType);
                break;
            case ADD:
                if (!lType.isSameType(__builtin_int) && !lType.isSameType(__builtin_string)) {
                    exceptionContainer.add("no match for operator `" + binaryExpression.operator + "` (operands are `" + lType.toPrintableString() + "` and `" + rType.toPrintableString() + "`)",
                            binaryExpression.left.startPosition);
                    flag = false;
                }
                if (!rType.isSameType(__builtin_int) && !rType.isSameType(__builtin_string)) {
                    exceptionContainer.add("no match for operator `" + binaryExpression.operator + "` (operands are `" + lType.toPrintableString() + "` and `" + rType.toPrintableString() + "`)",
                            binaryExpression.right.startPosition);
                    flag = false;
                }
                if (flag) binaryExpression.setResultType(lType);
                break;
            case LT:
            case RT:
            case EQL:
            case LEQ:
            case NEQ:
            case REQ:
                binaryExpression.setResultType(__builtin_bool);
                break;
            case XOR:
                if (!lType.isSameType(__builtin_int) && !lType.isSameType(__builtin_bool)) {
                    exceptionContainer.add("no match for operator `" + binaryExpression.operator + "` (operands are `" + lType.toPrintableString() + "` and `" + rType.toPrintableString() + "`)",
                            binaryExpression.left.startPosition);
                    flag = false;
                }
                if (!rType.isSameType(__builtin_int) && !rType.isSameType(__builtin_bool)) {
                    exceptionContainer.add("no match for operator `" + binaryExpression.operator + "` (operands are `" + lType.toPrintableString() + "` and `" + rType.toPrintableString() + "`)",
                            binaryExpression.right.startPosition);
                    flag = false;
                }
                if (flag) binaryExpression.setResultType(lType);
                break;
            case CONJ:
            case DISJ:
                if (!lType.isSameType(__builtin_bool)) {
                    exceptionContainer.add("no match for operator `" + binaryExpression.operator + "` (operands are `" + lType.toPrintableString() + "` and `" + rType.toPrintableString() + "`)",
                            binaryExpression.left.startPosition);
                    flag = false;
                }
                if (!rType.isSameType(__builtin_bool)) {
                    exceptionContainer.add("no match for operator `" + binaryExpression.operator + "` (operands are `" + lType.toPrintableString() + "` and `" + rType.toPrintableString() + "`)",
                            binaryExpression.right.startPosition);
                    flag = false;
                }
                if (flag) binaryExpression.setResultType(lType);
                break;
            case ASS:
                if (!binaryExpression.left.isMutable()) {
                    exceptionContainer.add("the result of left operand is not mutable",
                            binaryExpression.left.startPosition);
                    flag = false;
                }
                if (flag) {
                    binaryExpression.setResultType(rType);
                    binaryExpression.setMutable();
                }
                break;
            default:
                throw new RuntimeException("unsupported operator");
        }
    }

    @Override
    public void visit(MiracleASTreePrefixExpression prefixExpression) {
        prefixExpression.expression.accept(this);
        MiracleType type = prefixExpression.expression.getResultType();
        if (type == null) return;
        boolean flag = true;
        switch (prefixExpression.operator) {
            case INC:
            case DEC:
                if (!type.isSameType(__builtin_int)) {
                    exceptionContainer.add("no match for operator `" + prefixExpression.operator + "` (operand is `" + type.toPrintableString() + "`)",
                            prefixExpression.expression.startPosition);
                    flag = false;
                }
                if (!prefixExpression.expression.isMutable()) {
                    exceptionContainer.add("the result of operand is not mutable",
                            prefixExpression.expression.startPosition);
                    flag = false;
                }
                if (flag) prefixExpression.setResultType(type);
                break;
            case NEGATE:
                if (!type.isSameType(__builtin_bool)) {
                    exceptionContainer.add("no match for operator `" + prefixExpression.operator + "` (operand is `" + type.toPrintableString() + "`)",
                            prefixExpression.expression.startPosition);
                    flag = false;
                }
                if (flag) prefixExpression.setResultType(type);
                break;
            case NEGATIVE:
            case POSITIVE:
            case REV:
                if (!type.isSameType(__builtin_int)) {
                    exceptionContainer.add("no match for operator `" + prefixExpression.operator + "` (operand is `" + type.toPrintableString() + "`)",
                            prefixExpression.expression.startPosition);
                    flag = false;
                }
                if (flag) prefixExpression.setResultType(type);
                break;
            default:
                throw new RuntimeException("unsupported operator");
        }
    }

    @Override
    public void visit(MiracleASTreeSuffixExpression suffixExpression) {
        suffixExpression.expression.accept(this);
        MiracleType type = suffixExpression.expression.getResultType();
        if (type == null) return;
        boolean flag = true;
        if (!type.isSameType(__builtin_int)) {
            exceptionContainer.add("no match for operator `" + suffixExpression.operator + "` (operand is `" + type.toPrintableString() + "`)",
                    suffixExpression.expression.startPosition);
            flag = false;
        }
        if (!suffixExpression.expression.isMutable()) {
            exceptionContainer.add("the result of operand is not mutable",
                    suffixExpression.expression.startPosition);
            flag = false;
        }
        if (flag) suffixExpression.setResultType(type);
    }

    @Override
    public void visit(MiracleASTreeNew newNode) {
        if (!(newNode.variableType.type instanceof MiracleBaseType)) {
            exceptionContainer.add("base type is required here",
                    newNode.variableType.startPosition);
        } else if (newNode.variableType.type.isSameType(__builtin_void)) {
            exceptionContainer.add("memory of void type cannot be allocated",
                    newNode.variableType.startPosition);
        } else {
            boolean flag = true;
            for (int i = 0, empty = 0; i < newNode.expressions.size(); i++) {
                MiracleASTreeExpression expression = newNode.expressions.get(i);
                if (expression == null) {
                    empty = 1;
                } else {
                    if (empty == 1) {
                        exceptionContainer.add("invalid declaration of interleaved array",
                                expression.startPosition);
                        flag = false;
                    }
                    expression.accept(this);
                    MiracleType type = expression.getResultType();
                    if (type != null && !type.isSameType(__builtin_int)) {
                        exceptionContainer.add("expected `int`, but `" + type.toPrintableString() + "` was found",
                                expression.startPosition);
                        flag = false;
                    }
                }
            }
            if (flag) {
                newNode.setMutable();
                if (newNode.expressions.isEmpty()) {
                    newNode.setResultType(newNode.variableType.type);
                } else {
                    newNode.setResultType(new MiracleArrayType(
                            (MiracleBaseType) newNode.variableType.type,
                            newNode.expressions.size()
                    ));
                }
            }
        }
    }

    @Override
    public void visit(MiracleASTreeStringConstant stringConstant) {
        stringConstant.setResultType(__builtin_string);
    }

    @Override
    public void visit(MiracleASTreeIntegerConstant integerConstant) {
        integerConstant.setResultType(__builtin_int);
    }

    @Override
    public void visit(MiracleASTreeBooleanConstant booleanConstant) {
        booleanConstant.setResultType(__builtin_bool);
    }

    @Override
    public void visit(MiracleASTreeNullConstant nullConstant) {
        nullConstant.setResultType(__builtin_null);
    }

    @Override
    public void visit(MiracleASTreeThis thisNode) {
        if (classStack.empty()) {
            exceptionContainer.add("invalid use of this literal",
                    thisNode.startPosition);
        } else {
            thisNode.setMutable();
            thisNode.setResultType(classStack.peek().getType());
        }
    }

    @Override
    public void visit(MiracleASTreeField field) {
        field.expression.accept(this);
        MiracleType type = field.expression.getResultType();
        if (type == null) return;
        if (type instanceof MiracleFunctionType) {
            exceptionContainer.add("not match for operator `.` (operands are `" + type.toClassTypeString() + "` and \"" + field.identifier + "\")",
                    field.operatorPosition);
        }
        MiracleASTreeMemberDeclaration declaration =
                symbolTable.getClassIncludeAncestor(type.toClassTypeString()).get(field.identifier);
        if (declaration == null) {
            exceptionContainer.add("class `" + type.toClassTypeString() + "` has no member of \"" + field.identifier + "\"",
                    field.identifierPosition);
        } else {
            if (field.expression.isMutable()) {
                field.setMutable();
            }
            field.setResultType(declaration.getType());
        }
    }

    @Override
    public void visit(MiracleASTreeTypeNode typeNode) {

    }
}
