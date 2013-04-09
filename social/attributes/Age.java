/**
 * 
 */
package social.attributes;

/**
 * @author  Samer
 */
public class Age implements Attribute {

	/**
	 * it should be the average of ADULTS in 1980. Children r NEVER UNKNOWN, as
	 * we introduce them artificially
	 */
	private static final int DEFAULT_VALUE = 42;

	// 15? 17?
	private static int max_age_child = 17;

	private static int max_age_adult = 64;

	/**
	 * @uml.property  name="age"
	 */
	private int age;

	/**
	 * @uml.property  name="birthYear"
	 */
	private int birthYear;

	/**
	 * 
	 */
	public Age(int y) {
		age = 0;
		birthYear = y;
	}

	public int getValue() {
		return age;
	}

	/**
	 * @param  age
	 * @uml.property  name="age"
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * @return
	 * @uml.property  name="birthYear"
	 */
	public int getBirthYear() {
		return birthYear;
	}

	/**
	 * @param  birthYear
	 * @uml.property  name="birthYear"
	 */
	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}

	public void inc() {
		age++;
	}

	public String toString() {
		return "" + age;
	}

	/**
	 * @see social.attributes.Attribute#compareTo(java.lang.Object)
	 */
	public int compareTo(Object arg0) {
		Age other = (Age) arg0;
		Integer thisAge = age;
		Integer otherAge = other.getValue();
		return thisAge.compareTo(otherAge);
	}

	public void setAgeAndYear(int i) {

		if (i < 0)
			i = 0; // existe edad 0: meses de edad.
		else if (age < 99)
			age = i;
		else
			// missing info, but we need the age, so we give one
			useDefaultValue();

		int oldBirthYear = this.birthYear;
		this.birthYear = oldBirthYear - age;
	}

	public String checkAgeState() {
		if (age <= max_age_child) {
			return "child";
		} else if (age <= max_age_adult) {
			return "adult";
		} else
			return "old";
	}

	public void useDefaultValue() {
		age = DEFAULT_VALUE;
	}

	public void generateRandomValue() {
		// (0, 75)
		double r = Math.random() * 75.0;
		age = (int) Math.round(r);
		int oldBirthYear = this.birthYear;
		this.birthYear = oldBirthYear - age;
	}

	public double getNormalized() {
		double norm = age;
		// dividing by the maximum allowed:
		norm = norm / 80;
		return Math.min(1, norm);
	}

}
