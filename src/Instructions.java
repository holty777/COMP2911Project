import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * The "Instructions" class displays the instructions for the game.
 * @author  Jack Holt
 * 			Jesse Moses
 * 			Nick Balnaves
 * 			Jordan Jacobson
 * 			Shiyuan Liang
 *
 */
public class Instructions extends JPanel {

	private static final long serialVersionUID = 1L;
	//private MazePuzzleGame mainGame;
	private Timer timer;
	private float opacity = 0;

	/**
	 * The "Instructions" constructor.
	 */
	public Instructions() {
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.weightx = 0;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.BOTH;

		// user name input

		gc.gridx = 0;
		gc.gridy = 0;
		JLabel firstLine = new JLabel("<html>"
				+ "<center><b>The Legend of Zelda: Mario's Maze</b></center><br>"
				+ "Try to evade the evil Mario or defeat him head to head in an exiting game.<br>"
				+ "<br>"
				+ "<b>Single player:</b><br>"
				+ "Reach the Obelisk before Mario catches you...<br>"
				+ "Move through the maze with the arrow keys<br>"
				+ "<b>Double player:</b><br>"
				+ "Try to reach the obelisc before your opponent<br>"
				+ "<br>"
				+ "Use the items to assist in your journey. Try to figure our what each of them do!!");
		add(firstLine, gc);

		timer = new Timer(15, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				opacity += 0.08f;
				if (opacity > 1f) {
					opacity = 1f;
					timer.stop();
				}
				repaint();
			}
		});
		timer.start();
	}

	/**
	 * Draw the components
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
				opacity));
	}

}
