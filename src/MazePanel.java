import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Timer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Image;

/**
 * The "MazePanel" class creates a handles the input/output for the maze. 
 * It also acts as the main holder for the grid which the maze is displayed by.
 * Displayed on top of GameBoardPanel it is used to break up the operations.
 * 
 * @author  Jack Holt
 * 			Jesse Moses
 * 			Nick Balnaves
 * 			Jordan Jacobson
 * 			Shiyuan Liang
 *
 */
public class MazePanel extends JPanel implements KeyListener {

	private static final long serialVersionUID = 1L;
	private JPanel homeGlassPane;
	// The height of the maze.
	private int height;
	// The length of the maze
	private int length;
	// The player
	private Player player;
	private Player player2;
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
	//Position of the triforce (Goal)
	private int goalX;
	private int goalY;
	//Check whether the timer has started
	private Boolean timerCheck;
	private Timer timer;
	//Enemy Speed
	private int enemySpeed;
	//Reference to the GameBoardPanel above for some operations
	private GameBoardPanel parentPanel;
	//Boolean value for whether we are in single or double mode
	private Boolean enemyOff;
	private Boolean pause = false;	
	//arraylist of maze
	private ItemGenerator newMazeItems;
	
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
		} else if(height <= 35){
			iconWidth = 20;
		}else if(height <= 40){
			iconWidth = 18;
		}else iconWidth = 15;
		
		if(enemySpeed2 == 0){
			enemyOff = true;
		} else enemyOff = false;
		
		//JLabel intitialisation
		initMaze();
	}

	/**
	 * Load a matrix with what to display, 
	 * to represent the maze.
	 */
	public void initMaze(){
		
		//some items here
		this.newMazeItems = new ItemGenerator(6, this.mainMaze);
	
		boolean check = false;
		int x = 0;
		int y = 0;
		player = new Player(0, 0, iconWidth, iconWidth, 0);
		this.predator = new Predator(mainMaze, iconWidth, iconWidth);
		/**
		 * This loops through each segment of the maze and checks if the spot is a wall or a blank 
		 * space. It outputs player1 in the top left blank space and player2 is generated in the
		 * bottom right space.
		 * Predator is also intitialised to start at the player1 starting point, however his movement
		 * is controlled in the MyTimerTask class
		 */
		for(int i=0; i < height; i++){
			for(int j=0; j < length; j++){
				if(mainMaze.isEmpty(i,j) == false){
					if(check == false){
						player = new Player(i, j, iconWidth, iconWidth, 0);
						player.setName(parentPanel.getGameWindow().getStatisticsPanel().getPlayer1()); 
						player.setMinimumSize(new Dimension(10,10));
						player.setPreferredSize(new Dimension(10,10));
						player.setMaximumSize(new Dimension(10,10));
						labelGrid[i][j] = player;
						predator.setStart(i, j);
						predPlayer = new Player(i, j, iconWidth, iconWidth, 2);
						predPlayer.setName(parentPanel.getGameWindow().getStatisticsPanel().getPlayer2()); 
						predPlayer.setMinimumSize(new Dimension(10,10));
						predPlayer.setPreferredSize(new Dimension(10,10));
						predPlayer.setMaximumSize(new Dimension(10,10));
						check = true;
					}
					else{
//						ImageIcon image = new ImageIcon("src/stone_path.png");
//						JLabel imageLabel = new JLabel(image);
//						imageLabel.setOpaque(true);
//						imageLabel.setMinimumSize(new Dimension(10,10));
//						imageLabel.setPreferredSize(new Dimension(10,10));
//						imageLabel.setMaximumSize(new Dimension(10,10));
						JLabel blank = new JLabel();
						labelGrid[i][j] = blank;
						x = i;
						y = j;
					}
				}else {

					ImageIcon image = new ImageIcon("src/hedge.png");
					JLabel imageLabel = new JLabel(image);
					imageLabel.setOpaque(true);
					imageLabel.setMinimumSize(new Dimension(10,10));
					imageLabel.setPreferredSize(new Dimension(10,10));
					imageLabel.setMaximumSize(new Dimension(10,10));

					labelGrid[i][j] = imageLabel;
					
				}
			}
		}
		//If we are in doublePlayer mode
		if(enemyOff == true){
			player2 = new Player(x, y-1, iconWidth, iconWidth, 2);
			player2.setMinimumSize(new Dimension(10,10));
			player2.setPreferredSize(new Dimension(10,10));
			player2.setMaximumSize(new Dimension(10,10));
			labelGrid[x][y-1] = player2;
			player2.setName(parentPanel.getGameWindow().getStatisticsPanel().getPlayer2()); 
		}
		//Finds a spot for the triforce to displayw
		int xPos, yPos;
		if (this.length%2==0){
//			System.out.println(length/2);
//			System.out.println(height/2);
			xPos = length/2;
			yPos = height/2;
		}else{
//			System.out.println((length+1)/2);
//			System.out.println((height+1)/2);
			xPos = (length-1)/2;
			yPos = (height-1)/2;
		}
		
		//Triforce is intitialised at the final spot
		//The player class is used as it allows for us to check a collision
		//as well as having the constructors for loading an image into our maze
		triForce = new Player(xPos, yPos, iconWidth, iconWidth, 1);
		triForce.setMinimumSize(new Dimension(10,10));
		triForce.setPreferredSize(new Dimension(10,10));
		triForce.setMaximumSize(new Dimension(10,10));

		/**
		 * Seperate loops which adds the items once the entire maze is drawn, the items are drawn
		 * over the top
		 */
		for(int i=0; i < height; i++){
			for(int j=0; j < length; j++){
				if (this.newMazeItems.ItemLocCheck (i, j) == true 
						&& mainMaze.isEmpty(i, j) == false){
					ImageIcon image = null;
					if (newMazeItems.getItemID(i, j) == 1){  
						image = new ImageIcon("src/item_fireball.png");
					}else if (newMazeItems.getItemID(i, j) == 2){
						image = new ImageIcon("src/item_freeze.png");
					}else if (newMazeItems.getItemID(i, j) == 3){
						image = new ImageIcon("src/item_mini.png");
					}else if (newMazeItems.getItemID(i, j) == 4) {
						image = new ImageIcon("src/item_teleport.png");
					}else if (newMazeItems.getItemID(i, j) == 5){
						image = new ImageIcon("src/item_bomb.png");
					}else if (newMazeItems.getItemID(i, j) == 6){
						image = new ImageIcon("src/item_star.png");
					}
					//System.out.println("it broke at: "+i+"  "+j);
					//System.out.println("the item id is "+newMazeItems.getItemID(i, j));
					Image image1 = image.getImage(); // transform it
					//rescale it to the maze
					Image newimg = image1.getScaledInstance(length-1, height-1,  
							java.awt.Image.SCALE_SMOOTH); 
					image = new ImageIcon(newimg);

					JLabel imageLabel = new JLabel(image);
					imageLabel.setOpaque(true);
					imageLabel.setMinimumSize(new Dimension(10,10));
					imageLabel.setPreferredSize(new Dimension(10,10));
					imageLabel.setMaximumSize(new Dimension(10,10));
					labelGrid[i][j] = imageLabel;
				}
			}
		}

		
		labelGrid[xPos][yPos] = triForce;
		goalX = xPos;
		goalY = yPos;
		
		/**
		 * This final loop is very important, once we have the labelGrid
		 * array completely filled we add all its elements to the JPanel
		 * It is important that they are added in the right order as we
		 * are using a gridlayout with the same dimensions as the array
		 */
		for(int i=0; i < height; i++){
			for(int j=0; j < length; j++){
				this.add(labelGrid[i][j]);
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if (e.getKeyChar() == 'h'){
			System.out.println("in");

			Instructions instruction = new Instructions();
			JOptionPane.showOptionDialog(null, instruction,
					"Instructions", JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.PLAIN_MESSAGE, null,
					new String[] { "Close" }, "default");
		}
	}


	/**
	 * If the user enders an arrow key, 
	 * move the player if possible
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if(pause == false){
			//If a key is pressed the timer starts, as well as the enemy
			if(timerCheck == false){
				startTimer();
				timerCheck = true;
			}
			this.movesMade++;
			System.out.println(this.movesMade);
			
			//Right arrow is pressed
			if(e.getKeyCode() == KeyEvent.VK_RIGHT){
				int i = player.getILocation();
				int j = player.getJLocation();
				//set the last direction to east
				player.setLastDirection(2);

				if(mainMaze.isEmpty(i, j+1) == false){
					
					//check if the next square is an item
					if (this.newMazeItems.ItemLocCheck(i,j+1)){
						//set attribute to the player and remove from maze
						System.out.println("*******************");
						System.out.println("found a item to pickup");
						System.out.println("*******************");
						//remove and add attribute
						player.setAttribute(this.newMazeItems.getItemID(i, j+1));
						System.out.println("the attribute set: "+this.newMazeItems.getItemID(i, j+1));
						System.out.println("********************");
						this.newMazeItems.popItem(i, j+1);	
					}

					
					//change graphics
					player.changeGraphicMovement();
					player.setILocation(i);
					player.setJLocation(j+1);
					//debug statement here 
					System.out.println("the player being printer @ x, y"+i+" "+(j+1));
					labelGrid[i][j+1] = player;
					JLabel blank = new JLabel();
					labelGrid[i][j] = blank;
					
					//set the last post
					player.setLastLoc(i,j);
				}
			}

			if(e.getKeyCode() == KeyEvent.VK_LEFT){
				//set the last direction to west
				player.setLastDirection(4);

				int i = player.getILocation();
				int j = player.getJLocation();
				if(mainMaze.isEmpty(i, j-1) == false){
					//check if the next square is an item
					if (this.newMazeItems.ItemLocCheck(i,j-1)){
						//set attribute to the player and remove from maze
						System.out.println("*******************");
						System.out.println("found a item to pickup");
						System.out.println("*******************");
						//remove and add attribute
						player.setAttribute(this.newMazeItems.getItemID(i, j-1));
						System.out.println("the attribute set: "+this.newMazeItems.getItemID(i, j-1));
						System.out.println("********************");
						this.newMazeItems.popItem(i, j-1);	
					}
	
					//change graphics
					player.changeGraphicMovement();
					player.setILocation(i);
					player.setJLocation(j-1);
					labelGrid[i][j-1] = player;
					JLabel blank = new JLabel();
					labelGrid[i][j] = blank;
					
					//set the last post
					player.setLastLoc(i,j);
				}
				refreshMaze();
			}

			if(e.getKeyCode() == KeyEvent.VK_UP){
				int i = player.getILocation();
				int j = player.getJLocation();
				//set last direction
				player.setLastDirection(1);

				if(mainMaze.isEmpty(i-1, j) == false){
					
					//check if the next square is an item
					if (this.newMazeItems.ItemLocCheck(i-1,j)){
						//set attribute to the player and remove from maze
						System.out.println("*******************");
						System.out.println("found a item to pickup");
						System.out.println("*******************");
						//remove and add attribute
						player.setAttribute(this.newMazeItems.getItemID(i-1, j));
						System.out.println("the attribute set: "+this.newMazeItems.getItemID(i-1, j));
						System.out.println("********************");
						this.newMazeItems.popItem(i-1, j);	
					}
					
					//change graphics
					player.changeGraphicMovement();
					player.setILocation(i-1);
					player.setJLocation(j);
					labelGrid[i-1][j] = player;
					JLabel blank = new JLabel();
					labelGrid[i][j] = blank;
					
					//set the last post
					player.setLastLoc(i,j);
	
				}
				refreshMaze();
			}

			if(e.getKeyCode() == KeyEvent.VK_DOWN){

				int i = player.getILocation();
				int j = player.getJLocation();

				//set last direction
				player.setLastDirection(3);

				if(mainMaze.isEmpty(i+1, j) == false){
					
					//check if the next square is an item
					if (this.newMazeItems.ItemLocCheck(i+1,j)){
						//set attribute to the player and remove from maze
						System.out.println("*******************");
						System.out.println("found a item to pickup");
						System.out.println("*******************");
						//remove and add attribute
						player.setAttribute(this.newMazeItems.getItemID(i+1, j));
						System.out.println("the attribute set: "+this.newMazeItems.getItemID(i+1, j));
						System.out.println("********************");
						this.newMazeItems.popItem(i+1, j);	
					}
					
					//change graphics
					player.changeGraphicMovement();
					player.setILocation(i+1);
					player.setJLocation(j);
					labelGrid[i+1][j] = player;
					JLabel blank = new JLabel();
					labelGrid[i][j] = blank;

					//set the last post
					player.setLastLoc(i,j);
				}
				refreshMaze();
			}


			//Options for 2 Players
			if(enemyOff == true){
				if(e.getKeyCode() == KeyEvent.VK_D){
					int i = player2.getILocation();
					int j = player2.getJLocation();
					//set the last direction to east
					player2.setLastDirection(2);


					if(mainMaze.isEmpty(i, j+1) == false){
						//change graphics
						player2.changeGraphicMovement();
						player2.setILocation(i);
						player2.setJLocation(j+1);
						labelGrid[i][j+1] = player2;
						JLabel blank = new JLabel();
						labelGrid[i][j] = blank;
					}
				}

				if(e.getKeyCode() == KeyEvent.VK_A){
					//set the last direction to west
					player2.setLastDirection(4);

					int i = player2.getILocation();
					int j = player2.getJLocation();
					if(mainMaze.isEmpty(i, j-1) == false){
						//change graphics
						player2.changeGraphicMovement();
						player2.setILocation(i);
						player2.setJLocation(j-1);
						labelGrid[i][j-1] = player2;
						JLabel blank = new JLabel();
						labelGrid[i][j] = blank;

					}
					refreshMaze();
				}

				if(e.getKeyCode() == KeyEvent.VK_W){

					int i = player2.getILocation();
					int j = player2.getJLocation();
					//set last direction
					player2.setLastDirection(1);

					if(mainMaze.isEmpty(i-1, j) == false){
						//change graphics
						player2.changeGraphicMovement();
						player2.setILocation(i-1);
						player2.setJLocation(j);
						labelGrid[i-1][j] = player2;
						JLabel blank = new JLabel();
						labelGrid[i][j] = blank;
					}
					refreshMaze();
				}

				if(e.getKeyCode() == KeyEvent.VK_S){

					int i = player2.getILocation();
					int j = player2.getJLocation();

					//set last direction
					player2.setLastDirection(3);

					if(mainMaze.isEmpty(i+1, j) == false){
						//change graphics
						player2.changeGraphicMovement();
						player2.setILocation(i+1);
						player2.setJLocation(j);
						labelGrid[i+1][j] = player2;
						JLabel blank = new JLabel();
						labelGrid[i][j] = blank;

					}
					refreshMaze();
				}
			}

			//if the user presses space, they shoot stuff and drop traps
			if (e.getKeyCode() == KeyEvent.VK_SPACE){
				
				ArrayList<Integer> lastPos = player.getLastLoc();
				int i = player.getILocation();
				int j = player.getJLocation();
				
				//create a new trap 
				if (player.getAttribute() == 1){

					int k = 1;
					switch (player.getlastDIR()){
						case(1):
							//shoot a chain of 3 fire balls in the last location
							//while empty and less than 3
							while (!mainMaze.isEmpty(i-k, j) && k<3){
								

								ImageIcon image = new ImageIcon("src/trap_fireballU.png");
								//turn mario into a snowman
								
								//drawing the freeze trap
								Image image1 = image.getImage(); // transform it
								//rescale it to the maze
								Image newimg = image1.getScaledInstance(length-1, height-1,  
										java.awt.Image.SCALE_SMOOTH); 
								image = new ImageIcon(newimg);

								JLabel imageLabel = new JLabel(image);
								imageLabel.setOpaque(true);
								imageLabel.setMinimumSize(new Dimension(10,10));
								imageLabel.setPreferredSize(new Dimension(10,10));
								imageLabel.setMaximumSize(new Dimension(10,10));
								labelGrid[i-k][j] = imageLabel;
								k++;
							}
							break;
						case(2):
							while (!mainMaze.isEmpty(i, j+k) && k<3){					
								ImageIcon image = new ImageIcon("src/trap_fireballR.png");
								//turn mario into a snowman				
								//drawing the freeze trap
								Image image1 = image.getImage(); // transform it
								//rescale it to the maze
								Image newImg = image1.getScaledInstance(length-1, height-1,  
										java.awt.Image.SCALE_SMOOTH); 
								image = new ImageIcon(newImg);

								JLabel imageLabel = new JLabel(image);
								imageLabel.setOpaque(true);
								imageLabel.setMinimumSize(new Dimension(10,10));
								imageLabel.setPreferredSize(new Dimension(10,10));
								imageLabel.setMaximumSize(new Dimension(10,10));
								labelGrid[i][j+k] = imageLabel;
								k++;
							}
							
							break;
						case(3):
							
							while (!mainMaze.isEmpty(i+k, j) && k<3){
								
								ImageIcon image = new ImageIcon("src/trap_fireballD.png");
								//turn mario into a snowman
								
								//drawing the freeze trap
								Image image1 = image.getImage(); // transform it
								//rescale it to the maze
								Image newimg = image1.getScaledInstance(length-1, height-1,  
										java.awt.Image.SCALE_SMOOTH); 
								image = new ImageIcon(newimg);

								JLabel imageLabel = new JLabel(image);
								imageLabel.setOpaque(true);
								imageLabel.setMinimumSize(new Dimension(10,10));
								imageLabel.setPreferredSize(new Dimension(10,10));
								imageLabel.setMaximumSize(new Dimension(10,10));
								labelGrid[i+k][j] = imageLabel;
								k++;
							}
							break;
						case(4):
							
							while (!mainMaze.isEmpty(i, j-k) && k<3){		

								ImageIcon image = new ImageIcon("src/trap_fireballL.png");
								//turn mario into a snowman
								
								//drawing the freeze trap
								Image image1 = image.getImage(); // transform it
								//rescale it to the maze
								Image newimg = image1.getScaledInstance(length-1, height-1,  
										java.awt.Image.SCALE_SMOOTH); 
								image = new ImageIcon(newimg);

								JLabel imageLabel = new JLabel(image);
								imageLabel.setOpaque(true);
								imageLabel.setMinimumSize(new Dimension(10,10));
								imageLabel.setPreferredSize(new Dimension(10,10));
								imageLabel.setMaximumSize(new Dimension(10,10));
								labelGrid[i][j-k] = imageLabel	;
								k++;
							}

							break;
					}
					
				}else if (player.getAttribute() == 2){
					//decrement the attribute here

					ImageIcon image = new ImageIcon("src/trap_freeze.png");
					//turn mario into a snowman
					
					//drawing the freeze trap
					Image image1 = image.getImage(); // transform it
					//rescale it to the maze
					Image newimg = image1.getScaledInstance(length-1, height-1,  
							java.awt.Image.SCALE_SMOOTH); 
					image = new ImageIcon(newimg);

					JLabel imageLabel = new JLabel(image);
					imageLabel.setOpaque(true);
					imageLabel.setMinimumSize(new Dimension(10,10));
					imageLabel.setPreferredSize(new Dimension(10,10));
					imageLabel.setMaximumSize(new Dimension(10,10));
					labelGrid[lastPos.get(0)][lastPos.get(1)] = imageLabel;
				}else if (player.getAttribute () == 4){
					//teleport two squares
					//System.out.println("the speed booster is in play");
					//check last direction, cases then check squares then teleport

						
						System.out.println("the last location is: "+i+" "+j);
					switch(player.getlastDIR()){
						case(1): //north
				
						System.out.println("the new location is: "+i+" "+j);
							if(mainMaze.isEmpty(i-2, j) == false){
								//change graphics
								player.setILocation(i-2);
								player.setJLocation(j);
								labelGrid[i-2][j] = player;
								JLabel blank = new JLabel();
								labelGrid[i][j] = blank;

								//set the last post
								player.setLastLoc(i-1,j);
							}
							break;
						case(2): //east
		
						System.out.println("the new location is: "+i+" "+j);
							if(mainMaze.isEmpty(i, j+2) == false){
								//change graphics
								player.setILocation(i);
								player.setJLocation(j+2);
								labelGrid[i][j+2] = player;
								JLabel blank = new JLabel();
								labelGrid[i][j] = blank;

								//set the last post
								player.setLastLoc(i,j+1);
							}
							break;
						case(3): //south
					
						System.out.println("the new location is: "+i+" "+j);
							if(mainMaze.isEmpty(i+2, j) == false){
								//change graphics
								player.setILocation(i+2);
								player.setJLocation(j);
								labelGrid[i+2][j] = player;
								JLabel blank = new JLabel();
								labelGrid[i][j] = blank;

								//set the last post
								player.setLastLoc(i+1,j);	
							}
							break;
						case(4): //west
	
						System.out.println("the new location is: "+i+" "+j);
							if(mainMaze.isEmpty(i, j-2) == false){
								//change graphics
								player.changeGraphicMovement();
								player.setILocation(i);
								player.setJLocation(j-2);
								labelGrid[i][j-2] = player;
								JLabel blank = new JLabel();
								labelGrid[i][j] = blank;

								//set the last post
								player.setLastLoc(i,j-1);
							}
							break;
					}
					
					
				}else if (player.getAttribute() == 5){
					//add it to the trap list
					
					
					//drop a trap bomb
					
					ImageIcon image = new ImageIcon("src/trap_bomb.png");
					
					//drawing the freeze trap
					Image image1 = image.getImage(); // transform it
					//rescale it to the maze
					Image newimg = image1.getScaledInstance(length-1, height-1,  
							java.awt.Image.SCALE_SMOOTH); 
					image = new ImageIcon(newimg);

					JLabel imageLabel = new JLabel(image);
					imageLabel.setOpaque(true);
					imageLabel.setMinimumSize(new Dimension(10,10));
					imageLabel.setPreferredSize(new Dimension(10,10));
					imageLabel.setMaximumSize(new Dimension(10,10));
					labelGrid[lastPos.get(0)][lastPos.get(1)] = imageLabel;
					
					
				}
				refreshMaze();

			}

			// If the user presses "q", quit
			if(e.getKeyCode() == KeyEvent.VK_Q){
				System.exit(0);
			}

			// If the player and triforce location lines up displayEndGame
			if(player.getILocation() == goalX && player.getJLocation() == goalY){
				parentPanel.displayEndGame();
			}
			
			// Same thing for 2 player mode
			if(enemyOff == true){
				if(player2.getILocation() == goalX && player2.getJLocation() == goalY){
					parentPanel.displayEndGame();
				}
			}
			refreshMaze();
		}
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

	/**
	 * Starts the timner which controls the time elapsed as well as the enemy
	 */
	public void startTimer(){
		timer.schedule(new MyTimerTask(predator, player, predPlayer, labelGrid, this, enemySpeed, parentPanel, enemyOff), 0, enemySpeed);

	}


}
