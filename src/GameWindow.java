import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;


public class GameWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	private GameBoardPanel gameBoardPanel;
	private GameStatisticsPanel gameStatisticsPanel;
	private MazePuzzleGame mainGame;

	public GameWindow(MazePuzzleGame mg, String title) {
		super(title);
		mainGame = mg;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 700);
		setLocationRelativeTo(null);
		setResizable(false);
		setBounds(mainGame.getMainFrame().getBounds());

		gameStatisticsPanel = new GameStatisticsPanel(this, mainGame);
		gameStatisticsPanel.setPreferredSize(new Dimension(250, 700));
		getContentPane().add(gameStatisticsPanel, BorderLayout.WEST);

		gameBoardPanel = new GameBoardPanel(this, mainGame);
		gameBoardPanel.setPreferredSize(new Dimension(750, 700));
		getContentPane().add(gameBoardPanel, BorderLayout.EAST);

		pack();
	}

	public GameStatisticsPanel getStatisticsPanel() {
		return gameStatisticsPanel;
	}

	public GameBoardPanel getGameBoardPanel() {
		return gameBoardPanel;
	}

	public void startSinglePlayerGame(String playerName, int modeAI) {
		gameBoardPanel.initSinglePlayerGame(playerName, modeAI);
	}

	public void startDoublePlayersGame(String playerName, String playerName2) {
		gameBoardPanel.initDoublePlayersGame(playerName, playerName2);
	}

}
