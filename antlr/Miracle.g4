grammar Miracle;

miracle: (classDeclarationStatement | functionDeclarationStatement | variableDeclarationStatement)*;

classDeclarationStatement: 'class' IDENTIFIER ('extends' IDENTIFIER)? '{' (functionDeclarationStatement | variableDeclarationStatement)* '}';

functionDeclarationStatement: DECORATOR? typename IDENTIFIER '(' (typename IDENTIFIER)?(',' typename IDENTIFIER)* ')' '{' statement* '}';

variableDeclarationStatement: DECORATOR? typename IDENTIFIER ('=' expression)? ';';

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

typename: 'void'
    | 'int'
    | 'bool'
    | 'string'
    | IDENTIFIER
    | typename ('[]')+
    ;

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
    | 'new' typename ('[' expression ']')*('[]')*                                           #newExpression             /* Completed x 2*/
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

DECORATOR: 'public' | 'private' | 'protected';

IDENTIFIER: [_a-zA-Z][_a-zA-Z0-9]*;

INTEGER: [0-9]+;

STRING: '"' (~[\r\n])* '"';

NEXTLINE: '\\' [\n\r] -> skip;

LINECOMMENT: '//' ~[\r\n]* -> skip;

BLOCKCOMMENT: '/*' .*? '*/' -> skip;

WHITECHAR: [ \r\n\t]+ -> skip;
