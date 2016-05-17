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




public class MazePanel extends JPanel implements MouseListener, KeyListener {
	
	private int height;
	private int length;
	private Player player;
	private Player player2;
	GridLayout grid;
	AlphaMaze mainMaze;
	JLabel[][] labelGrid;
	
	public MazePanel(int height, int length) {
		labelGrid = new JLabel[height][length];
		this.height = height;
		this.length = length;
		mainMaze = new AlphaMaze(height,length);
		grid = new GridLayout(height,length);
		this.setLayout(grid);
		initMaze();

	}
	
	public void initMaze(){
		boolean check = false;
		int x = 0;
		int y = 0;
        for(int i=0; i < height; i++){
        	for(int j=0; j < length; j++){
        		if(mainMaze.isEmpty(i,j) == false){
        			if(check == false){
        				player = new Player(i, j, 25, 25, 0);
        				player.setMinimumSize(new Dimension(10,10));
        				player.setPreferredSize(new Dimension(10,10));
        				player.setMaximumSize(new Dimension(10,10));
        				labelGrid[i][j] = player;
        				//this.add(player);
        				check = true;
        			}
        			else{
        				JLabel full = new JLabel();
        				full.setBackground(Color.white);
        				full.setOpaque(true);
        				full.setMinimumSize(new Dimension(10,10));
        				full.setPreferredSize(new Dimension(10,10));
        				full.setMaximumSize(new Dimension(10,10));
        				labelGrid[i][j] = full;
        				//this.add(full);
        				x = i;
        				y = j;
        			}
        		}
        		else {
        			
        			JLabel blank = new JLabel();
        			blank.setBackground(Color.BLACK);
        			blank.setOpaque(true);
        			blank.setMinimumSize(new Dimension(10,10));
        			blank.setPreferredSize(new Dimension(10,10));
        			blank.setMaximumSize(new Dimension(10,10));
    				labelGrid[i][j] = blank;
        			//this.add(blank);
        			
        		}
        		

        	}
        	
        }
        player2 = new Player(x, y, 25, 25, 1);
		player2.setMinimumSize(new Dimension(10,10));
		player2.setPreferredSize(new Dimension(10,10));
		player2.setMaximumSize(new Dimension(10,10));
    	labelGrid[x][y] = player2;
        for(int i=0; i < height; i++){
        	for(int j=0; j < length; j++){
        			this.add(labelGrid[i][j]);
        		}
        	}
	}
	
//	public Dimension getPreferredSize() {
//        return new Dimension(500,500);
//    }
	
	

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
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			int i = player.getILocation();
			int j = player.getJLocation();
			if(mainMaze.isEmpty(i, j+1) == false){
				player.setILocation(i);
				player.setJLocation(j+1);
				labelGrid[i][j+1] = player;
				JLabel blank = new JLabel();
				blank.setBackground(Color.white);
    			blank.setOpaque(true);
    			blank.setMinimumSize(new Dimension(10,10));
    			blank.setPreferredSize(new Dimension(10,10));
    			blank.setMaximumSize(new Dimension(10,10));
				labelGrid[i][j] = blank;
				refreshMaze();
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_LEFT){

			int i = player.getILocation();
			int j = player.getJLocation();
			if(mainMaze.isEmpty(i, j-1) == false){
				player.setILocation(i);
				player.setJLocation(j-1);
				labelGrid[i][j-1] = player;
				JLabel blank = new JLabel();
				blank.setBackground(Color.white);
    			blank.setOpaque(true);
    			blank.setMinimumSize(new Dimension(10,10));
    			blank.setPreferredSize(new Dimension(10,10));
    			blank.setMaximumSize(new Dimension(10,10));
				labelGrid[i][j] = blank;
				
				refreshMaze();
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_UP){

			int i = player.getILocation();
			int j = player.getJLocation();
			if(mainMaze.isEmpty(i-1, j) == false){
				player.setILocation(i-1);
				player.setJLocation(j);
				labelGrid[i-1][j] = player;
				JLabel blank = new JLabel();
				blank.setBackground(Color.white);
    			blank.setOpaque(true);
    			blank.setMinimumSize(new Dimension(10,10));
    			blank.setPreferredSize(new Dimension(10,10));
    			blank.setMaximumSize(new Dimension(10,10));
				labelGrid[i][j] = blank;
				
				refreshMaze();
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_DOWN){

			int i = player.getILocation();
			int j = player.getJLocation();
			if(mainMaze.isEmpty(i+1, j) == false){
				player.setILocation(i+1);
				player.setJLocation(j);
				labelGrid[i+1][j] = player;
				JLabel blank = new JLabel();
				blank.setBackground(Color.white);
    			blank.setOpaque(true);
    			blank.setMinimumSize(new Dimension(10,10));
    			blank.setPreferredSize(new Dimension(10,10));
    			blank.setMaximumSize(new Dimension(10,10));
				labelGrid[i][j] = blank;
				
				refreshMaze();

			}
		}
		
		
	}
	
	public void refreshMaze(){
		this.removeAll();
		for(int a=0; a < height; a++){
        	for(int b=0; b < length; b++){
				//System.out.println(a + " " + b);
        			this.add(labelGrid[a][b]);
        		}
        	}
		revalidate();
		repaint();
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
