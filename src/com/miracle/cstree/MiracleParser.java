package com.miracle.cstree;// Generated from Miracle.g4 by ANTLR 4.6

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
            T__45 = 46, T__46 = 47, T__47 = 48, T__48 = 49, T__49 = 50, DECORATOR = 51, IDENTIFIER = 52,
            INTEGER = 53, STRING = 54, NEXTLINE = 55, LINECOMMENT = 56, BLOCKCOMMENT = 57, WHITECHAR = 58;
    public static final int
            RULE_miracle = 0, RULE_classDeclarationStatement = 1, RULE_functionDeclarationStatement = 2,
            RULE_variableDeclarationStatement = 3, RULE_blockStatement = 4, RULE_statement = 5,
            RULE_selectionStatement = 6, RULE_iterationStatement = 7, RULE_controlStatement = 8,
            RULE_expressionStatement = 9, RULE_emptyStatement = 10, RULE_typename = 11,
            RULE_expression = 12, RULE_constant = 13;
    public static final String[] ruleNames = {
            "miracle", "classDeclarationStatement", "functionDeclarationStatement",
            "variableDeclarationStatement", "blockStatement", "statement", "selectionStatement",
            "iterationStatement", "controlStatement", "expressionStatement", "emptyStatement",
            "typename", "expression", "constant"
    };
    /**
     * @deprecated Use {@link #VOCABULARY} instead.
     */
    @Deprecated
    public static final String[] tokenNames;
    public static final String _serializedATN =
            "\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3<\u0105\4\2\t\2\4" +
                    "\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t" +
                    "\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\3\2\3\2\7\2\"\n\2\f\2\16\2" +
                    "%\13\2\3\3\3\3\3\3\3\3\3\3\5\3,\n\3\3\3\5\3/\n\3\3\4\5\4\62\n\4\3\4\3" +
                    "\4\3\4\3\4\3\4\3\4\5\4:\n\4\3\4\3\4\3\4\3\4\7\4@\n\4\f\4\16\4C\13\4\3" +
                    "\4\3\4\3\4\5\4H\n\4\3\5\5\5K\n\5\3\5\3\5\3\5\3\5\5\5Q\n\5\3\5\3\5\3\6" +
                    "\3\6\7\6W\n\6\f\6\16\6Z\13\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7e" +
                    "\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\bn\n\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t" +
                    "\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u0080\n\t\3\n\3\n\3\n\3\n\3\n" +
                    "\3\n\5\n\u0088\n\n\3\n\5\n\u008b\n\n\3\13\3\13\3\13\3\f\3\f\3\r\3\r\3" +
                    "\r\3\r\3\r\3\r\5\r\u0098\n\r\3\r\3\r\6\r\u009c\n\r\r\r\16\r\u009d\7\r" +
                    "\u00a0\n\r\f\r\16\r\u00a3\13\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16" +
                    "\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\7\16\u00b5\n\16\f\16\16\16\u00b8" +
                    "\13\16\3\16\7\16\u00bb\n\16\f\16\16\16\u00be\13\16\5\16\u00c0\n\16\3\16" +
                    "\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16" +
                    "\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16" +
                    "\3\16\3\16\3\16\3\16\3\16\3\16\3\16\5\16\u00e6\n\16\3\16\3\16\7\16\u00ea" +
                    "\n\16\f\16\16\16\u00ed\13\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3" +
                    "\16\3\16\3\16\7\16\u00fa\n\16\f\16\16\16\u00fd\13\16\3\17\3\17\3\17\3" +
                    "\17\5\17\u0103\n\17\3\17\2\4\30\32\20\2\4\6\b\n\f\16\20\22\24\26\30\32" +
                    "\34\2\n\3\2\33\34\3\2\33 \3\2\"$\3\2\36\37\3\2%&\3\2\'*\3\2+,\3\2\62\63" +
                    "\u012e\2#\3\2\2\2\4&\3\2\2\2\6\61\3\2\2\2\bJ\3\2\2\2\nT\3\2\2\2\fd\3\2" +
                    "\2\2\16f\3\2\2\2\20\177\3\2\2\2\22\u008a\3\2\2\2\24\u008c\3\2\2\2\26\u008f" +
                    "\3\2\2\2\30\u0097\3\2\2\2\32\u00bf\3\2\2\2\34\u0102\3\2\2\2\36\"\5\4\3" +
                    "\2\37\"\5\6\4\2 \"\5\b\5\2!\36\3\2\2\2!\37\3\2\2\2! \3\2\2\2\"%\3\2\2" +
                    "\2#!\3\2\2\2#$\3\2\2\2$\3\3\2\2\2%#\3\2\2\2&\'\7\3\2\2\'.\7\66\2\2(/\7" +
                    "\4\2\2)*\7\5\2\2*,\7\66\2\2+)\3\2\2\2+,\3\2\2\2,-\3\2\2\2-/\5\n\6\2.(" +
                    "\3\2\2\2.+\3\2\2\2/\5\3\2\2\2\60\62\7\65\2\2\61\60\3\2\2\2\61\62\3\2\2" +
                    "\2\62\63\3\2\2\2\63\64\5\30\r\2\64\65\7\66\2\2\659\7\6\2\2\66\67\5\30" +
                    "\r\2\678\7\66\2\28:\3\2\2\29\66\3\2\2\29:\3\2\2\2:A\3\2\2\2;<\7\7\2\2" +
                    "<=\5\30\r\2=>\7\66\2\2>@\3\2\2\2?;\3\2\2\2@C\3\2\2\2A?\3\2\2\2AB\3\2\2" +
                    "\2BD\3\2\2\2CA\3\2\2\2DG\7\b\2\2EH\7\4\2\2FH\5\n\6\2GE\3\2\2\2GF\3\2\2" +
                    "\2H\7\3\2\2\2IK\7\65\2\2JI\3\2\2\2JK\3\2\2\2KL\3\2\2\2LM\5\30\r\2MP\7" +
                    "\66\2\2NO\7\t\2\2OQ\5\32\16\2PN\3\2\2\2PQ\3\2\2\2QR\3\2\2\2RS\7\4\2\2" +
                    "S\t\3\2\2\2TX\7\n\2\2UW\5\f\7\2VU\3\2\2\2WZ\3\2\2\2XV\3\2\2\2XY\3\2\2" +
                    "\2Y[\3\2\2\2ZX\3\2\2\2[\\\7\13\2\2\\\13\3\2\2\2]e\5\n\6\2^e\5\b\5\2_e" +
                    "\5\16\b\2`e\5\20\t\2ae\5\22\n\2be\5\24\13\2ce\5\26\f\2d]\3\2\2\2d^\3\2" +
                    "\2\2d_\3\2\2\2d`\3\2\2\2da\3\2\2\2db\3\2\2\2dc\3\2\2\2e\r\3\2\2\2fg\7" +
                    "\f\2\2gh\7\6\2\2hi\5\32\16\2ij\7\b\2\2jm\5\f\7\2kl\7\r\2\2ln\5\f\7\2m" +
                    "k\3\2\2\2mn\3\2\2\2n\17\3\2\2\2op\7\16\2\2pq\7\6\2\2qr\5\32\16\2rs\7\4" +
                    "\2\2st\5\32\16\2tu\7\4\2\2uv\5\32\16\2vw\7\b\2\2wx\5\f\7\2x\u0080\3\2" +
                    "\2\2yz\7\17\2\2z{\7\6\2\2{|\5\32\16\2|}\7\b\2\2}~\5\f\7\2~\u0080\3\2\2" +
                    "\2\177o\3\2\2\2\177y\3\2\2\2\u0080\21\3\2\2\2\u0081\u0082\7\20\2\2\u0082" +
                    "\u008b\7\4\2\2\u0083\u0084\7\21\2\2\u0084\u008b\7\4\2\2\u0085\u0087\7" +
                    "\22\2\2\u0086\u0088\5\32\16\2\u0087\u0086\3\2\2\2\u0087\u0088\3\2\2\2" +
                    "\u0088\u0089\3\2\2\2\u0089\u008b\7\4\2\2\u008a\u0081\3\2\2\2\u008a\u0083" +
                    "\3\2\2\2\u008a\u0085\3\2\2\2\u008b\23\3\2\2\2\u008c\u008d\5\32\16\2\u008d" +
                    "\u008e\7\4\2\2\u008e\25\3\2\2\2\u008f\u0090\7\4\2\2\u0090\27\3\2\2\2\u0091" +
                    "\u0092\b\r\1\2\u0092\u0098\7\23\2\2\u0093\u0098\7\24\2\2\u0094\u0098\7" +
                    "\25\2\2\u0095\u0098\7\26\2\2\u0096\u0098\7\66\2\2\u0097\u0091\3\2\2\2" +
                    "\u0097\u0093\3\2\2\2\u0097\u0094\3\2\2\2\u0097\u0095\3\2\2\2\u0097\u0096" +
                    "\3\2\2\2\u0098\u00a1\3\2\2\2\u0099\u009b\f\3\2\2\u009a\u009c\7\27\2\2" +
                    "\u009b\u009a\3\2\2\2\u009c\u009d\3\2\2\2\u009d\u009b\3\2\2\2\u009d\u009e" +
                    "\3\2\2\2\u009e\u00a0\3\2\2\2\u009f\u0099\3\2\2\2\u00a0\u00a3\3\2\2\2\u00a1" +
                    "\u009f\3\2\2\2\u00a1\u00a2\3\2\2\2\u00a2\31\3\2\2\2\u00a3\u00a1\3\2\2" +
                    "\2\u00a4\u00a5\b\16\1\2\u00a5\u00c0\5\34\17\2\u00a6\u00c0\7\66\2\2\u00a7" +
                    "\u00a8\7\6\2\2\u00a8\u00a9\5\32\16\2\u00a9\u00aa\7\b\2\2\u00aa\u00c0\3" +
                    "\2\2\2\u00ab\u00ac\t\2\2\2\u00ac\u00c0\5\32\16\20\u00ad\u00c0\t\3\2\2" +
                    "\u00ae\u00af\7!\2\2\u00af\u00b6\5\30\r\2\u00b0\u00b1\7\30\2\2\u00b1\u00b2" +
                    "\5\32\16\2\u00b2\u00b3\7\31\2\2\u00b3\u00b5\3\2\2\2\u00b4\u00b0\3\2\2" +
                    "\2\u00b5\u00b8\3\2\2\2\u00b6\u00b4\3\2\2\2\u00b6\u00b7\3\2\2\2\u00b7\u00bc" +
                    "\3\2\2\2\u00b8\u00b6\3\2\2\2\u00b9\u00bb\7\27\2\2\u00ba\u00b9\3\2\2\2" +
                    "\u00bb\u00be\3\2\2\2\u00bc\u00ba\3\2\2\2\u00bc\u00bd\3\2\2\2\u00bd\u00c0" +
                    "\3\2\2\2\u00be\u00bc\3\2\2\2\u00bf\u00a4\3\2\2\2\u00bf\u00a6\3\2\2\2\u00bf" +
                    "\u00a7\3\2\2\2\u00bf\u00ab\3\2\2\2\u00bf\u00ad\3\2\2\2\u00bf\u00ae\3\2" +
                    "\2\2\u00c0\u00fb\3\2\2\2\u00c1\u00c2\f\r\2\2\u00c2\u00c3\t\4\2\2\u00c3" +
                    "\u00fa\5\32\16\16\u00c4\u00c5\f\f\2\2\u00c5\u00c6\t\5\2\2\u00c6\u00fa" +
                    "\5\32\16\r\u00c7\u00c8\f\13\2\2\u00c8\u00c9\t\6\2\2\u00c9\u00fa\5\32\16" +
                    "\f\u00ca\u00cb\f\n\2\2\u00cb\u00cc\t\7\2\2\u00cc\u00fa\5\32\16\13\u00cd" +
                    "\u00ce\f\t\2\2\u00ce\u00cf\t\b\2\2\u00cf\u00fa\5\32\16\n\u00d0\u00d1\f" +
                    "\b\2\2\u00d1\u00d2\7-\2\2\u00d2\u00fa\5\32\16\t\u00d3\u00d4\f\7\2\2\u00d4" +
                    "\u00d5\7.\2\2\u00d5\u00fa\5\32\16\b\u00d6\u00d7\f\6\2\2\u00d7\u00d8\7" +
                    "/\2\2\u00d8\u00fa\5\32\16\7\u00d9\u00da\f\5\2\2\u00da\u00db\7\60\2\2\u00db" +
                    "\u00fa\5\32\16\6\u00dc\u00dd\f\4\2\2\u00dd\u00de\7\61\2\2\u00de\u00fa" +
                    "\5\32\16\5\u00df\u00e0\f\3\2\2\u00e0\u00e1\7\t\2\2\u00e1\u00fa\5\32\16" +
                    "\3\u00e2\u00e3\f\24\2\2\u00e3\u00e5\7\6\2\2\u00e4\u00e6\5\32\16\2\u00e5" +
                    "\u00e4\3\2\2\2\u00e5\u00e6\3\2\2\2\u00e6\u00eb\3\2\2\2\u00e7\u00e8\7\7" +
                    "\2\2\u00e8\u00ea\5\32\16\2\u00e9\u00e7\3\2\2\2\u00ea\u00ed\3\2\2\2\u00eb" +
                    "\u00e9\3\2\2\2\u00eb\u00ec\3\2\2\2\u00ec\u00ee\3\2\2\2\u00ed\u00eb\3\2" +
                    "\2\2\u00ee\u00fa\7\b\2\2\u00ef\u00f0\f\23\2\2\u00f0\u00f1\7\30\2\2\u00f1" +
                    "\u00f2\5\32\16\2\u00f2\u00f3\7\31\2\2\u00f3\u00fa\3\2\2\2\u00f4\u00f5" +
                    "\f\22\2\2\u00f5\u00f6\7\32\2\2\u00f6\u00fa\7\66\2\2\u00f7\u00f8\f\21\2" +
                    "\2\u00f8\u00fa\t\2\2\2\u00f9\u00c1\3\2\2\2\u00f9\u00c4\3\2\2\2\u00f9\u00c7" +
                    "\3\2\2\2\u00f9\u00ca\3\2\2\2\u00f9\u00cd\3\2\2\2\u00f9\u00d0\3\2\2\2\u00f9" +
                    "\u00d3\3\2\2\2\u00f9\u00d6\3\2\2\2\u00f9\u00d9\3\2\2\2\u00f9\u00dc\3\2" +
                    "\2\2\u00f9\u00df\3\2\2\2\u00f9\u00e2\3\2\2\2\u00f9\u00ef\3\2\2\2\u00f9" +
                    "\u00f4\3\2\2\2\u00f9\u00f7\3\2\2\2\u00fa\u00fd\3\2\2\2\u00fb\u00f9\3\2" +
                    "\2\2\u00fb\u00fc\3\2\2\2\u00fc\33\3\2\2\2\u00fd\u00fb\3\2\2\2\u00fe\u0103" +
                    "\7\67\2\2\u00ff\u0103\78\2\2\u0100\u0103\t\t\2\2\u0101\u0103\7\64\2\2" +
                    "\u0102\u00fe\3\2\2\2\u0102\u00ff\3\2\2\2\u0102\u0100\3\2\2\2\u0102\u0101" +
                    "\3\2\2\2\u0103\35\3\2\2\2\35!#+.\619AGJPXdm\177\u0087\u008a\u0097\u009d" +
                    "\u00a1\u00b6\u00bc\u00bf\u00e5\u00eb\u00f9\u00fb\u0102";
    public static final ATN _ATN =
            new ATNDeserializer().deserialize(_serializedATN.toCharArray());
    protected static final DFA[] _decisionToDFA;
    protected static final PredictionContextCache _sharedContextCache =
            new PredictionContextCache();
    private static final String[] _LITERAL_NAMES = {
            null, "'class'", "';'", "'extends'", "'('", "','", "')'", "'='", "'{'",
            "'}'", "'if'", "'else'", "'for'", "'while'", "'continue'", "'break'",
            "'return'", "'void'", "'int'", "'bool'", "'string'", "'[]'", "'['", "']'",
            "'.'", "'++'", "'--'", "'!'", "'+'", "'-'", "'~'", "'new'", "'*'", "'/'",
            "'%'", "'<<'", "'>>'", "'<'", "'<='", "'>'", "'>='", "'=='", "'!='", "'&'",
            "'^'", "'|'", "'&&'", "'||'", "'true'", "'false'", "'null'"
    };
    private static final String[] _SYMBOLIC_NAMES = {
            null, null, null, null, null, null, null, null, null, null, null, null,
            null, null, null, null, null, null, null, null, null, null, null, null,
            null, null, null, null, null, null, null, null, null, null, null, null,
            null, null, null, null, null, null, null, null, null, null, null, null,
            null, null, null, "DECORATOR", "IDENTIFIER", "INTEGER", "STRING", "NEXTLINE",
            "LINECOMMENT", "BLOCKCOMMENT", "WHITECHAR"
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
                while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << DECORATOR) | (1L << IDENTIFIER))) != 0)) {
                    {
                        setState(31);
                        _errHandler.sync(this);
                        switch (getInterpreter().adaptivePredict(_input, 0, _ctx)) {
                            case 1: {
                                setState(28);
                                classDeclarationStatement();
                            }
                            break;
                            case 2: {
                                setState(29);
                                functionDeclarationStatement();
                            }
                            break;
                            case 3: {
                                setState(30);
                                variableDeclarationStatement();
                            }
                            break;
                        }
                    }
                    setState(35);
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
            enterOuterAlt(_localctx, 1);
            {
                setState(36);
                match(T__0);
                setState(37);
                match(IDENTIFIER);
                setState(44);
                _errHandler.sync(this);
                switch (_input.LA(1)) {
                    case T__1: {
                        setState(38);
                        match(T__1);
                    }
                    break;
                    case T__2:
                    case T__7: {
                        {
                            setState(41);
                            _errHandler.sync(this);
                            _la = _input.LA(1);
                            if (_la == T__2) {
                                {
                                    setState(39);
                                    match(T__2);
                                    setState(40);
                                    match(IDENTIFIER);
                                }
                            }

                            setState(43);
                            blockStatement();
                        }
                    }
                    break;
                    default:
                        throw new NoViableAltException(this);
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

    public final FunctionDeclarationStatementContext functionDeclarationStatement() throws RecognitionException {
        FunctionDeclarationStatementContext _localctx = new FunctionDeclarationStatementContext(_ctx, getState());
        enterRule(_localctx, 4, RULE_functionDeclarationStatement);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(47);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == DECORATOR) {
                    {
                        setState(46);
                        match(DECORATOR);
                    }
                }

                setState(49);
                typename(0);
                setState(50);
                match(IDENTIFIER);
                setState(51);
                match(T__3);
                setState(55);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << IDENTIFIER))) != 0)) {
                    {
                        setState(52);
                        typename(0);
                        setState(53);
                        match(IDENTIFIER);
                    }
                }

                setState(63);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == T__4) {
                    {
                        {
                            setState(57);
                            match(T__4);
                            setState(58);
                            typename(0);
                            setState(59);
                            match(IDENTIFIER);
                        }
                    }
                    setState(65);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
                setState(66);
                match(T__5);
                setState(69);
                _errHandler.sync(this);
                switch (_input.LA(1)) {
                    case T__1: {
                        setState(67);
                        match(T__1);
                    }
                    break;
                    case T__7: {
                        setState(68);
                        blockStatement();
                    }
                    break;
                    default:
                        throw new NoViableAltException(this);
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

    public final VariableDeclarationStatementContext variableDeclarationStatement() throws RecognitionException {
        VariableDeclarationStatementContext _localctx = new VariableDeclarationStatementContext(_ctx, getState());
        enterRule(_localctx, 6, RULE_variableDeclarationStatement);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(72);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == DECORATOR) {
                    {
                        setState(71);
                        match(DECORATOR);
                    }
                }

                setState(74);
                typename(0);
                setState(75);
                match(IDENTIFIER);
                setState(78);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == T__6) {
                    {
                        setState(76);
                        match(T__6);
                        setState(77);
                        expression(0);
                    }
                }

                setState(80);
                match(T__1);
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
                setState(82);
                match(T__7);
                setState(86);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__3) | (1L << T__7) | (1L << T__9) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__24) | (1L << T__25) | (1L << T__26) | (1L << T__27) | (1L << T__28) | (1L << T__29) | (1L << T__30) | (1L << T__47) | (1L << T__48) | (1L << T__49) | (1L << DECORATOR) | (1L << IDENTIFIER) | (1L << INTEGER) | (1L << STRING))) != 0)) {
                    {
                        {
                            setState(83);
                            statement();
                        }
                    }
                    setState(88);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
                setState(89);
                match(T__8);
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
            setState(98);
            _errHandler.sync(this);
            switch (getInterpreter().adaptivePredict(_input, 11, _ctx)) {
                case 1:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(91);
                    blockStatement();
                }
                break;
                case 2:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(92);
                    variableDeclarationStatement();
                }
                break;
                case 3:
                    enterOuterAlt(_localctx, 3);
                {
                    setState(93);
                    selectionStatement();
                }
                break;
                case 4:
                    enterOuterAlt(_localctx, 4);
                {
                    setState(94);
                    iterationStatement();
                }
                break;
                case 5:
                    enterOuterAlt(_localctx, 5);
                {
                    setState(95);
                    controlStatement();
                }
                break;
                case 6:
                    enterOuterAlt(_localctx, 6);
                {
                    setState(96);
                    expressionStatement();
                }
                break;
                case 7:
                    enterOuterAlt(_localctx, 7);
                {
                    setState(97);
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
                setState(100);
                match(T__9);
                setState(101);
                match(T__3);
                setState(102);
                expression(0);
                setState(103);
                match(T__5);
                setState(104);
                statement();
                setState(107);
                _errHandler.sync(this);
                switch (getInterpreter().adaptivePredict(_input, 12, _ctx)) {
                    case 1: {
                        setState(105);
                        match(T__10);
                        setState(106);
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
        try {
            setState(125);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case T__11:
                    _localctx = new ForStatementContext(_localctx);
                    enterOuterAlt(_localctx, 1);
                {
                    setState(109);
                    match(T__11);
                    setState(110);
                    match(T__3);
                    setState(111);
                    expression(0);
                    setState(112);
                    match(T__1);
                    setState(113);
                    expression(0);
                    setState(114);
                    match(T__1);
                    setState(115);
                    expression(0);
                    setState(116);
                    match(T__5);
                    setState(117);
                    statement();
                }
                break;
                case T__12:
                    _localctx = new WhileStatementContext(_localctx);
                    enterOuterAlt(_localctx, 2);
                {
                    setState(119);
                    match(T__12);
                    setState(120);
                    match(T__3);
                    setState(121);
                    expression(0);
                    setState(122);
                    match(T__5);
                    setState(123);
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
            setState(136);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case T__13:
                    _localctx = new ContinueStatementContext(_localctx);
                    enterOuterAlt(_localctx, 1);
                {
                    setState(127);
                    match(T__13);
                    setState(128);
                    match(T__1);
                }
                break;
                case T__14:
                    _localctx = new BreakStatementContext(_localctx);
                    enterOuterAlt(_localctx, 2);
                {
                    setState(129);
                    match(T__14);
                    setState(130);
                    match(T__1);
                }
                break;
                case T__15:
                    _localctx = new ReturnStatementContext(_localctx);
                    enterOuterAlt(_localctx, 3);
                {
                    setState(131);
                    match(T__15);
                    setState(133);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__24) | (1L << T__25) | (1L << T__26) | (1L << T__27) | (1L << T__28) | (1L << T__29) | (1L << T__30) | (1L << T__47) | (1L << T__48) | (1L << T__49) | (1L << IDENTIFIER) | (1L << INTEGER) | (1L << STRING))) != 0)) {
                        {
                            setState(132);
                            expression(0);
                        }
                    }

                    setState(135);
                    match(T__1);
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
                setState(138);
                expression(0);
                setState(139);
                match(T__1);
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
                setState(141);
                match(T__1);
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
        return typename(0);
    }

    private TypenameContext typename(int _p) throws RecognitionException {
        ParserRuleContext _parentctx = _ctx;
        int _parentState = getState();
        TypenameContext _localctx = new TypenameContext(_ctx, _parentState);
        TypenameContext _prevctx = _localctx;
        int _startState = 22;
        enterRecursionRule(_localctx, 22, RULE_typename, _p);
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                setState(149);
                _errHandler.sync(this);
                switch (_input.LA(1)) {
                    case T__16: {
                        setState(144);
                        match(T__16);
                    }
                    break;
                    case T__17: {
                        setState(145);
                        match(T__17);
                    }
                    break;
                    case T__18: {
                        setState(146);
                        match(T__18);
                    }
                    break;
                    case T__19: {
                        setState(147);
                        match(T__19);
                    }
                    break;
                    case IDENTIFIER: {
                        setState(148);
                        match(IDENTIFIER);
                    }
                    break;
                    default:
                        throw new NoViableAltException(this);
                }
                _ctx.stop = _input.LT(-1);
                setState(159);
                _errHandler.sync(this);
                _alt = getInterpreter().adaptivePredict(_input, 18, _ctx);
                while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
                    if (_alt == 1) {
                        if (_parseListeners != null) triggerExitRuleEvent();
                        _prevctx = _localctx;
                        {
                            {
                                _localctx = new TypenameContext(_parentctx, _parentState);
                                pushNewRecursionContext(_localctx, _startState, RULE_typename);
                                setState(151);
                                if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
                                setState(153);
                                _errHandler.sync(this);
                                _alt = 1;
                                do {
                                    switch (_alt) {
                                        case 1: {
                                            {
                                                setState(152);
                                                match(T__20);
                                            }
                                        }
                                        break;
                                        default:
                                            throw new NoViableAltException(this);
                                    }
                                    setState(155);
                                    _errHandler.sync(this);
                                    _alt = getInterpreter().adaptivePredict(_input, 17, _ctx);
                                } while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER);
                            }
                        }
                    }
                    setState(161);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input, 18, _ctx);
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

    public final ExpressionContext expression() throws RecognitionException {
        return expression(0);
    }

    private ExpressionContext expression(int _p) throws RecognitionException {
        ParserRuleContext _parentctx = _ctx;
        int _parentState = getState();
        ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
        ExpressionContext _prevctx = _localctx;
        int _startState = 24;
        enterRecursionRule(_localctx, 24, RULE_expression, _p);
        int _la;
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                setState(189);
                _errHandler.sync(this);
                switch (getInterpreter().adaptivePredict(_input, 21, _ctx)) {
                    case 1: {
                        _localctx = new ConstantExpressionContext(_localctx);
                        _ctx = _localctx;
                        _prevctx = _localctx;

                        setState(163);
                        constant();
                    }
                    break;
                    case 2: {
                        _localctx = new VariableExpressionContext(_localctx);
                        _ctx = _localctx;
                        _prevctx = _localctx;
                        setState(164);
                        match(IDENTIFIER);
                    }
                    break;
                    case 3: {
                        _localctx = new BraceExpressionContext(_localctx);
                        _ctx = _localctx;
                        _prevctx = _localctx;
                        setState(165);
                        match(T__3);
                        setState(166);
                        expression(0);
                        setState(167);
                        match(T__5);
                    }
                    break;
                    case 4: {
                        _localctx = new PrefixExpressionContext(_localctx);
                        _ctx = _localctx;
                        _prevctx = _localctx;
                        setState(169);
                        ((PrefixExpressionContext) _localctx).operator = _input.LT(1);
                        _la = _input.LA(1);
                        if (!(_la == T__24 || _la == T__25)) {
                            ((PrefixExpressionContext) _localctx).operator = (Token) _errHandler.recoverInline(this);
                        } else {
                            if (_input.LA(1) == Token.EOF) matchedEOF = true;
                            _errHandler.reportMatch(this);
                            consume();
                        }
                        setState(170);
                        expression(14);
                    }
                    break;
                    case 5: {
                        _localctx = new UnaryExpressionContext(_localctx);
                        _ctx = _localctx;
                        _prevctx = _localctx;
                        setState(171);
                        ((UnaryExpressionContext) _localctx).operator = _input.LT(1);
                        _la = _input.LA(1);
                        if (!((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__24) | (1L << T__25) | (1L << T__26) | (1L << T__27) | (1L << T__28) | (1L << T__29))) != 0))) {
                            ((UnaryExpressionContext) _localctx).operator = (Token) _errHandler.recoverInline(this);
                        } else {
                            if (_input.LA(1) == Token.EOF) matchedEOF = true;
                            _errHandler.reportMatch(this);
                            consume();
                        }
                    }
                    break;
                    case 6: {
                        _localctx = new NewExpressionContext(_localctx);
                        _ctx = _localctx;
                        _prevctx = _localctx;
                        setState(172);
                        match(T__30);
                        setState(173);
                        typename(0);
                        setState(180);
                        _errHandler.sync(this);
                        _alt = getInterpreter().adaptivePredict(_input, 19, _ctx);
                        while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
                            if (_alt == 1) {
                                {
                                    {
                                        setState(174);
                                        match(T__21);
                                        setState(175);
                                        expression(0);
                                        setState(176);
                                        match(T__22);
                                    }
                                }
                            }
                            setState(182);
                            _errHandler.sync(this);
                            _alt = getInterpreter().adaptivePredict(_input, 19, _ctx);
                        }
                        setState(186);
                        _errHandler.sync(this);
                        _alt = getInterpreter().adaptivePredict(_input, 20, _ctx);
                        while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
                            if (_alt == 1) {
                                {
                                    {
                                        setState(183);
                                        match(T__20);
                                    }
                                }
                            }
                            setState(188);
                            _errHandler.sync(this);
                            _alt = getInterpreter().adaptivePredict(_input, 20, _ctx);
                        }
                    }
                    break;
                }
                _ctx.stop = _input.LT(-1);
                setState(249);
                _errHandler.sync(this);
                _alt = getInterpreter().adaptivePredict(_input, 25, _ctx);
                while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
                    if (_alt == 1) {
                        if (_parseListeners != null) triggerExitRuleEvent();
                        _prevctx = _localctx;
                        {
                            setState(247);
                            _errHandler.sync(this);
                            switch (getInterpreter().adaptivePredict(_input, 24, _ctx)) {
                                case 1: {
                                    _localctx = new MultDivExpressionContext(new ExpressionContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(191);
                                    if (!(precpred(_ctx, 11)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 11)");
                                    setState(192);
                                    ((MultDivExpressionContext) _localctx).operator = _input.LT(1);
                                    _la = _input.LA(1);
                                    if (!((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__31) | (1L << T__32) | (1L << T__33))) != 0))) {
                                        ((MultDivExpressionContext) _localctx).operator = (Token) _errHandler.recoverInline(this);
                                    } else {
                                        if (_input.LA(1) == Token.EOF) matchedEOF = true;
                                        _errHandler.reportMatch(this);
                                        consume();
                                    }
                                    setState(193);
                                    expression(12);
                                }
                                break;
                                case 2: {
                                    _localctx = new AddSubExpressionContext(new ExpressionContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(194);
                                    if (!(precpred(_ctx, 10)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 10)");
                                    setState(195);
                                    ((AddSubExpressionContext) _localctx).operator = _input.LT(1);
                                    _la = _input.LA(1);
                                    if (!(_la == T__27 || _la == T__28)) {
                                        ((AddSubExpressionContext) _localctx).operator = (Token) _errHandler.recoverInline(this);
                                    } else {
                                        if (_input.LA(1) == Token.EOF) matchedEOF = true;
                                        _errHandler.reportMatch(this);
                                        consume();
                                    }
                                    setState(196);
                                    expression(11);
                                }
                                break;
                                case 3: {
                                    _localctx = new ShlShrExpressionContext(new ExpressionContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(197);
                                    if (!(precpred(_ctx, 9)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 9)");
                                    setState(198);
                                    ((ShlShrExpressionContext) _localctx).operator = _input.LT(1);
                                    _la = _input.LA(1);
                                    if (!(_la == T__34 || _la == T__35)) {
                                        ((ShlShrExpressionContext) _localctx).operator = (Token) _errHandler.recoverInline(this);
                                    } else {
                                        if (_input.LA(1) == Token.EOF) matchedEOF = true;
                                        _errHandler.reportMatch(this);
                                        consume();
                                    }
                                    setState(199);
                                    expression(10);
                                }
                                break;
                                case 4: {
                                    _localctx = new CompareExpressionContext(new ExpressionContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(200);
                                    if (!(precpred(_ctx, 8)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 8)");
                                    setState(201);
                                    ((CompareExpressionContext) _localctx).operator = _input.LT(1);
                                    _la = _input.LA(1);
                                    if (!((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__36) | (1L << T__37) | (1L << T__38) | (1L << T__39))) != 0))) {
                                        ((CompareExpressionContext) _localctx).operator = (Token) _errHandler.recoverInline(this);
                                    } else {
                                        if (_input.LA(1) == Token.EOF) matchedEOF = true;
                                        _errHandler.reportMatch(this);
                                        consume();
                                    }
                                    setState(202);
                                    expression(9);
                                }
                                break;
                                case 5: {
                                    _localctx = new EqualityExpressionContext(new ExpressionContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(203);
                                    if (!(precpred(_ctx, 7)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 7)");
                                    setState(204);
                                    ((EqualityExpressionContext) _localctx).operator = _input.LT(1);
                                    _la = _input.LA(1);
                                    if (!(_la == T__40 || _la == T__41)) {
                                        ((EqualityExpressionContext) _localctx).operator = (Token) _errHandler.recoverInline(this);
                                    } else {
                                        if (_input.LA(1) == Token.EOF) matchedEOF = true;
                                        _errHandler.reportMatch(this);
                                        consume();
                                    }
                                    setState(205);
                                    expression(8);
                                }
                                break;
                                case 6: {
                                    _localctx = new AndExpressionContext(new ExpressionContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(206);
                                    if (!(precpred(_ctx, 6)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 6)");
                                    setState(207);
                                    match(T__42);
                                    setState(208);
                                    expression(7);
                                }
                                break;
                                case 7: {
                                    _localctx = new XorExpressionContext(new ExpressionContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(209);
                                    if (!(precpred(_ctx, 5)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 5)");
                                    setState(210);
                                    match(T__43);
                                    setState(211);
                                    expression(6);
                                }
                                break;
                                case 8: {
                                    _localctx = new OrExpressionContext(new ExpressionContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(212);
                                    if (!(precpred(_ctx, 4)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 4)");
                                    setState(213);
                                    match(T__44);
                                    setState(214);
                                    expression(5);
                                }
                                break;
                                case 9: {
                                    _localctx = new LogicAndExpressionContext(new ExpressionContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(215);
                                    if (!(precpred(_ctx, 3)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 3)");
                                    setState(216);
                                    match(T__45);
                                    setState(217);
                                    expression(4);
                                }
                                break;
                                case 10: {
                                    _localctx = new LogicOrExpressionContext(new ExpressionContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(218);
                                    if (!(precpred(_ctx, 2)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 2)");
                                    setState(219);
                                    match(T__46);
                                    setState(220);
                                    expression(3);
                                }
                                break;
                                case 11: {
                                    _localctx = new AssignExpressionContext(new ExpressionContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(221);
                                    if (!(precpred(_ctx, 1)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 1)");
                                    setState(222);
                                    match(T__6);
                                    setState(223);
                                    expression(1);
                                }
                                break;
                                case 12: {
                                    _localctx = new FunctionCallExpressionContext(new ExpressionContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(224);
                                    if (!(precpred(_ctx, 18)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 18)");
                                    setState(225);
                                    match(T__3);
                                    setState(227);
                                    _errHandler.sync(this);
                                    _la = _input.LA(1);
                                    if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__24) | (1L << T__25) | (1L << T__26) | (1L << T__27) | (1L << T__28) | (1L << T__29) | (1L << T__30) | (1L << T__47) | (1L << T__48) | (1L << T__49) | (1L << IDENTIFIER) | (1L << INTEGER) | (1L << STRING))) != 0)) {
                                        {
                                            setState(226);
                                            expression(0);
                                        }
                                    }

                                    setState(233);
                                    _errHandler.sync(this);
                                    _la = _input.LA(1);
                                    while (_la == T__4) {
                                        {
                                            {
                                                setState(229);
                                                match(T__4);
                                                setState(230);
                                                expression(0);
                                            }
                                        }
                                        setState(235);
                                        _errHandler.sync(this);
                                        _la = _input.LA(1);
                                    }
                                    setState(236);
                                    match(T__5);
                                }
                                break;
                                case 13: {
                                    _localctx = new SubscriptExpressionContext(new ExpressionContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(237);
                                    if (!(precpred(_ctx, 17)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 17)");
                                    setState(238);
                                    match(T__21);
                                    setState(239);
                                    expression(0);
                                    setState(240);
                                    match(T__22);
                                }
                                break;
                                case 14: {
                                    _localctx = new MemberExpressionContext(new ExpressionContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(242);
                                    if (!(precpred(_ctx, 16)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 16)");
                                    setState(243);
                                    match(T__23);
                                    setState(244);
                                    match(IDENTIFIER);
                                }
                                break;
                                case 15: {
                                    _localctx = new SuffixExpressionContext(new ExpressionContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(245);
                                    if (!(precpred(_ctx, 15)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 15)");
                                    setState(246);
                                    ((SuffixExpressionContext) _localctx).operator = _input.LT(1);
                                    _la = _input.LA(1);
                                    if (!(_la == T__24 || _la == T__25)) {
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
                    setState(251);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input, 25, _ctx);
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
        enterRule(_localctx, 26, RULE_constant);
        int _la;
        try {
            setState(256);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case INTEGER:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(252);
                    match(INTEGER);
                }
                break;
                case STRING:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(253);
                    match(STRING);
                }
                break;
                case T__47:
                case T__48:
                    enterOuterAlt(_localctx, 3);
                {
                    setState(254);
                    _la = _input.LA(1);
                    if (!(_la == T__47 || _la == T__48)) {
                        _errHandler.recoverInline(this);
                    } else {
                        if (_input.LA(1) == Token.EOF) matchedEOF = true;
                        _errHandler.reportMatch(this);
                        consume();
                    }
                }
                break;
                case T__49:
                    enterOuterAlt(_localctx, 4);
                {
                    setState(255);
                    match(T__49);
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
            case 11:
                return typename_sempred((TypenameContext) _localctx, predIndex);
            case 12:
                return expression_sempred((ExpressionContext) _localctx, predIndex);
        }
        return true;
    }

    private boolean typename_sempred(TypenameContext _localctx, int predIndex) {
        switch (predIndex) {
            case 0:
                return precpred(_ctx, 1);
        }
        return true;
    }

    private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
        switch (predIndex) {
            case 1:
                return precpred(_ctx, 11);
            case 2:
                return precpred(_ctx, 10);
            case 3:
                return precpred(_ctx, 9);
            case 4:
                return precpred(_ctx, 8);
            case 5:
                return precpred(_ctx, 7);
            case 6:
                return precpred(_ctx, 6);
            case 7:
                return precpred(_ctx, 5);
            case 8:
                return precpred(_ctx, 4);
            case 9:
                return precpred(_ctx, 3);
            case 10:
                return precpred(_ctx, 2);
            case 11:
                return precpred(_ctx, 1);
            case 12:
                return precpred(_ctx, 18);
            case 13:
                return precpred(_ctx, 17);
            case 14:
                return precpred(_ctx, 16);
            case 15:
                return precpred(_ctx, 15);
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

        public List<TerminalNode> IDENTIFIER() {
            return getTokens(MiracleParser.IDENTIFIER);
        }

        public TerminalNode IDENTIFIER(int i) {
            return getToken(MiracleParser.IDENTIFIER, i);
        }

        public BlockStatementContext blockStatement() {
            return getRuleContext(BlockStatementContext.class, 0);
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

        public BlockStatementContext blockStatement() {
            return getRuleContext(BlockStatementContext.class, 0);
        }

        public TerminalNode DECORATOR() {
            return getToken(MiracleParser.DECORATOR, 0);
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

        public TerminalNode DECORATOR() {
            return getToken(MiracleParser.DECORATOR, 0);
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

        public List<ExpressionContext> expression() {
            return getRuleContexts(ExpressionContext.class);
        }

        public ExpressionContext expression(int i) {
            return getRuleContext(ExpressionContext.class, i);
        }

        public StatementContext statement() {
            return getRuleContext(StatementContext.class, 0);
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

        public TerminalNode IDENTIFIER() {
            return getToken(MiracleParser.IDENTIFIER, 0);
        }

        public TypenameContext typename() {
            return getRuleContext(TypenameContext.class, 0);
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

    public static class XorExpressionContext extends ExpressionContext {
        public XorExpressionContext(ExpressionContext ctx) {
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
            if (listener instanceof MiracleListener) ((MiracleListener) listener).enterXorExpression(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof MiracleListener) ((MiracleListener) listener).exitXorExpression(this);
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

    public static class AssignExpressionContext extends ExpressionContext {
        public AssignExpressionContext(ExpressionContext ctx) {
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
            if (listener instanceof MiracleListener) ((MiracleListener) listener).enterAssignExpression(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof MiracleListener) ((MiracleListener) listener).exitAssignExpression(this);
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

    public static class MultDivExpressionContext extends ExpressionContext {
        public Token operator;

        public MultDivExpressionContext(ExpressionContext ctx) {
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
            if (listener instanceof MiracleListener) ((MiracleListener) listener).enterMultDivExpression(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof MiracleListener) ((MiracleListener) listener).exitMultDivExpression(this);
        }
    }

    public static class MemberExpressionContext extends ExpressionContext {
        public MemberExpressionContext(ExpressionContext ctx) {
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
            if (listener instanceof MiracleListener) ((MiracleListener) listener).enterMemberExpression(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof MiracleListener) ((MiracleListener) listener).exitMemberExpression(this);
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

    public static class CompareExpressionContext extends ExpressionContext {
        public Token operator;

        public CompareExpressionContext(ExpressionContext ctx) {
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
            if (listener instanceof MiracleListener) ((MiracleListener) listener).enterCompareExpression(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof MiracleListener) ((MiracleListener) listener).exitCompareExpression(this);
        }
    }

    public static class OrExpressionContext extends ExpressionContext {
        public OrExpressionContext(ExpressionContext ctx) {
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
            if (listener instanceof MiracleListener) ((MiracleListener) listener).enterOrExpression(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof MiracleListener) ((MiracleListener) listener).exitOrExpression(this);
        }
    }

    public static class AndExpressionContext extends ExpressionContext {
        public AndExpressionContext(ExpressionContext ctx) {
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
            if (listener instanceof MiracleListener) ((MiracleListener) listener).enterAndExpression(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof MiracleListener) ((MiracleListener) listener).exitAndExpression(this);
        }
    }

    public static class LogicAndExpressionContext extends ExpressionContext {
        public LogicAndExpressionContext(ExpressionContext ctx) {
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
            if (listener instanceof MiracleListener) ((MiracleListener) listener).enterLogicAndExpression(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof MiracleListener) ((MiracleListener) listener).exitLogicAndExpression(this);
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

    public static class LogicOrExpressionContext extends ExpressionContext {
        public LogicOrExpressionContext(ExpressionContext ctx) {
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
            if (listener instanceof MiracleListener) ((MiracleListener) listener).enterLogicOrExpression(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof MiracleListener) ((MiracleListener) listener).exitLogicOrExpression(this);
        }
    }

    public static class AddSubExpressionContext extends ExpressionContext {
        public Token operator;

        public AddSubExpressionContext(ExpressionContext ctx) {
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
            if (listener instanceof MiracleListener) ((MiracleListener) listener).enterAddSubExpression(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof MiracleListener) ((MiracleListener) listener).exitAddSubExpression(this);
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

    public static class ShlShrExpressionContext extends ExpressionContext {
        public Token operator;

        public ShlShrExpressionContext(ExpressionContext ctx) {
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
            if (listener instanceof MiracleListener) ((MiracleListener) listener).enterShlShrExpression(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof MiracleListener) ((MiracleListener) listener).exitShlShrExpression(this);
        }
    }

    public static class EqualityExpressionContext extends ExpressionContext {
        public Token operator;

        public EqualityExpressionContext(ExpressionContext ctx) {
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
            if (listener instanceof MiracleListener) ((MiracleListener) listener).enterEqualityExpression(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof MiracleListener) ((MiracleListener) listener).exitEqualityExpression(this);
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

    public static class UnaryExpressionContext extends ExpressionContext {
        public Token operator;

        public UnaryExpressionContext(ExpressionContext ctx) {
            copyFrom(ctx);
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof MiracleListener) ((MiracleListener) listener).enterUnaryExpression(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof MiracleListener) ((MiracleListener) listener).exitUnaryExpression(this);
        }
    }

    public static class ConstantContext extends ParserRuleContext {
        public ConstantContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode INTEGER() {
            return getToken(MiracleParser.INTEGER, 0);
        }

        public TerminalNode STRING() {
            return getToken(MiracleParser.STRING, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_constant;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof MiracleListener) ((MiracleListener) listener).enterConstant(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof MiracleListener) ((MiracleListener) listener).exitConstant(this);
        }
    }
}