grammar Miracle;

miracle: (classDeclarationStatement | functionDeclarationStatement | variableDeclarationStatement)*;

classDeclarationStatement: 'class' IDENTIFIER '{' (functionDeclarationStatement | variableDeclarationStatement | constructorDeclarationStatement)* '}' ';'*?;

functionDeclarationStatement: typename IDENTIFIER '(' (typename IDENTIFIER)?(',' typename IDENTIFIER)* ')' '{' statement* '}';

variableDeclarationStatement: typename IDENTIFIER ('=' expression)? ';';

constructorDeclarationStatement: typename '(' ')' '{' statement* '}';

blockStatement: '{' statement* '}';

statement: blockStatement
    | variableDeclarationStatement
    | selectionIfStatement
    | iterationStatement
    | controlStatement
    | expressionStatement
    | emptyStatement
    ;

selectionIfStatement: 'if' '(' expression ')' statement selectionElseStatement?;

selectionElseStatement: 'else' statement;

iterationStatement: 'for' '(' expression? ';' expression? ';' expression? ')' statement     #forStatement
    | 'while' '(' expression ')' statement                                                  #whileStatement
    ;

controlStatement: 'continue' ';'                                                            #continueStatement
    | 'break' ';'                                                                           #breakStatement
    | 'return' (expression)? ';'                                                            #returnStatement
    ;

expressionStatement: expression ';';

emptyStatement: ';';

typename: (BASETYPE | IDENTIFIER) ('[' ']')*;

/**
 * Here expression means every expression has a expression after processed,
 * particularly, the void expression.
 */
expression: constant                                                                        #constantExpression        /* Completed x 2 */
    | IDENTIFIER                                                                            #variableExpression        /* Completed x 2 */
    | '(' expression ')'                                                                    #braceExpression
    | expression '(' expression? (',' expression)* ')'                                      #functionCallExpression    /* Completed x 2*/
    | expression '[' expression ']'                                                         #subscriptExpression
    | expression '.' IDENTIFIER                                                             #memberExpression          /* Completed x 2*/
    | <assoc=right> expression operator=('++' | '--')                                       #suffixExpression          /* Completed x 2*/
    | <assoc=right> operator=('!' | '+' | '-' | '~' | '++' | '--') expression               #prefixExpression          /* Completed x 2*/
    | 'new' typename (('[' expression? ']')+ | '(' ')')?                                    #newExpression             /* Completed x 2*/
    | expression operator=('*' | '/' | '%') expression                                      #multDivExpression         /* Completed x 2*/
    | expression operator=('+' | '-') expression                                            #addSubExpression          /* Completed x 2*/
    | expression operator=('<<' | '>>') expression                                          #shlShrExpression          /* Completed x 2*/
    | expression operator=('<' | '<=' | '>' | '>=') expression                              #compareExpression         /* Completed x 2*/
    | expression operator=('==' | '!=') expression                                          #equalityExpression        /* Completed x 2*/
    | expression '&' expression                                                             #andExpression             /* Completed x 2*/
    | expression '^' expression                                                             #xorExpression             /* Completed x 2*/
    | expression '|' expression                                                             #orExpression              /* Completed x 2*/
    | expression '&&' expression                                                            #logicAndExpression        /* Completed x 2*/
    | expression '||' expression                                                            #logicOrExpression         /* Completed x 2*/
    | <assoc=right> expression '=' expression                                               #assignExpression          /* Completed x 2*/
    ;

constant: INTEGER
    | STRING
    | ('true' | 'false')
    | 'null'
    ;

BASETYPE: 'void' | 'int' | 'bool' | 'string';

DECORATOR: 'public' | 'private' | 'protected';

IDENTIFIER: [_a-zA-Z][_a-zA-Z0-9]*;

INTEGER: [0-9]+;

STRING: '"' (~["\\\r\n] | '\\' ['"nt\\])* '"';

NEXTLINE: '\\' [\n\r] -> skip;

LINECOMMENT: '//' ~[\r\n]* -> skip;

BLOCKCOMMENT: '/*' .*? '*/' -> skip;

WHITECHAR: [ \r\n\t]+ -> skip;
