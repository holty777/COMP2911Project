import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


/**
 * The "DoublePlayersMenu" class handles all operations 
 * regarding the double players menu. It allows each 
 * player to enter their name and start the game.
 * @author  Jack Holt
 * 			Jesse Moses
 * 			Nick Balnaves
 * 			Jordan Jacobson
 * 			Shiyuan Liang
 *
 */
public class DoublePlayersMenu extends JPanel {
	private static final long serialVersionUID = 1L;

	private MazePuzzleGame mainGame;

	/**
	 * The constructor for the "DoublePlayersMenu" class.
	 * @param mg	A high level overview of the current maze status.
	 */
	public DoublePlayersMenu(MazePuzzleGame mg) {
		mainGame = mg;

		// Set button layout.
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.weightx = 0;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.BOTH;

		// user name input
		gc.gridx = 0;
		gc.gridy = 0;
		JLabel username = new JLabel("Player 1's name:  ");
		add(username, gc);
		gc.gridx = 1;
		gc.gridy = 0;
		final JTextField player1NameField = new JTextField("", 10);
		add(player1NameField, gc);

		gc.gridx = 0;
		gc.gridy = 1;
		JLabel username2 = new JLabel("Player 2's name:  ");
		add(username2, gc);
		gc.gridx = 1;
		gc.gridy = 1;
		final JTextField player2NameField = new JTextField("", 10);
		add(player2NameField, gc);

		// start game button
		gc.gridx = 1;
		gc.gridy = 2;
		JButton startGame = new JButton("Start Game");
		startGame.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				GameWindow doublePlayersWindow;
				try {
					doublePlayersWindow = new GameWindow(mainGame, "Double Players Game");
					doublePlayersWindow.startDoublePlayersGame(player1NameField.getText(), player2NameField.getText(), 30);
					doublePlayersWindow.setVisible(true);
					mainGame.setVisibility(false);
					MenuPanel.closeJOptionPanel();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		add(startGame, gc);
	}

}
