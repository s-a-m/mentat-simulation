package social.attributes;

public class EconomicClass extends Economy {

	

	@Override
	public void cross(Economy econMom, Economy econDad) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getDoubleValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void generateRandomValue() {
		// TODO Auto-generated method stub
		
	}

	public double getNormalized() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void useDefaultValue() {
		// TODO Auto-generated method stub
		
	}

	public void setPositionExtended(int econ) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * 
	 * private static final Socioecon DEFAULT_VALUE = Socioecon.BM;
	 * public static enum Socioecon {
		B, BM, MA, A
	};

	private Socioecon position;

	public Socioecon getPosition() {
		return position;
	}

	public void setPosition(Socioecon position) {
		this.position = position;
	}

	public int positionToInt() {
		switch (position) {
		case B:
			return 0;
		case BM:
			return 1;
		case MA:
			return 2;
		case A:
			return 3;
		default:
			return 0;
		}
	}

	public int compareTo(Object arg0) {
		EconomicClass otherEc = (EconomicClass) arg0;
		int myEcPos = positionToInt();
		int otherEcPos = otherEc.positionToInt();
		return myEcPos - otherEcPos;
	}

	public double positionToDouble() {
		switch (position) {
		case B:
			return 0;
		case BM:
			return 3.33;
		case MA:
			return 6.66;
		case A:
			return 10;
		default:
			return 0;
		}
	}
	
	public double getDoubleValue(){
		return positionToDouble();
	}

	public void setPositionExtended(int pos) {
		switch (pos) {
		case 1:
			this.position = Socioecon.A;
			break;
		case 2:
			this.position = Socioecon.MA;
			break;
		case 3:
			this.position = Socioecon.BM;
			break;
		case 4:
			this.position = Socioecon.B;
			break;
		default:
			useDefaultValue();
			break;
		}
	}

	public void cross(Economy econMom, Economy econDad) {
		double douMom = ((EconomicClass)econMom).positionToDouble();
		double douDad = ((EconomicClass)econDad).positionToDouble();
		double newPos = (douMom + douDad) / 2.0;
		if (newPos <= 0)
			this.position = Socioecon.B;
		else if (newPos <= 3.33)
			this.position = Socioecon.BM;
		else if (newPos <= 6.66)
			this.position = Socioecon.MA;
		else
			this.position = Socioecon.A;
	}

	public void useDefaultValue() {
		position = DEFAULT_VALUE;
	}

	public void generateRandomValue() {
		double r= Math.random();
		if (r < 0.25)
			this.position = Socioecon.B;
		else if (r < 0.5)
			this.position = Socioecon.BM;
		else if (r < 0.75)
			this.position = Socioecon.MA;
		else
			this.position = Socioecon.A;
	}

	public double getNormalized() {
		double norm= positionToDouble();
		//dividing by the maximum allowed:
		norm = norm / 10;
		return norm;
	}*/
}
