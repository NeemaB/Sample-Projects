package ca.ece.ubc.cpen221.mp5.queryparser;

import org.antlr.v4.runtime.tree.TerminalNode;

import ca.ece.ubc.cpen221.mp5.Restaurant;

public class Name implements Query {
	String inString;

	/**
	 * 
	 * @param string
	 *            restaurant name
	 */

	public Name(String string) {
		this.inString = string;
	}

	@Override
	public boolean evaluate(Restaurant currentRest) {
		String name = inString.substring(1, (inString.length() - 1));

		if (currentRest.getName().equals(name)) {
			return true;
		}
		return false;
	}

}