import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.text.Position;

public class GameBoardPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private GameWindow gameWindow;
	private MazePuzzleGame mainGame;
	private GameEngine gameEngine;
	private MazePanel testMaze;

	// game data
	private Player player1;
	private Player player2;
	private int gameMode; // 0 for simulation, 1 for single player, 2 for double player

	public GameBoardPanel(GameWindow gameWindow, MazePuzzleGame mainGame) {

		this.gameWindow = gameWindow;
		this.mainGame = mainGame;
		gameEngine = mainGame.getGameEngine();

		setOpaque(true);
		setBackground(new Color(27, 120, 236));
		//setLayout(new GridLayout(6, 7));
		setLayout(new BorderLayout());
		
		
	}
	
	public void updateStatisticsPanel() {
		if (gameWindow != null && gameWindow.getStatisticsPanel() != null)
			gameWindow.getStatisticsPanel().setWhosTurn();
	}

	public void displayEndGame(Player winner) {
		if (gameMode == 0)
			return; // stimulation
		gameWindow.getStatisticsPanel().displayEndGame(winner);
	}

	public void restartNewGame() {
		mainGame.suspendGame();

		// randomize first player for single player mode
		if (gameMode != 0) {
			if (randPlayer() == 0) {
				Player temp = player1;
				player1 = player2;
				player2 = temp;
			}
			gameWindow.getStatisticsPanel().setPlayerNames(player1, player2);
		}
		
		startNewGame();
		updateStatisticsPanel();
	}

	public void startNewGame() {

		gameEngine.startNewGame(gameMode, player1, player2, this);

	}

	public void startSimulationGame() {
		gameMode = 0;
		startNewGame();
	}

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
		gameWindow.getStatisticsPanel().setPlayerNames(player1, player2);
		startNewGame();
		updateStatisticsPanel();
	}

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
		gameWindow.getStatisticsPanel().setPlayerNames(player1, player2);
		startNewGame();
		updateStatisticsPanel();
	}

	private int randPlayer() {
		Random rand = new Random();
		return rand.nextInt(2);
	}
	
	public MazePanel getMaze(){
		return this.testMaze;
	}
	
	public void setMaze(int gameMode){
		if(gameMode == 0){
			testMaze = new MazePanel(20,20);
		} else if(gameMode == 1){
			testMaze = new MazePanel(30,30);
		} else if(gameMode == 2){
			testMaze = new MazePanel(40,40);
		} else {
			testMaze = new MazePanel(100,100);
		}
			
		this.add(testMaze, BorderLayout.CENTER);
	}
}
