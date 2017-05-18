package com.miracle.symbol;

import com.miracle.astree.statement.declaration.MiracleASTreeDeclaration;

import java.util.HashMap;
import java.util.Map;

import static com.miracle.symbol.MiracleSymbolPrimitiveType.Types.*;

public class MiracleSymbolTable {
    public final static MiracleSymbolNullType __builtin_null = MiracleSymbolNullType.INSTANCE;
    public final static MiracleSymbolPrimitiveType __builtin_void = new MiracleSymbolPrimitiveType(VOID);
    public final static MiracleSymbolPrimitiveType __builtin_int = new MiracleSymbolPrimitiveType(INT);
    public final static MiracleSymbolPrimitiveType __builtin_bool = new MiracleSymbolPrimitiveType(BOOL);
    public final static MiracleSymbolPrimitiveType __builtin_string = new MiracleSymbolPrimitiveType(STRING);

    private final static MiracleSymbolFunctionType __builtin_print = new MiracleSymbolFunctionType(__builtin_void) {{
        addParameter("x", __builtin_string);
    }};
    private final static MiracleSymbolFunctionType __builtin_println = new MiracleSymbolFunctionType(__builtin_void) {{
        addParameter("x", __builtin_string);
    }};
    private final static MiracleSymbolFunctionType __builtin_getString = new MiracleSymbolFunctionType(__builtin_string);
    private final static MiracleSymbolFunctionType __builtin_getInt = new MiracleSymbolFunctionType(__builtin_int);
    private final static MiracleSymbolFunctionType __builtin_toString = new MiracleSymbolFunctionType(__builtin_string) {{
        addParameter("x", __builtin_int);
    }};

    private final static Map<String, MiracleSymbolFunctionType> builtinMethod = new HashMap<String, MiracleSymbolFunctionType>() {{
        put("print", __builtin_print);
        put("println", __builtin_println);
        put("getString", __builtin_getString);
        put("getInt", __builtin_getInt);
        put("toString", __builtin_toString);
    }};
    private final static Map<String, MiracleSymbolPrimitiveType> builtinType = new HashMap<String, MiracleSymbolPrimitiveType>() {{
        put("bool", __builtin_bool);
        put("int", __builtin_int);
        put("string", __builtin_string);
        put("void", __builtin_void);
    }};

    static {
        __builtin_string.addMethod("length", new MiracleSymbolFunctionType(__builtin_int));
        __builtin_string.addMethod("substring", new MiracleSymbolFunctionType(__builtin_string) {{
            addParameter("x", __builtin_int);
            addParameter("y", __builtin_int);
        }});
        __builtin_string.addMethod("parseInt", new MiracleSymbolFunctionType(__builtin_int));
        __builtin_string.addMethod("substring", new MiracleSymbolFunctionType(__builtin_string) {{
            addParameter("x", __builtin_int);
            addParameter("y", __builtin_int);
        }});
        __builtin_string.addMethod("ord", new MiracleSymbolFunctionType(__builtin_int) {{
            addParameter("x", __builtin_int);
        }});
    }

    private final MiracleSymbolTable parentSymbolTable;
    private final Map<String, MiracleASTreeDeclaration> currentSymbolTable = new HashMap<>();

    public MiracleSymbolTable(MiracleSymbolTable parentSymbolTable) {
        this.parentSymbolTable = parentSymbolTable;
    }

    public boolean put(MiracleASTreeDeclaration declaration) {
        if (builtinType.containsKey(declaration.identifier) ||
                currentSymbolTable.containsKey(declaration.identifier)) {
            return false;
        }
        currentSymbolTable.put(declaration.identifier, declaration);
        return true;
    }

    public MiracleSymbol get(String name) {
        MiracleSymbol answer = currentSymbolTable.getOrDefault(name, null);
        if (answer != null) return answer;
        if (parentSymbolTable != null) {
            return parentSymbolTable.get(name);
        }
        if (builtinType.containsKey(name)) {
            return builtinType.get(name);
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
