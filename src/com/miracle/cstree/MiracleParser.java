package com.miracle.cstree;// Generated from Miracle.g4 by ANTLR 4.7
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MiracleParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, T__37=38, 
		T__38=39, T__39=40, T__40=41, T__41=42, T__42=43, T__43=44, T__44=45, 
		BASETYPE=46, DECORATOR=47, IDENTIFIER=48, INTEGER=49, STRING=50, NEXTLINE=51, 
		LINECOMMENT=52, BLOCKCOMMENT=53, WHITECHAR=54;
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

	private static final String[] _LITERAL_NAMES = {
		null, "'class'", "'extends'", "'{'", "'}'", "'('", "','", "')'", "'='", 
		"';'", "'if'", "'else'", "'for'", "'while'", "'continue'", "'break'", 
		"'return'", "'['", "']'", "'.'", "'++'", "'--'", "'!'", "'+'", "'-'", 
		"'~'", "'new'", "'*'", "'/'", "'%'", "'<<'", "'>>'", "'<'", "'<='", "'>'", 
		"'>='", "'=='", "'!='", "'&'", "'^'", "'|'", "'&&'", "'||'", "'true'", 
		"'false'", "'null'"
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

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
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
	public String getGrammarFileName() { return "Miracle.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MiracleParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class MiracleContext extends ParserRuleContext {
		public List<ClassDeclarationStatementContext> classDeclarationStatement() {
			return getRuleContexts(ClassDeclarationStatementContext.class);
		}
		public ClassDeclarationStatementContext classDeclarationStatement(int i) {
			return getRuleContext(ClassDeclarationStatementContext.class,i);
		}
		public List<FunctionDeclarationStatementContext> functionDeclarationStatement() {
			return getRuleContexts(FunctionDeclarationStatementContext.class);
		}
		public FunctionDeclarationStatementContext functionDeclarationStatement(int i) {
			return getRuleContext(FunctionDeclarationStatementContext.class,i);
		}
		public List<VariableDeclarationStatementContext> variableDeclarationStatement() {
			return getRuleContexts(VariableDeclarationStatementContext.class);
		}
		public VariableDeclarationStatementContext variableDeclarationStatement(int i) {
			return getRuleContext(VariableDeclarationStatementContext.class,i);
		}
		public MiracleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_miracle; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiracleListener ) ((MiracleListener)listener).enterMiracle(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiracleListener ) ((MiracleListener)listener).exitMiracle(this);
		}
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
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << BASETYPE) | (1L << DECORATOR) | (1L << IDENTIFIER))) != 0)) {
				{
				setState(31);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					setState(28);
					classDeclarationStatement();
					}
					break;
				case 2:
					{
					setState(29);
					functionDeclarationStatement();
					}
					break;
				case 3:
					{
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
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassDeclarationStatementContext extends ParserRuleContext {
		public List<TerminalNode> IDENTIFIER() { return getTokens(MiracleParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(MiracleParser.IDENTIFIER, i);
		}
		public List<FunctionDeclarationStatementContext> functionDeclarationStatement() {
			return getRuleContexts(FunctionDeclarationStatementContext.class);
		}
		public FunctionDeclarationStatementContext functionDeclarationStatement(int i) {
			return getRuleContext(FunctionDeclarationStatementContext.class,i);
		}
		public List<VariableDeclarationStatementContext> variableDeclarationStatement() {
			return getRuleContexts(VariableDeclarationStatementContext.class);
		}
		public VariableDeclarationStatementContext variableDeclarationStatement(int i) {
			return getRuleContext(VariableDeclarationStatementContext.class,i);
		}
		public ClassDeclarationStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classDeclarationStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiracleListener ) ((MiracleListener)listener).enterClassDeclarationStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiracleListener ) ((MiracleListener)listener).exitClassDeclarationStatement(this);
		}
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
			setState(40);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(38);
				match(T__1);
				setState(39);
				match(IDENTIFIER);
				}
			}

			setState(42);
			match(T__2);
			setState(47);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BASETYPE) | (1L << DECORATOR) | (1L << IDENTIFIER))) != 0)) {
				{
				setState(45);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
				case 1:
					{
					setState(43);
					functionDeclarationStatement();
					}
					break;
				case 2:
					{
					setState(44);
					variableDeclarationStatement();
					}
					break;
				}
				}
				setState(49);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(50);
			match(T__3);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionDeclarationStatementContext extends ParserRuleContext {
		public List<TypenameContext> typename() {
			return getRuleContexts(TypenameContext.class);
		}
		public TypenameContext typename(int i) {
			return getRuleContext(TypenameContext.class,i);
		}
		public List<TerminalNode> IDENTIFIER() { return getTokens(MiracleParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(MiracleParser.IDENTIFIER, i);
		}
		public TerminalNode DECORATOR() { return getToken(MiracleParser.DECORATOR, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public FunctionDeclarationStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionDeclarationStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiracleListener ) ((MiracleListener)listener).enterFunctionDeclarationStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiracleListener ) ((MiracleListener)listener).exitFunctionDeclarationStatement(this);
		}
	}

	public final FunctionDeclarationStatementContext functionDeclarationStatement() throws RecognitionException {
		FunctionDeclarationStatementContext _localctx = new FunctionDeclarationStatementContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_functionDeclarationStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(53);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DECORATOR) {
				{
				setState(52);
				match(DECORATOR);
				}
			}

			setState(55);
			typename();
			setState(56);
			match(IDENTIFIER);
			setState(57);
			match(T__4);
			setState(61);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==BASETYPE || _la==IDENTIFIER) {
				{
				setState(58);
				typename();
				setState(59);
				match(IDENTIFIER);
				}
			}

			setState(69);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__5) {
				{
				{
				setState(63);
				match(T__5);
				setState(64);
				typename();
				setState(65);
				match(IDENTIFIER);
				}
				}
				setState(71);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(72);
			match(T__6);
			setState(73);
			match(T__2);
			setState(77);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__4) | (1L << T__8) | (1L << T__9) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__42) | (1L << T__43) | (1L << T__44) | (1L << BASETYPE) | (1L << DECORATOR) | (1L << IDENTIFIER) | (1L << INTEGER) | (1L << STRING))) != 0)) {
				{
				{
				setState(74);
				statement();
				}
				}
				setState(79);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(80);
			match(T__3);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableDeclarationStatementContext extends ParserRuleContext {
		public TypenameContext typename() {
			return getRuleContext(TypenameContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(MiracleParser.IDENTIFIER, 0); }
		public TerminalNode DECORATOR() { return getToken(MiracleParser.DECORATOR, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public VariableDeclarationStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableDeclarationStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiracleListener ) ((MiracleListener)listener).enterVariableDeclarationStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiracleListener ) ((MiracleListener)listener).exitVariableDeclarationStatement(this);
		}
	}

	public final VariableDeclarationStatementContext variableDeclarationStatement() throws RecognitionException {
		VariableDeclarationStatementContext _localctx = new VariableDeclarationStatementContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_variableDeclarationStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(83);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DECORATOR) {
				{
				setState(82);
				match(DECORATOR);
				}
			}

			setState(85);
			typename();
			setState(86);
			match(IDENTIFIER);
			setState(89);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__7) {
				{
				setState(87);
				match(T__7);
				setState(88);
				expression(0);
				}
			}

			setState(91);
			match(T__8);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlockStatementContext extends ParserRuleContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public BlockStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_blockStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiracleListener ) ((MiracleListener)listener).enterBlockStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiracleListener ) ((MiracleListener)listener).exitBlockStatement(this);
		}
	}

	public final BlockStatementContext blockStatement() throws RecognitionException {
		BlockStatementContext _localctx = new BlockStatementContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_blockStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(93);
			match(T__2);
			setState(97);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__4) | (1L << T__8) | (1L << T__9) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__42) | (1L << T__43) | (1L << T__44) | (1L << BASETYPE) | (1L << DECORATOR) | (1L << IDENTIFIER) | (1L << INTEGER) | (1L << STRING))) != 0)) {
				{
				{
				setState(94);
				statement();
				}
				}
				setState(99);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(100);
			match(T__3);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public BlockStatementContext blockStatement() {
			return getRuleContext(BlockStatementContext.class,0);
		}
		public VariableDeclarationStatementContext variableDeclarationStatement() {
			return getRuleContext(VariableDeclarationStatementContext.class,0);
		}
		public SelectionStatementContext selectionStatement() {
			return getRuleContext(SelectionStatementContext.class,0);
		}
		public IterationStatementContext iterationStatement() {
			return getRuleContext(IterationStatementContext.class,0);
		}
		public ControlStatementContext controlStatement() {
			return getRuleContext(ControlStatementContext.class,0);
		}
		public ExpressionStatementContext expressionStatement() {
			return getRuleContext(ExpressionStatementContext.class,0);
		}
		public EmptyStatementContext emptyStatement() {
			return getRuleContext(EmptyStatementContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiracleListener ) ((MiracleListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiracleListener ) ((MiracleListener)listener).exitStatement(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_statement);
		try {
			setState(109);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(102);
				blockStatement();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(103);
				variableDeclarationStatement();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(104);
				selectionStatement();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(105);
				iterationStatement();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(106);
				controlStatement();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(107);
				expressionStatement();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(108);
				emptyStatement();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SelectionStatementContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public SelectionStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selectionStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiracleListener ) ((MiracleListener)listener).enterSelectionStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiracleListener ) ((MiracleListener)listener).exitSelectionStatement(this);
		}
	}

	public final SelectionStatementContext selectionStatement() throws RecognitionException {
		SelectionStatementContext _localctx = new SelectionStatementContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_selectionStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(111);
			match(T__9);
			setState(112);
			match(T__4);
			setState(113);
			expression(0);
			setState(114);
			match(T__6);
			setState(115);
			statement();
			setState(118);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				{
				setState(116);
				match(T__10);
				setState(117);
				statement();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IterationStatementContext extends ParserRuleContext {
		public IterationStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_iterationStatement; }
	 
		public IterationStatementContext() { }
		public void copyFrom(IterationStatementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class WhileStatementContext extends IterationStatementContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public WhileStatementContext(IterationStatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiracleListener ) ((MiracleListener)listener).enterWhileStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiracleListener ) ((MiracleListener)listener).exitWhileStatement(this);
		}
	}
	public static class ForStatementContext extends IterationStatementContext {
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ForStatementContext(IterationStatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiracleListener ) ((MiracleListener)listener).enterForStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiracleListener ) ((MiracleListener)listener).exitForStatement(this);
		}
	}

	public final IterationStatementContext iterationStatement() throws RecognitionException {
		IterationStatementContext _localctx = new IterationStatementContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_iterationStatement);
		int _la;
		try {
			setState(141);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__11:
				_localctx = new ForStatementContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(120);
				match(T__11);
				setState(121);
				match(T__4);
				setState(123);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__42) | (1L << T__43) | (1L << T__44) | (1L << IDENTIFIER) | (1L << INTEGER) | (1L << STRING))) != 0)) {
					{
					setState(122);
					expression(0);
					}
				}

				setState(125);
				match(T__8);
				setState(127);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__42) | (1L << T__43) | (1L << T__44) | (1L << IDENTIFIER) | (1L << INTEGER) | (1L << STRING))) != 0)) {
					{
					setState(126);
					expression(0);
					}
				}

				setState(129);
				match(T__8);
				setState(131);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__42) | (1L << T__43) | (1L << T__44) | (1L << IDENTIFIER) | (1L << INTEGER) | (1L << STRING))) != 0)) {
					{
					setState(130);
					expression(0);
					}
				}

				setState(133);
				match(T__6);
				setState(134);
				statement();
				}
				break;
			case T__12:
				_localctx = new WhileStatementContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(135);
				match(T__12);
				setState(136);
				match(T__4);
				setState(137);
				expression(0);
				setState(138);
				match(T__6);
				setState(139);
				statement();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ControlStatementContext extends ParserRuleContext {
		public ControlStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_controlStatement; }
	 
		public ControlStatementContext() { }
		public void copyFrom(ControlStatementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class BreakStatementContext extends ControlStatementContext {
		public BreakStatementContext(ControlStatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiracleListener ) ((MiracleListener)listener).enterBreakStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiracleListener ) ((MiracleListener)listener).exitBreakStatement(this);
		}
	}
	public static class ContinueStatementContext extends ControlStatementContext {
		public ContinueStatementContext(ControlStatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiracleListener ) ((MiracleListener)listener).enterContinueStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiracleListener ) ((MiracleListener)listener).exitContinueStatement(this);
		}
	}
	public static class ReturnStatementContext extends ControlStatementContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ReturnStatementContext(ControlStatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiracleListener ) ((MiracleListener)listener).enterReturnStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiracleListener ) ((MiracleListener)listener).exitReturnStatement(this);
		}
	}

	public final ControlStatementContext controlStatement() throws RecognitionException {
		ControlStatementContext _localctx = new ControlStatementContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_controlStatement);
		int _la;
		try {
			setState(152);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__13:
				_localctx = new ContinueStatementContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(143);
				match(T__13);
				setState(144);
				match(T__8);
				}
				break;
			case T__14:
				_localctx = new BreakStatementContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(145);
				match(T__14);
				setState(146);
				match(T__8);
				}
				break;
			case T__15:
				_localctx = new ReturnStatementContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(147);
				match(T__15);
				setState(149);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__42) | (1L << T__43) | (1L << T__44) | (1L << IDENTIFIER) | (1L << INTEGER) | (1L << STRING))) != 0)) {
					{
					setState(148);
					expression(0);
					}
				}

				setState(151);
				match(T__8);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionStatementContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExpressionStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiracleListener ) ((MiracleListener)listener).enterExpressionStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiracleListener ) ((MiracleListener)listener).exitExpressionStatement(this);
		}
	}

	public final ExpressionStatementContext expressionStatement() throws RecognitionException {
		ExpressionStatementContext _localctx = new ExpressionStatementContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_expressionStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(154);
			expression(0);
			setState(155);
			match(T__8);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EmptyStatementContext extends ParserRuleContext {
		public EmptyStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_emptyStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiracleListener ) ((MiracleListener)listener).enterEmptyStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiracleListener ) ((MiracleListener)listener).exitEmptyStatement(this);
		}
	}

	public final EmptyStatementContext emptyStatement() throws RecognitionException {
		EmptyStatementContext _localctx = new EmptyStatementContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_emptyStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(157);
			match(T__8);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypenameContext extends ParserRuleContext {
		public TerminalNode BASETYPE() { return getToken(MiracleParser.BASETYPE, 0); }
		public TerminalNode IDENTIFIER() { return getToken(MiracleParser.IDENTIFIER, 0); }
		public TypenameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typename; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiracleListener ) ((MiracleListener)listener).enterTypename(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiracleListener ) ((MiracleListener)listener).exitTypename(this);
		}
	}

	public final TypenameContext typename() throws RecognitionException {
		TypenameContext _localctx = new TypenameContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_typename);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(159);
			_la = _input.LA(1);
			if ( !(_la==BASETYPE || _la==IDENTIFIER) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(164);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(160);
					match(T__16);
					setState(161);
					match(T__17);
					}
					} 
				}
				setState(166);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	 
		public ExpressionContext() { }
		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ConstantExpressionContext extends ExpressionContext {
		public ConstantContext constant() {
			return getRuleContext(ConstantContext.class,0);
		}
		public ConstantExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiracleListener ) ((MiracleListener)listener).enterConstantExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiracleListener ) ((MiracleListener)listener).exitConstantExpression(this);
		}
	}
	public static class XorExpressionContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public XorExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiracleListener ) ((MiracleListener)listener).enterXorExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiracleListener ) ((MiracleListener)listener).exitXorExpression(this);
		}
	}
	public static class SubscriptExpressionContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public SubscriptExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiracleListener ) ((MiracleListener)listener).enterSubscriptExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiracleListener ) ((MiracleListener)listener).exitSubscriptExpression(this);
		}
	}
	public static class NewExpressionContext extends ExpressionContext {
		public TypenameContext typename() {
			return getRuleContext(TypenameContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public NewExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiracleListener ) ((MiracleListener)listener).enterNewExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiracleListener ) ((MiracleListener)listener).exitNewExpression(this);
		}
	}
	public static class AssignExpressionContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public AssignExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiracleListener ) ((MiracleListener)listener).enterAssignExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiracleListener ) ((MiracleListener)listener).exitAssignExpression(this);
		}
	}
	public static class BraceExpressionContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public BraceExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiracleListener ) ((MiracleListener)listener).enterBraceExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiracleListener ) ((MiracleListener)listener).exitBraceExpression(this);
		}
	}
	public static class MultDivExpressionContext extends ExpressionContext {
		public Token operator;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public MultDivExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiracleListener ) ((MiracleListener)listener).enterMultDivExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiracleListener ) ((MiracleListener)listener).exitMultDivExpression(this);
		}
	}
	public static class MemberExpressionContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(MiracleParser.IDENTIFIER, 0); }
		public MemberExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiracleListener ) ((MiracleListener)listener).enterMemberExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiracleListener ) ((MiracleListener)listener).exitMemberExpression(this);
		}
	}
	public static class VariableExpressionContext extends ExpressionContext {
		public TerminalNode IDENTIFIER() { return getToken(MiracleParser.IDENTIFIER, 0); }
		public VariableExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiracleListener ) ((MiracleListener)listener).enterVariableExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiracleListener ) ((MiracleListener)listener).exitVariableExpression(this);
		}
	}
	public static class CompareExpressionContext extends ExpressionContext {
		public Token operator;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public CompareExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiracleListener ) ((MiracleListener)listener).enterCompareExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiracleListener ) ((MiracleListener)listener).exitCompareExpression(this);
		}
	}
	public static class OrExpressionContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public OrExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiracleListener ) ((MiracleListener)listener).enterOrExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiracleListener ) ((MiracleListener)listener).exitOrExpression(this);
		}
	}
	public static class AndExpressionContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public AndExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiracleListener ) ((MiracleListener)listener).enterAndExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiracleListener ) ((MiracleListener)listener).exitAndExpression(this);
		}
	}
	public static class LogicAndExpressionContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public LogicAndExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiracleListener ) ((MiracleListener)listener).enterLogicAndExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiracleListener ) ((MiracleListener)listener).exitLogicAndExpression(this);
		}
	}
	public static class PrefixExpressionContext extends ExpressionContext {
		public Token operator;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public PrefixExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiracleListener ) ((MiracleListener)listener).enterPrefixExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiracleListener ) ((MiracleListener)listener).exitPrefixExpression(this);
		}
	}
	public static class LogicOrExpressionContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public LogicOrExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiracleListener ) ((MiracleListener)listener).enterLogicOrExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiracleListener ) ((MiracleListener)listener).exitLogicOrExpression(this);
		}
	}
	public static class AddSubExpressionContext extends ExpressionContext {
		public Token operator;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public AddSubExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiracleListener ) ((MiracleListener)listener).enterAddSubExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiracleListener ) ((MiracleListener)listener).exitAddSubExpression(this);
		}
	}
	public static class SuffixExpressionContext extends ExpressionContext {
		public Token operator;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public SuffixExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiracleListener ) ((MiracleListener)listener).enterSuffixExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiracleListener ) ((MiracleListener)listener).exitSuffixExpression(this);
		}
	}
	public static class ShlShrExpressionContext extends ExpressionContext {
		public Token operator;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ShlShrExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiracleListener ) ((MiracleListener)listener).enterShlShrExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiracleListener ) ((MiracleListener)listener).exitShlShrExpression(this);
		}
	}
	public static class EqualityExpressionContext extends ExpressionContext {
		public Token operator;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public EqualityExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiracleListener ) ((MiracleListener)listener).enterEqualityExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiracleListener ) ((MiracleListener)listener).exitEqualityExpression(this);
		}
	}
	public static class FunctionCallExpressionContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public FunctionCallExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiracleListener ) ((MiracleListener)listener).enterFunctionCallExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiracleListener ) ((MiracleListener)listener).exitFunctionCallExpression(this);
		}
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
			setState(191);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__42:
			case T__43:
			case T__44:
			case INTEGER:
			case STRING:
				{
				_localctx = new ConstantExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(168);
				constant();
				}
				break;
			case IDENTIFIER:
				{
				_localctx = new VariableExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(169);
				match(IDENTIFIER);
				}
				break;
			case T__4:
				{
				_localctx = new BraceExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(170);
				match(T__4);
				setState(171);
				expression(0);
				setState(172);
				match(T__6);
				}
				break;
			case T__19:
			case T__20:
			case T__21:
			case T__22:
			case T__23:
			case T__24:
				{
				_localctx = new PrefixExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(174);
				((PrefixExpressionContext)_localctx).operator = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24))) != 0)) ) {
					((PrefixExpressionContext)_localctx).operator = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(175);
				expression(13);
				}
				break;
			case T__25:
				{
				_localctx = new NewExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(176);
				match(T__25);
				setState(177);
				typename();
				setState(189);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
				case 1:
					{
					setState(183); 
					_errHandler.sync(this);
					_alt = 1;
					do {
						switch (_alt) {
						case 1:
							{
							{
							setState(178);
							match(T__16);
							setState(180);
							_errHandler.sync(this);
							_la = _input.LA(1);
							if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__42) | (1L << T__43) | (1L << T__44) | (1L << IDENTIFIER) | (1L << INTEGER) | (1L << STRING))) != 0)) {
								{
								setState(179);
								expression(0);
								}
							}

							setState(182);
							match(T__17);
							}
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						setState(185); 
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
					} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
					}
					break;
				case 2:
					{
					setState(187);
					match(T__4);
					setState(188);
					match(T__6);
					}
					break;
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(251);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(249);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
					case 1:
						{
						_localctx = new MultDivExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(193);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(194);
						((MultDivExpressionContext)_localctx).operator = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__26) | (1L << T__27) | (1L << T__28))) != 0)) ) {
							((MultDivExpressionContext)_localctx).operator = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(195);
						expression(12);
						}
						break;
					case 2:
						{
						_localctx = new AddSubExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(196);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(197);
						((AddSubExpressionContext)_localctx).operator = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__22 || _la==T__23) ) {
							((AddSubExpressionContext)_localctx).operator = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(198);
						expression(11);
						}
						break;
					case 3:
						{
						_localctx = new ShlShrExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(199);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(200);
						((ShlShrExpressionContext)_localctx).operator = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__29 || _la==T__30) ) {
							((ShlShrExpressionContext)_localctx).operator = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(201);
						expression(10);
						}
						break;
					case 4:
						{
						_localctx = new CompareExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(202);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(203);
						((CompareExpressionContext)_localctx).operator = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__31) | (1L << T__32) | (1L << T__33) | (1L << T__34))) != 0)) ) {
							((CompareExpressionContext)_localctx).operator = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(204);
						expression(9);
						}
						break;
					case 5:
						{
						_localctx = new EqualityExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(205);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(206);
						((EqualityExpressionContext)_localctx).operator = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__35 || _la==T__36) ) {
							((EqualityExpressionContext)_localctx).operator = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(207);
						expression(8);
						}
						break;
					case 6:
						{
						_localctx = new AndExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(208);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(209);
						match(T__37);
						setState(210);
						expression(7);
						}
						break;
					case 7:
						{
						_localctx = new XorExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(211);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(212);
						match(T__38);
						setState(213);
						expression(6);
						}
						break;
					case 8:
						{
						_localctx = new OrExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(214);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(215);
						match(T__39);
						setState(216);
						expression(5);
						}
						break;
					case 9:
						{
						_localctx = new LogicAndExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(217);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(218);
						match(T__40);
						setState(219);
						expression(4);
						}
						break;
					case 10:
						{
						_localctx = new LogicOrExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(220);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(221);
						match(T__41);
						setState(222);
						expression(3);
						}
						break;
					case 11:
						{
						_localctx = new AssignExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(223);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(224);
						match(T__7);
						setState(225);
						expression(1);
						}
						break;
					case 12:
						{
						_localctx = new FunctionCallExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(226);
						if (!(precpred(_ctx, 17))) throw new FailedPredicateException(this, "precpred(_ctx, 17)");
						setState(227);
						match(T__4);
						setState(229);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__42) | (1L << T__43) | (1L << T__44) | (1L << IDENTIFIER) | (1L << INTEGER) | (1L << STRING))) != 0)) {
							{
							setState(228);
							expression(0);
							}
						}

						setState(235);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==T__5) {
							{
							{
							setState(231);
							match(T__5);
							setState(232);
							expression(0);
							}
							}
							setState(237);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(238);
						match(T__6);
						}
						break;
					case 13:
						{
						_localctx = new SubscriptExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(239);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(240);
						match(T__16);
						setState(241);
						expression(0);
						setState(242);
						match(T__17);
						}
						break;
					case 14:
						{
						_localctx = new MemberExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(244);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(245);
						match(T__18);
						setState(246);
						match(IDENTIFIER);
						}
						break;
					case 15:
						{
						_localctx = new SuffixExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(247);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(248);
						((SuffixExpressionContext)_localctx).operator = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__19 || _la==T__20) ) {
							((SuffixExpressionContext)_localctx).operator = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						break;
					}
					} 
				}
				setState(253);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ConstantContext extends ParserRuleContext {
		public TerminalNode INTEGER() { return getToken(MiracleParser.INTEGER, 0); }
		public TerminalNode STRING() { return getToken(MiracleParser.STRING, 0); }
		public ConstantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constant; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiracleListener ) ((MiracleListener)listener).enterConstant(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiracleListener ) ((MiracleListener)listener).exitConstant(this);
		}
	}

	public final ConstantContext constant() throws RecognitionException {
		ConstantContext _localctx = new ConstantContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_constant);
		int _la;
		try {
			setState(258);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INTEGER:
				enterOuterAlt(_localctx, 1);
				{
				setState(254);
				match(INTEGER);
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 2);
				{
				setState(255);
				match(STRING);
				}
				break;
			case T__42:
			case T__43:
				enterOuterAlt(_localctx, 3);
				{
				setState(256);
				_la = _input.LA(1);
				if ( !(_la==T__42 || _la==T__43) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case T__44:
				enterOuterAlt(_localctx, 4);
				{
				setState(257);
				match(T__44);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 12:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\38\u0107\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\3\2\3\2\7\2\"\n\2\f\2\16\2"+
		"%\13\2\3\3\3\3\3\3\3\3\5\3+\n\3\3\3\3\3\3\3\7\3\60\n\3\f\3\16\3\63\13"+
		"\3\3\3\3\3\3\4\5\48\n\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4@\n\4\3\4\3\4\3\4\3"+
		"\4\7\4F\n\4\f\4\16\4I\13\4\3\4\3\4\3\4\7\4N\n\4\f\4\16\4Q\13\4\3\4\3\4"+
		"\3\5\5\5V\n\5\3\5\3\5\3\5\3\5\5\5\\\n\5\3\5\3\5\3\6\3\6\7\6b\n\6\f\6\16"+
		"\6e\13\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7p\n\7\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\5\by\n\b\3\t\3\t\3\t\5\t~\n\t\3\t\3\t\5\t\u0082\n\t\3\t\3"+
		"\t\5\t\u0086\n\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u0090\n\t\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\5\n\u0098\n\n\3\n\5\n\u009b\n\n\3\13\3\13\3\13\3\f\3"+
		"\f\3\r\3\r\3\r\7\r\u00a5\n\r\f\r\16\r\u00a8\13\r\3\16\3\16\3\16\3\16\3"+
		"\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\5\16\u00b7\n\16\3\16\6\16"+
		"\u00ba\n\16\r\16\16\16\u00bb\3\16\3\16\5\16\u00c0\n\16\5\16\u00c2\n\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\5\16\u00e8\n\16\3\16\3\16\7\16"+
		"\u00ec\n\16\f\16\16\16\u00ef\13\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\7\16\u00fc\n\16\f\16\16\16\u00ff\13\16\3\17\3\17"+
		"\3\17\3\17\5\17\u0105\n\17\3\17\2\3\32\20\2\4\6\b\n\f\16\20\22\24\26\30"+
		"\32\34\2\13\4\2\60\60\62\62\3\2\26\33\3\2\35\37\3\2\31\32\3\2 !\3\2\""+
		"%\3\2&\'\3\2\26\27\3\2-.\2\u0130\2#\3\2\2\2\4&\3\2\2\2\6\67\3\2\2\2\b"+
		"U\3\2\2\2\n_\3\2\2\2\fo\3\2\2\2\16q\3\2\2\2\20\u008f\3\2\2\2\22\u009a"+
		"\3\2\2\2\24\u009c\3\2\2\2\26\u009f\3\2\2\2\30\u00a1\3\2\2\2\32\u00c1\3"+
		"\2\2\2\34\u0104\3\2\2\2\36\"\5\4\3\2\37\"\5\6\4\2 \"\5\b\5\2!\36\3\2\2"+
		"\2!\37\3\2\2\2! \3\2\2\2\"%\3\2\2\2#!\3\2\2\2#$\3\2\2\2$\3\3\2\2\2%#\3"+
		"\2\2\2&\'\7\3\2\2\'*\7\62\2\2()\7\4\2\2)+\7\62\2\2*(\3\2\2\2*+\3\2\2\2"+
		"+,\3\2\2\2,\61\7\5\2\2-\60\5\6\4\2.\60\5\b\5\2/-\3\2\2\2/.\3\2\2\2\60"+
		"\63\3\2\2\2\61/\3\2\2\2\61\62\3\2\2\2\62\64\3\2\2\2\63\61\3\2\2\2\64\65"+
		"\7\6\2\2\65\5\3\2\2\2\668\7\61\2\2\67\66\3\2\2\2\678\3\2\2\289\3\2\2\2"+
		"9:\5\30\r\2:;\7\62\2\2;?\7\7\2\2<=\5\30\r\2=>\7\62\2\2>@\3\2\2\2?<\3\2"+
		"\2\2?@\3\2\2\2@G\3\2\2\2AB\7\b\2\2BC\5\30\r\2CD\7\62\2\2DF\3\2\2\2EA\3"+
		"\2\2\2FI\3\2\2\2GE\3\2\2\2GH\3\2\2\2HJ\3\2\2\2IG\3\2\2\2JK\7\t\2\2KO\7"+
		"\5\2\2LN\5\f\7\2ML\3\2\2\2NQ\3\2\2\2OM\3\2\2\2OP\3\2\2\2PR\3\2\2\2QO\3"+
		"\2\2\2RS\7\6\2\2S\7\3\2\2\2TV\7\61\2\2UT\3\2\2\2UV\3\2\2\2VW\3\2\2\2W"+
		"X\5\30\r\2X[\7\62\2\2YZ\7\n\2\2Z\\\5\32\16\2[Y\3\2\2\2[\\\3\2\2\2\\]\3"+
		"\2\2\2]^\7\13\2\2^\t\3\2\2\2_c\7\5\2\2`b\5\f\7\2a`\3\2\2\2be\3\2\2\2c"+
		"a\3\2\2\2cd\3\2\2\2df\3\2\2\2ec\3\2\2\2fg\7\6\2\2g\13\3\2\2\2hp\5\n\6"+
		"\2ip\5\b\5\2jp\5\16\b\2kp\5\20\t\2lp\5\22\n\2mp\5\24\13\2np\5\26\f\2o"+
		"h\3\2\2\2oi\3\2\2\2oj\3\2\2\2ok\3\2\2\2ol\3\2\2\2om\3\2\2\2on\3\2\2\2"+
		"p\r\3\2\2\2qr\7\f\2\2rs\7\7\2\2st\5\32\16\2tu\7\t\2\2ux\5\f\7\2vw\7\r"+
		"\2\2wy\5\f\7\2xv\3\2\2\2xy\3\2\2\2y\17\3\2\2\2z{\7\16\2\2{}\7\7\2\2|~"+
		"\5\32\16\2}|\3\2\2\2}~\3\2\2\2~\177\3\2\2\2\177\u0081\7\13\2\2\u0080\u0082"+
		"\5\32\16\2\u0081\u0080\3\2\2\2\u0081\u0082\3\2\2\2\u0082\u0083\3\2\2\2"+
		"\u0083\u0085\7\13\2\2\u0084\u0086\5\32\16\2\u0085\u0084\3\2\2\2\u0085"+
		"\u0086\3\2\2\2\u0086\u0087\3\2\2\2\u0087\u0088\7\t\2\2\u0088\u0090\5\f"+
		"\7\2\u0089\u008a\7\17\2\2\u008a\u008b\7\7\2\2\u008b\u008c\5\32\16\2\u008c"+
		"\u008d\7\t\2\2\u008d\u008e\5\f\7\2\u008e\u0090\3\2\2\2\u008fz\3\2\2\2"+
		"\u008f\u0089\3\2\2\2\u0090\21\3\2\2\2\u0091\u0092\7\20\2\2\u0092\u009b"+
		"\7\13\2\2\u0093\u0094\7\21\2\2\u0094\u009b\7\13\2\2\u0095\u0097\7\22\2"+
		"\2\u0096\u0098\5\32\16\2\u0097\u0096\3\2\2\2\u0097\u0098\3\2\2\2\u0098"+
		"\u0099\3\2\2\2\u0099\u009b\7\13\2\2\u009a\u0091\3\2\2\2\u009a\u0093\3"+
		"\2\2\2\u009a\u0095\3\2\2\2\u009b\23\3\2\2\2\u009c\u009d\5\32\16\2\u009d"+
		"\u009e\7\13\2\2\u009e\25\3\2\2\2\u009f\u00a0\7\13\2\2\u00a0\27\3\2\2\2"+
		"\u00a1\u00a6\t\2\2\2\u00a2\u00a3\7\23\2\2\u00a3\u00a5\7\24\2\2\u00a4\u00a2"+
		"\3\2\2\2\u00a5\u00a8\3\2\2\2\u00a6\u00a4\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7"+
		"\31\3\2\2\2\u00a8\u00a6\3\2\2\2\u00a9\u00aa\b\16\1\2\u00aa\u00c2\5\34"+
		"\17\2\u00ab\u00c2\7\62\2\2\u00ac\u00ad\7\7\2\2\u00ad\u00ae\5\32\16\2\u00ae"+
		"\u00af\7\t\2\2\u00af\u00c2\3\2\2\2\u00b0\u00b1\t\3\2\2\u00b1\u00c2\5\32"+
		"\16\17\u00b2\u00b3\7\34\2\2\u00b3\u00bf\5\30\r\2\u00b4\u00b6\7\23\2\2"+
		"\u00b5\u00b7\5\32\16\2\u00b6\u00b5\3\2\2\2\u00b6\u00b7\3\2\2\2\u00b7\u00b8"+
		"\3\2\2\2\u00b8\u00ba\7\24\2\2\u00b9\u00b4\3\2\2\2\u00ba\u00bb\3\2\2\2"+
		"\u00bb\u00b9\3\2\2\2\u00bb\u00bc\3\2\2\2\u00bc\u00c0\3\2\2\2\u00bd\u00be"+
		"\7\7\2\2\u00be\u00c0\7\t\2\2\u00bf\u00b9\3\2\2\2\u00bf\u00bd\3\2\2\2\u00bf"+
		"\u00c0\3\2\2\2\u00c0\u00c2\3\2\2\2\u00c1\u00a9\3\2\2\2\u00c1\u00ab\3\2"+
		"\2\2\u00c1\u00ac\3\2\2\2\u00c1\u00b0\3\2\2\2\u00c1\u00b2\3\2\2\2\u00c2"+
		"\u00fd\3\2\2\2\u00c3\u00c4\f\r\2\2\u00c4\u00c5\t\4\2\2\u00c5\u00fc\5\32"+
		"\16\16\u00c6\u00c7\f\f\2\2\u00c7\u00c8\t\5\2\2\u00c8\u00fc\5\32\16\r\u00c9"+
		"\u00ca\f\13\2\2\u00ca\u00cb\t\6\2\2\u00cb\u00fc\5\32\16\f\u00cc\u00cd"+
		"\f\n\2\2\u00cd\u00ce\t\7\2\2\u00ce\u00fc\5\32\16\13\u00cf\u00d0\f\t\2"+
		"\2\u00d0\u00d1\t\b\2\2\u00d1\u00fc\5\32\16\n\u00d2\u00d3\f\b\2\2\u00d3"+
		"\u00d4\7(\2\2\u00d4\u00fc\5\32\16\t\u00d5\u00d6\f\7\2\2\u00d6\u00d7\7"+
		")\2\2\u00d7\u00fc\5\32\16\b\u00d8\u00d9\f\6\2\2\u00d9\u00da\7*\2\2\u00da"+
		"\u00fc\5\32\16\7\u00db\u00dc\f\5\2\2\u00dc\u00dd\7+\2\2\u00dd\u00fc\5"+
		"\32\16\6\u00de\u00df\f\4\2\2\u00df\u00e0\7,\2\2\u00e0\u00fc\5\32\16\5"+
		"\u00e1\u00e2\f\3\2\2\u00e2\u00e3\7\n\2\2\u00e3\u00fc\5\32\16\3\u00e4\u00e5"+
		"\f\23\2\2\u00e5\u00e7\7\7\2\2\u00e6\u00e8\5\32\16\2\u00e7\u00e6\3\2\2"+
		"\2\u00e7\u00e8\3\2\2\2\u00e8\u00ed\3\2\2\2\u00e9\u00ea\7\b\2\2\u00ea\u00ec"+
		"\5\32\16\2\u00eb\u00e9\3\2\2\2\u00ec\u00ef\3\2\2\2\u00ed\u00eb\3\2\2\2"+
		"\u00ed\u00ee\3\2\2\2\u00ee\u00f0\3\2\2\2\u00ef\u00ed\3\2\2\2\u00f0\u00fc"+
		"\7\t\2\2\u00f1\u00f2\f\22\2\2\u00f2\u00f3\7\23\2\2\u00f3\u00f4\5\32\16"+
		"\2\u00f4\u00f5\7\24\2\2\u00f5\u00fc\3\2\2\2\u00f6\u00f7\f\21\2\2\u00f7"+
		"\u00f8\7\25\2\2\u00f8\u00fc\7\62\2\2\u00f9\u00fa\f\20\2\2\u00fa\u00fc"+
		"\t\t\2\2\u00fb\u00c3\3\2\2\2\u00fb\u00c6\3\2\2\2\u00fb\u00c9\3\2\2\2\u00fb"+
		"\u00cc\3\2\2\2\u00fb\u00cf\3\2\2\2\u00fb\u00d2\3\2\2\2\u00fb\u00d5\3\2"+
		"\2\2\u00fb\u00d8\3\2\2\2\u00fb\u00db\3\2\2\2\u00fb\u00de\3\2\2\2\u00fb"+
		"\u00e1\3\2\2\2\u00fb\u00e4\3\2\2\2\u00fb\u00f1\3\2\2\2\u00fb\u00f6\3\2"+
		"\2\2\u00fb\u00f9\3\2\2\2\u00fc\u00ff\3\2\2\2\u00fd\u00fb\3\2\2\2\u00fd"+
		"\u00fe\3\2\2\2\u00fe\33\3\2\2\2\u00ff\u00fd\3\2\2\2\u0100\u0105\7\63\2"+
		"\2\u0101\u0105\7\64\2\2\u0102\u0105\t\n\2\2\u0103\u0105\7/\2\2\u0104\u0100"+
		"\3\2\2\2\u0104\u0101\3\2\2\2\u0104\u0102\3\2\2\2\u0104\u0103\3\2\2\2\u0105"+
		"\35\3\2\2\2 !#*/\61\67?GOU[cox}\u0081\u0085\u008f\u0097\u009a\u00a6\u00b6"+
		"\u00bb\u00bf\u00c1\u00e7\u00ed\u00fb\u00fd\u0104";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}