grammar Miracle;

miracle: (classDeclarationStatement | functionDeclarationStatement | variableDeclarationStatement)*;

classDeclarationStatement: 'class' IDENTIFIER ('extends' IDENTIFIER)? (';' | blockStatement ';');

functionDeclarationStatement: DECORATOR? typename IDENTIFIER '(' (typename IDENTIFIER)?(',' typename IDENTIFIER)* ')' (';' | blockStatement);

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

iterationStatement: 'for' '(' expression ';' expression ';' expression ')' statement        #forStatement
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
    | <assoc=right> expression operator=('++' | '--')                                       #suffixExpression
    | operator=('++' | '--') expression                                                     #prefixExpression
    | <assoc=right> operator=('!' | '+' | '-' | '~' | '++' | '--')                          #unaryExpression
    | 'new' typename ('[' expression ']')*('[]')*                                           #newExpression
    | expression operator=('*' | '/' | '%') expression                                      #multDivExpression
    | expression operator=('+' | '-') expression                                            #addSubExpression
    | expression operator=('<<' | '>>') expression                                          #shlShrExpression
    | expression operator=('<' | '<=' | '>' | '>=') expression                              #compareExpression
    | expression operator=('==' | '!=') expression                                          #equalityExpression
    | expression '&' expression                                                             #andExpression
    | expression '^' expression                                                             #xorExpression
    | expression '|' expression                                                             #orExpression
    | expression '&&' expression                                                            #logicAndExpression
    | expression '||' expression                                                            #logicOrExpression
    | <assoc=right> expression '=' expression                                               #assignExpression
    ;

constant: INTEGER
    | STRING
    | ('true' | 'false')
    | 'null'
    ;

DECORATOR: 'public' | 'private' | 'protected';

IDENTIFIER: [a-zA-Z][a-zA-Z0-9]*;

INTEGER: [0-9]+;

STRING: '"' (~[\r\n])* '"';

NEXTLINE: '\\' [\n\r] -> skip;

LINECOMMENT: '//' ~[\r\n]* -> skip;

BLOCKCOMMENT: '/*' .*? '*/' -> skip;

WHITECHAR: [ \r\n\t]+ -> skip;