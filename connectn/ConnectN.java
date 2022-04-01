package connectn;
import java.util.*;


public class ConnectN {
	private List<String> board = new ArrayList<String>();

	private List<Integer> rowStatus = new ArrayList<Integer>();
	private List<Integer> columnStatus = new ArrayList<Integer>();
	private List<Integer> diagStatus = new ArrayList<Integer>();
	private List<Integer> reverseDiagStatus = new ArrayList<Integer>();

	private List<Integer> columnCheck = new ArrayList<Integer>(); // index = column, value = min available row (0-indexed, -1 if column completely occupied);
	public ConnectN() {
		for (int i = 0; i < 4; i++) {
			board.add("*".repeat(7));
		}
	}

	public ConnectN(int rows) {
		rows = Math.min(8,Math.max(3,rows));
		for (int i = 0; i < rows; i++) {
			board.add("*".repeat(rows + 3));
		}
	}

	public void print() {
		System.out.println("The board:");
		for (int i = 0; i < board.size(); i++) {
			System.out.println(board.get(i));
		}
	}
}