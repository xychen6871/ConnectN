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

		System.out.println("Ready!");
		while (ConnectNBoard.canStillPlay() == true && ConnectNBoard.checkWin() == 0) {
			print();
			if (turn % 2 == 0) {
				System.out.println("Player 1, it is your turn. Pick a column to drop your token into.");
			} else {
				System.out.println("Player 2, it is your turn. Pick a column to drop your token into.");
			}

			char token = (turn % 2 == 0) ? 'O' : 'X';
			int column = -1;
			Scanner io = new Scanner(System.in);
			try {
				column = io.nextInt();
			} catch (Exception e) {
				column = -1;
			}
			boolean valid = ConnectNBoard.placeToken(column, token);
			if (valid == true) {
				turn++;
				turn %= 2;
			}

		}
		print();
		int result = ConnectNBoard.checkWin();
		if (result == 0) {
			System.out.println("Draw");
		} else if (result == 1) {
			System.out.println("Player 1 wins");
		} else {
			System.out.println("Player 2 wins");
		}

		
	}
	
}