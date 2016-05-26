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
	private MazePuzzleGame mainGame;

	/**
	 * The "GameWindow" class.
	 * @param mg	The MazePuzzelGame, a class containing information
	 * 				about the display of the maze.
	 * @param title
	 * @throws IOException 
	 */
	public GameWindow(MazePuzzleGame mg, String title) throws IOException {
		super(title);
		mainGame = mg;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 700);
		setLocationRelativeTo(null);
		setResizable(false);
		setBounds(mainGame.getMainFrame().getBounds());

		gameStatisticsPanel = new GameStatisticsPanel(this, mainGame);
		gameStatisticsPanel.setPreferredSize(new Dimension(300, 700));
		getContentPane().add(gameStatisticsPanel, BorderLayout.WEST);

		gameBoardPanel = new GameBoardPanel(this, mainGame);
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
