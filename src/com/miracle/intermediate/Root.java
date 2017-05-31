package com.miracle.intermediate;

import com.miracle.MiracleOption;
import com.miracle.astree.ASTree;
import com.miracle.astree.base.TypeNode;
import com.miracle.astree.statement.*;
import com.miracle.astree.statement.declaration.ClassDeclaration;
import com.miracle.astree.statement.declaration.FunctionDeclaration;
import com.miracle.astree.statement.declaration.VariableDeclaration;
import com.miracle.astree.statement.expression.*;
import com.miracle.astree.statement.expression.constant.BooleanConstant;
import com.miracle.astree.statement.expression.constant.IntegerConstant;
import com.miracle.astree.statement.expression.constant.NullConstant;
import com.miracle.astree.statement.expression.constant.StringConstant;
import com.miracle.astree.visitor.ASTreeVisitor;
import com.miracle.intermediate.instruction.Call;
import com.miracle.intermediate.instruction.Compare;
import com.miracle.intermediate.instruction.HeapAllocate;
import com.miracle.intermediate.instruction.Move;
import com.miracle.intermediate.instruction.arithmetic.BinaryArithmetic;
import com.miracle.intermediate.instruction.arithmetic.UnaryArithmetic;
import com.miracle.intermediate.instruction.fork.BinaryBranch;
import com.miracle.intermediate.instruction.fork.Jump;
import com.miracle.intermediate.instruction.fork.Return;
import com.miracle.intermediate.instruction.fork.UnaryBranch;
import com.miracle.intermediate.number.*;
import com.miracle.intermediate.number.Number;
import com.miracle.intermediate.structure.BasicBlock;
import com.miracle.intermediate.structure.Function;
import com.miracle.intermediate.visitor.IRVisitor;
import com.miracle.symbol.*;

import java.util.*;

import static com.miracle.symbol.SymbolTable.*;

public class Root extends Node {
    public final Map<String, StaticVariable> globalVariable;
    public final Map<String, StaticString> globalString;
    public final Map<String, Function> globalFunction;

    public Root(Map<String, StaticVariable> globalVariable,
                Map<String, StaticString> globalString,
                Map<String, Function> globalFunction) {
        this.globalVariable = globalVariable;
        this.globalString = globalString;
        this.globalFunction = globalFunction;
    }

    @Override
    public void accept(IRVisitor IRVisitor) {
        IRVisitor.visit(this);
    }

    public static class Builder implements ASTreeVisitor {
        private Map<String, StaticVariable> globalVariable;
        private Map<String, StaticString> globalString;
        private Map<String, Function> globalFunction;

        private BasicBlock curBasicBlock;
        private BasicBlock loopCondBlock;
        private BasicBlock loopExitBlock;
        private Function curFunction;

        private SymbolClassType curClass;
        private HashMap<Integer, Stack<VirtualRegister>> tmpVirtualRegister;

        private int countTmpRegister;
        private int countBlock;

        private VirtualRegister newVirtualRegister(int size) {
            return new VirtualRegister(
                    ".t" + String.valueOf(countTmpRegister++),
                    size
            );
        }

        private VirtualRegister newVirtualRegister(String name, int size) {
            if (tmpVirtualRegister.get(size).empty()) {
                return new VirtualRegister(
                        name + String.valueOf(countTmpRegister++),
                        size
                );
            } else {
                return tmpVirtualRegister.get(size).pop();
            }
        }

        @Override
        public void visit(FunctionDeclaration functionDeclaration) {
            curFunction = functionDeclaration.getSymbol().getAddress();
            globalFunction.put(functionDeclaration.identifier, curFunction);
            curBasicBlock = curFunction.getEntryBasicBlock();
            functionDeclaration.parameters.forEach(element -> {
                element.accept(this);
                if (!(element.getAddress() instanceof VirtualRegister)) {
                    throw new RuntimeException("Unexpected case");
                }
                curFunction.addParameter(element.getAddress());
            });
            functionDeclaration.body.forEach(element -> element.accept(this));

            if (!curBasicBlock.isForked()) {
                curBasicBlock.setFork(new Return(null));
                curFunction.addReturn(curBasicBlock.tail.getPrev());
            }

            curFunction = null;
        }

        @Override
        public void visit(ClassDeclaration classDeclaration) {
            curClass = classDeclaration.getSymbol();
            if (classDeclaration.constructorDeclaration != null) {
                classDeclaration.constructorDeclaration.accept(this);
            }
            classDeclaration.functionDeclarations.forEach(element -> element.accept(this));
            curClass = null;
        }

        @Override
        public void visit(ASTree astree) {
            globalString = new HashMap<>();
            globalVariable = new HashMap<>();
            globalFunction = new HashMap<>();
            tmpVirtualRegister = new HashMap<>();
            tmpVirtualRegister.put(MiracleOption.BOOL_SIZE, new Stack<>());
            tmpVirtualRegister.put(MiracleOption.INT_SIZE, new Stack<>());
            tmpVirtualRegister.put(MiracleOption.POINTER_SIZE, new Stack<>());


            Function initFunction = new Function(
                    "__init",
                    new SymbolFunctionType(__builtin_void, null)
            );
            globalFunction.put("__init", initFunction);
            curFunction = initFunction;
            curBasicBlock = initFunction.getEntryBasicBlock();
            astree.declarations.forEach(element -> {
                if (element instanceof VariableDeclaration) {
                    element.accept(this);
                }
            });

            if (!curBasicBlock.isForked()) {
                curBasicBlock.setFork(new Return(null));
                curFunction.addReturn(curBasicBlock.tail.getPrev());
            }

            ((FunctionDeclaration) astree.getScope().resolve("main"))
                    .getSymbol().getAddress().getEntryBasicBlock().tail
                    .prepend(new Call(
                            curFunction, new LinkedList<>(),
                            null, null
                    ));

            curBasicBlock = null;
            curFunction = null;

            astree.declarations.forEach(element -> {
                if (!(element instanceof VariableDeclaration)) {
                    element.accept(this);
                }
            });
        }

        @Override
        public void visit(VariableDeclaration variableDeclaration) {
            if (variableDeclaration.getMemberFrom() == null) {
                if (variableDeclaration.getScope().getParentSymbolTable() == null) {
                    StaticVariable register = new StaticVariable(variableDeclaration);
                    globalVariable.put(register.name, register);
                    variableDeclaration.setAddress(register);
                } else if (curFunction != null) {
                    variableDeclaration.setAddress(new VirtualRegister(variableDeclaration));
                } else {
                    throw new RuntimeException("unexpected case");
                }
                if (variableDeclaration.expression != null) {
                    variableDeclaration.expression.accept(this);
                    curBasicBlock.tail.prepend(new Move(
                            variableDeclaration.getAddress(),
                            variableDeclaration.expression.getResultNumber()
                    ));
                }
            }
        }

        @Override
        public void visit(Block block) {
            block.statements.forEach(element -> element.accept(this));
        }

        @Override
        public void visit(Selection selection) {
            BasicBlock passBlock = new BasicBlock("if_pass_" + String.valueOf(countBlock++), curFunction, false, false);
            BasicBlock failBlock = new BasicBlock("if_fail_" + String.valueOf(countBlock++), curFunction, false, false);
            BasicBlock bothBlock = new BasicBlock("if_both_" + String.valueOf(countBlock++), curFunction, false, false);

            if (selection.expression instanceof BinaryExpression &&
                    (((BinaryExpression) selection.expression).operator.equals(BinaryExpression.OPERATOR.LT)
                            || ((BinaryExpression) selection.expression).operator.equals(BinaryExpression.OPERATOR.RT)
                            || ((BinaryExpression) selection.expression).operator.equals(BinaryExpression.OPERATOR.LEQ)
                            || ((BinaryExpression) selection.expression).operator.equals(BinaryExpression.OPERATOR.REQ)
                            || ((BinaryExpression) selection.expression).operator.equals(BinaryExpression.OPERATOR.EQL)
                            || ((BinaryExpression) selection.expression).operator.equals(BinaryExpression.OPERATOR.NEQ))) {
                ((BinaryExpression) selection.expression).left.accept(this);
                ((BinaryExpression) selection.expression).right.accept(this);
                if (((BinaryExpression) selection.expression).left.getResultType().isSameType(__builtin_string)) {
                    VirtualRegister register = newVirtualRegister(MiracleOption.BOOL_SIZE);
                    curBasicBlock.tail.prepend(new Call(
                            __builtin_strcmp.getAddress(),
                            new LinkedList<Number>() {{
                                add(((BinaryExpression) selection.expression).left.getResultNumber());
                                add(((BinaryExpression) selection.expression).right.getResultNumber());
                            }},
                            register,
                            null
                    ));
                    curBasicBlock.setFork(new BinaryBranch(
                            register,
                            ((BinaryExpression) selection.expression).operator,
                            new Immediate(0, ((BinaryExpression) selection.expression).left.getResultNumber().getNumberSize()),
                            passBlock, failBlock
                    ));
                } else {
                    curBasicBlock.setFork(new BinaryBranch(
                            ((BinaryExpression) selection.expression).left.getResultNumber(),
                            ((BinaryExpression) selection.expression).operator,
                            ((BinaryExpression) selection.expression).right.getResultNumber(),
                            passBlock, failBlock
                    ));
                }
            } else {
                selection.expression.accept(this);
                curBasicBlock.setFork(new UnaryBranch(
                        selection.expression.getResultNumber(),
                        passBlock, failBlock
                ));
            }

            curBasicBlock = passBlock;
            if (selection.branchTrue != null) {
                selection.branchTrue.accept(this);
            }

            if (!curBasicBlock.isForked()) {
                curBasicBlock.setFork(new Jump(bothBlock));
            }

            curBasicBlock = failBlock;
            if (selection.branchFalse != null) {
                selection.branchFalse.accept(this);
            }

            if (!curBasicBlock.isForked()) {
                curBasicBlock.setFork(new Jump(bothBlock));
            }

            curBasicBlock = bothBlock;
        }

        @Override
        public void visit(Iteration iteration) {
            if (iteration.initializeExpression != null) {
                iteration.initializeExpression.accept(this);
            }
            BasicBlock condBlock = new BasicBlock("iter_cond_" + String.valueOf(countBlock++), curFunction, false, false);

            if (!curBasicBlock.isForked()) {
                curBasicBlock.setFork(new Jump(condBlock));
            }

            curBasicBlock = condBlock;
            BasicBlock bodyBlock = new BasicBlock("iter_body_" + String.valueOf(countBlock++), curFunction, false, false);
            BasicBlock exitBlock = new BasicBlock("iter_exit_" + String.valueOf(countBlock++), curFunction, false, false);
            if (iteration.conditionExpression != null) {
                iteration.conditionExpression.accept(this);

                curBasicBlock.setFork(new UnaryBranch(
                        iteration.conditionExpression.getResultNumber(),
                        bodyBlock, exitBlock
                ));
            } else {
                curBasicBlock.setFork(new Jump(bodyBlock));
            }
            BasicBlock oldBodyBlock = loopCondBlock;
            BasicBlock oldExitBlock = loopExitBlock;
            loopCondBlock = condBlock;
            loopExitBlock = exitBlock;

            curBasicBlock = bodyBlock;
            if (iteration.body != null) {
                iteration.body.accept(this);
            }

            if (iteration.incrementExpression != null) {
                iteration.incrementExpression.accept(this);
            }

            if (!curBasicBlock.isForked()) {
                curBasicBlock.setFork(new Jump(condBlock));
            }

            curBasicBlock = exitBlock;

            loopExitBlock = oldExitBlock;
            loopCondBlock = oldBodyBlock;
        }

        @Override
        public void visit(Break breakLiteral) {
            if (!curBasicBlock.isForked()) {
                curBasicBlock.setFork(new Jump(loopExitBlock));
            }
        }

        @Override
        public void visit(Continue continueLiteral) {
            if (!curBasicBlock.isForked()) {
                curBasicBlock.setFork(new Jump(loopCondBlock));
            }
        }

        @Override
        public void visit(ReturnStatement returnLiteral) {
            if (returnLiteral.expression != null) {
                returnLiteral.expression.accept(this);
            }
            Return returnStatement;
            if (returnLiteral.expression != null) {
                returnStatement = new Return(returnLiteral.expression.getResultNumber());
            } else {
                returnStatement = new Return(null);
            }
            if (!curBasicBlock.isForked()) {
                curBasicBlock.setFork(returnStatement);
                curFunction.addReturn(curBasicBlock.tail.getPrev());
            }
        }

        @Override
        public void visit(Variable variable) {
            Symbol symbol = variable.getScope().resolve(variable.identifier);
            if (symbol instanceof VariableDeclaration) {
                if (((VariableDeclaration) symbol).getMemberFrom() != null) {
                    // if a variable in class has no `this' before, then I will force to add it
                    variable.setResultNumber(new OffsetRegister(
                            curFunction.getSelfRegister(),
                            curClass.getVariableOffset(variable.identifier),
                            null, null,
                            curClass.getVariable(variable.identifier).getRegisterSize()
                    ));
                } else {
                    symbol = variable.getScope().resolveVariable(variable.identifier);
                    variable.setResultNumber(((VariableDeclaration) symbol).getAddress());
                }
            } else if (symbol instanceof FunctionDeclaration) {
            } else if (symbol instanceof SymbolFunctionType) {
            } else {
                throw new RuntimeException("unexpected case");
            }
        }

        @Override
        public void visit(CallExpression callExpression) {
            callExpression.function.accept(this);
            SymbolFunctionType type = (SymbolFunctionType) callExpression.function.getResultType();
            List<Number> parameters = new LinkedList<>();
            callExpression.parameters.forEach(element -> {
                element.accept(this);
                parameters.add(element.getResultNumber());
            });
            Register selfRegister = null;
            if (callExpression.function instanceof Field) {
                selfRegister = (Register) ((Field) callExpression.function).expression.getResultNumber();
            } else if (type.getMemberFrom() != null) {
                selfRegister = curFunction.getSelfRegister();
            }
            if (type.getReturnType().isSameType(__builtin_void)) {
                curBasicBlock.tail.prepend(new Call(
                        type.getAddress(), parameters,
                        null, selfRegister
                ));
            } else {
                Register register = newVirtualRegister(type.getReturnType().getRegisterSize());
                curBasicBlock.tail.prepend(new Call(
                        type.getAddress(), parameters,
                        register, selfRegister
                ));
                callExpression.setResultNumber(register);
            }
        }

        @Override
        public void visit(Subscript subscript) {
            subscript.base.accept(this);
            subscript.coordinate.accept(this);
            int size = ((SymbolArrayType) subscript.base.getResultType()).subscript().getRegisterSize();
            Number tmpCoor = subscript.coordinate.getResultNumber();
            if (!(tmpCoor instanceof DirectRegister)) {
                VirtualRegister victim = newVirtualRegister(subscript.coordinate.getResultType().getRegisterSize());
                curBasicBlock.tail.prepend(new Move(victim, tmpCoor));
                tmpCoor = victim;
            }
            Number tmpBase = subscript.base.getResultNumber();
            if (!(tmpBase instanceof DirectRegister)) {
                VirtualRegister victim = newVirtualRegister(subscript.base.getResultType().getRegisterSize());
                curBasicBlock.tail.prepend(new Move(victim, tmpBase));
                tmpBase = victim;
            }

            subscript.setResultNumber(new OffsetRegister(
                    (DirectRegister) tmpBase, 0,
                    (DirectRegister) tmpCoor, size,
                    size
            ));
        }

        private void addRelation(BinaryExpression expression) {
            expression.left.accept(this);
            expression.right.accept(this);
            Compare.Types operator;
            switch (expression.operator) {
                case REQ:
                    operator = Compare.Types.REQ;
                    break;
                case LEQ:
                    operator = Compare.Types.LEQ;
                    break;
                case RT:
                    operator = Compare.Types.RT;
                    break;
                case LT:
                    operator = Compare.Types.LT;
                    break;
                case NEQ:
                    operator = Compare.Types.NEQ;
                    break;
                case EQL:
                    operator = Compare.Types.EQL;
                    break;
                default:
                    throw new RuntimeException("unsupported operator");
            }
            VirtualRegister register = newVirtualRegister(MiracleOption.BOOL_SIZE);
            if (expression.left.getResultType().isSameType(__builtin_string)) {
                curBasicBlock.tail.prepend(new Call(
                        __builtin_strcmp.getAddress(),
                        new LinkedList<Number>() {{
                            add(expression.left.getResultNumber());
                            add(expression.right.getResultNumber());
                        }},
                        register,
                        null
                ));
                curBasicBlock.tail.prepend(new Compare(
                        operator,
                        new Immediate(0, MiracleOption.INT_SIZE),
                        register,
                        register
                ));
            } else {
                curBasicBlock.tail.prepend(new Compare(
                        operator,
                        expression.left.getResultNumber(),
                        expression.right.getResultNumber(),
                        register
                ));
            }
            expression.setResultNumber(register);
        }

        private void addArithmetic(BinaryExpression expression) {
            expression.left.accept(this);
            expression.right.accept(this);
            BinaryArithmetic.Types operator;
            switch (expression.operator) {
                case XOR:
                    operator = BinaryArithmetic.Types.XOR;
                    break;
                case AND:
                    operator = BinaryArithmetic.Types.AND;
                    break;
                case SHR:
                    operator = BinaryArithmetic.Types.SHR;
                    break;
                case SUB:
                    operator = BinaryArithmetic.Types.SUB;
                    break;
                case MOD:
                    operator = BinaryArithmetic.Types.MOD;
                    break;
                case SHL:
                    operator = BinaryArithmetic.Types.SHL;
                    break;
                case DIV:
                    operator = BinaryArithmetic.Types.DIV;
                    break;
                case OR:
                    operator = BinaryArithmetic.Types.OR;
                    break;
                case MUL:
                    operator = BinaryArithmetic.Types.MUL;
                    break;
                case ADD:
                    operator = BinaryArithmetic.Types.ADD;
                    break;
                default:
                    throw new RuntimeException("unsupported operator");
            }
            VirtualRegister register = newVirtualRegister(expression.left.getResultNumber().getNumberSize());
            curBasicBlock.tail.prepend(new Move(
                    register, expression.left.getResultNumber()
            ));
            curBasicBlock.tail.prepend(new BinaryArithmetic(
                    operator, register, expression.right.getResultNumber()
            ));
            expression.setResultNumber(register);
        }

        private void addAssignment(BinaryExpression expression) {
            expression.left.accept(this);
            expression.right.accept(this);
            curBasicBlock.tail.prepend(new Move(
                    (Register) expression.left.getResultNumber(),
                    expression.right.getResultNumber()
            ));
            expression.setResultNumber(expression.left.getResultNumber());
        }

        private void addStringConcat(BinaryExpression expression) {
            expression.left.accept(this);
            expression.right.accept(this);
            VirtualRegister register = newVirtualRegister(MiracleOption.INT_SIZE);
            curBasicBlock.tail.prepend(new Call(
                    __builtin_strcat.getAddress(),
                    new LinkedList<Number>() {{
                        add(expression.left.getResultNumber());
                        add(expression.right.getResultNumber());
                    }},
                    register, null
            ));
            expression.setResultNumber(register);
        }

        private void addLogicalExpression(BinaryExpression expression) {
            expression.left.accept(this);
            BasicBlock block_true = new BasicBlock("ss_block_true_" + String.valueOf(countBlock++), curFunction, false, false);
            BasicBlock block_fake = new BasicBlock("ss_block_fake_" + String.valueOf(countBlock++), curFunction, false, false);
            BasicBlock block_both = new BasicBlock("ss_block_fake_" + String.valueOf(countBlock++), curFunction, false, false);
            if (!curBasicBlock.isForked()) {
                curBasicBlock.setFork(new UnaryBranch(expression.left.getResultNumber(), block_true, block_fake));
            }
            VirtualRegister register = newVirtualRegister(expression.left.getResultNumber().getNumberSize());
            if (expression.operator.equals(BinaryExpression.OPERATOR.DISJ)) {
                curBasicBlock = block_true;
                curBasicBlock.tail.prepend(new Move(register, new Immediate(1, expression.left.getResultNumber().getNumberSize())));
                curBasicBlock.setFork(new Jump(block_both));

                curBasicBlock = block_fake;
                expression.right.accept(this);
                curBasicBlock.tail.prepend(new Move(register, expression.right.getResultNumber()));
                if (!curBasicBlock.isForked()) {
                    curBasicBlock.setFork(new Jump(block_both));
                }
            } else {
                curBasicBlock = block_true;
                expression.right.accept(this);
                curBasicBlock.tail.prepend(new Move(register, expression.right.getResultNumber()));
                if (!curBasicBlock.isForked()) {
                    curBasicBlock.setFork(new Jump(block_both));
                }

                curBasicBlock = block_fake;
                curBasicBlock.tail.prepend(new Move(register, new Immediate(0, expression.left.getResultNumber().getNumberSize())));
                curBasicBlock.setFork(new Jump(block_both));
            }
            curBasicBlock = block_both;
            expression.setResultNumber(register);
        }

        @Override
        public void visit(BinaryExpression binaryExpression) {
            switch (binaryExpression.operator) {
                case EQL:
                case NEQ:
                case LT:
                case RT:
                case LEQ:
                case REQ:
                    addRelation(binaryExpression);
                    break;
                case XOR:
                case AND:
                case SHR:
                case SUB:
                case MOD:
                case SHL:
                case DIV:
                case OR:
                case MUL:
                    addArithmetic(binaryExpression);
                    break;
                case ADD:
                    if (binaryExpression.left.getResultType().isSameType(__builtin_string)) {
                        addStringConcat(binaryExpression);
                    } else {
                        addArithmetic(binaryExpression);
                    }
                    break;
                case ASS:
                    addAssignment(binaryExpression);
                    break;
                case DISJ:
                case CONJ:
                    addLogicalExpression(binaryExpression);
                    break;
                default:
                    throw new RuntimeException("unsupported operator");
            }
        }

        @Override
        public void visit(PrefixExpression prefixExpression) {
            prefixExpression.expression.accept(this);
            UnaryArithmetic.Types operator;
            Number number = prefixExpression.expression.getResultNumber();
            switch (prefixExpression.operator) {
                case INC:
                    operator = UnaryArithmetic.Types.ADD;
                    break;
                case DEC:
                    operator = UnaryArithmetic.Types.SUB;
                    break;
                case REV:
                    operator = UnaryArithmetic.Types.REV;
                    if (number instanceof Immediate) {
                        prefixExpression.setResultNumber(new Immediate(
                                ~((Immediate) number).value,
                                number.getNumberSize()
                        ));
                        return;
                    } else {
                        VirtualRegister register = newVirtualRegister(number.getNumberSize());
                        curBasicBlock.tail.prepend(new Move(register, number));
                        curBasicBlock.tail.prepend(new UnaryArithmetic(register, operator));
                        prefixExpression.setResultNumber(register);
                        return;
                    }
                case NEGATIVE:
                    operator = UnaryArithmetic.Types.MINUS;
                    if (number instanceof Immediate) {
                        prefixExpression.setResultNumber(new Immediate(
                                -((Immediate) number).value,
                                number.getNumberSize()
                        ));
                        return;
                    } else {
                        VirtualRegister register = newVirtualRegister(number.getNumberSize());
                        curBasicBlock.tail.prepend(new Move(register, number));
                        curBasicBlock.tail.prepend(new UnaryArithmetic(register, operator));
                        prefixExpression.setResultNumber(register);
                        return;
                    }
                case NEGATE:
                    VirtualRegister register = newVirtualRegister(number.getNumberSize());
                    curBasicBlock.tail.prepend(new Compare(
                            Compare.Types.EQL,
                            number, new Immediate(0, number.getNumberSize()),
                            register
                    ));
                    prefixExpression.setResultNumber(register);
                    return;
                case POSITIVE:
                    prefixExpression.setResultType(prefixExpression.expression.getResultType());
                    return;
                default:
                    throw new RuntimeException("unsupported operator");
            }
            curBasicBlock.tail.prepend(new UnaryArithmetic((Register) number, operator));
            prefixExpression.setResultNumber(prefixExpression.expression.getResultNumber());
        }

        @Override
        public void visit(SuffixExpression suffixExpression) {
            suffixExpression.expression.accept(this);
            UnaryArithmetic.Types operator;
            switch (suffixExpression.operator) {
                case INC:
                    operator = UnaryArithmetic.Types.ADD;
                    break;
                case DEC:
                    operator = UnaryArithmetic.Types.SUB;
                    break;
                default:
                    throw new RuntimeException("unsupported operator");
            }
            VirtualRegister register = newVirtualRegister(MiracleOption.INT_SIZE);
            curBasicBlock.tail.prepend(new Move(
                    register, suffixExpression.expression.getResultNumber()
            ));
            curBasicBlock.tail.prepend(new UnaryArithmetic(
                    (Register) suffixExpression.expression.getResultNumber(),
                    operator
            ));
            suffixExpression.setResultNumber(register);
        }

        @Override
        public void visit(New newNode) {
            SymbolVariableType type = newNode.variableType.getType();
            if (newNode.expressions.isEmpty()) {
                VirtualRegister register = newVirtualRegister(MiracleOption.POINTER_SIZE);
                curBasicBlock.tail.prepend(new HeapAllocate(
                        register, type.getMemorySize(),
                        new Immediate(1, MiracleOption.INT_SIZE)
                ));
                if (type instanceof SymbolClassType) {
                    SymbolFunctionType constructor = type.getMethod("");
                    if (constructor != null) {
                        curBasicBlock.tail.prepend(new Call(
                                constructor.getAddress(),
                                new LinkedList<>(),
                                null, register
                        ));
                    }
                }
                newNode.setResultNumber(register);
            } else { // Array Type
                VirtualRegister register = newVirtualRegister(MiracleOption.POINTER_SIZE);
                List<Expression> nextExprs = new LinkedList<>(newNode.expressions);
                Expression expression = nextExprs.remove(0);
                if (expression == null) {
                    newNode.setResultNumber(new Immediate(0, MiracleOption.POINTER_SIZE));
                    return;
                }
                expression.accept(this);
                curBasicBlock.tail.prepend(new HeapAllocate(
                        register, MiracleOption.POINTER_SIZE,
                        expression.getResultNumber()
                ));
                curBasicBlock.tail.prepend(new Move(
                        new OffsetRegister(register, 0, null, null, MiracleOption.INT_SIZE),
                        expression.getResultNumber()
                ));
                curBasicBlock.tail.prepend(new BinaryArithmetic(
                        BinaryArithmetic.Types.ADD,
                        register,
                        new Immediate(MiracleOption.INT_SIZE, MiracleOption.POINTER_SIZE)
                ));
                newNode.setResultNumber(register);

                if (!nextExprs.isEmpty() || !(newNode.variableType.getType() instanceof SymbolPrimitiveType)) {
                    VirtualRegister iterreg = newVirtualRegister("new_init_iter_", MiracleOption.INT_SIZE);
                    curBasicBlock.tail.prepend(new BinaryArithmetic(
                            BinaryArithmetic.Types.XOR,
                            iterreg, iterreg
                    ));
                    BasicBlock newInitCondBlock = new BasicBlock("new_init_cond_" + String.valueOf(countBlock++), curFunction, false, false);
                    BasicBlock newInitExitBlock = new BasicBlock("new_init_exit_" + String.valueOf(countBlock++), curFunction, false, false);

                    curBasicBlock.setFork(new Jump(newInitCondBlock));

                    curBasicBlock = newInitCondBlock;
                    BasicBlock newInitBodyBlock = new BasicBlock("new_init_body_" + String.valueOf(countBlock++), curFunction, false, false);

                    curBasicBlock.setFork(new BinaryBranch(iterreg, BinaryBranch.Types.LT, expression.getResultNumber(), newInitBodyBlock, newInitExitBlock));

                    curBasicBlock = newInitBodyBlock;
                    New nextNew = new New(newNode.variableType, nextExprs, null);
                    nextNew.setScope(newNode.getScope());
                    nextNew.accept(this);
                    curBasicBlock.tail.prepend(new Move(
                            new OffsetRegister(register, 0,
                                    iterreg, MiracleOption.POINTER_SIZE,
                                    MiracleOption.POINTER_SIZE
                            ),
                            nextNew.getResultNumber()
                    ));
                    curBasicBlock.tail.prepend(new UnaryArithmetic(iterreg, UnaryArithmetic.Types.ADD));
                    curBasicBlock.setFork(new Jump(newInitCondBlock));
                    curBasicBlock = newInitExitBlock;
                }
            }
        }

        @Override
        public void visit(StringConstant stringConstant) {
            if (globalFunction.containsKey(stringConstant.value)) {
                stringConstant.setResultNumber(globalString.get(stringConstant.value));
            } else {
                StaticString register = globalString.getOrDefault(
                        stringConstant.value,
                        new StaticString(
                                "global$str" + String.valueOf(countTmpRegister++),
                                stringConstant.value
                        )
                );
                globalString.put(stringConstant.value, register);
                stringConstant.setResultNumber(register);
            }
        }

        @Override
        public void visit(IntegerConstant integerConstant) {
            integerConstant.setResultNumber(new Immediate(
                    Integer.parseInt(integerConstant.value), MiracleOption.INT_SIZE
            ));
        }

        @Override
        public void visit(BooleanConstant booleanConstant) {
            if (booleanConstant.value.equals("true")) {
                booleanConstant.setResultNumber(new Immediate(1, MiracleOption.BOOL_SIZE));
            } else {
                booleanConstant.setResultNumber(new Immediate(0, MiracleOption.BOOL_SIZE));
            }
        }

        @Override
        public void visit(NullConstant nullConstant) {
            nullConstant.setResultNumber(new Immediate(0, MiracleOption.POINTER_SIZE));
        }

        @Override
        public void visit(This thisNode) {
            thisNode.setResultNumber(curFunction.getSelfRegister());
        }

        @Override
        public void visit(Field field) {
            field.expression.accept(this);
            SymbolFunctionType functionType = field.expression.getResultType().getMethod(field.identifier);
            if (functionType != null) {
                field.setResultNumber(null);
            } else {
                SymbolType type = field.expression.getResultType();
                Number number = field.expression.getResultNumber();
                if (number instanceof IndirectRegister) {
                    VirtualRegister register = newVirtualRegister(number.getNumberSize());
                    curBasicBlock.tail.prepend(new Move(register, number));
                    number = register;
                }
                field.setResultNumber(new OffsetRegister(
                        (DirectRegister) number,
                        ((SymbolClassType) type).getVariableOffset(field.identifier),
                        null, null,
                        ((SymbolClassType) type).getVariable(field.identifier).getRegisterSize()
                ));
            }
        }

        @Override
        public void visit(TypeNode typeNode) {

        }

        public Root build() {
            return new Root(globalVariable, globalString, globalFunction);
        }
    }
}
