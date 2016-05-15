package ca.ece.ubc.cpen221.mp5.queryparser;

import java.util.ArrayList;

import ca.ece.ubc.cpen221.mp5.Restaurant;

public class And implements Query {

	ArrayList<Query> atoms = new ArrayList<Query>();

	/**
	 * 
	 * @param atoms
	 *            ArrayList of Querys.
	 */

	public And(ArrayList<Query> atoms) {
		this.atoms = atoms;
	}

	@Override
	public boolean evaluate(Restaurant CurrentRest) {
		for (Query current : atoms) {
			if (!current.evaluate(CurrentRest)) {
				return false;
			}
		}

		return true;
	}

}