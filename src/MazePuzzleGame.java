import java.awt.BorderLayout;
import java.io.IOException;
import javax.swing.JFrame;

/**
 * Main entry point for the project
 * Opens the 
 * @author holt
 *
 */
public class MazePuzzleGame implements Runnable {

	private JFrame mainFrame;
	private MenuPanel menuPanel;

	public MazePuzzleGame() throws IOException {

		mainFrame = new JFrame("Mario's Maze");
		//mainFrame.setSize(300, 700);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		menuPanel = new MenuPanel(this);
		//menuPanel.setPreferredSize(new Dimension(300, 700));
	}

	public static void main(String[] args) throws IOException {
		MazePuzzleGame game = new MazePuzzleGame();
		game.run();
	}

	private void display() {
		mainFrame.getContentPane().add(menuPanel, BorderLayout.WEST);
		mainFrame.setResizable(false);
		mainFrame.pack();
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
	}

	public void setVisibility(boolean visible) {
		mainFrame.setVisible(visible);
	}

	public MenuPanel getMenuPanel() {
		return menuPanel;
	}

	public JFrame getMainFrame() {
		return mainFrame;
	}

	@Override
	public void run() {
		display();
	}
	//public getGamePanel
}
