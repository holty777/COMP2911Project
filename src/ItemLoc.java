
/**
 * The "ItemLoc" class handles the properties and location of each item.
 * @author  Jack Holt
 * 			Jesse Moses
 * 			Nick Balnaves
 * 			Jordan Jacobson
 * 			Shiyuan Liang *
 */
public class ItemLoc {
	// x coordinate of the item.
	private int x;
	// y coordinate of the item.
	private int y;
	// type of the item (See ItemGenerator.java)
	private int itemType;

	/**
	 * The constructor for the "ItemLoc" class.
	 * @param x	The x coordinate of the item.
	 * @param y	The y coordinate of the item.
	 * @param item	The type of the item (See ItemGenerator.java).
	 */
	public ItemLoc (int x, int y, int item){
		this.x = x;
		this.y = y;
		this.itemType = item;
	}

	/**
	 * Get the x coordinate of the item.
	 * @return x coordinate of the item.
	 */
	public int getX (){
		return this.x;
	}	

	/**
	 * Get the y coordinate of the item.
	 * @return y coordinate of the item.
	 */
	public int getY(){
		return this.y;
	}

	/**
	 * Get the type of the item.
	 * @return the type of the item.
	 */
	public int getItemType (){
		return this.itemType;
	}
}
