package social.world;

import java.awt.Color;

import uchicago.src.sim.gui.DrawableEdge;
import uchicago.src.sim.gui.SimGraphics;
import uchicago.src.sim.network.DefaultEdge;
import uchicago.src.sim.network.Node;

/**
 * Bond can draw itself and keep track of the nodes on its ends.
 * 
 * @author Nick Collier
 */
public class Bond extends DefaultEdge implements DrawableEdge {

	private Color defaultColor = Color.GREEN;

	private Color color;

	public Bond(Node from, Node to, Color color, String type) {
		super(from, to, "");
		this.color = color;
		this.setType(type);
	}

	public Bond(Node from, Node to, String type) {
		super(from, to, "");
		this.color = defaultColor;
		this.setType(type);
	}

	public void setColor(Color c) {
		color = c;
	}

	public void draw(SimGraphics g, int fromX, int toX, int fromY, int toY) {
		g.drawDirectedLink(color, fromX, toX, fromY, toY);
	}
}
