
public class User {
	String name;
	int nextMove;
	boolean isReady; // is user input set

	public User(String name) {
		this.name = name;
		isReady = false;
	}

	public String getName() {
		return name;
	}

	public boolean isReady() {
		return isReady;
	}

	public void resetPlayer() {
		isReady = false;
	}
	public void userInputReady(int nextMove) {
		isReady = true;
		this.nextMove = nextMove;
	}

	public int decideMove() {
		isReady = false;// get ready to receive input
		return modeGUI();
	}

	private int modeGUI() {
		// wait isReady to be set
		while (!isReady) {
			if (Thread.interrupted()) {
				return -1;
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
		}

		return nextMove;
	}
}
