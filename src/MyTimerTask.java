import java.awt.Color;
import java.awt.Dimension;
import java.util.TimerTask;

import javax.swing.JLabel;


public class MyTimerTask extends TimerTask {

	private Predator predator;
	private Player player;
	private Player predPlayer;
	private MazePanel mazePanel;
	private JLabel[][] labelGrid;
	private int speed;
	private int timeElapsed;
	GameBoardPanel parentPanel;
	
	public MyTimerTask(Predator predator, Player player, Player predPlayer, JLabel[][] labelGrid, MazePanel mazePanel, int speed, 
			GameBoardPanel parentPanel) {
		this.predator = predator;
		this.player = player;
		this.mazePanel = mazePanel;
		this.predPlayer = predPlayer;
		this.labelGrid = labelGrid;
		this.speed = speed;
		timeElapsed = 0;
		this.parentPanel = parentPanel;
		}

	@Override
	public void run() {
		parentPanel.getGameWindow().getStatisticsPanel().setTimeLabel(timeElapsed/1000);
		if(timeElapsed > 2000){
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
			//Put him in the new spot
			predPlayer.setILocation(predator.getRow());
			predPlayer.setJLocation(predator.getCol());
			labelGrid[predator.getRow()][predator.getCol()] = predPlayer;
			mazePanel.refreshMaze();
		}
		timeElapsed = timeElapsed + speed;
		System.out.println("Time Elapsed = " + timeElapsed/1000 );
	}

}
