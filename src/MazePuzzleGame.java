import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class MazePuzzleGame implements Runnable {

	private static Thread mazeGameThread;
	private static Thread guiThread;
	
	private JFrame mainFrame;
	private MenuPanel menuPanel;
	private MazePanel mazePanel;
	private GameEngine gameEngine;
	private JPanel homeGlassPane;
	
	
	public MazePuzzleGame(GameEngine ge) throws IOException {
		this.gameEngine = ge;
		
		mainFrame = new JFrame("Maze Puzzle Game");
		mainFrame.setSize(1280, 720);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		menuPanel = new MenuPanel();
		menuPanel.setPreferredSize(new Dimension(200, 720));
	}
	
	public static void main(String[] args) throws IOException {
		
		mazeGameThread = new Thread(new GameEngine());
		
		guiThread = new Thread(new MazePuzzleGame(new GameEngine()));
		
		mazeGameThread.start();
		guiThread.start();
	}

	@Override
	public void run() {
		mainFrame.getContentPane().add(menuPanel, BorderLayout.EAST);
		//mainFrame.getContentPane().add(mazePanel, BorderLayout.WEST);
		mainFrame.setResizable(false);
		mainFrame.pack();
		mainFrame.setVisible(true);
		
		while (true) {
			/*if (!gameEngine.isInGame() && mainFrame.isVisible()) {
				simulationPanel.startSimulationGame();
			}*/
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
		}
		
	}
}
