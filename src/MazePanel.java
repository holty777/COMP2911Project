import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.GridLayout;
import java.awt.Image;



/**
 * The "MazePanel" class creates a handles the input/output for the maze.
 * @author  Jack Holt
 * 			Jesse Moses
 * 			Nick Balnaves
 * 			Jordan Jacobson
 * 			Shiyuan Liang
 *
 */
public class MazePanel extends JPanel implements MouseListener, KeyListener {
	
	private JPanel homeGlassPane;
	private JFrame mainFrame;
	// The height of the maze.
	private int height;
	// The length of the maze
	private int length;
	// The player
	private Player player;
	// The target 
	private Player triForce;
	
	private Player predPlayer;
	// The game layout
	private GridLayout grid;
	// The maze
	private AlphaMaze mainMaze;
	// What is being displayed at each point of the maze.
	private JLabel[][] labelGrid;
	// The predator
	private Predator predator;
	// The count of moves.
	private int movesMade;
	
	/**
	 * The "MazePanel" constructor.
	 * @param height	The height of the maze.
	 * @param length	The length/width of the maze.
	 */
	public MazePanel(int height, int length) {
		height --;
		length --;
		this.movesMade = 0;
		labelGrid = new JLabel[height][length];
		this.height = height;
		this.length = length;
		mainMaze = new AlphaMaze(height ,length );
		grid = new GridLayout(height ,length);
		this.setLayout(grid);
		this.predator = new Predator(mainMaze);
		homeGlassPane = new JPanel();
		homeGlassPane.setPreferredSize(new Dimension(700, 700));
		
		
		
		initMaze();

	}

	/**
	 * Load a matrix with what to display, 
	 * to represent the maze.
	 */
	public void initMaze(){
		boolean check = false;
		int x = 0;
		int y = 0;
        for(int i=0; i < height; i++){
        	for(int j=0; j < length; j++){
        		if(mainMaze.isEmpty(i,j) == false){
        			if(check == false){
        				player = new Player(i, j, 25, 25, 0);
        				player.setMinimumSize(new Dimension(10,10));
        				player.setPreferredSize(new Dimension(10,10));
        				player.setMaximumSize(new Dimension(10,10));
        				labelGrid[i][j] = player;
        				//this.add(player);
        				check = true;
        			}
        			else{
        				JLabel full = new JLabel();
        				full.setBackground(Color.white);
        				full.setOpaque(true);
        				full.setMinimumSize(new Dimension(10,10));
        				full.setPreferredSize(new Dimension(10,10));
        				full.setMaximumSize(new Dimension(10,10));
        				
        				ImageIcon image = new ImageIcon("src/stone_path.png");
            			JLabel imagelabel = new JLabel(image);
            			imagelabel.setOpaque(true);
            			imagelabel.setMinimumSize(new Dimension(10,10));
            			imagelabel.setPreferredSize(new Dimension(10,10));
            			imagelabel.setMaximumSize(new Dimension(10,10));
        				
        				labelGrid[i][j] = full;
        				//this.add(full);
        				x = i;
        				y = j;
        			}
        		}
        		else {
        			
        			JLabel blank = new JLabel();
        			blank.setBackground(Color.BLACK);
        			blank.setOpaque(true);
        			blank.setMinimumSize(new Dimension(10,10));
        			blank.setPreferredSize(new Dimension(10,10));
        			blank.setMaximumSize(new Dimension(10,10));
        			
        			ImageIcon image = new ImageIcon("src/hedge.png");
        			JLabel imagelabel = new JLabel(image);
        			imagelabel.setOpaque(true);
        			imagelabel.setMinimumSize(new Dimension(10,10));
        			imagelabel.setPreferredSize(new Dimension(10,10));
        			imagelabel.setMaximumSize(new Dimension(10,10));
        			
        			
        			
        			labelGrid[i][j] = imagelabel;
        			//this.add(blank);
        			
        		}
        		

        	}
        	
        }
        predator.setStart(x, 1);
        predPlayer = new Player(x, 0, 25, 25, 2);
		predPlayer.setMinimumSize(new Dimension(10,10));
		predPlayer.setPreferredSize(new Dimension(10,10));
		predPlayer.setMaximumSize(new Dimension(10,10));
    	labelGrid[x][1] = predPlayer;
    	
        triForce = new Player(x, y, 25, 25, 1);
		triForce.setMinimumSize(new Dimension(10,10));
		triForce.setPreferredSize(new Dimension(10,10));
		triForce.setMaximumSize(new Dimension(10,10));
    	labelGrid[x][y] = triForce;
        for(int i=0; i < height; i++){
        	for(int j=0; j < length; j++){
        			this.add(labelGrid[i][j]);
        	}
        }
        
	}
	
//	public Dimension getPreferredSize() {
//        return new Dimension(500,500);
//    }
	
	

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void keyTyped(KeyEvent e) {
		if (e.getKeyChar() == 'h'){
			System.out.println("in");
			
			if (homeGlassPane instanceof Instructions)
				homeGlassPane.repaint();
			else
				homeGlassPane = new Instructions();
			if (homeGlassPane != null) {
				//mainFrame.setGlassPane(homeGlassPane);
				
				homeGlassPane.setOpaque(false);
				homeGlassPane.setVisible(true);
			}
		}
		//display();
	}

	
	/**
	 * If the user enders an arrow key, 
	 * move the player if possible
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		this.movesMade++;
		System.out.println(this.movesMade);
		if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			int i = player.getILocation();
			int j = player.getJLocation();
			if(mainMaze.isEmpty(i, j+1) == false){
				player.setILocation(i);
				player.setJLocation(j+1);
				labelGrid[i][j+1] = player;
				JLabel blank = new JLabel();
				blank.setBackground(Color.white);
    			blank.setOpaque(true);
    			blank.setMinimumSize(new Dimension(10,10));
    			blank.setPreferredSize(new Dimension(10,10));
    			blank.setMaximumSize(new Dimension(10,10));
				labelGrid[i][j] = blank;
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_LEFT){

			int i = player.getILocation();
			int j = player.getJLocation();
			if(mainMaze.isEmpty(i, j-1) == false){
				player.setILocation(i);
				player.setJLocation(j-1);
				labelGrid[i][j-1] = player;
				JLabel blank = new JLabel();
				blank.setBackground(Color.white);
    			blank.setOpaque(true);
    			blank.setMinimumSize(new Dimension(10,10));
    			blank.setPreferredSize(new Dimension(10,10));
    			blank.setMaximumSize(new Dimension(10,10));
				labelGrid[i][j] = blank;
			
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_UP){

			int i = player.getILocation();
			int j = player.getJLocation();
			if(mainMaze.isEmpty(i-1, j) == false){
				player.setILocation(i-1);
				player.setJLocation(j);
				labelGrid[i-1][j] = player;
				JLabel blank = new JLabel();
				blank.setBackground(Color.white);
    			blank.setOpaque(true);
    			blank.setMinimumSize(new Dimension(10,10));
    			blank.setPreferredSize(new Dimension(10,10));
    			blank.setMaximumSize(new Dimension(10,10));
				labelGrid[i][j] = blank;
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_DOWN){

			int i = player.getILocation();
			int j = player.getJLocation();
			if(mainMaze.isEmpty(i+1, j) == false){
				player.setILocation(i+1);
				player.setJLocation(j);
				labelGrid[i+1][j] = player;
				JLabel blank = new JLabel();
				blank.setBackground(Color.white);
    			blank.setOpaque(true);
    			blank.setMinimumSize(new Dimension(10,10));
    			blank.setPreferredSize(new Dimension(10,10));
    			blank.setMaximumSize(new Dimension(10,10));
				labelGrid[i][j] = blank;
				

			}
		}
		// If the user presses "q", quit
		if(e.getKeyCode() == KeyEvent.VK_Q){
			System.exit(0);
		}
		
		//Blank
		JLabel blank = new JLabel();
		blank.setBackground(Color.white);
		blank.setOpaque(true);
		blank.setMinimumSize(new Dimension(10,10));
		blank.setPreferredSize(new Dimension(10,10));
		blank.setMaximumSize(new Dimension(10,10));
		//Make it blank
		labelGrid[predator.getRow()][predator.getCol()] = blank;
		//Move predator
		predator.makeMove(player.getILocation(), player.getJLocation());
		//Put him in the new spot
		predPlayer.setILocation(predator.getRow());
		predPlayer.setJLocation(predator.getCol());
		labelGrid[predator.getRow()][predator.getCol()] = predPlayer;
		refreshMaze();
		
	}
	
	/**
	 * Re-draw the maze.
	 */
	public void refreshMaze(){
		this.removeAll();
		for(int a=0; a < height; a++){
        	for(int b=0; b < length; b++){
				//System.out.println(a + " " + b);
        			this.add(labelGrid[a][b]);
        		}
        	}
		revalidate();
		repaint();
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	
	
}
