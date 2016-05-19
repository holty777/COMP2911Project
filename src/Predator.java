import java.util.ArrayList;

public class Predator {
	private String name = null;
	private int difficulty;
	private AlphaMaze maze;
	//Difficulty is determined by how fast the AI can move.
	//This is done by a refresh time for the AI.
	private int easyTime = 1; 
	private int mediumTime = 1;
	private int headTime = 1;
	
	private int colValue = 0;
	private int rowValue = 0;

	//Currently the AI moves when the player moves.
	
	public Predator(AlphaMaze m) {
		this.maze = m;
	}
	
	public String getName() {
		return name;
	}

	private int getDifficulty() { 
		return difficulty;
	}

	public void setStart(int row, int col){
		this.colValue = col;
		this.rowValue = row;
	}
	
	public int getCol(){
		return this.colValue;
	}
	
	public int getRow(){
		return this.rowValue;
	}
	
	public void makeMove(int playerRow, int playerCol) {
		//Run a BFS to find the player.
		ArrayList<PredatorState> queue = new ArrayList<PredatorState>();
		PredatorState first = new PredatorState(rowValue, colValue, null);
		PredatorState end   = null;
		queue.add(first);
		System.out.println("Row = " + first.getCol() + "Col = " + first.getRow());
		while(true){
			PredatorState cur = queue.get(0);
			queue.remove(0);
			//System.out.println("Row = " + cur.getCol() + "Col = " + cur.getRow());
			//If the player has been found, break
			if (cur.getCol() == playerCol && cur.getRow() == playerRow){
				end = cur;
				break;
			}
			
			//If it hasn't been found.
			//Check Up
			if (!maze.isEmpty(cur.getRow()+1, cur.getCol())){
				//Only add if the previous state was null
				//Or we didn't just come from that direction
				if (cur.getPreviousState()==null || cur.getPreviousState().getRow() != cur.getRow()+1){
					PredatorState pred = new PredatorState(cur.getRow()+1, cur.getCol(), cur);
					queue.add(pred);
				}
			}
			//Check down
			if (!maze.isEmpty(cur.getRow()-1, cur.getCol())){
				//Only add if the previous state was null
				//Or we didn't just come from that direction
				if (cur.getPreviousState()==null || cur.getPreviousState().getRow() != cur.getRow()-1){
					PredatorState pred = new PredatorState(cur.getRow()-1, cur.getCol(), cur);
					queue.add(pred);
				}
			}
			//Check left
			if (!maze.isEmpty(cur.getRow(), cur.getCol()+1)){
				//Only add if the previous state was null
				//Or we didn't just come from that direction
				if (cur.getPreviousState()==null || cur.getPreviousState().getCol() != cur.getCol()+1){
					PredatorState pred = new PredatorState(cur.getRow(), cur.getCol()+1, cur);
					queue.add(pred);
				}
			}
			//Check right
			if (!maze.isEmpty(cur.getRow(), cur.getCol()-1)){
				//Only add if the previous state was null
				//Or we didn't just come from that direction
				if (cur.getPreviousState()==null || cur.getPreviousState().getCol() != cur.getCol()-1){
					PredatorState pred = new PredatorState(cur.getRow(), cur.getCol()-1, cur);
					queue.add(pred);
				}
			}
			
		}
		
		//We have found the player, so now back track
		if (end.getPreviousState() == null){
			System.out.println("YOU HAVE LOST");
		}
		else{
			while (end.getPreviousState().getPreviousState() != null){
				end = end.getPreviousState();
			}
		}
		this.rowValue = end.getRow();
		this.colValue = end.getCol();
		System.out.println("MOVE TO: Row = " + this.rowValue + "Col = " + this.colValue);
		
	}
}
