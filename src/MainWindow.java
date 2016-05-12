import java.io.IOException;
import java.util.*;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.GridBagLayout;


import javax.swing.*;

public class MainWindow implements Runnable {
	
	private JFrame mainFrame;
	private MazePanel MazePanel;
	private JPanel menuPanel;
	private JPanel leftPanel;
	private JPanel outsidePanel;
	GridLayout grid;
	private JButton startButton;
	private JButton menuButton;
	GridBagConstraints con;
	
	public static void main(String[] args) throws IOException {
		final MainWindow mw = new MainWindow(null);
		
		// display the main window in a different thread.
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	mw.display();
            }
        });
	}
	
	public MainWindow(Runnable mainMaze) throws IOException {
		
		
		mainFrame = new JFrame("MazeGame");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		con = new GridBagConstraints();
		
		MazePanel = new MazePanel(this, 10, 10);
		
		GridBagLayout leftLayout = new GridBagLayout();
		leftPanel = new JPanel();
		leftPanel.setLayout(leftLayout);
		outsidePanel = new JPanel();
		
		con = new GridBagConstraints();
		createLeftButtons();
		
		outsidePanel.add(leftPanel);
		outsidePanel.add(MazePanel);
	}
	
	public JButton getMenu(){
		return menuButton;
	}
	public JButton getStart(){
		return startButton;
	}

	private void display() {
		mainFrame.getContentPane().add(outsidePanel);
		mainFrame.pack();
		mainFrame.setResizable(false);
        mainFrame.setVisible(true);
	}
	
	public void createLeftButtons(){
		
		startButton = new JButton("Start");
		menuButton = new JButton("Instructions");
	
		startButton.addActionListener(MazePanel);
		menuButton.addActionListener(MazePanel);
	
        con.fill = GridBagConstraints.CENTER;
		con.gridx = 0;
		con.gridy = 3;
		con.gridwidth = 2;

        leftPanel.add(startButton, con);
        con.fill = GridBagConstraints.CENTER;
		con.gridx = 0;
		con.gridy = 4;
		con.gridwidth = 1;

        leftPanel.add(menuButton, con);
        con.fill = GridBagConstraints.CENTER;
		con.gridx = 1;
		con.gridy = 4;
		con.gridwidth = 1;

	}

	public void restart(){
		mainFrame.getContentPane().remove(outsidePanel);
		outsidePanel.remove(MazePanel);
		MazePanel = new MazePanel(this,10,10);
		outsidePanel.add(MazePanel);
		this.display();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
