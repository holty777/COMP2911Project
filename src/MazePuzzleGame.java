import java.io.IOException;

import javax.swing.JFrame;


public class MazePuzzleGame {

	private static Thread MG;
	private static Thread GUI;
	
	private JFrame mainFrame;
	private MenuPanel menuPanel;
	
	//maybe change this to gamePanel
	private MainWindow mazeWindow;
	private Maze maze;
	
	public MazePuzzleGame(Maze maze) throws IOException {
		this.maze = maze;
		
		mainFrame = new JFrame("Maze Puzzle Game");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//need to use setPreferredSize for these
		menuPanel = new MenuPanel(this);
		//mazeWindow = new MainWindow();
		maze = new Maze();
		
	}
	
	public static void main(String[] args) throws IOException {
		Runnable mainMaze = new Maze();
		
		//maze generation thread
		MG = new Thread(mainMaze);
		
		//GUI thread
		GUI = new Thread(new MainWindow(mainMaze));
		
		MG.start();
		GUI.start();
	}
}
