package ca.ece.ubc.cpen221.mp5.statlearning;
import ca.ece.ubc.cpen221.mp5.Restaurant;
import ca.ece.ubc.cpen221.mp5.RestaurantDB;

/**
 * Longitude represents an MP5Function who's feature function returns the
 * Longitude of a restaurant.
 * 
 */
public class Longitude implements MP5Function {

	@Override
	public double f(Restaurant yelpRestaurant, RestaurantDB db) {
		return yelpRestaurant.getLongitude();
	}

}
