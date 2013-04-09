package social.attributes;

public class EconomicStatus extends Economy {

	private static final double DEFAULT_VALUE = 0;

	// From STATUSR of EVS
	private double statusr;

	public EconomicStatus() {
		useDefaultValue();
	}

	public double getStatusr() {
		return statusr;
	}

	public void setStatusr(double statusr) {
		this.statusr = statusr;
	}

	public int compareTo(Object arg0) {
		EconomicStatus otherEc = (EconomicStatus) arg0;
		return (int) statusr - (int) otherEc.getStatusr();
	}

	public void useDefaultValue() {
		statusr = DEFAULT_VALUE;
	}

	public double getDoubleValue() {
		return statusr;
	}

	@Override
	public void cross(Economy econMom, Economy econDad) {
		double douMom = ((EconomicStatus) econMom).getStatusr();
		double douDad = ((EconomicStatus) econDad).getStatusr();
		double newStat = (douMom + douDad) / 2.0;
		statusr = newStat;
	}

	public void generateRandomValue() {
		// (0,1)
		double r = Math.random();
		// (+0,+5) - 2 -->> (-2,+3)
		r = (r * 5) - 2;
		statusr = r;
	}

	public double getNormalized() {
		// (-2,+3) + 2 -->> (+0,+5)
		// (+0,+5) / 5 -->> (+0,+1)
		double normalized = (statusr + 2) / 5;
		return normalized;
	}

	@Override
	public String toString() {
		return ""+statusr;
	}
}
