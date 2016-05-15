package ca.ece.ubc.cpen221.mp5;

import java.util.Set;
import java.util.Stack;

import javax.swing.JDialog;

import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.io.BufferedReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import User_Queries.Progress_Bar;
import ca.ece.ubc.cpen221.mp5.statlearning.Algorithms;
import ca.ece.ubc.cpen221.mp5.queryparser.*;

// TODO: This class represents the Restaurant Database.
// Define the internal representation and 
// state the rep invariant and the abstraction function.
/**
 * 
 * This class represents the Restaurant Database. It is organized into a set of
 * Restaurants, Users and Reviews.
 * 
 * Representation Invariant: Each review object must be mapped to a single User
 * object, where the user_id fields in both objects are the same. Each
 * Restaurant object must be mapped to a set of reviews where the business_id
 * fields in mapped objects are the same.
 *
 * Abstraction Function: Legal rep values are mapped to nodes on a directed
 * graph with edges from Restaurant objects to review objects and edges from
 * review objects to User objects.
 * 
 */

public class RestaurantDB {

	private Set<Restaurant> Restaurants;
	private Set<User> Users;
	private Set<Review> Reviews;
	private Map<Restaurant, Set<Review>> RestaurantReviews;
	private Map<Review, User> ReviewWriter;

	/**
	 * Create a database from the Yelp dataset given the names of three files:
	 * <ul>
	 * <li>One that contains data about the restaurants;</li>
	 * <li>One that contains reviews of the restaurants;</li>
	 * <li>One that contains information about the users that submitted reviews.
	 * </li>
	 * </ul>
	 * The files contain data in JSON format.
	 * 
	 * @param restaurantJSONfilename
	 *            the filename for the restaurant data
	 * @param reviewsJSONfilename
	 *            the filename for the reviews
	 * @param usersJSONfilename
	 *            the filename for the users
	 */
	public RestaurantDB(String restaurantJSONfilename, String reviewsJSONfilename, String usersJSONfilename, Progress_Bar progress) {

		Restaurants = new HashSet<Restaurant>();
		Users = new HashSet<User>();
		Reviews = new HashSet<Review>();
		RestaurantReviews = new HashMap<Restaurant, Set<Review>>();
		ReviewWriter = new HashMap<Review, User>();

		Set<Review> temp = new HashSet<Review>();

		try {
			BufferedReader reader1 = new BufferedReader(new FileReader(restaurantJSONfilename));
			BufferedReader reader2 = new BufferedReader(new FileReader(reviewsJSONfilename));
			BufferedReader reader3 = new BufferedReader(new FileReader(usersJSONfilename));
			
			InitializeRestaurants(reader1);
			
			
			InitializeReviews(reader2);
			
			progress.progressMessage.setText("<html><b> Initializing Users ...</b></html> ");
			progress.progressBar.setValue(30);
			InitializeUsers(reader3);

			
			progress.progressMessage.setText("<html><b> Initializing Restaurant Reviews ... </b></html>");
			progress.progressBar.setValue(50);
			for (Restaurant r : Restaurants) {

				for (Review re : Reviews) {
					if (r.getBusinessId().equals(re.getBusinessId())) {
						temp.add(re);

					}
				}
				// assign a set of reviews to each restaurant provided the
				// businessId's
				// match
				RestaurantReviews.put(r, new HashSet<Review>(temp));
				temp.clear();
			}
			// assign each review to a suer provided the userId's match
			progress.progressMessage.setText("<html><b>Setting Up Review Users ...</b></html>");
			progress.progressBar.setValue(80);
			
			for (Review re : Reviews) {
				for (User u : Users) {
					if (u.getUserId().equals(re.getUserId()))
						ReviewWriter.put(re, u);
				}
			}
			
			progress.dispose();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Initialize the Restaurants in the database by parsing an input file
	 * 
	 * @param reader
	 *            a BufferedReader that holds the data from the input file
	 */
	public void InitializeRestaurants(BufferedReader reader) {
		@SuppressWarnings("unchecked")
		JSONParser parser = new JSONParser();
		Object obj;
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {

				RestaurantDBServer.addRestaurant(line, this, false);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Initialize the reviews in the database by parsing an input file
	 * 
	 * @param reader
	 *            a BufferedReader that holds the data from the input file
	 */
	public void InitializeReviews(BufferedReader reader) {
		@SuppressWarnings("unchecked")
		JSONParser parser = new JSONParser();
		Object obj;
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				RestaurantDBServer.addReview(line, this, false);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the Users in the database by parsing an input file
	 * 
	 * @param reader
	 *            a BufferedReader that holds the data from the input file
	 */
	public void InitializeUsers(BufferedReader reader) {
		@SuppressWarnings("unchecked")
		JSONParser parser = new JSONParser();
		Object obj;
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				RestaurantDBServer.addUser(line, this);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	/**
	 * Returns the set of Reviews for the RestaurantDB object
	 * 
	 * @return returns a set of Review objects
	 */
	public Set<Review> getReviews() {
		return Reviews;
	}
	
	public Set <User> getUsers() {
		return Users;
	}

	public Restaurant getRestaurantObj(String BusinessID) {
		for (Restaurant r : Restaurants) {
			if (r.getBusinessId().equals(BusinessID)) {
				return r;
			}
		}
		return null;
	}
	
	public String nameToBusinessId(String restaurantName){
		
		for (Restaurant r : Restaurants){
			if (r.getName().equals(restaurantName)){
				return r.getBusinessId();
			}
		}
		
		return null;
	}
	
	public String userIdToName(String userId){
		
		for (User u : Users){
			if(u.getUserId().equals(userId)){
				return u.getName();
			}
		}
		
		return "User Name Not Found";
	}

	/**
	 * 
	 * 
	 * @return Returns the Set of Restaurants for this database
	 */
	public Set<Restaurant> getRestaurants() {
		return Restaurants;
	}

	/**
	 * 
	 * @return returns the Mapping of Restaurants to Sets of Reviews for this
	 *         database
	 */
	public HashMap<Restaurant, Set<Review>> getRestaurantReviews() {
		return (HashMap) RestaurantReviews;
	}

	/**
	 * requires: r be valid restaurant and already in the database
	 * 
	 * @param r
	 *            a restaurant in the database
	 * @return returns a Set of all reviews associated with that restaurant
	 */
	public Set<Review> getReviewsForRest(Restaurant r) {
		return RestaurantReviews.get(r);
	}
	
	


}
