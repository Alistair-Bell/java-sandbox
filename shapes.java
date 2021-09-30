/* 
 * License: read `license at the root of the project.
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
		if (i - 2 < 0) {
			gap = "";
		} else {
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

		triangle tri = new triangle();
		tri.draw(length);
		rectangle rec = new rectangle();
		rec.draw(length);

	}
}
