
public class GameState {
	private final int MAX_TURN = 42;
	private final int COL_MAX = 7;
	private final int ROW_MAX = 6;

	private Player[][] board;
	private Player currPlayer;
	private Player nextPlayer;
	private Player winner;
	private int turn;

	public GameState(Player firstPlayer, Player secondPlayer) {
		board = new Player[COL_MAX][ROW_MAX];
		currPlayer = firstPlayer;
		nextPlayer = secondPlayer;
		turn = 1;
	}

	public GameState(Player[][] board, Player currPlayer, Player nextPlayer,
			Player winner, int turn) {
		this.board = board;
		this.currPlayer = currPlayer;
		this.nextPlayer = nextPlayer;
		this.winner = winner;
		this.turn = turn;
	}

	/**
	 * 
	 * @param col
	 * @return
	 */
	public int runNextMove(int col) {
		if (col >= 0 & col < COL_MAX) {
			int row = getAvailableRow(col);
			board[col][row] = currPlayer;
			return row;
		}
		return -1;
	}

	/**
	 * Determine whether the Game has ended.
	 * @return	Whether the game has ended:
	 * 			- true: Game has ended.
	 * 			- false: Game has not ended.
	 */
	public boolean checkGameEnd() {
		boolean gameEnd = false;

		// check vertical
		for (int c = 0; c < COL_MAX; c++) {
			for (int r = 0; r < ROW_MAX - 3; r++) {
				Player curr = board[c][r];
				if (curr != null && curr.equals(board[c][r + 1])
						&& curr.equals(board[c][r + 2])
						&& curr.equals(board[c][r + 3])) {
					winner = curr;
					gameEnd = true;
				}
			}
		}

		// check turn number
		// check only if no winner on 42
		if (turn == MAX_TURN + 1) gameEnd = true;
		if (gameEnd) return true;
		return false;
	}

	/**
	 * Get a location given its coordinates.
	 * @param col	The column in the grid. (int)
	 * @param row	The row in the grid. (int)
	 * @return		?
	 */
	public Player getLocation(int col, int row) {
		if (col >= 0 && col < COL_MAX && row >= 0 && row < ROW_MAX)
			return this.board[col][row];
		return null;
	}

	/**
	 * Get a clone of the board.
	 * @return	A shallow copy of the board. (Player[][])
	 */
	public Player[][] getBoard() {
		Player[][] cloneBoard = new Player[COL_MAX][];
		for (int i = 0; i < COL_MAX; i++)
			cloneBoard[i] = board[i].clone();
		return cloneBoard;
	}

	/**
	 * Get the other player.
	 * @return	The other player. (Player)
	 */
	public Player getOtherPlayer() {
		return nextPlayer;
	}

	/**
	 *
	 * @param col
	 * @return
	 */
	public int getAvailableRow(int col) {
		if (col >= 0 && col < COL_MAX)
			for (int r = 0; r < ROW_MAX; r++)
				if (board[col][r] == null)
					return r;
		return -1;
	}

	/**
	 * Get the winner of the competition.
	 * @return The winner (Player)
	 */
	public Player getWinner() {
		return this.winner;
	}

	public void setCurrPlayer(Player p) {
		Player temp = currPlayer;
		currPlayer = p;
		nextPlayer = temp;
	}

	public Player getCurrPlayer() {
		return currPlayer;
	}

	public int getTurn() {
		return turn;
	}

	public void incTurn() {
		turn++;
	}

	public boolean isValidMove(int col) {
		if (col >= 0 && col < COL_MAX && board[col][5] == null)
			return true;
		return false;
	}

	@Override
	public GameState clone() {
		GameState cloneState = new GameState(getBoard(), currPlayer,
				nextPlayer, winner, turn);
		return cloneState;
	}
}