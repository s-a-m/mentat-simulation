/**
 * 
 */
package social.indiv.strats;

import social.indiv.extensions.IndivMainAttributes;

/**
 * @author samer
 * 
 */
public class StrategyFuzzySimilarity implements IStrategySimilarity {
	
	private double similar_limit;

	public StrategyFuzzySimilarity(double slimit) {
		similar_limit = slimit;
	}

	public double similarity(IndivMainAttributes own, IndivMainAttributes other) {

		/*
		 * Tomar el normalizado de cada uno: At1,At2 Tomar la distancia de cada
		 * uno:d(At1-At2) Negar la distancia para obtener sim(At1-At2)
		 * 
		 * Así con todos los Atts. Devolver T-Norma (sim(Atts)) Qué
		 * T-Norma??????? Prod? 0.7 0.3 =0.2 NO... Min? 0.7 , 0.3 =0.3 NO Qué
		 * T-Norma??????? lukasiewicz? <Prod NO Mejor agregación: Media: 0.7 ,
		 * 0.3 = 0.5 SI
		 */

		double distAge = distance(own.getAge().getNormalized(), other.getAge()
				.getNormalized());
		double simAge = neg(distAge);

		double distIdeol = distance(own.getIdeology().getNormalized(), other
				.getIdeology().getNormalized());
		double simIdeol = neg(distIdeol);

		double distEdu = distance(own.getEducation().getNormalized(), other
				.getEducation().getNormalized());
		double simEdu = neg(distEdu);

		double distEcon = distance(own.getEconomy().getNormalized(), other
				.getEconomy().getNormalized());
		double simEcon = neg(distEcon);

		double distRlg = distance(own.getReligion().getNormalized(), other
				.getReligion().getNormalized());
		double simRlg = neg(distRlg);

		// global sim
		double sim = (simAge + simIdeol + simEdu + simEcon + simRlg) / 5;
		// double sim = (simSex + simAge + simIdeol + simEdu + simEcon + simRlg)
		// / 6;
		
		//Forbid 0 & 1:
		if(sim==1)
			sim=0.9999;
		return sim;
	}

	private double distance(double normalized, double normalized2) {
		if (normalized <= 1 && normalized >= 0 && normalized2 <= 1
				&& normalized2 >= 0)
			return Math.abs(normalized - normalized2);
		else
			return 0;
	}

	private double neg(double dist) {
		if (dist > 1 || dist < 0)
			return 0;
		else
			return 1 - dist;
	}

	public double getSimilarLimit() {
		return similar_limit;
	}
}
