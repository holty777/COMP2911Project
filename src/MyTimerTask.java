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
	
	public MyTimerTask(Predator predator, Player player, Player predPlayer, JLabel[][] labelGrid, MazePanel mazePanel) {
		this.predator = predator;
		this.player = player;
		this.mazePanel = mazePanel;
		this.predPlayer = predPlayer;
		this.labelGrid = labelGrid;
	}

	@Override
	public void run() {

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
		mazePanel.refreshMaze();
	}

}
