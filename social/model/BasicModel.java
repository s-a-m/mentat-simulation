package social.model;

import java.util.ArrayList;

import social.indiv.DemographicIndiv;
import social.indiv.extensions.IndivFamily;
import social.model.extensions.Demography;
import social.world.WorldSpace;
import uchicago.src.sim.engine.Schedule;
import uchicago.src.sim.engine.SimModelImpl;
import uchicago.src.sim.util.SimUtilities;

public abstract class BasicModel extends SimModelImpl {

	protected int worldSize;

	private int steps = 0;

	// All agents, including the dead ones
	// Useful for the statistics in the end of the simulation
	protected ArrayList<DemographicIndiv> allEverSimulatedAgents;

	protected Schedule schedule;

	protected WorldSpace wSpace;

	private ArrayList<DemographicIndiv> agentList;

	public String getName() {
		return "Mentat (beta)";
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public String[] getInitParam() {
		String[] initParams = { "NumAgents", "WorldXSize", "WorldYSize",
				"Money", "AgentMinLifespan", "AgentMaxLifespan" };
		return initParams;
	}

	public int getWorldSize() {
		return worldSize;
	}

	public void setWorldSize(int wxs) {
		worldSize = wxs;
	}

	/**
	 * @param agentList
	 *            the agentList to set
	 */
	public void setAgentList(ArrayList<DemographicIndiv> agentList) {
		this.agentList = agentList;
	}

	/**
	 * @return the agentList
	 */
	public ArrayList<DemographicIndiv> getAgentList() {
		return agentList;
	}

	/**
	 * @param steps
	 *            the steps to set
	 */
	public void setSteps(int steps) {
		this.steps = steps;
	}

	/**
	 * @return the steps
	 */
	public int getSteps() {
		return steps;
	}

	public void print(Object str) {
		System.out.println(str);
	}

	public void shuffleAgentList() {
		SimUtilities.shuffle(getAgentList());
	}

	public void activateAging() {
		// Activate aging of agents:
		for (int i = 0; i < getAgentList().size(); i++) {
			DemographicIndiv ind = (DemographicIndiv) getAgentList().get(i);
			ind.setAgeingActivated(true);
		}
	}

	public void stepEachAgent() {
		for (int i = 0; i < getAgentList().size(); i++) {
			DemographicIndiv ind = (DemographicIndiv) getAgentList().get(i);
			try {
				ind.step();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		setSteps(getSteps() + 1);
	}

	public boolean is1stValidationYear() {
		return (Demography.is1stValidationYear());
	}

	public boolean is2ndValidationYear() {
		return (Demography.is2ndValidationYear());
	}

	private void addCloseAgent(DemographicIndiv child, DemographicIndiv mother) {
		getAgentList().add(child);
		wSpace.addCloseAgent(child, mother);
	}

	public int reapDeadAgents() {
		int count = 0;
		for (int i = (getAgentList().size() - 1); i >= 0; i--) {
			DemographicIndiv ind = (DemographicIndiv) getAgentList().get(i);
			if (ind.dies()) {
				allEverSimulatedAgents.add(ind);

				wSpace.removeAgentAt((int) ind.getX(), (int) ind.getY());
				getAgentList().remove(i);
				count++;
			}
		}
		print("Number of living agents is: " + getAgentList().size());
		return count;
	}

	public void reproduction() {
		try {
			for (int i = 0; i < getAgentList().size(); i++) {
				DemographicIndiv ind = (DemographicIndiv) getAgentList().get(i);
				// only if female and in certain period of life
				if (ind.isWillHaveChildren()) {
					ArrayList<DemographicIndiv> children = new ArrayList<DemographicIndiv>();

					int numChildren = ind.reproduction();
					for (int j = 0; j < numChildren; j++) {
						DemographicIndiv child = ind.bornChild();
						addCloseAgent(child, ind);
						children.add(child);
					}

					IndivFamily.bondBrotherhood(children);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected int countLivingAgents() {
		int livingAgents = getAgentList().size();
		return livingAgents;
	}
}
