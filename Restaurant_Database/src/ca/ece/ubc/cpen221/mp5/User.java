package ca.ece.ubc.cpen221.mp5;

import java.util.HashMap;
import java.util.Map;

// TODO: Use this class to represent a Yelp user.

/**
 * this class represents a restaurant user. Reviews are mapped to users
 * 
 * Representation invariant: all field values !null, userId must be unique
 *
 *
 */
public class User {

	private final String url;
	private final Map<String, Long> votes;
	private Long reviewCount;
	private final String type;
	private final String userId;
	private final String name;
	private double averageStars;
	
	public User(String url, Long cool, Long funny, Long useful, Long reviewCount, String type, String userId,
			String name, double averageStars){
		
		votes = new HashMap<String, Long>();
		votes.put("cool", cool);
		votes.put("funny", funny);
		votes.put("useful", useful);
	
		this.url = url;
		this.reviewCount = reviewCount;
		this.type = type;
		this.userId = userId;
		this.name = name;
		this.averageStars = averageStars;
		
	}
	
	public String getUserId(){
		
		return userId;
	}
	public String getName(){
		return name;
	}
	
}

