package ca.ece.ubc.cpen221.mp5.queryparser;

// Generated from Query.g4 by ANTLR 4.4
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class QueryLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__4=1, T__3=2, T__2=3, T__1=4, T__0=5, OR=6, AND=7, STRING=8, RANGE=9, 
		LPAREN=10, RPAREN=11, WHITESPACE=12;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"'\\u0000'", "'\\u0001'", "'\\u0002'", "'\\u0003'", "'\\u0004'", "'\\u0005'", 
		"'\\u0006'", "'\\u0007'", "'\b'", "'\t'", "'\n'", "'\\u000B'", "'\f'"
	};
	public static final String[] ruleNames = {
		"T__4", "T__3", "T__2", "T__1", "T__0", "OR", "AND", "STRING", "RANGE", 
		"LPAREN", "RPAREN", "WHITESPACE"
	};


	public QueryLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Query.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\16X\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t\6\tB\n\t\r\t\16\tC\3\t\3\t\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\13\3\13\3\f\3\f\3\r\6\rS\n\r\r\r\16\rT\3\r\3\r\2\2\16"+
		"\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\3\2\5\6\2\""+
		"\"\62;C\\c|\3\2\63\67\5\2\13\f\17\17\"\"Y\2\3\3\2\2\2\2\5\3\2\2\2\2\7"+
		"\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2"+
		"\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\3\33\3\2\2\2\5"+
		"\"\3\2\2\2\7\'\3\2\2\2\t-\3\2\2\2\13\60\3\2\2\2\r9\3\2\2\2\17<\3\2\2\2"+
		"\21?\3\2\2\2\23G\3\2\2\2\25M\3\2\2\2\27O\3\2\2\2\31R\3\2\2\2\33\34\7t"+
		"\2\2\34\35\7c\2\2\35\36\7v\2\2\36\37\7k\2\2\37 \7p\2\2 !\7i\2\2!\4\3\2"+
		"\2\2\"#\7p\2\2#$\7c\2\2$%\7o\2\2%&\7g\2\2&\6\3\2\2\2\'(\7r\2\2()\7t\2"+
		"\2)*\7k\2\2*+\7e\2\2+,\7g\2\2,\b\3\2\2\2-.\7k\2\2./\7p\2\2/\n\3\2\2\2"+
		"\60\61\7e\2\2\61\62\7c\2\2\62\63\7v\2\2\63\64\7g\2\2\64\65\7i\2\2\65\66"+
		"\7q\2\2\66\67\7t\2\2\678\7{\2\28\f\3\2\2\29:\7~\2\2:;\7~\2\2;\16\3\2\2"+
		"\2<=\7(\2\2=>\7(\2\2>\20\3\2\2\2?A\7$\2\2@B\t\2\2\2A@\3\2\2\2BC\3\2\2"+
		"\2CA\3\2\2\2CD\3\2\2\2DE\3\2\2\2EF\7$\2\2F\22\3\2\2\2GH\t\3\2\2HI\7\60"+
		"\2\2IJ\7\60\2\2JK\3\2\2\2KL\t\3\2\2L\24\3\2\2\2MN\7*\2\2N\26\3\2\2\2O"+
		"P\7+\2\2P\30\3\2\2\2QS\t\4\2\2RQ\3\2\2\2ST\3\2\2\2TR\3\2\2\2TU\3\2\2\2"+
		"UV\3\2\2\2VW\b\r\2\2W\32\3\2\2\2\6\2ACT\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}