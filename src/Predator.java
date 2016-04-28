public class Predator {
	private int id;
	private String name = null;
	private int difficulty; // number of moves the predator can calculate ahead

	public Predator(int id, int difficulty) {
		this.id = id;
		this.difficulty = difficulty;
	}

	public int getID() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	private int getDifficulty() { 
		return difficulty;
	}

	public Move makeMove(int col, int row) {
		//implement move functionality
		Move move = null;
		return move;
	}
}
