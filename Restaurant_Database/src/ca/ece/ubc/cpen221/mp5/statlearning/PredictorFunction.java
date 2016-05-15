package ca.ece.ubc.cpen221.mp5.statlearning;

import java.util.*;

import ca.ece.ubc.cpen221.mp5.Restaurant;
import ca.ece.ubc.cpen221.mp5.RestaurantDB;
import ca.ece.ubc.cpen221.mp5.User;

/**
 * a PredictorFunction represents a line or constant function where the x values
 * are values associated with a given featureFunction and y is the predicted
 * rating.
 * 
 */

public class PredictorFunction implements MP5Function {
	private double a = 0.0;
	private double b = 0.0;
	private double r_squared = 0.0;
	MP5Function featureFunction;

	/**
	 * creates a new PredictorFunction
	 * 
	 * @param a
	 *            the intercept of the line
	 * @param b
	 *            the slope of the line
	 * @param r_squared
	 *            the regression coefficient of the line
	 * @param featureFunction
	 *            featureFunction associated with this line
	 */

	public PredictorFunction(double a, double b, double r_squared, MP5Function featureFunction) {
		this.a = a;
		this.b = b;
		this.r_squared = r_squared;
		this.featureFunction = featureFunction;
	}

	@Override
	public double f(Restaurant yelpRestaurant, RestaurantDB db) {
		double x = featureFunction.f(yelpRestaurant, db);

		double y = ((b * x) + a);
		if (y > 5.0) {
			y = 5.0;
		}
		if (y < 1.0) {
			y = 1.0;
		}

		return y;
	}

	/**
	 * 
	 * @return R2 (r_squared), which is an estimate of the quality of the
	 *         predictor,
	 */
	public double getR_Squared() {
		return r_squared;
	}

}