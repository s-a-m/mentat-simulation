package social.indiv.extensions;

import java.util.ArrayList;

import social.indiv.DemographicIndiv;
import social.relations.Friends;

public class IndivSocialRels {

	private DemographicIndiv couple;

	private Friends myFriends;

	private boolean fuzzyFriendshipActivated;
	// private boolean fuzzySimilarityActivated;

	private DemographicIndiv owner;

	/**
	 * @param fuzzyFriendshipActivated
	 */
	public IndivSocialRels(boolean fuzzyFriendshipActivated,
			DemographicIndiv owner) {
		this.fuzzyFriendshipActivated = fuzzyFriendshipActivated;
		this.owner = owner;
		couple = null;
	}

	public DemographicIndiv getCouple() {
		return couple;
	}

	public void setCouple(DemographicIndiv couple) {
		this.couple = couple;
	}

	public Friends getMyFriends() {
		return myFriends;
	}

	public void setMyFriends(Friends myFriends) {
		this.myFriends = myFriends;
	}

	// ****************************************************************
	// Friendship / Fuzzy:
	// ****************************************************************

	public void updateFuzzyFriendship() throws Exception {
		if (fuzzyFriendshipActivated) {
			// check all the friends
			for (DemographicIndiv ind : getMyFriends().getGroup()) {
				// generate a new grade for each one
				getMyFriends().setGradeOf(ind, generateNewGrade(ind));
				owner
						.setColorOfEdge(ind, getMyFriends().getColorFromGrade(
								ind));
			}
		}
	}

	/**
	 * Generates the new grade according to the friendship evolution formula (It
	 * is a logistic function, as formally described in wikipedia)
	 * 
	 * Eq(1)= M(t) + d(t)*M(t)*K(t)*r
	 * 
	 * r= J*Sim
	 * 
	 * J = J0 * exp(p*Sim)
	 * 
	 * K(t)= k - M(t)
	 * 
	 * K is the system capacity (maximum = 1; K(t) is how much till maximum) r
	 * is the growth rate p, J are constant factors Sim is similarity between
	 * two agents
	 * 
	 * @param ind
	 * @return
	 * @throws Exception
	 */
	private double generateNewGrade(DemographicIndiv ind) throws Exception {
		// it works :)
		double J0 = 0.001;
		double p = 5.8;
		// always fuzzy similarity (function defined for [0,1])
		double sim = owner.getMainAtts().similarity(ind);

		double J = J0 * Math.exp(p * sim);

		int K = 1;
		int dt = 1;
		double Mt = getMyFriends().getGradeOf(ind);
		double r = J * sim;
		double Kt = K - Mt;

		double newMt = Mt + dt * Mt * Kt * r;

		return newMt;
	}

	/**
	 * This function should find the friend who is the most similar to her
	 * 
	 * @param ind
	 * @return
	 * @throws Exception
	 */
	public DemographicIndiv findCouple() throws Exception {

		ArrayList<DemographicIndiv> friendsList = getMyFriends().getGroup();

		// if they are no friends, or i'm the only one in the list:
		if (friendsList == null || friendsList.size() == 1)
			return null;

		boolean hasCouple = false;
		boolean eqSex = false;
		boolean isAdult = false;

		double howSimilar = 0;
		double howFriends = 0;
		double maxCompatibility = -1;
		double compatibility = 0;
		DemographicIndiv maxSimilarInd = null;
		int i = 0;

		for (DemographicIndiv similarInd : friendsList) {
			hasCouple = !(similarInd.getCouple() == null);
			// As it has to be always different sex, I never select myself
			eqSex = (owner.getSex().getValue() == similarInd.getSex()
					.getValue());
			isAdult = similarInd.adult();

			// how similar I am
			howSimilar = owner.getMainAtts().similarity(similarInd);

			// if fuzzy activated, take into account grades

			// howSimilar is always < 1 if it's fuzzy and >=1 if it's classical
			boolean fuzzySimilarityActivated = howSimilar < 1;
			if (fuzzySimilarityActivated && fuzzyFriendshipActivated) {
				// pick grade of my friend
				howFriends = getMyFriends().getGradeOf(similarInd);
				// compatibility takes into account how similar am I and how
				// friends we are
				compatibility = (howSimilar + howFriends) / 2;

			} else {
				// if there are no grades, compatibility is just similarity
				compatibility = howSimilar;
			}

			/*
			 * System.out.print(", with fr " +
			 * myFriends.getGradeOf(similarInd)); System.out.print(", with sim "
			 * + howSimilar);
			 */
			if (isAdult && !eqSex && !hasCouple) {
				i++;
				// print("#" + i + " with compatib " + compatibility);
				if (compatibility > maxCompatibility) {
					maxCompatibility = compatibility;
					maxSimilarInd = similarInd;
				}
			}
		}
		return maxSimilarInd;
	}

	public boolean foundCouple() throws Exception {
		if (getCouple() != null)
			return true;
		else {
			setCouple(findCouple());
			if (getCouple() != null) {
				// define couple:
				getCouple().setCouple(owner);
				owner.makeFamilyBondTo(getCouple());

				// all this for stats: (FUZZY SIM!! always!)
				double sim = owner.getMainAtts().similarity(getCouple());
				// the friendship from her point of view:
				double frHers = getMyFriends().getGradeOf(getCouple());
				owner.setSimWithLastCoupleIfAny(sim);
				getCouple().setSimWithLastCoupleIfAny(sim);
				owner.setFrWithLastCoupleIfAny(frHers);
				getCouple().setFrWithLastCoupleIfAny(frHers);
				// print("COUPLE:");
				// print("Similarity= " + fuzzySimilarity(couple) +
				// "; Friendship= "
				// + frHers + "; # Frs= " + myFriends.getGroup().size());
				// print(this);
				// print(couple);
			}
		}

		return getCouple() != null;
	}

}