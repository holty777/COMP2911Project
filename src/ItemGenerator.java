import java.util.ArrayList;
import java.util.Random;

public class ItemGenerator {

	private ArrayList<ItemLoc> ItemLocations;
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
	public ItemGenerator (int numItems, AlphaMaze m){
		this.ItemLocations = new ArrayList<ItemLoc>();
		generateCoordinates(m, numItems);
	} 

	public int randomItemType (){
		Random Rand = new Random();
		return (Rand.nextInt(6)+1);

	}

	public void generateCoordinates (AlphaMaze m, int numItems){
		ArrayList <ItemLoc> existingLoc = new ArrayList <ItemLoc>();

		int i = 0;
		while (i!=numItems){
			Random Rand = new Random();
			int x_coordinate = Rand.nextInt(m.getHeight());
			int y_coordinate = Rand.nextInt(m.getWidth());
			
			//System.out.println("the coordinate generated for the item is at "+x_coordinate+" "+
			//y_coordinate);

			if (!m.isEmpty(y_coordinate, x_coordinate)){
				//System.out.println("the coordinate generated for the item is at "+x_coordinate+" "+
					//	y_coordinate+" true");
				//creating new object
				ItemLoc newitem = new ItemLoc(y_coordinate, x_coordinate, randomItemType());

				//check if the that coordinate has that item already
				if (!existingLoc.contains(newitem)){
					existingLoc.add(newitem);
					this.ItemLocations.add(newitem);
					i++;
				}
			}
		}
	}

	public ArrayList<ItemLoc> ArraygetItemLoc(){
		return this.ItemLocations;
	}
	
	public boolean ItemLocCheck(int i, int j) {
		//scan through the arraylist and check if there is an item in that arraylist
		for (ItemLoc k: ItemLocations){
			if (k.getX() == i && k.getY() == j) return true;
		}
		return false;
	}
	
	public int getItemID(int i, int j){
		for (ItemLoc k: ItemLocations){
			if (k.getX() == i && k.getY() == j) return k.getItemType();
		}
		
		//return -1 if there is no such item 
		return -1;
	}
	
	//remove the item from the array list 
	public void popItem (int i, int j){
		for (ItemLoc k: ItemLocations){
			if (k.getX() == i && k.getY() == j){
				ItemLocations.remove(k);
				break;
			}
		}
	}
}
