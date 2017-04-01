package com.miracle.scanner.environment;

import com.miracle.astree.node.statement.declaration.MiracleASTreeVariableDeclaration;
import com.miracle.exceptions.MiracleExceptionConflictWithBuiltin;
import com.miracle.exceptions.MiracleExceptionConflictWithKeyword;
import com.miracle.exceptions.MiracleExceptionDuplicateDeclaration;
import com.miracle.exceptions.MiracleExceptionIdentifierShadow;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.HashMap;

public final class MiracleEnvironmentLoader extends MiracleEnvironmentManager {
    private static HashMap<Integer, HashMap<String, ImmutablePair<Boolean,
            MiracleASTreeVariableDeclaration>>> varMap = new HashMap<>();

    private static void checkDeclaration(int scope, String identifier, String value) {
        if (scopes.peek().getLeft().equals(ScopeType.SCOPE_GLOBAL) && BUILTIN.contains(identifier)) {
            throw new MiracleExceptionConflictWithBuiltin(identifier);
        }
        if (KEYWORD.contains(identifier)) {
            throw new MiracleExceptionConflictWithKeyword(identifier);
        }
        if (varMap.containsKey(scope) && varMap.get(scope).containsKey(identifier)) {
            if (varMap.get(scope).get(identifier).getLeft()) {
                throw new MiracleExceptionDuplicateDeclaration("variable", value, identifier);
            } else {
                throw new MiracleExceptionIdentifierShadow(identifier, value, "function paramter");
            }
        }
        if (funcMap.containsKey(scope) && funcMap.get(scope).containsKey(identifier)) {
            throw new MiracleExceptionDuplicateDeclaration("function", value, identifier);
        }
        if (classMap.containsKey(scope) && classMap.get(scope).containsKey(identifier)) {
            throw new MiracleExceptionDuplicateDeclaration("class", value, identifier);
        }
    }

    public static void declareVariable(String identifier) {
        int scope = scopes.peek().getRight();
        checkDeclaration(scope, identifier, "variable");
        if (!varMap.containsKey(scope)) {
            varMap.put(scope, new HashMap<>());
        }
        varMap.get(scope).put(identifier, ImmutablePair.of(false, null));
    }

    public static void declareClass(String identifier) {
        int scope = scopes.peek().getRight();
        checkDeclaration(scope, identifier, "class");
        if (!classMap.containsKey(scope)) {
            classMap.put(scope, new HashMap<>());
        }
        classMap.get(scope).put(identifier, ImmutablePair.of(false, null));
    }

    public static void declareFunction(String identifier) {
        int scope = scopes.peek().getRight();
        checkDeclaration(scope, identifier, "function");
        if (!funcMap.containsKey(scope)) {
            funcMap.put(scope, new HashMap<>());
        }
        funcMap.get(scope).put(identifier, ImmutablePair.of(false, null));
    }

    @Override
    public void initScope() {
        super.initScope();
    }
}
