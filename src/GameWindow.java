import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;
import javax.swing.JFrame;

/**
 * The "GameWindow" class extend JFram and 
 * handles the statistics panel  and the game board classes.
 * @author  Jack Holt
 * 			Jesse Moses
 * 			Nick Balnaves
 * 			Jordan Jacobson
 * 			Shiyuan Liang 
 *
 */
public class GameWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	private GameBoardPanel gameBoardPanel;
	private GameStatisticsPanel gameStatisticsPanel;

	/**
	 * The "GameWindow" Constructor.
	 * @param mg	The MazePuzzelGame, a class containing information
	 * 				about the display of the maze.
	 * @param title	The type of game. Either:
	 * 				- "Double Players Game"
	 * 				- "Single Player Game"
	 * @throws IOException 
	 */
	public GameWindow(MazePuzzleGame mg, String title) throws IOException {
		super(title);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setLocationRelativeTo(null);
		setResizable(false);
		setBounds(mg.getMainFrame().getBounds());

		gameStatisticsPanel = new GameStatisticsPanel(this);

		getContentPane().add(gameStatisticsPanel, BorderLayout.WEST);

		gameBoardPanel = new GameBoardPanel(this);
		gameBoardPanel.setPreferredSize(new Dimension(700, 700));
		getContentPane().add(gameBoardPanel, BorderLayout.EAST);

		pack();
	}

	/**
	 * Get the GameStatisticsPanel.
	 * @return the GameStatisticsPanel.
	 */
	public GameStatisticsPanel getStatisticsPanel() {
		return gameStatisticsPanel;
	}

	/**
	 * Get the GameBoardPanel.
	 * @return the GameBoardPanel.
	 */
	public GameBoardPanel getGameBoardPanel() {
		return gameBoardPanel;
	}

	/**
	 * Start a single player game.
	 * @param playerName	The name of the player.
	 * @param enemySpeed 
	 * @param i 
	 * @param modeAI	The mode being played.
	 */
	public void startSinglePlayerGame(String playerName, int mazeSize, int enemySpeed, int i) {
		gameBoardPanel.initSinglePlayerGame(playerName, mazeSize, enemySpeed, i);
	}

	/**
	 * Start a double player game.
	 * @param playerName	The name of player 1.
	 * @param playerName2	The name of player 2.
	 */
	public void startDoublePlayersGame(String playerName, String playerName2, int mazeSize) {
		gameBoardPanel.initDoublePlayersGame(playerName, playerName2, mazeSize);
	}

}
