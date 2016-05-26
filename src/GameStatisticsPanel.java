import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * The "GameStatisticsPanel" class extend JPanel and 
 * handles the statistics panel on the left of the maze.
 * @author  Jack Holt
 * 			Jesse Moses
 * 			Nick Balnaves
 * 			Jordan Jacobson
 * 			Shiyuan Liang 
 *
 */
public class GameStatisticsPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private GameWindow gameWindow;
	private MazePuzzleGame mainGame;
	private GameEngine gameEngine;

	private JLabel player1;
	private JLabel player2;
	private JLabel msg;
	private JLabel modeLabel;
	private JLabel modeDescription;
	private JLabel timeLabel;

	/**
	 * The "GameStatisticsPanel" constructor.
	 * @param gw	The GameWindow, a class containing information 
	 * 				about the display of the whole screen.
	 * @param mg	The MazePuzzelGame, a class containing information
	 * 				about the display of the maze.
	 * @throws IOException 
	 */
	public GameStatisticsPanel(GameWindow gw, MazePuzzleGame mg) throws IOException {
		mainGame = mg;
		gameWindow = gw;
		gameEngine = mainGame.getGameEngine();

		//setBackground(Color.WHITE);

		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.FIRST_LINE_START;
		gc.gridx = 0;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.WEST;
		Image img = ImageIO.read(new File("src/MenuPic.png"));
		ImageIcon imgI = new ImageIcon(img);
		JLabel menu = new JLabel();
		menu.setIcon(imgI);
		//		// Button for Single Player
		gc.gridx = 0;
		gc.gridy = 0;
		add(menu);
		gc.gridy = 1;
		// who's turn
		player1 = new JLabel("Player 1: ");
		add(player1, gc);
		gc.gridy = 2;
		player2 = new JLabel("Player 2: ");
		add(player2, gc);

		gc.anchor = GridBagConstraints.SOUTH;
		msg = new JLabel();
		msg.setFont(new Font("Arial", Font.BOLD, 20));
		gc.gridy = 3;
		add(msg, gc);

		timeLabel = new JLabel("Time Elapsed: ");
		timeLabel.setFont(new Font("Arial", Font.BOLD, 20));

		gc.gridy = 4;
		add(timeLabel, gc);

		modeDescription = new JLabel();
		gc.gridy = 5;
		add(modeDescription, gc);

		JButton restartGameButton = new JButton("Restart");
		restartGameButton.setContentAreaFilled(false);
		restartGameButton.setFocusPainted(false);
		restartGameButton.setBorderPainted(false);
		restartGameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				gameWindow.getGameBoardPanel().restartNewGame();
				//need a refreshMaze from mazepanel
			}
		});

		gc.gridy = 6;
		add(restartGameButton, gc);

		JButton pauseGameButton = new JButton("Pause");
		pauseGameButton.setContentAreaFilled(false);
		pauseGameButton.setFocusPainted(false);
		pauseGameButton.setBorderPainted(false);
		pauseGameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				gameWindow.getGameBoardPanel().pauseGame();
				//need a refreshMaze from mazepanel
			}
		});

		gc.gridy = 8;
		add(pauseGameButton, gc);

	}

	/**
	 * Set the player names in the statistics panel.
	 * @param p1	Player 1
	 * @param p2	Player 2
	 */
	public void setPlayerNames(String p1, String p2) {
		//uncommenting crashes 
		player1.setFont(new Font("Arial", Font.BOLD, 20));
		player1.setText("Player 1 : " + p1);
		player1.setName(p1);
		player2.setFont(new Font("Arial", Font.BOLD, 20));
		player2.setText("Player 2 : " + p2);
		player2.setName(p2);
		msg.setText("Game Started.");
		msg.setForeground(Color.BLUE);
	}
	/**
	 * Set whose turn it is.
	 */
	public void setWhosTurn() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}

	}

	/**
	 * Display the winner at the end of the game.
	 * @param winner	The winner.
	 */
	public void displayEndGame(Player winner) {
		if (winner == null) {
			msg.setText("Null wins");
		} else {
			msg.setText(winner.getName() + " wins!");
			if (gameEngine.getCurrPlayerIndex() == 0) {
				msg.setForeground(new Color(255, 211, 2));
			} else {
				msg.setForeground(Color.RED);
			}
		}
		repaint();
	}

	public void displayEndGame(int i) {
		if(i == 1)
			msg.setText(player1.getName() + " wins!");
		else msg.setText(player2.getName() + " wins!");
	}

	public void setTimeLabel(int i){
		timeLabel.setText("Time Elapsed: " + i);
	}
}
