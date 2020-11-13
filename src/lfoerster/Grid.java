package lfoerster;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import lfoerster.Node.NodeTypes;

@SuppressWarnings("serial")
class Grid extends JPanel {
	private Node[][] nodes;
	private Node startNode;
	private Node endNode;
	private int gridSizeX;
	private int gridSizeY;

	public Grid(boolean[][] cells) {
		this(createNodeArray(cells));
	}

	public Grid(Node[][] nodes) {
		this.gridSizeX = nodes.length;
		this.gridSizeY = nodes[0].length;
		this.nodes = nodes;
		this.startNode = setStartNode(0, 0);
		this.endNode = setEndNode(0, 4);

		setLayout(new GridLayout(gridSizeX, gridSizeY));
		for (int x = 0; x < gridSizeX; x++) {
			for (int y = 0; y < gridSizeY; y++) {
				Node cell = nodes[x][y];
				cell.setPreferredSize(new Dimension(50, 50));
				add(cell);
			}
		}
		
		
	}

	public Node getNode(int x, int y) {
		return nodes[x][y];
	}

	public void setNode(Node node, int x, int y) {
		nodes[x][y] = node;
	}

	private static Node[][] createNodeArray(boolean[][] grid) {
		Node[][] nodes = new Node[grid.length][grid[0].length];
		for (int x = 0; x < grid.length; x++) {
			for (int y = 0; y < grid[x].length; y++) {
				if (grid[x][y] == true) {
					nodes[x][y] = new Node(grid[x][y], NodeTypes.WALKABLE, x, y);
				} else if (grid[x][y] == false) {
					nodes[x][y] = new Node(grid[x][y], NodeTypes.UNWALKABLE, x, y);
				}
			}
		}
		return nodes;
	}
	
	public List<Node> getNeighbours(Node node){
		List<Node> neighbours = new ArrayList<>();
		for(int x = -1; x <= 1; x++) {
			for(int y = -1; y <= 1; y++) {
				if((x==0 && y==0) || isCornerBlockCheck(x, y)) {
					continue;
				}
				int checkX = node.getNodeX() + x;
				int checkY = node.getNodeY() + y;
				if(checkX>=0 && checkX<gridSizeX && checkY>=0 && checkY<gridSizeY) {
                    neighbours.add(nodes[checkX][checkY]);
                }
			}
		}
		return neighbours;
	}

	private boolean isCornerBlockCheck(int x, int y) {
		if(Math.abs(x)==0 && Math.abs(y)==0) { //TODO mögliche Fehlerquelle weil vllt 0 sein sollte
			return true;
		}
		return false;
	}

	public Node getStartNode() {
		return startNode;
	}

	public Node setStartNode(int x, int y) {
		return nodes[x][y] = new Node(true, NodeTypes.START, x, y);
	}

	public Node getEndNode() {
		return endNode;
	}

	public Node setEndNode(int x, int y) {
		return nodes[x][y] = new Node(true, NodeTypes.END, x, y);
	}
	
}