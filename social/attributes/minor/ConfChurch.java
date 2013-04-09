/**
 * 
 */
package social.attributes.minor;

import social.attributes.Attribute;

/**
 * @author samer
 * 
 */
public class ConfChurch implements Attribute {

	private static final double DEFAULT_VALUE = 2.5;
	private double confidence;

	/*
	 * 1 grt deal 2 quite 3 not very 4 none 9 dk
	 */

	private boolean check() {
		if (confidence >= 1 && confidence <= 4)
			return true;
		else {
			if (confidence == unknownValue())
				return true;
			else
				return false;
		}
	}

	public double getConfChurch() {
		if (check())
			return confidence;
		else
			return -1;
	}

	public void setConfChurch(double conf) {
		confidence = conf;
	}

	public int compareTo(Object arg0) {
		Double myConf = confidence;
		Double otherConf = ((ConfChurch) arg0).getConfChurch();
		return myConf.compareTo(otherConf);
	}

	public void useDefaultValue() {
		confidence = DEFAULT_VALUE;
	}

	public void generateRandomValue() {
		double r = Math.random();

		// (0,1)*4 = (0,4) +1 = (1,5).
		r = (r * 4.0) + 1;
		if (r == 5)
			confidence = unknownValue();
		else
			confidence = r;
	}

	private double unknownValue() {
		return 999;
	}

	public double getNormalized() {
		double normalized = 0;
		// (1,5) -1 -->> (0,4)
		if (confidence >= 1 && confidence <= 4) {
			normalized = (confidence - 1) / 3;
		} else { // if (confidence == unknownValue()) {
			normalized = (DEFAULT_VALUE - 1) / 3;
		}

		return normalized;
	}
	
	public String toString(){
		return ""+confidence;
	}

}
