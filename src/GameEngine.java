
public class GameEngine implements Runnable {

	private GameState currState;  // the current game state
	private GameBoardPanel gameBoardPanel;  // the game board panel
	private int gameMode;     // 0 for simulation, 1 for single player, 2 for double player
	private boolean isInGame; // game is in run
	private int totalGame;    // number of game started
	
	public void startNewGame(int gameMode, Player player1, Player player2,
			GameBoardPanel gameBoardPanel) {
		this.gameMode = gameMode;
		this.gameBoardPanel = gameBoardPanel;
		gameBoardPanel.getMaze().setFocusable(true);
		gameBoardPanel.getMaze().requestFocus();
		gameBoardPanel.getMaze().addKeyListener(gameBoardPanel.getMaze());

		currState = new GameState(player1, player2);
		totalGame++;
		isInGame = true;
	}

	@Override
	public void run() {
		// delay thread to wait game started
		while (true) {
			sleep(500);

			// start game run
			int thisGame = totalGame;
			while (isInGame && thisGame == totalGame) {
				Player currPlayer = currState.getCurrPlayer();

				// check isInGame to avoid AI delay while screen jumping
				if (!Thread.interrupted() && isInGame && thisGame == totalGame) {

					// increment turn
					currState.incTurn();
					currState.setCurrPlayer(currState.getOtherPlayer());

					gameBoardPanel.updateStatisticsPanel();

					// check game end and winner
					if (currState.checkGameEnd()) {
						isInGame = false;
					}
				} else {
					break;
				}
			}
		}
	}

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
		return currState.clone();
	}

	public Player getCurrPlayer() {
		return currState.getCurrPlayer();
	}

	public Player getOtherPlayer() {
		return currState.getOtherPlayer();
	}

	public boolean isValidMove(int move) {
		return currState.isValidMove(move);
	}

	public boolean isInGame() {
		return isInGame;
	}

	public void suspendGame() {
		isInGame = false;
	}

	public int getCurrPlayerIndex() {
		return currState.getTurn() % 2;
	}

	public int getGameMode() {
		return gameMode;
	}

}
