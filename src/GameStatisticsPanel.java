import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

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


	public GameStatisticsPanel(GameWindow gw, MazePuzzleGame mg) {

		mainGame = mg;
		gameWindow = gw;
		gameEngine = mainGame.getGameEngine();

		Font defaultFont = new Font("Arial", Font.BOLD, 20);

		setBackground(Color.WHITE);

		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.FIRST_LINE_START;
		gc.gridx = 0;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.WEST;

		// who's turn
		player1 = new JLabel();
		add(player1, gc);
		gc.gridy = 2;
		player2 = new JLabel();
		add(player2, gc);

		gc.anchor = GridBagConstraints.SOUTH;
		msg = new JLabel();
		msg.setFont(defaultFont);
		gc.gridy = 3;
		add(msg, gc);

		modeLabel = new JLabel();
		gc.gridy = 4;
		add(modeLabel, gc);

		modeDescription = new JLabel();
		gc.gridy = 5;
		add(modeDescription, gc);

		JButton restartGameButton = new JButton();
		restartGameButton.setContentAreaFilled(false);
		restartGameButton.setFocusPainted(false);
		restartGameButton.setBorderPainted(false);
		restartGameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				gameWindow.getGameBoardPanel().restartNewGame();
			}
		});
		gc.gridy = 6;
		add(restartGameButton, gc);

	}

	public void setPlayerNames(Player p1, Player p2) {
//		player1.setText(p1.getName());
//		player2.setText(p2.getName());
		player1.setText("Player1");
		player2.setText("Player2");
		msg.setText("Game Started.");
		msg.setForeground(Color.BLUE);
	}

	public void setWhosTurn() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}

	}

	public void displayEndGame(Player winner) {
		if (winner == null) {
			msg.setText("Board Full. Drew.");
		} else {
			msg.setText(winner.getName() + " won.");
			if (gameEngine.getCurrPlayerIndex() == 0) {
				msg.setForeground(new Color(255, 211, 2));
			} else {
				msg.setForeground(Color.RED);
			}
		}
		repaint();
	}

}
