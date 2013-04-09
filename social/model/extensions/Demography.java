/**
 * 
 */
package social.model.extensions;

import social.indiv.AttributedIndiv;
import uchicago.src.sim.util.Random;

/**
 * Demography contains several models for population growing, like death and
 * birth controls.
 * 
 * @author Samer
 * 
 */
public class Demography {

	private static final int RADIUS_CHANCE_DEATH = 20;

	// default value: 50;
	public static final int STEPS_PER_YEAR = 50;

	// default: 1980
	private static final int INIT_YEAR = 1980;

	// default: 2000
	private static final int FINAL_YEAR = 2000;

	private static final int FIRST_VAL_YEAR = 1990;

	//default: 1999
	private static final int SECOND_VAL_YEAR = 1999;

	private static int year = INIT_YEAR;

	private static boolean activateEqLifeExpectancy;

	private static boolean activateEqAge1stChildMothers;

	private static double stnd_dev_age_repr = 0;

	private static double stnd_dev_age_death = 2.0;

	// mean of number of children
	private static double av_number_children = 0;

	// standard deviation of number of children
	private static double stnd_dev_number_children = 0;

	public Demography(Config cfg) {
		readConfig(cfg);
	}

	public static int getYear() {
		return year;
	}

	public static void setYear(int year) {
		Demography.year = year;
	}

	public static double getAv_number_children() {
		return av_number_children;
	}

	public static double getStnd_dev_age_repr() {
		return stnd_dev_age_repr;
	}

	public static double getStnd_dev_number_children() {
		return stnd_dev_number_children;
	}

	public static int getSTEPS_PER_YEAR() {
		return STEPS_PER_YEAR;
	}

	/** stable characteristics */
	public static void readConfig(Config config) {
		activateEqLifeExpectancy = (Boolean) config
				.getParam("activateEqLifeExpectancy");

		activateEqAge1stChildMothers = (Boolean) config
				.getParam("activateEqAge1stChildMothers");

		// av_female_age_repr = 29;
		// av_male_age_repr = 32;
		// stnd_dev_age_repr = 1.0;

		// 84
		// av_female_age_death = 84;
		// 76
		// av_male_age_death = 76;

		// stnd_dev_age_death = 2.0;

		// max_age_child = 15;
		// max_age_adult = 65;

		// 2.0; 2.66 fundamented in data
		// the average number of children of the women, married, with age 16->49
		// and children (in 1980):
		// av_number_children = 2.66;
		// 1.0; 1.22 fundamented in data
		// stnd_dev_number_children = 1.22;
	}

	public static void incYear() {
		year++;
	}

	public static boolean dies(AttributedIndiv ind) {
		double age_death;
		if (ind.male())
			age_death = maleLifeExpectancy();
		else
			age_death = femaleLifeExpectancy();

		int age = ind.getAge().getValue();

		// f.ex. if death= 75, till 66 no risk
		if (age <= age_death - RADIUS_CHANCE_DEATH)
			return false;
		else if (age > age_death + RADIUS_CHANCE_DEATH)
			return true;

		Random.createNormal(age_death, stnd_dev_age_death);
		double probDeath = Random.normal.pdf(age);
		double r = Math.random();

		if (r < probDeath)
			return true;
		else
			return false;
	}

	public static double maleLifeExpectancy() {
		// regression equation
		double maxAge = 0.1183 * getYear() - 162.12;
		if (activateEqLifeExpectancy)
			return maxAge;
		else
			return 72;
	}

	// =0,177*A32 - 272,21
	public static double femaleLifeExpectancy() {
		// regression equation
		double maxAge = 0.177 * getYear() - 272.21;
		if (activateEqLifeExpectancy)
			return maxAge;
		else
			return 80;
	}

	/**
	 * The probability of wanting a baby is the double of the normal(u= average
	 * age of reproduction)
	 * 
	 * @param ind
	 * @return
	 */
	public static boolean wantsToReprod(AttributedIndiv ind) {
		// y = 0,1722x - 316 (x=year) from x=1980 to 1989
		// y = = 0,2353*x - 441,31 from x= 1990 to 2000
		// int year = ind.getAge().getBirthYear();
		// use the actual year:
		int year = getYear();

		double av_age_repr = 0;
		// regression equation
		if (year <= 1989)
			av_age_repr = 0.1722 * year - 316;
		else
			av_age_repr = 0.2353 * year - 441.31;

		// if he has 28 and the av_age_repr of his year is 28.4:
		int closestAge;
		if (activateEqAge1stChildMothers)
			closestAge = (int) Math.round(av_age_repr);
		else
			closestAge = 29;

		if (closestAge == ind.getAge().getValue())
			return true;
		else
			return false;
	}

	public static void print() {
		String s = "Population";
		s += " in year " + year;
		print(s);
	}

	private static void print(String s) {
		System.out.println(s);
	}

	public static boolean finalYearReached() {
		return getYear() == FINAL_YEAR;
	}

	public static boolean is1stValidationYear() {
		return getYear() == FIRST_VAL_YEAR;
	}

	public static boolean is2ndValidationYear() {
		return getYear() == SECOND_VAL_YEAR;
		//return getYear() == SECOND_VAL_YEAR - 1;
	}
}
