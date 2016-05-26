import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
 * @author  Jack Holt
 * 			Jesse Moses
 * 			Nick Balnaves
 * 			Jordan Jacobson
 * 			Shiyuan Liang
 *
 */
public class MazePanel extends JPanel implements MouseListener, KeyListener {

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
	private int goalX;
	private int goalY;
	private Boolean timerCheck;
	private Timer timer;
	private int enemySpeed;
	private GameBoardPanel parentPanel;
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
				}else {

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
		if(enemyOff == true){
			player2 = new Player(x, y-1, iconWidth, iconWidth, 2);
			player2.setMinimumSize(new Dimension(10,10));
			player2.setPreferredSize(new Dimension(10,10));
			player2.setMaximumSize(new Dimension(10,10));
			labelGrid[x][y-1] = player2;
			player2.setName(parentPanel.getGameWindow().getStatisticsPanel().getPlayer2()); 
		}
		int xPos, yPos;
		if (this.length%2==0){
			System.out.println("HELLO!!");
			System.out.println(length/2);
			System.out.println(height/2);
			xPos = length/2;
			yPos = height/2;
		}else{
			System.out.println("HELLO");
			System.out.println((length+1)/2);
			System.out.println((height+1)/2);
			xPos = (length-1)/2;
			yPos = (height-1)/2;
		}
		triForce = new Player(xPos, yPos, iconWidth, iconWidth, 1);
		triForce.setMinimumSize(new Dimension(10,10));
		triForce.setPreferredSize(new Dimension(10,10));
		triForce.setMaximumSize(new Dimension(10,10));

		
		for(int i=0; i < height; i++){
			for(int j=0; j < length; j++){
				if (this.newMazeItems.ItemLocCheck (i, j) == true 
						&& mainMaze.isEmpty(i, j) == false){
					


					//declare image

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

			Instructions instruction = new Instructions();
			JOptionPane.showOptionDialog(null, instruction,
					"Instructions", JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.PLAIN_MESSAGE, null,
					new String[] { "Close" }, "default");
		}
		//display();
	}


	/**
	 * If the user enders an arrow key, 
	 * move the player if possible
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if(pause == false){
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
					//	blank.setBackground(Color.white);
					blank.setOpaque(true);
					blank.setMinimumSize(new Dimension(10,10));
					blank.setPreferredSize(new Dimension(10,10));
					blank.setMaximumSize(new Dimension(10,10));
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
					//blank.setBackground(Color.white);
					blank.setOpaque(true);
					blank.setMinimumSize(new Dimension(10,10));
					blank.setPreferredSize(new Dimension(10,10));
					blank.setMaximumSize(new Dimension(10,10));
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
					//blank.setBackground(Color.white);
					blank.setOpaque(true);
					blank.setMinimumSize(new Dimension(10,10));
					blank.setPreferredSize(new Dimension(10,10));
					blank.setMaximumSize(new Dimension(10,10));
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
					//blank.setBackground(Color.white);
					blank.setOpaque(true);
					blank.setMinimumSize(new Dimension(10,10));
					blank.setPreferredSize(new Dimension(10,10));
					blank.setMaximumSize(new Dimension(10,10));
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
						//	blank.setBackground(Color.white);
						blank.setOpaque(true);
						blank.setMinimumSize(new Dimension(10,10));
						blank.setPreferredSize(new Dimension(10,10));
						blank.setMaximumSize(new Dimension(10,10));
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
						//blank.setBackground(Color.white);
						blank.setOpaque(true);
						blank.setMinimumSize(new Dimension(10,10));
						blank.setPreferredSize(new Dimension(10,10));
						blank.setMaximumSize(new Dimension(10,10));
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
						//blank.setBackground(Color.white);
						blank.setOpaque(true);
						blank.setMinimumSize(new Dimension(10,10));
						blank.setPreferredSize(new Dimension(10,10));
						blank.setMaximumSize(new Dimension(10,10));
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
						//blank.setBackground(Color.white);
						blank.setOpaque(true);
						blank.setMinimumSize(new Dimension(10,10));
						blank.setPreferredSize(new Dimension(10,10));
						blank.setMaximumSize(new Dimension(10,10));
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
								labelGrid[i-k][j] = imageLabel	;
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
								Image newimg = image1.getScaledInstance(length-1, height-1,  
										java.awt.Image.SCALE_SMOOTH); 
								image = new ImageIcon(newimg);

								JLabel imageLabel = new JLabel(image);
								imageLabel.setOpaque(true);
								imageLabel.setMinimumSize(new Dimension(10,10));
								imageLabel.setPreferredSize(new Dimension(10,10));
								imageLabel.setMaximumSize(new Dimension(10,10));
								labelGrid[i][j+k] = imageLabel	;
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
								//blank.setBackground(Color.white);
								blank.setOpaque(true);
								blank.setMinimumSize(new Dimension(10,10));
								blank.setPreferredSize(new Dimension(10,10));
								blank.setMaximumSize(new Dimension(10,10));
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
								//blank.setBackground(Color.white);
								blank.setOpaque(true);
								blank.setMinimumSize(new Dimension(10,10));
								blank.setPreferredSize(new Dimension(10,10));
								blank.setMaximumSize(new Dimension(10,10));
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
								//blank.setBackground(Color.white);
								blank.setOpaque(true);
								blank.setMinimumSize(new Dimension(10,10));
								blank.setPreferredSize(new Dimension(10,10));
								blank.setMaximumSize(new Dimension(10,10));
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
								//blank.setBackground(Color.white);
								blank.setOpaque(true);
								blank.setMinimumSize(new Dimension(10,10));
								blank.setPreferredSize(new Dimension(10,10));
								blank.setMaximumSize(new Dimension(10,10));
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

			if(player.getILocation() == goalX && player.getJLocation() == goalY){
				parentPanel.displayEndGame();

			}
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

	public void startTimer(){
		timer.schedule(new MyTimerTask(predator, player, predPlayer, labelGrid, this, enemySpeed, parentPanel, enemyOff), 0, enemySpeed);

	}


}
