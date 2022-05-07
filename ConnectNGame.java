
import java.util.Scanner;
import connectn.ConnectN;
public class ConnectNGame {

	public static void main(String []args) {
		System.out.println("Please select the number of rows for this game:");
		Scanner in = new Scanner(System.in);
		int rows = 0;
		try {
			rows = in.nextInt();
		} catch (Exception e) { // User passed in an input that's not an integer.
			System.out.println("Illegal input detected.");
			return;
		}
		if (rows < 3 || rows > 8) {
			// Game becomes trivial to solve if there are too few rows and columns.
			// Also, the number of rows and columns must be positive.
			// Game becomes very tedious if there are too many rows and columns.
			System.out.println("Number of rows must be between 3 and 8 inclusive.");
			return;
		}
		ConnectN board = new ConnectN(rows);
		board.play();
	}
}