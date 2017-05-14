package com.miracle.cstree.parser;// Generated from Miracle.g4 by ANTLR 4.6

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MiracleParser extends Parser {
    public static final int
            T__0 = 1, T__1 = 2, T__2 = 3, T__3 = 4, T__4 = 5, T__5 = 6, T__6 = 7, T__7 = 8, T__8 = 9,
            T__9 = 10, T__10 = 11, T__11 = 12, T__12 = 13, T__13 = 14, T__14 = 15, T__15 = 16, T__16 = 17,
            T__17 = 18, T__18 = 19, T__19 = 20, T__20 = 21, T__21 = 22, T__22 = 23, T__23 = 24,
            T__24 = 25, T__25 = 26, T__26 = 27, T__27 = 28, T__28 = 29, T__29 = 30, T__30 = 31,
            T__31 = 32, T__32 = 33, T__33 = 34, T__34 = 35, T__35 = 36, T__36 = 37, T__37 = 38,
            T__38 = 39, T__39 = 40, T__40 = 41, T__41 = 42, T__42 = 43, T__43 = 44, T__44 = 45,
            BASETYPE = 46, DECORATOR = 47, IDENTIFIER = 48, INTEGER = 49, STRING = 50, NEXTLINE = 51,
            LINECOMMENT = 52, BLOCKCOMMENT = 53, WHITECHAR = 54;
    public static final int
            RULE_miracle = 0, RULE_classDeclarationStatement = 1, RULE_functionDeclarationStatement = 2,
            RULE_variableDeclarationStatement = 3, RULE_constructorDeclarationStatement = 4,
            RULE_blockStatement = 5, RULE_statement = 6, RULE_selectionStatement = 7,
            RULE_iterationStatement = 8, RULE_controlStatement = 9, RULE_expressionStatement = 10,
            RULE_emptyStatement = 11, RULE_typename = 12, RULE_expression = 13, RULE_constant = 14;
    public static final String[] ruleNames = {
            "miracle", "classDeclarationStatement", "functionDeclarationStatement",
            "variableDeclarationStatement", "constructorDeclarationStatement", "blockStatement",
            "statement", "selectionStatement", "iterationStatement", "controlStatement",
            "expressionStatement", "emptyStatement", "typename", "expression", "constant"
    };
    /**
     * @deprecated Use {@link #VOCABULARY} instead.
     */
    @Deprecated
    public static final String[] tokenNames;
    public static final String _serializedATN =
            "\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\38\u0116\4\2\t\2\4" +
                    "\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t" +
                    "\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\3\2\3\2\3\2\7\2$\n\2" +
                    "\f\2\16\2\'\13\2\3\3\3\3\3\3\3\3\3\3\7\3.\n\3\f\3\16\3\61\13\3\3\3\5\3" +
                    "\64\n\3\3\3\3\3\7\38\n\3\f\3\16\3;\13\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3" +
                    "\4\5\4E\n\4\3\4\3\4\3\4\3\4\7\4K\n\4\f\4\16\4N\13\4\3\4\3\4\3\4\7\4S\n" +
                    "\4\f\4\16\4V\13\4\3\4\3\4\3\5\3\5\3\5\3\5\5\5^\n\5\3\5\3\5\3\6\3\6\3\6" +
                    "\3\6\3\6\7\6g\n\6\f\6\16\6j\13\6\3\6\3\6\3\7\3\7\7\7p\n\7\f\7\16\7s\13" +
                    "\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b~\n\b\3\t\3\t\3\t\3\t\3\t\3" +
                    "\t\3\t\5\t\u0087\n\t\3\n\3\n\3\n\5\n\u008c\n\n\3\n\3\n\5\n\u0090\n\n\3" +
                    "\n\3\n\5\n\u0094\n\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u009e\n\n\3\13" +
                    "\3\13\3\13\3\13\3\13\3\13\5\13\u00a6\n\13\3\13\5\13\u00a9\n\13\3\f\3\f" +
                    "\3\f\3\r\3\r\3\16\3\16\3\16\7\16\u00b3\n\16\f\16\16\16\u00b6\13\16\3\17" +
                    "\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\5\17" +
                    "\u00c6\n\17\3\17\6\17\u00c9\n\17\r\17\16\17\u00ca\3\17\3\17\5\17\u00cf" +
                    "\n\17\5\17\u00d1\n\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17" +
                    "\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17" +
                    "\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u00f7" +
                    "\n\17\3\17\3\17\7\17\u00fb\n\17\f\17\16\17\u00fe\13\17\3\17\3\17\3\17" +
                    "\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\7\17\u010b\n\17\f\17\16\17\u010e" +
                    "\13\17\3\20\3\20\3\20\3\20\5\20\u0114\n\20\3\20\2\3\34\21\2\4\6\b\n\f" +
                    "\16\20\22\24\26\30\32\34\36\2\13\4\2\60\60\62\62\3\2\26\33\3\2\35\37\3" +
                    "\2\31\32\3\2 !\3\2\"%\3\2&\'\3\2\26\27\3\2-.\u0140\2%\3\2\2\2\4(\3\2\2" +
                    "\2\6>\3\2\2\2\bY\3\2\2\2\na\3\2\2\2\fm\3\2\2\2\16}\3\2\2\2\20\177\3\2" +
                    "\2\2\22\u009d\3\2\2\2\24\u00a8\3\2\2\2\26\u00aa\3\2\2\2\30\u00ad\3\2\2" +
                    "\2\32\u00af\3\2\2\2\34\u00d0\3\2\2\2\36\u0113\3\2\2\2 $\5\4\3\2!$\5\6" +
                    "\4\2\"$\5\b\5\2# \3\2\2\2#!\3\2\2\2#\"\3\2\2\2$\'\3\2\2\2%#\3\2\2\2%&" +
                    "\3\2\2\2&\3\3\2\2\2\'%\3\2\2\2()\7\3\2\2)*\7\62\2\2*/\7\4\2\2+.\5\6\4" +
                    "\2,.\5\b\5\2-+\3\2\2\2-,\3\2\2\2.\61\3\2\2\2/-\3\2\2\2/\60\3\2\2\2\60" +
                    "\63\3\2\2\2\61/\3\2\2\2\62\64\5\n\6\2\63\62\3\2\2\2\63\64\3\2\2\2\649" +
                    "\3\2\2\2\658\5\6\4\2\668\5\b\5\2\67\65\3\2\2\2\67\66\3\2\2\28;\3\2\2\2" +
                    "9\67\3\2\2\29:\3\2\2\2:<\3\2\2\2;9\3\2\2\2<=\7\5\2\2=\5\3\2\2\2>?\5\32" +
                    "\16\2?@\7\62\2\2@D\7\6\2\2AB\5\32\16\2BC\7\62\2\2CE\3\2\2\2DA\3\2\2\2" +
                    "DE\3\2\2\2EL\3\2\2\2FG\7\7\2\2GH\5\32\16\2HI\7\62\2\2IK\3\2\2\2JF\3\2" +
                    "\2\2KN\3\2\2\2LJ\3\2\2\2LM\3\2\2\2MO\3\2\2\2NL\3\2\2\2OP\7\b\2\2PT\7\4" +
                    "\2\2QS\5\16\b\2RQ\3\2\2\2SV\3\2\2\2TR\3\2\2\2TU\3\2\2\2UW\3\2\2\2VT\3" +
                    "\2\2\2WX\7\5\2\2X\7\3\2\2\2YZ\5\32\16\2Z]\7\62\2\2[\\\7\t\2\2\\^\5\34" +
                    "\17\2][\3\2\2\2]^\3\2\2\2^_\3\2\2\2_`\7\n\2\2`\t\3\2\2\2ab\5\32\16\2b" +
                    "c\7\6\2\2cd\7\b\2\2dh\7\4\2\2eg\5\16\b\2fe\3\2\2\2gj\3\2\2\2hf\3\2\2\2" +
                    "hi\3\2\2\2ik\3\2\2\2jh\3\2\2\2kl\7\5\2\2l\13\3\2\2\2mq\7\4\2\2np\5\16" +
                    "\b\2on\3\2\2\2ps\3\2\2\2qo\3\2\2\2qr\3\2\2\2rt\3\2\2\2sq\3\2\2\2tu\7\5" +
                    "\2\2u\r\3\2\2\2v~\5\f\7\2w~\5\b\5\2x~\5\20\t\2y~\5\22\n\2z~\5\24\13\2" +
                    "{~\5\26\f\2|~\5\30\r\2}v\3\2\2\2}w\3\2\2\2}x\3\2\2\2}y\3\2\2\2}z\3\2\2" +
                    "\2}{\3\2\2\2}|\3\2\2\2~\17\3\2\2\2\177\u0080\7\13\2\2\u0080\u0081\7\6" +
                    "\2\2\u0081\u0082\5\34\17\2\u0082\u0083\7\b\2\2\u0083\u0086\5\16\b\2\u0084" +
                    "\u0085\7\f\2\2\u0085\u0087\5\16\b\2\u0086\u0084\3\2\2\2\u0086\u0087\3" +
                    "\2\2\2\u0087\21\3\2\2\2\u0088\u0089\7\r\2\2\u0089\u008b\7\6\2\2\u008a" +
                    "\u008c\5\34\17\2\u008b\u008a\3\2\2\2\u008b\u008c\3\2\2\2\u008c\u008d\3" +
                    "\2\2\2\u008d\u008f\7\n\2\2\u008e\u0090\5\34\17\2\u008f\u008e\3\2\2\2\u008f" +
                    "\u0090\3\2\2\2\u0090\u0091\3\2\2\2\u0091\u0093\7\n\2\2\u0092\u0094\5\34" +
                    "\17\2\u0093\u0092\3\2\2\2\u0093\u0094\3\2\2\2\u0094\u0095\3\2\2\2\u0095" +
                    "\u0096\7\b\2\2\u0096\u009e\5\16\b\2\u0097\u0098\7\16\2\2\u0098\u0099\7" +
                    "\6\2\2\u0099\u009a\5\34\17\2\u009a\u009b\7\b\2\2\u009b\u009c\5\16\b\2" +
                    "\u009c\u009e\3\2\2\2\u009d\u0088\3\2\2\2\u009d\u0097\3\2\2\2\u009e\23" +
                    "\3\2\2\2\u009f\u00a0\7\17\2\2\u00a0\u00a9\7\n\2\2\u00a1\u00a2\7\20\2\2" +
                    "\u00a2\u00a9\7\n\2\2\u00a3\u00a5\7\21\2\2\u00a4\u00a6\5\34\17\2\u00a5" +
                    "\u00a4\3\2\2\2\u00a5\u00a6\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7\u00a9\7\n" +
                    "\2\2\u00a8\u009f\3\2\2\2\u00a8\u00a1\3\2\2\2\u00a8\u00a3\3\2\2\2\u00a9" +
                    "\25\3\2\2\2\u00aa\u00ab\5\34\17\2\u00ab\u00ac\7\n\2\2\u00ac\27\3\2\2\2" +
                    "\u00ad\u00ae\7\n\2\2\u00ae\31\3\2\2\2\u00af\u00b4\t\2\2\2\u00b0\u00b1" +
                    "\7\22\2\2\u00b1\u00b3\7\23\2\2\u00b2\u00b0\3\2\2\2\u00b3\u00b6\3\2\2\2" +
                    "\u00b4\u00b2\3\2\2\2\u00b4\u00b5\3\2\2\2\u00b5\33\3\2\2\2\u00b6\u00b4" +
                    "\3\2\2\2\u00b7\u00b8\b\17\1\2\u00b8\u00d1\5\36\20\2\u00b9\u00d1\7\24\2" +
                    "\2\u00ba\u00d1\7\62\2\2\u00bb\u00bc\7\6\2\2\u00bc\u00bd\5\34\17\2\u00bd" +
                    "\u00be\7\b\2\2\u00be\u00d1\3\2\2\2\u00bf\u00c0\t\3\2\2\u00c0\u00d1\5\34" +
                    "\17\17\u00c1\u00c2\7\34\2\2\u00c2\u00ce\5\32\16\2\u00c3\u00c5\7\22\2\2" +
                    "\u00c4\u00c6\5\34\17\2\u00c5\u00c4\3\2\2\2\u00c5\u00c6\3\2\2\2\u00c6\u00c7" +
                    "\3\2\2\2\u00c7\u00c9\7\23\2\2\u00c8\u00c3\3\2\2\2\u00c9\u00ca\3\2\2\2" +
                    "\u00ca\u00c8\3\2\2\2\u00ca\u00cb\3\2\2\2\u00cb\u00cf\3\2\2\2\u00cc\u00cd" +
                    "\7\6\2\2\u00cd\u00cf\7\b\2\2\u00ce\u00c8\3\2\2\2\u00ce\u00cc\3\2\2\2\u00ce" +
                    "\u00cf\3\2\2\2\u00cf\u00d1\3\2\2\2\u00d0\u00b7\3\2\2\2\u00d0\u00b9\3\2" +
                    "\2\2\u00d0\u00ba\3\2\2\2\u00d0\u00bb\3\2\2\2\u00d0\u00bf\3\2\2\2\u00d0" +
                    "\u00c1\3\2\2\2\u00d1\u010c\3\2\2\2\u00d2\u00d3\f\r\2\2\u00d3\u00d4\t\4" +
                    "\2\2\u00d4\u010b\5\34\17\16\u00d5\u00d6\f\f\2\2\u00d6\u00d7\t\5\2\2\u00d7" +
                    "\u010b\5\34\17\r\u00d8\u00d9\f\13\2\2\u00d9\u00da\t\6\2\2\u00da\u010b" +
                    "\5\34\17\f\u00db\u00dc\f\n\2\2\u00dc\u00dd\t\7\2\2\u00dd\u010b\5\34\17" +
                    "\13\u00de\u00df\f\t\2\2\u00df\u00e0\t\b\2\2\u00e0\u010b\5\34\17\n\u00e1" +
                    "\u00e2\f\b\2\2\u00e2\u00e3\7(\2\2\u00e3\u010b\5\34\17\t\u00e4\u00e5\f" +
                    "\7\2\2\u00e5\u00e6\7)\2\2\u00e6\u010b\5\34\17\b\u00e7\u00e8\f\6\2\2\u00e8" +
                    "\u00e9\7*\2\2\u00e9\u010b\5\34\17\7\u00ea\u00eb\f\5\2\2\u00eb\u00ec\7" +
                    "+\2\2\u00ec\u010b\5\34\17\6\u00ed\u00ee\f\4\2\2\u00ee\u00ef\7,\2\2\u00ef" +
                    "\u010b\5\34\17\5\u00f0\u00f1\f\3\2\2\u00f1\u00f2\7\t\2\2\u00f2\u010b\5" +
                    "\34\17\3\u00f3\u00f4\f\23\2\2\u00f4\u00f6\7\6\2\2\u00f5\u00f7\5\34\17" +
                    "\2\u00f6\u00f5\3\2\2\2\u00f6\u00f7\3\2\2\2\u00f7\u00fc\3\2\2\2\u00f8\u00f9" +
                    "\7\7\2\2\u00f9\u00fb\5\34\17\2\u00fa\u00f8\3\2\2\2\u00fb\u00fe\3\2\2\2" +
                    "\u00fc\u00fa\3\2\2\2\u00fc\u00fd\3\2\2\2\u00fd\u00ff\3\2\2\2\u00fe\u00fc" +
                    "\3\2\2\2\u00ff\u010b\7\b\2\2\u0100\u0101\f\22\2\2\u0101\u0102\7\22\2\2" +
                    "\u0102\u0103\5\34\17\2\u0103\u0104\7\23\2\2\u0104\u010b\3\2\2\2\u0105" +
                    "\u0106\f\21\2\2\u0106\u0107\7\25\2\2\u0107\u010b\7\62\2\2\u0108\u0109" +
                    "\f\20\2\2\u0109\u010b\t\t\2\2\u010a\u00d2\3\2\2\2\u010a\u00d5\3\2\2\2" +
                    "\u010a\u00d8\3\2\2\2\u010a\u00db\3\2\2\2\u010a\u00de\3\2\2\2\u010a\u00e1" +
                    "\3\2\2\2\u010a\u00e4\3\2\2\2\u010a\u00e7\3\2\2\2\u010a\u00ea\3\2\2\2\u010a" +
                    "\u00ed\3\2\2\2\u010a\u00f0\3\2\2\2\u010a\u00f3\3\2\2\2\u010a\u0100\3\2" +
                    "\2\2\u010a\u0105\3\2\2\2\u010a\u0108\3\2\2\2\u010b\u010e\3\2\2\2\u010c" +
                    "\u010a\3\2\2\2\u010c\u010d\3\2\2\2\u010d\35\3\2\2\2\u010e\u010c\3\2\2" +
                    "\2\u010f\u0114\7\63\2\2\u0110\u0114\7\64\2\2\u0111\u0114\t\n\2\2\u0112" +
                    "\u0114\7/\2\2\u0113\u010f\3\2\2\2\u0113\u0110\3\2\2\2\u0113\u0111\3\2" +
                    "\2\2\u0113\u0112\3\2\2\2\u0114\37\3\2\2\2!#%-/\63\679DLT]hq}\u0086\u008b" +
                    "\u008f\u0093\u009d\u00a5\u00a8\u00b4\u00c5\u00ca\u00ce\u00d0\u00f6\u00fc" +
                    "\u010a\u010c\u0113";
    public static final ATN _ATN =
            new ATNDeserializer().deserialize(_serializedATN.toCharArray());
    protected static final DFA[] _decisionToDFA;
    protected static final PredictionContextCache _sharedContextCache =
            new PredictionContextCache();
    private static final String[] _LITERAL_NAMES = {
            null, "'class'", "'{'", "'}'", "'('", "','", "')'", "'='", "';'", "'if'",
            "'else'", "'for'", "'while'", "'continue'", "'break'", "'return'", "'['",
            "']'", "'this'", "'.'", "'++'", "'--'", "'!'", "'+'", "'-'", "'~'", "'new'",
            "'*'", "'/'", "'%'", "'<<'", "'>>'", "'<'", "'<='", "'>'", "'>='", "'=='",
            "'!='", "'&'", "'^'", "'|'", "'&&'", "'||'", "'true'", "'false'", "'null'"
    };
    private static final String[] _SYMBOLIC_NAMES = {
            null, null, null, null, null, null, null, null, null, null, null, null,
            null, null, null, null, null, null, null, null, null, null, null, null,
            null, null, null, null, null, null, null, null, null, null, null, null,
            null, null, null, null, null, null, null, null, null, null, "BASETYPE",
            "DECORATOR", "IDENTIFIER", "INTEGER", "STRING", "NEXTLINE", "LINECOMMENT",
            "BLOCKCOMMENT", "WHITECHAR"
    };
    public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

    static {
        RuntimeMetaData.checkVersion("4.6", RuntimeMetaData.VERSION);
    }

    static {
        tokenNames = new String[_SYMBOLIC_NAMES.length];
        for (int i = 0; i < tokenNames.length; i++) {
            tokenNames[i] = VOCABULARY.getLiteralName(i);
            if (tokenNames[i] == null) {
                tokenNames[i] = VOCABULARY.getSymbolicName(i);
            }

            if (tokenNames[i] == null) {
                tokenNames[i] = "<INVALID>";
            }
        }
    }

    static {
        _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
        for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
            _decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
        }
    }

    public MiracleParser(TokenStream input) {
        super(input);
        _interp = new ParserATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
    }

    @Override
    @Deprecated
    public String[] getTokenNames() {
        return tokenNames;
    }

    @Override

    public Vocabulary getVocabulary() {
        return VOCABULARY;
    }

    @Override
    public String getGrammarFileName() {
        return "Miracle.g4";
    }

    @Override
    public String[] getRuleNames() {
        return ruleNames;
    }

    @Override
    public String getSerializedATN() {
        return _serializedATN;
    }

    @Override
    public ATN getATN() {
        return _ATN;
    }

    public final MiracleContext miracle() throws RecognitionException {
        MiracleContext _localctx = new MiracleContext(_ctx, getState());
        enterRule(_localctx, 0, RULE_miracle);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(35);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << BASETYPE) | (1L << IDENTIFIER))) != 0)) {
                    {
                        setState(33);
                        _errHandler.sync(this);
                        switch (getInterpreter().adaptivePredict(_input, 0, _ctx)) {
                            case 1: {
                                setState(30);
                                classDeclarationStatement();
                            }
                            break;
                            case 2: {
                                setState(31);
                                functionDeclarationStatement();
                            }
                            break;
                            case 3: {
                                setState(32);
                                variableDeclarationStatement();
                            }
                            break;
                        }
                    }
                    setState(37);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final ClassDeclarationStatementContext classDeclarationStatement() throws RecognitionException {
        ClassDeclarationStatementContext _localctx = new ClassDeclarationStatementContext(_ctx, getState());
        enterRule(_localctx, 2, RULE_classDeclarationStatement);
        int _la;
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                setState(38);
                match(T__0);
                setState(39);
                match(IDENTIFIER);
                setState(40);
                match(T__1);
                {
                    setState(45);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input, 3, _ctx);
                    while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
                        if (_alt == 1) {
                            {
                                setState(43);
                                _errHandler.sync(this);
                                switch (getInterpreter().adaptivePredict(_input, 2, _ctx)) {
                                    case 1: {
                                        setState(41);
                                        functionDeclarationStatement();
                                    }
                                    break;
                                    case 2: {
                                        setState(42);
                                        variableDeclarationStatement();
                                    }
                                    break;
                                }
                            }
                        }
                        setState(47);
                        _errHandler.sync(this);
                        _alt = getInterpreter().adaptivePredict(_input, 3, _ctx);
                    }
                    setState(49);
                    _errHandler.sync(this);
                    switch (getInterpreter().adaptivePredict(_input, 4, _ctx)) {
                        case 1: {
                            setState(48);
                            constructorDeclarationStatement();
                        }
                        break;
                    }
                    setState(55);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    while (_la == BASETYPE || _la == IDENTIFIER) {
                        {
                            setState(53);
                            _errHandler.sync(this);
                            switch (getInterpreter().adaptivePredict(_input, 5, _ctx)) {
                                case 1: {
                                    setState(51);
                                    functionDeclarationStatement();
                                }
                                break;
                                case 2: {
                                    setState(52);
                                    variableDeclarationStatement();
                                }
                                break;
                            }
                        }
                        setState(57);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                    }
                }
                setState(58);
                match(T__2);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final FunctionDeclarationStatementContext functionDeclarationStatement() throws RecognitionException {
        FunctionDeclarationStatementContext _localctx = new FunctionDeclarationStatementContext(_ctx, getState());
        enterRule(_localctx, 4, RULE_functionDeclarationStatement);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(60);
                typename();
                setState(61);
                match(IDENTIFIER);
                setState(62);
                match(T__3);
                setState(66);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == BASETYPE || _la == IDENTIFIER) {
                    {
                        setState(63);
                        typename();
                        setState(64);
                        match(IDENTIFIER);
                    }
                }

                setState(74);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == T__4) {
                    {
                        {
                            setState(68);
                            match(T__4);
                            setState(69);
                            typename();
                            setState(70);
                            match(IDENTIFIER);
                        }
                    }
                    setState(76);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
                setState(77);
                match(T__5);
                setState(78);
                match(T__1);
                setState(82);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__3) | (1L << T__7) | (1L << T__8) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__17) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__42) | (1L << T__43) | (1L << T__44) | (1L << BASETYPE) | (1L << IDENTIFIER) | (1L << INTEGER) | (1L << STRING))) != 0)) {
                    {
                        {
                            setState(79);
                            statement();
                        }
                    }
                    setState(84);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
                setState(85);
                match(T__2);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final VariableDeclarationStatementContext variableDeclarationStatement() throws RecognitionException {
        VariableDeclarationStatementContext _localctx = new VariableDeclarationStatementContext(_ctx, getState());
        enterRule(_localctx, 6, RULE_variableDeclarationStatement);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(87);
                typename();
                setState(88);
                match(IDENTIFIER);
                setState(91);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == T__6) {
                    {
                        setState(89);
                        match(T__6);
                        setState(90);
                        expression(0);
                    }
                }

                setState(93);
                match(T__7);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final ConstructorDeclarationStatementContext constructorDeclarationStatement() throws RecognitionException {
        ConstructorDeclarationStatementContext _localctx = new ConstructorDeclarationStatementContext(_ctx, getState());
        enterRule(_localctx, 8, RULE_constructorDeclarationStatement);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(95);
                typename();
                setState(96);
                match(T__3);
                setState(97);
                match(T__5);
                setState(98);
                match(T__1);
                setState(102);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__3) | (1L << T__7) | (1L << T__8) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__17) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__42) | (1L << T__43) | (1L << T__44) | (1L << BASETYPE) | (1L << IDENTIFIER) | (1L << INTEGER) | (1L << STRING))) != 0)) {
                    {
                        {
                            setState(99);
                            statement();
                        }
                    }
                    setState(104);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
                setState(105);
                match(T__2);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final BlockStatementContext blockStatement() throws RecognitionException {
        BlockStatementContext _localctx = new BlockStatementContext(_ctx, getState());
        enterRule(_localctx, 10, RULE_blockStatement);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(107);
                match(T__1);
                setState(111);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__3) | (1L << T__7) | (1L << T__8) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__17) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__42) | (1L << T__43) | (1L << T__44) | (1L << BASETYPE) | (1L << IDENTIFIER) | (1L << INTEGER) | (1L << STRING))) != 0)) {
                    {
                        {
                            setState(108);
                            statement();
                        }
                    }
                    setState(113);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
                setState(114);
                match(T__2);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final StatementContext statement() throws RecognitionException {
        StatementContext _localctx = new StatementContext(_ctx, getState());
        enterRule(_localctx, 12, RULE_statement);
        try {
            setState(123);
            _errHandler.sync(this);
            switch (getInterpreter().adaptivePredict(_input, 13, _ctx)) {
                case 1:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(116);
                    blockStatement();
                }
                break;
                case 2:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(117);
                    variableDeclarationStatement();
                }
                break;
                case 3:
                    enterOuterAlt(_localctx, 3);
                {
                    setState(118);
                    selectionStatement();
                }
                break;
                case 4:
                    enterOuterAlt(_localctx, 4);
                {
                    setState(119);
                    iterationStatement();
                }
                break;
                case 5:
                    enterOuterAlt(_localctx, 5);
                {
                    setState(120);
                    controlStatement();
                }
                break;
                case 6:
                    enterOuterAlt(_localctx, 6);
                {
                    setState(121);
                    expressionStatement();
                }
                break;
                case 7:
                    enterOuterAlt(_localctx, 7);
                {
                    setState(122);
                    emptyStatement();
                }
                break;
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final SelectionStatementContext selectionStatement() throws RecognitionException {
        SelectionStatementContext _localctx = new SelectionStatementContext(_ctx, getState());
        enterRule(_localctx, 14, RULE_selectionStatement);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(125);
                match(T__8);
                setState(126);
                match(T__3);
                setState(127);
                expression(0);
                setState(128);
                match(T__5);
                setState(129);
                statement();
                setState(132);
                _errHandler.sync(this);
                switch (getInterpreter().adaptivePredict(_input, 14, _ctx)) {
                    case 1: {
                        setState(130);
                        match(T__9);
                        setState(131);
                        statement();
                    }
                    break;
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final IterationStatementContext iterationStatement() throws RecognitionException {
        IterationStatementContext _localctx = new IterationStatementContext(_ctx, getState());
        enterRule(_localctx, 16, RULE_iterationStatement);
        int _la;
        try {
            setState(155);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case T__10:
                    _localctx = new ForStatementContext(_localctx);
                    enterOuterAlt(_localctx, 1);
                {
                    setState(134);
                    match(T__10);
                    setState(135);
                    match(T__3);
                    setState(137);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__17) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__42) | (1L << T__43) | (1L << T__44) | (1L << IDENTIFIER) | (1L << INTEGER) | (1L << STRING))) != 0)) {
                        {
                            setState(136);
                            expression(0);
                        }
                    }

                    setState(139);
                    match(T__7);
                    setState(141);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__17) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__42) | (1L << T__43) | (1L << T__44) | (1L << IDENTIFIER) | (1L << INTEGER) | (1L << STRING))) != 0)) {
                        {
                            setState(140);
                            expression(0);
                        }
                    }

                    setState(143);
                    match(T__7);
                    setState(145);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__17) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__42) | (1L << T__43) | (1L << T__44) | (1L << IDENTIFIER) | (1L << INTEGER) | (1L << STRING))) != 0)) {
                        {
                            setState(144);
                            expression(0);
                        }
                    }

                    setState(147);
                    match(T__5);
                    setState(148);
                    statement();
                }
                break;
                case T__11:
                    _localctx = new WhileStatementContext(_localctx);
                    enterOuterAlt(_localctx, 2);
                {
                    setState(149);
                    match(T__11);
                    setState(150);
                    match(T__3);
                    setState(151);
                    expression(0);
                    setState(152);
                    match(T__5);
                    setState(153);
                    statement();
                }
                break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final ControlStatementContext controlStatement() throws RecognitionException {
        ControlStatementContext _localctx = new ControlStatementContext(_ctx, getState());
        enterRule(_localctx, 18, RULE_controlStatement);
        int _la;
        try {
            setState(166);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case T__12:
                    _localctx = new ContinueStatementContext(_localctx);
                    enterOuterAlt(_localctx, 1);
                {
                    setState(157);
                    match(T__12);
                    setState(158);
                    match(T__7);
                }
                break;
                case T__13:
                    _localctx = new BreakStatementContext(_localctx);
                    enterOuterAlt(_localctx, 2);
                {
                    setState(159);
                    match(T__13);
                    setState(160);
                    match(T__7);
                }
                break;
                case T__14:
                    _localctx = new ReturnStatementContext(_localctx);
                    enterOuterAlt(_localctx, 3);
                {
                    setState(161);
                    match(T__14);
                    setState(163);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__17) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__42) | (1L << T__43) | (1L << T__44) | (1L << IDENTIFIER) | (1L << INTEGER) | (1L << STRING))) != 0)) {
                        {
                            setState(162);
                            expression(0);
                        }
                    }

                    setState(165);
                    match(T__7);
                }
                break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final ExpressionStatementContext expressionStatement() throws RecognitionException {
        ExpressionStatementContext _localctx = new ExpressionStatementContext(_ctx, getState());
        enterRule(_localctx, 20, RULE_expressionStatement);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(168);
                expression(0);
                setState(169);
                match(T__7);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final EmptyStatementContext emptyStatement() throws RecognitionException {
        EmptyStatementContext _localctx = new EmptyStatementContext(_ctx, getState());
        enterRule(_localctx, 22, RULE_emptyStatement);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(171);
                match(T__7);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final TypenameContext typename() throws RecognitionException {
        TypenameContext _localctx = new TypenameContext(_ctx, getState());
        enterRule(_localctx, 24, RULE_typename);
        int _la;
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                setState(173);
                _la = _input.LA(1);
                if (!(_la == BASETYPE || _la == IDENTIFIER)) {
                    _errHandler.recoverInline(this);
                } else {
                    if (_input.LA(1) == Token.EOF) matchedEOF = true;
                    _errHandler.reportMatch(this);
                    consume();
                }
                setState(178);
                _errHandler.sync(this);
                _alt = getInterpreter().adaptivePredict(_input, 21, _ctx);
                while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
                    if (_alt == 1) {
                        {
                            {
                                setState(174);
                                match(T__15);
                                setState(175);
                                match(T__16);
                            }
                        }
                    }
                    setState(180);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input, 21, _ctx);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final ExpressionContext expression() throws RecognitionException {
        return expression(0);
    }

    private ExpressionContext expression(int _p) throws RecognitionException {
        ParserRuleContext _parentctx = _ctx;
        int _parentState = getState();
        ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
        ExpressionContext _prevctx = _localctx;
        int _startState = 26;
        enterRecursionRule(_localctx, 26, RULE_expression, _p);
        int _la;
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                setState(206);
                _errHandler.sync(this);
                switch (_input.LA(1)) {
                    case T__42:
                    case T__43:
                    case T__44:
                    case INTEGER:
                    case STRING: {
                        _localctx = new ConstantExpressionContext(_localctx);
                        _ctx = _localctx;
                        _prevctx = _localctx;

                        setState(182);
                        constant();
                    }
                    break;
                    case T__17: {
                        _localctx = new ThisExpressionContext(_localctx);
                        _ctx = _localctx;
                        _prevctx = _localctx;
                        setState(183);
                        match(T__17);
                    }
                    break;
                    case IDENTIFIER: {
                        _localctx = new VariableExpressionContext(_localctx);
                        _ctx = _localctx;
                        _prevctx = _localctx;
                        setState(184);
                        match(IDENTIFIER);
                    }
                    break;
                    case T__3: {
                        _localctx = new BraceExpressionContext(_localctx);
                        _ctx = _localctx;
                        _prevctx = _localctx;
                        setState(185);
                        match(T__3);
                        setState(186);
                        expression(0);
                        setState(187);
                        match(T__5);
                    }
                    break;
                    case T__19:
                    case T__20:
                    case T__21:
                    case T__22:
                    case T__23:
                    case T__24: {
                        _localctx = new PrefixExpressionContext(_localctx);
                        _ctx = _localctx;
                        _prevctx = _localctx;
                        setState(189);
                        ((PrefixExpressionContext) _localctx).operator = _input.LT(1);
                        _la = _input.LA(1);
                        if (!((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24))) != 0))) {
                            ((PrefixExpressionContext) _localctx).operator = (Token) _errHandler.recoverInline(this);
                        } else {
                            if (_input.LA(1) == Token.EOF) matchedEOF = true;
                            _errHandler.reportMatch(this);
                            consume();
                        }
                        setState(190);
                        expression(13);
                    }
                    break;
                    case T__25: {
                        _localctx = new NewExpressionContext(_localctx);
                        _ctx = _localctx;
                        _prevctx = _localctx;
                        setState(191);
                        match(T__25);
                        setState(192);
                        typename();
                        setState(204);
                        _errHandler.sync(this);
                        switch (getInterpreter().adaptivePredict(_input, 24, _ctx)) {
                            case 1: {
                                setState(198);
                                _errHandler.sync(this);
                                _alt = 1;
                                do {
                                    switch (_alt) {
                                        case 1: {
                                            {
                                                setState(193);
                                                match(T__15);
                                                setState(195);
                                                _errHandler.sync(this);
                                                _la = _input.LA(1);
                                                if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__17) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__42) | (1L << T__43) | (1L << T__44) | (1L << IDENTIFIER) | (1L << INTEGER) | (1L << STRING))) != 0)) {
                                                    {
                                                        setState(194);
                                                        expression(0);
                                                    }
                                                }

                                                setState(197);
                                                match(T__16);
                                            }
                                        }
                                        break;
                                        default:
                                            throw new NoViableAltException(this);
                                    }
                                    setState(200);
                                    _errHandler.sync(this);
                                    _alt = getInterpreter().adaptivePredict(_input, 23, _ctx);
                                } while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER);
                            }
                            break;
                            case 2: {
                                setState(202);
                                match(T__3);
                                setState(203);
                                match(T__5);
                            }
                            break;
                        }
                    }
                    break;
                    default:
                        throw new NoViableAltException(this);
                }
                _ctx.stop = _input.LT(-1);
                setState(266);
                _errHandler.sync(this);
                _alt = getInterpreter().adaptivePredict(_input, 29, _ctx);
                while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
                    if (_alt == 1) {
                        if (_parseListeners != null) triggerExitRuleEvent();
                        _prevctx = _localctx;
                        {
                            setState(264);
                            _errHandler.sync(this);
                            switch (getInterpreter().adaptivePredict(_input, 28, _ctx)) {
                                case 1: {
                                    _localctx = new BinaryExpressionContext(new ExpressionContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(208);
                                    if (!(precpred(_ctx, 11)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 11)");
                                    setState(209);
                                    ((BinaryExpressionContext) _localctx).operator = _input.LT(1);
                                    _la = _input.LA(1);
                                    if (!((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__26) | (1L << T__27) | (1L << T__28))) != 0))) {
                                        ((BinaryExpressionContext) _localctx).operator = (Token) _errHandler.recoverInline(this);
                                    } else {
                                        if (_input.LA(1) == Token.EOF) matchedEOF = true;
                                        _errHandler.reportMatch(this);
                                        consume();
                                    }
                                    setState(210);
                                    expression(12);
                                }
                                break;
                                case 2: {
                                    _localctx = new BinaryExpressionContext(new ExpressionContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(211);
                                    if (!(precpred(_ctx, 10)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 10)");
                                    setState(212);
                                    ((BinaryExpressionContext) _localctx).operator = _input.LT(1);
                                    _la = _input.LA(1);
                                    if (!(_la == T__22 || _la == T__23)) {
                                        ((BinaryExpressionContext) _localctx).operator = (Token) _errHandler.recoverInline(this);
                                    } else {
                                        if (_input.LA(1) == Token.EOF) matchedEOF = true;
                                        _errHandler.reportMatch(this);
                                        consume();
                                    }
                                    setState(213);
                                    expression(11);
                                }
                                break;
                                case 3: {
                                    _localctx = new BinaryExpressionContext(new ExpressionContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(214);
                                    if (!(precpred(_ctx, 9)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 9)");
                                    setState(215);
                                    ((BinaryExpressionContext) _localctx).operator = _input.LT(1);
                                    _la = _input.LA(1);
                                    if (!(_la == T__29 || _la == T__30)) {
                                        ((BinaryExpressionContext) _localctx).operator = (Token) _errHandler.recoverInline(this);
                                    } else {
                                        if (_input.LA(1) == Token.EOF) matchedEOF = true;
                                        _errHandler.reportMatch(this);
                                        consume();
                                    }
                                    setState(216);
                                    expression(10);
                                }
                                break;
                                case 4: {
                                    _localctx = new BinaryExpressionContext(new ExpressionContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(217);
                                    if (!(precpred(_ctx, 8)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 8)");
                                    setState(218);
                                    ((BinaryExpressionContext) _localctx).operator = _input.LT(1);
                                    _la = _input.LA(1);
                                    if (!((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__31) | (1L << T__32) | (1L << T__33) | (1L << T__34))) != 0))) {
                                        ((BinaryExpressionContext) _localctx).operator = (Token) _errHandler.recoverInline(this);
                                    } else {
                                        if (_input.LA(1) == Token.EOF) matchedEOF = true;
                                        _errHandler.reportMatch(this);
                                        consume();
                                    }
                                    setState(219);
                                    expression(9);
                                }
                                break;
                                case 5: {
                                    _localctx = new BinaryExpressionContext(new ExpressionContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(220);
                                    if (!(precpred(_ctx, 7)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 7)");
                                    setState(221);
                                    ((BinaryExpressionContext) _localctx).operator = _input.LT(1);
                                    _la = _input.LA(1);
                                    if (!(_la == T__35 || _la == T__36)) {
                                        ((BinaryExpressionContext) _localctx).operator = (Token) _errHandler.recoverInline(this);
                                    } else {
                                        if (_input.LA(1) == Token.EOF) matchedEOF = true;
                                        _errHandler.reportMatch(this);
                                        consume();
                                    }
                                    setState(222);
                                    expression(8);
                                }
                                break;
                                case 6: {
                                    _localctx = new BinaryExpressionContext(new ExpressionContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(223);
                                    if (!(precpred(_ctx, 6)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 6)");
                                    setState(224);
                                    ((BinaryExpressionContext) _localctx).operator = match(T__37);
                                    setState(225);
                                    expression(7);
                                }
                                break;
                                case 7: {
                                    _localctx = new BinaryExpressionContext(new ExpressionContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(226);
                                    if (!(precpred(_ctx, 5)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 5)");
                                    setState(227);
                                    ((BinaryExpressionContext) _localctx).operator = match(T__38);
                                    setState(228);
                                    expression(6);
                                }
                                break;
                                case 8: {
                                    _localctx = new BinaryExpressionContext(new ExpressionContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(229);
                                    if (!(precpred(_ctx, 4)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 4)");
                                    setState(230);
                                    ((BinaryExpressionContext) _localctx).operator = match(T__39);
                                    setState(231);
                                    expression(5);
                                }
                                break;
                                case 9: {
                                    _localctx = new BinaryExpressionContext(new ExpressionContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(232);
                                    if (!(precpred(_ctx, 3)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 3)");
                                    setState(233);
                                    ((BinaryExpressionContext) _localctx).operator = match(T__40);
                                    setState(234);
                                    expression(4);
                                }
                                break;
                                case 10: {
                                    _localctx = new BinaryExpressionContext(new ExpressionContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(235);
                                    if (!(precpred(_ctx, 2)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 2)");
                                    setState(236);
                                    ((BinaryExpressionContext) _localctx).operator = match(T__41);
                                    setState(237);
                                    expression(3);
                                }
                                break;
                                case 11: {
                                    _localctx = new BinaryExpressionContext(new ExpressionContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(238);
                                    if (!(precpred(_ctx, 1)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 1)");
                                    setState(239);
                                    ((BinaryExpressionContext) _localctx).operator = match(T__6);
                                    setState(240);
                                    expression(1);
                                }
                                break;
                                case 12: {
                                    _localctx = new FunctionCallExpressionContext(new ExpressionContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(241);
                                    if (!(precpred(_ctx, 17)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 17)");
                                    setState(242);
                                    match(T__3);
                                    setState(244);
                                    _errHandler.sync(this);
                                    _la = _input.LA(1);
                                    if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__17) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__42) | (1L << T__43) | (1L << T__44) | (1L << IDENTIFIER) | (1L << INTEGER) | (1L << STRING))) != 0)) {
                                        {
                                            setState(243);
                                            expression(0);
                                        }
                                    }

                                    setState(250);
                                    _errHandler.sync(this);
                                    _la = _input.LA(1);
                                    while (_la == T__4) {
                                        {
                                            {
                                                setState(246);
                                                match(T__4);
                                                setState(247);
                                                expression(0);
                                            }
                                        }
                                        setState(252);
                                        _errHandler.sync(this);
                                        _la = _input.LA(1);
                                    }
                                    setState(253);
                                    match(T__5);
                                }
                                break;
                                case 13: {
                                    _localctx = new SubscriptExpressionContext(new ExpressionContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(254);
                                    if (!(precpred(_ctx, 16)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 16)");
                                    setState(255);
                                    match(T__15);
                                    setState(256);
                                    expression(0);
                                    setState(257);
                                    match(T__16);
                                }
                                break;
                                case 14: {
                                    _localctx = new FieldExpressionContext(new ExpressionContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(259);
                                    if (!(precpred(_ctx, 15)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 15)");
                                    setState(260);
                                    ((FieldExpressionContext) _localctx).operator = match(T__18);
                                    setState(261);
                                    match(IDENTIFIER);
                                }
                                break;
                                case 15: {
                                    _localctx = new SuffixExpressionContext(new ExpressionContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(262);
                                    if (!(precpred(_ctx, 14)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 14)");
                                    setState(263);
                                    ((SuffixExpressionContext) _localctx).operator = _input.LT(1);
                                    _la = _input.LA(1);
                                    if (!(_la == T__19 || _la == T__20)) {
                                        ((SuffixExpressionContext) _localctx).operator = (Token) _errHandler.recoverInline(this);
                                    } else {
                                        if (_input.LA(1) == Token.EOF) matchedEOF = true;
                                        _errHandler.reportMatch(this);
                                        consume();
                                    }
                                }
                                break;
                            }
                        }
                    }
                    setState(268);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input, 29, _ctx);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            unrollRecursionContexts(_parentctx);
        }
        return _localctx;
    }

    public final ConstantContext constant() throws RecognitionException {
        ConstantContext _localctx = new ConstantContext(_ctx, getState());
        enterRule(_localctx, 28, RULE_constant);
        int _la;
        try {
            setState(273);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case INTEGER:
                    _localctx = new IntegerConstantContext(_localctx);
                    enterOuterAlt(_localctx, 1);
                {
                    setState(269);
                    match(INTEGER);
                }
                break;
                case STRING:
                    _localctx = new StringConstantContext(_localctx);
                    enterOuterAlt(_localctx, 2);
                {
                    setState(270);
                    match(STRING);
                }
                break;
                case T__42:
                case T__43:
                    _localctx = new BoolConstantContext(_localctx);
                    enterOuterAlt(_localctx, 3);
                {
                    setState(271);
                    _la = _input.LA(1);
                    if (!(_la == T__42 || _la == T__43)) {
                        _errHandler.recoverInline(this);
                    } else {
                        if (_input.LA(1) == Token.EOF) matchedEOF = true;
                        _errHandler.reportMatch(this);
                        consume();
                    }
                }
                break;
                case T__44:
                    _localctx = new NullConstantContext(_localctx);
                    enterOuterAlt(_localctx, 4);
                {
                    setState(272);
                    match(T__44);
                }
                break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
        switch (ruleIndex) {
            case 13:
                return expression_sempred((ExpressionContext) _localctx, predIndex);
        }
        return true;
    }

    private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
        switch (predIndex) {
            case 0:
                return precpred(_ctx, 11);
            case 1:
                return precpred(_ctx, 10);
            case 2:
                return precpred(_ctx, 9);
            case 3:
                return precpred(_ctx, 8);
            case 4:
                return precpred(_ctx, 7);
            case 5:
                return precpred(_ctx, 6);
            case 6:
                return precpred(_ctx, 5);
            case 7:
                return precpred(_ctx, 4);
            case 8:
                return precpred(_ctx, 3);
            case 9:
                return precpred(_ctx, 2);
            case 10:
                return precpred(_ctx, 1);
            case 11:
                return precpred(_ctx, 17);
            case 12:
                return precpred(_ctx, 16);
            case 13:
                return precpred(_ctx, 15);
            case 14:
                return precpred(_ctx, 14);
        }
        return true;
    }

    public static class MiracleContext extends ParserRuleContext {
        public MiracleContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public List<ClassDeclarationStatementContext> classDeclarationStatement() {
            return getRuleContexts(ClassDeclarationStatementContext.class);
        }

        public ClassDeclarationStatementContext classDeclarationStatement(int i) {
            return getRuleContext(ClassDeclarationStatementContext.class, i);
        }

        public List<FunctionDeclarationStatementContext> functionDeclarationStatement() {
            return getRuleContexts(FunctionDeclarationStatementContext.class);
        }

        public FunctionDeclarationStatementContext functionDeclarationStatement(int i) {
            return getRuleContext(FunctionDeclarationStatementContext.class, i);
        }

        public List<VariableDeclarationStatementContext> variableDeclarationStatement() {
            return getRuleContexts(VariableDeclarationStatementContext.class);
        }

        public VariableDeclarationStatementContext variableDeclarationStatement(int i) {
            return getRuleContext(VariableDeclarationStatementContext.class, i);
        }

        @Override
        public int getRuleIndex() {
            return RULE_miracle;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof MiracleListener) ((MiracleListener) listener).enterMiracle(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof MiracleListener) ((MiracleListener) listener).exitMiracle(this);
        }
    }

    public static class ClassDeclarationStatementContext extends ParserRuleContext {
        public ClassDeclarationStatementContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode IDENTIFIER() {
            return getToken(MiracleParser.IDENTIFIER, 0);
        }

        public List<FunctionDeclarationStatementContext> functionDeclarationStatement() {
            return getRuleContexts(FunctionDeclarationStatementContext.class);
        }

        public FunctionDeclarationStatementContext functionDeclarationStatement(int i) {
            return getRuleContext(FunctionDeclarationStatementContext.class, i);
        }

        public List<VariableDeclarationStatementContext> variableDeclarationStatement() {
            return getRuleContexts(VariableDeclarationStatementContext.class);
        }

        public VariableDeclarationStatementContext variableDeclarationStatement(int i) {
            return getRuleContext(VariableDeclarationStatementContext.class, i);
        }

        public ConstructorDeclarationStatementContext constructorDeclarationStatement() {
            return getRuleContext(ConstructorDeclarationStatementContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_classDeclarationStatement;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof MiracleListener) ((MiracleListener) listener).enterClassDeclarationStatement(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof MiracleListener) ((MiracleListener) listener).exitClassDeclarationStatement(this);
        }
    }

    public static class FunctionDeclarationStatementContext extends ParserRuleContext {
        public FunctionDeclarationStatementContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public List<TypenameContext> typename() {
            return getRuleContexts(TypenameContext.class);
        }

        public TypenameContext typename(int i) {
            return getRuleContext(TypenameContext.class, i);
        }

        public List<TerminalNode> IDENTIFIER() {
            return getTokens(MiracleParser.IDENTIFIER);
        }

        public TerminalNode IDENTIFIER(int i) {
            return getToken(MiracleParser.IDENTIFIER, i);
        }

        public List<StatementContext> statement() {
            return getRuleContexts(StatementContext.class);
        }

        public StatementContext statement(int i) {
            return getRuleContext(StatementContext.class, i);
        }

        @Override
        public int getRuleIndex() {
            return RULE_functionDeclarationStatement;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof MiracleListener)
                ((MiracleListener) listener).enterFunctionDeclarationStatement(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof MiracleListener)
                ((MiracleListener) listener).exitFunctionDeclarationStatement(this);
        }
    }

    public static class VariableDeclarationStatementContext extends ParserRuleContext {
        public VariableDeclarationStatementContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TypenameContext typename() {
            return getRuleContext(TypenameContext.class, 0);
        }

        public TerminalNode IDENTIFIER() {
            return getToken(MiracleParser.IDENTIFIER, 0);
        }

        public ExpressionContext expression() {
            return getRuleContext(ExpressionContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_variableDeclarationStatement;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof MiracleListener)
                ((MiracleListener) listener).enterVariableDeclarationStatement(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof MiracleListener)
                ((MiracleListener) listener).exitVariableDeclarationStatement(this);
        }
    }

    public static class ConstructorDeclarationStatementContext extends ParserRuleContext {
        public ConstructorDeclarationStatementContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TypenameContext typename() {
            return getRuleContext(TypenameContext.class, 0);
        }

        public List<StatementContext> statement() {
            return getRuleContexts(StatementContext.class);
        }

        public StatementContext statement(int i) {
            return getRuleContext(StatementContext.class, i);
        }

        @Override
        public int getRuleIndex() {
            return RULE_constructorDeclarationStatement;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof MiracleListener)
                ((MiracleListener) listener).enterConstructorDeclarationStatement(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof MiracleListener)
                ((MiracleListener) listener).exitConstructorDeclarationStatement(this);
        }
    }

    public static class BlockStatementContext extends ParserRuleContext {
        public BlockStatementContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public List<StatementContext> statement() {
            return getRuleContexts(StatementContext.class);
        }

        public StatementContext statement(int i) {
            return getRuleContext(StatementContext.class, i);
        }

        @Override
        public int getRuleIndex() {
            return RULE_blockStatement;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof MiracleListener) ((MiracleListener) listener).enterBlockStatement(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof MiracleListener) ((MiracleListener) listener).exitBlockStatement(this);
        }
    }

    public static class StatementContext extends ParserRuleContext {
        public StatementContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public BlockStatementContext blockStatement() {
            return getRuleContext(BlockStatementContext.class, 0);
        }

        public VariableDeclarationStatementContext variableDeclarationStatement() {
            return getRuleContext(VariableDeclarationStatementContext.class, 0);
        }

        public SelectionStatementContext selectionStatement() {
            return getRuleContext(SelectionStatementContext.class, 0);
        }

        public IterationStatementContext iterationStatement() {
            return getRuleContext(IterationStatementContext.class, 0);
        }

        public ControlStatementContext controlStatement() {
            return getRuleContext(ControlStatementContext.class, 0);
        }

        public ExpressionStatementContext expressionStatement() {
            return getRuleContext(ExpressionStatementContext.class, 0);
        }

        public EmptyStatementContext emptyStatement() {
            return getRuleContext(EmptyStatementContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_statement;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof MiracleListener) ((MiracleListener) listener).enterStatement(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof MiracleListener) ((MiracleListener) listener).exitStatement(this);
        }
    }

    public static class SelectionStatementContext extends ParserRuleContext {
        public SelectionStatementContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public ExpressionContext expression() {
            return getRuleContext(ExpressionContext.class, 0);
        }

        public List<StatementContext> statement() {
            return getRuleContexts(StatementContext.class);
        }

        public StatementContext statement(int i) {
            return getRuleContext(StatementContext.class, i);
        }

        @Override
        public int getRuleIndex() {
            return RULE_selectionStatement;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof MiracleListener) ((MiracleListener) listener).enterSelectionStatement(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof MiracleListener) ((MiracleListener) listener).exitSelectionStatement(this);
        }
    }

    public static class IterationStatementContext extends ParserRuleContext {
        public IterationStatementContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public IterationStatementContext() {
        }

        @Override
        public int getRuleIndex() {
            return RULE_iterationStatement;
        }

        public void copyFrom(IterationStatementContext ctx) {
            super.copyFrom(ctx);
        }
    }

    public static class WhileStatementContext extends IterationStatementContext {
        public WhileStatementContext(IterationStatementContext ctx) {
            copyFrom(ctx);
        }

        public ExpressionContext expression() {
            return getRuleContext(ExpressionContext.class, 0);
        }

        public StatementContext statement() {
            return getRuleContext(StatementContext.class, 0);
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof MiracleListener) ((MiracleListener) listener).enterWhileStatement(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof MiracleListener) ((MiracleListener) listener).exitWhileStatement(this);
        }
    }

    public static class ForStatementContext extends IterationStatementContext {
        public ForStatementContext(IterationStatementContext ctx) {
            copyFrom(ctx);
        }

        public StatementContext statement() {
            return getRuleContext(StatementContext.class, 0);
        }

        public List<ExpressionContext> expression() {
            return getRuleContexts(ExpressionContext.class);
        }

        public ExpressionContext expression(int i) {
            return getRuleContext(ExpressionContext.class, i);
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof MiracleListener) ((MiracleListener) listener).enterForStatement(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof MiracleListener) ((MiracleListener) listener).exitForStatement(this);
        }
    }

    public static class ControlStatementContext extends ParserRuleContext {
        public ControlStatementContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public ControlStatementContext() {
        }

        @Override
        public int getRuleIndex() {
            return RULE_controlStatement;
        }

        public void copyFrom(ControlStatementContext ctx) {
            super.copyFrom(ctx);
        }
    }

    public static class BreakStatementContext extends ControlStatementContext {
        public BreakStatementContext(ControlStatementContext ctx) {
            copyFrom(ctx);
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof MiracleListener) ((MiracleListener) listener).enterBreakStatement(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof MiracleListener) ((MiracleListener) listener).exitBreakStatement(this);
        }
    }

    public static class ContinueStatementContext extends ControlStatementContext {
        public ContinueStatementContext(ControlStatementContext ctx) {
            copyFrom(ctx);
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof MiracleListener) ((MiracleListener) listener).enterContinueStatement(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof MiracleListener) ((MiracleListener) listener).exitContinueStatement(this);
        }
    }

    public static class ReturnStatementContext extends ControlStatementContext {
        public ReturnStatementContext(ControlStatementContext ctx) {
            copyFrom(ctx);
        }

        public ExpressionContext expression() {
            return getRuleContext(ExpressionContext.class, 0);
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof MiracleListener) ((MiracleListener) listener).enterReturnStatement(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof MiracleListener) ((MiracleListener) listener).exitReturnStatement(this);
        }
    }

    public static class ExpressionStatementContext extends ParserRuleContext {
        public ExpressionStatementContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public ExpressionContext expression() {
            return getRuleContext(ExpressionContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_expressionStatement;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof MiracleListener) ((MiracleListener) listener).enterExpressionStatement(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof MiracleListener) ((MiracleListener) listener).exitExpressionStatement(this);
        }
    }

    public static class EmptyStatementContext extends ParserRuleContext {
        public EmptyStatementContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_emptyStatement;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof MiracleListener) ((MiracleListener) listener).enterEmptyStatement(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof MiracleListener) ((MiracleListener) listener).exitEmptyStatement(this);
        }
    }

    public static class TypenameContext extends ParserRuleContext {
        public TypenameContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode BASETYPE() {
            return getToken(MiracleParser.BASETYPE, 0);
        }

        public TerminalNode IDENTIFIER() {
            return getToken(MiracleParser.IDENTIFIER, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_typename;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof MiracleListener) ((MiracleListener) listener).enterTypename(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof MiracleListener) ((MiracleListener) listener).exitTypename(this);
        }
    }

    public static class ExpressionContext extends ParserRuleContext {
        public ExpressionContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public ExpressionContext() {
        }

        @Override
        public int getRuleIndex() {
            return RULE_expression;
        }

        public void copyFrom(ExpressionContext ctx) {
            super.copyFrom(ctx);
        }
    }

    public static class BinaryExpressionContext extends ExpressionContext {
        public Token operator;

        public BinaryExpressionContext(ExpressionContext ctx) {
            copyFrom(ctx);
        }

        public List<ExpressionContext> expression() {
            return getRuleContexts(ExpressionContext.class);
        }

        public ExpressionContext expression(int i) {
            return getRuleContext(ExpressionContext.class, i);
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof MiracleListener) ((MiracleListener) listener).enterBinaryExpression(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof MiracleListener) ((MiracleListener) listener).exitBinaryExpression(this);
        }
    }

    public static class PrefixExpressionContext extends ExpressionContext {
        public Token operator;

        public PrefixExpressionContext(ExpressionContext ctx) {
            copyFrom(ctx);
        }

        public ExpressionContext expression() {
            return getRuleContext(ExpressionContext.class, 0);
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof MiracleListener) ((MiracleListener) listener).enterPrefixExpression(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof MiracleListener) ((MiracleListener) listener).exitPrefixExpression(this);
        }
    }

    public static class ConstantExpressionContext extends ExpressionContext {
        public ConstantExpressionContext(ExpressionContext ctx) {
            copyFrom(ctx);
        }

        public ConstantContext constant() {
            return getRuleContext(ConstantContext.class, 0);
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof MiracleListener) ((MiracleListener) listener).enterConstantExpression(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof MiracleListener) ((MiracleListener) listener).exitConstantExpression(this);
        }
    }

    public static class SubscriptExpressionContext extends ExpressionContext {
        public SubscriptExpressionContext(ExpressionContext ctx) {
            copyFrom(ctx);
        }

        public List<ExpressionContext> expression() {
            return getRuleContexts(ExpressionContext.class);
        }

        public ExpressionContext expression(int i) {
            return getRuleContext(ExpressionContext.class, i);
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof MiracleListener) ((MiracleListener) listener).enterSubscriptExpression(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof MiracleListener) ((MiracleListener) listener).exitSubscriptExpression(this);
        }
    }

    public static class ThisExpressionContext extends ExpressionContext {
        public ThisExpressionContext(ExpressionContext ctx) {
            copyFrom(ctx);
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof MiracleListener) ((MiracleListener) listener).enterThisExpression(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof MiracleListener) ((MiracleListener) listener).exitThisExpression(this);
        }
    }

    public static class SuffixExpressionContext extends ExpressionContext {
        public Token operator;

        public SuffixExpressionContext(ExpressionContext ctx) {
            copyFrom(ctx);
        }

        public ExpressionContext expression() {
            return getRuleContext(ExpressionContext.class, 0);
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof MiracleListener) ((MiracleListener) listener).enterSuffixExpression(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof MiracleListener) ((MiracleListener) listener).exitSuffixExpression(this);
        }
    }

    public static class NewExpressionContext extends ExpressionContext {
        public NewExpressionContext(ExpressionContext ctx) {
            copyFrom(ctx);
        }

        public TypenameContext typename() {
            return getRuleContext(TypenameContext.class, 0);
        }

        public List<ExpressionContext> expression() {
            return getRuleContexts(ExpressionContext.class);
        }

        public ExpressionContext expression(int i) {
            return getRuleContext(ExpressionContext.class, i);
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof MiracleListener) ((MiracleListener) listener).enterNewExpression(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof MiracleListener) ((MiracleListener) listener).exitNewExpression(this);
        }
    }

    public static class FieldExpressionContext extends ExpressionContext {
        public Token operator;

        public FieldExpressionContext(ExpressionContext ctx) {
            copyFrom(ctx);
        }

        public ExpressionContext expression() {
            return getRuleContext(ExpressionContext.class, 0);
        }

        public TerminalNode IDENTIFIER() {
            return getToken(MiracleParser.IDENTIFIER, 0);
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof MiracleListener) ((MiracleListener) listener).enterFieldExpression(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof MiracleListener) ((MiracleListener) listener).exitFieldExpression(this);
        }
    }

    public static class BraceExpressionContext extends ExpressionContext {
        public BraceExpressionContext(ExpressionContext ctx) {
            copyFrom(ctx);
        }

        public ExpressionContext expression() {
            return getRuleContext(ExpressionContext.class, 0);
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof MiracleListener) ((MiracleListener) listener).enterBraceExpression(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof MiracleListener) ((MiracleListener) listener).exitBraceExpression(this);
        }
    }

    public static class FunctionCallExpressionContext extends ExpressionContext {
        public FunctionCallExpressionContext(ExpressionContext ctx) {
            copyFrom(ctx);
        }

        public List<ExpressionContext> expression() {
            return getRuleContexts(ExpressionContext.class);
        }

        public ExpressionContext expression(int i) {
            return getRuleContext(ExpressionContext.class, i);
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof MiracleListener) ((MiracleListener) listener).enterFunctionCallExpression(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof MiracleListener) ((MiracleListener) listener).exitFunctionCallExpression(this);
        }
    }

    public static class VariableExpressionContext extends ExpressionContext {
        public VariableExpressionContext(ExpressionContext ctx) {
            copyFrom(ctx);
        }

        public TerminalNode IDENTIFIER() {
            return getToken(MiracleParser.IDENTIFIER, 0);
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof MiracleListener) ((MiracleListener) listener).enterVariableExpression(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof MiracleListener) ((MiracleListener) listener).exitVariableExpression(this);
        }
    }

    public static class ConstantContext extends ParserRuleContext {
        public ConstantContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public ConstantContext() {
        }

        @Override
        public int getRuleIndex() {
            return RULE_constant;
        }

        public void copyFrom(ConstantContext ctx) {
            super.copyFrom(ctx);
        }
    }

    public static class StringConstantContext extends ConstantContext {
        public StringConstantContext(ConstantContext ctx) {
            copyFrom(ctx);
        }

        public TerminalNode STRING() {
            return getToken(MiracleParser.STRING, 0);
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof MiracleListener) ((MiracleListener) listener).enterStringConstant(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof MiracleListener) ((MiracleListener) listener).exitStringConstant(this);
        }
    }

    public static class IntegerConstantContext extends ConstantContext {
        public IntegerConstantContext(ConstantContext ctx) {
            copyFrom(ctx);
        }

        public TerminalNode INTEGER() {
            return getToken(MiracleParser.INTEGER, 0);
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof MiracleListener) ((MiracleListener) listener).enterIntegerConstant(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof MiracleListener) ((MiracleListener) listener).exitIntegerConstant(this);
        }
    }

    public static class NullConstantContext extends ConstantContext {
        public NullConstantContext(ConstantContext ctx) {
            copyFrom(ctx);
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof MiracleListener) ((MiracleListener) listener).enterNullConstant(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof MiracleListener) ((MiracleListener) listener).exitNullConstant(this);
        }
    }

    public static class BoolConstantContext extends ConstantContext {
        public BoolConstantContext(ConstantContext ctx) {
            copyFrom(ctx);
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof MiracleListener) ((MiracleListener) listener).enterBoolConstant(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof MiracleListener) ((MiracleListener) listener).exitBoolConstant(this);
        }
    }
}