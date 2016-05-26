import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;
import javax.swing.JFrame;

public class MazePuzzleGame implements Runnable {

	private static Thread guiThread;

	private JFrame mainFrame;
	private MenuPanel menuPanel;

	public MazePuzzleGame() throws IOException {

		mainFrame = new JFrame("Mario's Maze");
		mainFrame.setSize(300, 700);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		menuPanel = new MenuPanel(this);
		menuPanel.setPreferredSize(new Dimension(300, 700));
	}

	public static void main(String[] args) throws IOException {
		guiThread = new Thread(new MazePuzzleGame());
		guiThread.start();
	}

	private void display() {
		mainFrame.getContentPane().add(menuPanel, BorderLayout.WEST);
		mainFrame.setResizable(false);
		mainFrame.pack();
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

	public void suspendGame() {
		guiThread.interrupt();
	}

	@Override
	public void run() {
		display();
	}
	//public getGamePanel
}
