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
	
	public void buildTestMaze(){
		for(int i=0; i < 10; i++){
        	for(int j=0; j < 10; j++){
        		if(i % 2 != 0){
        			mazemtx[i][j] = 1;
        		}
        		else mazemtx[i][j] = 0;
        	}
		}
	}
}
