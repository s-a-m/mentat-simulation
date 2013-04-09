/**
 * 
 */
package social.indiv.strats;

import social.indiv.extensions.IndivMainAttributes;

/**
 * @author samer
 * 
 */
public class StrategyClassicalSimilarity implements IStrategySimilarity {

	private double similar_limit;
	
	public StrategyClassicalSimilarity (double slimit){
		similar_limit= slimit;
	}
	
	public double similarity(IndivMainAttributes own, IndivMainAttributes other) {
		double sim = 0;

		// if they have the same class or not
		// 1
		sim = sim + 1;

		// if they have the same sex, more similarity. No penalization if not
		// 0.25
		if ((own.getSex().isFemale() && other.getSex().isFemale())
				|| (own.getSex().isMale() && other.getSex().isMale()))
			sim = sim + 0.25;

		// if they belong to the same generation, more similarity
		// 0.25
		int diffAges = Math.abs(own.getAge().getValue()
				- other.getAge().getValue());
		if (diffAges <= 5) {
			sim = sim + 0.25;
		} else if (diffAges >= 15) {
			sim = sim - 0.25;
		}

		// if they have similar ideology, more similarity
		// 0.25
		double diffIdeologies = Math.abs(own.getIdeology().getIdeology()
				- other.getIdeology().getIdeology());
		if (diffIdeologies <= 1) {
			sim = sim + 0.25;
		} else if (diffIdeologies >= 2) {
			sim = sim - 0.25;
		}

		// if they have similar education, more similarity
		// 0.25
		int cmpEduc = (own.getEducation().compareTo(other.getEducation()));
		if (cmpEduc == 0)
			sim = sim + 0.25;
		else if (cmpEduc >= 2 || cmpEduc <= -2)
			sim = sim - 0.25;

		// if they have similar education, more similarity
		// 0.25
		int cmpEco = (own.getEconomy().compareTo(other.getEconomy()));
		if (cmpEco == 0)
			sim = sim + 0.25;
		else if (cmpEco >= 2 || cmpEco <= -2)
			sim = sim - 0.25;

		// if they have similar religion, more similarity
		// 0.25
		int cmpRel = (own.getReligion().compareTo(other.getReligion()));
		if (cmpRel == 0)
			sim = sim + 0.25;
		else if (cmpRel >= 2 || cmpRel <= -2)
			sim = sim - 0.25;

		return sim;
	}

	public double getSimilarLimit() {
		return similar_limit;
	}

}
