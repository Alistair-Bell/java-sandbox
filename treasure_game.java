/*
 * License: read `license.txt` at the root of the project.
 * Lesson learned from this project.
 *     Java would be much improved if it had pointers or references.
 * 
 */

import java.util.*;

abstract class object {
	public int              _position;
	public abstract int     tick();
	/* Called when another object touches it. */
	public abstract int     interact(int __l);
	public abstract char    represent();
	public static String    _directions = "nesw";

	/* Allows for the player or enemies to move with wrapping, this program takes the direction and handles wrap. */
	public static int wrap(char __dir, int __width, int __pos) {
		/* Turn the single dimention coordinates into double dimention. */
		int[] coords = { __pos - (__pos / __width) * __width, __pos / __width };
		switch (__dir) {
			case 'n': {
				coords[1] = (coords[1] == 0) ? __width - 1 : coords[1] - 1;
				break;
			}
			case 'e': {
				coords[0] = (coords[0] == __width - 1) ? 0 : coords[0] + 1;
				break;
			}
			case 's': {
				coords[1] = (coords[1] == __width -1) ? 0 : coords[1] + 1;
				break;
			}
			case 'w': {
				coords[0] = (coords[0] == 0) ? __width - 1: coords[0] - 1;
				break;
			}
		}
		return coords[0] + coords[1] * __width;
	}
}
final class treasure extends object {
	public int tick() {
		return 1;
	}
	public int interact(int __l) {
		object ref = map.get()._tiles[__l];
		System.out.println(ref.represent());
		switch (ref.represent()) {
			case '&': {
				((player)(map.get()._tiles[__l]))._coins += 1;
				/* Allow for the garbage collector to clean this up. */
				map.get()._tiles[_position] = null;
				break;
			}
			case '%': {
				/* Swap the position. */
				map.get()._tiles[__l] = this;
				map.get()._tiles[_position] = ref;
				return 1;
			}
		}
		return 1;
	}
	public char represent() {
		return '$';
	}
	public treasure(int __pos) {
		_position = __pos;
	}
}
final class enemy extends object {
	public int tick() {
		int width = map.get()._width;
		int[] moves = {
			object.wrap('n', width, _position),
			object.wrap('e', width, _position),
			object.wrap('s', width, _position),
			object.wrap('w', width, _position),
		};
		for (int p : moves) {
			if (map.get()._tiles[p] != null && map.get()._tiles[p].represent() == '&') {
				if (map.get()._tiles[p].interact(_position) < 0) {
					System.out.println("An enemy moved to your position");
					return -1;
				}
			}
		}

		return 1;
	}
	public int interact(int __l) {
		return (map.get()._tiles[__l].represent() == '&') ? -1 : 1;
	}
	public char represent() {
		return '%';
	}
	public enemy(int __pos) {
		_position = __pos;	
	}
}
final class player extends object {
	public int _coins;
	private Scanner _scanner;

	public int tick() {
		char direction = (char)0;
		map.get().display();
		System.out.printf("Player: coins -> %d.\n", _coins);
		do {
			System.out.printf("Please choose a direction, (n)orth, (e)ast, (s)out, (w)est. [%s].\n", object._directions);
			direction = _scanner.nextLine().charAt(0);	
		} while (object._directions.indexOf(direction) < 0);

		/* Get the new direction handling wrap. */
		int new_pos = object.wrap(direction, map.get()._width, _position);
		/* Interact with the object at this location. */
		if (map.get()._tiles[new_pos] != null) {
			if (map.get()._tiles[new_pos].interact(_position) < 0) {
				System.out.println("You died!, an enemy got you!");
				return -1;
			}
		}
		/* Swap to the new place. */
		map.get()._tiles[_position] = null;
		_position = new_pos;
		map.get()._tiles[new_pos] = this;

		/* Check for win condition. */
		if (map.placement_formula(map.get()._width) / 2 == _coins) {
			System.out.println("You won!, you collected all the coins!");
			return -1;
		}
		return 1;
	}
	public int interact(int __l) {
		return (map.get()._tiles[__l].represent() == '%') ? -1 : 1;
	}
	public char represent() {
		return '&';
	}
	public player(int __pos) {
		_position = __pos;
		_coins    = 0;
		_scanner  = new Scanner(System.in);
	}
}
final class map {
	private static map _instance;
	public int _width;
	public object[] _tiles;

	private static int place_object(HashMap<Integer, Boolean> __map, int __size) {
		do {
			int pos = (int)(Math.random() * __size);
			if (__map.get(pos) == null) {
				return pos;
			}
		} while (true);
	}
	public static int placement_formula(int __width) {
		return ((int)(__width / 2)) + __width;
	}
	public map() {
		/* TODO allow for the user to choose a playing size. */
		_width = 5;
		_tiles = new object[_width * _width];

		HashMap<Integer, Boolean> placements = new HashMap<Integer, Boolean>();

		int object_count = placement_formula(_width);
		for (int i = 0; i <= object_count; ++i) {
			int pos = place_object(placements, _tiles.length);
			placements.put(pos, true);
			/* We love turnary operators!. */
			_tiles[pos] = (i < (int)(object_count / 2)) 
				? new treasure(pos) : ((i == object_count) ? new player(pos) : new enemy(pos));
		}
	}
	public void display() {
		for (int i = 0; i < _width; ++i) {
			for (int j = 0; j < _width; ++j) {
				int board_pos = (i * _width) + j; 
				System.out.printf("[%c]", (_tiles[board_pos] == null) ? ' ' : _tiles[board_pos].represent());
			}
			System.out.println("");
		}
	}

	public static map get() {
		if (_instance == null) {
			_instance = new map();
		}
		return _instance;
	}
}
class treasure_game_program {
	public static char _reps[] = { new player(0).represent(), new enemy(0).represent(), new treasure(0).represent() } ;
	public static void display_key() {
		System.out.printf("Character keys, player [%c], enemy [%c], treasure [%c].\n", _reps[0], _reps[1], _reps[2]);
	}
	public static void main(String[] __argv) {
		do {
			display_key();
			for (int i = 0; i < map.get()._tiles.length; ++i) {
				if (map.get()._tiles[i] != null) {
					if (map.get()._tiles[i].tick() < 0) 
						return;
				}
			}
		} while (true);
	}
}
