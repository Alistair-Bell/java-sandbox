/* 
 * License: read `license` at the root of the project.
 */


import java.util.*;

abstract class object {
	public int _position;
	abstract public void tick(map __map);
	/* I would love to use emojis (unicode wide chars) but my terminal emulator does not support unicode! */
	abstract char representation();
	/* Allows for objects to move around the map freely, allows wraparound. */
	public static int handle_wrap_around(int __initial_position, map __map, char __movement) {
		/* Shorthand. */
		int l = __map._length;
		switch (__movement) {
			case 'n': {
				return (__initial_position < l) ? (__initial_position + (l * l - 1)) : (__initial_position - l);
			}
			case 'e': {
				return ((__initial_position + 1) % l == 0) ? (__initial_position - 4) : (__initial_position + 1);
			}
			case 's': {
				return (l * (l - 1) <= __initial_position) ? (__initial_position - (l * (l - 1))) : (__initial_position + l);
			}
			case 'w': {
				return ((__initial_position % l) == 0) ? (__initial_position + (l - 1)) : (__initial_position - 1);
			}
		}
		return 3;
	}
}

final class treasure extends object {
	public double _total;

	public treasure(int __position) {
		_position = __position;
		_total    = Math.random() * 20;
	}
	public void tick(map __map) { 
		/* The treasures only function is on interact. */	
	}
	public char representation() {
		return '$';
	}
}
final class monster extends object {
	public monster(int __position) {
		_position = __position;
	}
	public void tick(map __map) {

	}
	public char representation() {
		return '%';
	}
}
final class tile extends object {

	public tile(int __position) {
		_position = __position;	
	}
	public void tick(map __map) {
		/* The tiles only functionality is to swap it's place with another object. */	
	}
	public char representation() {
		return ' ';
	}
}
final class player extends object {
	public double  _total_treasure;
	public int     _health;
	public Scanner _scanner;

	public player(int __position) {
		_position       = __position;
		_scanner        = new Scanner(System.in);
		_health         = 3;
		_total_treasure = 0.0f;
	}
	public void tick(map __map) {
		char input;
		/* Validate that the input was either n, e, s, w. */
		do {
			System.out.println("Where to move? options (n)orth, (e)ast, (s)out, (w)est.");
			input = _scanner.next().charAt(0);
		} while ("nesw".indexOf(input) == -1);

		System.out.println(object.handle_wrap_around(_position, __map, input));
	}
	public char representation() {
		return '&';
	}
}

final class map {
	public object[] _map;
	public int _length;

	private void populate(int __treasure_count, int __monster_count) {
		
		/* An hashmap where if the value has a home then the tile is occupied. */
		HashMap<Integer, Boolean> positions = new HashMap<Integer, Boolean>();
		for (int i = 0; i < __treasure_count + __monster_count; ++i) {
			do {
				int start_position = (int)(Math.random() * _map.length);
				if (positions.get(start_position) == null) {
					_map[start_position] = (i < __treasure_count) ? new treasure(start_position) : new monster(start_position);
					positions.put(start_position, true);

					if (i == (__treasure_count + __monster_count - 1)) {
						_map[start_position] = new player(start_position);
					}
					break;
				}
			} while (true);
		}
	}
	public map(int __size) {
		/* Stores the unsquared version of `_map.length` */
		_length = __size;
		_map = new object[__size * __size];
		for (int i = 0; i < _map.length; ++i) {
			/* Populate the map with tile objects. */
			_map[i] = new tile(i);
		}
		populate(3, 3);
	}
	public void display() {
		for (int i = 0; i  < _length; ++i) {
			for (int j = 0; j < _length; ++j) {
				System.out.printf("[%2d %c]", j + (i * _length), _map[j + (i * _length)].representation());
			}
			System.out.println("");
		}
	}
}

class treasure_game_program {
	public static Scanner _scanner = new Scanner(System.in);
	public static map _map;

	public static object[] _ds = { new monster(0), new treasure(0), new player(0) };

	public static void display_representation() {
		System.out.printf("Key: monster [%c], treasure [%c], player [%c].\n", _ds[0].representation(),
			_ds[1].representation(), _ds[2].representation());
	}
	public static void main(String[] __argv) {
		_map = new map(5);
		do {
			display_representation();
			_map.display();
			for (int i = 0; i < _map._map.length; ++i) {
				_map._map[i].tick(_map);
			}
			return;
		} while (true);
	}
}
