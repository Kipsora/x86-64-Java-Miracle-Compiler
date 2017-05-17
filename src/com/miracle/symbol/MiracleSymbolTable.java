package com.miracle.symbol;

import com.miracle.astree.base.MiracleASTreeTypeNode;
import com.miracle.astree.statement.declaration.MiracleASTreeClassDeclaration;
import com.miracle.astree.statement.declaration.MiracleASTreeDeclaration;
import com.miracle.astree.statement.declaration.MiracleASTreeFunctionDeclaration;
import com.miracle.astree.statement.declaration.MiracleASTreeVariableDeclaration;
import com.miracle.symbol.type.MiracleBaseType;

import java.util.*;

public class MiracleSymbolTable {
    public final static MiracleBaseType __builtin_void;
    public final static MiracleBaseType __builtin_int;
    public final static MiracleBaseType __builtin_bool;
    public final static MiracleBaseType __builtin_string;
    public final static MiracleBaseType __builtin_array;
    public final static MiracleBaseType __builtin_null;

    private final static MiracleASTreeFunctionDeclaration __builtin_print;
    private final static MiracleASTreeFunctionDeclaration __builtin_println;
    private final static MiracleASTreeFunctionDeclaration __builtin_getString;
    private final static MiracleASTreeFunctionDeclaration __builtin_getInt;
    private final static MiracleASTreeFunctionDeclaration __builtin_toString;

    private final static MiracleASTreeClassDeclaration __builtin_Void;
    private final static MiracleASTreeClassDeclaration __builtin_Int;
    private final static MiracleASTreeClassDeclaration __builtin_Bool;
    private final static MiracleASTreeClassDeclaration __builtin_String;
    private final static MiracleASTreeClassDeclaration __builtin_Array;
    private final static MiracleASTreeClassDeclaration __builtin_Null;
    private final static Map<String, MiracleASTreeFunctionDeclaration> builtinMethod;
    private final static Map<String, MiracleASTreeClassDeclaration> builtinType;

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
                Collections.singletonList(new MiracleASTreeVariableDeclaration(
                        "a",
                        new MiracleASTreeTypeNode(__builtin_string, null)
                ))
        );
        __builtin_println = new MiracleASTreeFunctionDeclaration(
                "println",
                new MiracleASTreeTypeNode(__builtin_void, null),
                Collections.singletonList(new MiracleASTreeVariableDeclaration(
                        "a",
                        new MiracleASTreeTypeNode(__builtin_string, null)
                ))
        );
        __builtin_getString = new MiracleASTreeFunctionDeclaration(
                "getString",
                new MiracleASTreeTypeNode(__builtin_string, null),
                new LinkedList<>()
        );
        __builtin_getInt = new MiracleASTreeFunctionDeclaration(
                "getInt",
                new MiracleASTreeTypeNode(__builtin_int, null),
                new LinkedList<>()
        );
        __builtin_toString = new MiracleASTreeFunctionDeclaration(
                "toString",
                new MiracleASTreeTypeNode(__builtin_string, null),
                Collections.singletonList(new MiracleASTreeVariableDeclaration(
                        "a",
                        new MiracleASTreeTypeNode(__builtin_int, null)
                ))
        );

        __builtin_Void = new MiracleASTreeClassDeclaration("void");
        __builtin_Int = new MiracleASTreeClassDeclaration("int");
        __builtin_Bool = new MiracleASTreeClassDeclaration("bool");
        __builtin_Null = new MiracleASTreeClassDeclaration("null");
        __builtin_String = new MiracleASTreeClassDeclaration("string",
                Arrays.asList(
                        new MiracleASTreeFunctionDeclaration(
                                "substring",
                                new MiracleASTreeTypeNode(__builtin_string, null),
                                Arrays.asList(
                                        new MiracleASTreeVariableDeclaration(
                                                "a",
                                                new MiracleASTreeTypeNode(__builtin_int, null)
                                        ),
                                        new MiracleASTreeVariableDeclaration(
                                                "b",
                                                new MiracleASTreeTypeNode(__builtin_int, null)
                                        )
                                )
                        ),
                        new MiracleASTreeFunctionDeclaration(
                                "length",
                                new MiracleASTreeTypeNode(__builtin_int, null),
                                new LinkedList<>()
                        ),
                        new MiracleASTreeFunctionDeclaration(
                                "ord",
                                new MiracleASTreeTypeNode(__builtin_int, null),
                                Collections.singletonList(new MiracleASTreeVariableDeclaration(
                                        "a",
                                        new MiracleASTreeTypeNode(__builtin_int, null)
                                ))
                        ),
                        new MiracleASTreeFunctionDeclaration(
                                "parseInt",
                                new MiracleASTreeTypeNode(__builtin_int, null),
                                new LinkedList<>()
                        ),
                        new MiracleASTreeFunctionDeclaration(
                                "$string.less",
                                new MiracleASTreeTypeNode(__builtin_bool, null),
                                Arrays.asList(
                                        new MiracleASTreeVariableDeclaration(
                                                "a",
                                                new MiracleASTreeTypeNode(__builtin_string, null)
                                        ),
                                        new MiracleASTreeVariableDeclaration(
                                                "b",
                                                new MiracleASTreeTypeNode(__builtin_string, null)
                                        )
                                )
                        ),
                        new MiracleASTreeFunctionDeclaration(
                                "$string.concat",
                                new MiracleASTreeTypeNode(__builtin_string, null),
                                Arrays.asList(
                                        new MiracleASTreeVariableDeclaration(
                                                "a",
                                                new MiracleASTreeTypeNode(__builtin_string, null)
                                        ),
                                        new MiracleASTreeVariableDeclaration(
                                                "b",
                                                new MiracleASTreeTypeNode(__builtin_string, null)
                                        )
                                )
                        )
                )
        );
        __builtin_Array = new MiracleASTreeClassDeclaration("$array",
                Collections.singletonList(new MiracleASTreeFunctionDeclaration(
                        "size",
                        new MiracleASTreeTypeNode(__builtin_int, null),
                        new LinkedList<>()
                ))
        );
        builtinMethod = Collections.unmodifiableMap(new HashMap<String, MiracleASTreeFunctionDeclaration>() {{
            put(__builtin_print.identifier, __builtin_print);
            put(__builtin_println.identifier, __builtin_println);
            put(__builtin_getInt.identifier, __builtin_getInt);
            put(__builtin_getString.identifier, __builtin_getString);
            put(__builtin_toString.identifier, __builtin_toString);
        }});
        builtinType = Collections.unmodifiableMap(new HashMap<String, MiracleASTreeClassDeclaration>() {{
            put(__builtin_Void.identifier, __builtin_Void);
            put(__builtin_Int.identifier, __builtin_Int);
            put(__builtin_Bool.identifier, __builtin_Bool);
            put(__builtin_String.identifier, __builtin_String);
            put(__builtin_Array.identifier, __builtin_Array);
            put(__builtin_Null.identifier, __builtin_Null);
        }});
    }

    private final MiracleSymbolTable parentSymbolTable;
    private final Map<String, MiracleASTreeDeclaration> currentSymbolTable = new HashMap<>();

    public MiracleSymbolTable(MiracleSymbolTable parentSymbolTable) {
        this.parentSymbolTable = parentSymbolTable;
    }

    public static boolean hasBuiltinType(String name) {
        return builtinType.containsKey(name);
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

    public MiracleASTreeFunctionDeclaration getMainFunction() {
        MiracleASTreeDeclaration declaration = currentSymbolTable.getOrDefault("main", null);
        if (declaration != null && (declaration instanceof MiracleASTreeFunctionDeclaration)) {
            return (MiracleASTreeFunctionDeclaration) declaration;
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
