package ca.ece.ubc.cpen221.mp5.statlearning;

import java.util.HashMap;


import org.json.simple.JSONArray;

import ca.ece.ubc.cpen221.mp5.Restaurant;
import ca.ece.ubc.cpen221.mp5.RestaurantDB;

public class Category implements MP5Function {

    @Override
    public double f(Restaurant yelpRestaurant, RestaurantDB db) {
      HashMap<JSONArray, Double> categoryMap = new HashMap<JSONArray, Double>();
      double i = 1.0;
      
      for(Restaurant r: db.getRestaurantSet()){
          categoryMap.put(r.getCategories(), i);
          i++;
      }
      return categoryMap.get(yelpRestaurant.getCategories());
    }
    
}