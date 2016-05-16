import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;



/**
 * A panel for displaying the game instruction. With fading effects.
 */
public class HowToPlay extends JPanel {

	private static final long serialVersionUID = 1L;

	private Timer timer;
	private float opacity = 0;

	public HowToPlay() {
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

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
				opacity));
	}

}
