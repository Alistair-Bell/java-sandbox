/* 
 * License: read `license` at the root of the project.
 */

import java.util.Scanner;
import java.util.Arrays;

abstract class shape {
	/* Some shapes may require a constructor init prior to the draw function. */
	abstract void draw(int __length);
}
final class triangle extends shape {
	void draw(int __length) {
		String buffer = "";
		int i = 0;
		for (; i < __length; ++i) {
			buffer += i;
			System.out.printf("%s\n", buffer);
		}
		/* Turnary statement magic. */
		i = buffer.length();
		do {
			System.out.printf("%s\n", buffer.substring(0, i));
			--i;
		} while (i != 0);
	}
}
final class rectangle {
	private String _buffer;

	rectangle() {
		_buffer = "";
	}
	void draw(int __length) {
		/* Populate the string. */
		int i = 0;
		for (; i < __length; ++i) {
			_buffer += i;
		}
		/* Print the top row. */
		System.out.println(_buffer);

		/* Set `i` to the true length of the string. */
		i = _buffer.length();

		/* Allows a nice gap between the walls. */
		String gap;
	
		{
			char[] whitespace = new char[i - 2];
			Arrays.fill(whitespace, ' ');
			gap = new String(whitespace);
		}

		do {
			char wall = _buffer.charAt(_buffer.length() - i);
			System.out.printf("%c%s%c\n", wall, gap, wall);
			--i;
		} while (i != 0);

		/* Print the bottom row. */
		System.out.println(_buffer);
	}
}
final class circle extends shape {
	private String _buffer;
	private String _whitespace;

	circle() {
		_buffer = "";
	}
	public void draw(int __length) {
		/* Populate `_buffer` with values of `__length`. */
		int i = 0;
		for (; i < __length; ++i) {
			_buffer += i;
		}
			
		i = _buffer.length();
		/* Create the whitespace buffer. */
		char[] whitespace_chars = new char[i * 2];
		Arrays.fill(whitespace_chars, ' ');
		_whitespace = new String(whitespace_chars);
		
		draw_top(_buffer.length() / 2);
		draw_arch(true);
		draw_shaft();
		draw_arch(false);
		draw_top(_buffer.length() / 2);
	}
	private void draw_top(int __offset) {
		System.out.printf("%s%s\n", _whitespace.substring(0, __offset), _buffer);
	}
	private void draw_arch(boolean __outwards) {
		/* Create a midpoint for the turn around point of the circle going outwards. */	
		int blen = _buffer.length();
		int turning_point = (int)blen / 2;
		/* Print the top segment. */
		int i = 0;
		for (; i < turning_point; ++i) {
			/* Print in segments of 2. */
			int wl1, wl2;
			if (__outwards) {
				wl1 = turning_point - i - 1;
				wl2 = (blen - 2) + i * 2;
			} else {
				wl1 = i + 1;
				wl2 = (turning_point * 3) - (i * 2) - 1;
			}
			System.out.printf("%s%s%s%s\n", _whitespace.substring(0, wl1),
				_buffer.substring(i, i + 2), _whitespace.substring(0, wl2),
				_buffer.substring(i, i + 2));
		}
	}
	private void draw_shaft() {
		/* Do the shaft in incriments of 2, make it look less lanky. */
		int i = 0;
		for (i = 0; i < _buffer.length(); i += 2) {
			char[] shaft_chars = { _buffer.charAt(i), _buffer.charAt(i + 1) };	
			System.out.printf("%c%c%s%c%c\n", shaft_chars[0], shaft_chars[1],
				_whitespace.substring(0, (_buffer.length() - 2 )* 2), shaft_chars[0], shaft_chars[1]); 
		}
	}
}

class shapes_program {
	public static Scanner _scanner;
	public static void main(String[] __argv) {
		_scanner = new Scanner(System.in);
		System.out.printf("Please enter a number!\n");
		int length = _scanner.nextInt();
		/* A length of < 3 will break things. */
		if (length < 3) {
			length += 3;
		}
		/*
		triangle tri = new triangle();
		tri.draw(length);
		rectangle rec = new rectangle();
		rec.draw(length);
		*/
		circle circ = new circle();
		circ.draw(length);

	}
}
