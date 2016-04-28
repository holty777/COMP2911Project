import java.util.LinkedList;

/** 
 * generate a random path in the adjancey list
 * @author steve
 *
 */
public class maze{
	private int [][] mazemtx;
	
	public maze (int length, int size){
		this.mazemtx = new int [length][size];
	}
	boolean checkifempty (int col, int row){
		if (mazemtx[col][row] == 1){
			return true;
		}else{
			return false;
		}
	}
}
