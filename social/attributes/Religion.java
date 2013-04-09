/**
 * 
 */
package social.attributes;


/**
 * @author Samer
 * 
 */
public class Religion implements Attribute {

	/**
	 * it should be the average of ADULTS in 1980. Children r NEVER UNKNOWN, as we introduce them
	 * artificially  */
	private static final double DEFAULT_VALUE = 2.43;

	private final int NO = 1;

	private final int CENT = 2;

	private final int LAX = 3;

	private final int ORTO = 4;

	/*
	 * public static enum Intensity { No, Cent, Lax, Orto };
	 */

	private double religiosity;


	/**
	 * 
	 */
	public Religion() {
		generateRandomValue();
	}

	public double getReligiosity() {
		return religiosity;
	}

	public void setReligiosity(double religiosity) {
		this.religiosity = religiosity;
	}

	public void religionNo() {
		religiosity = rndReligion(0.8, 0.2, 0, 0);
	}

	public void religionCent() {
		religiosity = rndReligion(0.1, 0.8, 0.1, 0);
	}

	public void religionLax() {
		religiosity = rndReligion(0, 0.1, 0.8, 0.1);
	}

	public void religionOrto() {
		religiosity = rndReligion(0, 0, 0.2, 0.8);
	}

	public void religionRandom() {
		religiosity = rndReligion(0.25, 0.25, 0.25, 0.25);
	}

	public double rndReligion(double pNo, double pCent, double pLax,
			double pOrto) {

		double r = Math.random();
		if (r < pNo)
			return NO;
		if (r < pNo + pCent)
			return CENT;
		if (r < pNo + pCent + pLax)
			return LAX;
		else
			// (r < pNo + pCent + pLax + pOrto)
			return ORTO;
	}

	/*
	 * public int rlgToInt() { switch (religiosity) { case No: return 0; case
	 * Cent: return 1; case Lax: return 2; case Orto: return 3; default: return
	 * -1; } }
	 * 
	 * public double rlgForSocialEq() { switch (religiosity) { case No: return
	 * 1; case Cent: return 2; case Lax: return 3; case Orto: return 4; default:
	 * return 0; } }
	 */

	public int compareTo(Object arg0) {
		Religion otherR = (Religion) arg0;
		double myRLevel = religiosity;
		double otherRLevel = otherR.getReligiosity();
		return (int) myRLevel - (int) otherRLevel;
	}

	public String toString() {
		return rlgToStndDouble() + "";
	}

	public double rlgToStndDouble() {
		// 1.5----4
		// X ----10
		// X= 1.5*10/4

		return religiosity * 10 / 4;
	}

	/**
	 * Values 1->4, 0.75 each range
	 * @return
	 */
	public String myReligType() {
		if (religiosity < 1.75)
			return "NON";
		else if (religiosity < 2.5)
			return "ALT";
		else if (religiosity < 3.25)
			return "LOW";
		else if (religiosity <= 4)
			return "EC";
		else
			return "NON";
	}

	public boolean isClassif(String type) {
		return myReligType().equals(type);
	}

	public void setTurnedReligiosity(double rlg) {
		if(rlg==4.0)
			religiosity= 1;
		else if(rlg==3.0)
			religiosity= 2;
		else if(rlg==2.0)
			religiosity= 3;
		else if(rlg==1.0)
			religiosity= 4;
		else
			useDefaultValue();
		
	}
	
	public void useDefaultValue(){
		religiosity= DEFAULT_VALUE;
	}
	
	public void generateRandomValue(){
		religionRandom();
	}

	public double getNormalized() {		
		if(religiosity<=1)
			return 0;
		else{
			double y= 0.33*religiosity-0.33;
			return Math.min(1,y);
		}
	}
}
