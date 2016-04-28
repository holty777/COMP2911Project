
public class Move {
	private int col;
	private int row;
	public Move (int col, int row) {
		this.col = col;
		this.row = row;
	}
	
	public int getRow() {
		return this.row;
	}

	public int getCol() {
		return this.col;
	}
}