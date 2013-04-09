/**
 * 
 */
package social.attributes;

/**
 * @author Samer
 * 
 */
public class Education implements Attribute {

	// public static enum Studies {
	// No, Pri, Sec, Uni, Doc
	// };
	//	
	// private static final Studies DEFAULT_VALUE = Studies.Sec;

	private static final double DEFAULT_VALUE = 16.5001;

	private double ageEndEduc;

	// private Studies level;

	public Education() {
		generateRandomValue();
	}

	public double getAgeEndEduc() {
		return ageEndEduc;
	}

	public void setAgeEndEduc(double ageEndEduc) {
		this.ageEndEduc = ageEndEduc;
	}

	public void generateRandomValue() {

		double r = Math.random();
		int intr = (int) Math.round(r);
		if(intr<=9)
			ageEndEduc= intr+12;
		else
			ageEndEduc= DEFAULT_VALUE;
		
		// level = rndEducation(0.2, 0.2, 0.2, 0.2, 0.2);
	}

	/*
	 * // Only increasing: it makes no sense to decrease public void riseLevel() {
	 * switch (level) { case No: level = Studies.Pri; break; case Pri: level =
	 * Studies.Sec; break; case Sec: level = Studies.Uni; break; case Uni: level =
	 * Studies.Doc; break; case Doc: break; default: break; } }
	 */
	/*
	 * public Studies rndEducation(double pNo, double pPri, double pSec, double
	 * pUni, double pDoc) { double r = Math.random(); if (r < pNo) return
	 * Studies.No; if (r < pNo + pPri) return Studies.Pri; if (r < pNo + pPri +
	 * pSec) return Studies.Sec; if (r < pNo + pPri + pSec + pUni) return
	 * Studies.Uni; else // (r < pNo + pPri + pSec + pUni + pDoc) return
	 * Studies.Doc; }
	 */

	public int levelToInt() {

		return (int) Math.round(((Double) ageEndEduc));

		/*
		 * switch (level) { case No: return 0; case Pri: return 1; case Sec:
		 * return 2; case Uni: return 3; case Doc: return 4; default: return 0; }
		 */
	}

	public int compareTo(Object arg0) {
		Education otherEd = (Education) arg0;
		int myEdLevel = levelToInt();
		int otherEdLevel = otherEd.levelToInt();
		return myEdLevel - otherEdLevel;
	}

	public String toString() {
		return "" + ageEndEduc;
	}

	public double levelToDouble() {
		// normalize:
		// (12,21) - 12 -> (0,9)

		if (ageEndEduc >= 12 && ageEndEduc <= 21)
			return ageEndEduc - 12;
		else
			// 99
			return ageEndEduc = DEFAULT_VALUE;

		/*
		 * switch (level) { case No: return 0; case Pri: return 2.5; case Sec:
		 * return 5.0; case Uni: return 7.5; case Doc: return 10; default:
		 * return 0; }
		 */
	}

	public static double getLevelFromDouble(double mean) {
		return mean;
		/*
		 * if (mean <= 0) return 12.5; if (mean <= 2.5) return 14.5; if (mean <=
		 * 5.0) return 16.5; if (mean <= 7.5) return 18.5; if (mean <= 10)
		 * return 20.5; else return 0;
		 */
	}

	public void setLevelExtended(int age) {

		ageEndEduc = age;

		/*
		 * switch (age) { case 12: case 13: level = Studies.No; break; case 14:
		 * case 15: level = Studies.Pri; break; case 16: case 17: level =
		 * Studies.Sec; break; case 18: case 19: level = Studies.Uni; break;
		 * case 20: case 21: level = Studies.Doc; break; default: // 99 is no
		 * info useDefaultValue(); break; }
		 */
	}

	public void useDefaultValue() {
		ageEndEduc = DEFAULT_VALUE;

	}

	public void cross(Education educationMom, Education educationDad) {
		double educMom = educationMom.getAgeEndEduc();
		double educDad = educationDad.getAgeEndEduc();
		if (educDad == DEFAULT_VALUE)
			educDad = educMom;
		else {
			if (educMom == DEFAULT_VALUE)
				educMom = educDad;
		}

		ageEndEduc = (educMom + educDad) / 2.0;

		/*
		 * if (newLevel <= 0) level = Studies.No; else if (newLevel <= 2.5)
		 * level = Studies.Pri; else if (newLevel <= 5.0) level = Studies.Sec;
		 * else if (newLevel <= 7.5) level = Studies.Uni; else level =
		 * Studies.Doc;
		 */
	}

	public double getNormalized() {
		double norm = levelToDouble();
		// dividing by the maximum allowed:
		norm = norm / 10;
		return norm;
	}

}
