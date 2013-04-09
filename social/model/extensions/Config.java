/**
 * 
 */
package social.model.extensions;

import java.util.Hashtable;

/**
 * @author samer
 * 
 */
public class Config {

	private Hashtable<String, Object> params;

	public Config() {
		params = new Hashtable<String, Object>();

		// ****************************************************************
		// BasicModel:
		// ****************************************************************
		// default: 500; 2303/3019 [it modifies 2xSize according to it]
		params.put("configChooseBase2300", true);
		// default: true
		params.put("configInitialChildren", true);
		// Steps that longs the Phase B (building frienships and couples before
		// the
		// year counting begins)
		// default: 500; 100
		params.put("configStepsWarmingUp", 100);

		
		
		// ****************************************************************
		// World Space:
		// ****************************************************************
		// For 500: Default = 40; (— 50)
		// For 2303: Default = 86
		// For ~3000: Default= 98
		// 86*86 / 2303 = 3.2 = 40*40 / 500 = 98*98 / 3000
		params.put("densityAgents", 3.2);
		params.put("neighb_radius", 6);
				
		
		// ****************************************************************
		// Friends:
		// ****************************************************************
		//FIXME:
		//random friends around
		//default: 0.0001; 0.0; 0.0005
		params.put("chance_friends", 0.005);
		//FIXME: it was 0.4
		//time to know all the people that passes the threshold similar_limit
		//default: 0.4; 0.001
		params.put("chance_friends_if_similars", 0.4);
		
		// ****************************************************************
		// AttributedIndiv:
		// ****************************************************************
		/*
		 * Fuzzy similarity over [0,1]. The procedure is much better than the
		 * classical one by gratification. Now it calculates the distance
		 * between normalized attributes, negate it (1-x), and builds the mean.
		 */
		// FIXME:
		params.put("fuzzySimilarityActivated", true);
		/*
		 * Fuzzy friendship is defined in [0,1], it evolves over time and (if
		 * fuzzy similarity activated) it influences in which couple to choose
		 */
		params.put("fuzzyFriendshipActivated", true);
				
		// There are CHANCE_FRIENDS & CHANCE_FRIENDS_IF_SIMILARS in Friends.java
		// Plural: public static final double similar_limit = 1.5;
		// Common: public static final double similar_limit = 2.5;
		// FIXME: it was 2.5; 1.75
		params.put("similar_limit", 2.5);

		// Classic is usually (0->3), so to (0->1) we have to / 3:
		// default: si queremos efecto neutro, como similar_limit=2.5, 0.95
		// default: 0.95;  1.1
		// FIXME:
		params.put("fuzzy_similar_limit", 0.95);
		
		
		
		
		
		

		/*
		 * Equation for the number of children
		 */
		//DEJARLO IGUAL DEJARLO IGUAL DEJARLO IGUAL DEJARLO IGUAL 
		params.put("activateEqNumberChildren", true);
		// ****************************************************************
		// SocialStructure:
		// ****************************************************************
		
		
		
		
		
		// FIXME: false
		params.put("activateEqShouldBeMarried", true);
		
		
		
		
		
		
		
		
		
		params.put("activateEqProbHavingChildren", true);
		// controls if the ABM has random initialization or the normal EVS
		// loaded
		// initial conditions
		// true= loads from EVS
		// false= random
		params.put("empiricalInitialization", true);
		
		// ****************************************************************
		// Demography:
		// ****************************************************************
		params.put("activateEqLifeExpectancy", true);
		params.put("activateEqAge1stChildMothers", true);
	}

	public Config(Hashtable<String, Object> config) {
		this.params = config;
	}

	public Hashtable<String, Object> getConfig() {
		return params;
	}

	public void setConfig(Hashtable<String, Object> config) {
		this.params = config;
	}

	public Object getParam(String name) {
		return params.get(name);
	}

	public void addOrModifyParam(String name, Object newValue) {
		params.put(name, newValue);
	}


	/**
	 * 
	 */
	public int calcWorldSize() {
		// For 500: Default = 40; (— 50)
		// For 2303: Default = 86
		// For 3019: Default =
		// 86*86 / 2303 = 3.2 = 40*40 / 500
		// 98*98 / 3019 = 3.2 casillas - 1 agente
		Boolean configInitialChildren = (Boolean) params.get("configInitialChildren");
		Double densityAgents = (Double) params.get("densityAgents");

		double numAgs;
		if (configInitialChildren)
			numAgs= 3000;
		else
			numAgs= 2303;
		
		//WORLD_SIDE_SIZE_2300 = 86;
		//WORLD_SIDE_SIZE_3000 = 98;
		Double size= Math.floor(Math.sqrt(densityAgents*numAgs));
		return ((Long)size.longValue()).intValue();
	}
	
	public String toString() {
		String s = "";
		
		boolean configChooseBase2300 = (Boolean) params
				.get("configChooseBase2300");
		boolean configInitialChildren = (Boolean) params
				.get("configInitialChildren");
		int configStepsWarmingUp = (Integer) params
				.get("configStepsWarmingUp");

		boolean fuzzySimilarityActivated = (Boolean) params
				.get("fuzzySimilarityActivated");
		boolean fuzzyFriendshipActivated = (Boolean) params
				.get("fuzzyFriendshipActivated");
		boolean activateEqNumberChildren = (Boolean) params
				.get("activateEqNumberChildren");
		double similar_limit = (Double) params
		.get("similar_limit");
		double fuzzy_similar_limit = (Double) params
		.get("fuzzy_similar_limit");

		boolean activateEqShouldBeMarried = (Boolean) params
				.get("activateEqShouldBeMarried");
		boolean activateEqProbHavingChildren = (Boolean) params
				.get("activateEqProbHavingChildren");
		boolean empiricalInitialization = (Boolean) params
				.get("empiricalInitialization");

		boolean activateEqLifeExpectancy = (Boolean) params
				.get("activateEqLifeExpectancy");
		boolean activateEqAge1stChildMothers = (Boolean) params
				.get("activateEqAge1stChildMothers");
		
		double chance_friends = (Double) params
		.get("chance_friends");
		double chance_friends_if_similars = (Double) params
		.get("chance_friends_if_similars");
		
		s += "PhB=" + configStepsWarmingUp + ",";

		if (fuzzySimilarityActivated) {
			if (fuzzyFriendshipActivated)
				s += "Fuzzy S1:F1,";
			else
				s += "Fuzzy S1:F0,";
		} else {
			if (fuzzyFriendshipActivated)
				s += "Fuzzy S0:F1,";
			else
				s += "Fuzzy S0:F0,";
		}
		
		s+="FrsProb:"+chance_friends+":"+chance_friends_if_similars+",";
		s+="SimLims:"+similar_limit+":"+fuzzy_similar_limit+",";
		
		
		s += "Eq:";
		if (activateEqNumberChildren)
			s += "1";
		else
			s += "0";
		if (activateEqShouldBeMarried)
			s += "1";
		else
			s += "0";
		if (activateEqProbHavingChildren)
			s += "1";
		else
			s += "0";
		if (activateEqLifeExpectancy)
			s += "1";
		else
			s += "0";
		if (activateEqAge1stChildMothers)
			s += "1";
		else
			s += "0";
		s += ",";

		if (empiricalInitialization)
			s += "EVS-";
		else
			s += "RND-";

		if (configChooseBase2300) {
			if (configInitialChildren)
				s += "3000";
			else
				s += "2300";
		} else
			s += "500";
		return s;
	}
}
