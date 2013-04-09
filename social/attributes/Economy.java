/**
 * 
 */
package social.attributes;


/**
 * @author Samer
 * 
 */
public abstract class Economy implements Attribute {

	
	public abstract void cross(Economy econMom, Economy econDad);
	
	public abstract double getDoubleValue();

	public abstract String toString();
/*
	public Socioecon rndEconomy(double pB, double pBM, double pM, //double pMA,
			double pA) {
		double r = Math.random();
		if (r < pB)
			return Socioecon.B;
		if (r < pB + pBM)
			return Socioecon.BM;
		if (r < pB + pBM + pM)
			return Socioecon.MA;
		else
			// (r < pB + pBM +pM +pMA +pA)
			return Socioecon.A;
	}
*/
}
