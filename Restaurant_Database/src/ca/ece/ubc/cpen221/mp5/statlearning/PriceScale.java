package ca.ece.ubc.cpen221.mp5.statlearning;

import ca.ece.ubc.cpen221.mp5.Restaurant;
import ca.ece.ubc.cpen221.mp5.RestaurantDB;

/**
 * PriceScale represents an MP5Function who's feature function returns the price
 * scale of a restaurant.
 * 
 */

public class PriceScale implements MP5Function {

	@Override
	public double f(Restaurant yelpRestaurant, RestaurantDB db) {
		return yelpRestaurant.getPrice();
	}

}