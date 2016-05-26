import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Timer;

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
	// The height of the maze.
	private int height;
	// The length of the maze
	private int length;
	// The player
	private Player player;
	// The target 
	private Player triForce;
	// Icon Width
	private int iconWidth;
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
	private int goalX;
	private int goalY;
	private Boolean timerCheck;
	private Timer timer;
	private int enemySpeed;
	private GameBoardPanel parentPanel;

	/**
	 * The "MazePanel" constructor.
	 * @param height	The height of the maze.
	 * @param length	The length/width of the maze.
	 * @param notification 
	 * @param gameBoardPanel 
	 * @param enemySpeed2 
	 */
	public MazePanel(int height, int length, GameBoardPanel gameBoardPanel, int enemySpeed2){
		if(height > 5){
			height --;
		}
		if(length > 5){
			length --;
		}
		this.movesMade = 0;
		goalX = 0;
		goalY = 0;

		labelGrid = new JLabel[height][length];
		this.height = height;
		this.length = length;
		mainMaze = new AlphaMaze(height ,length );
		grid = new GridLayout(height ,length);
		this.setLayout(grid);
		homeGlassPane = new JPanel();
		homeGlassPane.setPreferredSize(new Dimension(700, 700));		
		timerCheck = false;
		timer = new Timer();
		this.enemySpeed = 500 - enemySpeed2 * 7;
		this.parentPanel = gameBoardPanel;
		if(height <= 10){
			iconWidth = 70;
		} else if(height <= 20){
			iconWidth = 35;
		} else if(height <= 30){
			iconWidth = 25;
		} else if(height <= 40){
			iconWidth = 15;
		} else iconWidth = 5;
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
		player = new Player(0, 0, iconWidth, iconWidth, 0);
		this.predator = new Predator(mainMaze, iconWidth, iconWidth);
		for(int i=0; i < height; i++){
			for(int j=0; j < length; j++){
				if(mainMaze.isEmpty(i,j) == false){
					if(check == false){
						player = new Player(i, j, iconWidth, iconWidth, 0);
						player.setMinimumSize(new Dimension(10,10));
						player.setPreferredSize(new Dimension(10,10));
						player.setMaximumSize(new Dimension(10,10));
						labelGrid[i][j] = player;
						predator.setStart(i, j);
						predPlayer = new Player(i, j, iconWidth, iconWidth, 2);
						predPlayer.setMinimumSize(new Dimension(10,10));
						predPlayer.setPreferredSize(new Dimension(10,10));
						predPlayer.setMaximumSize(new Dimension(10,10));
						//this.add(player);
						check = true;
					}
					else{
						JLabel full = new JLabel();
						//full.setBackground(Color.white);
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

		//labelGrid[x][1] = predPlayer;

		triForce = new Player(x/2-1, y/2-1, iconWidth, iconWidth, 1);
		triForce.setMinimumSize(new Dimension(10,10));
		triForce.setPreferredSize(new Dimension(10,10));
		triForce.setMaximumSize(new Dimension(10,10));
		labelGrid[x/2-1][y/2-1] = triForce;
		goalX = x/2-1;
		goalY = y/2-1;
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
		if(timerCheck == false){
			startTimer();
			timerCheck = true;
		}
		this.movesMade++;
		System.out.println(this.movesMade);
		if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			int i = player.getILocation();
			int j = player.getJLocation();
			//set the last direction to east
			player.setLastDirection(2);


			if(mainMaze.isEmpty(i, j+1) == false){
				//change graphics
				player.changeGraphicMovement();
				player.setILocation(i);
				player.setJLocation(j+1);
				labelGrid[i][j+1] = player;
				JLabel blank = new JLabel();
				//	blank.setBackground(Color.white);
				blank.setOpaque(true);
				blank.setMinimumSize(new Dimension(10,10));
				blank.setPreferredSize(new Dimension(10,10));
				blank.setMaximumSize(new Dimension(10,10));
				labelGrid[i][j] = blank;
			}
		}

		if(e.getKeyCode() == KeyEvent.VK_LEFT){
			//set the last direction to west
			player.setLastDirection(4);

			int i = player.getILocation();
			int j = player.getJLocation();
			if(mainMaze.isEmpty(i, j-1) == false){
				//change graphics
				player.changeGraphicMovement();
				player.setILocation(i);
				player.setJLocation(j-1);
				labelGrid[i][j-1] = player;
				JLabel blank = new JLabel();
				//blank.setBackground(Color.white);
				blank.setOpaque(true);
				blank.setMinimumSize(new Dimension(10,10));
				blank.setPreferredSize(new Dimension(10,10));
				blank.setMaximumSize(new Dimension(10,10));
				labelGrid[i][j] = blank;

			}
			refreshMaze();
		}

		if(e.getKeyCode() == KeyEvent.VK_UP){

			int i = player.getILocation();
			int j = player.getJLocation();
			//set last direction
			player.setLastDirection(1);

			if(mainMaze.isEmpty(i-1, j) == false){
				//change graphics
				player.changeGraphicMovement();
				player.setILocation(i-1);
				player.setJLocation(j);
				labelGrid[i-1][j] = player;
				JLabel blank = new JLabel();
				//blank.setBackground(Color.white);
				blank.setOpaque(true);
				blank.setMinimumSize(new Dimension(10,10));
				blank.setPreferredSize(new Dimension(10,10));
				blank.setMaximumSize(new Dimension(10,10));
				labelGrid[i][j] = blank;
			}
			refreshMaze();
		}

		if(e.getKeyCode() == KeyEvent.VK_DOWN){

			int i = player.getILocation();
			int j = player.getJLocation();

			//set last direction
			player.setLastDirection(3);

			if(mainMaze.isEmpty(i+1, j) == false){
				//change graphics
				player.changeGraphicMovement();
				player.setILocation(i+1);
				player.setJLocation(j);
				labelGrid[i+1][j] = player;
				JLabel blank = new JLabel();
				//blank.setBackground(Color.white);
				blank.setOpaque(true);
				blank.setMinimumSize(new Dimension(10,10));
				blank.setPreferredSize(new Dimension(10,10));
				blank.setMaximumSize(new Dimension(10,10));
				labelGrid[i][j] = blank;


			}
			refreshMaze();
		}
		//if the user presses space, they shoot stuff
		if (e.getKeyCode() == KeyEvent.VK_SPACE){
			int i = player.getILocation();
			int j = player.getJLocation();
			int direction = player.getlastDIR();

			if (direction == 1){
				if(mainMaze.isEmpty(i-1, j) == false){
					//draw a fire ball/object
				}
			}else if (direction == 2){
				if(mainMaze.isEmpty(i, j-1) == false){
					//draw a fire ball/object
				}
			}else if (direction == 3){
				if(mainMaze.isEmpty(i+1, j) == false){
					//draw a fire ball or object
				}
			}else if (direction == 4){
				if(mainMaze.isEmpty(i, j+1) == false){
					//draw a fire ball/object
				}
			}
			refreshMaze();

		}



		// If the user presses "q", quit
		if(e.getKeyCode() == KeyEvent.VK_Q){
			System.exit(0);
		}

		if(player.getILocation() == goalX && player.getJLocation() == goalY){
			parentPanel.displayEndGame(1);
		}
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
		labelGrid[goalX][goalY] = triForce;
		revalidate();
		repaint();
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void startTimer(){
		timer.schedule(new MyTimerTask(predator, player, predPlayer, labelGrid, this, enemySpeed, parentPanel), 0, enemySpeed);

	}


}
