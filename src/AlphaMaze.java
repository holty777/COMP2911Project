import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

/**
 * The "AlphaMaze" class generates a maze 
 * for the player to move through. It accepts 
 * a height and width for the maze dimensions.
 * @author  Jack Holt
 * 			Jesse Moses
 * 			Nick Balnaves
 * 			Jordan Jacobson
 * 			Shiyuan Liang
 *
 */
public class AlphaMaze {
	// The height of the maze to be generated.
	private int height;
	// The width of the maze to be generated.
	private int width;
	/*
	 * The array matrix containing the maze details.
	 * 	- 0: Pathway
	 * 	- 1: Wall
	 */
	private int [][] mazemtx;
	//private int [][] start;
	//private int [][] end;
	
	/**
	 * The constructor for the AlphaMaze class.
	 * @param height The height of the maze to be generated.
	 * @param width The width of the maze to be generated.
	 */
	public AlphaMaze (int height, int width){
		this.mazemtx =  new int [height][width];
		this.width = width;
		this.height = height;
		//this.start = new int [1][1];
		//this.end = new int [1][1];
		contruct();
	}
	
	/**
	 * Reset and generate the maze.
	 */
	public void contruct(){
		//resets the 2d array, basically making everything a wall
		reset();
		//maze generator method
		generate();
		//print();
		//exit - win game
		mazemtx[width-2][height-1] = 0;
		//triforce location
		mazemtx[width/2][height/2] = 0;
	}
	
	/**
	 * Begin the maze generation process 
	 * by setting all the elements of the 
	 * "mazemtx" variable to 1 (a wall).
	 */
	public void reset(){
		for(int i=0; i<height; i++){
			for (int j=0; j<width; j++){
				mazemtx[i][j] = 1;
			}
		}
	}
	
	/**
	 * Generate the maze through a depth 
	 * first search algorithm implemented with a stack.
	 */
	public void generate(){
		/*
		 * Create a stack containing nodes 
		 * that has been visited by 
		 * the algorithm so far.
		 */	
		/*
		Mark maze start as visited
		Push start onto the stack
		
		while stack is not empty 
			do
			
			Pop current cell off stack;
			
			if current cell has unvisited neighbors
			then
			Choose a random unvisited neighbor;
			Remove wall with neighbor;
			Mark neighbor as visited;
			Push current cell back on stack;
			Push neighbor on stack;
			end
		end
		*/
		Stack<State> visited = new Stack<State>();
		/*
		 * Always start the maze generation process a 
		 * position [1,1] since the outside border 
		 * will be blocked by a wall. (i.e.[0,0] is a wall)
		 */
		State start = new State(1,1, 1);
		visited.add(start);
		removeWall(start);
	
		//check if visited list is empty
		while (!visited.isEmpty()){
			State curr = visited.pop();
			State moves = possibleMoves(curr, visited);
			if (moves != null){
				
				removeWall(moves);
				visited.push(curr);
				visited.push(pushnextsq (moves));
				visited.push(moves);
			}
		}
		
	}
	//check if +1 square and +2 square is taken
	//if not add +1 square and +2 square to the stack
	//and return random +2square
	/**
	 * Find possible moves from current square. 
	 * @param curr	Current node to find neighbors.
	 * @param visited	A stack containing all visited neighbors.
	 * @return
	 */
	public State possibleMoves(State curr, Stack<State> visited){

		ArrayList <State> possibleDIR = new ArrayList <State>();
		
		/*
		 * Directory
		 * 
		 * north- 1
		 * east - 2
		 * south - 3
		 * west - 4
		 */
		
		// Get the location of the current node.
		int x = curr.getX(); 
		int y = curr.getY();
		

		/*
		System.out.println("**************");
		System.out.println("the x and y coordinate @: "+x+" "+y);
		System.out.println("**************");
		*/
		
		//Initialization of the possible moves
		
		//north move
		State checkN2 = new State (x, y-2, 1);
		//east move
		State checkE2 = new State (x+2, y, 2);
		//south move
		State checkS2 = new State (x, y+2, 3);
		//west move
		State checkW2 = new State (x-2, y, 4);
		
		//north
		//edge cases to check out of bound
		if (y-2 > 0){
			// x & y
			//System.out.println("check north ok");
			if(isEmpty(y-2, x) 
					&& isEmpty(y-1, x)
					&& isEmpty(y-2, x+1)
					&& isEmpty(y-2, x-1)){
				possibleDIR.add(checkN2);
			}
		}
		
		//south
		//edge cases to check out of bound
		//check if +2 ahead left and right is taken
		if (y+2 < height-1){
			//System.out.println("check south ok");
			if(isEmpty(y+2, x)
					&& isEmpty(y+1, x)
					&& isEmpty(y+2, x-1)
					&& isEmpty(y+2, x+1)){
				possibleDIR.add(checkS2);
			}
		}
			
		//east
		//edge cases to check out of bound
		//check if +2 north and south
		if (x+2 < width-1){
			//System.out.println("check east ok");
			if(isEmpty(y, x+2)
					&& isEmpty(y, x+1)
					&& isEmpty(y-1, x+2)
					&& isEmpty(y+1, x+2)){
				possibleDIR.add(checkE2);
			}
		}
			
		//west
		//edge cases to check out of bound
		//check if +2 north and south
		if(x-2 > 0){
			//System.out.println("check west ok");
			if(isEmpty(y, x-2)
					&& isEmpty(y, x-1)
					&& isEmpty(y+1, x-2)
					&& isEmpty(y-1, x-2)){
				possibleDIR.add(checkW2);
			}
		}
		
		if (possibleDIR.size()>1){
			Collections.shuffle(possibleDIR);
		}	
		

		/*printing
		for (int i=0; i<possibleDIR.size();i++){
			System.out.println("x:"+possibleDIR.get(i).getX()+" "
					+"y:"+possibleDIR.get(i).getY());
		}
		System.out.println("------------------------------");
		
		System.out.println();
		print();
		System.out.println();
		*/
		
		if (!possibleDIR.isEmpty()){
			//System.out.println("the next move is at x:"+possibleDIR.get(0).getX()+" "
				//	+"y:"+possibleDIR.get(0).getY());
			/*System.out.println("the next move is at x:"+possibleDIR.get(0).getX()+" "
					+"y:"+possibleDIR.get(0).getY());*/
			return possibleDIR.get(0);
		}else{
			return null;
		}
	}
	
	/**
	 * Transform a cell from wall to pathway.
	 * @param n	The cell to change from wall to pathway.
	 */
	public void removeWall(State n){
		/*
		 * use as a reference
		State checkN = new State (x, y-1, 1);
		State checkN2 = new State (x, y-2, 1);
		
		State checkE = new State (x+1, y, 2);
		State checkE2 = new State (x+2, y, 2);
		
		State checkS = new State (x, y+1, 3);
		State checkS2 = new State (x, y+2, 3);
		
		State checkW = new State (x-1, y, 4);
		State checkW2 = new State (x-2, y, 4);
		*/
		
		if (n.getDIR() == 1){
			//north case
			mazemtx[n.getY()][n.getX()] = 0;
			mazemtx[n.getY()+1][n.getX()] = 0;
		}
		
		if (n.getDIR() == 2){
			//east case
			mazemtx[n.getY()][n.getX()] = 0;
			mazemtx[n.getY()][n.getX()-1] = 0;
		}
			
		if (n.getDIR() == 3){
			//south case
			mazemtx[n.getY()][n.getX()] = 0;
			mazemtx[n.getY()-1][n.getX()] = 0;
		}
			
		if (n.getDIR() == 4){
			//west case
			mazemtx[n.getY()][n.getX()] = 0;
			mazemtx[n.getY()][n.getX()+1] = 0;
		}
	}
	
	/**
	 * Create the next block to add to the stack.
	 * @param p	The current node.
	 * @return	A node to add to the stack.
	 */
	public State pushnextsq (State p){
		/*
		 * use as a reference
		State checkN = new State (x, y-1, 1);
		State checkN2 = new State (x, y-2, 1);
		
		State checkE = new State (x+1, y, 2);
		State checkE2 = new State (x+2, y, 2);
		
		State checkS = new State (x, y+1, 3);
		State checkS2 = new State (x, y+2, 3);
		
		State checkW = new State (x-1, y, 4);
		State checkW2 = new State (x-2, y, 4);
		*/
		
		
		State nextblock = null;
		
		/*
		 * If the direction of the current node is 1
		 * then create a node from the position above.
		 */
		if (p.getDIR() == 1){
			//north
			nextblock = 
					new State (p.getX(), p.getY()+1, p.getDIR());
		}else if (p.getDIR() == 2){
			//east
			nextblock = 
					new State (p.getX()-1, p.getY(), p.getDIR());
		}else if (p.getDIR() == 3){
			//south
			nextblock = 
					new State (p.getX(), p.getY()-1, p.getDIR());
		}else if (p.getDIR() == 4){
			//west
			nextblock = 
					new State (p.getX()+1, p.getY(), p.getDIR());
		}
		
		return nextblock;
	}
	
	
	/**
	 * Check whether a cell is empty of not.
	 * A cell is empty if it is outside the 
	 * expected range of the map, or it is currently a wall.
	 * @param height The height of the maze.
	 * @param width	The width of the maze.
	 * @return Whether the cell is empty.
	 */
	public boolean isEmpty(int height, int width){
		if (height<0 || height >this.height-1 || width < 0 || width>this.width-1){
			return false;
		}
		if (mazemtx[height][width] == 1){
			return true;
		}
		return false;
	}
	/*
	public int [][] getStart(){
		return this.getStart();
	}
	
	public int getEnd (){
		return this.getEnd();
	}
	*/
	
	
	/**
	 * Print out an ASCII maze.
	 * 	- o: represents a wall
	 *  - #: represents a path.
	 */
	public void print(){
		for (int i=0; i<height; i++){
			for (int j=0; j<width; j++){
				if (isEmpty(i, j)){
					System.out.print("o ");
				}else{
					System.out.print("# ");
				}
			}
			System.out.println();
		}
	}
	
	/**
	 * Get the height of the maze.
	 * @return height of the maze.
	 */
	public int getHeight (){
		return this.height;
	}
	
	/**
	 * Get the width of the maze.
	 * @return width of the maze.
	 */
	public int getWidth (){
		return this.width;
	}
}

