import java.util.ArrayList;
import java.util.Random;

public class ItemGenerator {
	private int numItems;
	private ArrayList<ItemLoc> ItemLocations;
	/*
	 * 1. fireballs
	 * 2. freeze
	 * 3. mini mario
	 * 4. big mario destroy the map
	 * 5. star- invisicibility
	 * 6. speed boost
	 */
	public ItemGenerator (int numItems, AlphaMaze m){
		this.numItems = numItems;
		generateCoordinates(m, numItems);
	} 
	
	public int randomItemType (){
		Random Rand = new Random();
		return int ;
		
	}
	
	public void generateCoordinates (AlphaMaze m, int numItems){
		ArrayList <ItemLoc> existingLoc = new ArrayList <ItemLoc>();
		
		int i = 0;
		while (i != numItems){
			//fix here
			Random Rand = null;
			int x_coordinate = Rand.nextInt(m.getWidth());
			int y_coordinate = Rand.nextInt(m.getHeight());
			
			if (!m.isEmpty(x_coordinate, y_coordinate)){
				//creating new object
				ItemLoc newitem = new ItemLoc(x_coordinate, y_coordinate, randomItemType());
				
				if (!existingLoc.contains(newitem)){
					existingLoc.add(newitem);
					this.ItemLocations.add(newitem);
				}
			}
		}
	}
	
	public ArrayList<ItemLoc> ArraygetItemLoc(){
		return this.ItemLocations;
	}
	
}
