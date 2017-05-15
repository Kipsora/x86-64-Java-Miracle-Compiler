package com.miracle.symbol;

import com.miracle.astree.base.MiracleASTreeTypeNode;
import com.miracle.astree.statement.declaration.MiracleASTreeClassDeclaration;
import com.miracle.astree.statement.declaration.MiracleASTreeDeclaration;
import com.miracle.astree.statement.declaration.MiracleASTreeFunctionDeclaration;
import com.miracle.astree.statement.declaration.MiracleASTreeVariableDeclaration;
import com.miracle.symbol.type.MiracleBaseType;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class MiracleSymbolTable {
    public static MiracleBaseType __builtin_void;
    public static MiracleBaseType __builtin_int;
    public static MiracleBaseType __builtin_bool;
    public static MiracleBaseType __builtin_string;
    public static MiracleBaseType __builtin_array;
    public static MiracleBaseType __builtin_null;

    private static MiracleASTreeFunctionDeclaration __builtin_print;
    private static MiracleASTreeFunctionDeclaration __builtin_println;
    private static MiracleASTreeFunctionDeclaration __builtin_getString;
    private static MiracleASTreeFunctionDeclaration __builtin_getInt;
    private static MiracleASTreeFunctionDeclaration __builtin_toString;

    private static MiracleASTreeFunctionDeclaration __builtin_string_lessthan;
    private static MiracleASTreeFunctionDeclaration __builtin_string_concat;

    private static MiracleASTreeFunctionDeclaration __builtin_array_size;

    private static MiracleASTreeClassDeclaration __builtin_Void;
    private static MiracleASTreeClassDeclaration __builtin_Int;
    private static MiracleASTreeClassDeclaration __builtin_Bool;
    private static MiracleASTreeClassDeclaration __builtin_String;
    private static MiracleASTreeClassDeclaration __builtin_Array;
    private static MiracleASTreeClassDeclaration __builtin_Null;
    private static Map<String, MiracleASTreeFunctionDeclaration> builtinMethod;
    private static Map<String, MiracleASTreeClassDeclaration> builtinType;

    static {
        __builtin_void = new MiracleBaseType("void");
        __builtin_int = new MiracleBaseType("int");
        __builtin_bool = new MiracleBaseType("bool");
        __builtin_string = new MiracleBaseType("string");
        __builtin_array = new MiracleBaseType("$array");
        __builtin_null = new MiracleBaseType("null");

        __builtin_print = new MiracleASTreeFunctionDeclaration(
                "print",
                new MiracleASTreeTypeNode(__builtin_void, null),
                new LinkedList<MiracleASTreeVariableDeclaration>() {
                    private static final long serialVersionUID = 1397849931823570227L;

                    {
                        add(new MiracleASTreeVariableDeclaration(
                                "a",
                                new MiracleASTreeTypeNode(__builtin_string, null),
                                null,
                                null,
                                null,
                                false)
                        );
                    }
                },
                null,
                null,
                null
        );
        __builtin_println = new MiracleASTreeFunctionDeclaration(
                "println",
                new MiracleASTreeTypeNode(__builtin_void, null),
                new LinkedList<MiracleASTreeVariableDeclaration>() {
                    private static final long serialVersionUID = 1397849931823570227L;

                    {
                        add(new MiracleASTreeVariableDeclaration(
                                "a",
                                new MiracleASTreeTypeNode(__builtin_string, null),
                                null,
                                null,
                                null,
                                false)
                        );
                    }
                },
                null,
                null,
                null
        );
        __builtin_getString = new MiracleASTreeFunctionDeclaration(
                "getString",
                new MiracleASTreeTypeNode(__builtin_string, null),
                new LinkedList<>(),
                null,
                null,
                null
        );
        __builtin_getInt = new MiracleASTreeFunctionDeclaration(
                "getInt",
                new MiracleASTreeTypeNode(__builtin_int, null),
                new LinkedList<>(),
                null,
                null,
                null
        );
        __builtin_toString = new MiracleASTreeFunctionDeclaration(
                "toString",
                new MiracleASTreeTypeNode(__builtin_string, null),
                new LinkedList<MiracleASTreeVariableDeclaration>() {
                    private static final long serialVersionUID = 1397849931823570227L;

                    {
                        add(new MiracleASTreeVariableDeclaration(
                                "a",
                                new MiracleASTreeTypeNode(__builtin_int, null),
                                null,
                                null,
                                null,
                                false
                        ));
                    }
                },
                null,
                null,
                null
        );
        __builtin_string_concat = new MiracleASTreeFunctionDeclaration(
                "$string.concat",
                new MiracleASTreeTypeNode(__builtin_string, null),
                new LinkedList<MiracleASTreeVariableDeclaration>() {
                    private static final long serialVersionUID = 289980611494618290L;

                    {
                        add(new MiracleASTreeVariableDeclaration(
                                "a",
                                new MiracleASTreeTypeNode(__builtin_string, null),
                                null,
                                null,
                                null,
                                false
                        ));
                        add(new MiracleASTreeVariableDeclaration(
                                "b",
                                new MiracleASTreeTypeNode(__builtin_string, null),
                                null,
                                null,
                                null,
                                false
                        ));
                    }
                },
                null,
                null,
                null
        );
        __builtin_string_lessthan = new MiracleASTreeFunctionDeclaration(
                "$string.less",
                new MiracleASTreeTypeNode(__builtin_bool, null),
                new LinkedList<MiracleASTreeVariableDeclaration>() {
                    private static final long serialVersionUID = 289980611494618290L;

                    {
                        add(new MiracleASTreeVariableDeclaration(
                                "a",
                                new MiracleASTreeTypeNode(__builtin_string, null),
                                null,
                                null,
                                null,
                                false
                        ));
                        add(new MiracleASTreeVariableDeclaration(
                                "b",
                                new MiracleASTreeTypeNode(__builtin_string, null),
                                null,
                                null,
                                null,
                                false
                        ));
                    }
                },
                null,
                null,
                null
        );
        __builtin_array_size = new MiracleASTreeFunctionDeclaration(
                "size",
                new MiracleASTreeTypeNode(__builtin_int, null),
                new LinkedList<MiracleASTreeVariableDeclaration>(),
                null,
                null,
                null
        );

        __builtin_Void = new MiracleASTreeClassDeclaration("void",
                null,
                null,
                null,
                null,
                null
        );
        __builtin_Int = new MiracleASTreeClassDeclaration("int",
                null,
                null,
                null,
                null,
                null
        );
        __builtin_Bool = new MiracleASTreeClassDeclaration("bool",
                null,
                null,
                null,
                null,
                null
        );
        __builtin_Null = new MiracleASTreeClassDeclaration("null",
                null,
                null,
                null,
                null,
                null
        );
        __builtin_String = new MiracleASTreeClassDeclaration("string",
                null,
                new LinkedList<MiracleASTreeFunctionDeclaration>() {
                    private static final long serialVersionUID = -8896859257944921595L;

                    {
                        add(new MiracleASTreeFunctionDeclaration(
                                "substring",
                                new MiracleASTreeTypeNode(__builtin_string, null),
                                new LinkedList<MiracleASTreeVariableDeclaration>() {
                                    private static final long serialVersionUID = 419070340520120804L;

                                    {
                                        add(new MiracleASTreeVariableDeclaration(
                                                "a",
                                                new MiracleASTreeTypeNode(__builtin_int, null),
                                                null,
                                                null,
                                                null,
                                                false
                                        ));
                                        add(new MiracleASTreeVariableDeclaration(
                                                "b",
                                                new MiracleASTreeTypeNode(__builtin_int, null),
                                                null,
                                                null,
                                                null,
                                                false
                                        ));
                                    }
                                },
                                null,
                                null,
                                null
                        ));
                        add(new MiracleASTreeFunctionDeclaration(
                                "length",
                                new MiracleASTreeTypeNode(__builtin_int, null),
                                new LinkedList<>(),
                                null,
                                null,
                                null
                        ));
                        add(new MiracleASTreeFunctionDeclaration(
                                "ord",
                                new MiracleASTreeTypeNode(__builtin_int, null),
                                new LinkedList<MiracleASTreeVariableDeclaration>() {
                                    private static final long serialVersionUID = 5968958016338308327L;

                                    {
                                        add(new MiracleASTreeVariableDeclaration(
                                                "a",
                                                new MiracleASTreeTypeNode(__builtin_int, null),
                                                null,
                                                null,
                                                null,
                                                false
                                        ));
                                    }
                                },
                                null,
                                null,
                                null
                        ));
                        add(new MiracleASTreeFunctionDeclaration(
                                "parseInt",
                                new MiracleASTreeTypeNode(__builtin_int, null),
                                new LinkedList<>(),
                                null,
                                null,
                                null
                        ));
                        add(__builtin_string_lessthan);
                        add(__builtin_string_concat);
                    }
                },
                null,
                null,
                null
        );
        __builtin_Array = new MiracleASTreeClassDeclaration("$array",
                null,
                new LinkedList<MiracleASTreeFunctionDeclaration>() {
                    private static final long serialVersionUID = 1397849931823570227L;

                    {
                        add(__builtin_array_size);
                    }
                },
                null,
                null,
                null
        );
        builtinMethod = Collections.unmodifiableMap(
                new HashMap<String, MiracleASTreeFunctionDeclaration>() {
                    private static final long serialVersionUID = -8896859257944921595L;

                    {
                        put(__builtin_print.identifier, __builtin_print);
                        put(__builtin_println.identifier, __builtin_println);
                        put(__builtin_getInt.identifier, __builtin_getInt);
                        put(__builtin_getString.identifier, __builtin_getString);
                        put(__builtin_toString.identifier, __builtin_toString);
                    }
                }
        );
        builtinType = Collections.unmodifiableMap(
                new HashMap<String, MiracleASTreeClassDeclaration>() {
                    private static final long serialVersionUID = -7246160769398647470L;

                    {
                        put(__builtin_Void.identifier, __builtin_Void);
                        put(__builtin_Int.identifier, __builtin_Int);
                        put(__builtin_Bool.identifier, __builtin_Bool);
                        put(__builtin_String.identifier, __builtin_String);
                        put(__builtin_Array.identifier, __builtin_Array);
                        put(__builtin_Null.identifier, __builtin_Null);
                    }
                }
        );
    }

    private final MiracleSymbolTable parentSymbolTable;
    private final Map<String, MiracleASTreeDeclaration> currentSymbolTable = new HashMap<>();

    public MiracleSymbolTable(MiracleSymbolTable parentSymbolTable) {
        this.parentSymbolTable = parentSymbolTable;
    }

    public static MiracleBaseType getBuiltinType(String name) {
        if (builtinType.containsKey(name)) {
            return (MiracleBaseType) builtinType.get(name).getType();
        }
        return null;
    }

    public boolean put(String name, MiracleASTreeDeclaration declaration) {
        if (builtinType.containsKey(name)) {
            return false;
        }
        if (currentSymbolTable.containsKey(name)) {
            return false;
        }
        currentSymbolTable.put(name, declaration);
        return true;
    }

    public MiracleASTreeFunctionDeclaration getFunctionIncludeAncestor(String name) {
        if (builtinMethod.containsKey(name)) {
            return builtinMethod.get(name);
        }
        MiracleASTreeDeclaration declaration = currentSymbolTable.getOrDefault(name, null);
        if (declaration != null && (declaration instanceof MiracleASTreeFunctionDeclaration)) {
            return (MiracleASTreeFunctionDeclaration) declaration;
        }
        if (parentSymbolTable != null) {
            return parentSymbolTable.getFunctionIncludeAncestor(name);
        }
        return null;
    }

    public MiracleASTreeVariableDeclaration getVariableIncludeAncestor(String name) {
        MiracleASTreeDeclaration declaration = currentSymbolTable.getOrDefault(name, null);
        if (declaration != null && (declaration instanceof MiracleASTreeVariableDeclaration)) {
            return (MiracleASTreeVariableDeclaration) declaration;
        }
        if (parentSymbolTable != null) {
            return parentSymbolTable.getVariableIncludeAncestor(name);
        }
        return null;
    }

    public MiracleASTreeClassDeclaration getClassIncludeAncestor(String name) {
        if (builtinType.containsKey(name)) {
            return builtinType.get(name);
        }
        MiracleASTreeDeclaration declaration = currentSymbolTable.getOrDefault(name, null);
        if (declaration != null && (declaration instanceof MiracleASTreeClassDeclaration)) {
            return (MiracleASTreeClassDeclaration) declaration;
        }
        if (parentSymbolTable != null) {
            return parentSymbolTable.getClassIncludeAncestor(name);
        }
        return null;
    }

    public MiracleASTreeFunctionDeclaration getFunction(String name) {
        MiracleASTreeDeclaration declaration = currentSymbolTable.getOrDefault(name, null);
        if (declaration != null && (declaration instanceof MiracleASTreeFunctionDeclaration)) {
            return (MiracleASTreeFunctionDeclaration) declaration;
        }
        if (builtinMethod.containsKey(name)) {
            return builtinMethod.get(name);
        }
        return null;
    }

    public MiracleASTreeVariableDeclaration getVariable(String name) {
        MiracleASTreeDeclaration declaration = currentSymbolTable.getOrDefault(name, null);
        if (declaration != null && (declaration instanceof MiracleASTreeVariableDeclaration)) {
            return (MiracleASTreeVariableDeclaration) declaration;
        }
        return null;
    }

    public MiracleASTreeClassDeclaration getClass(String name) {
        if (builtinType.containsKey(name)) {
            return builtinType.get(name);
        }
        MiracleASTreeDeclaration declaration = currentSymbolTable.getOrDefault(name, null);
        if (declaration != null && (declaration instanceof MiracleASTreeClassDeclaration)) {
            return (MiracleASTreeClassDeclaration) declaration;
        }
        return null;
    }

    public MiracleASTreeDeclaration getIncludeAncestor(String name) {
        if (currentSymbolTable.containsKey(name)) {
            return currentSymbolTable.get(name);
        }
        if (parentSymbolTable != null) {
            return parentSymbolTable.getIncludeAncestor(name);
        }
        if (builtinMethod.containsKey(name)) {
            return builtinMethod.get(name);
        }
        return null;
    }

    public MiracleSymbolTable getParentSymbolTable() {
        return parentSymbolTable;
    }
}
