import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Predator{
	private String name = null;
	private int difficulty;
	private AlphaMaze maze;
	//Difficulty is determined by how fast the AI can move.
	//This is done by a refresh time for the AI.
	private int easyTime = 1; 
	private int mediumTime = 1;
	private int headTime = 1;

	private int width;
	private int height;

	private boolean won = false;

	private int colValue = 0;
	private int rowValue = 0;

	private int previousDirection = 3;


	public Predator(AlphaMaze m, int iconWidth, int iconHeight) {
		this.maze = m;
	}

	public String getName() {
		return name;
	}

	private int getDifficulty() { 
		return difficulty;
	}

	public boolean won(){
		return this.won;
	}

	public int getPreviousDirection(){
		System.out.println(this.previousDirection);
		return this.previousDirection;
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

	public static BufferedImage toBufferedImage(Image img)
	{
		if (img instanceof BufferedImage)
		{
			return (BufferedImage) img;
		}

		// Create a buffered image with transparency
		BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

		// Draw the image on to the buffered image
		Graphics2D bGr = bimage.createGraphics();
		bGr.drawImage(img, 0, 0, null);
		bGr.dispose();

		// Return the buffered image
		return bimage;
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
			if (!maze.isEmpty(cur.getRow()+1, cur.getCol()) && !outOfBounds(cur.getRow()+1, cur.getCol())){
				//Only add if the previous state was null
				//Or we didn't just come from that direction
				if (cur.getPreviousState()==null || cur.getPreviousState().getRow() != cur.getRow()+1){
					PredatorState pred = new PredatorState(cur.getRow()+1, cur.getCol(), cur);
					queue.add(pred);
				}
			}
			//Check down
			if (!maze.isEmpty(cur.getRow()-1, cur.getCol()) && !outOfBounds(cur.getRow()-1, cur.getCol())){
				//Only add if the previous state was null
				//Or we didn't just come from that direction
				if (cur.getPreviousState()==null || cur.getPreviousState().getRow() != cur.getRow()-1){
					PredatorState pred = new PredatorState(cur.getRow()-1, cur.getCol(), cur);
					queue.add(pred);
				}
			}
			//Check left
			if (!maze.isEmpty(cur.getRow(), cur.getCol()+1) && !outOfBounds(cur.getRow(), cur.getCol()+1)){
				//Only add if the previous state was null
				//Or we didn't just come from that direction
				if (cur.getPreviousState()==null || cur.getPreviousState().getCol() != cur.getCol()+1){
					PredatorState pred = new PredatorState(cur.getRow(), cur.getCol()+1, cur);
					queue.add(pred);
				}
			}
			//Check right
			if (!maze.isEmpty(cur.getRow(), cur.getCol()-1) && !outOfBounds(cur.getRow()+1, cur.getCol()-1)){
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
			this.won = true;
		}
		else{
			while (end.getPreviousState().getPreviousState() != null){
				end = end.getPreviousState();
			}
		}
		changePreviousMovement(this.rowValue, this.colValue, end.getRow(), end.getCol());
		this.rowValue = end.getRow();
		this.colValue = end.getCol();

	}
	
	private boolean outOfBounds(int row, int col){
		if (row<0 || row>=maze.getHeight() || col<0 || col>=maze.getWidth()) return true; 
		return false;
	}

	private void changePreviousMovement(int lastRow, int lastCol, int newRow, int newCol){
		//Check up
		if (lastRow+1 == newRow){
			this.previousDirection = 3;
		}
		//Check down
		else if (lastRow-1 == newRow){
			this.previousDirection = 1;
		}
		//check left
		else if (lastCol-1 == newCol){
			this.previousDirection = 4;
		}
		//check right
		else if (lastCol+1 == newCol){
			this.previousDirection = 2;
		}
	}
}
