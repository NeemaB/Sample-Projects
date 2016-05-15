package ca.ece.ubc.cpen221.mp5.statlearning;

import java.io.*;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import ca.ece.ubc.cpen221.mp5.*;

public class Algorithms {

	/**
	 * Use k-means clustering to compute k clusters for the restaurants in the
	 * database.
	 * 
	 * To simplify, we will assume that longitude is equivalent to x and
	 * latitude is equivalent to y in euclidean space.
	 * 
	 * @param db
	 * @return
	 */
	public static List<Set<Restaurant>> kMeansClustering(int k, RestaurantDB db) throws IllegalArgumentException {
		HashSet<Restaurant> restaurants = db.getRestaurantSet();

		if (k <= 0 || restaurants == null) {
			throw new IllegalArgumentException();
		}
		// find the bounds of the 2d space within which we can apply the k-means
		// clustering algorithm.
		double temp1;
		double temp2;
		double maxLongitude = Integer.MIN_VALUE;
		double maxLatitude = 0;
		double minLongitude = Integer.MAX_VALUE;
		double minLatitude = Integer.MAX_VALUE;
		for (Restaurant r : restaurants) {
			temp1 = r.getLongitude();
			temp2 = r.getLatitude();
			if (temp1 > maxLongitude) {
				maxLongitude = temp1;
			}
			if (temp1 < minLongitude) {
				minLongitude = temp1;
			}
			if (temp2 > maxLatitude) {
				maxLatitude = temp2;
			}
			if (temp2 < minLatitude) {
				minLatitude = temp2;
			}
		}

		List<Centroid> centroids = new ArrayList<Centroid>();
		HashMap<Centroid, Set<Restaurant>> centroidClusters = new HashMap<Centroid, Set<Restaurant>>();

		// Create k centroids positioned randomly in the 2d space we
		// have defined

		double x;
		double y;
		for (int i = 0; i < k; i++) {
			x = minLongitude + (maxLongitude - minLongitude) * (Math.random());
			y = minLatitude + (maxLatitude - minLatitude) * (Math.random());

			centroids.add(new Centroid(x, y));
			centroidClusters.put(centroids.get(i), new HashSet<Restaurant>());

		}

		List<Set<Restaurant>> finalClusters = new ArrayList<Set<Restaurant>>();

		double minDistance = Integer.MAX_VALUE;
		double tempdistance;
		Centroid closestCentroid = null;
		int centroidIndex;
		Set<Restaurant> s;
		double avgX = 0;
		double avgY = 0;
		double offSetX = 0;
		double offSetY = 0;
		boolean Equilibrium = true;
		while (true) {
			// step 1 assign each restaurant to the centroid it is closest to

			// first clear all sets of restaurants for each centroid
			for (Centroid c : centroids) {
				centroidIndex = centroids.indexOf(c);
				s = centroidClusters.get(centroids.get(centroidIndex));
				s.clear();
			}
			// assign each restaurant to the closest centroid
			for (Restaurant r : restaurants) {
				for (Centroid c : centroids) {

					// minimizing euclidean distance, enough to minimize ((x -
					// c.x)^2 + (y - c.y)^2)

					tempdistance = Math.pow(c.x - r.getLongitude(), 2) + Math.pow(c.y - r.getLatitude(), 2);
					if (tempdistance < minDistance) {
						minDistance = tempdistance;
						closestCentroid = c;
					}

				}
				centroidIndex = centroids.indexOf(closestCentroid);
				s = centroidClusters.get(centroids.get(centroidIndex));
				s.add(r);

				minDistance = Integer.MAX_VALUE;
			}

			// step 2 compute the new location of each centroid
			for (Centroid c : centroids) {
				centroidIndex = centroids.indexOf(c);
				s = centroidClusters.get(centroids.get(centroidIndex));
				for (Restaurant r : s) {
					avgX += r.getLongitude();
					avgY += r.getLatitude();
				}
				if (s.size() > 0) {
					avgX = avgX / s.size();
					avgY = avgY / s.size();

					/*
					 * check if each centroid is less than 0.0000 units away
					 * from the average x co-ordinate and average y co-ordinate
					 * of restaurants in their respective cluster.
					 * 
					 * If this condition is met for every centroid then the
					 * system has reached equilibrium and we may break out of
					 * the loop
					 * 
					 * if not, set the x and y co-ordinates of each centroid to
					 * be equal to the average x and y co-ordinate of
					 * restaurants in their respective cluster.
					 */

					offSetX = Math.abs(c.x - avgX);
					offSetY = Math.abs(c.y - avgY);
					String offX = String.format("%.4f", offSetX);
					String offY = String.format("%.4f", offSetY);

					System.out.println(offX + " " + offY);
					offSetX = Double.parseDouble(offX);
					offSetY = Double.parseDouble(offY);
					System.out.println(offSetX + " " + offSetY);

					if (offSetX == 0.0000 && offSetY == 0.0000 && Equilibrium) {
						Equilibrium = true;
					} else {
						c.x = avgX;
						c.y = avgY;
						Equilibrium = false;
					}
				}
				avgX = 0;
				avgY = 0;

			}
			if (Equilibrium == true)
				break;

			Equilibrium = true;
		}
		for (Centroid c : centroids) {
			centroidIndex = centroids.indexOf(c);
			s = centroidClusters.get(centroids.get(centroidIndex));
			finalClusters.add(s);
		}

		return finalClusters;
	}

	public static String convertClustersToJSON(List<Set<Restaurant>> clusters) {
		JSONArray array = new JSONArray();

		for (Set<Restaurant> s : clusters) {
			for (Restaurant r : s) {
				JSONObject obj = new JSONObject();
				obj.put("x", r.getLongitude());
				obj.put("y", r.getLatitude());
				obj.put("name", r.getName());
				obj.put("cluster", clusters.indexOf(s));
				obj.put("weight", 4);

				StringWriter out = new StringWriter();
				try {
					obj.writeJSONString(out);
				} catch (IOException e) {
					e.printStackTrace();
				}

				array.add(out);

			}
		}
		return array.toString();
	}

	/**
	 * 
	 * @param u
	 *            a User object
	 * 
	 * @param db
	 *            a RestaurantDB
	 * 
	 * @param featureFunction
	 *            an MP5Function that corresponds to a certain feature
	 *            associated with a restaurant (ie category, name, latitude,
	 *            longitude, meanRating, and Pricescale).
	 * 
	 * @return a PredictorFunction associated with the user u and the
	 *         featureFunction. In other words, returns a function that can
	 *         predict what rating user u will give to a restaurant based on the
	 *         given feature. If a user only has one review, has only reviewed
	 *         restaurants with the same value for the featureFunction, or has
	 *         given the same rating regardless of the featureFunction value
	 *         then we return a constant PredictorFunction, that returns 3 for
	 *         all restaurants.
	 * 
	 * 
	 */

	public static PredictorFunction getPredictor(User u, RestaurantDB db, MP5Function featureFunction) {
		Set<Review> Reviews = new HashSet<Review>();
		Reviews = db.getReviews();

		List<Double> yList = new ArrayList<Double>();
		List<Double> xList = new ArrayList<Double>(); 

		double Sxx = 0.0;
		double Syy = 0.0;
		double Sxy = 0.0;
		double a = 0.0;
		double b = 0.0;
		double r_squared;

		for (Review r : Reviews) {
			if (r.getUserId().equals(u.getUserId())) {
				xList.add(featureFunction.f(db.getRestaurantObj(r.getBusinessId()), db));
				yList.add((double) r.getStars());
			}
		}

		double ymean = getmean(yList);
		double xmean = getmean(xList);

		for (Double x : xList) {
			Sxx += Math.pow((x - xmean), 2);
		}
		for (Double y : yList) {
			Syy += Math.pow((y - ymean), 2);
		}

		for (int i = 0; i < xList.size(); i++) {
			Sxy += ((xList.get(i) - xmean) * (yList.get(i) - ymean));
		}

		// if there is only 1 point, or we have only points in one x value, or
		// only points
		// in one y value then we cannot do a linear regression for this
		// user/feature pair.
		// instead, we return a constant PredictorFunction of 3. (we choose 3
		// because it is
		// the median of the range 1 <= y <=5.
		if ((xList.size() == 1 && yList.size() == 1) || Sxx == 0 || Syy == 0) {
			b = 0;
			a = 3;
			r_squared = 0;
			return new PredictorFunction(a, b, r_squared, featureFunction);
		}

		b = Sxy / Sxx;
		a = ymean - (b * xmean);
		r_squared = (Math.pow(Sxy, 2)) / (Sxx * Syy);

		return new PredictorFunction(a, b, r_squared, featureFunction);
	}

	/**
	 * requires: List not be null
	 * 
	 * @param List
	 *            a list of doubles
	 * 
	 * @return the mean of all values in the list, if list is empty, returns 0
	 */
	private static double getmean(List<Double> List) {
		double sum = 0.0;
		if (!List.isEmpty()) {
			for (Double x : List) {
				sum += x;
			}
			return sum / List.size();
		}
		return sum;
	}

	/**  
	     *   
	     * @param u  
	      *            user  
	      * @param db  
	      *            RestaurantDB  
	      * @param featureFunctionList  
	     *            a list of MP5Functions that corresponds to a certain feature  
	     *            associated with a restaurant (ie category, name, latitude,  
	     *            longitude, meanRating, and Pricescale)  
	    * @return the element in the featureFunctionList that is the best predictor  
     *         of a user u's rating of a given restaurant  
	    */  
	   public static MP5Function getBestPredictor(User u, RestaurantDB db, List<MP5Function> featureFunctionList) {  
	       ArrayList<PredictorFunction> PredictorFunctions = new ArrayList<PredictorFunction>();  
	        double max_r = 0.0;  
	         int indexofmax = 0;  
	  
	        for (MP5Function featureFunction : featureFunctionList) {  
	            PredictorFunctions.add(Algorithms.getPredictor(u, db, featureFunction));  
	       }  
	         for (PredictorFunction p : PredictorFunctions) {  
	            if (p.getR_Squared() > max_r) {  
	               max_r = p.getR_Squared();  
	               indexofmax = PredictorFunctions.indexOf(p);  
	           }  
	
       }  
      return PredictorFunctions.get(indexofmax);  
    }
	  
	   
	public static void main (String [] args){
		
		RestaurantDB database = new RestaurantDB("data/restaurants.json", "data/reviews.json", "data/users.json");
		System.out.println("ready");
		
		List<Set<Restaurant>> testList = Algorithms.kMeansClustering(2, database) ;
		String Voronoi_Tesselation = convertClustersToJSON(testList);
		FileOutputStream out = null;
		
		try{
			 out = new FileOutputStream("visualize/voronoi.json");
		}catch(IOException f){
			f.printStackTrace();
		};
		System.out.println("got here");
		PrintWriter writer = new PrintWriter(new OutputStreamWriter(out), true);
		writer.print(Voronoi_Tesselation);
		
		writer.close();
		
	}
}
