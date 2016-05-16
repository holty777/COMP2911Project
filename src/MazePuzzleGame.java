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
		mainFrame.setSize(1280, 720);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		menuPanel = new MenuPanel(this);
		menuPanel.setPreferredSize(new Dimension(200, 720));
		
		gamePanel = new GameBoardPanel(null, this);
		gamePanel.setPreferredSize(new Dimension(750, 700));

		homeGlassPane = new HomePanel();
		homeGlassPane.setPreferredSize(new Dimension(750, 700));
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
	
	/**
	 * Change the glass panel on home screen.
	 * @param mode 0 for logo panel, 1 for single player menu
	 * 2 for double player menu, 3 for credit page, 
	 * 4 for how to play page
	 */
	public void changeGlassPane(int mode) {
		switch (mode) {
		case 0:
			// logo
			homeGlassPane = new HomePanel();
			break;
		case 1:
			// single player menu
			if (homeGlassPane instanceof SinglePlayerMenu)
				homeGlassPane = new HomePanel();
			else
				homeGlassPane = new SinglePlayerMenu(this);
			break;
		case 2:
			// double players menu
			if (homeGlassPane instanceof DoublePlayersMenu)
				homeGlassPane = new HomePanel();
			else
				homeGlassPane = new DoublePlayersMenu(this);
			break;
		case 3:
			// credits page
			if (homeGlassPane instanceof Credits)
				homeGlassPane = new HomePanel();
			else
				homeGlassPane = new Credits();
			break;
		case 4:
			// how to play page
			if (homeGlassPane instanceof Instructions)
				homeGlassPane = new HomePanel();
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
}
