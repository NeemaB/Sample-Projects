package ca.ece.ubc.cpen221.mp5.queryparser;

import ca.ece.ubc.cpen221.mp5.Restaurant;

/**
 * 
 * @param currentRest
 *            a given Restaurant
 * 
 * @return true if the Query is true, false if the Query is false
 */

public interface Query {

	public boolean evaluate(Restaurant currentRest);
}