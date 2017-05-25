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
import com.miracle.intermediate.instruction.Call;
import com.miracle.intermediate.instruction.Compare;
import com.miracle.intermediate.instruction.HeapAllocate;
import com.miracle.intermediate.instruction.Move;
import com.miracle.intermediate.instruction.arithmetic.BinaryArithmetic;
import com.miracle.intermediate.instruction.arithmetic.UnaryArithmetic;
import com.miracle.intermediate.instruction.fork.Branch;
import com.miracle.intermediate.instruction.fork.Jump;
import com.miracle.intermediate.instruction.fork.Return;
import com.miracle.intermediate.number.*;
import com.miracle.intermediate.number.Number;
import com.miracle.intermediate.structure.BasicBlock;
import com.miracle.intermediate.structure.Function;
import com.miracle.intermediate.visitor.Visitor;
import com.miracle.symbol.*;
import org.apache.commons.lang3.tuple.ImmutablePair;

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
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public static class Builder implements com.miracle.astree.visitor.Visitor {
        private Map<String, StaticVariable> globalVariable;
        private Map<String, StaticString> globalString;
        private Map<String, Function> globalFunction;

        private BasicBlock curBasicBlock;
        private BasicBlock loopCondBlock;
        private BasicBlock loopExitBlock;
        private Function curFunction;

        private SymbolClassType curClass;

        private int countTmpRegister;
        private int countBlock;

        @Override
        public void visit(FunctionDeclaration functionDeclaration) {
            curFunction = functionDeclaration.getSymbol().getAddress();
            globalFunction.put(functionDeclaration.identifier, curFunction);
            curBasicBlock = curFunction.getEntryBasicBlock();
            functionDeclaration.parameters.forEach(element -> element.accept(this));
            functionDeclaration.body.forEach(element -> element.accept(this));

            curBasicBlock.addSuccBasicBlock(curFunction.getExitBasicBlock());
            if (!curBasicBlock.isForked()) {
                curBasicBlock.setFork(new Jump(curFunction.getExitBasicBlock()));
            } else {
                curBasicBlock.tail.prepend(new Jump(curFunction.getExitBasicBlock()));
            }
            curFunction.getExitBasicBlock().setFork(new Return());
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
            Function miracleEntryFunction = new Function(
                    "__start",
                    new SymbolFunctionType(__builtin_void, null)
            );
            globalFunction.put("__start", miracleEntryFunction);
            curFunction = miracleEntryFunction;
            curBasicBlock = miracleEntryFunction.getEntryBasicBlock();
            astree.declarations.forEach(element -> {
                if (element instanceof VariableDeclaration) {
                    element.accept(this);
                }
            });
            VirtualRegister register =
                    new VirtualRegister(".t" + String.valueOf(countTmpRegister++), MiracleOption.INT_SIZE);

            curBasicBlock.addSuccBasicBlock(curFunction.getExitBasicBlock());
            if (!curBasicBlock.isForked()) {
                curBasicBlock.setFork(new Jump(curFunction.getExitBasicBlock()));
            } else {
                curBasicBlock.tail.prepend(new Jump(curFunction.getExitBasicBlock()));
            }
            curFunction.getExitBasicBlock().setFork(new Return());

            astree.declarations.forEach(element -> {
                if (!(element instanceof VariableDeclaration)) {
                    element.accept(this);
                }
            });

            curBasicBlock = null;
            curFunction = null;

            miracleEntryFunction.getEntryBasicBlock().tail.prepend(new Call(
                    ((FunctionDeclaration) astree.getScope().resolve("main")).getSymbol().getAddress(),
                    new LinkedList<>(), register, null
            ));
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
            selection.expression.accept(this);
            BasicBlock passBlock = new BasicBlock("if_pass_" + String.valueOf(countBlock++), curFunction, false, false);
            BasicBlock failBlock = new BasicBlock("if_fail_" + String.valueOf(countBlock++), curFunction, false, false);
            BasicBlock bothBlock = new BasicBlock("if_both_" + String.valueOf(countBlock++), curFunction, false, false);
            curBasicBlock.addSuccBasicBlock(passBlock);
            curBasicBlock.addSuccBasicBlock(failBlock);
            curBasicBlock.setFork(new Branch(
                    selection.expression.getResultNumber(),
                    passBlock, failBlock
            ));
            curBasicBlock = passBlock;
            if (selection.branchTrue != null) {
                selection.branchTrue.accept(this);
            }

            curBasicBlock.addSuccBasicBlock(bothBlock);
            if (!curBasicBlock.isForked()) {
                curBasicBlock.setFork(new Jump(bothBlock));
            } else {
                curBasicBlock.tail.prepend(new Jump(bothBlock));
            }

            curBasicBlock = failBlock;
            if (selection.branchFalse != null) {
                selection.branchFalse.accept(this);
            }

            curBasicBlock.addSuccBasicBlock(bothBlock);
            if (!curBasicBlock.isForked()) {
                curBasicBlock.setFork(new Jump(bothBlock));
            } else {
                curBasicBlock.tail.prepend(new Jump(bothBlock));
            }

            curBasicBlock = bothBlock;
        }

        @Override
        public void visit(Iteration iteration) {
            if (iteration.initializeExpression != null) {
                iteration.initializeExpression.accept(this);
            }
            BasicBlock condBlock = new BasicBlock("iter_cond_" + String.valueOf(countBlock++), curFunction, false, false);

            curBasicBlock.addSuccBasicBlock(condBlock);
            if (!curBasicBlock.isForked()) {
                curBasicBlock.setFork(new Jump(condBlock));
            } else {
                curBasicBlock.tail.prepend(new Jump(condBlock));
            }

            curBasicBlock = condBlock;
            BasicBlock bodyBlock = new BasicBlock("iter_body_" + String.valueOf(countBlock++), curFunction, false, false);
            BasicBlock exitBlock = new BasicBlock("iter_exit_" + String.valueOf(countBlock++), curFunction, false, false);
            if (iteration.conditionExpression != null) {
                iteration.conditionExpression.accept(this);

                curBasicBlock.addSuccBasicBlock(bodyBlock);
                curBasicBlock.addSuccBasicBlock(exitBlock);
                curBasicBlock.setFork(new Branch(
                        iteration.conditionExpression.getResultNumber(),
                        bodyBlock, exitBlock
                ));
            } else {
                curBasicBlock.setFork(new Jump(bodyBlock));
                curBasicBlock.addSuccBasicBlock(bodyBlock);
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

            curBasicBlock.addSuccBasicBlock(condBlock);
            if (!curBasicBlock.isForked()) {
                curBasicBlock.setFork(new Jump(condBlock));
            } else {
                curBasicBlock.tail.prepend(new Jump(condBlock));
            }

            curBasicBlock = exitBlock;

            loopExitBlock = oldExitBlock;
            loopCondBlock = oldBodyBlock;
        }

        @Override
        public void visit(Break breakLiteral) {
            curBasicBlock.addSuccBasicBlock(loopExitBlock);
            if (!curBasicBlock.isForked()) {
                curBasicBlock.setFork(new Jump(loopExitBlock));
            } else {
                curBasicBlock.tail.prepend(new Jump(loopExitBlock));
            }
        }

        @Override
        public void visit(Continue continueLiteral) {
            curBasicBlock.addSuccBasicBlock(loopCondBlock);
            if (!curBasicBlock.isForked()) {
                curBasicBlock.setFork(new Jump(loopCondBlock));
            } else {
                curBasicBlock.tail.prepend(new Jump(loopCondBlock));
            }
        }

        @Override
        public void visit(com.miracle.astree.statement.Return returnLiteral) {
            if (returnLiteral.expression != null) {
                returnLiteral.expression.accept(this);
                curBasicBlock.tail.prepend(new Move(
                        curFunction.getReturnRegister(),
                        returnLiteral.expression.getResultNumber()
                ));
            }
            curBasicBlock.addSuccBasicBlock(curFunction.getExitBasicBlock());
            if (!curBasicBlock.isForked()) {
                curBasicBlock.setFork(new Jump(curFunction.getExitBasicBlock()));
            } else {
                curBasicBlock.tail.prepend(new Jump(curFunction.getExitBasicBlock()));
            }
        }

        @Override
        public void visit(Variable variable) {
            Symbol symbol = variable.getScope().resolve(variable.identifier);
            if (symbol instanceof VariableDeclaration) {
                if (((VariableDeclaration) symbol).getMemberFrom() != null) {
                    // if a variable in class has no this before, then I will force to add it
                    variable.setResultNumber(new OffsetRegister(
                            curFunction.getSelfRegister(),
                            new Immediate(
                                    curClass.getVariableOffset(variable.identifier),
                                    MiracleOption.INT_SIZE
                            ),
                            null,
                            curClass.getVariable(variable.identifier).getRegisterSize()
                    ));
                } else {
                    variable.setResultNumber(((VariableDeclaration) symbol).getAddress());
                }
            } else if (symbol instanceof FunctionDeclaration) {
                variable.setResultNumber(((FunctionDeclaration) symbol)
                        .getSymbol().getAddress().getReturnRegister()
                );
            } else if (symbol instanceof SymbolFunctionType) {
                variable.setResultNumber(((SymbolFunctionType) symbol)
                        .getAddress().getReturnRegister()
                );
            } else {
                throw new RuntimeException("unexpected case");
            }
        }

        @Override
        public void visit(com.miracle.astree.statement.expression.Call call) {
            call.function.accept(this);
            SymbolFunctionType type = (SymbolFunctionType) call.function.getResultType();
            List<Number> parameters = new LinkedList<>();
            call.parameters.forEach(element -> {
                element.accept(this);
                parameters.add(element.getResultNumber());
            });
            Register selfRegister = null;
            if (call.function instanceof Field) {
                selfRegister = (Register) ((Field) call.function).expression.getResultNumber();
            }
            if (type.getReturnType().isSameType(__builtin_void)) {
                curBasicBlock.tail.prepend(new Call(
                        type.getAddress(), parameters,
                        null, selfRegister
                ));
            } else {
                Register register = new VirtualRegister(
                        ".t" + String.valueOf(countTmpRegister++),
                        type.getReturnType().getRegisterSize()
                );
                curBasicBlock.tail.prepend(new Call(
                        type.getAddress(), parameters,
                        register, selfRegister
                ));
                call.setResultNumber(register);
            }
        }

        @Override
        public void visit(Subscript subscript) {
            subscript.base.accept(this);
            subscript.coordinate.accept(this);
            int size = ((SymbolArrayType) subscript.base.getResultType()).subscript().getRegisterSize();
            Number tmpCoor = subscript.coordinate.getResultNumber();
            if (!(tmpCoor instanceof DirectRegister)) {
                VirtualRegister victim = new VirtualRegister(
                        ".t" + String.valueOf(countTmpRegister++),
                        subscript.coordinate.getResultType().getRegisterSize()
                );
                curBasicBlock.tail.prepend(new Move(victim, tmpCoor));
                tmpCoor = victim;
            }
            Number tmpBase = subscript.base.getResultNumber();
            if (!(tmpBase instanceof DirectRegister)) {
                VirtualRegister victim = new VirtualRegister(
                        ".t" + String.valueOf(countTmpRegister++),
                        subscript.base.getResultType().getRegisterSize()
                );
                curBasicBlock.tail.prepend(new Move(victim, tmpBase));
                tmpBase = victim;
            }

            subscript.setResultNumber(new OffsetRegister(
                    (DirectRegister) tmpBase, null,
                    ImmutablePair.of((DirectRegister) tmpCoor, new Immediate(size, MiracleOption.INT_SIZE)),
                    size
            ));
        }

        private void addRelation(BinaryExpression expression) {
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
            DirectRegister register = new VirtualRegister(
                    ".t" + String.valueOf(countTmpRegister++),
                    MiracleOption.INT_SIZE
            );
            if (expression.left.getResultType().isSameType(__builtin_string)) {
                curBasicBlock.tail.prepend(new Call(
                        __builtin_strcmp.getAddress(),
                        Arrays.asList(
                                expression.left.getResultNumber(),
                                expression.right.getResultNumber()
                        ),
                        register,
                        null
                ));
                curBasicBlock.tail.prepend(new Compare(operator, register,
                        new Immediate(0, MiracleOption.INT_SIZE), register
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
            BinaryArithmetic.Types operator;
            switch (expression.operator) {
                case DISJ:
                    operator = BinaryArithmetic.Types.OR;
                    break;
                case CONJ:
                    operator = BinaryArithmetic.Types.AND;
                    break;
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
            DirectRegister register = new VirtualRegister(
                    ".t" + String.valueOf(countTmpRegister++),
                    MiracleOption.INT_SIZE
            );
            curBasicBlock.tail.prepend(new Move(
                    register, expression.left.getResultNumber()
            ));
            if (operator.equals(BinaryArithmetic.Types.MOD)) {
                DirectRegister subtrahend = new VirtualRegister(
                        ".t" + String.valueOf(countTmpRegister++),
                        MiracleOption.INT_SIZE
                );
                curBasicBlock.tail.prepend(new Move(
                        subtrahend, expression.left.getResultNumber()
                ));
                curBasicBlock.tail.prepend(new BinaryArithmetic(
                        BinaryArithmetic.Types.DIV,
                        subtrahend, expression.right.getResultNumber()
                ));
                curBasicBlock.tail.prepend(new BinaryArithmetic(
                        BinaryArithmetic.Types.MUL,
                        subtrahend, expression.right.getResultNumber()
                ));
                curBasicBlock.tail.prepend(new BinaryArithmetic(
                        BinaryArithmetic.Types.SUB,
                        register, subtrahend
                ));
            } else {
                curBasicBlock.tail.prepend(new BinaryArithmetic(
                        operator, register, expression.right.getResultNumber()
                ));
            }
            expression.setResultNumber(register);
        }

        private void addAssignment(BinaryExpression expression) {
            curBasicBlock.tail.prepend(new Move(
                    (Register) expression.left.getResultNumber(),
                    expression.right.getResultNumber()
            ));
        }

        private void addStringConcat(BinaryExpression expression) {
            DirectRegister register = new VirtualRegister(
                    ".t" + String.valueOf(countTmpRegister++),
                    MiracleOption.INT_SIZE
            );
            curBasicBlock.tail.prepend(new Call(
                    __builtin_strcat.getAddress(),
                    Arrays.asList(
                            expression.left.getResultNumber(),
                            expression.right.getResultNumber()
                    ),
                    register, null
            ));
            expression.setResultNumber(register);
        }

        @Override
        public void visit(BinaryExpression binaryExpression) {
            binaryExpression.left.accept(this);
            binaryExpression.right.accept(this);
            switch (binaryExpression.operator) {
                case EQL:
                case NEQ:
                case LT:
                case RT:
                case LEQ:
                case REQ:
                    addRelation(binaryExpression);
                    break;
                case DISJ:
                case CONJ:
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
                    break;
                case NEGATIVE:
                    operator = UnaryArithmetic.Types.MINUS;
                    if (number instanceof Immediate) {
                        prefixExpression.setResultNumber(new Immediate(
                                -((Immediate) number).value,
                                number.getNumberSize()
                        ));
                        return;
                    }
                    break;
                case NEGATE:
                    DirectRegister register = new VirtualRegister(
                            ".t" + String.valueOf(countTmpRegister++),
                            MiracleOption.INT_SIZE
                    );
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
            DirectRegister register = new VirtualRegister(
                    ".t" + String.valueOf(countTmpRegister++),
                    MiracleOption.INT_SIZE
            );
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
                DirectRegister register = new VirtualRegister(
                        ".t" + String.valueOf(countTmpRegister++),
                        MiracleOption.POINTER_SIZE
                );
                curBasicBlock.tail.prepend(new HeapAllocate(
                        register, type.getMemorySize(),
                        new Immediate(1, MiracleOption.INT_SIZE)
                ));
                if (type instanceof SymbolClassType) {
                    SymbolFunctionType constructor = type.getMethod("");
                    if (constructor != null) {
                        curBasicBlock.tail.prepend(new Call(
                                constructor.getAddress(),
                                Collections.emptyList(),
                                null, register
                        ));
                    }
                }
                newNode.setResultNumber(register);
            } else { // Array Type
                DirectRegister register = new VirtualRegister(
                        ".t" + String.valueOf(countTmpRegister++),
                        MiracleOption.POINTER_SIZE
                );
                List<Expression> nextExprs = new LinkedList<>(newNode.expressions);
                Expression expression = nextExprs.remove(0);
                if (expression == null) return;
                expression.accept(this);

                curBasicBlock.tail.prepend(new HeapAllocate(
                        register, MiracleOption.POINTER_SIZE,
                        expression.getResultNumber()
                ));
                newNode.setResultNumber(register);

                if (!nextExprs.isEmpty()) {
                    DirectRegister iterreg = new VirtualRegister("new_init_iter_" + String.valueOf(countTmpRegister++), MiracleOption.INT_SIZE);
                    curBasicBlock.tail.prepend(new BinaryArithmetic(
                            BinaryArithmetic.Types.XOR,
                            iterreg, iterreg
                    ));
                    BasicBlock newInitCondBlock = new BasicBlock("new_init_cond_" + String.valueOf(countBlock++), curFunction, false, false);
                    BasicBlock newInitExitBlock = new BasicBlock("new_init_exit_" + String.valueOf(countBlock++), curFunction, false, false);
                    DirectRegister flagreg = new VirtualRegister("new_init_flag_" + String.valueOf(countTmpRegister++), MiracleOption.INT_SIZE);

                    curBasicBlock.addSuccBasicBlock(newInitCondBlock);
                    if (!curBasicBlock.isForked()) {
                        curBasicBlock.setFork(new Jump(newInitCondBlock));
                    } else {
                        curBasicBlock.tail.prepend(new Jump(newInitCondBlock));
                    }

                    curBasicBlock = newInitCondBlock;
                    curBasicBlock.tail.prepend(new Compare(
                            Compare.Types.LT, iterreg,
                            expression.getResultNumber(), flagreg
                    ));
                    BasicBlock newInitBodyBlock = new BasicBlock("new_init_body_" + String.valueOf(countBlock++), curFunction, false, false);

                    curBasicBlock.addSuccBasicBlock(newInitBodyBlock);
                    curBasicBlock.addSuccBasicBlock(newInitExitBlock);
                    curBasicBlock.setFork(new Branch(flagreg, newInitBodyBlock, newInitExitBlock));

                    curBasicBlock = newInitBodyBlock;
                    New nextNew = new New(newNode.variableType, nextExprs, null);
                    nextNew.setScope(newNode.getScope());
                    nextNew.accept(this);
                    curBasicBlock.tail.prepend(new Move(
                            new OffsetRegister(register, null,
                                    ImmutablePair.of(iterreg, new Immediate(MiracleOption.POINTER_SIZE, MiracleOption.INT_SIZE)),
                                    MiracleOption.POINTER_SIZE
                            ),
                            nextNew.getResultNumber()
                    ));
                    curBasicBlock.addSuccBasicBlock(newInitCondBlock);
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
                StaticString register = new StaticString(
                        ".str" + String.valueOf(countTmpRegister++),
                        stringConstant.value
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
                if (number instanceof OffsetRegister) {
                    VirtualRegister register = new VirtualRegister(
                            ".t" + String.valueOf(countTmpRegister++),
                            number.getNumberSize()
                    );
                    curBasicBlock.tail.prepend(new Move(register, number));
                    number = register;
                }
                field.setResultNumber(new OffsetRegister(
                        (DirectRegister) number,
                        new Immediate(
                                ((SymbolClassType) type).getVariableOffset(field.identifier),
                                MiracleOption.INT_SIZE
                        ),
                        null,
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
