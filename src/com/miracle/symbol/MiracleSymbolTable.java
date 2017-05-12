package com.miracle.symbol;

import com.miracle.astree.statement.MiracleASTreeIteration;
import com.miracle.astree.statement.declaration.MiracleASTreeDeclaration;
import com.miracle.astree.statement.declaration.MiracleASTreeFunctionDeclaration;
import com.miracle.astree.statement.declaration.MiracleASTreeVariableDeclaration;
import com.miracle.astree.type.MiracleASTreeArrayType;
import com.miracle.astree.type.MiracleASTreeBaseType;
import com.miracle.astree.type.MiracleASTreeFunctionType;
import com.miracle.astree.type.MiracleASTreeVariableType;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class MiracleSymbolTable {
    public static MiracleASTreeBaseType __builtin_void = new MiracleASTreeBaseType("void");
    public static MiracleASTreeBaseType __builtin_int = new MiracleASTreeBaseType("int");
    public static MiracleASTreeBaseType __builtin_bool = new MiracleASTreeBaseType("bool");
    public static MiracleASTreeBaseType __builtin_string = new MiracleASTreeBaseType("string");

    private static MiracleASTreeBaseType __builtin_null = new MiracleASTreeBaseType("null");
    private static MiracleASTreeArrayType __builtin_array = new MiracleASTreeArrayType(null, 0);

    private static MiracleASTreeFunctionDeclaration __builtin_print = new MiracleASTreeFunctionDeclaration(
            "print",
            __builtin_void,
            new LinkedList<MiracleASTreeVariableDeclaration>() {
                private static final long serialVersionUID = 1397849931823570227L;
                {
                    add(new MiracleASTreeVariableDeclaration("a", __builtin_string, null));
                }
            },
            null
    );

    private static MiracleASTreeFunctionDeclaration __builtin_println = new MiracleASTreeFunctionDeclaration(
            "println",
            __builtin_void,
            new LinkedList<MiracleASTreeVariableDeclaration>() {
                private static final long serialVersionUID = 1397849931823570227L;
                {
                    add(new MiracleASTreeVariableDeclaration("a", __builtin_string, null));
                }
            },
            null
    );

    private static MiracleASTreeFunctionDeclaration __builtin_getString = new MiracleASTreeFunctionDeclaration(
            "getString",
            __builtin_string,
            new LinkedList<MiracleASTreeVariableDeclaration>(),
            null
    );

    private static MiracleASTreeFunctionDeclaration __builtin_getInt = new MiracleASTreeFunctionDeclaration(
            "getInt",
            __builtin_int,
            new LinkedList<MiracleASTreeVariableDeclaration>(),
            null
    );

    private static MiracleASTreeFunctionDeclaration __builtin_toString = new MiracleASTreeFunctionDeclaration(
            "toString",
            __builtin_string,
            new LinkedList<MiracleASTreeVariableDeclaration>() {
                private static final long serialVersionUID = 1397849931823570227L;
                {
                    add(new MiracleASTreeVariableDeclaration("a", __builtin_int, null));
                }
            },
            null
    );

    private static MiracleASTreeFunctionDeclaration __builtin_string_lessthan = new MiracleASTreeFunctionDeclaration(
            "$string.less",
            __builtin_bool,
            new LinkedList<MiracleASTreeVariableDeclaration>() {
                private static final long serialVersionUID = 289980611494618290L;
                {
                    add(new MiracleASTreeVariableDeclaration("a", __builtin_string, null));
                    add(new MiracleASTreeVariableDeclaration("b", __builtin_string, null));
                }
            },
            null
    );

    private static MiracleASTreeFunctionDeclaration __builtin_string_length = new MiracleASTreeFunctionDeclaration(
            "$string.length",
            __builtin_int,
            new LinkedList<MiracleASTreeVariableDeclaration>() {
                private static final long serialVersionUID = -5600580671280003543L;
                {
                    add(new MiracleASTreeVariableDeclaration("self", __builtin_string, null));
                }
            },
            null
    );

    private static MiracleASTreeFunctionDeclaration __builtin_string_substring = new MiracleASTreeFunctionDeclaration(
            "$string.substring",
            __builtin_string,
            new LinkedList<MiracleASTreeVariableDeclaration>() {
                private static final long serialVersionUID = 419070340520120804L;
                {
                    add(new MiracleASTreeVariableDeclaration("self", __builtin_string, null));
                    add(new MiracleASTreeVariableDeclaration("l", __builtin_int, null));
                    add(new MiracleASTreeVariableDeclaration("r", __builtin_int, null));
                }
            },
            null
    );

    private static MiracleASTreeFunctionDeclaration __builtin_string_parseInt = new MiracleASTreeFunctionDeclaration(
            "$string.parseInt",
            __builtin_int,
            new LinkedList<MiracleASTreeVariableDeclaration>() {
                private static final long serialVersionUID = -975205882602668267L;
                {
                    add(new MiracleASTreeVariableDeclaration("self", __builtin_string, null));
                }
            },
            null
    );

    private static MiracleASTreeFunctionDeclaration __builtin_string_ord = new MiracleASTreeFunctionDeclaration(
            "$string.ord",
            __builtin_int,
            new LinkedList<MiracleASTreeVariableDeclaration>() {
                private static final long serialVersionUID = 5968958016338308327L;
                {
                    add(new MiracleASTreeVariableDeclaration("self", __builtin_string, null));
                    add(new MiracleASTreeVariableDeclaration("x", __builtin_int, null));
                }
            },
            null
    );

    private static MiracleASTreeFunctionDeclaration __builtin_array_size = new MiracleASTreeFunctionDeclaration(
            "$array.size",
            __builtin_int,
            new LinkedList<MiracleASTreeVariableDeclaration>() {
                private static final long serialVersionUID = 5968958016338308327L;
                {
                    add(new MiracleASTreeVariableDeclaration("self", __builtin_array, null));
                }
            },
            null
    );

    private static Map<String, MiracleASTreeFunctionDeclaration> builtinMethod = Collections.unmodifiableMap(
            new HashMap<String, MiracleASTreeFunctionDeclaration>() {
                private static final long serialVersionUID = 1397849931823570227L;
                {
                    put(__builtin_print.identifier, __builtin_print);
                    put(__builtin_println.identifier, __builtin_println);
                    put(__builtin_getInt.identifier, __builtin_getInt);
                    put(__builtin_getString.identifier, __builtin_getString);
                    put(__builtin_toString.identifier, __builtin_toString);

                    put(__builtin_array_size.identifier, __builtin_array_size);
                    put(__builtin_string_ord.identifier, __builtin_string_ord);
                    put(__builtin_string_length.identifier, __builtin_string_length);
                    put(__builtin_string_lessthan.identifier, __builtin_string_lessthan);
                    put(__builtin_string_parseInt.identifier, __builtin_string_parseInt);
                    put(__builtin_string_substring.identifier, __builtin_string_substring);
                }
            }
    );

    private static Map<String, MiracleASTreeBaseType> builtinType = Collections.unmodifiableMap(
            new HashMap<String, MiracleASTreeBaseType>() {
                private static final long serialVersionUID = -7246160769398647470L;
                {
                    put(__builtin_void.identifier, __builtin_void);
                    put(__builtin_int.identifier, __builtin_int);
                    put(__builtin_bool.identifier, __builtin_bool);
                    put(__builtin_string.identifier, __builtin_string);
                    put(__builtin_null.identifier, __builtin_null);
                }
            }
    );

    private final MiracleSymbolTable parentSymbolTable;
    private final Map<String, MiracleASTreeDeclaration> currentSymbolTable = new HashMap<>();
    private MiracleASTreeFunctionDeclaration functionDeclaration;
    private MiracleASTreeIteration iteration;

    public MiracleSymbolTable(MiracleSymbolTable parentSymbolTable) {
        this.parentSymbolTable = parentSymbolTable;
        this.functionDeclaration = functionDeclaration;
        this.iteration = iteration;
    }

    public void setFunctionDeclaration(MiracleASTreeFunctionDeclaration functionDeclaration) {
        this.functionDeclaration = functionDeclaration;
    }

    public void setIteration(MiracleASTreeIteration iteration) {
        this.iteration = iteration;
    }

    public final MiracleASTreeFunctionDeclaration getFunctionDeclaration() {
        if (functionDeclaration != null) {
            return functionDeclaration;
        }
        if (parentSymbolTable != null) {
            return parentSymbolTable.getFunctionDeclaration();
        }
        return null;
    }

    public final MiracleASTreeIteration getIteration() {
        if (iteration != null) {
            return iteration;
        }
        if (parentSymbolTable != null) {
            return parentSymbolTable.getIteration();
        }
        return null;
    }

    public boolean put(String name, MiracleASTreeDeclaration declaration) {
        if (builtinType.containsKey(name)) {
            return false;
        }
        if (builtinMethod.containsKey(name)) {
            return false;
        }
        if (currentSymbolTable.containsKey(name)) {
            return false;
        }
        currentSymbolTable.put(name, declaration);
        return true;
    }

    public boolean contain(String name) {
        return currentSymbolTable.containsKey(name);
    }

    public boolean containIncludeAncestor(String name) {
        if (currentSymbolTable.containsKey(name)) {
            return true;
        }
        if (builtinMethod.containsKey(name)) {
            return true;
        }
        if (builtinType.containsKey(name)) {
            return true;
        }
        if (parentSymbolTable != null) {
            return parentSymbolTable.containIncludeAncestor(name);
        }
        return false;
    }

    public MiracleASTreeDeclaration get(String name) {
        if (builtinMethod.containsKey(name)) {
            return builtinMethod.get(name);
        }
        if (currentSymbolTable.containsKey(name)) {
            return currentSymbolTable.get(name);
        }
        return null;
    }

    public MiracleASTreeBaseType getBuiltinType(String name) {
        if (builtinType.containsKey(name)) {
            return builtinType.get(name);
        }
        return null;
    }

    public MiracleASTreeDeclaration getIncludeAncestor(String name) {
        if (builtinMethod.containsKey(name)) {
            return builtinMethod.get(name);
        }
        if (currentSymbolTable.containsKey(name)) {
            return currentSymbolTable.get(name);
        }
        if (parentSymbolTable != null) {
            return parentSymbolTable.getIncludeAncestor(name);
        }
        return null;
    }

    public MiracleSymbolTable getParentSymbolTable() {
        return parentSymbolTable;
    }
}
