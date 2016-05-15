package ca.ece.ubc.cpen221.mp5.queryparser;

import ca.ece.ubc.cpen221.mp5.Restaurant;

public class Category implements Query {
	String inString;

	/**
	 * 
	 * @param inString
	 *            name of category
	 */

	public Category(String inString) {
		this.inString = inString;
	}

	@Override
	public boolean evaluate(Restaurant currentRest) {
		String in = inString.substring(1, (inString.length() - 1));

		if (currentRest.getCategories().contains(in)) {
			return true;
		}
		return false;
	}

}