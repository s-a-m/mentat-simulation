/**
 * 
 */
package social.relations;

import java.awt.Color;
import java.util.ArrayList;

import social.indiv.DemographicIndiv;

/**
 * @author Samer
 * 
 */
public abstract class Group {
	private ArrayList<DemographicIndiv> group;

	public Group() {
		group = new ArrayList<DemographicIndiv>();
	}

	public Group(DemographicIndiv ind) {
		group = new ArrayList<DemographicIndiv>();
		group.add(ind);
	}

	public Group(ArrayList<DemographicIndiv> gr) {
		group = gr;
	}

	public ArrayList<DemographicIndiv> getGroup() {
		return group;
	}

	public void setGroup(ArrayList<DemographicIndiv> group) {
		this.group = group;
	}

	public void addIndiv(DemographicIndiv ind) {
		if (!group.contains(ind))
			group.add(ind);
	}

	public boolean removeIndiv(DemographicIndiv ind) {
		return group.remove(ind);
	}

	public boolean belong(DemographicIndiv ind) {
		return group.contains(ind);
	}

	public boolean isEmpty() {
		return group.size() == 0;
	}

	public abstract String toString();

	public abstract Color color();

	public abstract String type();

}
