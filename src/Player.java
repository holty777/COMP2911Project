import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Player extends JLabel {
	private int id;
	private String name;
	private int score;
	private int width;
	private int height;
	private int iLocation;
	private int jLocation;
	
	public Player(int i, int j, int height, int width, int character) {
		this.height = height;
		this.width = width;
		this.iLocation = i;
		this.jLocation = j;
		Image img = null;
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
	
}
