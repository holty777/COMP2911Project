import java.io.IOException;

import javax.swing.JFrame;


public class MazePuzzleGame implements Runnable {

	private static Thread MG;
	private static Thread GUI;
	
	private JFrame mainFrame;
	private MenuPanel menuPanel;
	private GameEngine gameEngine;
	
	//maybe change this to gamePanel
	private MainWindow mazeWindow;
	//maybe change to gameEngine
	private Maze maze;
	
	public MazePuzzleGame(GameEngine ge) throws IOException {
		this.gameEngine = ge;
		
		mainFrame = new JFrame("Maze Puzzle Game");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//need to use setPreferredSize for these
		menuPanel = new MenuPanel(this);
		//mazeWindow = new MainWindow();
		maze = new Maze();
		
	}
	
	public static void main(String[] args) throws IOException {
		
		//maze generation thread
		MG = new Thread(new GameEngine());
		
		//GUI thread
		GUI = new Thread(new MazePuzzleGame(new GameEngine()));
		
		MG.start();
		GUI.start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
