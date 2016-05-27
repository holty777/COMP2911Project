import java.awt.BorderLayout;
import javax.swing.JPanel;

/**
 * The "GameBoardPanel" class handles all operations 
 * regarding the displaying of the game 
 * including the statistics panel.
 * @author  Jack Holt
 * 			Jesse Moses
 * 			Nick Balnaves
 * 			Jordan Jacobson
 * 			Shiyuan Liang
 *
 */
public class GameBoardPanel extends JPanel implements Runnable {

	private static final long serialVersionUID = 1L;

	private GameWindow gameWindow;
	private MazePanel mainMaze;

	// game data
	private Player player1;
	private Player player2;
	private int gameMode; // 1 for single player, 2 for double player
	private int enemySpeed;
	private int gameStartCheck;
	private int singleDouble;

	/**
	 * The constructor for the "GameBoardPanel" class.
	 * @param gameWindow	The whole game screen
	 * @param mainGame		The main interface to the game
	 */
	public GameBoardPanel(GameWindow gameWindow) {
		this.gameWindow = gameWindow;
		gameStartCheck = 0;
		singleDouble = 1;

		setLayout(new BorderLayout());

	}
	
	/**
	 * Update the statistics panel with the current information.
	 */
	public void updateStatisticsPanel() {
		if (gameWindow != null && gameWindow.getStatisticsPanel() != null)
			gameWindow.getStatisticsPanel().setWhosTurn();
	}

	/**
	 * If the game has ended, display the winner.
	 * @param string 
	 * @param winner The player who has won.
	 */
	public void displayEndGame() {
		int i = 1;
		gameWindow.getStatisticsPanel().displayEndGame(i);
	}

	/**
	 * Create and randomly generate the data to create a new double player game.
	 */
	public void restartNewGame() {

		if (gameMode != 0) {
			gameWindow.getStatisticsPanel().setPlayerNames(player1.getName(), player2.getName());
		}

		startNewGame();
		updateStatisticsPanel();
	}

	/**
	 * Create and randomly generate the data to create a new double player game.
	 */
	public void pauseGame() {
	}
	/**
	 * Start the new game.
	 */
	public void startNewGame() {
		setMaze(gameMode, singleDouble);
		getMaze().setFocusable(true);
		getMaze().requestFocus();
		getMaze().addKeyListener(getMaze());

	}

	/**
	 * Create a new single player game and determine the difficulty.
	 * @param playerName	The player's name.
	 * @param enemySpeed 
	 * @param i 
	 * @param AIMode	The difficulty of the game.
	 */
	public void initSinglePlayerGame(String playerName, int mazeSize, int enemySpeed, int i) {
		if (playerName == null || playerName.equals(""))
			playerName = "YOU";
		gameMode = mazeSize;
		this.enemySpeed = enemySpeed;
		gameWindow.getStatisticsPanel().setPlayerNames(playerName, "AI");
		gameStartCheck = i;
		if(gameStartCheck == 1)
			startNewGame();
		updateStatisticsPanel();
	}

	/**
	 * Initiate a double player game.
	 * @param name1	Name of player 1.
	 * @param name2 Name of player 2.
	 */
	public void initDoublePlayersGame(String name1, String name2, int mazeSize) {
		// set default name if input is empty
		if (name1 == null || name1.equals("")) {
			name1 = "Player 1";
		}
		if (name2 == null || name2.equals("")) {
			name2 = "Player 2";
		}
		gameMode = mazeSize;

		gameMode = 2;
		gameWindow.getStatisticsPanel().setPlayerNames(name1, name2);
		singleDouble = 2;
		startNewGame();
		updateStatisticsPanel();
	}

	/**
	 * Return the maze.
	 * @return The maze.
	 */
	public MazePanel getMaze(){
		return this.mainMaze;
	}

	/**
	 * Determine the size of the maze.
	 * @param gameMode The difficulty of the maze. Number between 0-2.
	 */
	public void setMaze(int mazeSize, int singleDouble){
		if(singleDouble == 1){
			mainMaze = new MazePanel(mazeSize,mazeSize,this, enemySpeed);
		} else {
			mainMaze = new MazePanel(30,30,this, 0);
		}
		this.add(mainMaze, BorderLayout.CENTER);
	}

	public GameWindow getGameWindow(){
		return gameWindow;
	}
	@Override
	public void run() {
		
	}


}
