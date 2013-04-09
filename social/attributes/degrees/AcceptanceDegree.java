package social.attributes.degrees;

import social.attributes.Attribute;

/**
 * @author Samer
 * 
 */
public abstract class AcceptanceDegree implements Attribute {

	private static final double DEFAULT_VALUE = 5.00001;

	// 1->10 (99 no data, but it shouldn't occur)
	private double grade;

	public AcceptanceDegree(double grade) {
		this.grade = grade;
	}

	public AcceptanceDegree() {
		this.grade = DEFAULT_VALUE;
	}

	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}

	public void setLimitedGrade(double grade) {
		if (grade >= 1 && grade <= 10)
			this.grade = grade;
		else
			this.grade = 99;
	}

	public int compareTo(Object arg0) {
		AcceptanceDegree other = (AcceptanceDegree) arg0;
		return ((Double) this.getGrade()).compareTo((Double) other.getGrade());
	}

	public String toString() {
		return getGrade() + "";
	}

	public int type() {
		if (grade < 1.5)
			return 1;
		else if (grade < 2.5)
			return 2;
		else if (grade < 3.5)
			return 3;
		else if (grade < 4.5)
			return 4;
		else if (grade < 5.5)
			return 5;
		else if (grade < 6.5)
			return 6;
		else if (grade < 7.5)
			return 7;
		else if (grade < 8.5)
			return 8;
		else if (grade < 9.5)
			return 9;
		else if (grade <= 10)
			return 10;
		else
			// =99
			return 99;
	}

	public boolean isOfType(int type) {
		return type == type();
	}

	public void useDefaultValue() {
		grade = DEFAULT_VALUE;
	}

	public void generateRandomValue() {
		// (0,1)
		double rnd = Math.random();
		// (0,9) +1 -->> (1,10)
		rnd = (rnd * 9) + 1;
		grade = rnd;
	}

	public void cross(AcceptanceDegree mom, AcceptanceDegree dad) {
		double momGrade = mom.getGrade();
		double dadGrade = dad.getGrade();
		if (momGrade != 99 && dadGrade != 99) {
			double sonGrade = (momGrade + dadGrade) / 2;
			setGrade(sonGrade);
		} else {
			if (momGrade != 99)
				setGrade(momGrade);
			else
				setGrade(dadGrade);
		}
	}

	public double getNormalized() {
		double normalized = 0;
		// (1,10) -1 -->> (0,9)
		if (grade >= 1 && grade <= 10) {
			normalized = (grade - 1) / 9;
		} else {
			normalized = (DEFAULT_VALUE - 1) / 10;
		}
		
		return normalized;
	}
}
