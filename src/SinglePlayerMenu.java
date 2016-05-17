
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class SinglePlayerMenu extends JPanel {

	private static final long serialVersionUID = 1L;

	private MazePuzzleGame mainGame;
	private int AIMode;
	private ButtonGroup group;

	private JRadioButton AIButton0;
	private JRadioButton AIButton1;
	private JRadioButton AIButton2;

	public SinglePlayerMenu(MazePuzzleGame mg) {
		mainGame = mg;

		// set the format of the buttons
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.weightx = 0;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.BOTH;

		// user name input

		gc.gridx = 0;
		gc.gridy = 0;
		JLabel username = new JLabel("Your name:  ");
		add(username, gc);

		gc.gridx = 1;
		gc.gridy = 0;
		final JTextField playerNameField = new JTextField("", 10);
		add(playerNameField, gc);

		gc.gridx = 0;
		gc.gridy = 1;
		JLabel modeText = new JLabel("Difficulty:");
		add(modeText, gc);

		// Easy button
		gc.gridx = 1;
		gc.gridy = 1;
		AIButton0 = new JRadioButton("Easy");
		AIButton0.setToolTipText("For Beginner of this game");
		AIButton0.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				AIMode = 0;
			}
		});
		add(AIButton0, gc);

		// Medium button
		gc.gridx = 1;
		gc.gridy = 2;
		AIButton1 = new JRadioButton("Medium");
		AIButton1.setToolTipText("For Adavance Thinker");
		AIButton1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				AIMode = 1;
			}
		});
		add(AIButton1, gc);

		// Hard button
		gc.gridx = 1;
		gc.gridy = 3;
		AIButton2 = new JRadioButton("Hard");
		AIButton2.setToolTipText("For Extreme Player!");
		AIButton2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				AIMode = 2;
			}
		});
		add(AIButton2, gc);

		group = new ButtonGroup();
		group.add(AIButton0);
		group.add(AIButton1);
		group.add(AIButton2);

		// start game button
		gc.gridx = 1;
		gc.gridy = 4;
		JButton startGame = new JButton("Start Game");
		startGame.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				GameWindow singlePlayerWindow = new GameWindow(mainGame,
						"Single Player Game");
				singlePlayerWindow.startSinglePlayerGame(
						playerNameField.getText(), AIMode);

				singlePlayerWindow.setVisible(true);
				mainGame.setVisibility(false);
				MenuPanel.closeJOptionPanel();
			}
		});
		add(startGame, gc);
	}

}
