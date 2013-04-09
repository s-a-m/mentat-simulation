/**
 * 
 */
package social.relations;

import java.awt.Color;
import java.util.ArrayList;

import social.indiv.DemographicIndiv;

/**
 * @author Samer
 *
 */
public class Family extends Group {

	
	/**
	 * 
	 */
	public Family() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param ind
	 */
	public Family(DemographicIndiv ind) {
		super(ind);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param gr
	 */
	public Family(ArrayList<DemographicIndiv> gr) {
		super(gr);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Color color() {
		return Color.green;
	}

	@Override
	public String type() {
		return "Family";
	}

	@Override
	public String toString() {
		return "FAMILY:\n" + getGroup().toString();
	}

}
