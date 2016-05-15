package ca.ece.ubc.cpen221.mp5;

import User_Queries.Server_Response_GUI;
import ca.ece.ubc.cpen221.mp5.queryparser.QListener;
import ca.ece.ubc.cpen221.mp5.queryparser.Query;
import ca.ece.ubc.cpen221.mp5.queryparser.QueryLexer;
import ca.ece.ubc.cpen221.mp5.queryparser.QueryParser;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

import User_Queries.Progress_Bar;
import User_Queries.Query_GUI;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.IllegalFormatException;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import User_Queries.Query_GUI;

// TODO: Implement a server that will instantiate a database, 
// process queries concurrently, etc.

public class RestaurantDBServer {

	public static final int RESTAURANTDB_PORT = 1000;

	private ServerSocket serverSocket;
	private RestaurantDB RestaurantDB;
	private static Query testQuery = null;

	/**
	 * Constructor
	 * 
	 * @param port
	 * @param filename1
	 * @param filename2
	 * @param filename3
	 * 
	 *            Creates a Server that will listen for connections on the
	 *            specified port number and will handle queries from clients.
	 *            The constructor of the server also instantiates the restaurant
	 *            database.
	 */
	public RestaurantDBServer(int port, String filename1, String filename2, String filename3, Progress_Bar progress)
			throws IOException {
		serverSocket = new ServerSocket(port);

		RestaurantDB = new RestaurantDB(filename1, filename2, filename3, progress);

	}

	/**
	 * Processes Multiple clients at once by creating threads for each connected
	 * "socket"
	 * 
	 * @throws IOException
	 */
	public void serve() throws IOException {
		while (true) {
			// block until a client connects
			final Socket socket = serverSocket.accept();
			// create a new thread to handle that client
			Thread request = new Thread(new Runnable() {
				public void run() {
					try {
						try {
							respond(socket);
						} finally {
							socket.close();
						}
					} catch (IOException ioe) {
						ioe.printStackTrace();

					}
				}
			});
			// start the thread
			request.start();
		}
	}

	/**
	 * Process a client's query and reply to it with a JSON formatted string
	 * 
	 * @param socket
	 *            the socket corresponding to a single client
	 * @throws IOException
	 */
	public void respond(Socket socket) throws IOException {

		System.out.println("processing new client!");

		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

		PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

		try {

			for (String line = in.readLine(); line != null; line = in.readLine()) {
				System.out.println("request: " + line + "\n");

				try {
					// check the format of the input query and match it with
					// a corresponding
					// method call
					if (line.contains("randomReview")) {
						
						boolean isEmpty = false;
						String s = randomReview(line.substring(14, line.length() - 2), RestaurantDB);
						if (s.contains("error")){
							isEmpty = true;
						}
						System.out.println("hi");
						String output = RandomRestaurantReviewOutput(s, RestaurantDB, isEmpty);
						System.out.println("reply : " + output + "\n");
						out.println(output);
						// output to the clients inputStream
					}

					else if (line.contains("getRestaurant")) {
						String s = getRestaurant(line.substring(15, line.length() - 2), RestaurantDB);
						System.out.println("reply : " + s + "\n");
						out.println(s);
					}

					else if (line.contains("addRestaurant")) {
						String s = addRestaurant(line.substring(15, line.length() - 2), RestaurantDB, true);
						System.out.println("reply: " + s + "\n");
						out.println(s);

					}

					else if (line.contains("addUser")) {
						String s = addUser(line.substring(9, line.length() - 2), RestaurantDB);
						System.out.println("reply : " + s + "\n");
						out.println(s);

					}

					else if (line.contains("addReview")) {
						String s = addReview(line.substring(11, line.length() - 2), RestaurantDB, true);
						System.out.println("reply : " + s + "\n");
						out.println(s);

					} else {
						
						Set<String> matches = query(line, RestaurantDB);
						String output = RestaurantSearchOutput(matches);
						System.out.println("reply : " + output + "\n");
						out.println(output);

					}
				} catch (Exception e) {
					JSONObject err = new JSONObject();
					err.put("error", "invalid query");
					StringWriter output = new StringWriter();
					try {
						err.writeJSONString(output);
					} catch (IOException f) {
						f.printStackTrace();
					}

					String jsonText = output.toString();

					out.print(jsonText);
				}

			}
		} finally {
			out.close();
			in.close();
		}
	}

	/**
	 * 
	 * @param queryString
	 *            a String that contains a query request
	 * 
	 * @return a Set of all restaurants in JSON format that match the given
	 *         query. if the query is invalid, returns a Set containing one
	 *         unique error message in JSON format.
	 */

	public synchronized static Set<String> query(String queryString, RestaurantDB database) {

		Set<String> matches = new HashSet<String>();

		CharStream stream = new ANTLRInputStream(queryString);
		QueryLexer lexer = new QueryLexer(stream);
		TokenStream tokens = new CommonTokenStream(lexer);
		QueryParser parser = new QueryParser(tokens);
		ParseTree tree = parser.root();

		ParseTreeWalker walker = new ParseTreeWalker();
		QListener listener = new QListener();

		// walker will throw an exception if the queryString
		// doesn't match the query grammar.
		// if exception is thrown return a set containing an
		// error message in JSON format
		try {
			walker.walk(listener, tree);
		} catch (Exception err) {
			JSONObject obj = new JSONObject();

			obj.put("error", "Invalid Query");
			StringWriter out = new StringWriter();

			try {
				obj.writeJSONString(out);
			} catch (IOException e) {
				e.printStackTrace();
			}

			String jsonText = out.toString();
			matches.add(jsonText);

			return matches;
		}
		Query query = listener.getQuery();
		
		for (Restaurant r : database.getRestaurants()) {
			if (query.evaluate(r)) {
				matches.add(restObjtoJSON(r));
			}
		}

		return matches;
	}

	/**
	 * 
	 * @param r
	 *            a valid restaurant object
	 * 
	 * @return a JSON formatted string containing all the associated information
	 *         of the restaurant r
	 */

	public static String restObjtoJSON(Restaurant r) {
		JSONObject obj = new JSONObject();

		obj.put("open", r.getOpen());
		obj.put("url", r.getUrl());
		obj.put("name", r.getName());
		obj.put("price", r.getPrice());
		obj.put("categories", r.getCategories());
		obj.put("neighborhoods", r.getNeighborhoods());
		obj.put("longitude", r.getLongitude());
		obj.put("latitude", r.getLatitude());
		obj.put("business_id", r.getBusinessId());
		obj.put("state", r.getState());
		obj.put("type", r.getType());
		obj.put("stars", r.getStars());
		obj.put("city", r.getCity());
		obj.put("full_address", r.getFullAddress());
		obj.put("review_count", r.getReviewCount());
		obj.put("photo_url", r.getPhotoURL());
		obj.put("schools", r.getSchools());

		StringWriter out = new StringWriter();
		try {
			obj.writeJSONString(out);
		} catch (IOException e) {
			e.printStackTrace();
		}

		String jsonText = out.toString();

		return jsonText;
	}

	/**
	 * Searches the specified database for a Restaurant with the provided name
	 * 
	 * @param BusinessID
	 *            A string corresponding to the businessId of the desired
	 *            restaurant
	 * 
	 * @param database
	 *            The RestaurantDB object to search in
	 * 
	 * @return returns the details of the restaurant in JSON format or an
	 *         appropriate error string in JSON format
	 */
	public synchronized static String getRestaurant(String BusinessID, RestaurantDB database) {
		for (Restaurant r : database.getRestaurants()) {
			if (r.getBusinessId().equals(BusinessID)) {
				return restObjtoJSON(r);
			}
		}
		JSONObject err = new JSONObject();
		err.put("error", "restaurant (" + BusinessID + ") not found");
		StringWriter out = new StringWriter();
		try {
			err.writeJSONString(out);
		} catch (IOException e) {
			e.printStackTrace();
		}

		String jsonText = out.toString();

		return jsonText;

	}

	/**
	 * Retrieves a random review for a Restaurant in the database with the
	 * specified name
	 * 
	 * @param name
	 *            The name of the Restaurant
	 * 
	 * @param database
	 *            The database to search in
	 * 
	 * @return returns a String with the details of the random review in JSON
	 *         format or returns an appropriate error message in JSON format
	 */

	public synchronized static String randomReview(String name, RestaurantDB database) {
		Restaurant queryRestaurant = null;
		// check if restaurant exists in database
		for (Restaurant r : database.getRestaurants()) {
			if (r.getName().equals(name)) {
				queryRestaurant = r;
			}
		}
		if (queryRestaurant == null) {
			JSONObject err = new JSONObject();
			err.put("error", "restaurant (" + name + ") doesn't exist in this database");
			StringWriter out = new StringWriter();
			try {
				err.writeJSONString(out);
			} catch (IOException e) {
				e.printStackTrace();
			}

			String jsonText = out.toString();

			return jsonText;

		} else if (database.getReviewsForRest(queryRestaurant).size() == 0) {
			// Restaurant has no reviews
			JSONObject err = new JSONObject();
			err.put("error", "restaurant (" + name + ") does not have a review in this database");
			StringWriter out = new StringWriter();
			try {
				err.writeJSONString(out);
			} catch (IOException e) {
				e.printStackTrace();
			}

			String jsonText = out.toString();

			return jsonText;
		}

		Set<Review> reviews = database.getReviewsForRest(queryRestaurant);
		int size = reviews.size();
		int item = new Random().nextInt(size);
		int i = 0;
		for (Review r : reviews) {
			// looks for a random review within the set of reviews
			if (i == item) {
				JSONObject obj = new JSONObject();

				// encode the string in JSON
				obj.put("text", r.getText());
				obj.put("type", r.getType());
				obj.put("cool", r.getCool());
				obj.put("funny", r.getFunny());
				obj.put("useful", r.getUseful());
				obj.put("review_id", r.getReviewID());
				obj.put("stars", r.getStars());
				obj.put("business_id", r.getBusinessId());
				obj.put("user_id", r.getUserId());
				obj.put("date", r.getDate());

				StringWriter out = new StringWriter();
				try {
					obj.writeJSONString(out);
				} catch (IOException e) {
					e.printStackTrace();
				}

				String jsonText = out.toString();

				return jsonText;
			}
			i = i + 1;
		}
		JSONObject err = new JSONObject();
		err.put("error", "invalid query");
		StringWriter output = new StringWriter();
		try {
			err.writeJSONString(output);
		} catch (IOException f) {
			f.printStackTrace();
		}

		String jsonText = output.toString();
		return jsonText;

	}

	/**
	 * Adds a new Restaurant object to the database provided a Restaurant object
	 * with the same name and in the same location does not already exist
	 * 
	 * @param details
	 *            A String containing the details of the restaurant in JSON
	 *            format
	 * @param database
	 *            The database to add the Restaurant to
	 * @return returns a string in JSON format that states whether a Restaurant
	 *         object was successfully added or not
	 */

	public synchronized static String addRestaurant(String details, RestaurantDB database, boolean newRest) {
		@SuppressWarnings("unchecked")
		JSONParser parser = new JSONParser();
		Object obj;
		boolean exists = false;
		try {
			obj = parser.parse(details);

			JSONObject jsonObject = (JSONObject) obj;
			String name = (String) jsonObject.get("name");
			double longitude = (double) jsonObject.get("longitude");
			double latitude = (double) jsonObject.get("latitude");

			// check if restaurant already exists
			for (Restaurant r : database.getRestaurants()) {
				if (name.equals(r.getName()) && Math.abs(longitude - r.getLongitude()) < 0.00000001
						&& Math.abs(latitude - r.getLatitude()) < 0.00000001) {

					exists = true;
				}

			}
			// if restaurant does not already exist, add it to the database
			if (exists == false) {

				Restaurant restaurant = new Restaurant

				((Boolean) jsonObject.get("open"), (String) jsonObject.get("url"), (String) jsonObject.get("name"),
						(Long) jsonObject.get("price"), (JSONArray) jsonObject.get("categories"),
						(JSONArray) jsonObject.get("neighborhoods"), (Double) jsonObject.get("longitude"),
						(Double) jsonObject.get("latitude"), (String) jsonObject.get("business_id"),
						(String) jsonObject.get("state"), (String) jsonObject.get("type"),
						(Double) jsonObject.get("stars"), (String) jsonObject.get("city"),
						(String) jsonObject.get("full_address"), (newRest ?  0 : (Long) jsonObject.get("review_count") ),
						(String) jsonObject.get("photo_url"), (JSONArray) jsonObject.get("schools"));
				
				database.getRestaurants().add(restaurant);
				Query_GUI.updateListModel((String) jsonObject.get("name"));
				
				if(newRest){
					database.getRestaurantReviews().put(restaurant, new HashSet<Review>());
				}
				

				JSONObject err = new JSONObject();
				err.put("Added", "restaurant (" + name + ") to the database!");
				StringWriter out = new StringWriter();
				try {
					err.writeJSONString(out);
				} catch (IOException e) {
					e.printStackTrace();
				}

				String jsonText = out.toString();

				return jsonText;

				// else state that the restaurant was not added
			} else {
				JSONObject err = new JSONObject();
				err.put("Not Added", "restaurant (" + name + ") already exists in the database!");
				StringWriter out = new StringWriter();
				try {
					err.writeJSONString(out);
				} catch (IOException e) {
					e.printStackTrace();
				}

				String jsonText = out.toString();

				return jsonText;
			}

		} catch (Exception e) {
			JSONObject err = new JSONObject();
			err.put("error", "invalid query");
			StringWriter output = new StringWriter();
			try {
				err.writeJSONString(output);
			} catch (IOException f) {
				f.printStackTrace();
			}

			String jsonText = output.toString();
			return jsonText;
		}

	}

	/**
	 * Adds a User object to the database provided a User object with the same
	 * UserId does not already exist
	 * 
	 * @param details
	 *            a String representing the details of the User object in JSON
	 *            format
	 * 
	 * @param database
	 *            the database to add the User to
	 * @return returns a string in JSON format that states whether the User was
	 *         successfully added to the database or not
	 */

	public synchronized static String addUser(String details, RestaurantDB database) {
		@SuppressWarnings("unchecked")
		JSONParser parser = new JSONParser();
		Object obj;

		boolean exists = false;
		try {
			obj = parser.parse(details);

			JSONObject jsonObject = (JSONObject) obj;
			String userId = (String) jsonObject.get("user_id");

			for (User u : database.getUsers()) {
				if (u.getUserId().equals(userId)) {
					exists = true;
				}
			}
			if (exists == false) {
				database.getUsers().add(new User

				((String) jsonObject.get("url"), (Long) jsonObject.get("cool"), (Long) jsonObject.get("funny"),
						(Long) jsonObject.get("useful"), (Long) jsonObject.get("review_count"),
						(String) jsonObject.get("type"), (String) jsonObject.get("user_id"),
						(String) jsonObject.get("name"), (Double) jsonObject.get("average_stars")));

				JSONObject err = new JSONObject();
				err.put(" Added", "User (" + userId + ") was added to the database!");
				StringWriter out = new StringWriter();
				try {
					err.writeJSONString(out);
				} catch (IOException e) {
					e.printStackTrace();
				}

				String jsonText = out.toString();

				return jsonText;

			} else {
				JSONObject err = new JSONObject();
				err.put("Not Added", "User (" + userId + ") already exists in the database!");
				StringWriter out = new StringWriter();
				try {
					err.writeJSONString(out);
				} catch (IOException e) {
					e.printStackTrace();
				}

				String jsonText = out.toString();

				return jsonText;
			}

		} catch (Exception e) {
			JSONObject err = new JSONObject();
			err.put("error", "invalid query");
			StringWriter output = new StringWriter();
			try {
				err.writeJSONString(output);
			} catch (IOException f) {
				f.printStackTrace();
			}

			String jsonText = output.toString();
			return jsonText;
		}

	}

	/**
	 * Adds a Review object to the database provided a review with the same
	 * reviewId does not already exist
	 * 
	 * @param details
	 *            a String that holds the details of the Review object in JSON
	 *            format
	 * 
	 * @param database
	 *            the database to add the review to
	 * 
	 * @return returns a string in JSON format that states if the review was
	 *         successfully added to the database or not
	 */

	public synchronized static String addReview(String details, RestaurantDB database, boolean ReviewsSetUp) {
		@SuppressWarnings("unchecked")
		JSONParser parser = new JSONParser();
		Object obj;
		boolean exists = false;
		try {
			obj = parser.parse(details);

			JSONObject jsonObject = (JSONObject) obj;
			String reviewId = (String) jsonObject.get("review_id");
			for (Review r : database.getReviews()) {
				if (r.getReviewID().equals(reviewId)) {
					exists = true;
				}
			}
			if (exists == false) {
				
				Review newReview = new Review ((String) jsonObject.get("text"), (String) jsonObject.get("type"), (Long) jsonObject.get("cool"),
						(Long) jsonObject.get("funny"), (Long) jsonObject.get("useful"),
						(String) jsonObject.get("review_id"), (Long) jsonObject.get("stars"),
						(String) jsonObject.get("business_id"), (String) jsonObject.get("user_id"),
						(String) jsonObject.get("date"));
				
				database.getReviews().add(newReview);
				
				if(ReviewsSetUp){
					
					Restaurant updateRest = database.getRestaurantObj((String) jsonObject.get("business_id"));
					Set<Review> restaurantReviews = database.getReviewsForRest(updateRest);
					restaurantReviews.add(newReview);
					updateRest.incrementReviewCount();
					
				}
				
				JSONObject err = new JSONObject();
				err.put("Added", "Review (" + reviewId + ") to the database!");
				StringWriter out = new StringWriter();
				try {
					err.writeJSONString(out);
				} catch (IOException e) {
					e.printStackTrace();
				}

				String jsonText = out.toString();

				return jsonText;
			} else {
				JSONObject err = new JSONObject();
				err.put("Not Added", "Review (" + reviewId + ") already exists in the database!");
				StringWriter out = new StringWriter();
				try {
					err.writeJSONString(out);
				} catch (IOException e) {
					e.printStackTrace();
				}

				String jsonText = out.toString();

				return jsonText;
			}

		} catch (Exception e) {
			//throw e.getCause();
			JSONObject err = new JSONObject();
			err.put("error", "invalid query");
			StringWriter output = new StringWriter();
			try {
				err.writeJSONString(output);
			} catch (IOException f) {
				f.printStackTrace();
			}

			String jsonText = output.toString();
			return jsonText;
		}

	}

	public static String getRestaurantOutput(String JSONRestaurant) {

		JSONParser parser = new JSONParser();
		Object obj;

		
		String name = null;
		Boolean open = null;
		Long price = null;
		Double stars = null;
		String fullAddress = null;
		Long reviewCount = null;
		JSONArray categories = null;
		String businessId = null;
		
		try {

			obj = parser.parse(JSONRestaurant);
			JSONObject jsonObject = (JSONObject) obj;
			
			businessId = (String) jsonObject.get("business_id");
			open = (Boolean) jsonObject.get("open");
			name = (String) jsonObject.get("name");
			price = (Long) jsonObject.get("price");
			stars = (Double) jsonObject.get("stars");
			fullAddress = (String) jsonObject.get("full_address");
			reviewCount = (Long) jsonObject.get("review_count");
			categories = (JSONArray) jsonObject.get("categories");
			

			

		} catch (Exception e) {
			e.printStackTrace();
		}

		return  name + "\n\n" + "Open: " + open + "\n" + "Price: " + price + "\n" + "Stars: "
				+ stars + "\n" + "Full Address: " + fullAddress.replaceAll("\n", " ") + "\n" + "Review Count: " + reviewCount + "\n"
				+ "Categories: " + categories + "\n" + "BusinessID: " + businessId + "\n\n"; 
		
		

	}
	
	public static String RestaurantSearchOutput(Set <String> JSONRestaurants){
		
		JSONParser parser = new JSONParser();
		Object obj;
		
		String name = null;
		String output = "";
		
		if(!JSONRestaurants.isEmpty()){
			for(String s : JSONRestaurants){
				
				try{
					obj = parser.parse(s);
					JSONObject jsonObject = (JSONObject) obj;
					
					name = (String) jsonObject.get("name");
					output += name;
					output += "#";
				
				} catch(Exception e){
					e.printStackTrace();
				}
			}
			
			return output;
			
		}else {
			
			return "Sorry, No Restaurants Matched The Search Criteria";
		}

	}
	
	public static String RandomRestaurantReviewOutput(String JSONReview, RestaurantDB database, boolean isEmpty){
		
		if(isEmpty == false){
			JSONParser parser = new JSONParser();
			Object obj;
			
			String review = null;
			String userName = null;
			String date = null;
			String restaurantName = null;
			
			try{
				obj = parser.parse(JSONReview);
				JSONObject jsonObject = (JSONObject) obj;
				review = (String) jsonObject.get("text");
				date = (String) jsonObject.get("date");
				userName = database.userIdToName((String) jsonObject.get("user_id"));
				restaurantName = database.getRestaurantObj((String) jsonObject.get("business_id")).getName();
				
			} catch(Exception e){
				e.printStackTrace();
				return "Sorry, There Are No Reviews For This Restaurant";
			}
			review = review.replaceAll("\n", "#");
			return "Date: " + date + "#" + "Review Writer: " +  userName + "#" + "Restaurant: " + restaurantName + 
					"##" + review + "#";
		}else{
			
			return "Sorry, There Are No Reviews For This Restaurant";
		}
	}

	public static void main(String[] args) {
		try {

			try {
				UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			} catch (Throwable e) {
				e.printStackTrace();
			}

			Progress_Bar progress = new Progress_Bar();
			progress.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			progress.setVisible(true);

			RestaurantDBServer server = new RestaurantDBServer(RESTAURANTDB_PORT, "data/restaurants.json",
					"data/reviews.json", "data/users.json", progress);
			System.out.println("ready");

			EventQueue.invokeLater(new Runnable() {
				public void run() {

					try {

						Query_GUI window2 = new Query_GUI(server.RestaurantDB);
						window2.Client = new ServerClient("localhost", RestaurantDBServer.RESTAURANTDB_PORT);
						window2.frmQueryConstructor.setVisible(true);

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});

			server.serve();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
