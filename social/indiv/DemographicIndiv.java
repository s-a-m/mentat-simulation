/**
 * 
 */
package social.indiv;

import java.util.ArrayList;

import social.indiv.extensions.IndivFamily;
import social.indiv.extensions.IndivSocialRels;
import social.indiv.strats.IStrategySimilarity;
import social.indiv.strats.StrategyClassicalSimilarity;
import social.indiv.strats.StrategyFuzzySimilarity;
import social.model.extensions.Config;
import social.model.extensions.Demography;
import social.relations.Family;
import social.relations.Friends;

/**
 * @author samer
 * 
 */
public class DemographicIndiv extends AttributedIndiv {

	protected int steps;
	protected IndivSocialRels socialRelationships;
	protected IndivFamily relatives;
	// public double similar_limit;
	// public double fuzzy_similar_limit;
	/** Child, adult or old */
	protected int numChildren;
	protected boolean willHaveChildren; 
	protected boolean hadChildren;
	protected boolean ageingActivated;
	protected boolean shouldBeMarried;
	protected boolean probHavingChildrenConsideringAge;
	/**
	 * Only the agents loaded from EVS or born during the simulation are
	 * comparable with the EVS data. This means that the children introduced for
	 * demographic purposes should not be considered
	 */
	protected boolean isComparableWithEVS;
	protected int statTotalNumFriends = 0;
	protected int statTotalNumRelatives = 0;
	/**
	 * Fuzzy similarity over [0,1]. The procedure is much better than the
	 * classical one by gratification. Now it calculates the distance between
	 * normalized attributes, negate it (1-x), and builds the mean.
	 */
	// private static boolean fuzzySimilarityActivated;
	/**
	 * Fuzzy friendship is defined in [0,1], it evolves over time and (if fuzzy
	 * similarity activated) it influences in which couple to choose
	 */
	private static boolean fuzzyFriendshipActivated;

	/**
	 * Equation for the number of children
	 */
	protected static boolean activateEqNumberChildren;

	protected double simWithLastCoupleIfAny;

	protected double frWithLastCoupleIfAny;

	private Config config;

	public Friends getMyFriends() {
		return socialRelationships.getMyFriends();
	}

	public void setMyFriends(Friends myFriends) {
		this.socialRelationships.setMyFriends(myFriends);
	}

	public Family getMyFamily() {
		return relatives.getMyFamily();
	}

	public void setMyFamily(Family myFamily) {
		this.relatives.setMyFamily(myFamily);
	}

	public DemographicIndiv getCouple() {
		return socialRelationships.getCouple();
	}

	public void setCouple(DemographicIndiv couple) {
		this.socialRelationships.setCouple(couple);
	}

	public IndivSocialRels getRelationships() {
		return socialRelationships;
	}

	public void setRelationships(IndivSocialRels relationships) {
		this.socialRelationships = relationships;
	}

	public IndivFamily getRelatives() {
		return relatives;
	}

	public void setRelatives(IndivFamily relatives) {
		this.relatives = relatives;
	}

	public void saveStatistics() {
		statTotalNumFriends = socialRelationships.getMyFriends().getGroup()
				.size();
		statTotalNumRelatives = relatives.getMyFamily().getGroup().size();
	}

	public int getStatTotalNumFriends() {
		return statTotalNumFriends;
	}

	public void setStatTotalNumFriends(int totalNumFriends) {
		this.statTotalNumFriends = totalNumFriends;
	}

	public int getStatTotalNumRelatives() {
		return statTotalNumRelatives;
	}

	public void setStatTotalNumRelatives(int totalNumRelatives) {
		this.statTotalNumRelatives = totalNumRelatives;
	}

	public int getNumChildren() {
		return numChildren;
	}

	public void setNumChildren(int numChildren) {
		this.numChildren = numChildren;
	}

	public boolean isWillHaveChildren() {
		return willHaveChildren;
	}

	public boolean isAgeingActivated() {
		return ageingActivated;
	}

	public void setAgeingActivated(boolean ageingActivated) {
		this.ageingActivated = ageingActivated;
	}

	public boolean isShouldBeMarried() {
		return shouldBeMarried;
	}

	public void setShouldBeMarried(boolean shouldBeMarried) {
		this.shouldBeMarried = shouldBeMarried;
	}

	public boolean isProbHavingChildren() {
		return probHavingChildrenConsideringAge;
	}

	public void setProbHavingChildren(boolean probHavingChildren) {
		this.probHavingChildrenConsideringAge = probHavingChildren;
	}

	public boolean isComparableWithEVS() {
		return isComparableWithEVS;
	}

	public void setComparableWithEVS(boolean isLoadedFromEVS) {
		this.isComparableWithEVS = isLoadedFromEVS;
	}

	public boolean isHadChildren() {
		return hadChildren;
	}

	public void setHadChildren(boolean hadChildren) {
		this.hadChildren = hadChildren;
	}

	public double getSimWithLastCoupleIfAny() {
		return simWithLastCoupleIfAny;
	}

	public void setSimWithLastCoupleIfAny(double sim) {
		this.simWithLastCoupleIfAny = sim;
	}

	public double getFrWithLastCoupleIfAny() {
		return frWithLastCoupleIfAny;
	}

	public void setFrWithLastCoupleIfAny(double fr) {
		this.frWithLastCoupleIfAny = fr;
	}

	public DemographicIndiv(Config cfg) {
		this.config = cfg;
		steps = 0;
		fuzzyFriendshipActivated = (Boolean) config
				.getParam("fuzzyFriendshipActivated");
		activateEqNumberChildren = (Boolean) config
				.getParam("activateEqNumberChildren");
		initDemog();
		mainAtts.initSim(chooseStrat());
	}

	private IStrategySimilarity chooseStrat() {
		boolean fuzzySimilarityActivated = (Boolean) config
				.getParam("fuzzySimilarityActivated");
		double similar_limit = (Double) config.getParam("similar_limit");
		double fuzzy_similar_limit = (Double) config
				.getParam("fuzzy_similar_limit");

		if (!fuzzySimilarityActivated)
			return new StrategyClassicalSimilarity(similar_limit);
		else
			return new StrategyFuzzySimilarity(fuzzy_similar_limit);
	}

	public void initDemog() {
		socialRelationships = new IndivSocialRels(fuzzyFriendshipActivated,
				this);

		relatives = new IndivFamily();

		relatives.setMyFamily(new Family(this));

		double ch_fr = (Double) config.getParam("chance_friends");
		double ch_fr_sim = (Double) config
				.getParam("chance_friends_if_similars");
		socialRelationships.setMyFriends(new Friends(this, ch_fr, ch_fr_sim));

		willHaveChildren = false;

		hadChildren = false;

		simWithLastCoupleIfAny = 0;

		frWithLastCoupleIfAny = 0;

		ageingActivated = false;

		shouldBeMarried = false;

		// usually agents are loaded from EVS (except the initial children
		// introduced for demographic purposes):
		isComparableWithEVS = true;

		// Introduced probability of having children
		// If she is 40 years, low; if she is 23, high... this attribute gives
		// the results of the evaluation
		probHavingChildrenConsideringAge = true;

		numChildren = 0; // generateNumberChildren();

	}

	public void makeFamilyBondTo(DemographicIndiv ind) {
		if (ind != null && getMyFamily() != null) {
			getMyFamily().addIndiv(ind);
			Family family = ind.getMyFamily();
			if (family != null) {
				family.addIndiv(this);
				makeBondTo(ind, family.type(), family.color());
			}
		}
	}

	private void makeFriendBondTo(DemographicIndiv ind) {
		if (ind != null) {
			getMyFriends().addIndiv(ind);
			Friends friends = ind.getMyFriends();
			if (friends == null) {
				ind.setMyFriends(new Friends(ind, (Double) config
						.getParam("chance_friends"), (Double) config
						.getParam("chance_friends_if_similars")));
				friends = ind.getMyFriends();
			}
			friends.addIndiv(this);
			makeBondTo(ind, friends.type(), friends.color());
			// imprime("FRIENDS:");
			// imprime(this);
			// imprime(ind);
		}
	}

	public void relationWithNeighbours() {
		if (getWSpace() != null) {
			ArrayList<DemographicIndiv> neighbours = getWSpace().boxNeighbors(
					this);
			if (neighbours.size() > 0) {
				beFriends(neighbours);
			}
		}
	}

	private void beFriends(ArrayList<DemographicIndiv> neighbours) {
		for (DemographicIndiv ind : neighbours) {
			// if he's not my friend:
			if (getMyFriends() != null && !getMyFriends().belong(ind)) {
				// If they are not family (nor me), there is a chance to be
				// friends (friends are possible couples... and family can't be
				// couple)
				if (!relatives.isFamilyOf(ind)) {
					boolean similar = mainAtts.isSimilarTo(ind);
					if (getMyFriends().becomeFriends(similar))
						makeFriendBondTo(ind);
				}
			}
		}
	}

	public void step() throws Exception {
		if (ageingActivated) {
			steps++;
			if (steps % Demography.getSTEPS_PER_YEAR() == 0) {
				getMainAtts().getAge().inc();
				generateWillHaveChildren();
			}
		}
		// Possibility of new Friends:
		relationWithNeighbours();

		// Update fuzzy grades of friends:
		socialRelationships.updateFuzzyFriendship();
	}

	public boolean dies() {
		if (Demography.dies(this)) {
			// save my main values for statistical purposes:
			saveStatistics();

			removeAllBonds();
			removeCouple();
			removeFamily();
			removeFriends();
			return true;
		} else
			return false;
	}

	private void removeFriends() {
		if (socialRelationships.getMyFriends() != null) {
			ArrayList<DemographicIndiv> friends = socialRelationships
					.getMyFriends().getGroup();
			DemographicIndiv fr;
			for (int i = 0; i < friends.size(); i++) {
				fr = friends.get(i);
				fr.removeEdgesTo(this);
				fr.removeEdgesFrom(this);
				if (fr.getMyFriends() != null)
					fr.getMyFriends().removeIndiv(this);
				if (fr.getCouple() != null && fr.getCouple().equals(this))
					fr.setCouple(null);

			}
			socialRelationships.getMyFriends().removeIndiv(this);
			socialRelationships.setMyFriends(null);
		}
	}

	private void removeFamily() {
		if (relatives.getMyFamily() != null) {
			ArrayList<DemographicIndiv> family = relatives.getMyFamily()
					.getGroup();
			DemographicIndiv relative;
			for (int i = 0; i < family.size(); i++) {
				relative = family.get(i);
				relative.removeEdgesTo(this);
				relative.removeEdgesFrom(this);
				if (relative.getMyFamily() != null)
					relative.getMyFamily().removeIndiv(this);
			}
			relatives.getMyFamily().removeIndiv(this);
			relatives.setMyFamily(null);
		}
	}

	private void removeCouple() {
		if (socialRelationships.getCouple() != null) {
			socialRelationships.getCouple().setCouple(null);
			socialRelationships.setCouple(null);
		}
	}

	public DemographicIndiv bornChild() throws Exception {
		DemographicIndiv child = newCrossedIndiv(this, socialRelationships
				.getCouple());
		// print("CHILD:\n");
		// print(child);
		hadChildren = true;
		makeFamilyBondTo(child);
		if (socialRelationships.getCouple() != null)
			socialRelationships.getCouple().makeFamilyBondTo(child);
		return child;
	}

	/**
	 * For new individuals dynamically (children of couples)
	 * 
	 * @param mother
	 * @param father
	 * @return
	 */
	private DemographicIndiv newCrossedIndiv(DemographicIndiv mother,
			DemographicIndiv father) {
		DemographicIndiv son = new DemographicIndiv(config);

		if (father == null) {
			son.getMainAtts().cross(mother.getMainAtts(), null);
			return son;
		} else if (mother == null) {
			son.getMainAtts().cross(father.getMainAtts(), null);
			return son;
		}
		son.getMainAtts().cross(mother.getMainAtts(), father.getMainAtts());
		return son;
	}

	private void generateWillHaveChildren() throws Exception {

		if (getMainAtts().getSex().isMale()) {
			willHaveChildren = false;
			return;
		}
		// if married according to EVS, wants to have children
		boolean wantsToReprod = !child() && shouldBeMarried;

		// if not married according to EVS, depends on her age if she wants to
		// have children or not
		if (!wantsToReprod)
			wantsToReprod = !child() && Demography.wantsToReprod(this);

		boolean will = getMainAtts().getSex().isFemale() && wantsToReprod
				&& socialRelationships.foundCouple();
		willHaveChildren = will;
	}

	public int reproduction() {
		// Introduced probability of having children "testProbHavingChildren"
		// If she is 40 years, low; if she is 23, high...
		if (willHaveChildren && !hadChildren
				&& probHavingChildrenConsideringAge) {
			updateNumChildren();
			// print("numChildren in reproduction:" + getNumChildren());
			return getNumChildren();
		} else
			return 0;
	}

	public void updateNumChildren() {
		if (!female()
				|| child()
				|| (socialRelationships.getCouple() != null && socialRelationships
						.getCouple().child()))
			numChildren = 0;
		else
			numChildren = relatives.generateNumberChildren(female(),
					activateEqNumberChildren);
	}

	public void notComparableWithEVS() {
		isComparableWithEVS = false;
		// they are young, so for sure, no equation needed:
		probHavingChildrenConsideringAge = true;
	}

}
