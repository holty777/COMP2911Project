
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/** 
 * generate a random path in the adjacency list
 * 
 * list of things to be passed in:
 * - difficulty
 * - size: length, height
 * 
 * 
 * @author steve
 *
 */
public class Maze implements Runnable{
	private int [][] mazemtx;
	private int length;
	private int height;
	
	public Maze (){
	}
	
	
	public Maze (int height, int length, int difficulty){
		//height then length
		this.mazemtx = new int [height][length];
		this.length = length;
		this.height = height;
		mazegenerator(difficulty);
	}
	
	//check if that block is empty
	//error here
	public boolean checkifempty (int row, int col){
		if (mazemtx[row][col] == 0){
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
	
	public int randODD (int max){
		  Random rand = new Random();
		  int randomNum = 0;
		  while (randomNum % 2 == 0){
			  randomNum = rand.nextInt((max - 0) + 1) + 0; 
		  }
		  return randomNum;
		}
	
	//random an array of 4 directions
	public Integer[] randomDir (){
		 ArrayList<Integer> randDirections = new ArrayList<Integer>();
		 for (int i=0; i<4; i++){
			 randDirections.add(i+1);
		 }
		 Collections.shuffle(randDirections);
		return randDirections.toArray(new Integer[4]);
	}
	
	public void convertblock (int row, int col){
		this.mazemtx [row][col] = 1;
	}
	
	//uses some alogrithm to generate the path
	
	//public void depthfirstsearch (){
		
	//}
	
	public void printer(){
		//print the matrix
		for (int i=0; i<height; i++){
			for (int j=0; j<length; j++){
				if (checkifempty(i, j)){
					System.out.print("o");
				}else{
					System.out.print("*");
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
		
		/*
		//case statement to determine the number of entry/exit 
		int brokenblock = 0;
		switch (difficulty){
			case 0: brokenblock = 8; break;
			case 1: brokenblock = 6; break;
			case 2: brokenblock = 4; break;
			case 3: brokenblock = 2; break;
		}
			
		//check if the edges is taken
		int openPTS= 0;
		
		while (openPTS != brokenblock){
			//call random number, problem: random might random two number next to each
			//other, 
			int row = randomnum(this.height);
			int col = randomnum(this.length);
			
			if (row == height) row --;
			if (col == length) col --;
			
			//check if the block on the top edge is empty
			if (checkifempty(0, col)){
				convertblock(0, col);
				openPTS++;
			}else if (checkifempty(height-1, col)){
				convertblock(height-1, col);
				openPTS++;
			}
			
			if (checkifempty(row, 0)){
				convertblock(row, 0);
				openPTS++;
			}else if (checkifempty(row, length-1)){
				convertblock(row, length-1);
				openPTS++;
			}
		}
			*/
			
		//random a point and start generating a path
		int startX = randODD(length);
		int startY = randODD(height);
		
		//System.out.println("the height and width: "+startY +" " +startX);
		
		convertblock (startY, startX);
			
		MazeDFS (startY, startX);
			
		
	}
	
	public int MazeDFS (int row, int col){
		//implements dfs alogrithm to generate a random maze
 
		Integer [] randomDir = randomDir();
		
		for (int j=0; j<randomDir.length; j++){
			System.out.println(randomDir[j]);
			switch(randomDir[j]){
			
			//moving up
			case 1:
				if ((row-2) <= 0) continue;
				if (checkifempty (row-2, col)){
					convertblock (row-2, col);
					convertblock(row-1, col);
					return MazeDFS(row-2, col);
				}
				break;
				
				
			//moving right
			case 2:
				if((col+2) >= (length-1) ) continue;
				if (checkifempty (row,col+2)){
					convertblock (row, col+2);
					convertblock (row,col+1);
					return MazeDFS(row,col+2);
				}
			break;
			
			//moving down
			case 3:
				if ((row+2) >= (height-1)) continue;
				if (checkifempty (row+2, col)){
					convertblock(row+2, col);
					convertblock(row+1, col);
					return MazeDFS(row+2, col);
				}
				break;
				
			//moving left
			case 4:
				//row and then col
				if((col-2) <= 0) continue;
				if (checkifempty (row,col-2)){
					convertblock (row, col-2);
					convertblock (row,col-1);
					return MazeDFS(row,col-2);
				}
				break;
			}
		}
		
		if(true){
			System.out.println("i broke out of here...."
					+row+" "+col);
			return 0;
		}
		return 0;
	}

	
	public void optimalise (){
		//use this fucntion to clean up the maze
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

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	

	
	
}
