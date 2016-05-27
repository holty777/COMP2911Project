import java.util.ArrayList;
import java.util.Random;

/**
 * This Class generates the items which the character can pick up
 * while traversing the maze.
 * 
 * @author  Jack Holt
 * 			Jesse Moses
 * 			Nick Balnaves
 * 			Jordan Jacobson
 * 			Shiyuan Liang
 */
public class ItemGenerator {

	private ArrayList<ItemLoc> itemLocations;
	/*
	 * item limits for the different difficulty
	 * 
	 *  
	 *  
	 * 1. fireballs- shoot a fire ball in the direction on which the player is facing
	 * and it travels for 3,5,7 or breaks if it hits a wall
	 * 2. freeze- freezes mario or the other player
	 * 3. mini mario- slow mario down
	 * 4. speed boost- increase the speed of the player	
	 * 5. drop bomb- drop a bomb and if the player walks over it, he respawn at the starting point
	 * 6. star- invisicibility, increase player speed
	 * 
	 */
	/**
	 * Construction, inits arraylist of item locations
	 * generates random coordinates for the number of items
	 * 
	 * @param numItems 	number of items to be put in maze
	 * @param maze 		maze for the items to be put into
	 */
	public ItemGenerator (int numItems, AlphaMaze maze){
		this.itemLocations = new ArrayList<ItemLoc>();
		generateCoordinates(maze, numItems);
	} 

	/**
	 * Returns a random item
	 * @return randomItem
	 */
	public int randomItemType (){
		Random randItem = new Random();
		return (randItem.nextInt(6)+1);

	}
	
	/**
	 * Generates coordinates within the maze for the items to be displayed at
	 * @param maze
	 * @param numItems
	 */
	public void generateCoordinates (AlphaMaze maze, int numItems){
		ArrayList <ItemLoc> existingLoc = new ArrayList <ItemLoc>();

		int i = 0;
		while (i!=numItems){
			Random rand = new Random();
			int xCoordinate = rand.nextInt(maze.getHeight());
			int yCoordinate = rand.nextInt(maze.getWidth());
			
			//System.out.println("the coordinate generated for the item is at "+x_coordinate+" "+
			//y_coordinate);

			if (!maze.isEmpty(yCoordinate, xCoordinate)){
				//System.out.println("the coordinate generated for the item is at "+x_coordinate+" "+
					//	y_coordinate+" true");
				//creating new object
				ItemLoc newItem = new ItemLoc(yCoordinate, xCoordinate, randomItemType());

				//check if the that coordinate has that item already
				if (!existingLoc.contains(newItem)){
					existingLoc.add(newItem);
					this.itemLocations.add(newItem);
					i++;
				}
			}
		}
	}

	/**
	 * Returns the itemLocaation ArrayList
	 * @return itemLocation		a list of all the item locations
	 */
	public ArrayList<ItemLoc> getArrayItemLoc(){
		return this.itemLocations;
	}
	
	/**
	 * Scans through the arraylist and check if there is an item in that arraylist
	 * @param i
	 * @param j
	 * @return Boolean
	 */
	public boolean ItemLocCheck(int i, int j) {
		//
		for (ItemLoc k: itemLocations){
			if (k.getX() == i && k.getY() == j) return true;
		}
		return false;
	}
	
	/**
	 * Returns -1 if item doesn't exist and otherwise the ID of the item
	 * @param i
	 * @param j
	 * @return
	 */
	public int getItemID(int i, int j){
		for (ItemLoc k: itemLocations){
			if (k.getX() == i && k.getY() == j) return k.getItemType();
		}
		
		//return -1 if there is no such item 
		return -1;
	}
	
	/**
	 * Removes item from the list
	 * @param i
	 * @param j
	 */
	public void popItem (int i, int j){
		for (ItemLoc k: itemLocations){
			if (k.getX() == i && k.getY() == j){
				itemLocations.remove(k);
				break;
			}
		}
	}
}
