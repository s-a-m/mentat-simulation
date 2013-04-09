package social.attributes.minor;

import social.attributes.Attribute;

public class ChurchAttendance implements Attribute {

	private static final double DEFAULT_VALUE = 3.92;

	private double attendance;

	public double getAttendance() {
		return attendance;
	}

	public void setAttendance(double attendance) {
		this.attendance = attendance;
	}

	public int compareTo(Object arg0) {
		Double myAtt = attendance;
		Double otherAtt = ((ChurchAttendance) arg0).getAttendance();
		return myAtt.compareTo(otherAtt);
	}

	public void useDefaultValue() {
		attendance = DEFAULT_VALUE;
	}

	public void generateRandomValue() {
		double r = Math.random();
		// (0,1)*6 = (0,6) +1 = (1,7).
		r = (r * 6) + 1;
		attendance = r;
	}

	public double getNormalized() {
		double normalized = 0;
		// (1,7) -1 -->> (0,6)
		if (attendance >= 1 && attendance <= 7) {
			normalized = (attendance - 1) / 6;
		} else {
			normalized = (DEFAULT_VALUE - 1) / 6;
		}

		return normalized;
	}
	
	public String toString(){
		return ""+attendance;
	}

}
