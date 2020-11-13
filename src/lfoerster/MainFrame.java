package lfoerster;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	public MainFrame() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("A* - Pathfinding");
		setResizable(false);
		setVisible(true);
		
		boolean[][] b = { 
				{ true, false, true, true, true, true }, 
				{ true, false, true, true, true, true },
				{ true, false, true, true, true, true }, 
				{ true, false, true, true, true, true },
				{ true, false, true, true, true, true }, 
				{ true, true, true, true, false, true } };

		Grid field = new Grid(b);
		AStar astar = new AStar(field);
		List<Node> pathList = astar.getList();
		
		for(Node node : pathList) {
			node.setBackground(Node.pink);
		}
		
		add(field, BorderLayout.CENTER);
		pack();
	}

	public static void main(String[] args) {
		new MainFrame();
	}
}
