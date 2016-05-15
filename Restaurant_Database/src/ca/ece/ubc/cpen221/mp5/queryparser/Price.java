package ca.ece.ubc.cpen221.mp5.queryparser;

import ca.ece.ubc.cpen221.mp5.Restaurant;

public class Price implements Query {
	String range;

	/**
	 * 
	 * @param range
	 *            price range in the form 'a..b' where a is the lower bound of
	 *            the range and b is the upper bound of the range
	 */

	public Price(String range) {
		this.range = range;
	}

	@Override
	public boolean evaluate(Restaurant currentRest) {
		int left = 0;
		int right = 0;

		if (range.charAt(0) == '1') {
			left = 1;
		}
		if (range.charAt(0) == '2') {
			left = 2;
		}
		if (range.charAt(0) == '3') {
			left = 3;
		}
		if (range.charAt(0) == '4') {
			left = 4;
		}

		if (range.charAt(3) == '1') {
			right = 1;
		}
		if (range.charAt(3) == '2') {
			right = 2;
		}
		if (range.charAt(3) == '3') {
			right = 3;
		}
		if (range.charAt(3) == '4') {
			right = 4;
		}

		if (currentRest.getPrice() <= right && currentRest.getPrice() >= left) {
			return true;
		}
		return false;
	}

}