import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.GridLayout;



public class MazePanel extends JPanel implements ActionListener, MouseListener, KeyListener {
	
	private MainWindow mw;
	private int height;
	private int length;
	GridLayout grid;
	Maze mainMaze;

	
	public MazePanel(MainWindow window, int height, int length) {
		this.mw = window;
		this.height = height;
		this.length = length;
	
		mainMaze = new Maze(10,10,1);
		//mainMaze.buildTestMaze();
		grid = new GridLayout(10,10);
		this.setLayout(grid);

        for(int i=0; i < height; i++){
        	for(int j=0; j < length; j++){
        		if(mainMaze.checkifempty(i,j) == false){
        			
        			JLabel full = new JLabel();
        			full.setBackground(Color.BLACK);
        			full.setOpaque(true);
        			full.setMinimumSize(new Dimension(10,10));
        			full.setPreferredSize(new Dimension(10,10));
        			full.setMaximumSize(new Dimension(10,10));
        			this.add(full);
        			
        		}
        		else {
        			
        			JLabel blank = new JLabel();
        			blank.setBackground(Color.white);
        			blank.setOpaque(true);
        			blank.setMinimumSize(new Dimension(10,10));
        			blank.setPreferredSize(new Dimension(10,10));
        			blank.setMaximumSize(new Dimension(10,10));
        			this.add(blank);
        			
        		}
        	}
        }

	}
	
	public Dimension getPreferredSize() {
        return new Dimension(500,500);
    }
	
	

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mw.getMenu()){
			JOptionPane.showMessageDialog(null,"Instructions");
			System.out.println("Test");
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
