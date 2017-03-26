package com.miracle.scanner.environment.manager;

import com.miracle.exceptions.MiracleExceptionConflictWithBuiltin;
import com.miracle.exceptions.MiracleExceptionConflictWithKeyword;
import com.miracle.exceptions.MiracleExceptionDuplicateDeclaration;
import com.miracle.scanner.environment.identifier.*;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.HashMap;

public final class MiracleEnvironmentLoader extends MiracleEnvironmentManager {
    private static int classNumber;
    private static int varNumber;
    private static int funcNumber;

    private static void checkDeclaration(int scope, String identifier, MiracleIdentifier value) {
        if (BUILTIN.contains(identifier)) {
            throw new MiracleExceptionConflictWithBuiltin(identifier);
        }
        if (KEYWORD.contains(identifier)) {
            throw new MiracleExceptionConflictWithKeyword(identifier);
        }
        if (varMap.containsKey(scope) && varMap.get(scope).containsKey(identifier)) {
            throw new MiracleExceptionDuplicateDeclaration(
                    value.getIdentifierType(),
                    varMap.get(scope).get(identifier).getRight().getIdentifierType(),
                    identifier
            );
        }
        if (funcMap.containsKey(scope) && funcMap.get(scope).containsKey(identifier)) {
            throw new MiracleExceptionDuplicateDeclaration(
                    value.getIdentifierType(),
                    funcMap.get(scope).get(identifier).getRight().getIdentifierType(),
                    identifier
            );
        }
        if (classMap.containsKey(scope) && classMap.get(scope).containsKey(identifier)) {
            throw new MiracleExceptionDuplicateDeclaration(
                    value.getIdentifierType(),
                    classMap.get(scope).get(identifier).getRight().getIdentifierType(),
                    identifier
            );
        }
    }

    public static void declare(String identifier, MiracleIdentifierVariable value) {
        int scope = scopes.peek().getRight();
        checkDeclaration(scope, identifier, value);
        if (!varMap.containsKey(scope)) {
            varMap.put(scope, new HashMap<>());
        }
        varMap.get(scope).put(identifier, ImmutablePair.of(++varNumber, value));
    }

    public static void declare(String identifier, MiracleIdentifierClass value) {
        int scope = scopes.peek().getRight();
        checkDeclaration(scope, identifier, value);
        if (!classMap.containsKey(scope)) {
            classMap.put(scope, new HashMap<>());
        }
        classMap.get(scope).put(identifier, ImmutablePair.of(++classNumber, value));
    }

    public static void declare(String identifier, MiracleIdentifierFunction value) {
        int scope = scopes.peek().getRight();
        checkDeclaration(scope, identifier, value);
        if (!funcMap.containsKey(scope)) {
            funcMap.put(scope, new HashMap<>());
        }
        funcMap.get(scope).put(identifier, ImmutablePair.of(++funcNumber, value));
    }

    @Override
    public void initScope() {
        super.initScope();
        varNumber = funcNumber = classNumber = 0;
    }
}
