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
            BUILTIN_INT = 46, BUILTIN_BOOL = 47, BUILTIN_STRING = 48, BUILTIN_VOID = 49, DECORATOR = 50,
            IDENTIFIER = 51, INTEGER = 52, STRING = 53, NEXTLINE = 54, LINECOMMENT = 55, BLOCKCOMMENT = 56,
            WHITECHAR = 57;
    public static final int
            RULE_miracle = 0, RULE_classDeclarationStatement = 1, RULE_functionDeclarationStatement = 2,
            RULE_variableDeclarationStatement = 3, RULE_blockStatement = 4, RULE_statement = 5,
            RULE_selectionStatement = 6, RULE_iterationStatement = 7, RULE_controlStatement = 8,
            RULE_expressionStatement = 9, RULE_emptyStatement = 10, RULE_typename = 11,
            RULE_basetype = 12, RULE_expression = 13, RULE_constant = 14;
    public static final String[] ruleNames = {
            "miracle", "classDeclarationStatement", "functionDeclarationStatement",
            "variableDeclarationStatement", "blockStatement", "statement", "selectionStatement",
            "iterationStatement", "controlStatement", "expressionStatement", "emptyStatement",
            "typename", "basetype", "expression", "constant"
    };
    /**
     * @deprecated Use {@link #VOCABULARY} instead.
     */
    @Deprecated
    public static final String[] tokenNames;
    public static final String _serializedATN =
            "\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3;\u0108\4\2\t\2\4" +
                    "\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t" +
                    "\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\3\2\3\2\3\2\6\2$\n\2" +
                    "\r\2\16\2%\3\3\3\3\3\3\3\3\3\3\7\3-\n\3\f\3\16\3\60\13\3\3\3\3\3\3\4\3" +
                    "\4\5\4\66\n\4\3\4\3\4\3\4\3\4\5\4<\n\4\3\4\3\4\3\4\3\4\7\4B\n\4\f\4\16" +
                    "\4E\13\4\3\4\3\4\3\4\7\4J\n\4\f\4\16\4M\13\4\3\4\3\4\3\5\3\5\3\5\3\5\5" +
                    "\5U\n\5\3\5\3\5\3\6\3\6\7\6[\n\6\f\6\16\6^\13\6\3\6\3\6\3\7\3\7\3\7\3" +
                    "\7\3\7\3\7\3\7\5\7i\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\br\n\b\3\t\3\t\3" +
                    "\t\5\tw\n\t\3\t\3\t\5\t{\n\t\3\t\3\t\5\t\177\n\t\3\t\3\t\3\t\3\t\3\t\3" +
                    "\t\3\t\3\t\5\t\u0089\n\t\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u0091\n\n\3\n\5\n" +
                    "\u0094\n\n\3\13\3\13\3\13\3\f\3\f\3\r\3\r\3\r\7\r\u009e\n\r\f\r\16\r\u00a1" +
                    "\13\r\3\16\3\16\3\16\3\16\3\16\5\16\u00a8\n\16\3\17\3\17\3\17\3\17\3\17" +
                    "\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u00b8\n\17\3\17\6\17" +
                    "\u00bb\n\17\r\17\16\17\u00bc\3\17\3\17\5\17\u00c1\n\17\5\17\u00c3\n\17" +
                    "\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17" +
                    "\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17" +
                    "\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u00e9\n\17\3\17\3\17\7\17" +
                    "\u00ed\n\17\f\17\16\17\u00f0\13\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17" +
                    "\3\17\3\17\3\17\3\17\7\17\u00fd\n\17\f\17\16\17\u0100\13\17\3\20\3\20" +
                    "\3\20\3\20\5\20\u0106\n\20\3\20\2\3\34\21\2\4\6\b\n\f\16\20\22\24\26\30" +
                    "\32\34\36\2\n\3\2\26\33\3\2\35\37\3\2\31\32\3\2 !\3\2\"%\3\2&\'\3\2\26" +
                    "\27\3\2-.\u0133\2#\3\2\2\2\4\'\3\2\2\2\6\63\3\2\2\2\bP\3\2\2\2\nX\3\2" +
                    "\2\2\fh\3\2\2\2\16j\3\2\2\2\20\u0088\3\2\2\2\22\u0093\3\2\2\2\24\u0095" +
                    "\3\2\2\2\26\u0098\3\2\2\2\30\u009a\3\2\2\2\32\u00a7\3\2\2\2\34\u00c2\3" +
                    "\2\2\2\36\u0105\3\2\2\2 $\5\4\3\2!$\5\6\4\2\"$\5\b\5\2# \3\2\2\2#!\3\2" +
                    "\2\2#\"\3\2\2\2$%\3\2\2\2%#\3\2\2\2%&\3\2\2\2&\3\3\2\2\2\'(\7\3\2\2()" +
                    "\7\65\2\2).\7\4\2\2*-\5\6\4\2+-\5\b\5\2,*\3\2\2\2,+\3\2\2\2-\60\3\2\2" +
                    "\2.,\3\2\2\2./\3\2\2\2/\61\3\2\2\2\60.\3\2\2\2\61\62\7\5\2\2\62\5\3\2" +
                    "\2\2\63\65\5\30\r\2\64\66\7\65\2\2\65\64\3\2\2\2\65\66\3\2\2\2\66\67\3" +
                    "\2\2\2\67;\7\6\2\289\5\30\r\29:\7\65\2\2:<\3\2\2\2;8\3\2\2\2;<\3\2\2\2" +
                    "<C\3\2\2\2=>\7\7\2\2>?\5\30\r\2?@\7\65\2\2@B\3\2\2\2A=\3\2\2\2BE\3\2\2" +
                    "\2CA\3\2\2\2CD\3\2\2\2DF\3\2\2\2EC\3\2\2\2FG\7\b\2\2GK\7\4\2\2HJ\5\f\7" +
                    "\2IH\3\2\2\2JM\3\2\2\2KI\3\2\2\2KL\3\2\2\2LN\3\2\2\2MK\3\2\2\2NO\7\5\2" +
                    "\2O\7\3\2\2\2PQ\5\30\r\2QT\7\65\2\2RS\7\t\2\2SU\5\34\17\2TR\3\2\2\2TU" +
                    "\3\2\2\2UV\3\2\2\2VW\7\n\2\2W\t\3\2\2\2X\\\7\4\2\2Y[\5\f\7\2ZY\3\2\2\2" +
                    "[^\3\2\2\2\\Z\3\2\2\2\\]\3\2\2\2]_\3\2\2\2^\\\3\2\2\2_`\7\5\2\2`\13\3" +
                    "\2\2\2ai\5\n\6\2bi\5\b\5\2ci\5\16\b\2di\5\20\t\2ei\5\22\n\2fi\5\24\13" +
                    "\2gi\5\26\f\2ha\3\2\2\2hb\3\2\2\2hc\3\2\2\2hd\3\2\2\2he\3\2\2\2hf\3\2" +
                    "\2\2hg\3\2\2\2i\r\3\2\2\2jk\7\13\2\2kl\7\6\2\2lm\5\34\17\2mn\7\b\2\2n" +
                    "q\5\f\7\2op\7\f\2\2pr\5\f\7\2qo\3\2\2\2qr\3\2\2\2r\17\3\2\2\2st\7\r\2" +
                    "\2tv\7\6\2\2uw\5\34\17\2vu\3\2\2\2vw\3\2\2\2wx\3\2\2\2xz\7\n\2\2y{\5\34" +
                    "\17\2zy\3\2\2\2z{\3\2\2\2{|\3\2\2\2|~\7\n\2\2}\177\5\34\17\2~}\3\2\2\2" +
                    "~\177\3\2\2\2\177\u0080\3\2\2\2\u0080\u0081\7\b\2\2\u0081\u0089\5\f\7" +
                    "\2\u0082\u0083\7\16\2\2\u0083\u0084\7\6\2\2\u0084\u0085\5\34\17\2\u0085" +
                    "\u0086\7\b\2\2\u0086\u0087\5\f\7\2\u0087\u0089\3\2\2\2\u0088s\3\2\2\2" +
                    "\u0088\u0082\3\2\2\2\u0089\21\3\2\2\2\u008a\u008b\7\17\2\2\u008b\u0094" +
                    "\7\n\2\2\u008c\u008d\7\20\2\2\u008d\u0094\7\n\2\2\u008e\u0090\7\21\2\2" +
                    "\u008f\u0091\5\34\17\2\u0090\u008f\3\2\2\2\u0090\u0091\3\2\2\2\u0091\u0092" +
                    "\3\2\2\2\u0092\u0094\7\n\2\2\u0093\u008a\3\2\2\2\u0093\u008c\3\2\2\2\u0093" +
                    "\u008e\3\2\2\2\u0094\23\3\2\2\2\u0095\u0096\5\34\17\2\u0096\u0097\7\n" +
                    "\2\2\u0097\25\3\2\2\2\u0098\u0099\7\n\2\2\u0099\27\3\2\2\2\u009a\u009f" +
                    "\5\32\16\2\u009b\u009c\7\22\2\2\u009c\u009e\7\23\2\2\u009d\u009b\3\2\2" +
                    "\2\u009e\u00a1\3\2\2\2\u009f\u009d\3\2\2\2\u009f\u00a0\3\2\2\2\u00a0\31" +
                    "\3\2\2\2\u00a1\u009f\3\2\2\2\u00a2\u00a8\7\60\2\2\u00a3\u00a8\7\61\2\2" +
                    "\u00a4\u00a8\7\63\2\2\u00a5\u00a8\7\62\2\2\u00a6\u00a8\7\65\2\2\u00a7" +
                    "\u00a2\3\2\2\2\u00a7\u00a3\3\2\2\2\u00a7\u00a4\3\2\2\2\u00a7\u00a5\3\2" +
                    "\2\2\u00a7\u00a6\3\2\2\2\u00a8\33\3\2\2\2\u00a9\u00aa\b\17\1\2\u00aa\u00c3" +
                    "\5\36\20\2\u00ab\u00c3\7\24\2\2\u00ac\u00c3\7\65\2\2\u00ad\u00ae\7\6\2" +
                    "\2\u00ae\u00af\5\34\17\2\u00af\u00b0\7\b\2\2\u00b0\u00c3\3\2\2\2\u00b1" +
                    "\u00b2\t\2\2\2\u00b2\u00c3\5\34\17\17\u00b3\u00b4\7\34\2\2\u00b4\u00c0" +
                    "\5\30\r\2\u00b5\u00b7\7\22\2\2\u00b6\u00b8\5\34\17\2\u00b7\u00b6\3\2\2" +
                    "\2\u00b7\u00b8\3\2\2\2\u00b8\u00b9\3\2\2\2\u00b9\u00bb\7\23\2\2\u00ba" +
                    "\u00b5\3\2\2\2\u00bb\u00bc\3\2\2\2\u00bc\u00ba\3\2\2\2\u00bc\u00bd\3\2" +
                    "\2\2\u00bd\u00c1\3\2\2\2\u00be\u00bf\7\6\2\2\u00bf\u00c1\7\b\2\2\u00c0" +
                    "\u00ba\3\2\2\2\u00c0\u00be\3\2\2\2\u00c0\u00c1\3\2\2\2\u00c1\u00c3\3\2" +
                    "\2\2\u00c2\u00a9\3\2\2\2\u00c2\u00ab\3\2\2\2\u00c2\u00ac\3\2\2\2\u00c2" +
                    "\u00ad\3\2\2\2\u00c2\u00b1\3\2\2\2\u00c2\u00b3\3\2\2\2\u00c3\u00fe\3\2" +
                    "\2\2\u00c4\u00c5\f\r\2\2\u00c5\u00c6\t\3\2\2\u00c6\u00fd\5\34\17\16\u00c7" +
                    "\u00c8\f\f\2\2\u00c8\u00c9\t\4\2\2\u00c9\u00fd\5\34\17\r\u00ca\u00cb\f" +
                    "\13\2\2\u00cb\u00cc\t\5\2\2\u00cc\u00fd\5\34\17\f\u00cd\u00ce\f\n\2\2" +
                    "\u00ce\u00cf\t\6\2\2\u00cf\u00fd\5\34\17\13\u00d0\u00d1\f\t\2\2\u00d1" +
                    "\u00d2\t\7\2\2\u00d2\u00fd\5\34\17\n\u00d3\u00d4\f\b\2\2\u00d4\u00d5\7" +
                    "(\2\2\u00d5\u00fd\5\34\17\t\u00d6\u00d7\f\7\2\2\u00d7\u00d8\7)\2\2\u00d8" +
                    "\u00fd\5\34\17\b\u00d9\u00da\f\6\2\2\u00da\u00db\7*\2\2\u00db\u00fd\5" +
                    "\34\17\7\u00dc\u00dd\f\5\2\2\u00dd\u00de\7+\2\2\u00de\u00fd\5\34\17\6" +
                    "\u00df\u00e0\f\4\2\2\u00e0\u00e1\7,\2\2\u00e1\u00fd\5\34\17\5\u00e2\u00e3" +
                    "\f\3\2\2\u00e3\u00e4\7\t\2\2\u00e4\u00fd\5\34\17\3\u00e5\u00e6\f\23\2" +
                    "\2\u00e6\u00e8\7\6\2\2\u00e7\u00e9\5\34\17\2\u00e8\u00e7\3\2\2\2\u00e8" +
                    "\u00e9\3\2\2\2\u00e9\u00ee\3\2\2\2\u00ea\u00eb\7\7\2\2\u00eb\u00ed\5\34" +
                    "\17\2\u00ec\u00ea\3\2\2\2\u00ed\u00f0\3\2\2\2\u00ee\u00ec\3\2\2\2\u00ee" +
                    "\u00ef\3\2\2\2\u00ef\u00f1\3\2\2\2\u00f0\u00ee\3\2\2\2\u00f1\u00fd\7\b" +
                    "\2\2\u00f2\u00f3\f\22\2\2\u00f3\u00f4\7\22\2\2\u00f4\u00f5\5\34\17\2\u00f5" +
                    "\u00f6\7\23\2\2\u00f6\u00fd\3\2\2\2\u00f7\u00f8\f\21\2\2\u00f8\u00f9\7" +
                    "\25\2\2\u00f9\u00fd\7\65\2\2\u00fa\u00fb\f\20\2\2\u00fb\u00fd\t\b\2\2" +
                    "\u00fc\u00c4\3\2\2\2\u00fc\u00c7\3\2\2\2\u00fc\u00ca\3\2\2\2\u00fc\u00cd" +
                    "\3\2\2\2\u00fc\u00d0\3\2\2\2\u00fc\u00d3\3\2\2\2\u00fc\u00d6\3\2\2\2\u00fc" +
                    "\u00d9\3\2\2\2\u00fc\u00dc\3\2\2\2\u00fc\u00df\3\2\2\2\u00fc\u00e2\3\2" +
                    "\2\2\u00fc\u00e5\3\2\2\2\u00fc\u00f2\3\2\2\2\u00fc\u00f7\3\2\2\2\u00fc" +
                    "\u00fa\3\2\2\2\u00fd\u0100\3\2\2\2\u00fe\u00fc\3\2\2\2\u00fe\u00ff\3\2" +
                    "\2\2\u00ff\35\3\2\2\2\u0100\u00fe\3\2\2\2\u0101\u0106\7\66\2\2\u0102\u0106" +
                    "\7\67\2\2\u0103\u0106\t\t\2\2\u0104\u0106\7/\2\2\u0105\u0101\3\2\2\2\u0105" +
                    "\u0102\3\2\2\2\u0105\u0103\3\2\2\2\u0105\u0104\3\2\2\2\u0106\37\3\2\2" +
                    "\2\37#%,.\65;CKT\\hqvz~\u0088\u0090\u0093\u009f\u00a7\u00b7\u00bc\u00c0" +
                    "\u00c2\u00e8\u00ee\u00fc\u00fe\u0105";
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
            "'!='", "'&'", "'^'", "'|'", "'&&'", "'||'", "'true'", "'false'", "'null'",
            "'int'", "'bool'", "'string'", "'void'"
    };
    private static final String[] _SYMBOLIC_NAMES = {
            null, null, null, null, null, null, null, null, null, null, null, null,
            null, null, null, null, null, null, null, null, null, null, null, null,
            null, null, null, null, null, null, null, null, null, null, null, null,
            null, null, null, null, null, null, null, null, null, null, "BUILTIN_INT",
            "BUILTIN_BOOL", "BUILTIN_STRING", "BUILTIN_VOID", "DECORATOR", "IDENTIFIER",
            "INTEGER", "STRING", "NEXTLINE", "LINECOMMENT", "BLOCKCOMMENT", "WHITECHAR"
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
                setState(33);
                _errHandler.sync(this);
                _la = _input.LA(1);
                do {
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
                    setState(35);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
                while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << BUILTIN_INT) | (1L << BUILTIN_BOOL) | (1L << BUILTIN_STRING) | (1L << BUILTIN_VOID) | (1L << IDENTIFIER))) != 0));
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
            enterOuterAlt(_localctx, 1);
            {
                setState(37);
                match(T__0);
                setState(38);
                match(IDENTIFIER);
                setState(39);
                match(T__1);
                setState(44);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BUILTIN_INT) | (1L << BUILTIN_BOOL) | (1L << BUILTIN_STRING) | (1L << BUILTIN_VOID) | (1L << IDENTIFIER))) != 0)) {
                    {
                        setState(42);
                        _errHandler.sync(this);
                        switch (getInterpreter().adaptivePredict(_input, 2, _ctx)) {
                            case 1: {
                                setState(40);
                                functionDeclarationStatement();
                            }
                            break;
                            case 2: {
                                setState(41);
                                variableDeclarationStatement();
                            }
                            break;
                        }
                    }
                    setState(46);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
                setState(47);
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
                setState(49);
                typename();
                setState(51);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == IDENTIFIER) {
                    {
                        setState(50);
                        match(IDENTIFIER);
                    }
                }

                setState(53);
                match(T__3);
                setState(57);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BUILTIN_INT) | (1L << BUILTIN_BOOL) | (1L << BUILTIN_STRING) | (1L << BUILTIN_VOID) | (1L << IDENTIFIER))) != 0)) {
                    {
                        setState(54);
                        typename();
                        setState(55);
                        match(IDENTIFIER);
                    }
                }

                setState(65);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == T__4) {
                    {
                        {
                            setState(59);
                            match(T__4);
                            setState(60);
                            typename();
                            setState(61);
                            match(IDENTIFIER);
                        }
                    }
                    setState(67);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
                setState(68);
                match(T__5);
                setState(69);
                match(T__1);
                setState(73);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__3) | (1L << T__7) | (1L << T__8) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__17) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__42) | (1L << T__43) | (1L << T__44) | (1L << BUILTIN_INT) | (1L << BUILTIN_BOOL) | (1L << BUILTIN_STRING) | (1L << BUILTIN_VOID) | (1L << IDENTIFIER) | (1L << INTEGER) | (1L << STRING))) != 0)) {
                    {
                        {
                            setState(70);
                            statement();
                        }
                    }
                    setState(75);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
                setState(76);
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
                setState(78);
                typename();
                setState(79);
                match(IDENTIFIER);
                setState(82);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == T__6) {
                    {
                        setState(80);
                        match(T__6);
                        setState(81);
                        expression(0);
                    }
                }

                setState(84);
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

    public final BlockStatementContext blockStatement() throws RecognitionException {
        BlockStatementContext _localctx = new BlockStatementContext(_ctx, getState());
        enterRule(_localctx, 8, RULE_blockStatement);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(86);
                match(T__1);
                setState(90);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__3) | (1L << T__7) | (1L << T__8) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__17) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__42) | (1L << T__43) | (1L << T__44) | (1L << BUILTIN_INT) | (1L << BUILTIN_BOOL) | (1L << BUILTIN_STRING) | (1L << BUILTIN_VOID) | (1L << IDENTIFIER) | (1L << INTEGER) | (1L << STRING))) != 0)) {
                    {
                        {
                            setState(87);
                            statement();
                        }
                    }
                    setState(92);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
                setState(93);
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
        enterRule(_localctx, 10, RULE_statement);
        try {
            setState(102);
            _errHandler.sync(this);
            switch (getInterpreter().adaptivePredict(_input, 10, _ctx)) {
                case 1:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(95);
                    blockStatement();
                }
                break;
                case 2:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(96);
                    variableDeclarationStatement();
                }
                break;
                case 3:
                    enterOuterAlt(_localctx, 3);
                {
                    setState(97);
                    selectionStatement();
                }
                break;
                case 4:
                    enterOuterAlt(_localctx, 4);
                {
                    setState(98);
                    iterationStatement();
                }
                break;
                case 5:
                    enterOuterAlt(_localctx, 5);
                {
                    setState(99);
                    controlStatement();
                }
                break;
                case 6:
                    enterOuterAlt(_localctx, 6);
                {
                    setState(100);
                    expressionStatement();
                }
                break;
                case 7:
                    enterOuterAlt(_localctx, 7);
                {
                    setState(101);
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
        enterRule(_localctx, 12, RULE_selectionStatement);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(104);
                match(T__8);
                setState(105);
                match(T__3);
                setState(106);
                expression(0);
                setState(107);
                match(T__5);
                setState(108);
                statement();
                setState(111);
                _errHandler.sync(this);
                switch (getInterpreter().adaptivePredict(_input, 11, _ctx)) {
                    case 1: {
                        setState(109);
                        match(T__9);
                        setState(110);
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
        enterRule(_localctx, 14, RULE_iterationStatement);
        int _la;
        try {
            setState(134);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case T__10:
                    _localctx = new ForStatementContext(_localctx);
                    enterOuterAlt(_localctx, 1);
                {
                    setState(113);
                    match(T__10);
                    setState(114);
                    match(T__3);
                    setState(116);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__17) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__42) | (1L << T__43) | (1L << T__44) | (1L << IDENTIFIER) | (1L << INTEGER) | (1L << STRING))) != 0)) {
                        {
                            setState(115);
                            expression(0);
                        }
                    }

                    setState(118);
                    match(T__7);
                    setState(120);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__17) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__42) | (1L << T__43) | (1L << T__44) | (1L << IDENTIFIER) | (1L << INTEGER) | (1L << STRING))) != 0)) {
                        {
                            setState(119);
                            expression(0);
                        }
                    }

                    setState(122);
                    match(T__7);
                    setState(124);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__17) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__42) | (1L << T__43) | (1L << T__44) | (1L << IDENTIFIER) | (1L << INTEGER) | (1L << STRING))) != 0)) {
                        {
                            setState(123);
                            expression(0);
                        }
                    }

                    setState(126);
                    match(T__5);
                    setState(127);
                    statement();
                }
                break;
                case T__11:
                    _localctx = new WhileStatementContext(_localctx);
                    enterOuterAlt(_localctx, 2);
                {
                    setState(128);
                    match(T__11);
                    setState(129);
                    match(T__3);
                    setState(130);
                    expression(0);
                    setState(131);
                    match(T__5);
                    setState(132);
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
        enterRule(_localctx, 16, RULE_controlStatement);
        int _la;
        try {
            setState(145);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case T__12:
                    _localctx = new ContinueStatementContext(_localctx);
                    enterOuterAlt(_localctx, 1);
                {
                    setState(136);
                    match(T__12);
                    setState(137);
                    match(T__7);
                }
                break;
                case T__13:
                    _localctx = new BreakStatementContext(_localctx);
                    enterOuterAlt(_localctx, 2);
                {
                    setState(138);
                    match(T__13);
                    setState(139);
                    match(T__7);
                }
                break;
                case T__14:
                    _localctx = new ReturnStatementContext(_localctx);
                    enterOuterAlt(_localctx, 3);
                {
                    setState(140);
                    match(T__14);
                    setState(142);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__17) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__42) | (1L << T__43) | (1L << T__44) | (1L << IDENTIFIER) | (1L << INTEGER) | (1L << STRING))) != 0)) {
                        {
                            setState(141);
                            expression(0);
                        }
                    }

                    setState(144);
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
        enterRule(_localctx, 18, RULE_expressionStatement);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(147);
                expression(0);
                setState(148);
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
        enterRule(_localctx, 20, RULE_emptyStatement);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(150);
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
        enterRule(_localctx, 22, RULE_typename);
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                setState(152);
                basetype();
                setState(157);
                _errHandler.sync(this);
                _alt = getInterpreter().adaptivePredict(_input, 18, _ctx);
                while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
                    if (_alt == 1) {
                        {
                            {
                                setState(153);
                                match(T__15);
                                setState(154);
                                match(T__16);
                            }
                        }
                    }
                    setState(159);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input, 18, _ctx);
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

    public final BasetypeContext basetype() throws RecognitionException {
        BasetypeContext _localctx = new BasetypeContext(_ctx, getState());
        enterRule(_localctx, 24, RULE_basetype);
        try {
            setState(165);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case BUILTIN_INT:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(160);
                    ((BasetypeContext) _localctx).type = match(BUILTIN_INT);
                }
                break;
                case BUILTIN_BOOL:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(161);
                    ((BasetypeContext) _localctx).type = match(BUILTIN_BOOL);
                }
                break;
                case BUILTIN_VOID:
                    enterOuterAlt(_localctx, 3);
                {
                    setState(162);
                    ((BasetypeContext) _localctx).type = match(BUILTIN_VOID);
                }
                break;
                case BUILTIN_STRING:
                    enterOuterAlt(_localctx, 4);
                {
                    setState(163);
                    ((BasetypeContext) _localctx).type = match(BUILTIN_STRING);
                }
                break;
                case IDENTIFIER:
                    enterOuterAlt(_localctx, 5);
                {
                    setState(164);
                    match(IDENTIFIER);
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
                setState(192);
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

                        setState(168);
                        constant();
                    }
                    break;
                    case T__17: {
                        _localctx = new ThisExpressionContext(_localctx);
                        _ctx = _localctx;
                        _prevctx = _localctx;
                        setState(169);
                        match(T__17);
                    }
                    break;
                    case IDENTIFIER: {
                        _localctx = new VariableExpressionContext(_localctx);
                        _ctx = _localctx;
                        _prevctx = _localctx;
                        setState(170);
                        match(IDENTIFIER);
                    }
                    break;
                    case T__3: {
                        _localctx = new BraceExpressionContext(_localctx);
                        _ctx = _localctx;
                        _prevctx = _localctx;
                        setState(171);
                        match(T__3);
                        setState(172);
                        expression(0);
                        setState(173);
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
                        setState(175);
                        ((PrefixExpressionContext) _localctx).operator = _input.LT(1);
                        _la = _input.LA(1);
                        if (!((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24))) != 0))) {
                            ((PrefixExpressionContext) _localctx).operator = (Token) _errHandler.recoverInline(this);
                        } else {
                            if (_input.LA(1) == Token.EOF) matchedEOF = true;
                            _errHandler.reportMatch(this);
                            consume();
                        }
                        setState(176);
                        expression(13);
                    }
                    break;
                    case T__25: {
                        _localctx = new NewExpressionContext(_localctx);
                        _ctx = _localctx;
                        _prevctx = _localctx;
                        setState(177);
                        match(T__25);
                        setState(178);
                        typename();
                        setState(190);
                        _errHandler.sync(this);
                        switch (getInterpreter().adaptivePredict(_input, 22, _ctx)) {
                            case 1: {
                                setState(184);
                                _errHandler.sync(this);
                                _alt = 1;
                                do {
                                    switch (_alt) {
                                        case 1: {
                                            {
                                                setState(179);
                                                match(T__15);
                                                setState(181);
                                                _errHandler.sync(this);
                                                _la = _input.LA(1);
                                                if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__17) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__42) | (1L << T__43) | (1L << T__44) | (1L << IDENTIFIER) | (1L << INTEGER) | (1L << STRING))) != 0)) {
                                                    {
                                                        setState(180);
                                                        expression(0);
                                                    }
                                                }

                                                setState(183);
                                                match(T__16);
                                            }
                                        }
                                        break;
                                        default:
                                            throw new NoViableAltException(this);
                                    }
                                    setState(186);
                                    _errHandler.sync(this);
                                    _alt = getInterpreter().adaptivePredict(_input, 21, _ctx);
                                } while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER);
                            }
                            break;
                            case 2: {
                                setState(188);
                                match(T__3);
                                setState(189);
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
                setState(252);
                _errHandler.sync(this);
                _alt = getInterpreter().adaptivePredict(_input, 27, _ctx);
                while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
                    if (_alt == 1) {
                        if (_parseListeners != null) triggerExitRuleEvent();
                        _prevctx = _localctx;
                        {
                            setState(250);
                            _errHandler.sync(this);
                            switch (getInterpreter().adaptivePredict(_input, 26, _ctx)) {
                                case 1: {
                                    _localctx = new BinaryExpressionContext(new ExpressionContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(194);
                                    if (!(precpred(_ctx, 11)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 11)");
                                    setState(195);
                                    ((BinaryExpressionContext) _localctx).operator = _input.LT(1);
                                    _la = _input.LA(1);
                                    if (!((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__26) | (1L << T__27) | (1L << T__28))) != 0))) {
                                        ((BinaryExpressionContext) _localctx).operator = (Token) _errHandler.recoverInline(this);
                                    } else {
                                        if (_input.LA(1) == Token.EOF) matchedEOF = true;
                                        _errHandler.reportMatch(this);
                                        consume();
                                    }
                                    setState(196);
                                    expression(12);
                                }
                                break;
                                case 2: {
                                    _localctx = new BinaryExpressionContext(new ExpressionContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(197);
                                    if (!(precpred(_ctx, 10)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 10)");
                                    setState(198);
                                    ((BinaryExpressionContext) _localctx).operator = _input.LT(1);
                                    _la = _input.LA(1);
                                    if (!(_la == T__22 || _la == T__23)) {
                                        ((BinaryExpressionContext) _localctx).operator = (Token) _errHandler.recoverInline(this);
                                    } else {
                                        if (_input.LA(1) == Token.EOF) matchedEOF = true;
                                        _errHandler.reportMatch(this);
                                        consume();
                                    }
                                    setState(199);
                                    expression(11);
                                }
                                break;
                                case 3: {
                                    _localctx = new BinaryExpressionContext(new ExpressionContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(200);
                                    if (!(precpred(_ctx, 9)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 9)");
                                    setState(201);
                                    ((BinaryExpressionContext) _localctx).operator = _input.LT(1);
                                    _la = _input.LA(1);
                                    if (!(_la == T__29 || _la == T__30)) {
                                        ((BinaryExpressionContext) _localctx).operator = (Token) _errHandler.recoverInline(this);
                                    } else {
                                        if (_input.LA(1) == Token.EOF) matchedEOF = true;
                                        _errHandler.reportMatch(this);
                                        consume();
                                    }
                                    setState(202);
                                    expression(10);
                                }
                                break;
                                case 4: {
                                    _localctx = new BinaryExpressionContext(new ExpressionContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(203);
                                    if (!(precpred(_ctx, 8)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 8)");
                                    setState(204);
                                    ((BinaryExpressionContext) _localctx).operator = _input.LT(1);
                                    _la = _input.LA(1);
                                    if (!((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__31) | (1L << T__32) | (1L << T__33) | (1L << T__34))) != 0))) {
                                        ((BinaryExpressionContext) _localctx).operator = (Token) _errHandler.recoverInline(this);
                                    } else {
                                        if (_input.LA(1) == Token.EOF) matchedEOF = true;
                                        _errHandler.reportMatch(this);
                                        consume();
                                    }
                                    setState(205);
                                    expression(9);
                                }
                                break;
                                case 5: {
                                    _localctx = new BinaryExpressionContext(new ExpressionContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(206);
                                    if (!(precpred(_ctx, 7)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 7)");
                                    setState(207);
                                    ((BinaryExpressionContext) _localctx).operator = _input.LT(1);
                                    _la = _input.LA(1);
                                    if (!(_la == T__35 || _la == T__36)) {
                                        ((BinaryExpressionContext) _localctx).operator = (Token) _errHandler.recoverInline(this);
                                    } else {
                                        if (_input.LA(1) == Token.EOF) matchedEOF = true;
                                        _errHandler.reportMatch(this);
                                        consume();
                                    }
                                    setState(208);
                                    expression(8);
                                }
                                break;
                                case 6: {
                                    _localctx = new BinaryExpressionContext(new ExpressionContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(209);
                                    if (!(precpred(_ctx, 6)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 6)");
                                    setState(210);
                                    ((BinaryExpressionContext) _localctx).operator = match(T__37);
                                    setState(211);
                                    expression(7);
                                }
                                break;
                                case 7: {
                                    _localctx = new BinaryExpressionContext(new ExpressionContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(212);
                                    if (!(precpred(_ctx, 5)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 5)");
                                    setState(213);
                                    ((BinaryExpressionContext) _localctx).operator = match(T__38);
                                    setState(214);
                                    expression(6);
                                }
                                break;
                                case 8: {
                                    _localctx = new BinaryExpressionContext(new ExpressionContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(215);
                                    if (!(precpred(_ctx, 4)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 4)");
                                    setState(216);
                                    ((BinaryExpressionContext) _localctx).operator = match(T__39);
                                    setState(217);
                                    expression(5);
                                }
                                break;
                                case 9: {
                                    _localctx = new BinaryExpressionContext(new ExpressionContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(218);
                                    if (!(precpred(_ctx, 3)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 3)");
                                    setState(219);
                                    ((BinaryExpressionContext) _localctx).operator = match(T__40);
                                    setState(220);
                                    expression(4);
                                }
                                break;
                                case 10: {
                                    _localctx = new BinaryExpressionContext(new ExpressionContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(221);
                                    if (!(precpred(_ctx, 2)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 2)");
                                    setState(222);
                                    ((BinaryExpressionContext) _localctx).operator = match(T__41);
                                    setState(223);
                                    expression(3);
                                }
                                break;
                                case 11: {
                                    _localctx = new BinaryExpressionContext(new ExpressionContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(224);
                                    if (!(precpred(_ctx, 1)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 1)");
                                    setState(225);
                                    ((BinaryExpressionContext) _localctx).operator = match(T__6);
                                    setState(226);
                                    expression(1);
                                }
                                break;
                                case 12: {
                                    _localctx = new FunctionCallExpressionContext(new ExpressionContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(227);
                                    if (!(precpred(_ctx, 17)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 17)");
                                    setState(228);
                                    match(T__3);
                                    setState(230);
                                    _errHandler.sync(this);
                                    _la = _input.LA(1);
                                    if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__17) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__42) | (1L << T__43) | (1L << T__44) | (1L << IDENTIFIER) | (1L << INTEGER) | (1L << STRING))) != 0)) {
                                        {
                                            setState(229);
                                            expression(0);
                                        }
                                    }

                                    setState(236);
                                    _errHandler.sync(this);
                                    _la = _input.LA(1);
                                    while (_la == T__4) {
                                        {
                                            {
                                                setState(232);
                                                match(T__4);
                                                setState(233);
                                                expression(0);
                                            }
                                        }
                                        setState(238);
                                        _errHandler.sync(this);
                                        _la = _input.LA(1);
                                    }
                                    setState(239);
                                    match(T__5);
                                }
                                break;
                                case 13: {
                                    _localctx = new SubscriptExpressionContext(new ExpressionContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(240);
                                    if (!(precpred(_ctx, 16)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 16)");
                                    setState(241);
                                    match(T__15);
                                    setState(242);
                                    expression(0);
                                    setState(243);
                                    match(T__16);
                                }
                                break;
                                case 14: {
                                    _localctx = new FieldExpressionContext(new ExpressionContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(245);
                                    if (!(precpred(_ctx, 15)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 15)");
                                    setState(246);
                                    ((FieldExpressionContext) _localctx).operator = match(T__18);
                                    setState(247);
                                    match(IDENTIFIER);
                                }
                                break;
                                case 15: {
                                    _localctx = new SuffixExpressionContext(new ExpressionContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(248);
                                    if (!(precpred(_ctx, 14)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 14)");
                                    setState(249);
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
                    setState(254);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input, 27, _ctx);
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
            setState(259);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case INTEGER:
                    _localctx = new IntegerConstantContext(_localctx);
                    enterOuterAlt(_localctx, 1);
                {
                    setState(255);
                    match(INTEGER);
                }
                break;
                case STRING:
                    _localctx = new StringConstantContext(_localctx);
                    enterOuterAlt(_localctx, 2);
                {
                    setState(256);
                    match(STRING);
                }
                break;
                case T__42:
                case T__43:
                    _localctx = new BoolConstantContext(_localctx);
                    enterOuterAlt(_localctx, 3);
                {
                    setState(257);
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
                    setState(258);
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

        public BasetypeContext basetype() {
            return getRuleContext(BasetypeContext.class, 0);
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

    public static class BasetypeContext extends ParserRuleContext {
        public Token type;

        public BasetypeContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode BUILTIN_INT() {
            return getToken(MiracleParser.BUILTIN_INT, 0);
        }

        public TerminalNode BUILTIN_BOOL() {
            return getToken(MiracleParser.BUILTIN_BOOL, 0);
        }

        public TerminalNode BUILTIN_VOID() {
            return getToken(MiracleParser.BUILTIN_VOID, 0);
        }

        public TerminalNode BUILTIN_STRING() {
            return getToken(MiracleParser.BUILTIN_STRING, 0);
        }

        public TerminalNode IDENTIFIER() {
            return getToken(MiracleParser.IDENTIFIER, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_basetype;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof MiracleListener) ((MiracleListener) listener).enterBasetype(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof MiracleListener) ((MiracleListener) listener).exitBasetype(this);
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