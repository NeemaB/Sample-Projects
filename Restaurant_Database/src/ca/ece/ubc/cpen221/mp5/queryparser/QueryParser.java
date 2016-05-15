package ca.ece.ubc.cpen221.mp5.queryparser;

// Generated from Query.g4 by ANTLR 4.4
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class QueryParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__4=1, T__3=2, T__2=3, T__1=4, T__0=5, OR=6, AND=7, STRING=8, RANGE=9, 
		LPAREN=10, RPAREN=11, WHITESPACE=12;
	public static final String[] tokenNames = {
		"<INVALID>", "'rating'", "'name'", "'price'", "'in'", "'category'", "'||'", 
		"'&&'", "STRING", "RANGE", "'('", "')'", "WHITESPACE"
	};
	public static final int
		RULE_root = 0, RULE_orexpr = 1, RULE_andexpr = 2, RULE_atom = 3, RULE_in = 4, 
		RULE_category = 5, RULE_name = 6, RULE_rating = 7, RULE_price = 8;
	public static final String[] ruleNames = {
		"root", "orexpr", "andexpr", "atom", "in", "category", "name", "rating", 
		"price"
	};

	@Override
	public String getGrammarFileName() { return "Query.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public QueryParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class RootContext extends ParserRuleContext {
		public OrexprContext orexpr() {
			return getRuleContext(OrexprContext.class,0);
		}
		public RootContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_root; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).enterRoot(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).exitRoot(this);
		}
	}

	public final RootContext root() throws RecognitionException {
		RootContext _localctx = new RootContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_root);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(18); orexpr();
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

	public static class OrexprContext extends ParserRuleContext {
		public AndexprContext andexpr(int i) {
			return getRuleContext(AndexprContext.class,i);
		}
		public List<TerminalNode> OR() { return getTokens(QueryParser.OR); }
		public List<AndexprContext> andexpr() {
			return getRuleContexts(AndexprContext.class);
		}
		public TerminalNode OR(int i) {
			return getToken(QueryParser.OR, i);
		}
		public OrexprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_orexpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).enterOrexpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).exitOrexpr(this);
		}
	}

	public final OrexprContext orexpr() throws RecognitionException {
		OrexprContext _localctx = new OrexprContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_orexpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(20); andexpr();
			setState(25);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR) {
				{
				{
				setState(21); match(OR);
				setState(22); andexpr();
				}
				}
				setState(27);
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

	public static class AndexprContext extends ParserRuleContext {
		public AtomContext atom(int i) {
			return getRuleContext(AtomContext.class,i);
		}
		public TerminalNode AND(int i) {
			return getToken(QueryParser.AND, i);
		}
		public List<TerminalNode> AND() { return getTokens(QueryParser.AND); }
		public List<AtomContext> atom() {
			return getRuleContexts(AtomContext.class);
		}
		public AndexprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_andexpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).enterAndexpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).exitAndexpr(this);
		}
	}

	public final AndexprContext andexpr() throws RecognitionException {
		AndexprContext _localctx = new AndexprContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_andexpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(28); atom();
			setState(33);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND) {
				{
				{
				setState(29); match(AND);
				setState(30); atom();
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

	public static class AtomContext extends ParserRuleContext {
		public InContext in() {
			return getRuleContext(InContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(QueryParser.LPAREN, 0); }
		public PriceContext price() {
			return getRuleContext(PriceContext.class,0);
		}
		public OrexprContext orexpr() {
			return getRuleContext(OrexprContext.class,0);
		}
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(QueryParser.RPAREN, 0); }
		public RatingContext rating() {
			return getRuleContext(RatingContext.class,0);
		}
		public CategoryContext category() {
			return getRuleContext(CategoryContext.class,0);
		}
		public AtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).enterAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).exitAtom(this);
		}
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_atom);
		try {
			setState(45);
			switch (_input.LA(1)) {
			case T__1:
				enterOuterAlt(_localctx, 1);
				{
				setState(36); in();
				}
				break;
			case T__0:
				enterOuterAlt(_localctx, 2);
				{
				setState(37); category();
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 3);
				{
				setState(38); rating();
				}
				break;
			case T__2:
				enterOuterAlt(_localctx, 4);
				{
				setState(39); price();
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 5);
				{
				setState(40); name();
				}
				break;
			case LPAREN:
				enterOuterAlt(_localctx, 6);
				{
				setState(41); match(LPAREN);
				setState(42); orexpr();
				setState(43); match(RPAREN);
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

	public static class InContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(QueryParser.LPAREN, 0); }
		public TerminalNode STRING() { return getToken(QueryParser.STRING, 0); }
		public TerminalNode RPAREN() { return getToken(QueryParser.RPAREN, 0); }
		public InContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_in; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).enterIn(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).exitIn(this);
		}
	}

	public final InContext in() throws RecognitionException {
		InContext _localctx = new InContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_in);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(47); match(T__1);
			setState(48); match(LPAREN);
			setState(49); match(STRING);
			setState(50); match(RPAREN);
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

	public static class CategoryContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(QueryParser.LPAREN, 0); }
		public TerminalNode STRING() { return getToken(QueryParser.STRING, 0); }
		public TerminalNode RPAREN() { return getToken(QueryParser.RPAREN, 0); }
		public CategoryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_category; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).enterCategory(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).exitCategory(this);
		}
	}

	public final CategoryContext category() throws RecognitionException {
		CategoryContext _localctx = new CategoryContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_category);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(52); match(T__0);
			setState(53); match(LPAREN);
			setState(54); match(STRING);
			setState(55); match(RPAREN);
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

	public static class NameContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(QueryParser.LPAREN, 0); }
		public TerminalNode STRING() { return getToken(QueryParser.STRING, 0); }
		public TerminalNode RPAREN() { return getToken(QueryParser.RPAREN, 0); }
		public NameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).enterName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).exitName(this);
		}
	}

	public final NameContext name() throws RecognitionException {
		NameContext _localctx = new NameContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(57); match(T__3);
			setState(58); match(LPAREN);
			setState(59); match(STRING);
			setState(60); match(RPAREN);
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

	public static class RatingContext extends ParserRuleContext {
		public TerminalNode RANGE() { return getToken(QueryParser.RANGE, 0); }
		public TerminalNode LPAREN() { return getToken(QueryParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(QueryParser.RPAREN, 0); }
		public RatingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rating; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).enterRating(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).exitRating(this);
		}
	}

	public final RatingContext rating() throws RecognitionException {
		RatingContext _localctx = new RatingContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_rating);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(62); match(T__4);
			setState(63); match(LPAREN);
			setState(64); match(RANGE);
			setState(65); match(RPAREN);
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

	public static class PriceContext extends ParserRuleContext {
		public TerminalNode RANGE() { return getToken(QueryParser.RANGE, 0); }
		public TerminalNode LPAREN() { return getToken(QueryParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(QueryParser.RPAREN, 0); }
		public PriceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_price; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).enterPrice(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).exitPrice(this);
		}
	}

	public final PriceContext price() throws RecognitionException {
		PriceContext _localctx = new PriceContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_price);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(67); match(T__2);
			setState(68); match(LPAREN);
			setState(69); match(RANGE);
			setState(70); match(RPAREN);
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

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\16K\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\3\2\3\2\3\3"+
		"\3\3\3\3\7\3\32\n\3\f\3\16\3\35\13\3\3\4\3\4\3\4\7\4\"\n\4\f\4\16\4%\13"+
		"\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5\60\n\5\3\6\3\6\3\6\3\6\3\6"+
		"\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\2\2\13\2\4\6\b\n\f\16\20\22\2\2H\2\24\3\2\2\2\4\26\3\2"+
		"\2\2\6\36\3\2\2\2\b/\3\2\2\2\n\61\3\2\2\2\f\66\3\2\2\2\16;\3\2\2\2\20"+
		"@\3\2\2\2\22E\3\2\2\2\24\25\5\4\3\2\25\3\3\2\2\2\26\33\5\6\4\2\27\30\7"+
		"\b\2\2\30\32\5\6\4\2\31\27\3\2\2\2\32\35\3\2\2\2\33\31\3\2\2\2\33\34\3"+
		"\2\2\2\34\5\3\2\2\2\35\33\3\2\2\2\36#\5\b\5\2\37 \7\t\2\2 \"\5\b\5\2!"+
		"\37\3\2\2\2\"%\3\2\2\2#!\3\2\2\2#$\3\2\2\2$\7\3\2\2\2%#\3\2\2\2&\60\5"+
		"\n\6\2\'\60\5\f\7\2(\60\5\20\t\2)\60\5\22\n\2*\60\5\16\b\2+,\7\f\2\2,"+
		"-\5\4\3\2-.\7\r\2\2.\60\3\2\2\2/&\3\2\2\2/\'\3\2\2\2/(\3\2\2\2/)\3\2\2"+
		"\2/*\3\2\2\2/+\3\2\2\2\60\t\3\2\2\2\61\62\7\6\2\2\62\63\7\f\2\2\63\64"+
		"\7\n\2\2\64\65\7\r\2\2\65\13\3\2\2\2\66\67\7\7\2\2\678\7\f\2\289\7\n\2"+
		"\29:\7\r\2\2:\r\3\2\2\2;<\7\4\2\2<=\7\f\2\2=>\7\n\2\2>?\7\r\2\2?\17\3"+
		"\2\2\2@A\7\3\2\2AB\7\f\2\2BC\7\13\2\2CD\7\r\2\2D\21\3\2\2\2EF\7\5\2\2"+
		"FG\7\f\2\2GH\7\13\2\2HI\7\r\2\2I\23\3\2\2\2\5\33#/";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}