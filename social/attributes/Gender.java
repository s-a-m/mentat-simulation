/**
 * 
 */
package social.attributes;

/**
 * @author Samer
 */
public class Gender implements Attribute {

	/**
	 * @uml.property name="sex"
	 */
	public boolean sex;

	/**
	 * 
	 */
	public Gender() {
		sex = generateSex();
	}

	public boolean getValue() {
		return sex;
	}

	/**
	 * @param sex
	 * @uml.property name="sex"
	 */
	public void setSex(boolean sex) {
		this.sex = sex;
	}

	private boolean generateSex() {
		boolean sx = ((Math.random() > 0.5) == true);
		return sx;
	}

	public boolean isFemale() {
		return sex == true;
	}

	public boolean isMale() {
		return sex == false;
	}

	public double sexForSocialEq() {
		if (isFemale())
			return 0;
		else
			return 1;
	}

	public int compareTo(Object arg0) {
		Gender other = (Gender) arg0;
		if ((other.isFemale() && isFemale()) || (other.isMale() && isMale()))
			return 0;
		else
			return 1;
	}

	public void useDefaultValue() {
		sex = true;
	}

	public void generateRandomValue() {
		double r = Math.random();
		if (r < 0.5)
			sex = true;
		else
			sex = false;
	}

	public double getNormalized() {
		if (sex)
			return 1;
		else
			return 0;
	}

	public String toString() {
		if (sex)
			return ""+0;
		else
			return ""+1;
	}
}
