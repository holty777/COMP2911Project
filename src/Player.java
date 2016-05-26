import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Player extends JLabel {
	private int previousDirection;
	private int id;
	private String name;
	private int score;
	private int width;
	private int height;
	private int iLocation;
	private int jLocation;
	
	//player attribute
	/*
	 * if the player attribute is:
	 * 0- no special ability
	 * 1. fireballs- shoot a fire ball in the direction on which the player is facing
	 * and it travels for 3,5,7 or breaks if it hits a wall
	 * 2. freeze- freezes mario or the other player
	 * 3. mini mario- slow mario down
	 * 4. big mario destroy the map- if timer is end, mario turns big and stream 
	 * 	  rolls the player
	 * 5. star- invisicibility, increase player speed
	 * 6. speed boost squares- boost the speed the player for a number of squares
	 */
	private int attribute;
	private int attributeRunTime;
	//used to counter the number of fire ball
	private int countattribute; 
	
	
	public Player(int i, int j, int height, int width, int character) {
		this.height = height;
		this.width = width;
		this.iLocation = i;
		this.jLocation = j;
		Image img = null;
		this.attribute = 0;
		this.attributeRunTime = 0;
		this.countattribute = 0;
		this.previousDirection = 1;
		System.out.println("the character id:" +character);
		this.id = character;
		
		try {
			if(character == 0){
				img = ImageIO.read(new File("src/link_stationary.png"));
			}
			if(character == 1){
				img = ImageIO.read(new File("src/triforce.jpg"));
			}
			if (character == 2){
				img = ImageIO.read(new File("src/mario_stationary.png"));
			}
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image bimg = img.getScaledInstance(this.width, this.height,
		        Image.SCALE_SMOOTH);
		BufferedImage dimg = toBufferedImage(bimg);
		
		ImageIcon link = new ImageIcon(dimg);
		
		this.setIcon(link);
	}
	
	public int getID() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public int getILocation(){
		return this.iLocation;
	}
	
	public int getJLocation(){
		return this.jLocation;
	}
	public void setILocation(int i){
		this.iLocation = i;
	}
	public void setJLocation(int j){
		this.jLocation = j;
	}
	public Move makeMove(int col, int row) {
		//implement move functionality
		Move move = null;
		return move;
	}
	
	public void decAttribute (){
		if (this.attributeRunTime != 0){
			this.attributeRunTime --;
			//turn the player back to normal form 
			//if the run time has reach zero
			if (this.attributeRunTime == 0){
				this.attribute = 0;
			}
		}
		
	}
	
	public void decCountAttribute (){
		if (this.countattribute != 0){
			this.countattribute --;
			if (this.countattribute == 0){
				this.attribute = 0;
			}
		}
	}
	
	public int getAttribute(){
		return this.attribute;
	}
	
	//set the player attribute
	public void setAttribute (int attr){
		/*
		fireball : 1
		freeze: 2
		mini: 3
		big: 4
		star: 5
		speed: 6
		*/
		//set attribute and set turns
		this.attribute = attr;
		switch (attr){
			case(1): 
				//the player gets to shoot 3xfire ball 
				this.countattribute = 3;
				break;
			case(2): 
				//the enemy is frozen for 2 turns
				this.attributeRunTime = 2;
				break;
			case(3):
				//the enemy is mini for 2 turns
				this.attributeRunTime = 2;
				break;
			case(4):
				//the run time will be till the game ends
				this.attributeRunTime = -1;
				break;	
			case(5):
				this.attributeRunTime =  4;
				break;
			case(6):
				this.attributeRunTime = 5;
				break;
		}
	
	}
	
	public static BufferedImage toBufferedImage(Image img)
	{
	    if (img instanceof BufferedImage)
	    {
	        return (BufferedImage) img;
	    }

	    // Create a buffered image with transparency
	    BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

	    // Draw the image on to the buffered image
	    Graphics2D bGr = bimage.createGraphics();
	    bGr.drawImage(img, 0, 0, null);
	    bGr.dispose();

	    // Return the buffered image
	    return bimage;
	}

	public int getlastDIR() {
		return this.previousDirection;
	}
	
	public void setLastDirection(int key){
		this.previousDirection = key;
	}
	
	public void changeGraphicMovement (){
		Image img = null;
		
		System.out.println("shut up throws character id:"+this.id);
		if (this.id == 0){
			//link
			try {
				System.out.println("knees weak directio is :"+this.previousDirection);

				
				//load north 
				if (this.previousDirection == 1){
					//need to fix
					img = ImageIO.read(new File("src/link_back.png"));
				}else if (this.previousDirection == 2){
				//load east
					img = ImageIO.read(new File("src/link_right.png"));
				}else if (this.previousDirection == 3){
				//load south
					img = ImageIO.read(new File("src/link_stationary.png"));
				}else if (this.previousDirection == 4){
				//load west
					img = ImageIO.read(new File("src/link_left.png"));
				}
				if (img == null){
					System.out.println("mom spaghetti~");
				}
				Image bimg = img.getScaledInstance(this.width, this.height,
				        Image.SCALE_SMOOTH);
				BufferedImage dimg = toBufferedImage(bimg);
				
				ImageIcon link = new ImageIcon(dimg);
				
				this.setIcon(link);
				Image bimg1 = img.getScaledInstance(this.width, this.height,
				        Image.SCALE_SMOOTH);
				BufferedImage dimg1 = toBufferedImage(bimg1);
				
				ImageIcon link1 = new ImageIcon(dimg);
				
				this.setIcon(link1);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if (this.id == 2){
			//mario
			try {
				//load north 
				if (this.previousDirection == 1){
					img = ImageIO.read(new File("src/mario_run_back.png"));
				}else if (this.previousDirection == 2){
				//load east
					img = ImageIO.read(new File("src/mario_run_right.png"));
				}else if (this.previousDirection == 3){
				//load south
					img = ImageIO.read(new File("src/mario_run_right.png"));
				}else if (this.previousDirection == 4){
				//load west
					img = ImageIO.read(new File("src/mario_run_left.png"));
				}
				
				Image bimg = img.getScaledInstance(this.width, this.height,
				        Image.SCALE_SMOOTH);
				BufferedImage dimg = toBufferedImage(bimg);
				
				ImageIcon link = new ImageIcon(dimg);
				
				this.setIcon(link);
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}else{
			try{
				img = ImageIO.read(new File("src/triforce.jpg"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
