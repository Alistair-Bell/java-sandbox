import java.util.*;

class player {
	public String _name;
	public int _placed;
}

class better_guessing_program {
	private static Scanner _scanner = new Scanner(System.in);
	private static int[] _board;

	public static String get_name(String __message) {
		System.out.println(__message);
		return _scanner.next();
	}
	public static int get_range(String __message, int __min, int __max) {
		int return_value = -1;
		do {
			try {
				System.out.println(__message);
				return_value = _scanner.nextInt();
			} catch (Exception e) {
				System.out.printf("Caught %s\n", e.toString());
			}
			/* Make sure that the value is between the ranges. */
			if (return_value < __min || __max < return_value) {
				System.out.printf("Value %d not in range, -> [%d %d].\n", return_value, __min, __max);
			} else {
				return return_value;
			}
			/* Clear the scanner. */
			_scanner.nextLine();
		} while (true);
	}
	public static boolean attempt_place(int __placement) {
		if (_board[__placement] == 0) {
			_board[__placement] = Integer.MAX_VALUE;
			return true;
		} else {
			return false;
		}
	}

	public static void main(String[] __argv) {
		/* Our board. */
		_board = new int[get_range("How big should the board be?", 0, 30)];
		/* This ensures that all the memory is set to zero. */
		Arrays.fill(_board, 0);
		int[] placements = new int[2];
		String player_name = get_name("What is your name?");

		do {
			int cpu_placement = (int)(Math.random() * _board.length);
			if (attempt_place(get_range("Where do you want to place your counter?", 0, _board.length - 1))) {
				++placements[0];
			}
			if (attempt_place(cpu_placement)) {
				++placements[1];
			}
		 } while (placements[0] + placements[1] != _board.length);

		 if (placements[0] == placements[1]) {
			 System.out.printf("It was a tie, each player had %d placements.\n", placements[0]);
		 } else {
			 System.out.printf("%s has won, they had placed more on the board.\n", (placements[0] > placements[1]) ? player_name : "Bob the robot");
		 }
	}
}
