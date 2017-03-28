package com.miracle.scanner.environment.manager;

import com.miracle.astree.node.statement.declaration.*;
import com.miracle.exceptions.MiracleExceptionConflictWithBuiltin;
import com.miracle.exceptions.MiracleExceptionConflictWithKeyword;
import com.miracle.exceptions.MiracleExceptionDuplicateDeclaration;
import com.miracle.exceptions.MiracleExceptionIdentifierShadow;
import org.apache.commons.lang3.tuple.ImmutableTriple;

import java.util.HashMap;

public final class MiracleEnvironmentLoader extends MiracleEnvironmentManager {
    private static HashMap<Integer, HashMap<String, ImmutableTriple<Integer, Boolean,
            MiracleASTreeVariableDeclaration>>> varMap = new HashMap<>();
    private static int classNumber;
    private static int varNumber;
    private static int funcNumber;

    private static void checkDeclaration(int scope, String identifier, MiracleASTreeDeclaration value) {
        if (BUILTIN.contains(identifier)) {
            throw new MiracleExceptionConflictWithBuiltin(identifier);
        }
        if (KEYWORD.contains(identifier)) {
            throw new MiracleExceptionConflictWithKeyword(identifier);
        }
        if (varMap.containsKey(scope) && varMap.get(scope).containsKey(identifier)) {
            if (varMap.get(scope).get(identifier).getMiddle()) {
                if (value instanceof MiracleASTreeClassDeclaration) {
                    throw new MiracleExceptionDuplicateDeclaration(
                            "variable",
                            "class",
                            identifier
                    );
                } else {
                    throw new MiracleExceptionDuplicateDeclaration(
                            "variable",
                            ((MiracleASTreeMemberDeclaration) value).getType().toString(),
                            identifier
                    );
                }
            } else {
                throw new MiracleExceptionIdentifierShadow(
                        identifier,
                        value.getIdentifier(),
                        "function paramter"
                );
            }
        }
        if (funcMap.containsKey(scope) && funcMap.get(scope).containsKey(identifier)) {
            if (value instanceof MiracleASTreeClassDeclaration) {
                throw new MiracleExceptionDuplicateDeclaration(
                        "function",
                        "class",
                        identifier
                );
            } else {
                throw new MiracleExceptionDuplicateDeclaration(
                        "function",
                        ((MiracleASTreeMemberDeclaration) value).getType().toString(),
                        identifier
                );
            }
        }
        if (classMap.containsKey(scope) && classMap.get(scope).containsKey(identifier)) {
            if (value instanceof MiracleASTreeClassDeclaration) {
                throw new MiracleExceptionDuplicateDeclaration(
                        "class",
                        "class",
                        identifier
                );
            } else {
                throw new MiracleExceptionDuplicateDeclaration(
                        "class",
                        ((MiracleASTreeMemberDeclaration) value).getType().toString(),
                        identifier
                );
            }
        }
    }

    public static void declare(String identifier, boolean coverable, MiracleASTreeVariableDeclaration value) {
        int scope = scopes.peek().getRight();
        checkDeclaration(scope, identifier, value);
        if (!varMap.containsKey(scope)) {
            varMap.put(scope, new HashMap<>());
        }
        varMap.get(scope).put(identifier, ImmutableTriple.of(++varNumber, coverable, value));
    }

    public static void declare(String identifier, MiracleASTreeClassDeclaration value) {
        int scope = scopes.peek().getRight();
        checkDeclaration(scope, identifier, value);
        if (!classMap.containsKey(scope)) {
            classMap.put(scope, new HashMap<>());
        }
        classMap.get(scope).put(identifier, ImmutableTriple.of(++classNumber, false, value));
    }

    public static void declare(String identifier, MiracleASTreeFunctionDeclaration value) {
        int scope = scopes.peek().getRight();
        checkDeclaration(scope, identifier, value);
        if (!funcMap.containsKey(scope)) {
            funcMap.put(scope, new HashMap<>());
        }
        funcMap.get(scope).put(identifier, ImmutableTriple.of(++funcNumber, false, value));
    }

    @Override
    public void initScope() {
        super.initScope();
        varNumber = funcNumber = classNumber = 0;
    }
}
