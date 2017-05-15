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
            RULE_variableDeclarationStatement = 3, RULE_constructorDeclarationStatement = 4,
            RULE_blockStatement = 5, RULE_statement = 6, RULE_selectionStatement = 7,
            RULE_iterationStatement = 8, RULE_controlStatement = 9, RULE_expressionStatement = 10,
            RULE_emptyStatement = 11, RULE_typename = 12, RULE_basetype = 13, RULE_expression = 14,
            RULE_constant = 15;
    public static final String[] ruleNames = {
            "miracle", "classDeclarationStatement", "functionDeclarationStatement",
            "variableDeclarationStatement", "constructorDeclarationStatement", "blockStatement",
            "statement", "selectionStatement", "iterationStatement", "controlStatement",
            "expressionStatement", "emptyStatement", "typename", "basetype", "expression",
            "constant"
    };
    /**
     * @deprecated Use {@link #VOCABULARY} instead.
     */
    @Deprecated
    public static final String[] tokenNames;
    public static final String _serializedATN =
            "\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3;\u011f\4\2\t\2\4" +
                    "\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t" +
                    "\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\3\2\3\2\3" +
                    "\2\7\2&\n\2\f\2\16\2)\13\2\3\3\3\3\3\3\3\3\3\3\7\3\60\n\3\f\3\16\3\63" +
                    "\13\3\3\3\5\3\66\n\3\3\3\3\3\7\3:\n\3\f\3\16\3=\13\3\3\3\3\3\3\4\3\4\3" +
                    "\4\3\4\3\4\3\4\5\4G\n\4\3\4\3\4\3\4\3\4\7\4M\n\4\f\4\16\4P\13\4\3\4\3" +
                    "\4\3\4\7\4U\n\4\f\4\16\4X\13\4\3\4\3\4\3\5\3\5\3\5\3\5\5\5`\n\5\3\5\3" +
                    "\5\3\6\3\6\3\6\3\6\3\6\7\6i\n\6\f\6\16\6l\13\6\3\6\3\6\3\7\3\7\7\7r\n" +
                    "\7\f\7\16\7u\13\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b\u0080\n\b\3" +
                    "\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u0089\n\t\3\n\3\n\3\n\5\n\u008e\n\n\3\n" +
                    "\3\n\5\n\u0092\n\n\3\n\3\n\5\n\u0096\n\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3" +
                    "\n\5\n\u00a0\n\n\3\13\3\13\3\13\3\13\3\13\3\13\5\13\u00a8\n\13\3\13\5" +
                    "\13\u00ab\n\13\3\f\3\f\3\f\3\r\3\r\3\16\3\16\3\16\7\16\u00b5\n\16\f\16" +
                    "\16\16\u00b8\13\16\3\17\3\17\3\17\3\17\3\17\5\17\u00bf\n\17\3\20\3\20" +
                    "\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\5\20\u00cf" +
                    "\n\20\3\20\6\20\u00d2\n\20\r\20\16\20\u00d3\3\20\3\20\5\20\u00d8\n\20" +
                    "\5\20\u00da\n\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20" +
                    "\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20" +
                    "\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\5\20\u0100\n\20" +
                    "\3\20\3\20\7\20\u0104\n\20\f\20\16\20\u0107\13\20\3\20\3\20\3\20\3\20" +
                    "\3\20\3\20\3\20\3\20\3\20\3\20\3\20\7\20\u0114\n\20\f\20\16\20\u0117\13" +
                    "\20\3\21\3\21\3\21\3\21\5\21\u011d\n\21\3\21\2\3\36\22\2\4\6\b\n\f\16" +
                    "\20\22\24\26\30\32\34\36 \2\n\3\2\26\33\3\2\35\37\3\2\31\32\3\2 !\3\2" +
                    "\"%\3\2&\'\3\2\26\27\3\2-.\u014c\2\'\3\2\2\2\4*\3\2\2\2\6@\3\2\2\2\b[" +
                    "\3\2\2\2\nc\3\2\2\2\fo\3\2\2\2\16\177\3\2\2\2\20\u0081\3\2\2\2\22\u009f" +
                    "\3\2\2\2\24\u00aa\3\2\2\2\26\u00ac\3\2\2\2\30\u00af\3\2\2\2\32\u00b1\3" +
                    "\2\2\2\34\u00be\3\2\2\2\36\u00d9\3\2\2\2 \u011c\3\2\2\2\"&\5\4\3\2#&\5" +
                    "\6\4\2$&\5\b\5\2%\"\3\2\2\2%#\3\2\2\2%$\3\2\2\2&)\3\2\2\2\'%\3\2\2\2\'" +
                    "(\3\2\2\2(\3\3\2\2\2)\'\3\2\2\2*+\7\3\2\2+,\7\65\2\2,\61\7\4\2\2-\60\5" +
                    "\6\4\2.\60\5\b\5\2/-\3\2\2\2/.\3\2\2\2\60\63\3\2\2\2\61/\3\2\2\2\61\62" +
                    "\3\2\2\2\62\65\3\2\2\2\63\61\3\2\2\2\64\66\5\n\6\2\65\64\3\2\2\2\65\66" +
                    "\3\2\2\2\66;\3\2\2\2\67:\5\6\4\28:\5\b\5\29\67\3\2\2\298\3\2\2\2:=\3\2" +
                    "\2\2;9\3\2\2\2;<\3\2\2\2<>\3\2\2\2=;\3\2\2\2>?\7\5\2\2?\5\3\2\2\2@A\5" +
                    "\32\16\2AB\7\65\2\2BF\7\6\2\2CD\5\32\16\2DE\7\65\2\2EG\3\2\2\2FC\3\2\2" +
                    "\2FG\3\2\2\2GN\3\2\2\2HI\7\7\2\2IJ\5\32\16\2JK\7\65\2\2KM\3\2\2\2LH\3" +
                    "\2\2\2MP\3\2\2\2NL\3\2\2\2NO\3\2\2\2OQ\3\2\2\2PN\3\2\2\2QR\7\b\2\2RV\7" +
                    "\4\2\2SU\5\16\b\2TS\3\2\2\2UX\3\2\2\2VT\3\2\2\2VW\3\2\2\2WY\3\2\2\2XV" +
                    "\3\2\2\2YZ\7\5\2\2Z\7\3\2\2\2[\\\5\32\16\2\\_\7\65\2\2]^\7\t\2\2^`\5\36" +
                    "\20\2_]\3\2\2\2_`\3\2\2\2`a\3\2\2\2ab\7\n\2\2b\t\3\2\2\2cd\5\32\16\2d" +
                    "e\7\6\2\2ef\7\b\2\2fj\7\4\2\2gi\5\16\b\2hg\3\2\2\2il\3\2\2\2jh\3\2\2\2" +
                    "jk\3\2\2\2km\3\2\2\2lj\3\2\2\2mn\7\5\2\2n\13\3\2\2\2os\7\4\2\2pr\5\16" +
                    "\b\2qp\3\2\2\2ru\3\2\2\2sq\3\2\2\2st\3\2\2\2tv\3\2\2\2us\3\2\2\2vw\7\5" +
                    "\2\2w\r\3\2\2\2x\u0080\5\f\7\2y\u0080\5\b\5\2z\u0080\5\20\t\2{\u0080\5" +
                    "\22\n\2|\u0080\5\24\13\2}\u0080\5\26\f\2~\u0080\5\30\r\2\177x\3\2\2\2" +
                    "\177y\3\2\2\2\177z\3\2\2\2\177{\3\2\2\2\177|\3\2\2\2\177}\3\2\2\2\177" +
                    "~\3\2\2\2\u0080\17\3\2\2\2\u0081\u0082\7\13\2\2\u0082\u0083\7\6\2\2\u0083" +
                    "\u0084\5\36\20\2\u0084\u0085\7\b\2\2\u0085\u0088\5\16\b\2\u0086\u0087" +
                    "\7\f\2\2\u0087\u0089\5\16\b\2\u0088\u0086\3\2\2\2\u0088\u0089\3\2\2\2" +
                    "\u0089\21\3\2\2\2\u008a\u008b\7\r\2\2\u008b\u008d\7\6\2\2\u008c\u008e" +
                    "\5\36\20\2\u008d\u008c\3\2\2\2\u008d\u008e\3\2\2\2\u008e\u008f\3\2\2\2" +
                    "\u008f\u0091\7\n\2\2\u0090\u0092\5\36\20\2\u0091\u0090\3\2\2\2\u0091\u0092" +
                    "\3\2\2\2\u0092\u0093\3\2\2\2\u0093\u0095\7\n\2\2\u0094\u0096\5\36\20\2" +
                    "\u0095\u0094\3\2\2\2\u0095\u0096\3\2\2\2\u0096\u0097\3\2\2\2\u0097\u0098" +
                    "\7\b\2\2\u0098\u00a0\5\16\b\2\u0099\u009a\7\16\2\2\u009a\u009b\7\6\2\2" +
                    "\u009b\u009c\5\36\20\2\u009c\u009d\7\b\2\2\u009d\u009e\5\16\b\2\u009e" +
                    "\u00a0\3\2\2\2\u009f\u008a\3\2\2\2\u009f\u0099\3\2\2\2\u00a0\23\3\2\2" +
                    "\2\u00a1\u00a2\7\17\2\2\u00a2\u00ab\7\n\2\2\u00a3\u00a4\7\20\2\2\u00a4" +
                    "\u00ab\7\n\2\2\u00a5\u00a7\7\21\2\2\u00a6\u00a8\5\36\20\2\u00a7\u00a6" +
                    "\3\2\2\2\u00a7\u00a8\3\2\2\2\u00a8\u00a9\3\2\2\2\u00a9\u00ab\7\n\2\2\u00aa" +
                    "\u00a1\3\2\2\2\u00aa\u00a3\3\2\2\2\u00aa\u00a5\3\2\2\2\u00ab\25\3\2\2" +
                    "\2\u00ac\u00ad\5\36\20\2\u00ad\u00ae\7\n\2\2\u00ae\27\3\2\2\2\u00af\u00b0" +
                    "\7\n\2\2\u00b0\31\3\2\2\2\u00b1\u00b6\5\34\17\2\u00b2\u00b3\7\22\2\2\u00b3" +
                    "\u00b5\7\23\2\2\u00b4\u00b2\3\2\2\2\u00b5\u00b8\3\2\2\2\u00b6\u00b4\3" +
                    "\2\2\2\u00b6\u00b7\3\2\2\2\u00b7\33\3\2\2\2\u00b8\u00b6\3\2\2\2\u00b9" +
                    "\u00bf\7\60\2\2\u00ba\u00bf\7\61\2\2\u00bb\u00bf\7\63\2\2\u00bc\u00bf" +
                    "\7\62\2\2\u00bd\u00bf\7\65\2\2\u00be\u00b9\3\2\2\2\u00be\u00ba\3\2\2\2" +
                    "\u00be\u00bb\3\2\2\2\u00be\u00bc\3\2\2\2\u00be\u00bd\3\2\2\2\u00bf\35" +
                    "\3\2\2\2\u00c0\u00c1\b\20\1\2\u00c1\u00da\5 \21\2\u00c2\u00da\7\24\2\2" +
                    "\u00c3\u00da\7\65\2\2\u00c4\u00c5\7\6\2\2\u00c5\u00c6\5\36\20\2\u00c6" +
                    "\u00c7\7\b\2\2\u00c7\u00da\3\2\2\2\u00c8\u00c9\t\2\2\2\u00c9\u00da\5\36" +
                    "\20\17\u00ca\u00cb\7\34\2\2\u00cb\u00d7\5\32\16\2\u00cc\u00ce\7\22\2\2" +
                    "\u00cd\u00cf\5\36\20\2\u00ce\u00cd\3\2\2\2\u00ce\u00cf\3\2\2\2\u00cf\u00d0" +
                    "\3\2\2\2\u00d0\u00d2\7\23\2\2\u00d1\u00cc\3\2\2\2\u00d2\u00d3\3\2\2\2" +
                    "\u00d3\u00d1\3\2\2\2\u00d3\u00d4\3\2\2\2\u00d4\u00d8\3\2\2\2\u00d5\u00d6" +
                    "\7\6\2\2\u00d6\u00d8\7\b\2\2\u00d7\u00d1\3\2\2\2\u00d7\u00d5\3\2\2\2\u00d7" +
                    "\u00d8\3\2\2\2\u00d8\u00da\3\2\2\2\u00d9\u00c0\3\2\2\2\u00d9\u00c2\3\2" +
                    "\2\2\u00d9\u00c3\3\2\2\2\u00d9\u00c4\3\2\2\2\u00d9\u00c8\3\2\2\2\u00d9" +
                    "\u00ca\3\2\2\2\u00da\u0115\3\2\2\2\u00db\u00dc\f\r\2\2\u00dc\u00dd\t\3" +
                    "\2\2\u00dd\u0114\5\36\20\16\u00de\u00df\f\f\2\2\u00df\u00e0\t\4\2\2\u00e0" +
                    "\u0114\5\36\20\r\u00e1\u00e2\f\13\2\2\u00e2\u00e3\t\5\2\2\u00e3\u0114" +
                    "\5\36\20\f\u00e4\u00e5\f\n\2\2\u00e5\u00e6\t\6\2\2\u00e6\u0114\5\36\20" +
                    "\13\u00e7\u00e8\f\t\2\2\u00e8\u00e9\t\7\2\2\u00e9\u0114\5\36\20\n\u00ea" +
                    "\u00eb\f\b\2\2\u00eb\u00ec\7(\2\2\u00ec\u0114\5\36\20\t\u00ed\u00ee\f" +
                    "\7\2\2\u00ee\u00ef\7)\2\2\u00ef\u0114\5\36\20\b\u00f0\u00f1\f\6\2\2\u00f1" +
                    "\u00f2\7*\2\2\u00f2\u0114\5\36\20\7\u00f3\u00f4\f\5\2\2\u00f4\u00f5\7" +
                    "+\2\2\u00f5\u0114\5\36\20\6\u00f6\u00f7\f\4\2\2\u00f7\u00f8\7,\2\2\u00f8" +
                    "\u0114\5\36\20\5\u00f9\u00fa\f\3\2\2\u00fa\u00fb\7\t\2\2\u00fb\u0114\5" +
                    "\36\20\3\u00fc\u00fd\f\23\2\2\u00fd\u00ff\7\6\2\2\u00fe\u0100\5\36\20" +
                    "\2\u00ff\u00fe\3\2\2\2\u00ff\u0100\3\2\2\2\u0100\u0105\3\2\2\2\u0101\u0102" +
                    "\7\7\2\2\u0102\u0104\5\36\20\2\u0103\u0101\3\2\2\2\u0104\u0107\3\2\2\2" +
                    "\u0105\u0103\3\2\2\2\u0105\u0106\3\2\2\2\u0106\u0108\3\2\2\2\u0107\u0105" +
                    "\3\2\2\2\u0108\u0114\7\b\2\2\u0109\u010a\f\22\2\2\u010a\u010b\7\22\2\2" +
                    "\u010b\u010c\5\36\20\2\u010c\u010d\7\23\2\2\u010d\u0114\3\2\2\2\u010e" +
                    "\u010f\f\21\2\2\u010f\u0110\7\25\2\2\u0110\u0114\7\65\2\2\u0111\u0112" +
                    "\f\20\2\2\u0112\u0114\t\b\2\2\u0113\u00db\3\2\2\2\u0113\u00de\3\2\2\2" +
                    "\u0113\u00e1\3\2\2\2\u0113\u00e4\3\2\2\2\u0113\u00e7\3\2\2\2\u0113\u00ea" +
                    "\3\2\2\2\u0113\u00ed\3\2\2\2\u0113\u00f0\3\2\2\2\u0113\u00f3\3\2\2\2\u0113" +
                    "\u00f6\3\2\2\2\u0113\u00f9\3\2\2\2\u0113\u00fc\3\2\2\2\u0113\u0109\3\2" +
                    "\2\2\u0113\u010e\3\2\2\2\u0113\u0111\3\2\2\2\u0114\u0117\3\2\2\2\u0115" +
                    "\u0113\3\2\2\2\u0115\u0116\3\2\2\2\u0116\37\3\2\2\2\u0117\u0115\3\2\2" +
                    "\2\u0118\u011d\7\66\2\2\u0119\u011d\7\67\2\2\u011a\u011d\t\t\2\2\u011b" +
                    "\u011d\7/\2\2\u011c\u0118\3\2\2\2\u011c\u0119\3\2\2\2\u011c\u011a\3\2" +
                    "\2\2\u011c\u011b\3\2\2\2\u011d!\3\2\2\2\"%\'/\61\659;FNV_js\177\u0088" +
                    "\u008d\u0091\u0095\u009f\u00a7\u00aa\u00b6\u00be\u00ce\u00d3\u00d7\u00d9" +
                    "\u00ff\u0105\u0113\u0115\u011c";
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
                setState(37);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << BUILTIN_INT) | (1L << BUILTIN_BOOL) | (1L << BUILTIN_STRING) | (1L << BUILTIN_VOID) | (1L << IDENTIFIER))) != 0)) {
                    {
                        setState(35);
                        _errHandler.sync(this);
                        switch (getInterpreter().adaptivePredict(_input, 0, _ctx)) {
                            case 1: {
                                setState(32);
                                classDeclarationStatement();
                            }
                            break;
                            case 2: {
                                setState(33);
                                functionDeclarationStatement();
                            }
                            break;
                            case 3: {
                                setState(34);
                                variableDeclarationStatement();
                            }
                            break;
                        }
                    }
                    setState(39);
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
                setState(40);
                match(T__0);
                setState(41);
                match(IDENTIFIER);
                setState(42);
                match(T__1);
                {
                    setState(47);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input, 3, _ctx);
                    while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
                        if (_alt == 1) {
                            {
                                setState(45);
                                _errHandler.sync(this);
                                switch (getInterpreter().adaptivePredict(_input, 2, _ctx)) {
                                    case 1: {
                                        setState(43);
                                        functionDeclarationStatement();
                                    }
                                    break;
                                    case 2: {
                                        setState(44);
                                        variableDeclarationStatement();
                                    }
                                    break;
                                }
                            }
                        }
                        setState(49);
                        _errHandler.sync(this);
                        _alt = getInterpreter().adaptivePredict(_input, 3, _ctx);
                    }
                    setState(51);
                    _errHandler.sync(this);
                    switch (getInterpreter().adaptivePredict(_input, 4, _ctx)) {
                        case 1: {
                            setState(50);
                            constructorDeclarationStatement();
                        }
                        break;
                    }
                    setState(57);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BUILTIN_INT) | (1L << BUILTIN_BOOL) | (1L << BUILTIN_STRING) | (1L << BUILTIN_VOID) | (1L << IDENTIFIER))) != 0)) {
                        {
                            setState(55);
                            _errHandler.sync(this);
                            switch (getInterpreter().adaptivePredict(_input, 5, _ctx)) {
                                case 1: {
                                    setState(53);
                                    functionDeclarationStatement();
                                }
                                break;
                                case 2: {
                                    setState(54);
                                    variableDeclarationStatement();
                                }
                                break;
                            }
                        }
                        setState(59);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                    }
                }
                setState(60);
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
                setState(62);
                typename();
                setState(63);
                match(IDENTIFIER);
                setState(64);
                match(T__3);
                setState(68);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BUILTIN_INT) | (1L << BUILTIN_BOOL) | (1L << BUILTIN_STRING) | (1L << BUILTIN_VOID) | (1L << IDENTIFIER))) != 0)) {
                    {
                        setState(65);
                        typename();
                        setState(66);
                        match(IDENTIFIER);
                    }
                }

                setState(76);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == T__4) {
                    {
                        {
                            setState(70);
                            match(T__4);
                            setState(71);
                            typename();
                            setState(72);
                            match(IDENTIFIER);
                        }
                    }
                    setState(78);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
                setState(79);
                match(T__5);
                setState(80);
                match(T__1);
                setState(84);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__3) | (1L << T__7) | (1L << T__8) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__17) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__42) | (1L << T__43) | (1L << T__44) | (1L << BUILTIN_INT) | (1L << BUILTIN_BOOL) | (1L << BUILTIN_STRING) | (1L << BUILTIN_VOID) | (1L << IDENTIFIER) | (1L << INTEGER) | (1L << STRING))) != 0)) {
                    {
                        {
                            setState(81);
                            statement();
                        }
                    }
                    setState(86);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
                setState(87);
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
                setState(89);
                typename();
                setState(90);
                match(IDENTIFIER);
                setState(93);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == T__6) {
                    {
                        setState(91);
                        match(T__6);
                        setState(92);
                        expression(0);
                    }
                }

                setState(95);
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
                setState(97);
                typename();
                setState(98);
                match(T__3);
                setState(99);
                match(T__5);
                setState(100);
                match(T__1);
                setState(104);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__3) | (1L << T__7) | (1L << T__8) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__17) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__42) | (1L << T__43) | (1L << T__44) | (1L << BUILTIN_INT) | (1L << BUILTIN_BOOL) | (1L << BUILTIN_STRING) | (1L << BUILTIN_VOID) | (1L << IDENTIFIER) | (1L << INTEGER) | (1L << STRING))) != 0)) {
                    {
                        {
                            setState(101);
                            statement();
                        }
                    }
                    setState(106);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
                setState(107);
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
                setState(109);
                match(T__1);
                setState(113);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__3) | (1L << T__7) | (1L << T__8) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__17) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__42) | (1L << T__43) | (1L << T__44) | (1L << BUILTIN_INT) | (1L << BUILTIN_BOOL) | (1L << BUILTIN_STRING) | (1L << BUILTIN_VOID) | (1L << IDENTIFIER) | (1L << INTEGER) | (1L << STRING))) != 0)) {
                    {
                        {
                            setState(110);
                            statement();
                        }
                    }
                    setState(115);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
                setState(116);
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
            setState(125);
            _errHandler.sync(this);
            switch (getInterpreter().adaptivePredict(_input, 13, _ctx)) {
                case 1:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(118);
                    blockStatement();
                }
                break;
                case 2:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(119);
                    variableDeclarationStatement();
                }
                break;
                case 3:
                    enterOuterAlt(_localctx, 3);
                {
                    setState(120);
                    selectionStatement();
                }
                break;
                case 4:
                    enterOuterAlt(_localctx, 4);
                {
                    setState(121);
                    iterationStatement();
                }
                break;
                case 5:
                    enterOuterAlt(_localctx, 5);
                {
                    setState(122);
                    controlStatement();
                }
                break;
                case 6:
                    enterOuterAlt(_localctx, 6);
                {
                    setState(123);
                    expressionStatement();
                }
                break;
                case 7:
                    enterOuterAlt(_localctx, 7);
                {
                    setState(124);
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
                setState(127);
                match(T__8);
                setState(128);
                match(T__3);
                setState(129);
                expression(0);
                setState(130);
                match(T__5);
                setState(131);
                statement();
                setState(134);
                _errHandler.sync(this);
                switch (getInterpreter().adaptivePredict(_input, 14, _ctx)) {
                    case 1: {
                        setState(132);
                        match(T__9);
                        setState(133);
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
            setState(157);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case T__10:
                    _localctx = new ForStatementContext(_localctx);
                    enterOuterAlt(_localctx, 1);
                {
                    setState(136);
                    match(T__10);
                    setState(137);
                    match(T__3);
                    setState(139);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__17) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__42) | (1L << T__43) | (1L << T__44) | (1L << IDENTIFIER) | (1L << INTEGER) | (1L << STRING))) != 0)) {
                        {
                            setState(138);
                            expression(0);
                        }
                    }

                    setState(141);
                    match(T__7);
                    setState(143);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__17) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__42) | (1L << T__43) | (1L << T__44) | (1L << IDENTIFIER) | (1L << INTEGER) | (1L << STRING))) != 0)) {
                        {
                            setState(142);
                            expression(0);
                        }
                    }

                    setState(145);
                    match(T__7);
                    setState(147);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__17) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__42) | (1L << T__43) | (1L << T__44) | (1L << IDENTIFIER) | (1L << INTEGER) | (1L << STRING))) != 0)) {
                        {
                            setState(146);
                            expression(0);
                        }
                    }

                    setState(149);
                    match(T__5);
                    setState(150);
                    statement();
                }
                break;
                case T__11:
                    _localctx = new WhileStatementContext(_localctx);
                    enterOuterAlt(_localctx, 2);
                {
                    setState(151);
                    match(T__11);
                    setState(152);
                    match(T__3);
                    setState(153);
                    expression(0);
                    setState(154);
                    match(T__5);
                    setState(155);
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
            setState(168);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case T__12:
                    _localctx = new ContinueStatementContext(_localctx);
                    enterOuterAlt(_localctx, 1);
                {
                    setState(159);
                    match(T__12);
                    setState(160);
                    match(T__7);
                }
                break;
                case T__13:
                    _localctx = new BreakStatementContext(_localctx);
                    enterOuterAlt(_localctx, 2);
                {
                    setState(161);
                    match(T__13);
                    setState(162);
                    match(T__7);
                }
                break;
                case T__14:
                    _localctx = new ReturnStatementContext(_localctx);
                    enterOuterAlt(_localctx, 3);
                {
                    setState(163);
                    match(T__14);
                    setState(165);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__17) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__42) | (1L << T__43) | (1L << T__44) | (1L << IDENTIFIER) | (1L << INTEGER) | (1L << STRING))) != 0)) {
                        {
                            setState(164);
                            expression(0);
                        }
                    }

                    setState(167);
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
                setState(170);
                expression(0);
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

    public final EmptyStatementContext emptyStatement() throws RecognitionException {
        EmptyStatementContext _localctx = new EmptyStatementContext(_ctx, getState());
        enterRule(_localctx, 22, RULE_emptyStatement);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(173);
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
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                setState(175);
                basetype();
                setState(180);
                _errHandler.sync(this);
                _alt = getInterpreter().adaptivePredict(_input, 21, _ctx);
                while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
                    if (_alt == 1) {
                        {
                            {
                                setState(176);
                                match(T__15);
                                setState(177);
                                match(T__16);
                            }
                        }
                    }
                    setState(182);
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

    public final BasetypeContext basetype() throws RecognitionException {
        BasetypeContext _localctx = new BasetypeContext(_ctx, getState());
        enterRule(_localctx, 26, RULE_basetype);
        try {
            setState(188);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case BUILTIN_INT:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(183);
                    ((BasetypeContext) _localctx).type = match(BUILTIN_INT);
                }
                break;
                case BUILTIN_BOOL:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(184);
                    ((BasetypeContext) _localctx).type = match(BUILTIN_BOOL);
                }
                break;
                case BUILTIN_VOID:
                    enterOuterAlt(_localctx, 3);
                {
                    setState(185);
                    ((BasetypeContext) _localctx).type = match(BUILTIN_VOID);
                }
                break;
                case BUILTIN_STRING:
                    enterOuterAlt(_localctx, 4);
                {
                    setState(186);
                    ((BasetypeContext) _localctx).type = match(BUILTIN_STRING);
                }
                break;
                case IDENTIFIER:
                    enterOuterAlt(_localctx, 5);
                {
                    setState(187);
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
        int _startState = 28;
        enterRecursionRule(_localctx, 28, RULE_expression, _p);
        int _la;
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                setState(215);
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

                        setState(191);
                        constant();
                    }
                    break;
                    case T__17: {
                        _localctx = new ThisExpressionContext(_localctx);
                        _ctx = _localctx;
                        _prevctx = _localctx;
                        setState(192);
                        match(T__17);
                    }
                    break;
                    case IDENTIFIER: {
                        _localctx = new VariableExpressionContext(_localctx);
                        _ctx = _localctx;
                        _prevctx = _localctx;
                        setState(193);
                        match(IDENTIFIER);
                    }
                    break;
                    case T__3: {
                        _localctx = new BraceExpressionContext(_localctx);
                        _ctx = _localctx;
                        _prevctx = _localctx;
                        setState(194);
                        match(T__3);
                        setState(195);
                        expression(0);
                        setState(196);
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
                        setState(198);
                        ((PrefixExpressionContext) _localctx).operator = _input.LT(1);
                        _la = _input.LA(1);
                        if (!((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24))) != 0))) {
                            ((PrefixExpressionContext) _localctx).operator = (Token) _errHandler.recoverInline(this);
                        } else {
                            if (_input.LA(1) == Token.EOF) matchedEOF = true;
                            _errHandler.reportMatch(this);
                            consume();
                        }
                        setState(199);
                        expression(13);
                    }
                    break;
                    case T__25: {
                        _localctx = new NewExpressionContext(_localctx);
                        _ctx = _localctx;
                        _prevctx = _localctx;
                        setState(200);
                        match(T__25);
                        setState(201);
                        typename();
                        setState(213);
                        _errHandler.sync(this);
                        switch (getInterpreter().adaptivePredict(_input, 25, _ctx)) {
                            case 1: {
                                setState(207);
                                _errHandler.sync(this);
                                _alt = 1;
                                do {
                                    switch (_alt) {
                                        case 1: {
                                            {
                                                setState(202);
                                                match(T__15);
                                                setState(204);
                                                _errHandler.sync(this);
                                                _la = _input.LA(1);
                                                if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__17) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__42) | (1L << T__43) | (1L << T__44) | (1L << IDENTIFIER) | (1L << INTEGER) | (1L << STRING))) != 0)) {
                                                    {
                                                        setState(203);
                                                        expression(0);
                                                    }
                                                }

                                                setState(206);
                                                match(T__16);
                                            }
                                        }
                                        break;
                                        default:
                                            throw new NoViableAltException(this);
                                    }
                                    setState(209);
                                    _errHandler.sync(this);
                                    _alt = getInterpreter().adaptivePredict(_input, 24, _ctx);
                                } while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER);
                            }
                            break;
                            case 2: {
                                setState(211);
                                match(T__3);
                                setState(212);
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
                setState(275);
                _errHandler.sync(this);
                _alt = getInterpreter().adaptivePredict(_input, 30, _ctx);
                while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
                    if (_alt == 1) {
                        if (_parseListeners != null) triggerExitRuleEvent();
                        _prevctx = _localctx;
                        {
                            setState(273);
                            _errHandler.sync(this);
                            switch (getInterpreter().adaptivePredict(_input, 29, _ctx)) {
                                case 1: {
                                    _localctx = new BinaryExpressionContext(new ExpressionContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(217);
                                    if (!(precpred(_ctx, 11)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 11)");
                                    setState(218);
                                    ((BinaryExpressionContext) _localctx).operator = _input.LT(1);
                                    _la = _input.LA(1);
                                    if (!((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__26) | (1L << T__27) | (1L << T__28))) != 0))) {
                                        ((BinaryExpressionContext) _localctx).operator = (Token) _errHandler.recoverInline(this);
                                    } else {
                                        if (_input.LA(1) == Token.EOF) matchedEOF = true;
                                        _errHandler.reportMatch(this);
                                        consume();
                                    }
                                    setState(219);
                                    expression(12);
                                }
                                break;
                                case 2: {
                                    _localctx = new BinaryExpressionContext(new ExpressionContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(220);
                                    if (!(precpred(_ctx, 10)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 10)");
                                    setState(221);
                                    ((BinaryExpressionContext) _localctx).operator = _input.LT(1);
                                    _la = _input.LA(1);
                                    if (!(_la == T__22 || _la == T__23)) {
                                        ((BinaryExpressionContext) _localctx).operator = (Token) _errHandler.recoverInline(this);
                                    } else {
                                        if (_input.LA(1) == Token.EOF) matchedEOF = true;
                                        _errHandler.reportMatch(this);
                                        consume();
                                    }
                                    setState(222);
                                    expression(11);
                                }
                                break;
                                case 3: {
                                    _localctx = new BinaryExpressionContext(new ExpressionContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(223);
                                    if (!(precpred(_ctx, 9)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 9)");
                                    setState(224);
                                    ((BinaryExpressionContext) _localctx).operator = _input.LT(1);
                                    _la = _input.LA(1);
                                    if (!(_la == T__29 || _la == T__30)) {
                                        ((BinaryExpressionContext) _localctx).operator = (Token) _errHandler.recoverInline(this);
                                    } else {
                                        if (_input.LA(1) == Token.EOF) matchedEOF = true;
                                        _errHandler.reportMatch(this);
                                        consume();
                                    }
                                    setState(225);
                                    expression(10);
                                }
                                break;
                                case 4: {
                                    _localctx = new BinaryExpressionContext(new ExpressionContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(226);
                                    if (!(precpred(_ctx, 8)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 8)");
                                    setState(227);
                                    ((BinaryExpressionContext) _localctx).operator = _input.LT(1);
                                    _la = _input.LA(1);
                                    if (!((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__31) | (1L << T__32) | (1L << T__33) | (1L << T__34))) != 0))) {
                                        ((BinaryExpressionContext) _localctx).operator = (Token) _errHandler.recoverInline(this);
                                    } else {
                                        if (_input.LA(1) == Token.EOF) matchedEOF = true;
                                        _errHandler.reportMatch(this);
                                        consume();
                                    }
                                    setState(228);
                                    expression(9);
                                }
                                break;
                                case 5: {
                                    _localctx = new BinaryExpressionContext(new ExpressionContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(229);
                                    if (!(precpred(_ctx, 7)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 7)");
                                    setState(230);
                                    ((BinaryExpressionContext) _localctx).operator = _input.LT(1);
                                    _la = _input.LA(1);
                                    if (!(_la == T__35 || _la == T__36)) {
                                        ((BinaryExpressionContext) _localctx).operator = (Token) _errHandler.recoverInline(this);
                                    } else {
                                        if (_input.LA(1) == Token.EOF) matchedEOF = true;
                                        _errHandler.reportMatch(this);
                                        consume();
                                    }
                                    setState(231);
                                    expression(8);
                                }
                                break;
                                case 6: {
                                    _localctx = new BinaryExpressionContext(new ExpressionContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(232);
                                    if (!(precpred(_ctx, 6)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 6)");
                                    setState(233);
                                    ((BinaryExpressionContext) _localctx).operator = match(T__37);
                                    setState(234);
                                    expression(7);
                                }
                                break;
                                case 7: {
                                    _localctx = new BinaryExpressionContext(new ExpressionContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(235);
                                    if (!(precpred(_ctx, 5)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 5)");
                                    setState(236);
                                    ((BinaryExpressionContext) _localctx).operator = match(T__38);
                                    setState(237);
                                    expression(6);
                                }
                                break;
                                case 8: {
                                    _localctx = new BinaryExpressionContext(new ExpressionContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(238);
                                    if (!(precpred(_ctx, 4)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 4)");
                                    setState(239);
                                    ((BinaryExpressionContext) _localctx).operator = match(T__39);
                                    setState(240);
                                    expression(5);
                                }
                                break;
                                case 9: {
                                    _localctx = new BinaryExpressionContext(new ExpressionContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(241);
                                    if (!(precpred(_ctx, 3)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 3)");
                                    setState(242);
                                    ((BinaryExpressionContext) _localctx).operator = match(T__40);
                                    setState(243);
                                    expression(4);
                                }
                                break;
                                case 10: {
                                    _localctx = new BinaryExpressionContext(new ExpressionContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(244);
                                    if (!(precpred(_ctx, 2)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 2)");
                                    setState(245);
                                    ((BinaryExpressionContext) _localctx).operator = match(T__41);
                                    setState(246);
                                    expression(3);
                                }
                                break;
                                case 11: {
                                    _localctx = new BinaryExpressionContext(new ExpressionContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(247);
                                    if (!(precpred(_ctx, 1)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 1)");
                                    setState(248);
                                    ((BinaryExpressionContext) _localctx).operator = match(T__6);
                                    setState(249);
                                    expression(1);
                                }
                                break;
                                case 12: {
                                    _localctx = new FunctionCallExpressionContext(new ExpressionContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(250);
                                    if (!(precpred(_ctx, 17)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 17)");
                                    setState(251);
                                    match(T__3);
                                    setState(253);
                                    _errHandler.sync(this);
                                    _la = _input.LA(1);
                                    if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__17) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__42) | (1L << T__43) | (1L << T__44) | (1L << IDENTIFIER) | (1L << INTEGER) | (1L << STRING))) != 0)) {
                                        {
                                            setState(252);
                                            expression(0);
                                        }
                                    }

                                    setState(259);
                                    _errHandler.sync(this);
                                    _la = _input.LA(1);
                                    while (_la == T__4) {
                                        {
                                            {
                                                setState(255);
                                                match(T__4);
                                                setState(256);
                                                expression(0);
                                            }
                                        }
                                        setState(261);
                                        _errHandler.sync(this);
                                        _la = _input.LA(1);
                                    }
                                    setState(262);
                                    match(T__5);
                                }
                                break;
                                case 13: {
                                    _localctx = new SubscriptExpressionContext(new ExpressionContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(263);
                                    if (!(precpred(_ctx, 16)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 16)");
                                    setState(264);
                                    match(T__15);
                                    setState(265);
                                    expression(0);
                                    setState(266);
                                    match(T__16);
                                }
                                break;
                                case 14: {
                                    _localctx = new FieldExpressionContext(new ExpressionContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(268);
                                    if (!(precpred(_ctx, 15)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 15)");
                                    setState(269);
                                    ((FieldExpressionContext) _localctx).operator = match(T__18);
                                    setState(270);
                                    match(IDENTIFIER);
                                }
                                break;
                                case 15: {
                                    _localctx = new SuffixExpressionContext(new ExpressionContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(271);
                                    if (!(precpred(_ctx, 14)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 14)");
                                    setState(272);
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
                    setState(277);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input, 30, _ctx);
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
        enterRule(_localctx, 30, RULE_constant);
        int _la;
        try {
            setState(282);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case INTEGER:
                    _localctx = new IntegerConstantContext(_localctx);
                    enterOuterAlt(_localctx, 1);
                {
                    setState(278);
                    match(INTEGER);
                }
                break;
                case STRING:
                    _localctx = new StringConstantContext(_localctx);
                    enterOuterAlt(_localctx, 2);
                {
                    setState(279);
                    match(STRING);
                }
                break;
                case T__42:
                case T__43:
                    _localctx = new BoolConstantContext(_localctx);
                    enterOuterAlt(_localctx, 3);
                {
                    setState(280);
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
                    setState(281);
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
            case 14:
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