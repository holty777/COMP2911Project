import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
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
public class GameBoardPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private GameWindow gameWindow;
	private MazePuzzleGame mainGame;
	private GameEngine gameEngine;
	private MazePanel mainMaze;

	// game data
	private Player player1;
	private Player player2;
	private int gameMode; // 0 for simulation, 1 for single player, 2 for double player
	private JPanel notification;
	/**
	 * The constructor for the "GameBoardPanel" class.
	 * @param gameWindow	
	 * @param mainGame
	 * @param homeGlassPane 
	 */
	public GameBoardPanel(GameWindow gameWindow, MazePuzzleGame mainGame) {
		this.gameWindow = gameWindow;
		this.mainGame = mainGame;
		gameEngine = mainGame.getGameEngine();

		//setOpaque(true);
		
		//setBackground(new Color(27, 120, 236));
		
		ImageIcon image = new ImageIcon("src/hedge.png");
		JLabel imagelabel = new JLabel(image);
		imagelabel.setOpaque(true);
		imagelabel.setMinimumSize(new Dimension(700,700));
		imagelabel.setPreferredSize(new Dimension(700,700));
		imagelabel.setMaximumSize(new Dimension(700,700));
		
		//imagelabel.pac
		
		//Image image = GenerateImage.toImage(true);  //this generates an image file
		//ImageIcon icon = new ImageIcon(image); 
		JLabel thumb = new JLabel();
		System.out.println(imagelabel);
		thumb.setIcon(image);
		//setLayout(new GridLayout(6, 7));
		
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
	 * @param winner The player who has won.
	 */
	public void displayEndGame(Player winner) {
		//if (gameMode == 0)
			//return; // stimulation
		gameWindow.getStatisticsPanel().displayEndGame(winner);
	}

	/**
	 * Create and randomly generate the data to create a new double player game.
	 */
	public void restartNewGame() {
		mainGame.suspendGame();

		// randomize first player for single player mode
		if (gameMode != 0) {
			if (randPlayer() == 0) {
				Player temp = player1;
				player1 = player2;
				player2 = temp;
			}
			gameWindow.getStatisticsPanel().setPlayerNames(player1.getName(), player2.getName());
		}
		
		startNewGame();
		updateStatisticsPanel();
	}

	/**
	 * Start the new game.
	 */
	public void startNewGame() {

		gameEngine.startNewGame(gameMode, player1, player2, this);

	}

	/**
	 * Testing module.
	 */
	public void startSimulationGame() {
		gameMode = 0;
		startNewGame();
	}

	/**
	 * Create a new single player game and determine the difficulty.
	 * @param playerName	The player's name.
	 * @param AIMode	The difficulty of the game.
	 */
	public void initSinglePlayerGame(String playerName, int AIMode) {
		if (playerName == null || playerName.equals(""))
			playerName = "YOU";

		String nameAI = "AI";
		switch (AIMode) {
		case 0:
			gameMode = 0;
			break;
		case 1:
			gameMode = 1;
			break;
		case 2:
			gameMode = 2;
		}

		/*if (randPlayer() == 0) {
			player1 = new User(playerName);
			player2 = new AI(nameAI, AIMode);
		} else {
			player1 = new AI(nameAI, AIMode);
			player2 = new User(playerName);
		}*/
		//gameMode = 1;
		gameWindow.getStatisticsPanel().setPlayerNames(playerName, "AI");
		startNewGame();
		updateStatisticsPanel();
	}

	/**
	 * Initiate a double player game.
	 * @param name1	Name of player 1.
	 * @param name2 Name of player 2.
	 */
	public void initDoublePlayersGame(String name1, String name2) {
		// set default name if input is empty
		if (name1 == null || name1.equals("")) {
			name1 = "Player 1";
		}
		if (name2 == null || name2.equals("")) {
			name2 = "Player 2";
		}

		// randomize user play order
		/*if (randPlayer() == 0) {
			player1 = new User(name1);
			player2 = new User(name2);
		} else {
			player1 = new User(name2);
			player2 = new User(name1);
		}*/

		gameMode = 2;
		gameWindow.getStatisticsPanel().setPlayerNames(name1, name2);
		startNewGame();
		updateStatisticsPanel();
	}

	/**
	 * Generate a random number to determine which player moves first.
	 * @return	A random number, either 0, 1.
	 */
	private int randPlayer() {
		Random rand = new Random();
		return rand.nextInt(2);
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
	public void setMaze(int gameMode){
		if(gameMode == 0){
			mainMaze = new MazePanel(20,20,35,notification, this);
		} else if(gameMode == 1){
			mainMaze = new MazePanel(30,30,25,notification, this);
		} else if(gameMode == 2){
			mainMaze = new MazePanel(40,40,20,notification, this);
		} else {
			mainMaze = new MazePanel(100,100,10,notification, this);
		}
			
		this.add(mainMaze, BorderLayout.CENTER);
	}

		
	
}
