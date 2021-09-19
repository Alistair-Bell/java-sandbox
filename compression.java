/* 
 * License: read `license` at the root of the project.
 */

import java.util.Scanner;

/* The compression algorithms will just dump it's result to stdout. */
class compression {
	public static void rle_encode(String compress_in) {
		/* Start the compress value as the first char, this ensures that it will be incrimented at least a single time. */
		char compress_value = compress_in.charAt(0);
		int compress_count = 0, i = 0;

		for (; i < compress_in.length(); ++i) {
			/* Stores the current char at index 'i' use of a c lang pointer loop would have been better but I'm stuck with this approach. */
			char cchar = compress_in.charAt(i);

			/* The current char is not the same as the current series of chars being compressed. */
			if (cchar != compress_value) {
				System.out.printf("%c%d", compress_value, compress_count);
				/* Stores 1 as the new char has one currently recorded instance. */
				compress_count = 1;	
				/* Assign the new char to the compress_value. */
				compress_value = cchar;
			} else {
				/* 
				 * Just incriment the compress_count var and move onwards to the next char.
				 * A forseeable bug would be the incriment is done regardless if a overflow would occur.
				 */
				++compress_count;
			}
		}
		System.out.printf("%c%d\n", compress_value, compress_count);
	}
}

class program {

	static String messages[] = {
		"GNU's not unix!",
		"Java is an island in indochina.",
		"What you're refering to as Linux, is in fact, GNU/Linux, or as I've recently taken to calling it, GNU plus Linux.",
	};

	public static void main(String[] argv) {
		Scanner in = new Scanner(System.in);
		/* Provides no authentication on user input, the user is free to put any old garbage. */
		System.out.print("Please enter some text for the encoder! \n");
		compression.rle_encode(in.next());
		/* Run through with some of my own examples. */
		int i = 0;
		for (; i < messages.length; ++i) {
			System.out.printf("Message before: %s\nMessage after ", messages[i]);
			compression.rle_encode(messages[i]);
		}
	}
}
