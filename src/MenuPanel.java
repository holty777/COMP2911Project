import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MenuPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private MazePuzzleGame mainGame;

	public MenuPanel(MazePuzzleGame mg) throws IOException {
		mainGame = mg;

		setLayout(new GridBagLayout());

		setOpaque(true);
		setBackground(Color.WHITE);

		GridBagConstraints gc = new GridBagConstraints();
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.BOTH;

		Image img = ImageIO.read(new File("src/MenuPic2.png"));
		ImageIcon imgI = new ImageIcon(img);
		JLabel menu = new JLabel();
		menu.setIcon(imgI);
		// Button for Single Player
		gc.gridx = 0;
		gc.gridy = 0;
		add(menu);
		// JLabel currentHighScore = new JLabel(" Current High Score: ");
		// add(currentHighScore, gc);
		gc.gridy = 1;
		// JLabel singlePlayerHighScores = new JLabel(" Single Player High Scores:");
		// add(singlePlayerHighScores, gc);
		// gc.gridy = 2;
		// JLabel doublePlayerHighScores = new JLabel(" Double Player High Scores:");
		// add(doublePlayerHighScores, gc);
		// gc.gridy = 3;

		final JLabel singlePlayerButton = new JLabel();
		
		img = ImageIO.read(new File("src/splayer.png"));
		Image img3 = ImageIO.read(new File("src/splayerS.png"));
		final ImageIcon imgI2 = new ImageIcon(img);
		final ImageIcon simg2 = new ImageIcon(img3);
		singlePlayerButton.setIcon(imgI2);

		add(singlePlayerButton, gc);
		singlePlayerButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SinglePlayerMenu singlePlayer = new SinglePlayerMenu(mainGame);
				JOptionPane.showOptionDialog(null, singlePlayer,
						"Mode Selection", JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.PLAIN_MESSAGE, null,
						new String[] { "Cancel" }, "default");

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
				singlePlayerButton.setIcon(simg2);
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				singlePlayerButton.setIcon(imgI2);
				
			}
		});

		// Button for Two Player
		gc.gridy = 4;
		Image img1 = ImageIO.read(new File("src/dplayer.png"));
		Image img2 = ImageIO.read(new File("src/dplayerS.png"));
		final ImageIcon dimg = new ImageIcon(img1);
		final ImageIcon dimg2 = new ImageIcon(img2);
		final JLabel twoPlayersButton = new JLabel();
		twoPlayersButton.setIcon(dimg);
		add(twoPlayersButton, gc);
		twoPlayersButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DoublePlayersMenu doublePlayer = new DoublePlayersMenu(mainGame);
				JOptionPane.showOptionDialog(null, doublePlayer,
						"Enter Players Name", JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.PLAIN_MESSAGE, null,
						new String[] { "Cancel" }, "default");
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
				twoPlayersButton.setIcon(dimg2);
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				twoPlayersButton.setIcon(dimg);
				
			}

		});

		// how to play button
		gc.gridy = 5;
		img1 = ImageIO.read(new File("src/instructions.png"));
		img2 = ImageIO.read(new File("src/instructionsS.png"));
		final ImageIcon iimg = new ImageIcon(img1);
		final ImageIcon iimg2 = new ImageIcon(img2);
		final JLabel htpButton = new JLabel();
		htpButton.setIcon(iimg);
		add(htpButton, gc);
		htpButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//mainGame.changeGlassPane(4);
				Instructions instruction = new Instructions();
				JOptionPane.showOptionDialog(null, instruction,
						"Instructions", JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.PLAIN_MESSAGE, null,
						new String[] { "Close" }, "default");
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
				htpButton.setIcon(iimg2);
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				htpButton.setIcon(iimg);
				
			}
		});

		// Button for Quit
		gc.gridy = 6;
		img1 = ImageIO.read(new File("src/quit.png"));
		img2 = ImageIO.read(new File("src/quitS.png"));
		final ImageIcon qimg = new ImageIcon(img1);
		final ImageIcon qimg2 = new ImageIcon(img2);
		final JLabel quitButton = new JLabel();
		quitButton.setIcon(qimg);
		add(quitButton, gc);
		quitButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
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
				quitButton.setIcon(qimg2);
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				quitButton.setIcon(qimg);
				
			}

		});
	}

	public static void closeJOptionPanel() {
		JOptionPane.getRootFrame().dispose();
	}
}
