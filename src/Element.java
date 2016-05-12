
public class Element {
	private boolean visited;
	private int x;
	private int y;
	private boolean topIsWall;
	private boolean rightIsWall;
	private boolean bottomIsWall;
	private boolean leftIsWall;
	
	public Element(int x, int y){
		this.x = x;
		this.y = y;
		this.visited = false;
		this.topIsWall = true;
		this.bottomIsWall = true;
		this.leftIsWall = true;
		this.rightIsWall = true;
	}
	
	public boolean beenVisited(){
		return this.visited;
	}

	public void setElementToSeen() {
		this.visited = true;
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public void setTopWallToDoor(){
		this.topIsWall = false;
	}
	public void setBottomWallToDoor(){
		this.bottomIsWall = false;
	}
	public void setRightWallToDoor(){
		this.rightIsWall = false;
	}
	public void setLeftWallToDoor(){
		this.leftIsWall = false;
	}
	
	public String toString(){
		return "(" + this.x + "," + this.y + ")";
	}
}
