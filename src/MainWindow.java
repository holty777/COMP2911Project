import java.io.IOException;
import java.util.*;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;

import javax.swing.*;

public class MainWindow {
	
	private JFrame mainFrame;
	private MazePanel MazePanel;
	private JPanel menuPanel;
	
	GridLayout grid;
	private JButton startButton;
	private JButton menuButton;
	GridBagConstraints con;
	
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
		con = new GridBagConstraints();
		
		MazePanel = new MazePanel(this, 10, 10);
		
		/**
		menuPanel = new JPanel();
		startButton = new JButton("Start");
		menuButton = new JButton("Menu");
		menuPanel.add(startButton, con);
		menuPanel.add(menuButton, con);
		**/
	}
	
	public JButton getMenu(){
		return menuButton;
	}

	private void display() {
		mainFrame.add(MazePanel);
		mainFrame.pack();
		mainFrame.setResizable(false);
        mainFrame.setVisible(true);
	}
}
