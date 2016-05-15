package ca.ece.ubc.cpen221.mp5;

import java.util.HashMap;
import java.util.Map;

/**
 * This class represents a yelp Review
 * 
 * Representation Invariant: All field values !null and reviewId is unique for each 
 * 							 review object
 * 
 * Abstraction Function: maps legal rep values to a review written by a user
 * 
 *
 */

public class Review {

    private final String type;
    private final Map<String, Long> votes;
    private final String reviewId;
    private final String text;
    private final Long stars;
    private final String businessId;
    private final String userId;
    private final String date;
    private final Long cool;
    private final Long funny;
    private final Long useful;
    
    public Review(String text, String type, Long cool, Long funny, Long useful, String reviewId, Long stars
            ,String businessId, String userId, String date){
        
        votes = new HashMap<String, Long>();
        votes.put("cool", cool);
        votes.put("funny", funny);
        votes.put("useful", useful);
        
        this.cool = cool;
        this.funny = funny;
        this.useful = useful;
        this.type = type;
        this.text = text;
        this.businessId = businessId;
        this.userId = userId;
        this.reviewId = reviewId;
        this.stars = stars;
        this.date = date;
        
    }
    
    public String getBusinessId(){
        return businessId;
        
    }
    
    public String getType(){
        return type;
    }
    
    public Long getCool(){
        return cool;
    }
    
    public Long getFunny(){
        return funny;
    }
    public Long getUseful(){
        return useful;
    }
    
    public String getReviewID(){
        return reviewId;
    }
    
    public String getDate() {
        return date;
    }
    
    public String getUserId(){
        return userId;
    }
    public Long getStars(){
        return stars;
    }
    public String getText(){
        return text;
    }
    
}
