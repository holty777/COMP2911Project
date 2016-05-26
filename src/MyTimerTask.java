import java.awt.Dimension;
import java.util.TimerTask;

import javax.swing.JLabel;

/**
 * This class is a timer which keeps track of the time of the game elapsed
 * and also the speed at which the predator (Mario) moves around the maze
 * @author jhol149
 *
 */
public class MyTimerTask extends TimerTask {

	private Predator predator;
	private Player player;
	private Player predPlayer;
	private MazePanel mazePanel;
	private JLabel[][] labelGrid;
	private int speed;
	private int timeElapsed;
	private boolean finished = false;
	GameBoardPanel parentPanel;
	private boolean enemyOff = false;

	/**
	 * 
	 * @param predator		Mario
	 * @param player 		The drawn position of our main character
	 * @param predPlayer	The drawn position of Mario
	 * @param labelGrid		An array with all the maze pictures
	 * @param mazePanel		The JPanel which holds the maze
	 * @param speed			Speed at which Mario chases us
	 * @param parentPanel	The JPanel which holds the mazePanel
	 * @param enemyOff 
	 */
	public MyTimerTask(Predator predator, Player player, Player predPlayer, JLabel[][] labelGrid, MazePanel mazePanel, int speed, 
			GameBoardPanel parentPanel, Boolean enemyOff) {
		this.predator = predator;
		this.player = player;
		this.mazePanel = mazePanel;
		this.predPlayer = predPlayer;
		this.labelGrid = labelGrid;
		this.speed = speed;
		timeElapsed = 0;
		this.parentPanel = parentPanel;
		this.enemyOff = enemyOff;
	}
	
	/**
	 * Updates the predators position as well as the timer.
	 */
	@Override
	public void run() {
		if (this.finished) return;
		parentPanel.getGameWindow().getStatisticsPanel().setTimeLabel(timeElapsed/1000);
		if(timeElapsed > 2000 && enemyOff == false){
			JLabel blank = new JLabel();
			//blank.setBackground(Color.white);
			blank.setOpaque(true);
			blank.setMinimumSize(new Dimension(10,10));
			blank.setPreferredSize(new Dimension(10,10));
			blank.setMaximumSize(new Dimension(10,10));
			//Make it blank
			labelGrid[predator.getRow()][predator.getCol()] = blank;
			//Move predator
			predator.makeMove(player.getILocation(), player.getJLocation());
			predPlayer.setLastDirection(predator.getPreviousDirection());
			predPlayer.changeGraphicMovement();
			//Put him in the new spot
			predPlayer.setILocation(predator.getRow());
			predPlayer.setJLocation(predator.getCol());
			labelGrid[predator.getRow()][predator.getCol()] = predPlayer;
			mazePanel.refreshMaze();
		}
		if (predator.won()){
			this.finished = true;
		};
		timeElapsed = timeElapsed + speed;
		System.out.println("Time Elapsed = " + timeElapsed/1000 );
	}

}
