package ca.ece.ubc.cpen221.mp5;

import org.json.simple.JSONArray;

/**
 * This class represents a Restaurant. The field values provide a means to
 * search and distinguish different Restaurant objects based on certain criteria
 * 
 * rep invariant: all field values ! null; url, longitude, latitude,
 * 				  business_id and full_address must be unique.
 * 
 * abstraction function: all legal rep values are mapped to a unique restaurant
 *
 */

public class Restaurant {

    private final boolean open;
    private final String url;
    private final String name;
    private final Long price;
    private final JSONArray categories;
    private final JSONArray neighborhoods;
    private final double longitude;
    private final double latitude;
    private final String business_id;
    private final String state;
    private final String type;
    private final double stars;
    private final String city;
    private final String full_address;
    private Long review_count;
    private final String photo_url;
    private final JSONArray schools;
    
    
    public Restaurant(boolean open, String url, String name, Long price, JSONArray categories, JSONArray neighborhoods, double longitude,
            double latitude, String business_id, String state, String type, double stars, String city, 
            String full_address, Long review_count, String photo_url, JSONArray schools){
        
        this.open = open;
        this.url = url;
        this.name = name;
        this.price = price;
        this.categories = categories;
        this.neighborhoods = neighborhoods;
        this.longitude = longitude;
        this.latitude = latitude;
        this.business_id = business_id;
        this.state = state;
        this.type = type;
        this.stars = stars;
        this.city = city;
        this.full_address = full_address;
        this.review_count = review_count;
        this.photo_url = photo_url;
        this.schools = schools;
    }
    
    public boolean getOpen(){
        return open;
    }
    public String getUrl(){
        return url;
    }
    
    public String getState(){
        return state;
    }
    
    public double getStars(){
        return stars;
    }
    
    public String getCity(){
        return city;
    }
    
    public String getFullAddress(){
        return full_address;
    }
    
    public Long getReviewCount(){
        return review_count;
    }
    
    public void incrementReviewCount(){
    	review_count++;
    }
    
    public String getPhotoURL(){
        return photo_url;
    }
    
    public JSONArray getSchools(){
        return schools;
    }
    
    public String getBusinessId(){
        return business_id;
    }
    public String getName(){
        return name;
    }
    public JSONArray getNeighborhoods(){
        return neighborhoods;
    }
    public String getType(){
        return type;
    }
    public Long getPrice(){
        return price;
    }
    public JSONArray getCategories(){
        return categories;
    }
    public double getLatitude(){
        return latitude;
    }
    public double getLongitude(){
        return longitude;
    }
}