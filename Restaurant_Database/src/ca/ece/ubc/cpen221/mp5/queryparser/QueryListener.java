package ca.ece.ubc.cpen221.mp5.queryparser;

// Generated from Query.g4 by ANTLR 4.4
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link QueryParser}.
 */
public interface QueryListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link QueryParser#in}.
	 * @param ctx the parse tree
	 */
	void enterIn(@NotNull QueryParser.InContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#in}.
	 * @param ctx the parse tree
	 */
	void exitIn(@NotNull QueryParser.InContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#price}.
	 * @param ctx the parse tree
	 */
	void enterPrice(@NotNull QueryParser.PriceContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#price}.
	 * @param ctx the parse tree
	 */
	void exitPrice(@NotNull QueryParser.PriceContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#root}.
	 * @param ctx the parse tree
	 */
	void enterRoot(@NotNull QueryParser.RootContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#root}.
	 * @param ctx the parse tree
	 */
	void exitRoot(@NotNull QueryParser.RootContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#orexpr}.
	 * @param ctx the parse tree
	 */
	void enterOrexpr(@NotNull QueryParser.OrexprContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#orexpr}.
	 * @param ctx the parse tree
	 */
	void exitOrexpr(@NotNull QueryParser.OrexprContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#name}.
	 * @param ctx the parse tree
	 */
	void enterName(@NotNull QueryParser.NameContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#name}.
	 * @param ctx the parse tree
	 */
	void exitName(@NotNull QueryParser.NameContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#rating}.
	 * @param ctx the parse tree
	 */
	void enterRating(@NotNull QueryParser.RatingContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#rating}.
	 * @param ctx the parse tree
	 */
	void exitRating(@NotNull QueryParser.RatingContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtom(@NotNull QueryParser.AtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtom(@NotNull QueryParser.AtomContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#category}.
	 * @param ctx the parse tree
	 */
	void enterCategory(@NotNull QueryParser.CategoryContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#category}.
	 * @param ctx the parse tree
	 */
	void exitCategory(@NotNull QueryParser.CategoryContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#andexpr}.
	 * @param ctx the parse tree
	 */
	void enterAndexpr(@NotNull QueryParser.AndexprContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#andexpr}.
	 * @param ctx the parse tree
	 */
	void exitAndexpr(@NotNull QueryParser.AndexprContext ctx);
}