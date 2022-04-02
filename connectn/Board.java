package connectn;
import java.util.*;

public class Board {
	private List<String> board = new ArrayList<String>();

	private List<Integer> columnStatus = new ArrayList<Integer>();
	private List<Integer> diagStatus = new ArrayList<Integer>();
	private List<Integer> reverseDiagStatus = new ArrayList<Integer>();

	private List<Integer> columnCheck = new ArrayList<Integer>(); // index = column, value = min available row (0-indexed, -1 if column completely occupied);
	private int columnsAvailable = 0;

	public Board() {
		for (int i = 0; i < 4; i++) {
			board.add("*".repeat(7));
		}

		columnCheck = new ArrayList<Integer>(Collections.nCopies(7, 3));
		columnsAvailable = 7;

		columnStatus = new ArrayList<Integer>(Collections.nCopies(7, 0)); // +1 for Player 1, -1 for Player 2, 0 if unoccupied
		diagStatus = new ArrayList<Integer>(Collections.nCopies(7, 0)); // +1 for Player 1, -1 for Player 2, 0 if unoccupied
		reverseDiagStatus = new ArrayList<Integer>(Collections.nCopies(7, 0)); // +1 for Player 1, -1 for Player 2, 0 if unoccupied
	}

	public Board(int rows) {
		rows = Math.min(8,Math.max(3,rows));
		for (int i = 0; i < rows; i++) {
			board.add("*".repeat(rows + 3));
		}
		columnCheck = new ArrayList<Integer>(Collections.nCopies(rows + 3, rows - 1));
		columnsAvailable = rows + 3;

		columnStatus = new ArrayList<Integer>(Collections.nCopies(rows + 3, 0)); // +1 for Player 1, -1 for Player 2, 0 if unoccupied
		diagStatus = new ArrayList<Integer>(Collections.nCopies(rows + 3, 0)); // +1 for Player 1, -1 for Player 2, 0 if unoccupied
		reverseDiagStatus = new ArrayList<Integer>(Collections.nCopies(rows + 3, 0)); // +1 for Player 1, -1 for Player 2, 0 if unoccupied
	}

	public void printBoard() {
		System.out.println("The board:");
		for (int i = 0; i < board.size(); i++) {
			System.out.println(board.get(i));
		}
		System.out.println("columnCheck: " + columnCheck.toString());
		System.out.println("columns available: " + columnsAvailable);
		System.out.println("");
		System.out.println("columnStatus: " + columnStatus.toString());
		System.out.println("diagStatus: " + diagStatus.toString());
		System.out.println("reverseDiagStatus: " + reverseDiagStatus.toString());
	}


}