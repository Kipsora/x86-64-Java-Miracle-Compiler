grammar Miracle;

miracle: (classDeclarationStatement | functionDeclarationStatement | variableDeclarationStatement)+;

classDeclarationStatement:
    'class' IDENTIFIER '{'
        (functionDeclarationStatement | variableDeclarationStatement)*
    '}';

functionDeclarationStatement: typename IDENTIFIER? '(' (typename IDENTIFIER)?(',' typename IDENTIFIER)* ')' '{' statement* '}';

variableDeclarationStatement: typename IDENTIFIER ('=' expression)? ';';

blockStatement: '{' statement* '}';

statement: blockStatement
    | variableDeclarationStatement
    | selectionStatement
    | iterationStatement
    | controlStatement
    | expressionStatement
    | emptyStatement
    ;

selectionStatement: 'if' '(' expression ')' statement ('else' statement)?;

iterationStatement: 'for' '(' expression? ';' expression? ';' expression? ')' statement     #forStatement
    | 'while' '(' expression ')' statement                                                  #whileStatement
    ;

controlStatement: 'continue' ';'                                                            #continueStatement
    | 'break' ';'                                                                           #breakStatement
    | 'return' (expression)? ';'                                                            #returnStatement
    ;

expressionStatement: expression ';';

emptyStatement: ';';

typename: basetype ('[' ']')*;

basetype
    : type=BUILTIN_INT
    | type=BUILTIN_BOOL
    | type=BUILTIN_VOID
    | type=BUILTIN_STRING
    | IDENTIFIER
    ;

/**
 * Here expression means every expression has a expression after processed,
 * particularly, the void expression.
 */
expression: constant                                                                        #constantExpression
    | 'this'                                                                                #thisExpression
    | IDENTIFIER                                                                            #variableExpression
    | '(' expression ')'                                                                    #braceExpression
    | expression '(' expression? (',' expression)* ')'                                      #functionCallExpression
    | expression '[' expression ']'                                                         #subscriptExpression
    | expression operator='.' IDENTIFIER                                                    #fieldExpression
    | <assoc=right> expression operator=('++' | '--')                                       #suffixExpression
    | <assoc=right> operator=('!' | '+' | '-' | '~' | '++' | '--') expression               #prefixExpression
    | 'new' typename (('[' expression? ']')+ | '(' ')')?                                    #newExpression
    | expression operator=('*' | '/' | '%') expression                                      #binaryExpression
    | expression operator=('+' | '-') expression                                            #binaryExpression
    | expression operator=('<<' | '>>') expression                                          #binaryExpression
    | expression operator=('<' | '<=' | '>' | '>=') expression                              #binaryExpression
    | expression operator=('==' | '!=') expression                                          #binaryExpression
    | expression operator='&' expression                                                    #binaryExpression
    | expression operator='^' expression                                                    #binaryExpression
    | expression operator='|' expression                                                    #binaryExpression
    | expression operator='&&' expression                                                   #binaryExpression
    | expression operator='||' expression                                                   #binaryExpression
    | <assoc=right> expression operator='=' expression                                      #binaryExpression
    ;

constant: INTEGER                                                                           #integerConstant
    | STRING                                                                                #stringConstant
    | ('true' | 'false')                                                                    #boolConstant
    | 'null'                                                                                #nullConstant
    ;

BUILTIN_INT: 'int';
BUILTIN_BOOL: 'bool';
BUILTIN_STRING: 'string';
BUILTIN_VOID: 'void';

DECORATOR: 'public' | 'private' | 'protected';

IDENTIFIER: [_a-zA-Z][_a-zA-Z0-9]*;

INTEGER: [0-9]+;

STRING: '"' (~["\\\r\n] | '\\' ['"nt\\])* '"';

NEXTLINE: '\\' [\n\r] -> skip;

LINECOMMENT: '//' ~[\r\n]* -> skip;

BLOCKCOMMENT: '/*' .*? '*/' -> skip;

WHITECHAR: [ \r\n\t]+ -> skip;
