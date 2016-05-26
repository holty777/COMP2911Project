
public class PredatorState {
	private int row;
	private int col;
	private PredatorState previousState = null;

	public PredatorState(int row, int col, PredatorState prev){
		this.row = row;
		this.col = col;
		this.previousState = prev;

	}

	public int getRow(){
		return this.row;
	}

	public int getCol(){
		return this.col;
	}

	public PredatorState getPreviousState(){
		return this.previousState;
	}
}
