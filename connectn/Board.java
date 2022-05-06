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
			board.add("*".repeat(2 * rows - 1));
		}
		columnCheck = new ArrayList<Integer>(Collections.nCopies(2 * rows - 1, rows - 1));
		columnsAvailable = 2 * rows - 1;

		columnStatus = new ArrayList<Integer>(Collections.nCopies(2 * rows - 1, 0)); // +1 for Player 1, -1 for Player 2, 0 if unoccupied
		diagStatus = new ArrayList<Integer>(Collections.nCopies(2 * rows - 1, 0)); // +1 for Player 1, -1 for Player 2, 0 if unoccupied
		reverseDiagStatus = new ArrayList<Integer>(Collections.nCopies(2 * rows - 1, 0)); // +1 for Player 1, -1 for Player 2, 0 if unoccupied
	}

	public void printBoard() {
		System.out.println("The board:");
		for (int i = 0; i < board.size(); i++) {
			System.out.println(board.get(i));
		}
		System.out.println("columnCheck: " + columnCheck.toString());
		System.out.println("columns available: " + columnsAvailable);
		System.out.println("Statuses:");
		System.out.println("columnStatus: " + columnStatus.toString());
		System.out.println("diagStatus: " + diagStatus.toString());
		System.out.println("reverseDiagStatus: " + reverseDiagStatus.toString());
		System.out.println("");
	}

	public int checkColumnWin() {
		for (int i = 0; i < columnCheck.size(); i++) {
			if (Math.abs(columnCheck.get(i)) == board.size()) {
				return (columnCheck.get(i) > 0) ? 1 : 2; // if columnCheck[i] == -rows, player 2 wins, else player 1 wins
			}
		}
		return 0; // No winner
	}

	public int checkDiagWin() {
		for (int i = 0; i < diagStatus.size(); i++) {
			if (Math.abs(diagStatus.get(i)) == board.size()) {
				return (diagStatus.get(i) > 0) ? 1 : 2; // if diagStatus[i] == -rows, player 2 wins, else player 1 wins
			}
		}

		for (int i = 0; i < reverseDiagStatus.size(); i++) {
			if (Math.abs(reverseDiagStatus.get(i)) == board.size()) {
				return (reverseDiagStatus.get(i) > 0) ? 1 : 2; // if reverseDiagStatus[i] == -rows, player 2 wins, else player 1 wins
			}
		}
		return 0; // No winner
	}

	public int checkHorizontalWin() {
		int rows = board.size();
		for (int i = 0; i < rows; i++) {
			List<Integer> prefixSum = new ArrayList<Integer>();
			Integer pSum = 0;
			//prefixSum.add(0);
			for (int j = rows; j < 2 * rows - 1; j++) {
				char c = board.get(i).charAt(j);
				if (c == 'O') {
					pSum++;
				} else if (c == 'X') {
					pSum--;
				}
				prefixSum.add(pSum);
			}

			for (int j = rows; j < 2 * rows - 1; j++) {
				int end = prefixSum.get(j);
				int start = (j - rows >= 0) ? prefixSum.get(j - rows) : 0;
				if (Math.abs(end - start) == rows) {
					return (end - start > 0) ? 1 : 2; // O * rows  -> Player 1, X * rows -> Player 2 
				}
			}
		}

		return 0;
	}

	public int checkWin() {
		// 0 --> no winner, 1 --> Player 1 wins, 2 --> Player 2 wins
		int cWin = checkColumnWin();
		if (cWin) {
			return cWin;
		}

		int hWin = checkHorizontalWin();
		if (hWin != 0) {
			return hWin;
		}

		int dWin = checkDiagWin();
		return dWin;
	}

	public boolean canStillPlay() {
		return (columnsAvailable > 0);
	}

	public boolean placeToken(int column, char token) {
		if (token != 'X' && token != 'O') {
			System.out.println("Illegal token used.");
			return false;
		} else if (column < 0 || column >= columnStatus.size()) {
			System.out.println("Column indexed must be between 0 and number of columns - 1 inclusive.");
			return false;
		} else if (canStillPlay() == false) {
			System.out.println("No more columns available");
			return false;
		}
		int rowIdx = columnCheck.get(column);
		if (rowIdx < 0) {
			System.out.println("Column is fully occupied.");
		}

		String currRow = board.get(column);
		currRow = currRow.substring(0, column) + token + currRow.substring(column + 1); // update board

		int val = (token == 'O') ? 1 : -1;
		
		// update column status
		columnStatus.set(column, columnStatus.get(column) + val);
		
		// update diagonal status
		int diagIdx = column - rowIdx;
		if (diagIdx >= 0) {
			diagStatus.set(diagIdx, diagStatus.get(diagIdx) + val);
		}

		// update reverse diagonal status
		int rDiagIdx = column + rowIdx;
		if (rDiagIdx < columnStatus.size()) {
			reverseDiagStatus.set(rDiagIdx, reverseDiagStatus.get(rDiagIdx) + val);
		}

		// update columnCheck
		columnCheck.set(column, rowIdx - 1);
		if (rowIdx == 0) {
			columnsAvailable--;
		}
		return true;
	}
}