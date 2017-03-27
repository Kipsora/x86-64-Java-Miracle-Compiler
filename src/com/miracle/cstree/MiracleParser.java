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
	static {
		RuntimeMetaData.checkVersion("4.6", RuntimeMetaData.VERSION);
	}

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
			new PredictionContextCache();
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

	private static final String[] _LITERAL_NAMES = {
			null, "'class'", "'extends'", "'{'", "'}'", "'('", "','", "')'", "'='",
			"';'", "'if'", "'else'", "'for'", "'while'", "'continue'", "'break'",
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

	public MiracleParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
	}

	public static class MiracleContext extends ParserRuleContext {
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

		public MiracleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
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

	public static class ClassDeclarationStatementContext extends ParserRuleContext {
		public List<TerminalNode> IDENTIFIER() {
			return getTokens(MiracleParser.IDENTIFIER);
		}

		public TerminalNode IDENTIFIER(int i) {
			return getToken(MiracleParser.IDENTIFIER, i);
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

		public ClassDeclarationStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
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
				if (_la == T__1) {
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
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << DECORATOR) | (1L << IDENTIFIER))) != 0)) {
					{
						setState(45);
						_errHandler.sync(this);
						switch (getInterpreter().adaptivePredict(_input, 3, _ctx)) {
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
					setState(49);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(50);
				match(T__3);
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

	public static class FunctionDeclarationStatementContext extends ParserRuleContext {
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

		public TerminalNode DECORATOR() {
			return getToken(MiracleParser.DECORATOR, 0);
		}

		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}

		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class, i);
		}

		public FunctionDeclarationStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
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
				if (_la == DECORATOR) {
					{
						setState(52);
						match(DECORATOR);
					}
				}

				setState(55);
				typename(0);
				setState(56);
				match(IDENTIFIER);
				setState(57);
				match(T__4);
				setState(61);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << IDENTIFIER))) != 0)) {
					{
						setState(58);
						typename(0);
						setState(59);
						match(IDENTIFIER);
					}
				}

				setState(69);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == T__5) {
					{
						{
							setState(63);
							match(T__5);
							setState(64);
							typename(0);
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
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__4) | (1L << T__8) | (1L << T__9) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__24) | (1L << T__25) | (1L << T__26) | (1L << T__27) | (1L << T__28) | (1L << T__29) | (1L << T__30) | (1L << T__47) | (1L << T__48) | (1L << T__49) | (1L << DECORATOR) | (1L << IDENTIFIER) | (1L << INTEGER) | (1L << STRING))) != 0)) {
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
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableDeclarationStatementContext extends ParserRuleContext {
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

		public VariableDeclarationStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
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
				if (_la == DECORATOR) {
					{
						setState(82);
						match(DECORATOR);
					}
				}

				setState(85);
				typename(0);
				setState(86);
				match(IDENTIFIER);
				setState(89);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == T__7) {
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
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlockStatementContext extends ParserRuleContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}

		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class, i);
		}

		public BlockStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
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
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__4) | (1L << T__8) | (1L << T__9) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__24) | (1L << T__25) | (1L << T__26) | (1L << T__27) | (1L << T__28) | (1L << T__29) | (1L << T__30) | (1L << T__47) | (1L << T__48) | (1L << T__49) | (1L << DECORATOR) | (1L << IDENTIFIER) | (1L << INTEGER) | (1L << STRING))) != 0)) {
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
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
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

		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
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

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_statement);
		try {
			setState(109);
			_errHandler.sync(this);
			switch (getInterpreter().adaptivePredict(_input, 12, _ctx)) {
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
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SelectionStatementContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class, 0);
		}

		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}

		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class, i);
		}

		public SelectionStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
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
				switch (getInterpreter().adaptivePredict(_input, 13, _ctx)) {
					case 1: {
						setState(116);
						match(T__10);
						setState(117);
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

	public static class IterationStatementContext extends ParserRuleContext {
		public IterationStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_iterationStatement;
		}

		public IterationStatementContext() {
		}

		public void copyFrom(IterationStatementContext ctx) {
			super.copyFrom(ctx);
		}
	}

	public static class WhileStatementContext extends IterationStatementContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class, 0);
		}

		public StatementContext statement() {
			return getRuleContext(StatementContext.class, 0);
		}

		public WhileStatementContext(IterationStatementContext ctx) {
			copyFrom(ctx);
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
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}

		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class, i);
		}

		public StatementContext statement() {
			return getRuleContext(StatementContext.class, 0);
		}

		public ForStatementContext(IterationStatementContext ctx) {
			copyFrom(ctx);
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

	public final IterationStatementContext iterationStatement() throws RecognitionException {
		IterationStatementContext _localctx = new IterationStatementContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_iterationStatement);
		try {
			setState(136);
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
					setState(122);
					expression(0);
					setState(123);
					match(T__8);
					setState(124);
					expression(0);
					setState(125);
					match(T__8);
					setState(126);
					expression(0);
					setState(127);
					match(T__6);
					setState(128);
					statement();
				}
				break;
				case T__12:
					_localctx = new WhileStatementContext(_localctx);
					enterOuterAlt(_localctx, 2);
				{
					setState(130);
					match(T__12);
					setState(131);
					match(T__4);
					setState(132);
					expression(0);
					setState(133);
					match(T__6);
					setState(134);
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

	public static class ControlStatementContext extends ParserRuleContext {
		public ControlStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_controlStatement;
		}

		public ControlStatementContext() {
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
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class, 0);
		}

		public ReturnStatementContext(ControlStatementContext ctx) {
			copyFrom(ctx);
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

	public final ControlStatementContext controlStatement() throws RecognitionException {
		ControlStatementContext _localctx = new ControlStatementContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_controlStatement);
		int _la;
		try {
			setState(147);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
				case T__13:
					_localctx = new ContinueStatementContext(_localctx);
					enterOuterAlt(_localctx, 1);
				{
					setState(138);
					match(T__13);
					setState(139);
					match(T__8);
				}
				break;
				case T__14:
					_localctx = new BreakStatementContext(_localctx);
					enterOuterAlt(_localctx, 2);
				{
					setState(140);
					match(T__14);
					setState(141);
					match(T__8);
				}
				break;
				case T__15:
					_localctx = new ReturnStatementContext(_localctx);
					enterOuterAlt(_localctx, 3);
				{
					setState(142);
					match(T__15);
					setState(144);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__24) | (1L << T__25) | (1L << T__26) | (1L << T__27) | (1L << T__28) | (1L << T__29) | (1L << T__30) | (1L << T__47) | (1L << T__48) | (1L << T__49) | (1L << IDENTIFIER) | (1L << INTEGER) | (1L << STRING))) != 0)) {
						{
							setState(143);
							expression(0);
						}
					}

					setState(146);
					match(T__8);
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

	public static class ExpressionStatementContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class, 0);
		}

		public ExpressionStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
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

	public final ExpressionStatementContext expressionStatement() throws RecognitionException {
		ExpressionStatementContext _localctx = new ExpressionStatementContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_expressionStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(149);
				expression(0);
				setState(150);
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

	public final EmptyStatementContext emptyStatement() throws RecognitionException {
		EmptyStatementContext _localctx = new EmptyStatementContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_emptyStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(152);
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

	public static class TypenameContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() {
			return getToken(MiracleParser.IDENTIFIER, 0);
		}

		public TypenameContext typename() {
			return getRuleContext(TypenameContext.class, 0);
		}

		public TypenameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
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
				setState(160);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
					case T__16: {
						setState(155);
						match(T__16);
					}
					break;
					case T__17: {
						setState(156);
						match(T__17);
					}
					break;
					case T__18: {
						setState(157);
						match(T__18);
					}
					break;
					case T__19: {
						setState(158);
						match(T__19);
					}
					break;
					case IDENTIFIER: {
						setState(159);
						match(IDENTIFIER);
					}
					break;
					default:
						throw new NoViableAltException(this);
				}
				_ctx.stop = _input.LT(-1);
				setState(170);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input, 19, _ctx);
				while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
					if (_alt == 1) {
						if (_parseListeners != null) triggerExitRuleEvent();
						_prevctx = _localctx;
						{
							{
								_localctx = new TypenameContext(_parentctx, _parentState);
								pushNewRecursionContext(_localctx, _startState, RULE_typename);
								setState(162);
								if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
								setState(164);
								_errHandler.sync(this);
								_alt = 1;
								do {
									switch (_alt) {
										case 1: {
											{
												setState(163);
												match(T__20);
											}
										}
										break;
										default:
											throw new NoViableAltException(this);
									}
									setState(166);
									_errHandler.sync(this);
									_alt = getInterpreter().adaptivePredict(_input, 18, _ctx);
								} while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER);
							}
						}
					}
					setState(172);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 19, _ctx);
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

	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_expression;
		}

		public ExpressionContext() {
		}

		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}

	public static class ConstantExpressionContext extends ExpressionContext {
		public ConstantContext constant() {
			return getRuleContext(ConstantContext.class, 0);
		}

		public ConstantExpressionContext(ExpressionContext ctx) {
			copyFrom(ctx);
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
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}

		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class, i);
		}

		public XorExpressionContext(ExpressionContext ctx) {
			copyFrom(ctx);
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
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}

		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class, i);
		}

		public SubscriptExpressionContext(ExpressionContext ctx) {
			copyFrom(ctx);
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
		public TypenameContext typename() {
			return getRuleContext(TypenameContext.class, 0);
		}

		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}

		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class, i);
		}

		public NewExpressionContext(ExpressionContext ctx) {
			copyFrom(ctx);
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
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}

		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class, i);
		}

		public AssignExpressionContext(ExpressionContext ctx) {
			copyFrom(ctx);
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
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class, 0);
		}

		public BraceExpressionContext(ExpressionContext ctx) {
			copyFrom(ctx);
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

		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}

		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class, i);
		}

		public MultDivExpressionContext(ExpressionContext ctx) {
			copyFrom(ctx);
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
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class, 0);
		}

		public TerminalNode IDENTIFIER() {
			return getToken(MiracleParser.IDENTIFIER, 0);
		}

		public MemberExpressionContext(ExpressionContext ctx) {
			copyFrom(ctx);
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
		public TerminalNode IDENTIFIER() {
			return getToken(MiracleParser.IDENTIFIER, 0);
		}

		public VariableExpressionContext(ExpressionContext ctx) {
			copyFrom(ctx);
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

		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}

		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class, i);
		}

		public CompareExpressionContext(ExpressionContext ctx) {
			copyFrom(ctx);
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
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}

		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class, i);
		}

		public OrExpressionContext(ExpressionContext ctx) {
			copyFrom(ctx);
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
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}

		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class, i);
		}

		public AndExpressionContext(ExpressionContext ctx) {
			copyFrom(ctx);
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
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}

		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class, i);
		}

		public LogicAndExpressionContext(ExpressionContext ctx) {
			copyFrom(ctx);
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

		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class, 0);
		}

		public PrefixExpressionContext(ExpressionContext ctx) {
			copyFrom(ctx);
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
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}

		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class, i);
		}

		public LogicOrExpressionContext(ExpressionContext ctx) {
			copyFrom(ctx);
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

		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}

		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class, i);
		}

		public AddSubExpressionContext(ExpressionContext ctx) {
			copyFrom(ctx);
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

		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class, 0);
		}

		public SuffixExpressionContext(ExpressionContext ctx) {
			copyFrom(ctx);
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

		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}

		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class, i);
		}

		public ShlShrExpressionContext(ExpressionContext ctx) {
			copyFrom(ctx);
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

		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}

		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class, i);
		}

		public EqualityExpressionContext(ExpressionContext ctx) {
			copyFrom(ctx);
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
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}

		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class, i);
		}

		public FunctionCallExpressionContext(ExpressionContext ctx) {
			copyFrom(ctx);
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
				setState(199);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
					case T__47:
					case T__48:
					case T__49:
					case INTEGER:
					case STRING: {
						_localctx = new ConstantExpressionContext(_localctx);
						_ctx = _localctx;
						_prevctx = _localctx;

						setState(174);
						constant();
					}
					break;
					case IDENTIFIER: {
						_localctx = new VariableExpressionContext(_localctx);
						_ctx = _localctx;
						_prevctx = _localctx;
						setState(175);
						match(IDENTIFIER);
					}
					break;
					case T__4: {
						_localctx = new BraceExpressionContext(_localctx);
						_ctx = _localctx;
						_prevctx = _localctx;
						setState(176);
						match(T__4);
						setState(177);
						expression(0);
						setState(178);
						match(T__6);
					}
					break;
					case T__24:
					case T__25:
					case T__26:
					case T__27:
					case T__28:
					case T__29: {
						_localctx = new PrefixExpressionContext(_localctx);
						_ctx = _localctx;
						_prevctx = _localctx;
						setState(180);
						((PrefixExpressionContext) _localctx).operator = _input.LT(1);
						_la = _input.LA(1);
						if (!((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__24) | (1L << T__25) | (1L << T__26) | (1L << T__27) | (1L << T__28) | (1L << T__29))) != 0))) {
							((PrefixExpressionContext) _localctx).operator = (Token) _errHandler.recoverInline(this);
						} else {
							if (_input.LA(1) == Token.EOF) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(181);
						expression(13);
					}
					break;
					case T__30: {
						_localctx = new NewExpressionContext(_localctx);
						_ctx = _localctx;
						_prevctx = _localctx;
						setState(182);
						match(T__30);
						setState(183);
						typename(0);
						setState(190);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input, 20, _ctx);
						while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
							if (_alt == 1) {
								{
									{
										setState(184);
										match(T__21);
										setState(185);
										expression(0);
										setState(186);
										match(T__22);
									}
								}
							}
							setState(192);
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input, 20, _ctx);
						}
						setState(196);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input, 21, _ctx);
						while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
							if (_alt == 1) {
								{
									{
										setState(193);
										match(T__20);
									}
								}
							}
							setState(198);
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input, 21, _ctx);
						}
					}
					break;
					default:
						throw new NoViableAltException(this);
				}
				_ctx.stop = _input.LT(-1);
				setState(259);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input, 26, _ctx);
				while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
					if (_alt == 1) {
						if (_parseListeners != null) triggerExitRuleEvent();
						_prevctx = _localctx;
						{
							setState(257);
							_errHandler.sync(this);
							switch (getInterpreter().adaptivePredict(_input, 25, _ctx)) {
								case 1: {
									_localctx = new MultDivExpressionContext(new ExpressionContext(_parentctx, _parentState));
									pushNewRecursionContext(_localctx, _startState, RULE_expression);
									setState(201);
									if (!(precpred(_ctx, 11)))
										throw new FailedPredicateException(this, "precpred(_ctx, 11)");
									setState(202);
									((MultDivExpressionContext) _localctx).operator = _input.LT(1);
									_la = _input.LA(1);
									if (!((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__31) | (1L << T__32) | (1L << T__33))) != 0))) {
										((MultDivExpressionContext) _localctx).operator = (Token) _errHandler.recoverInline(this);
									} else {
										if (_input.LA(1) == Token.EOF) matchedEOF = true;
										_errHandler.reportMatch(this);
										consume();
									}
									setState(203);
									expression(12);
								}
								break;
								case 2: {
									_localctx = new AddSubExpressionContext(new ExpressionContext(_parentctx, _parentState));
									pushNewRecursionContext(_localctx, _startState, RULE_expression);
									setState(204);
									if (!(precpred(_ctx, 10)))
										throw new FailedPredicateException(this, "precpred(_ctx, 10)");
									setState(205);
									((AddSubExpressionContext) _localctx).operator = _input.LT(1);
									_la = _input.LA(1);
									if (!(_la == T__27 || _la == T__28)) {
										((AddSubExpressionContext) _localctx).operator = (Token) _errHandler.recoverInline(this);
									} else {
										if (_input.LA(1) == Token.EOF) matchedEOF = true;
										_errHandler.reportMatch(this);
										consume();
									}
									setState(206);
									expression(11);
								}
								break;
								case 3: {
									_localctx = new ShlShrExpressionContext(new ExpressionContext(_parentctx, _parentState));
									pushNewRecursionContext(_localctx, _startState, RULE_expression);
									setState(207);
									if (!(precpred(_ctx, 9)))
										throw new FailedPredicateException(this, "precpred(_ctx, 9)");
									setState(208);
									((ShlShrExpressionContext) _localctx).operator = _input.LT(1);
									_la = _input.LA(1);
									if (!(_la == T__34 || _la == T__35)) {
										((ShlShrExpressionContext) _localctx).operator = (Token) _errHandler.recoverInline(this);
									} else {
										if (_input.LA(1) == Token.EOF) matchedEOF = true;
										_errHandler.reportMatch(this);
										consume();
									}
									setState(209);
									expression(10);
								}
								break;
								case 4: {
									_localctx = new CompareExpressionContext(new ExpressionContext(_parentctx, _parentState));
									pushNewRecursionContext(_localctx, _startState, RULE_expression);
									setState(210);
									if (!(precpred(_ctx, 8)))
										throw new FailedPredicateException(this, "precpred(_ctx, 8)");
									setState(211);
									((CompareExpressionContext) _localctx).operator = _input.LT(1);
									_la = _input.LA(1);
									if (!((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__36) | (1L << T__37) | (1L << T__38) | (1L << T__39))) != 0))) {
										((CompareExpressionContext) _localctx).operator = (Token) _errHandler.recoverInline(this);
									} else {
										if (_input.LA(1) == Token.EOF) matchedEOF = true;
										_errHandler.reportMatch(this);
										consume();
									}
									setState(212);
									expression(9);
								}
								break;
								case 5: {
									_localctx = new EqualityExpressionContext(new ExpressionContext(_parentctx, _parentState));
									pushNewRecursionContext(_localctx, _startState, RULE_expression);
									setState(213);
									if (!(precpred(_ctx, 7)))
										throw new FailedPredicateException(this, "precpred(_ctx, 7)");
									setState(214);
									((EqualityExpressionContext) _localctx).operator = _input.LT(1);
									_la = _input.LA(1);
									if (!(_la == T__40 || _la == T__41)) {
										((EqualityExpressionContext) _localctx).operator = (Token) _errHandler.recoverInline(this);
									} else {
										if (_input.LA(1) == Token.EOF) matchedEOF = true;
										_errHandler.reportMatch(this);
										consume();
									}
									setState(215);
									expression(8);
								}
								break;
								case 6: {
									_localctx = new AndExpressionContext(new ExpressionContext(_parentctx, _parentState));
									pushNewRecursionContext(_localctx, _startState, RULE_expression);
									setState(216);
									if (!(precpred(_ctx, 6)))
										throw new FailedPredicateException(this, "precpred(_ctx, 6)");
									setState(217);
									match(T__42);
									setState(218);
									expression(7);
								}
								break;
								case 7: {
									_localctx = new XorExpressionContext(new ExpressionContext(_parentctx, _parentState));
									pushNewRecursionContext(_localctx, _startState, RULE_expression);
									setState(219);
									if (!(precpred(_ctx, 5)))
										throw new FailedPredicateException(this, "precpred(_ctx, 5)");
									setState(220);
									match(T__43);
									setState(221);
									expression(6);
								}
								break;
								case 8: {
									_localctx = new OrExpressionContext(new ExpressionContext(_parentctx, _parentState));
									pushNewRecursionContext(_localctx, _startState, RULE_expression);
									setState(222);
									if (!(precpred(_ctx, 4)))
										throw new FailedPredicateException(this, "precpred(_ctx, 4)");
									setState(223);
									match(T__44);
									setState(224);
									expression(5);
								}
								break;
								case 9: {
									_localctx = new LogicAndExpressionContext(new ExpressionContext(_parentctx, _parentState));
									pushNewRecursionContext(_localctx, _startState, RULE_expression);
									setState(225);
									if (!(precpred(_ctx, 3)))
										throw new FailedPredicateException(this, "precpred(_ctx, 3)");
									setState(226);
									match(T__45);
									setState(227);
									expression(4);
								}
								break;
								case 10: {
									_localctx = new LogicOrExpressionContext(new ExpressionContext(_parentctx, _parentState));
									pushNewRecursionContext(_localctx, _startState, RULE_expression);
									setState(228);
									if (!(precpred(_ctx, 2)))
										throw new FailedPredicateException(this, "precpred(_ctx, 2)");
									setState(229);
									match(T__46);
									setState(230);
									expression(3);
								}
								break;
								case 11: {
									_localctx = new AssignExpressionContext(new ExpressionContext(_parentctx, _parentState));
									pushNewRecursionContext(_localctx, _startState, RULE_expression);
									setState(231);
									if (!(precpred(_ctx, 1)))
										throw new FailedPredicateException(this, "precpred(_ctx, 1)");
									setState(232);
									match(T__7);
									setState(233);
									expression(1);
								}
								break;
								case 12: {
									_localctx = new FunctionCallExpressionContext(new ExpressionContext(_parentctx, _parentState));
									pushNewRecursionContext(_localctx, _startState, RULE_expression);
									setState(234);
									if (!(precpred(_ctx, 17)))
										throw new FailedPredicateException(this, "precpred(_ctx, 17)");
									setState(235);
									match(T__4);
									setState(237);
									_errHandler.sync(this);
									_la = _input.LA(1);
									if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__24) | (1L << T__25) | (1L << T__26) | (1L << T__27) | (1L << T__28) | (1L << T__29) | (1L << T__30) | (1L << T__47) | (1L << T__48) | (1L << T__49) | (1L << IDENTIFIER) | (1L << INTEGER) | (1L << STRING))) != 0)) {
										{
											setState(236);
											expression(0);
										}
									}

									setState(243);
									_errHandler.sync(this);
									_la = _input.LA(1);
									while (_la == T__5) {
										{
											{
												setState(239);
												match(T__5);
												setState(240);
												expression(0);
											}
										}
										setState(245);
										_errHandler.sync(this);
										_la = _input.LA(1);
									}
									setState(246);
									match(T__6);
								}
								break;
								case 13: {
									_localctx = new SubscriptExpressionContext(new ExpressionContext(_parentctx, _parentState));
									pushNewRecursionContext(_localctx, _startState, RULE_expression);
									setState(247);
									if (!(precpred(_ctx, 16)))
										throw new FailedPredicateException(this, "precpred(_ctx, 16)");
									setState(248);
									match(T__21);
									setState(249);
									expression(0);
									setState(250);
									match(T__22);
								}
								break;
								case 14: {
									_localctx = new MemberExpressionContext(new ExpressionContext(_parentctx, _parentState));
									pushNewRecursionContext(_localctx, _startState, RULE_expression);
									setState(252);
									if (!(precpred(_ctx, 15)))
										throw new FailedPredicateException(this, "precpred(_ctx, 15)");
									setState(253);
									match(T__23);
									setState(254);
									match(IDENTIFIER);
								}
								break;
								case 15: {
									_localctx = new SuffixExpressionContext(new ExpressionContext(_parentctx, _parentState));
									pushNewRecursionContext(_localctx, _startState, RULE_expression);
									setState(255);
									if (!(precpred(_ctx, 14)))
										throw new FailedPredicateException(this, "precpred(_ctx, 14)");
									setState(256);
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
					setState(261);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 26, _ctx);
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

	public static class ConstantContext extends ParserRuleContext {
		public TerminalNode INTEGER() {
			return getToken(MiracleParser.INTEGER, 0);
		}

		public TerminalNode STRING() {
			return getToken(MiracleParser.STRING, 0);
		}

		public ConstantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
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

	public final ConstantContext constant() throws RecognitionException {
		ConstantContext _localctx = new ConstantContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_constant);
		int _la;
		try {
			setState(266);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
				case INTEGER:
					enterOuterAlt(_localctx, 1);
				{
					setState(262);
					match(INTEGER);
				}
				break;
				case STRING:
					enterOuterAlt(_localctx, 2);
				{
					setState(263);
					match(STRING);
				}
				break;
				case T__47:
				case T__48:
					enterOuterAlt(_localctx, 3);
				{
					setState(264);
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
					setState(265);
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
				return precpred(_ctx, 17);
			case 13:
				return precpred(_ctx, 16);
			case 14:
				return precpred(_ctx, 15);
			case 15:
				return precpred(_ctx, 14);
		}
		return true;
	}

	public static final String _serializedATN =
			"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3<\u010f\4\2\t\2\4" +
					"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t" +
					"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\3\2\3\2\7\2\"\n\2\f\2\16\2" +
					"%\13\2\3\3\3\3\3\3\3\3\5\3+\n\3\3\3\3\3\3\3\7\3\60\n\3\f\3\16\3\63\13" +
					"\3\3\3\3\3\3\4\5\48\n\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4@\n\4\3\4\3\4\3\4\3" +
					"\4\7\4F\n\4\f\4\16\4I\13\4\3\4\3\4\3\4\7\4N\n\4\f\4\16\4Q\13\4\3\4\3\4" +
					"\3\5\5\5V\n\5\3\5\3\5\3\5\3\5\5\5\\\n\5\3\5\3\5\3\6\3\6\7\6b\n\6\f\6\16" +
					"\6e\13\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7p\n\7\3\b\3\b\3\b\3\b" +
					"\3\b\3\b\3\b\5\by\n\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t" +
					"\3\t\3\t\3\t\3\t\5\t\u008b\n\t\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u0093\n\n\3" +
					"\n\5\n\u0096\n\n\3\13\3\13\3\13\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\5\r\u00a3" +
					"\n\r\3\r\3\r\6\r\u00a7\n\r\r\r\16\r\u00a8\7\r\u00ab\n\r\f\r\16\r\u00ae" +
					"\13\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16" +
					"\3\16\3\16\7\16\u00bf\n\16\f\16\16\16\u00c2\13\16\3\16\7\16\u00c5\n\16" +
					"\f\16\16\16\u00c8\13\16\5\16\u00ca\n\16\3\16\3\16\3\16\3\16\3\16\3\16" +
					"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16" +
					"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16" +
					"\3\16\3\16\5\16\u00f0\n\16\3\16\3\16\7\16\u00f4\n\16\f\16\16\16\u00f7" +
					"\13\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\7\16\u0104" +
					"\n\16\f\16\16\16\u0107\13\16\3\17\3\17\3\17\3\17\5\17\u010d\n\17\3\17" +
					"\2\4\30\32\20\2\4\6\b\n\f\16\20\22\24\26\30\32\34\2\n\3\2\33 \3\2\"$\3" +
					"\2\36\37\3\2%&\3\2\'*\3\2+,\3\2\33\34\3\2\62\63\u0138\2#\3\2\2\2\4&\3" +
					"\2\2\2\6\67\3\2\2\2\bU\3\2\2\2\n_\3\2\2\2\fo\3\2\2\2\16q\3\2\2\2\20\u008a" +
					"\3\2\2\2\22\u0095\3\2\2\2\24\u0097\3\2\2\2\26\u009a\3\2\2\2\30\u00a2\3" +
					"\2\2\2\32\u00c9\3\2\2\2\34\u010c\3\2\2\2\36\"\5\4\3\2\37\"\5\6\4\2 \"" +
					"\5\b\5\2!\36\3\2\2\2!\37\3\2\2\2! \3\2\2\2\"%\3\2\2\2#!\3\2\2\2#$\3\2" +
					"\2\2$\3\3\2\2\2%#\3\2\2\2&\'\7\3\2\2\'*\7\66\2\2()\7\4\2\2)+\7\66\2\2" +
					"*(\3\2\2\2*+\3\2\2\2+,\3\2\2\2,\61\7\5\2\2-\60\5\6\4\2.\60\5\b\5\2/-\3" +
					"\2\2\2/.\3\2\2\2\60\63\3\2\2\2\61/\3\2\2\2\61\62\3\2\2\2\62\64\3\2\2\2" +
					"\63\61\3\2\2\2\64\65\7\6\2\2\65\5\3\2\2\2\668\7\65\2\2\67\66\3\2\2\2\67" +
					"8\3\2\2\289\3\2\2\29:\5\30\r\2:;\7\66\2\2;?\7\7\2\2<=\5\30\r\2=>\7\66" +
					"\2\2>@\3\2\2\2?<\3\2\2\2?@\3\2\2\2@G\3\2\2\2AB\7\b\2\2BC\5\30\r\2CD\7" +
					"\66\2\2DF\3\2\2\2EA\3\2\2\2FI\3\2\2\2GE\3\2\2\2GH\3\2\2\2HJ\3\2\2\2IG" +
					"\3\2\2\2JK\7\t\2\2KO\7\5\2\2LN\5\f\7\2ML\3\2\2\2NQ\3\2\2\2OM\3\2\2\2O" +
					"P\3\2\2\2PR\3\2\2\2QO\3\2\2\2RS\7\6\2\2S\7\3\2\2\2TV\7\65\2\2UT\3\2\2" +
					"\2UV\3\2\2\2VW\3\2\2\2WX\5\30\r\2X[\7\66\2\2YZ\7\n\2\2Z\\\5\32\16\2[Y" +
					"\3\2\2\2[\\\3\2\2\2\\]\3\2\2\2]^\7\13\2\2^\t\3\2\2\2_c\7\5\2\2`b\5\f\7" +
					"\2a`\3\2\2\2be\3\2\2\2ca\3\2\2\2cd\3\2\2\2df\3\2\2\2ec\3\2\2\2fg\7\6\2" +
					"\2g\13\3\2\2\2hp\5\n\6\2ip\5\b\5\2jp\5\16\b\2kp\5\20\t\2lp\5\22\n\2mp" +
					"\5\24\13\2np\5\26\f\2oh\3\2\2\2oi\3\2\2\2oj\3\2\2\2ok\3\2\2\2ol\3\2\2" +
					"\2om\3\2\2\2on\3\2\2\2p\r\3\2\2\2qr\7\f\2\2rs\7\7\2\2st\5\32\16\2tu\7" +
					"\t\2\2ux\5\f\7\2vw\7\r\2\2wy\5\f\7\2xv\3\2\2\2xy\3\2\2\2y\17\3\2\2\2z" +
					"{\7\16\2\2{|\7\7\2\2|}\5\32\16\2}~\7\13\2\2~\177\5\32\16\2\177\u0080\7" +
					"\13\2\2\u0080\u0081\5\32\16\2\u0081\u0082\7\t\2\2\u0082\u0083\5\f\7\2" +
					"\u0083\u008b\3\2\2\2\u0084\u0085\7\17\2\2\u0085\u0086\7\7\2\2\u0086\u0087" +
					"\5\32\16\2\u0087\u0088\7\t\2\2\u0088\u0089\5\f\7\2\u0089\u008b\3\2\2\2" +
					"\u008az\3\2\2\2\u008a\u0084\3\2\2\2\u008b\21\3\2\2\2\u008c\u008d\7\20" +
					"\2\2\u008d\u0096\7\13\2\2\u008e\u008f\7\21\2\2\u008f\u0096\7\13\2\2\u0090" +
					"\u0092\7\22\2\2\u0091\u0093\5\32\16\2\u0092\u0091\3\2\2\2\u0092\u0093" +
					"\3\2\2\2\u0093\u0094\3\2\2\2\u0094\u0096\7\13\2\2\u0095\u008c\3\2\2\2" +
					"\u0095\u008e\3\2\2\2\u0095\u0090\3\2\2\2\u0096\23\3\2\2\2\u0097\u0098" +
					"\5\32\16\2\u0098\u0099\7\13\2\2\u0099\25\3\2\2\2\u009a\u009b\7\13\2\2" +
					"\u009b\27\3\2\2\2\u009c\u009d\b\r\1\2\u009d\u00a3\7\23\2\2\u009e\u00a3" +
					"\7\24\2\2\u009f\u00a3\7\25\2\2\u00a0\u00a3\7\26\2\2\u00a1\u00a3\7\66\2" +
					"\2\u00a2\u009c\3\2\2\2\u00a2\u009e\3\2\2\2\u00a2\u009f\3\2\2\2\u00a2\u00a0" +
					"\3\2\2\2\u00a2\u00a1\3\2\2\2\u00a3\u00ac\3\2\2\2\u00a4\u00a6\f\3\2\2\u00a5" +
					"\u00a7\7\27\2\2\u00a6\u00a5\3\2\2\2\u00a7\u00a8\3\2\2\2\u00a8\u00a6\3" +
					"\2\2\2\u00a8\u00a9\3\2\2\2\u00a9\u00ab\3\2\2\2\u00aa\u00a4\3\2\2\2\u00ab" +
					"\u00ae\3\2\2\2\u00ac\u00aa\3\2\2\2\u00ac\u00ad\3\2\2\2\u00ad\31\3\2\2" +
					"\2\u00ae\u00ac\3\2\2\2\u00af\u00b0\b\16\1\2\u00b0\u00ca\5\34\17\2\u00b1" +
					"\u00ca\7\66\2\2\u00b2\u00b3\7\7\2\2\u00b3\u00b4\5\32\16\2\u00b4\u00b5" +
					"\7\t\2\2\u00b5\u00ca\3\2\2\2\u00b6\u00b7\t\2\2\2\u00b7\u00ca\5\32\16\17" +
					"\u00b8\u00b9\7!\2\2\u00b9\u00c0\5\30\r\2\u00ba\u00bb\7\30\2\2\u00bb\u00bc" +
					"\5\32\16\2\u00bc\u00bd\7\31\2\2\u00bd\u00bf\3\2\2\2\u00be\u00ba\3\2\2" +
					"\2\u00bf\u00c2\3\2\2\2\u00c0\u00be\3\2\2\2\u00c0\u00c1\3\2\2\2\u00c1\u00c6" +
					"\3\2\2\2\u00c2\u00c0\3\2\2\2\u00c3\u00c5\7\27\2\2\u00c4\u00c3\3\2\2\2" +
					"\u00c5\u00c8\3\2\2\2\u00c6\u00c4\3\2\2\2\u00c6\u00c7\3\2\2\2\u00c7\u00ca" +
					"\3\2\2\2\u00c8\u00c6\3\2\2\2\u00c9\u00af\3\2\2\2\u00c9\u00b1\3\2\2\2\u00c9" +
					"\u00b2\3\2\2\2\u00c9\u00b6\3\2\2\2\u00c9\u00b8\3\2\2\2\u00ca\u0105\3\2" +
					"\2\2\u00cb\u00cc\f\r\2\2\u00cc\u00cd\t\3\2\2\u00cd\u0104\5\32\16\16\u00ce" +
					"\u00cf\f\f\2\2\u00cf\u00d0\t\4\2\2\u00d0\u0104\5\32\16\r\u00d1\u00d2\f" +
					"\13\2\2\u00d2\u00d3\t\5\2\2\u00d3\u0104\5\32\16\f\u00d4\u00d5\f\n\2\2" +
					"\u00d5\u00d6\t\6\2\2\u00d6\u0104\5\32\16\13\u00d7\u00d8\f\t\2\2\u00d8" +
					"\u00d9\t\7\2\2\u00d9\u0104\5\32\16\n\u00da\u00db\f\b\2\2\u00db\u00dc\7" +
					"-\2\2\u00dc\u0104\5\32\16\t\u00dd\u00de\f\7\2\2\u00de\u00df\7.\2\2\u00df" +
					"\u0104\5\32\16\b\u00e0\u00e1\f\6\2\2\u00e1\u00e2\7/\2\2\u00e2\u0104\5" +
					"\32\16\7\u00e3\u00e4\f\5\2\2\u00e4\u00e5\7\60\2\2\u00e5\u0104\5\32\16" +
					"\6\u00e6\u00e7\f\4\2\2\u00e7\u00e8\7\61\2\2\u00e8\u0104\5\32\16\5\u00e9" +
					"\u00ea\f\3\2\2\u00ea\u00eb\7\n\2\2\u00eb\u0104\5\32\16\3\u00ec\u00ed\f" +
					"\23\2\2\u00ed\u00ef\7\7\2\2\u00ee\u00f0\5\32\16\2\u00ef\u00ee\3\2\2\2" +
					"\u00ef\u00f0\3\2\2\2\u00f0\u00f5\3\2\2\2\u00f1\u00f2\7\b\2\2\u00f2\u00f4" +
					"\5\32\16\2\u00f3\u00f1\3\2\2\2\u00f4\u00f7\3\2\2\2\u00f5\u00f3\3\2\2\2" +
					"\u00f5\u00f6\3\2\2\2\u00f6\u00f8\3\2\2\2\u00f7\u00f5\3\2\2\2\u00f8\u0104" +
					"\7\t\2\2\u00f9\u00fa\f\22\2\2\u00fa\u00fb\7\30\2\2\u00fb\u00fc\5\32\16" +
					"\2\u00fc\u00fd\7\31\2\2\u00fd\u0104\3\2\2\2\u00fe\u00ff\f\21\2\2\u00ff" +
					"\u0100\7\32\2\2\u0100\u0104\7\66\2\2\u0101\u0102\f\20\2\2\u0102\u0104" +
					"\t\b\2\2\u0103\u00cb\3\2\2\2\u0103\u00ce\3\2\2\2\u0103\u00d1\3\2\2\2\u0103" +
					"\u00d4\3\2\2\2\u0103\u00d7\3\2\2\2\u0103\u00da\3\2\2\2\u0103\u00dd\3\2" +
					"\2\2\u0103\u00e0\3\2\2\2\u0103\u00e3\3\2\2\2\u0103\u00e6\3\2\2\2\u0103" +
					"\u00e9\3\2\2\2\u0103\u00ec\3\2\2\2\u0103\u00f9\3\2\2\2\u0103\u00fe\3\2" +
					"\2\2\u0103\u0101\3\2\2\2\u0104\u0107\3\2\2\2\u0105\u0103\3\2\2\2\u0105" +
					"\u0106\3\2\2\2\u0106\33\3\2\2\2\u0107\u0105\3\2\2\2\u0108\u010d\7\67\2" +
					"\2\u0109\u010d\78\2\2\u010a\u010d\t\t\2\2\u010b\u010d\7\64\2\2\u010c\u0108" +
					"\3\2\2\2\u010c\u0109\3\2\2\2\u010c\u010a\3\2\2\2\u010c\u010b\3\2\2\2\u010d" +
					"\35\3\2\2\2\36!#*/\61\67?GOU[cox\u008a\u0092\u0095\u00a2\u00a8\u00ac\u00c0" +
					"\u00c6\u00c9\u00ef\u00f5\u0103\u0105\u010c";
	public static final ATN _ATN =
			new ATNDeserializer().deserialize(_serializedATN.toCharArray());

	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}