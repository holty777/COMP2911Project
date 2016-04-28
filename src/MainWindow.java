import java.io.IOException;
import java.util.*;
import javax.swing.*;


public class MainWindow {
	
	private JFrame mainFrame;
	private MazePanel MazePanel;

	public static void main(String[] args) throws IOException {
		final MainWindow mw = new MainWindow();
		
		// display the main window in a different thread.
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	mw.display();
            }
        });
	}
	
	public MainWindow() throws IOException {

		mainFrame = new JFrame("Test");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MazePanel = new MazePanel(this);
		
	}

	public JFrame getMainFrame() {
		return mainFrame;
	}

	public void setMainFrame(JFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	private void display() {

		mainFrame.getContentPane().add(MazePanel);
		mainFrame.pack();
        mainFrame.setVisible(true);
        mainFrame.setResizable(false);
	}
}
