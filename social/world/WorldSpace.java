package social.world;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Vector;

import social.indiv.AttributedIndiv;
import social.indiv.DemographicIndiv;
import social.indiv.SimpleIndiv;
import uchicago.src.sim.space.Object2DGrid;
import uchicago.src.sim.util.Random;

public class WorldSpace {

	private static final double STND_DEV_POSITION_CHILD = 2.0;

	private static final int MAX_COUNT = 20;

	// private static final int NEIGHB_X_EXTENS = 6;

	// private static final int NEIGHB_Y_EXTENS = NEIGHB_X_EXTENS;

	private int configNeighbRadius;

	private Object2DGrid agentSpace;

	// private Object2DTorus agentSpace;

	public WorldSpace(int neighb_extens, int xSize, int ySize) {

		agentSpace = new Object2DGrid(xSize, ySize);
		// agentSpace = new Object2DTorus(xSize, ySize);

		configNeighbRadius = neighb_extens;
	}

	public Object2DGrid getCurrentAgentSpace() {
		return agentSpace;
	}

	public boolean isPointOccupied(int x, int y) {
		boolean retVal = false;
		if (agentSpace.getObjectAt(x, y) != null)
			retVal = true;

		return retVal;
	}

	public boolean addAgent(AttributedIndiv agent) {
		boolean retVal = false;
		int count = 0;
		int countLimit = 10 * agentSpace.getSize().height
				* agentSpace.getSize().width;

		while ((retVal == false) && (count < countLimit)) {
			int x = (int) (Math.random() * (agentSpace.getSize().width));
			int y = (int) (Math.random() * (agentSpace.getSize().height));
			if (isPointOccupied(x, y) == false) {
				agentSpace.putObjectAt(x, y, agent);
				agent.setXY(x, y);
				agent.setWSpace(this);
				retVal = true;
			}
			count++;
		}

		return retVal;
	}

	public void addCloseAgent(AttributedIndiv child, AttributedIndiv mother) {
		boolean retVal = false;
		int count = 0;
		int countLimit = MAX_COUNT;

		while ((retVal == false) && (count < countLimit)) {
			Point p = generateClosePosition(mother.getX(), mother.getY());
			int x = p.x;
			int y = p.y;

			if (isPointOccupied(x, y) == false) {
				agentSpace.putObjectAt(x, y, child);
				child.setXY(x, y);
				child.setWSpace(this);
				retVal = true;
			}
			count++;
		}

		if (count >= countLimit) {
			Point p = generateClosePosition(mother.getX(), mother.getY());
			int x = p.x;
			int y = p.y;
			agentSpace.putObjectAt(x, y, child);
			child.setXY(x, y);
			child.setWSpace(this);
		}

	}

	private Point generateClosePosition(double motherX, double motherY) {
		double sdPosition = STND_DEV_POSITION_CHILD;

		Random.createNormal(motherX, sdPosition);
		int childX = (int) Random.normal.nextDouble(motherX, sdPosition);

		Random.createNormal(motherY, sdPosition);
		int childY = (int) Random.normal.nextDouble(motherY, sdPosition);

		if (childX >= agentSpace.getSize().width)
			childX = agentSpace.getSize().width - 1;

		if (childY >= agentSpace.getSize().height)
			childY = agentSpace.getSize().height - 1;

		if (childX < 0)
			childX = -childX;

		if (childY < 0)
			childY = -childY;

		Point p = new Point(childX, childY);
		return p;
	}

	public AttributedIndiv getAgentAt(int x, int y) {
		AttributedIndiv retVal = null;
		if (agentSpace.getObjectAt(x, y) != null) {
			retVal = (AttributedIndiv) agentSpace.getObjectAt(x, y);
		}
		return retVal;
	}

	public void removeAgentAt(int x, int y) {
		agentSpace.putObjectAt(x, y, null);
	}

	/**
	 * Gets the von Neumann neighbors (shape of a diamond) of the object at x,
	 * y. Objects are returned in west, east, north, south order. The object at
	 * x, y is not returned. Default distance = 1. *
	 * .............................................
	 * ......................X......................
	 * .....................XOX.....................
	 * ......................X......................
	 * .............................................
	 * 
	 * @param ind
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<AttributedIndiv> diamondNeighbors(AttributedIndiv ind) {
		if (ind == null)
			return null;
		Vector<AttributedIndiv> vec = agentSpace.getVonNeumannNeighbors(
				(int) ind.getX(), (int) ind.getY(), false);
		ArrayList<AttributedIndiv> array = new ArrayList<AttributedIndiv>(vec);
		return array;
	}

	/**
	 * Gets the Moore neighbors (shape of a box) of the object at x, y. Objects
	 * are returned by row starting with the "North-West corner" and ending with
	 * the "South-East corner." The Object at (x, y) is not returned. Default
	 * distance = 1.
	 * 
	 * .............................................
	 * .....................XXX.....................
	 * .....................XOX.....................
	 * .....................XXX.....................
	 * .............................................
	 * 
	 * @param ind
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<DemographicIndiv> boxNeighbors(DemographicIndiv ind) {
		if (ind == null)
			return null;
		int xExtension = configNeighbRadius;
		int yExtension = configNeighbRadius;
		Vector<DemographicIndiv> vec = agentSpace.getMooreNeighbors((int) ind
				.getX(), (int) ind.getY(), xExtension, yExtension, false);
		;
		ArrayList<DemographicIndiv> array = new ArrayList<DemographicIndiv>(vec);
		return array;
	}

	public double distance(SimpleIndiv ind1, SimpleIndiv ind2) {
		return Point2D.distance(ind1.getX(), ind1.getY(), ind2.getX(), ind2
				.getY());
	}
}
