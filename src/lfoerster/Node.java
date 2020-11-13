package lfoerster;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class Node extends JPanel {

	private static final Color black = new Color(0, 0, 0);
	private static final Color white = new Color(255, 255, 255);
	private static final Color gray = new Color(150, 150, 150);
	private static final Color green = new Color(50, 205, 50);
	private static final Color red = new Color(238, 44, 44);
	public static final Color pink = new Color(199, 21, 133);

	private boolean walkable = false;
	private Node parent;
	private NodeTypes nodeType;
	
	private int gCost;
	private int hCost;
	private int nodeX;
	private int nodeY;

	public Node(boolean walkable, NodeTypes nodeType, int nodeX, int nodeY) {
		setCellBackground(walkable, nodeType);
		setBorder(new LineBorder(gray));
		
		this.walkable = walkable;
		this.nodeX = nodeX;
		this.nodeY = nodeY;
		this.nodeType = nodeType;
	}

	public boolean isWalkable() {
		return walkable;
	}

	public void setCellBackground(boolean walkable, NodeTypes nodeType) {
		this.walkable = walkable;
		if (NodeTypes.WALKABLE == nodeType) {
			setBackground(white);
		} else if (NodeTypes.UNWALKABLE == nodeType) {
			setBackground(black);
		} else if (NodeTypes.START == nodeType) {
			setBackground(green);
		} else if (NodeTypes.END == nodeType) {
			setBackground(red);
		} else if (NodeTypes.FINAL_PATH == nodeType) {
			setBackground(pink);
		}
	}

	public int getgCost() {
		return gCost;
	}

	public void setgCost(int gCost) {
		this.gCost = gCost;
	}

	public int gethCost() {
		return hCost;
	}

	public void sethCost(int hCost) {
		this.hCost = hCost;
	}
	
	public int fCost() {
		return gCost+hCost;
	}
	
	public Node getParent() {
		return parent;
	}
	
	public void setParent(Node parent) {
		this.parent = parent;
	}

	public int getNodeX() {
		return nodeX;
	}

	public int getNodeY() {
		return nodeY;
	}

	public NodeTypes getNodeType() {
		return nodeType;
	}

	public enum NodeTypes {
		START, END, WALKABLE, UNWALKABLE, FINAL_PATH;
	}

}
