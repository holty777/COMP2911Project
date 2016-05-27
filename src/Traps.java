
public class Traps {
	private int LocX;
	private int LocY;
	private int itemID;
	
	public Traps (int x, int y, int itemID){
		this.LocX = x;
		this.LocY = y;
		this.itemID = itemID;
	}
	
	public boolean checkTrap(int i, int j){
		//i is x and j is y
		if (this.LocX == i && this.LocY == j) return true;
		return false;
	}
	
	public int getItemID (){
		return this.itemID;
	}
	
	public int getX(){
		return this.LocX;
	}
	
	public int getY(){
		return this.LocY;
	}
}
