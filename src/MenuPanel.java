import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MenuPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private MazePuzzleGame mainGame;
	
	public MenuPanel(MazePuzzleGame mg) {
		mainGame = mg;
		
		setLayout(new GridBagLayout());

		setOpaque(true);
		setBackground(Color.WHITE);

		GridBagConstraints gc = new GridBagConstraints();
		gc.weightx = 1;
		gc.weighty = 10;
		gc.fill = GridBagConstraints.BOTH;

		// Button for Single Player
		gc.gridx = 0;
		gc.gridy = 0;
		JLabel currentHighScore = new JLabel(" Current High Score: ");
		add(currentHighScore, gc);
		gc.gridy = 1;
		JLabel singlePlayerHighScores = new JLabel(" Single Player High Scores:");
		add(singlePlayerHighScores, gc);
		gc.gridy = 2;
		JLabel doublePlayerHighScores = new JLabel(" Double Player High Scores:");
		add(doublePlayerHighScores, gc);
		gc.gridy = 3;

		JButton singlePlayerButton = new JButton("Single Player");

		add(singlePlayerButton, gc);
		singlePlayerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SinglePlayerMenu singlePlayer = new SinglePlayerMenu(mainGame);
				JOptionPane.showOptionDialog(null, singlePlayer,
						"Mode Selection", JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.PLAIN_MESSAGE, null,
						new String[] { "Cancel" }, "default");

			}
		});
		
		// Button for Two Player
		gc.gridy = 4;
		JButton twoPlayersButton = new JButton("Double Player");
		add(twoPlayersButton, gc);
		twoPlayersButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DoublePlayersMenu doublePlayer = new DoublePlayersMenu(mainGame);
				JOptionPane.showOptionDialog(null, doublePlayer,
						"Enter Players Name", JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.PLAIN_MESSAGE, null,
						new String[] { "Cancel" }, "default");
			}

		});
		
		// how to play button
		gc.gridy = 5;
		JButton htpButton = new JButton("Instructions");
		add(htpButton, gc);
		htpButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainGame.changeGlassPane(4);
			}
		});

		// Button for Quit
		gc.gridy = 6;
		JButton quitButton = new JButton("Quit (Q)");
		add(quitButton, gc);
		quitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}

		});
	}

	public static void closeJOptionPanel() {
		JOptionPane.getRootFrame().dispose();
	}
}
