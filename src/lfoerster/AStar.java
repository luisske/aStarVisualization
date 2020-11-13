package lfoerster;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AStar {

	private Grid grid;
	private List<Node> pathList;
	
	public AStar(Grid grid) {
		this.grid = grid;
		this.pathList = createPathList();
	}

	private List<Node> createPathList() {
		Node startNode = grid.getStartNode();
		Node endNode = grid.getEndNode();
		return findPath(startNode, endNode);
	}

	private List<Node> findPath(Node startNode, Node endNode) {
		List<Node> openList = new ArrayList<>();
		Set<Node> closedSet = new HashSet<>();
		
		openList.add(startNode);
		
		while (!openList.isEmpty()) {
			Node currentNode = openList.get(0);
			for (int i = 1; i < openList.size(); i++) {
				if(openList.get(i).fCost() < currentNode.fCost() || openList.get(i).fCost() == currentNode.fCost()) {
					if(openList.get(i).gethCost() < currentNode.gethCost()) {
						currentNode = openList.get(i);
					}
				}
			}
			openList.remove(currentNode);
			closedSet.add(currentNode);
			
			if(currentNode.equals(endNode)) {
				return retracePath(startNode, endNode);
			}
			
			for (Node neighbour : grid.getNeighbours(currentNode)) {
				if(!neighbour.isWalkable() || closedSet.contains(neighbour)) {
					continue;
				}
				int newMovementCostToNeighbour = currentNode.getgCost() + getDistanceBetweenTwoNodes(currentNode, neighbour);
				
				if(newMovementCostToNeighbour < neighbour.getgCost() || !openList.contains(neighbour)) {
					neighbour.setgCost(newMovementCostToNeighbour);
					neighbour.sethCost(getDistanceBetweenTwoNodes(neighbour, endNode));
					neighbour.setParent(currentNode);
					
					if(!openList.contains(neighbour)) {
						openList.add(neighbour);
					}
				}
			}
		}
		return null;
	}
	
	private int getDistanceBetweenTwoNodes(Node nodeA, Node nodeB) {
		int result = 0;
		int diagonalCost = 14;
		int straightCost = 10;
		int distanceX = Math.abs(nodeA.getNodeX() - nodeB.getNodeX());
		int distanceZ = Math.abs(nodeA.getNodeY() - nodeB.getNodeY());
		if (distanceX > distanceZ) {
			result = diagonalCost * distanceZ + straightCost * (distanceX - distanceZ);
		} else {
			result = diagonalCost * distanceX + straightCost * (distanceZ - distanceX);
		}
		return result;
	}
	
	private List<Node> retracePath(Node startNode, Node endNode){
		List<Node> path = new ArrayList<>();
		Node currentNode = endNode;
		int pathLength = 0;
		while(!currentNode.equals(startNode)) {
			path.add(currentNode);
			pathLength++;
			currentNode = currentNode.getParent();
		}
		Collections.reverse(path);
		path.remove(pathLength-1);
		return path;
	}
	
	public List<Node> getList(){
		return pathList;
	}
	
}
