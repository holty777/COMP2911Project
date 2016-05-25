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
	private GameBoardPanel gamePanel;
	private GameEngine gameEngine;
	private JPanel homeGlassPane;
	
	public MazePuzzleGame(GameEngine ge) throws IOException {
		this.gameEngine = ge;
		
		mainFrame = new JFrame("Maze Puzzle Game");
		mainFrame.setSize(1000, 700);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		menuPanel = new MenuPanel(this);
		menuPanel.setPreferredSize(new Dimension(300, 700));

		homeGlassPane = new JPanel();
		homeGlassPane.setPreferredSize(new Dimension(700, 700));
		
		gamePanel = new GameBoardPanel(null, this);
		gamePanel.setPreferredSize(new Dimension(700, 700));
	}
	
	public static void main(String[] args) throws IOException {
		
		mazeGameThread = new Thread(new GameEngine());
		
		guiThread = new Thread(new MazePuzzleGame(new GameEngine()));
		
		mazeGameThread.start();
		guiThread.start();
	}
	
	private void display() {
		if (homeGlassPane != null) {
			mainFrame.setGlassPane(homeGlassPane);
			homeGlassPane.setOpaque(false);
			homeGlassPane.setVisible(true);
		}
		mainFrame.getContentPane().add(menuPanel, BorderLayout.WEST);
		mainFrame.getContentPane().add(gamePanel, BorderLayout.EAST);
		mainFrame.setResizable(false);
		mainFrame.pack();
		mainFrame.setVisible(true);
	}

	public void setVisibility(boolean visible) {
		mainFrame.setVisible(visible);
	}

	public GameEngine getGameEngine() {
		return gameEngine;
	}

	public MenuPanel getMenuPanel() {
		return menuPanel;
	}

	public JFrame getMainFrame() {
		return mainFrame;
	}

	public void suspendGame() {
		gameEngine.suspendGame();
		guiThread.interrupt();
	}
	
	public void changeGlassPane(int mode) {
		switch (mode) {
		case 0:
			// logo
			homeGlassPane.repaint();
			break;
		case 1:
			// single player menu
			if (homeGlassPane instanceof SinglePlayerMenu)
				homeGlassPane.repaint();
			else
				homeGlassPane = new SinglePlayerMenu(this);
			break;
		case 2:
			// double players menu
			if (homeGlassPane instanceof DoublePlayersMenu)
				homeGlassPane.repaint();
			else
				homeGlassPane = new DoublePlayersMenu(this);
			break;
		case 4:
			// how to play page
			if (homeGlassPane instanceof Instructions)
				homeGlassPane.repaint();
			else
				homeGlassPane = new Instructions();
			break;
		default:
			// blank
			homeGlassPane = new JPanel();
		}
		display();
	}
	
	@Override
	public void run() {
		display();
		
		while (true) {
			if (!gameEngine.isInGame() && mainFrame.isVisible()) {
				gamePanel.startSimulationGame();
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
		}
		
	}
	//public getGamePanel
}
