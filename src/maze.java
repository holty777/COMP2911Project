import java.util.LinkedList;
import java.util.Random;

/** 
 * generate a random path in the adjancey list
 * 
 * list of things to be passed in:
 * - difficulty
 * - size: length, height
 * 
 * 
 * @author steve
 *
 */
public class maze{
	private int [][] mazemtx;
	private int length;
	private int height;
	
	public maze (int height, int length, int difficulty){
		//height then length
		this.mazemtx = new int [height][length];
		this.length = length;
		this.height = height;
		//mazegenerator(difficulty);
	}
	
	//check if that block is empty
	//error here
	public boolean checkifempty (int row, int col){
		if (mazemtx[row][col] == 1){
			return true;
		}else{
			return false;
		}
	}
	
	public int randomnum (int max){
	  Random rand = new Random();
	  int randomNum = rand.nextInt((max - 0) + 1) + 0;
	  return randomNum;
	}
	
	
	public void convertblock (int col, int row){
		this.mazemtx [col][row] = 1;
	}
	
	//uses some alogrithm to generate the path
	
	//public void depthfirstsearch (){
		
	//}
	
	public void printer(){
		//print the matrix
		for (int i=0; i<height; i++){
			for (int j=0; j<length; j++){
				if (checkifempty(i, j)){
					System.out.print("O");
				}else{
					System.out.print("1");
				}
			}
			System.out.println();
		}
	}
	
	
	
	public void mazegenerator(int difficulty){
		/*definition of difficulty
		/difficulty is defined from 0 to 3 
		 * where the higher the number, the hard the game is
		 * 0 has 8 entry/exit
		 * 1 has 6
		 * 2 has 4
		 * 3 has 2
		 * 
		*/	
		
		//case statement to determine the number of entry/exit 
		int brokenblock = 0;
		switch (difficulty){
			case 0: brokenblock = 8; break;
			case 1: brokenblock = 6; break;
			case 2: brokenblock = 4; break;
			case 3: brokenblock = 2; break;
		}
			
		//check if the edges is taken
		int pathgenerated = 0;
		
		while (pathgenerated != brokenblock){
			//call random number, problem: random might random two number next to each
			//other, 
			int row = randomnum(this.height);
			int col = randomnum(this.length);
			
			//check if the block is free
			if (checkifempty (col, row)){
				//change the block
				convertblock(col, row);
			}
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
