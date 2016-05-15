package ca.ece.ubc.cpen221.mp5.statlearning;  
 
import java.util.Set;  
 
import ca.ece.ubc.cpen221.mp5.Restaurant;  
import ca.ece.ubc.cpen221.mp5.Review;  
import ca.ece.ubc.cpen221.mp5.RestaurantDB;  
  
/**  
* MeanRating represents an MP5Function who's feature function returns  
  * the mean rating of a restaurant.  
 *  
  */  
   
  public class MeanRating implements MP5Function {  
 
      @Override  
    public double f(Restaurant yelpRestaurant, RestaurantDB db) {  
         Set<Review> reviews = db.getReviewsforRest(yelpRestaurant);  
        yelpRestaurant.getReviewCount();  
        double sum = 0.0;  
  
        for (Review r : reviews) {  
             sum += r.getStars();  
       }  
  
         return sum / yelpRestaurant.getReviewCount();  
    }  
 }
