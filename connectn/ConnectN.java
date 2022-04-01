package connectn;
import java.util.*;


public class ConnectN {
	
	private Board ConnectNBoard = null;
	private int turn = 0; // Player 1 (turn % 2 = 0), Player 2 (turn % 2 = 1)
	
	public ConnectN() {
		ConnectNBoard = new Board();

	}

	public ConnectN(int rows) {
		ConnectNBoard = new Board(rows);
	}

	public void print() {
		if (ConnectNBoard != null) {
			ConnectNBoard.printBoard();
		}
	}

	public void play() {
		if (ConnectNBoard == null) {
			return;
		}
	}
	
}