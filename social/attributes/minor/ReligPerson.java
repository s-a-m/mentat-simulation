/**
 * 
 */
package social.attributes.minor;

import social.attributes.Attribute;

/**
 * @author samer
 * 
 */
public class ReligPerson implements Attribute {

	private static final double DEFAULT_VALUE = 1.5;
	private double religPers;

	/*
	 * 1 relig 2 not rel 3 atheist 9 dk
	 */

	public double getReligPers() {
		return religPers;
	}

	public void setReligPers(double religPers) {
		this.religPers = religPers;
	}

	public int compareTo(Object arg0) {
		Double myRP = religPers;
		Double otherRP = ((ReligPerson) arg0).getReligPers();
		return myRP.compareTo(otherRP);
	}

	public void useDefaultValue() {
		religPers = DEFAULT_VALUE;
	}

	public void generateRandomValue() {
		double r = Math.random();

		// (0,1)*3 = (0,3) +1 = (1,4).
		r = (r * 3.0) + 1;
		if (r == 4)
			religPers = unknownValue();
		else
			religPers = r;
	}

	private double unknownValue() {
		return 999;
	}

	public double getNormalized() {
		double normalized = 0;
		// (1,4) -1 -->> (0,3)
		if (religPers >= 1 && religPers <= 3) {
			normalized = (religPers - 1) / 2;
		} else { // if (confidence == unknownValue()) {
			normalized = (DEFAULT_VALUE - 1) / 3;
		}

		return normalized;
	}
	
	public String toString(){
		return ""+religPers;
	}
}
