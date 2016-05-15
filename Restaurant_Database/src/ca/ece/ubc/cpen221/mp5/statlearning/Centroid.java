package ca.ece.ubc.cpen221.mp5.statlearning;

/**
 * This class represents a Centroid object. The Centroid is the center point of 
 * a set of Restaurants in a cluster at equilibrium
 * 
 * 
 *
 */
public class Centroid {
	public double x;
	public double y;
	
	public Centroid(double x, double y){
		this.x = x;
		this.y = y;
	}
	public Centroid(Centroid c){
		this.x = c.x;
		this.y = c.y;
	}
	

}
