package com.miracle.scanner;

import com.miracle.astree.node.statement.declaration.*;
import com.miracle.exceptions.MiracleExceptionConflictWithKeyword;
import com.miracle.exceptions.MiracleExceptionDuplicateDeclaration;
import com.miracle.exceptions.MiracleExceptionUndefinedIdentifier;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.ImmutableTriple;

import java.util.*;

public class MiracleEnvironmentManager {
    private static final HashSet<String> MiracleKEYWORD = new HashSet<String>() {{
        add("bool");
        add("int");
        add("string");
        add("null");
        add("void");
        add("true");
        add("false");
        add("if");
        add("for");
        add("while");
        add("break");
        add("continue");
        add("return");
        add("new");
        add("class");
        add("this");
    }};
    private static int scopeNumber = 0;
    private static List<HashMap<String, MiracleASTreeClassDeclaration>> mapclass = new ArrayList<>();
    private static List<HashMap<String, MiracleASTreeFunctionDeclaration>> mapfunc = new ArrayList<>();
    private static List<HashMap<String, MiracleASTreeVariableDeclaration>> mapvari = new ArrayList<>();

    private static HashMap<String, ImmutablePair<Integer, MiracleASTreeMemberDeclaration>> declared = new HashMap<>();
    private static Stack<ImmutableTriple<Integer, String, MiracleASTreeMemberDeclaration>> recover = new Stack<>();

    private static HashMap<String, ImmutablePair<Integer, MiracleASTreeClassDeclaration>> declaredClass = new HashMap<>();
    private static Stack<ImmutableTriple<Integer, String, MiracleASTreeClassDeclaration>> recclass = new Stack<>();

    private static Stack<ImmutableTriple<Integer, Boolean, Boolean>> scope = new Stack<>();

    private static MiracleEnvironmentManager instance = new MiracleEnvironmentManager();

    private MiracleEnvironmentManager() {
    }

    private static void checkDeclaration(String identifier, String type) {
        if (MiracleKEYWORD.contains(identifier)) {
            throw new MiracleExceptionConflictWithKeyword(identifier);
        }
        if (mapclass.get(scope.peek().getLeft()).containsKey(identifier)) {
            throw new MiracleExceptionDuplicateDeclaration("class", type, identifier);
        }
        if (mapfunc.get(scope.peek().getLeft()).containsKey(identifier)) {
            throw new MiracleExceptionDuplicateDeclaration("function", type, identifier);
        }
        if (scope.peek().getRight()) {
            if (mapvari.get(scope.peek().getLeft()).containsKey(identifier)) {
                throw new MiracleExceptionDuplicateDeclaration("variable", type, identifier);
            }
        } else {
            if (declared.containsKey(identifier)
                    && declared.get(identifier).getRight().getDeclarationType().equals(MiracleASTreeDeclaration.DECTYPE.DEC_VAR)
                    && declared.get(identifier).getLeft().equals(scope.peek().getLeft())) {
                throw new MiracleExceptionDuplicateDeclaration("variable", type, identifier);
            }
        }
    }

    public static void init() {
        scopeNumber = 0;
    }

    public static boolean inMemberScope() {
        return scope.peek().getMiddle();
    }

    public static void newscope(boolean member) {
        if (!scope.empty()) {
            scope.push(ImmutableTriple.of(scopeNumber++, scope.peek().getMiddle() | member, member));
        } else {
            scope.push(ImmutableTriple.of(scopeNumber++, member, member));
        }
        if (mapclass.size() < scopeNumber) {
            mapclass.add(new HashMap<>());
        }
        mapclass.get(scope.peek().getLeft()).forEach((key, value) -> {
            if (declaredClass.containsKey(key)) {
                recclass.push(ImmutableTriple.of(scope.peek().getLeft(), key, declaredClass.get(key).getRight()));
            } else {
                recclass.push(ImmutableTriple.of(scope.peek().getLeft(), key, null));
            }
            declaredClass.put(key, ImmutablePair.of(scope.peek().getLeft(), value));
        });
        if (mapfunc.size() < scopeNumber) {
            mapfunc.add(new HashMap<>());
        }
        mapfunc.get(scope.peek().getLeft()).forEach((key, value) -> {
            if (declared.containsKey(key)) {
                recover.push(ImmutableTriple.of(scope.peek().getLeft(), key, declared.get(key).getRight()));
            } else {
                recover.push(ImmutableTriple.of(scope.peek().getLeft(), key, null));
            }
            declared.put(key, ImmutablePair.of(scope.peek().getLeft(), value));
        });
        if (mapvari.size() < scopeNumber) {
            mapvari.add(new HashMap<>());
        }
        if (scope.peek().getRight()) {
            mapvari.get(scope.peek().getLeft()).forEach((key, value) -> {
                if (declared.containsKey(key)) {
                    recover.push(ImmutableTriple.of(scope.peek().getLeft(), key, declared.get(key).getRight()));
                } else {
                    recover.push(ImmutableTriple.of(scope.peek().getLeft(), key, null));
                }
                declared.put(key, ImmutablePair.of(scope.peek().getLeft(), value));
            });
        }
    }

    public static void exitscope() {
        while (!recover.empty() && recover.peek().getLeft().equals(scope.peek().getLeft())) {
            if (recover.peek().getRight() == null) {
                declared.remove(recover.peek().getMiddle());
            } else {
                declared.put(recover.peek().getMiddle(), ImmutablePair.of(recover.peek().getLeft(), recover.peek().getRight()));
            }
            recover.pop();
        }
        scope.pop();
    }

    public static void declareClass(String identifier) {
        checkDeclaration(identifier, "class");
        MiracleASTreeClassDeclaration node = new MiracleASTreeClassDeclaration(identifier);
        mapclass.get(scope.peek().getLeft()).put(identifier, node);
        if (declaredClass.containsKey(identifier)) {
            recclass.push(ImmutableTriple.of(scope.peek().getLeft(), identifier, declaredClass.get(identifier).getRight()));
        } else {
            recclass.push(ImmutableTriple.of(scope.peek().getLeft(), identifier, null));
        }
        declaredClass.put(identifier, ImmutablePair.of(scope.peek().getLeft(), node));
    }

    public static void declareFunction(String identifier, MiracleASTreeTypename returnType,
                                       List<MiracleASTreeVariableDeclaration> arguments) {
        checkDeclaration(identifier, "function");
        MiracleASTreeFunctionDeclaration node = new MiracleASTreeFunctionDeclaration(returnType, identifier, arguments);
        mapfunc.get(scope.peek().getLeft()).put(identifier, node);
        if (declared.containsKey(identifier)) {
            recover.push(ImmutableTriple.of(scope.peek().getLeft(), identifier, declared.get(identifier).getRight()));
        } else {
            recover.push(ImmutableTriple.of(scope.peek().getLeft(), identifier, null));
        }
        declared.put(identifier, ImmutablePair.of(scope.peek().getLeft(), node));
    }

    public static void declareVariable(String identifier, MiracleASTreeTypename type) {
        checkDeclaration(identifier, "variable");
        MiracleASTreeVariableDeclaration node = new MiracleASTreeVariableDeclaration(identifier, type);
        mapvari.get(scope.peek().getLeft()).put(identifier, node);
        if (declared.containsKey(identifier)) {
            recover.push(ImmutableTriple.of(scope.peek().getLeft(), identifier, declared.get(identifier).getRight()));
        } else {
            recover.push(ImmutableTriple.of(scope.peek().getLeft(), identifier, null));
        }
        declared.put(identifier, ImmutablePair.of(scope.peek().getLeft(), node));
    }

    public static void fetchVariable(String identifier) {
        if (!mapvari.get(scope.peek().getLeft()).containsKey(identifier)) {
            throw new MiracleExceptionUndefinedIdentifier(identifier);
        }
        if (!scope.peek().getRight()) {
            if (declared.containsKey(identifier)) {
                recover.push(ImmutableTriple.of(scope.peek().getLeft(), identifier, declared.get(identifier).getRight()));
            } else {
                recover.push(ImmutableTriple.of(scope.peek().getLeft(), identifier, null));
            }
            declared.put(identifier, ImmutablePair.of(scope.peek().getLeft(),
                    mapvari.get(scope.peek().getLeft()).get(identifier)));
        }
    }

    public static MiracleASTreeClassDeclaration getClass(String identifier) {
        if (!declaredClass.containsKey(identifier)) {
            throw new MiracleExceptionUndefinedIdentifier(identifier);
        }
        return declaredClass.get(identifier).getRight();
    }

    public static MiracleASTreeFunctionDeclaration getFunction(String identifier) {
        if (!declared.containsKey(identifier)
                || !declared.get(identifier).getRight().getDeclarationType().equals(MiracleASTreeDeclaration.DECTYPE.DEC_FUNC)) {
            throw new MiracleExceptionUndefinedIdentifier(identifier);
        }
        return (MiracleASTreeFunctionDeclaration) declared.get(identifier).getRight();
    }

    public static MiracleASTreeVariableDeclaration getVariable(String identifier) {
        if (!declared.containsKey(identifier)
                || !declared.get(identifier).getRight().getDeclarationType().equals(MiracleASTreeDeclaration.DECTYPE.DEC_VAR)) {
            throw new MiracleExceptionUndefinedIdentifier(identifier);
        }
        return (MiracleASTreeVariableDeclaration) declared.get(identifier).getRight();
    }

    public static boolean containVariable(String identifier) {
        return declared.containsKey(identifier)
                && declared.get(identifier).getRight().getDeclarationType().equals(MiracleASTreeDeclaration.DECTYPE.DEC_VAR);
    }

    public static boolean containClass(String identifier) {
        return declaredClass.containsKey(identifier);
    }

    public static boolean containFunction(String identifier) {
        return declared.containsKey(identifier)
                && declared.get(identifier).getRight().getDeclarationType().equals(MiracleASTreeDeclaration.DECTYPE.DEC_FUNC);
    }

    public static boolean contain(String identifier) {
        return declared.containsKey(identifier);
    }

    public static MiracleASTreeDeclaration getDeclaration(String identifier) {
        return declared.get(identifier).getRight();
    }
}
