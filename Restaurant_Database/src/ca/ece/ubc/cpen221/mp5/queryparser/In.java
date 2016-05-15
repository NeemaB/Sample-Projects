package ca.ece.ubc.cpen221.mp5.queryparser;

import ca.ece.ubc.cpen221.mp5.Restaurant;

public class In implements Query {
	String inString;

	/**
	 * 
	 * @param inString
	 *            name of neighborhood
	 */

	public In(String inString) {
		this.inString = inString;
	}

	@Override
	public boolean evaluate(Restaurant currentRest) {
		String in = inString.substring(1, (inString.length() - 1));

		if (currentRest.getNeighborhoods().contains(in)) {
			return true;
		}
		return false;
	}

}