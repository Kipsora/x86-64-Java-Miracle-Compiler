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
 * Here expression means every expression has a value after processed,
 * particularly, the void value.
 */
expression: constant                                                                        #constantExpression
    | IDENTIFIER                                                                            #variableExpression
    | '(' expression ')'                                                                    #braceExpression
    | expression '(' expression? (',' expression)* ')'                                      #functionCallExpression
    | expression '[' expression ']'                                                         #subscriptExpression
    | expression '.' IDENTIFIER                                                             #memberExpression
    | <assoc=right> expression operator=('++' | '--')                                       #suffixExpression          /* Completed */
    | <assoc=right> operator=('!' | '+' | '-' | '~' | '++' | '--') expression               #prefixExpression          /* Completed */
    | 'new' typename ('[' expression ']')*('[]')*                                           #newExpression             /* Completed */
    | expression operator=('*' | '/' | '%') expression                                      #multDivExpression         /* Completed */
    | expression operator=('+' | '-') expression                                            #addSubExpression          /* Completed */
    | expression operator=('<<' | '>>') expression                                          #shlShrExpression          /* Completed */
    | expression operator=('<' | '<=' | '>' | '>=') expression                              #compareExpression         /* Completed */
    | expression operator=('==' | '!=') expression                                          #equalityExpression        /* Completed */
    | expression '&' expression                                                             #andExpression             /* Completed */
    | expression '^' expression                                                             #xorExpression             /* Completed */
    | expression '|' expression                                                             #orExpression              /* Completed */
    | expression '&&' expression                                                            #logicAndExpression        /* Completed */
    | expression '||' expression                                                            #logicOrExpression         /* Completed */
    | <assoc=right> expression '=' expression                                               #assignExpression          /* Completed */
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
