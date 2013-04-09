package social.model;

import java.util.ArrayList;

import social.indiv.DemographicIndiv;
import social.model.extensions.Config;
import social.model.extensions.Demography;
import social.model.extensions.SocialStructure;
import social.model.extensions.Stats;
import social.world.WorldSpace;
import uchicago.src.sim.engine.Schedule;

/**
 * @author samer
 * 
 */
public abstract class ComplexModel extends BasicModel {


	private int configNeighbRadius;
	
	// default: 500; 2303/3019 [it modifies 2xSize according to it]
	protected boolean configChooseBase2300;

	// default: true
	protected boolean configInitialChildren;
	// Steps that longs the Phase B (building frienships and couples before the
	// year counting begins)
	// default: 500; 100
	private int configStepsWarmingUp;

	protected static final double UPDATE_GRAPHS_STEPS = 10;

	private SocialStructure socialStruct;
	private Stats stats;
	private Config config;


	/**
	 * @param stats
	 *            the stats to set
	 */
	public void setStats(Stats stats) {
		this.stats = stats;
	}

	/**
	 * @return the stats
	 */
	public Stats getStats() {
		return stats;
	}

	/**
	 * @param configStepsWarmingUp
	 *            the configStepsWarmingUp to set
	 */
	public void setConfigStepsWarmingUp(int configStepsWarmingUp) {
		this.configStepsWarmingUp = configStepsWarmingUp;
	}

	/**
	 * @return the configStepsWarmingUp
	 */
	public int getConfigStepsWarmingUp() {
		return configStepsWarmingUp;
	}

	public void initParams() {
		// First, load initial params (& stats)
		config = new Config();
		loadConfigIntoModel();
		setStats(new Stats(config));

		setAgentList(new ArrayList<DemographicIndiv>());
		socialStruct = new SocialStructure(config);

		// Prepare static module(s)
		Demography.readConfig(config);

		worldSize = config.calcWorldSize();
		configNeighbRadius = (Integer) config.getParam("neighb_radius");

		wSpace = null;
		allEverSimulatedAgents = new ArrayList<DemographicIndiv>();
		schedule = new Schedule(1);
	}

	private void loadConfigIntoModel() {
		configChooseBase2300 = (Boolean) config
				.getParam("configChooseBase2300");

		configInitialChildren = (Boolean) config
				.getParam("configInitialChildren");

		setConfigStepsWarmingUp((Integer) config
				.getParam("configStepsWarmingUp"));
	}

	public void updateStats() {
		getStats().update(getAgentList());
	}

	public void buildModel() {
		print("Running BuildModel");
		try {
			wSpace = new WorldSpace(configNeighbRadius,worldSize, worldSize);

			// build population
			setAgentList(socialStruct.readPopulation(configChooseBase2300,
					configInitialChildren));

			for (int i = 0; i < getAgentList().size(); i++) {
				addAgentToSpace(getAgentList().get(i));
			}

			// agents report
			for (int i = 0; i < getAgentList().size(); i++) {
				DemographicIndiv ind = (DemographicIndiv) getAgentList().get(i);
				ind.report();
			}

		} catch (Exception e) {
			System.err.println("Error building model");
			e.printStackTrace();
		}

	}

	private void addAgentToSpace(DemographicIndiv a) {
		// agentList.add(a);
		wSpace.addAgent(a);
	}

	public void printMainStatistics() {

		int numInitialNumberAgs = 0;
		if (configChooseBase2300) {
			if (configInitialChildren)
				numInitialNumberAgs = socialStruct.getNumAdultsLoaded()
						+ socialStruct.getNumChildrenLoaded();
			else
				numInitialNumberAgs = socialStruct.getNumAdultsLoaded();
		} else
			numInitialNumberAgs = 500;

		updateStats();
		getStats().printMainStatistics(allEverSimulatedAgents,
				numInitialNumberAgs, getSteps());
	}

	public void endSimulation() {
		print("*****************STATISTICS*****************");
		printMainStatistics();
		print("*****************END OF SIMULATION*****************");

	}

}
