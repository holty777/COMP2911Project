import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
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

	private JLabel player1;
	private JLabel player2;
	private JLabel msg;
	private JLabel modeDescription;
	private JLabel timeLabel;
	Font customFont = null;

	/**
	 * The "GameStatisticsPanel" constructor.
	 * @param gw	The GameWindow, a class containing information 
	 * 				about the display of the whole screen.
	 * @throws IOException 
	 */
	public GameStatisticsPanel(GameWindow gw) throws IOException {
		gameWindow = gw;
		//SUPER MARIO FONT
		try {
		    //create the font to use. Specify the size!
		    customFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/SuperMario256.ttf")).deriveFont(Font.ROMAN_BASELINE, 25f);
		    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		    //register the font
		    ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/SuperMario256.ttf")));
		    
		} catch (IOException e) {
		    e.printStackTrace();
		} catch(FontFormatException e) {
		    e.printStackTrace();
		}
		setBackground(Color.decode("#cccccc"));

		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.weightx = 0;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.FIRST_LINE_START;
		gc.gridx = 0;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.CENTER;
		
		//Picture at top of panel (LOGO)
		Image img = ImageIO.read(new File("src/MenuPic.png"));
		ImageIcon imgI = new ImageIcon(img);
		JLabel menu = new JLabel();
		menu.setIcon(imgI);
		
		// Time Elapsed Label
		timeLabel = new JLabel("Time Elapsed: ");
		timeLabel.setFont(customFont);
		gc.gridy = 1;
		add(timeLabel, gc);
		gc.gridy = 2;
		add(menu);
		
		// Players names
		player1 = new JLabel();
		gc.gridy = 3;
		add(player1, gc);
		gc.gridy = 4;
		player2 = new JLabel();
		add(player2, gc);

		// Message to the game (started, won, lost etc)
		gc.anchor = GridBagConstraints.SOUTH;
		msg = new JLabel();
		msg.setFont(customFont);
		gc.gridy = 5;
		add(msg, gc);

		

		modeDescription = new JLabel();
		gc.gridy = 6;
		add(modeDescription, gc);
		
		//Restart Button
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

		//Pause Button
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
		player1.setHorizontalAlignment(JLabel.RIGHT);
		player1.setFont(customFont);
		player1.setText("Player 1 : " + p1);
		player1.setName(p1);
		player1.setForeground(Color.decode("#055e05"));
		player2.setForeground(Color.decode("#4ed504"));
		player2.setFont(customFont);
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
	public void displayEndGame(String winner) {
		if (winner == null) {
			msg.setText("Null wins");
		} else {
			if (winner.toLowerCase().equals("you")){
				msg.setText(winner + " win!");
			} else {
				msg.setText(winner + " wins!");
			}
			msg.setForeground(Color.RED);
		}
		repaint();
	}

	/**
	 * Display the winner at the end of the game for a two player game.
	 * @param i	The winner number.
	 */
	public void displayEndGame(int i) {
		if(i == 1)
			msg.setText(player1.getName() + " wins!");
		else msg.setText(player2.getName() + " wins!");
	}

	/**
	 * Store the amount of time that has passed.
	 * @param i	The elapsed time in seconds.
	 */
	public void setTimeLabel(int i){
		timeLabel.setText("Time Elapsed: " + i);
	}
	
	/**
	 * A getter method for player 1.
	 * @return	Player 1
	 */
	public String getPlayer1(){
		return player1.getText();
	}
	
	/**
	 * A getter method for player 2.
	 * @return	Player 2
	 */
	public String getPlayer2(){
		return player2.getText();
	}
}
