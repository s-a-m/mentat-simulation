package social.attributes;

import social.indiv.AttributedIndiv;
import uchicago.src.sim.util.Random;

/**
 * Ideology will determine the characteristics of the political ideas of an
 * individual
 * 
 * @author Samer
 * 
 */
public class Ideology implements Attribute {

	/**
	 * it should be the average of ADULTS in 1980. Children r NEVER UNKNOWN, as
	 * we introduce them artificially
	 * 
	 * Average is 3.39, but with a big % of NULL. Mill‡n proposed 5.5
	 * 
	 */
	private static final double DEFAULT_VALUE = 5.5001;

	// mean of ideology
	private double av_ideology;

	// standard deviation of ideology
	private double stnd_dev_ideology;

	private double factor_influence;

	private double ideology;

	public AttributedIndiv owner;

	public Ideology(AttributedIndiv ind) {
		ideology = 0;
		// ideologyNormal();
		intensityNormal();
		// ideology = generateIdeology();
		owner = ind;
	}

	public double getIdeology() {
		if (ideology < 1)
			return 1;

		if (ideology > 10)
			return 10;

		return ideology;
	}

	public void setIdeology(double ideology) {
		this.ideology = ideology;
	}

	public double getAv_ideology() {
		return av_ideology;
	}

	public void setAv_ideology(double av_ideology) {
		this.av_ideology = av_ideology;
	}

	public double getFactor_influence() {
		return factor_influence;
	}

	public void setFactor_influence(double factor_influence) {
		this.factor_influence = factor_influence;
	}

	public double getStnd_dev_ideology() {
		return stnd_dev_ideology;
	}

	public void setStnd_dev_ideology(double stnd_dev_ideology) {
		this.stnd_dev_ideology = stnd_dev_ideology;
	}

	public int compareTo(Object arg0) {
		Double myId = ideology;
		Double otherId = ((Ideology) arg0).getIdeology();
		return myId.compareTo(otherId);
	}

	public String toString() {
		if (ideology <= 10 && ideology != DEFAULT_VALUE)
			return "" + ideology;
		else
			return "" + unknownValue();

	}

	private double unknownValue() {
		return 999;
	}

	/** spanish characteristics */
	public void ideologyNormal() {
		av_ideology = 4.0;
		stnd_dev_ideology = 2.0;
		ideology = generateIdeology();
	}

	/** leftist characteristics */
	public void ideologyLeft() {
		av_ideology = 2.5;
		stnd_dev_ideology = 2.0;
		ideology = generateIdeology();
	}

	/** rightist characteristics */
	public void ideologyRight() {
		av_ideology = 7.5;
		stnd_dev_ideology = 2.0;
		ideology = generateIdeology();
	}

	public void intensityNormal() {
		factor_influence = 0.1;
	}

	public void intensityHigh() {
		factor_influence = 0.2;
	}

	public void intensityLow() {
		factor_influence = 0.05;
	}

	public void spain() {
		ideologyNormal();
	}

	public double idForSocialEq() {
		double id = getIdeology();

		if (id < 1)
			return 1;

		if (id > 10)
			return 10;

		return id;
	}

	private double generateIdeology() {
		Random.createNormal(av_ideology, stnd_dev_ideology);
		double rnd = -1;
		while (rnd < 1 || rnd > 10) {
			rnd = Random.normal.nextDouble(av_ideology, stnd_dev_ideology);
		}

		return rnd;
	}

	/*
	 * private double influence(AttributedIndiv influencer) { // a child doesn't
	 * influence if (owner.child()) return 0;
	 *  // factor of influence by default double inf = factor_influence;
	 *  // if they belong to the same family, doble influence: if (owner != null &&
	 * owner.getMyFamily() != null && influencer != null) { if
	 * (owner.getMyFamily().belong(influencer)) inf = inf * FACTOR_INFLUENCE; //
	 * if they are similar, doble influence: else if
	 * (owner.isSimilarTo(influencer)) inf = inf * FACTOR_INFLUENCE; }
	 * 
	 * return inf; }
	 * 
	 * public void influencedBy(AttributedIndiv influencer) { Ideology
	 * otherIdeology = influencer.getIdeology(); // if they are the same, do
	 * nothing if (this == otherIdeology || this.getIdeology() ==
	 * otherIdeology.getIdeology()) return;
	 *  // it should maximum influence the difference between them: double
	 * diffIdeologies = Math.abs(this.getIdeology() -
	 * otherIdeology.getIdeology()); double influence = Math.min(diffIdeologies,
	 * influence(influencer));
	 * 
	 * if (this.getIdeology() < otherIdeology.getIdeology()) { ideology =
	 * ideology + influence; } else ideology = ideology - influence;
	 *  }
	 */
	public void setLimitedIdeology(double d) {
		if (d >= 1 && d <= 10)
			ideology = d;
		else
			// sociological common inference:
			useDefaultValue();

	}

	public boolean isLeft() {
		return ideology < 5;
	}

	public boolean isCentre() {
		boolean isNoAns = isNoAnswer();
		return !isNoAns && ideology >= 5 && ideology < 6;
	}

	public boolean isRight() {
		return ideology >= 6;
	}

	public boolean isNoAnswer() {
		return ideology == DEFAULT_VALUE;
	}

	public void useDefaultValue() {
		ideology = DEFAULT_VALUE;
	}

	public void generateRandomValue() {
		// (0,1)
		double rnd = Math.random();
		// (0,9) +1 -->> (1,10)
		rnd = (rnd * 9) + 1;
		/*
		 * while (rnd < 1 || rnd > 10) { rnd = Math.random() * 10; }
		 */
		ideology = rnd;
	}

	public double getNormalized() {
		double norm = ideology;
		// dividing by the maximum allowed:
		norm = norm / 10;
		return norm;
	}

}
