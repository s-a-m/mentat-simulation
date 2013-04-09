/**
 * 
 */
package social.model;

import java.awt.Color;

import uchicago.src.sim.analysis.OpenSequenceGraph;
import uchicago.src.sim.engine.BasicAction;
import uchicago.src.sim.engine.SimInit;
import uchicago.src.sim.gui.ColorMap;
import uchicago.src.sim.gui.DisplaySurface;
import uchicago.src.sim.gui.Network2DGridDisplay;

/**
 * @author samer
 * 
 */
public class GraphicModel extends SpaceModel {

	private OpenSequenceGraph actualNumIndivsGraph;
	private OpenSequenceGraph attributesGraph;
	private OpenSequenceGraph religAttsGraph;
	private OpenSequenceGraph homosexAttsGraph;
	private OpenSequenceGraph prosAttsGraph;
	private OpenSequenceGraph abortAttsGraph;
	private OpenSequenceGraph divorAttsGraph;
	private OpenSequenceGraph euthaAttsGraph;
	private OpenSequenceGraph suiciAttsGraph;
	private DisplaySurface displaySurf;

	/**
	 * @param displaySurf
	 *            the displaySurf to set
	 */
	public void setDisplaySurf(DisplaySurface displaySurf) {
		this.displaySurf = displaySurf;
	}

	/**
	 * @return the displaySurf
	 */
	public DisplaySurface getDisplaySurf() {
		return displaySurf;
	}

	public void setup() {
		print("Running setup");
		initParams();
		initGraphs();
	}

	public void initGraphs() {
		disposeGraphs();
		createDisplays();
		registerDisplays();
	}

	/**
	 * 
	 */
	private void disposeGraphs() {
		// Tear down Displays
		if (getDisplaySurf() != null) {
			getDisplaySurf().dispose();
		}
		setDisplaySurf(null);

		// Dispose Graphs
		if (actualNumIndivsGraph != null) {
			actualNumIndivsGraph.dispose();
		}
		actualNumIndivsGraph = null;

		if (religAttsGraph != null) {
			religAttsGraph.dispose();
		}
		religAttsGraph = null;

		if (homosexAttsGraph != null) {
			homosexAttsGraph.dispose();
		}
		homosexAttsGraph = null;

		if (prosAttsGraph != null) {
			prosAttsGraph.dispose();
		}
		prosAttsGraph = null;

		if (abortAttsGraph != null) {
			abortAttsGraph.dispose();
		}
		abortAttsGraph = null;

		if (divorAttsGraph != null) {
			divorAttsGraph.dispose();
		}
		divorAttsGraph = null;

		if (euthaAttsGraph != null) {
			euthaAttsGraph.dispose();
		}
		euthaAttsGraph = null;

		if (suiciAttsGraph != null) {
			suiciAttsGraph.dispose();
		}
		suiciAttsGraph = null;

		if (attributesGraph != null) {
			attributesGraph.dispose();
		}
		attributesGraph = null;
	}

	/**
	 * 
	 */
	private void createDisplays() {
		// Create Displays
		setDisplaySurf(new DisplaySurface(this, "Mentat (beta)"));

		actualNumIndivsGraph = new OpenSequenceGraph(
				"Number of individuals in Space", this);

		attributesGraph = new OpenSequenceGraph("Main Attributes in Space",
				this);
		religAttsGraph = new OpenSequenceGraph(
				"Religious classification (% / Steps)", this);

		homosexAttsGraph = new OpenSequenceGraph(
				"Homosexual acceptance (% / Steps)", this);
		prosAttsGraph = new OpenSequenceGraph(
				"Prostitution acceptance (% / Steps)", this);
		abortAttsGraph = new OpenSequenceGraph("Abort acceptance (% / Steps)",
				this);
		divorAttsGraph = new OpenSequenceGraph(
				"Divorce acceptance (% / Steps)", this);
		euthaAttsGraph = new OpenSequenceGraph(
				"Euthanasia acceptance (% / Steps)", this);
		suiciAttsGraph = new OpenSequenceGraph(
				"Suicide acceptance (% / Steps)", this);
	}

	/**
	 * 
	 */
	private void registerDisplays() {
		// Register Displays
		registerDisplaySurface("Social Simulation BasicModel Window 1",
				getDisplaySurf());
		this.registerMediaProducer("Plot", actualNumIndivsGraph);
		this.registerMediaProducer("Plot", attributesGraph);
		this.registerMediaProducer("Plot", religAttsGraph);
		this.registerMediaProducer("Plot", homosexAttsGraph);
		this.registerMediaProducer("Plot", prosAttsGraph);
		this.registerMediaProducer("Plot", abortAttsGraph);
		this.registerMediaProducer("Plot", divorAttsGraph);
		this.registerMediaProducer("Plot", euthaAttsGraph);
		this.registerMediaProducer("Plot", suiciAttsGraph);
	}

	public void begin() {
		buildModel();
		buildSchedule();
		buildDisplay();

		// record data:
		updateStats();
		getStats().recordInitialYear();

		displaysAll();
	}

	private void displaysAll() {
		getDisplaySurf().display();
		actualNumIndivsGraph.display();
		attributesGraph.display();
		religAttsGraph.display();
		homosexAttsGraph.display();
		prosAttsGraph.display();
		abortAttsGraph.display();
		divorAttsGraph.display();
		euthaAttsGraph.display();
		suiciAttsGraph.display();
	}

	public void buildSchedule() {
		print("Running BuildSchedule");
		// BasicStep est‡ fuera
		schedule.scheduleActionBeginning(0, new BasicStep(this));

		class CountLiving extends BasicAction {
			public void execute() {
				countLivingAgents();
			}
		}

		schedule.scheduleActionAtInterval(10, new CountLiving());

		class UpdateNumIndivsInSpace extends BasicAction {
			public void execute() {
				actualNumIndivsGraph.step();
			}
		}
		schedule.scheduleActionAtInterval(UPDATE_GRAPHS_STEPS,
				new UpdateNumIndivsInSpace());

		class UpdateAttsInSpace extends BasicAction {
			public void execute() {
				attributesGraph.step();
			}
		}
		schedule.scheduleActionAtInterval(UPDATE_GRAPHS_STEPS,
				new UpdateAttsInSpace());

		class UpdateReligClasifInSpace extends BasicAction {
			public void execute() {
				religAttsGraph.step();
			}
		}
		schedule.scheduleActionAtInterval(UPDATE_GRAPHS_STEPS,
				new UpdateReligClasifInSpace());

		class UpdateHomosexAccInSpace extends BasicAction {
			public void execute() {
				homosexAttsGraph.step();
			}
		}
		schedule.scheduleActionAtInterval(UPDATE_GRAPHS_STEPS,
				new UpdateHomosexAccInSpace());

		class UpdateProsAccInSpace extends BasicAction {
			public void execute() {
				prosAttsGraph.step();
			}
		}
		schedule.scheduleActionAtInterval(UPDATE_GRAPHS_STEPS,
				new UpdateProsAccInSpace());

		class UpdateAbortAccInSpace extends BasicAction {
			public void execute() {
				abortAttsGraph.step();
			}
		}
		schedule.scheduleActionAtInterval(UPDATE_GRAPHS_STEPS,
				new UpdateAbortAccInSpace());

		class UpdateDivorAccInSpace extends BasicAction {
			public void execute() {
				divorAttsGraph.step();
			}
		}
		schedule.scheduleActionAtInterval(UPDATE_GRAPHS_STEPS,
				new UpdateDivorAccInSpace());

		class UpdateEuthaAccInSpace extends BasicAction {
			public void execute() {
				euthaAttsGraph.step();
			}
		}
		schedule.scheduleActionAtInterval(UPDATE_GRAPHS_STEPS,
				new UpdateEuthaAccInSpace());

		class UpdateSuiciAccInSpace extends BasicAction {
			public void execute() {
				suiciAttsGraph.step();
			}
		}
		schedule.scheduleActionAtInterval(UPDATE_GRAPHS_STEPS,
				new UpdateSuiciAccInSpace());

		schedule.scheduleActionAtEnd(this, "endSimulation");
	}

	public void buildDisplay() {
		print("Running BuildDisplay");

		ColorMap map = new ColorMap();

		for (int i = 1; i < 16; i++) {
			map.mapColor(i, new Color((int) (i * 8 + 127), 0, 0));
		}
		map.mapColor(0, Color.white);

		Network2DGridDisplay displayAgents = new Network2DGridDisplay(wSpace
				.getCurrentAgentSpace());
		displayAgents.setObjectList(getAgentList());

		getDisplaySurf().addDisplayableProbeable(displayAgents, "Agents");

		addMainAttsGraphSequences();
		addOtherAttsGraphSequences();
	}

	/**
	 * 
	 */
	private void addOtherAttsGraphSequences() {
		homosexAttsGraph.addSequence("Value 1", new homosexAcc1InSpace());
		homosexAttsGraph.addSequence("Value 2", new homosexAcc2InSpace());
		homosexAttsGraph.addSequence("Value 3", new homosexAcc3InSpace());
		homosexAttsGraph.addSequence("Value 4", new homosexAcc4InSpace());
		homosexAttsGraph.addSequence("Value 5", new homosexAcc5InSpace());
		homosexAttsGraph.addSequence("Value 6", new homosexAcc6InSpace());
		homosexAttsGraph.addSequence("Value 7", new homosexAcc7InSpace());
		homosexAttsGraph.addSequence("Value 8", new homosexAcc8InSpace());
		homosexAttsGraph.addSequence("Value 9", new homosexAcc9InSpace());
		homosexAttsGraph.addSequence("Value 10", new homosexAcc10InSpace());
		homosexAttsGraph.addSequence("Value 99", new homosexAcc99InSpace());

		prosAttsGraph.addSequence("Value 1", new prosAcc1InSpace());
		prosAttsGraph.addSequence("Value 2", new prosAcc2InSpace());
		prosAttsGraph.addSequence("Value 3", new prosAcc3InSpace());
		prosAttsGraph.addSequence("Value 4", new prosAcc4InSpace());
		prosAttsGraph.addSequence("Value 5", new prosAcc5InSpace());
		prosAttsGraph.addSequence("Value 6", new prosAcc6InSpace());
		prosAttsGraph.addSequence("Value 7", new prosAcc7InSpace());
		prosAttsGraph.addSequence("Value 8", new prosAcc8InSpace());
		prosAttsGraph.addSequence("Value 9", new prosAcc9InSpace());
		prosAttsGraph.addSequence("Value 10", new prosAcc10InSpace());
		prosAttsGraph.addSequence("Value 99", new prosAcc99InSpace());

		abortAttsGraph.addSequence("Value 1", new abortAcc1InSpace());
		abortAttsGraph.addSequence("Value 2", new abortAcc2InSpace());
		abortAttsGraph.addSequence("Value 3", new abortAcc3InSpace());
		abortAttsGraph.addSequence("Value 4", new abortAcc4InSpace());
		abortAttsGraph.addSequence("Value 5", new abortAcc5InSpace());
		abortAttsGraph.addSequence("Value 6", new abortAcc6InSpace());
		abortAttsGraph.addSequence("Value 7", new abortAcc7InSpace());
		abortAttsGraph.addSequence("Value 8", new abortAcc8InSpace());
		abortAttsGraph.addSequence("Value 9", new abortAcc9InSpace());
		abortAttsGraph.addSequence("Value 10", new abortAcc10InSpace());
		abortAttsGraph.addSequence("Value 99", new abortAcc99InSpace());

		divorAttsGraph.addSequence("Value 1", new divorAcc1InSpace());
		divorAttsGraph.addSequence("Value 2", new divorAcc2InSpace());
		divorAttsGraph.addSequence("Value 3", new divorAcc3InSpace());
		divorAttsGraph.addSequence("Value 4", new divorAcc4InSpace());
		divorAttsGraph.addSequence("Value 5", new divorAcc5InSpace());
		divorAttsGraph.addSequence("Value 6", new divorAcc6InSpace());
		divorAttsGraph.addSequence("Value 7", new divorAcc7InSpace());
		divorAttsGraph.addSequence("Value 8", new divorAcc8InSpace());
		divorAttsGraph.addSequence("Value 9", new divorAcc9InSpace());
		divorAttsGraph.addSequence("Value 10", new divorAcc10InSpace());
		divorAttsGraph.addSequence("Value 99", new divorAcc99InSpace());

		euthaAttsGraph.addSequence("Value 1", new euthaAcc1InSpace());
		euthaAttsGraph.addSequence("Value 2", new euthaAcc2InSpace());
		euthaAttsGraph.addSequence("Value 3", new euthaAcc3InSpace());
		euthaAttsGraph.addSequence("Value 4", new euthaAcc4InSpace());
		euthaAttsGraph.addSequence("Value 5", new euthaAcc5InSpace());
		euthaAttsGraph.addSequence("Value 6", new euthaAcc6InSpace());
		euthaAttsGraph.addSequence("Value 7", new euthaAcc7InSpace());
		euthaAttsGraph.addSequence("Value 8", new euthaAcc8InSpace());
		euthaAttsGraph.addSequence("Value 9", new euthaAcc9InSpace());
		euthaAttsGraph.addSequence("Value 10", new euthaAcc10InSpace());
		euthaAttsGraph.addSequence("Value 99", new euthaAcc99InSpace());

		suiciAttsGraph.addSequence("Value 1", new suiciAcc1InSpace());
		suiciAttsGraph.addSequence("Value 2", new suiciAcc2InSpace());
		suiciAttsGraph.addSequence("Value 3", new suiciAcc3InSpace());
		suiciAttsGraph.addSequence("Value 4", new suiciAcc4InSpace());
		suiciAttsGraph.addSequence("Value 5", new suiciAcc5InSpace());
		suiciAttsGraph.addSequence("Value 6", new suiciAcc6InSpace());
		suiciAttsGraph.addSequence("Value 7", new suiciAcc7InSpace());
		suiciAttsGraph.addSequence("Value 8", new suiciAcc8InSpace());
		suiciAttsGraph.addSequence("Value 9", new suiciAcc9InSpace());
		suiciAttsGraph.addSequence("Value 10", new suiciAcc10InSpace());
		suiciAttsGraph.addSequence("Value 99", new suiciAcc99InSpace());
		/*
		 * Aï¿½adir aquï¿½ mï¿½s muestreos a esta grï¿½fica (ideologï¿½a tiene
		 * otra escala y la coloco aparte)
		 */
	}

	/**
	 * 
	 */
	private void addMainAttsGraphSequences() {
		actualNumIndivsGraph.addSequence("People In Space",
				new numAgentsInSpace());

		attributesGraph.addSequence("Ideology In Space", new ideologyInSpace());
		attributesGraph.addSequence("Education In Space",
				new educationInSpace());
		attributesGraph.addSequence("Economy In Space", new economyInSpace());
		attributesGraph.addSequence("Religion In Space", new religionInSpace());

		religAttsGraph.addSequence("Ecclesiastical",
				new religClassifEcInSpace());
		religAttsGraph.addSequence("Low-intensity",
				new religClassifLowInSpace());
		religAttsGraph
				.addSequence("Alternatives", new religClassifAltInSpace());
		religAttsGraph.addSequence("Non-religious",
				new religClassifNonInSpace());
	}

	public static void main(String[] args) {
		
		int execs= 1;
		
		for (int i = 0; i < execs; i++) {
			SimInit init = new SimInit();
			GraphicModel model = new GraphicModel();
			// arg1 is the name of the parameter file
			init.loadModel(model, "", false);
		}
	}
}
