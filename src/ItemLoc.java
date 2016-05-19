
public class ItemLoc {
	private int x;
	private int y;
	private int itemType;
	
	public ItemLoc (int x, int y, int item){
		this.x = x;
		this.y = y;
		this.itemType = item;
	}
	
	public int getX (){
		return this.x;
	}	
	public int getY(){
		return this.y;
	}
	public int getItemType (){
		return this.itemType;
	}
}
