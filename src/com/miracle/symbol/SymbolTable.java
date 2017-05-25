package com.miracle.symbol;

import com.miracle.astree.statement.declaration.ClassDeclaration;
import com.miracle.astree.statement.declaration.Declaration;
import com.miracle.intermediate.RegisterBuffer;

import java.util.HashMap;
import java.util.Map;

import static com.miracle.intermediate.number.PhysicalRegister.*;
import static com.miracle.symbol.SymbolPrimitiveType.Types.*;

public class SymbolTable {
    public final static SymbolNullType __builtin_null = SymbolNullType.INSTANCE;
    public final static SymbolPrimitiveType __builtin_void = new SymbolPrimitiveType(VOID);
    public final static SymbolPrimitiveType __builtin_int = new SymbolPrimitiveType(INT);
    public final static SymbolPrimitiveType __builtin_bool = new SymbolPrimitiveType(BOOL);
    public final static SymbolPrimitiveType __builtin_string = new SymbolPrimitiveType(STRING);
    public final static SymbolFunctionType __builtin_strcat = new SymbolFunctionType(__builtin_string, null) {{
        addParameter("x", __builtin_string);
        addParameter("y", __builtin_string);
        setAddress("@strcat");
        getAddress().setBuffer(new RegisterBuffer() {{
            enroll(EAX);
            enroll(EDI);
            enroll(ESI);
        }});
    }};
    public final static SymbolFunctionType __builtin_strcmp = new SymbolFunctionType(__builtin_int, null) {{
        addParameter("x", __builtin_string);
        addParameter("y", __builtin_string);
        setAddress("@strcmp");
        getAddress().setBuffer(new RegisterBuffer() {{
            enroll(EAX);
            enroll(EDI);
            enroll(ESI);
        }});
    }};
    private final static SymbolFunctionType __builtin_print = new SymbolFunctionType(__builtin_void, null) {{
        addParameter("x", __builtin_string);
        setAddress("@print");
        getAddress().setBuffer(new RegisterBuffer() {{
            enroll(EAX);
            enroll(EDI);
        }});
    }};
    private final static SymbolFunctionType __builtin_println = new SymbolFunctionType(__builtin_void, null) {{
        addParameter("x", __builtin_string);
        setAddress("@println");
        getAddress().setBuffer(new RegisterBuffer() {{
            enroll(EAX);
            enroll(EDI);
        }});
    }};
    private final static SymbolFunctionType __builtin_getString = new SymbolFunctionType(__builtin_string, null) {{
        setAddress("@getString");
        getAddress().setBuffer(new RegisterBuffer() {{
            enroll(EAX);
        }});
    }};
    private final static SymbolFunctionType __builtin_getInt = new SymbolFunctionType(__builtin_int, null) {{
        setAddress("@getInt");
        getAddress().setBuffer(new RegisterBuffer() {{
            enroll(EAX);
        }});
    }};
    private final static SymbolFunctionType __builtin_toString = new SymbolFunctionType(__builtin_string, null) {{
        addParameter("x", __builtin_int);
        setAddress("@toString");
        getAddress().setBuffer(new RegisterBuffer() {{
            enroll(EAX);
            enroll(EDI);
        }});
    }};
    private final static Map<String, SymbolFunctionType> builtinMethod = new HashMap<String, SymbolFunctionType>() {{
        put("print", __builtin_print);
        put("println", __builtin_println);
        put("getString", __builtin_getString);
        put("getInt", __builtin_getInt);
        put("toString", __builtin_toString);
    }};
    private final static Map<String, SymbolPrimitiveType> builtinType = new HashMap<String, SymbolPrimitiveType>() {{
        put("bool", __builtin_bool);
        put("int", __builtin_int);
        put("string", __builtin_string);
        put("void", __builtin_void);
    }};

    static {
        __builtin_string.addMethod("length", new SymbolFunctionType(__builtin_int, __builtin_string) {{
            setAddress("@string.length");
            getAddress().setBuffer(new RegisterBuffer() {{
                enroll(EAX);
                enroll(EDI);
            }});
        }});
        __builtin_string.addMethod("substring", new SymbolFunctionType(__builtin_string, __builtin_string) {{
            addParameter("x", __builtin_int);
            addParameter("y", __builtin_int);
            setAddress("@string.substring");
            getAddress().setBuffer(new RegisterBuffer() {{
                enroll(EAX);
                enroll(EDI);
                enroll(ESI);
                enroll(EDX);
            }});
        }});
        __builtin_string.addMethod("parseInt", new SymbolFunctionType(__builtin_int, __builtin_string) {{
            setAddress("@string.parseInt");
            getAddress().setBuffer(new RegisterBuffer() {{
                enroll(EAX);
                enroll(EDI);
            }});
        }});
        __builtin_string.addMethod("ord", new SymbolFunctionType(__builtin_int, __builtin_string) {{
            addParameter("x", __builtin_int);
            setAddress("@string.ord");
            getAddress().setBuffer(new RegisterBuffer() {{
                enroll(EAX);
                enroll(EDI);
                enroll(ESI);
            }});
        }});
    }

    private final SymbolTable parentSymbolTable;
    private final Map<String, Declaration> currentSymbolTable = new HashMap<>();

    public SymbolTable(SymbolTable parentSymbolTable) {
        this.parentSymbolTable = parentSymbolTable;
    }

    public boolean put(Declaration declaration) {
        if (builtinType.containsKey(declaration.identifier) ||
                currentSymbolTable.containsKey(declaration.identifier)) {
            return false;
        }
        currentSymbolTable.put(declaration.identifier, declaration);
        return true;
    }

    public Symbol resolve(String name) {
        Symbol answer = currentSymbolTable.getOrDefault(name, null);
        if (answer != null) return answer;
        if (parentSymbolTable != null) {
            return parentSymbolTable.resolve(name);
        }
        if (builtinType.containsKey(name)) {
            return builtinType.get(name);
        }
        if (builtinMethod.containsKey(name)) {
            return builtinMethod.get(name);
        }
        return null;
    }

    public SymbolTable getParentSymbolTable() {
        return parentSymbolTable;
    }

    public ClassDeclaration resolveClass(String name) {
        Symbol answer = currentSymbolTable.getOrDefault(name, null);
        if (answer instanceof ClassDeclaration) {
            return (ClassDeclaration) answer;
        }
        if (parentSymbolTable != null) {
            return parentSymbolTable.resolveClass(name);
        }
        return null;
    }
}