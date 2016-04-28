
public class Player {
	private int id;
	private String name;
	private int score;
	
	public Player(int id) {
		this.id = id;
	}
	
	public int getID() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void getScore(int score) {
		this.score = score;
	}
	
	public Move makeMove(int col, int row) {
		//implement move functionality
		Move move = null;
		return move;
	}
	
}