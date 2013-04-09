/**
 * 
 */
package social.model;

import social.model.extensions.Demography;
import uchicago.src.sim.engine.BasicAction;

/**
 * @author samer
 * 
 */
public class BasicStep extends BasicAction {

	private GraphicModel model;

	public BasicStep(GraphicModel model) {
		super();
		this.model = model;
	}

	@Override
	public void execute() {

		// update statistics with new agents
		model.updateStats();
		// shuffle the agent list:
		model.shuffleAgentList();

		// Warming-up (Phase B):
		if (warmingUp()) {
			// step each agent (and step++):
			model.stepEachAgent();

			// reproduction (it doesn't depend on deaths):
			model.reproduction();

			// If we reached the maximum of steps planned for Phase B,
			// change to Phase C:
			if (maxWarmingUp()) {
				// Activate ageing of agents:
				model.activateAging();
			}
		}
		// Normal Execution (Phase C):
		else {
			// step each agent (and step++):
			model.stepEachAgent();
			if (yearReached()) {
				// Increment current year, till final year, and record
				manageYears();
				// Once a year: kill agents:
				model.reapDeadAgents();
				Demography.print();
			}
			// Once a step: try to find couple
			model.reproduction();
		}
		model.getDisplaySurf().updateDisplay();
	}

	/**
		 * 
		 */
	private void manageYears() {
		Demography.incYear();
		//System.out.println(Demography.getYear());
		if (model.is1stValidationYear()){
			System.out.println("1st Validation Year: "+Demography.getYear());
			model.getStats().record1stValYear();
		}
		else if (model.is2ndValidationYear()) {
			System.out.println("2nd Validation Year: "+ Demography.getYear());
			model.getStats().record2ndValYear();
			model.getStats().generateDMOutput();
			// if (Demography.finalYearReached())
			model.stop();
		}
	}

	/**
	 * @return if a year has been reached in the steps counting
	 */
	private boolean yearReached() {
		return model.getSteps() % Demography.getSTEPS_PER_YEAR() == 0;
	}

	public boolean warmingUp() {
		return model.getConfigStepsWarmingUp() > 0
				&& model.getSteps() < model.getConfigStepsWarmingUp();
	}

	public boolean maxWarmingUp() {
		// return (steps % configStepsWarmingUp == 0);
		return (model.getSteps() == model.getConfigStepsWarmingUp());
	}
}
