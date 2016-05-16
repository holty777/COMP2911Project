import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class MazePuzzleGame implements Runnable {

	private static Thread MG;
	private static Thread GUI;
	
	private JFrame mainFrame;
	private MenuPanel menuPanel;
	private GameEngine gameEngine;
	private JPanel homeGlassPane;
	
	//maybe change this to gamePanel
	private MainWindow mazeWindow;
	//maybe change to gameEngine
	private Maze maze;
	
	public MazePuzzleGame(GameEngine ge) throws IOException {
		this.gameEngine = ge;
		
		mainFrame = new JFrame("Maze Puzzle Game");
		mainFrame.setSize(1280, 720);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//need to use setPreferredSize for these
		menuPanel = new MenuPanel();
		menuPanel.setPreferredSize(new Dimension(250, 700));
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
		if (homeGlassPane != null) {
			mainFrame.setGlassPane(homeGlassPane);
			homeGlassPane.setOpaque(false);
			homeGlassPane.setVisible(true);
		}
		mainFrame.getContentPane().add(menuPanel, BorderLayout.EAST);
		//mainFrame.getContentPane().add(simulationPanel, BorderLayout.WEST);
		mainFrame.setResizable(false);
		mainFrame.pack();
		mainFrame.setVisible(true);
		
		while (true) {
			// start new simulation if game is not on
			/*if (!gameEngine.isInGame() && mainFrame.isVisible()) {
				simulationPanel.startSimulationGame();
			}*/
			// delay waiting
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
		}
		
	}
}
