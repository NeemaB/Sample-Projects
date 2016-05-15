package ca.ece.ubc.cpen221.mp5.statlearning;

import ca.ece.ubc.cpen221.mp5.*;

import static org.junit.Assert.*;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

/**
 * A class that represents a test suite for the kMeansClusteringAlgorithm
 * 
 *
 */
public class AlgorithmsTest {

	int Test0 = 0;
	int Test1 = 1;
	int Test2 = 5;
	int Test3 = 10; 
	int Test4 = 20;
	
	@Test
	public void kMeansTest1() {
		
		RestaurantDB database = new RestaurantDB("data/restaurants.json", "data/reviews.json", "data/users.json");
		int centroidNum = 1;
		
		for(int a = 0; a < 5; a++){
			switch (a){
				case 0 : centroidNum = Test0;
				case 1 : centroidNum = Test1;
				case 2 : centroidNum = Test2;
				case 3 : centroidNum = Test3;
				case 4 : centroidNum = Test4;
				default : centroidNum = 1;
			}
			
			List<Set<Restaurant>> testList = Algorithms.kMeansClustering(centroidNum, database) ;
			double avgX = 0;
			double avgY = 0;
			
			Centroid[] centroids = new Centroid[centroidNum];
			
			//compute an effective centroid for each set. This will be the average x and average y location of 
			//restaurants in the set. This is reasonable since we can assume that if the algorithm returned then
			//the equilibrium condition was met. 
			
			for(int i = 0; i < testList.size(); i++){
				Set<Restaurant> s = testList.get(i);
				for (Restaurant r : s) {
					avgX += r.getLongitude();
					avgY += r.getLatitude();
				}
					avgX = avgX / s.size();
					avgY = avgY / s.size();
					
					centroids[i] = new Centroid(avgX, avgY);
					
					
					
					avgX = 0;
					avgY = 0;
			}
			double shortestDistance = Integer.MAX_VALUE;
			double temp;
			Centroid closestCentroid = null;
			
			//find the closestCentroid for each restaurant in each set. Assert that
			//the closest Centroid is the Centroid for the corresponding set
			
			for(int i = 0; i < testList.size(); i++){
				Set<Restaurant> s = testList.get(i);
				
				for (Restaurant r : s) {
					for(int j = 0; j < centroids.length; j++){
						temp = Math.pow(centroids[j].x - r.getLongitude(), 2) + 
								Math.pow(centroids[j].y - r.getLatitude(), 2);
						
						
						if(temp < shortestDistance){
							shortestDistance = temp;
							closestCentroid = centroids[j];
						}
					}
					
					assertEquals(centroids[i], closestCentroid);
					shortestDistance = Integer.MAX_VALUE;
				}
			
			}
		}
	}
}
	
