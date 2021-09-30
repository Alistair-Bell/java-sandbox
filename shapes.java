/* 
 * License: read `license at the root of the project.
 */

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
		i += __length - 10;
		do {
			System.out.printf("%s\n", buffer.substring(0, i));
			--i;
		} while (i != 0);
	}
}

class shapes_program {
	public static void main(String[] __argv) {
		triangle s = new triangle();
		s.draw(21);
	}
}
