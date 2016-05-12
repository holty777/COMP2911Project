
public class State {
	private int x;
	private int y;
	private int direction;
	
	public State (int x, int y, int direction){
		this.x = x;
		this.y = y;
		this.direction = direction;
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public int getDIR (){
		return this.direction;
	}
	
}
