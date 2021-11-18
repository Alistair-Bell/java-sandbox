/*
 * License: read `license` at the root of the project.
 */

import java.util.*;

final class coordinate {
	public int _x;
	public int _y;
	public coordinate(int __position, board __target) {
		_y = __position / __target._width;
		_x = _y * __target._width + (__position % __target._width);
	}
}

final class board {
	public int _width;
	public int _height;
	public char[] _tiles;

	public board(int __width, int __height) {
		/* Make sure that the values are apropriate. */
		__width  += (__width < 3) ? 3 : 0;
		__height += (__height < 3) ? 3 : 0;
		
		/* Set them to the member varaibles. */
		_width  = __width;
		_height = __height;

		_tiles = new char[_width * _height];
		Arrays.fill(_tiles, ' ');
	}
	public void display() {
		/* Display the row numbers. */
		for (int i = 0; i < _width; ++i)
			System.out.printf(" %d", i);
		System.out.print('\n');

		for (int i = 0; i < _height; ++i) {
			for (int j = 0; j < _width; ++j) {
				System.out.printf("|%c", _tiles[i * _width + j]);
			}
			System.out.println('|');
		}
	}
	public boolean place(int __row, char __icon) {
		/* Validate that the row is in the width bounds. */
		if (__row < 0 || __row >= _width) {
			System.out.printf("Invalid index %d\n", __row);
			return false;
		}

		for (int i = _height - 1; i >= 0; --i) {
			int search = __row + i * _width;
			if (_tiles[search] == ' ') {
				_tiles[search] = __icon;
				return true;
			}
		}
		System.out.printf("Row %d is full please pick another\n", __row);
		return false;
	}
	/* Checks the game for an end condition. */
	public boolean check_end() {
		/* All the rows have been filled. */
		int accumilator = 0;
		for (int i = 0; i < _width; ++i) {
			accumilator += (_tiles[i] != ' ') ? 1 : 0;
		}
		return (accumilator == _width);
	}
	public boolean check_wins(int __last_placed) {
		char icon = _tiles[__last_placed];

		/* 
		 * As this is connect 4, 4 sequential tokens are required for the win.
		 * The directions we check ignore directly above as it is impossible to win in this scenario.
		 */

		/* North east. */
		return false;
	}
}

class match4_program {
	public static Scanner _scanner = new Scanner(System.in);
	public static board _board;

	public static int get_int(String __message) {
		int val = 0;
		do {
			try {
				System.out.println(__message);
				val = _scanner.nextInt();
			} catch (Exception e) {
				System.out.println("Expected an integer input");
			}
			break;
		} while (true);
		return val;
	}

	public static void main(String[] __in) {
		_board = new board(get_int("Please enter the board width."), get_int("Please enter the board height."));
		final char player_icon = '0';

		/* Mainloop. */
		int player_drop = 0;
		do {
			_board.display();
			/* Get where the user wants to place their counter. */
			do {
				player_drop = get_int("Please enter an index for the drop!");
			} while (!_board.place(player_drop, player_icon));
			
			/* Check the win condition for the */
			coordinate c = new coordinate(player_drop, _board);
			System.out.printf("%d : %d\n", c._x, c._y);

			/* Check that all the end slots are not taken -> all places placed. */
			if (_board.check_end()) {
				System.out.println("All the slots are taken!, nobody wins, how unlucky?");
				break;
			}
		} while (true);
	}
}
