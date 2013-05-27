package engine.rendering;

public enum Font {

	A(0, "a"),
	B(1, "b"),
	C(2, "c"),
	D(3, "d"),
	E(4, "e"),
	F(5, "f"),
	G(6, "g"),
	H(7, "h"),
	I(8, "i"),
	J(9, "j"),
	K(10, "k"),
	L(11, "l"),
	M(12, "m"),
	N(13, "n"),
	O(14, "o"),
	P(15, "p"),
	Q(16, "q"),
	R(17, "r"),
	S(18, "s"),
	T(19, "t"),
	U(20, "u"),
	V(21, "v"),
	W(22, "w"),
	X(23, "x"),
	Y(24, "y"),
	Z(25, "z"),
	ZERO(26, "0"),
	ONE(27, "1"),
	TWO(28, "2"),
	THREE(29, "3"),
	FOUR(30, "4"),
	FIVE(31, "5"),
	SIX(32, "6"),
	SEVEN(33, "7"),
	EIGHT(34, "8"),
	NINE(35, "9"),
	SPACE(78, " "),
	POINT(36, "."),
	COlON(37, ","),
	QUOTE(38, "\""),
	QUESTION_MARK(39, "?"),
	EXCLAMATION_MARK(40, "!"),
	AT(41, "@"),
	UNDERSCORE(42, "_"),
	STAR(43, "*"),
	SHARP(44, "#"),
	DOLLAR(45, "$"),
	PERCENT(46, "%"),
	AND(47, "&"),
	LEFT_PARANTHESIS(48, "("),
	RIGHT_PARANTHESIS(49, ")"),
	PLUS(50, "+"),
	MINUS(51, "-"),
	SLASH(52, "/"),
	BACKSLASH(53, "\\"),
	DOUBLE_POINT(54, ":"),
	SEMICOLON(55, ";"),
	LESS_THAN(56, "<"),
	GREATER_THAN(57, ">"),
	EQUAL(58, "="),
	LEFT_BRACKET(59, "["),
	RIGHT_BRACKET(60, "]"),
	CIRCUMFLEX(61, "^"),
	LEFT_CURLYBRACKET(62, "{"),
	RIGHT_CURLYBRACKET(63, "}"),
	PIPE(64, "|");
	
	
	private int fontImageIndex;
	private String symbol;
	
	
	private Font(int fontImageIndex, String symbol) {
		this.fontImageIndex = fontImageIndex;
		this.symbol = symbol;
	}


	public int getFontImageIndex() {
		return fontImageIndex;
	}


	public String getSymbol() {
		return symbol;
	}
	
	
	
	
}
