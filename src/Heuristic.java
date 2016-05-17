public class Heuristic {
//implement two different types of heuristics here
	//euclidean distance
	public float getHvalue (int x1, int x2, int y1, int y2){
		double dy = Math.pow((y2-y1),2);
		double dx = Math.pow((x2-x1),2);
		
		if (dx>dy){
			return (float)Math.sqrt(dy+dx);
		}else{
			dy = Math.abs(y2-y1);
			dx = Math.abs(x2-x1);
			
			return (float) (dy+dx);
		}
	}
}
