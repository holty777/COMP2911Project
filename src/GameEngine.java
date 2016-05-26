import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

/**
 * The "GameEngine" class handles all functionality 
 * concerning the logic behind playing the maze game.
 * Including assaigning whos turn it is.
 * @author  Jack Holt
 * 			Jesse Moses
 * 			Nick Balnaves
 * 			Jordan Jacobson
 * 			Shiyuan Liang
 *
 */
public class GameEngine implements Runnable {

	private GameState currState;  // the current game state
	private GameBoardPanel gameBoardPanel;  // the game board panel
	private int gameMode;     // 1 for single player, 2 for double player
	private boolean isInGame; // game is in run

	/**
	 * Create a new game.
	 * @param gameMode	The mode currently being utilized in the game 
	 * 					(1 - Single player, 2 - Double player).
	 * @param player1	The player 1.
	 * @param player2	The player 2.
	 * @param gameBoardPanel	A class responsible for displaying the board.
	 */
	public void startNewGame(int gameMode, Player player1, Player player2,
			GameBoardPanel gameBoardPanel) {

		this.gameMode = gameMode;
		this.gameBoardPanel = gameBoardPanel;
		gameBoardPanel.setMaze(gameMode);
		gameBoardPanel.getMaze().setFocusable(true);
		gameBoardPanel.getMaze().requestFocus();
		gameBoardPanel.getMaze().addKeyListener(gameBoardPanel.getMaze());
		this.currState = new GameState(player1, player2);
		this.isInGame = true;
	}

	/**
	 * Run the game.
	 */
	@Override
	public void run() {
		// delay thread to wait game started
		while (true) {
			sleep(500);
			Timer SimpleTimer = new Timer(1000, new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					//	System.out.println("Time =  " + SimpleTime.format(new Date()));
				}
			});
			SimpleTimer.start();
			/*
			 * Run the game while the game is not over 
			 * and it is the same game.
			 */
			while (isInGame) {
				Player currPlayer = this.currState.getCurrPlayer();

				// check isInGame to avoid AI delay while screen jumping
				if (!Thread.interrupted() && isInGame) {

					// increment turn
					this.currState.incTurn();
					this.currState.setCurrPlayer(this.currState.getOtherPlayer());

					this.gameBoardPanel.updateStatisticsPanel();

					// check game end and winner
					if (this.currState.checkGameEnd()) {
						this.isInGame = false;
					}
				} else {
					break;
				}
			}
		}
	}

	/**
	 * Sleep for some time.
	 * @param millis	The amount of time to sleep for.
	 */
	private void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

	/**
	 * Getter function for the current game state.
	 * @return a copy of the current game state
	 */
	public GameState getCurrState() {
		return this.currState.clone();
	}

	/**
	 * Getter function for the current player.
	 * @return a copy of the current player.
	 */
	public Player getCurrPlayer() {
		return this.currState.getCurrPlayer();
	}

	/**
	 * Getter function for the non-current player.
	 * @return a copy of the non-current player.
	 */
	public Player getOtherPlayer() {
		return this.currState.getOtherPlayer();
	}

	/**
	 * Determine whether the move is valid depending on the current state.
	 * @param move The move to make.
	 * @return	Whether or not the move was valid.
	 */
	public boolean isValidMove(int move) {
		return this.currState.isValidMove(move);
	}

	/**
	 * Determine whether currently in a game.
	 * @return
	 */
	public boolean isInGame() {
		return this.isInGame;
	}

	/**
	 * Suspend the gameplay.
	 */
	public void suspendGame() {
		this.isInGame = false;
	}

	/**
	 * Get the current player number.
	 * @return	The current player number.
	 */
	public int getCurrPlayerIndex() {
		return this.currState.getTurn() % 2;
	}

	/**
	 * Get the game mode
	 * @return The game mode.
	 */
	public int getGameMode() {
		return this.gameMode;
	}

}
