/**
 * 
 */
package social.indiv.extensions;

import java.util.ArrayList;

import social.indiv.DemographicIndiv;
import social.model.extensions.Demography;
import social.relations.Family;

/**
 * @author samer
 * 
 */
public class IndivFamily {

	private Family myFamily;

	// private DemographicIndiv owner;

	public Family getMyFamily() {
		return myFamily;
	}

	public void setMyFamily(Family myFamily) {
		this.myFamily = myFamily;
	}

	// ****************************************************************
	// Family:
	// ****************************************************************

	/**
	 * Says if "ind" is my family. If "ind" is me, return true.
	 */
	public boolean isFamilyOf(DemographicIndiv ind) {
		if (getMyFamily() == null)
			return false;
		return getMyFamily().belong(ind);
	}

	/**
	 * Everybody has some family
	 * 
	 * @return
	 */
	boolean hasFamily() {
		return getMyFamily() != null;
	}

	/**
	 * Links (and relates as family) all the brothers to each other
	 * 
	 * @param agents
	 */
	public static void bondBrotherhood(ArrayList<DemographicIndiv> agents) {
		int size = agents.size();
		int k = 0;
		for (int i = 0; i < size - 1; i++) {
			DemographicIndiv ind1 = (DemographicIndiv) agents.get(i);
			int i2 = i + 1;
			for (k = i2; k < size; k++) {
				DemographicIndiv ind2 = (DemographicIndiv) agents.get(k);
				ind1.makeFamilyBondTo(ind2);
			}
		}
	}

	public int generateNumberChildren(boolean female,
			boolean activateEqNumberChildren) {
		if (!female)// || child() || (couple != null && couple.child()))
			return 0;

		// It has to be fixed by 1.209*, because the original data was built
		// without taking into account the women with 0 children (neither the
		// children, nor the older than 49).

		double resultTable = 1.209 * tableNatalityData();
		int numCh = (int) Math.round(resultTable);

		if (activateEqNumberChildren)
			return numCh;
		else
			return 2;
	}

	private double tableNatalityData() {
		// equivalent to a regression equation, but more precise:
		int y = Demography.getYear();

		switch (y) {
		case 1980:
			return 2.2;
		case 1981:
			return 2.04;
		case 1982:
			return 1.94;
		case 1983:
			return 1.8;
		case 1984:
			return 1.73;
		case 1985:
			return 1.64;
		case 1986:
			return 1.56;
		case 1987:
			return 1.5;
		case 1988:
			return 1.45;
		case 1989:
			return 1.4;
		case 1990:
			return 1.36;
		case 1991:
			return 1.33;
		case 1992:
			return 1.32;
		case 1993:
			return 1.27;
		case 1994:
			return 1.2;
		case 1995:
			return 1.17;
		case 1996:
			return 1.16;
		case 1997:
			return 1.17;
		case 1998:
			return 1.16;
		case 1999:
			return 1.19;
		case 2000:
			return 1.23;
		default:
			return 0;
		}
	}
}
