/* 
 * License: read `license.txt` at the root of the project.
 * 
 * Write a program where the user types in a letter and a number.
 * The program will print out the alphabet backwards in order, for the number of characters the user has requested.
 * Challenge: let the user type in either numbers or letters to loop through.
 */

import java.util.Scanner;

class sequence_reverse_program {
	/* Static definitions of the sequences. */
	public static String _alphabet = "abcdefghijklmnopqrstuvwxyz";
	public static String _numbers  = "0123456789";
	public static Scanner _scanner = new Scanner(System.in);

	/* Validates the input, returns < 0 on failure and 0 or 1 to tell the target char set. */
	public static int validate_arguments(final String __arguments) {
		/* Validate that only 1 or more characters are present, starting place and length. */
		if (__arguments.length() < 1) {
			return -1;
		}
		/* Validate that first character is present in either character sets. */
		if (_alphabet.indexOf(__arguments.charAt(0)) == -1) {
			if (_numbers.indexOf(__arguments.charAt(0)) != -1) {
				/* Found first char within numbers, this is our target. */
				return 1;
			}
			/* Neither found, input invalid. */
			return -1;
		}
		/* Alphabet is our target. */
		return 0;
	}
	public static void main(String[] __argv) {
		/* Get the arguments that the user wants. */
		System.out.printf("Please enter a start char a reverse count, eg: a10, 01, [%s] [%s]\n", _alphabet, _numbers);
		int result;
		String target_set, user_input = _scanner.next();
		if ((result = validate_arguments(user_input)) < 0) {
			/* Return as false. */
			return;
		}
		/* Use the chad turnary operator to assing the correct set to `target_set`. */
		target_set = (result == 0) ? _alphabet : _numbers;

		/* Get the amount that we need to count down. */
		int decriment_count;
		if (user_input.length() < 2) {
			/* Inputs such as 'a' or '1' will only count down a single time. */
			decriment_count = 1;
		} else {
			/* Extract a substring of the `user_input` variable then cast this to an integer. */
			decriment_count = Integer.parseInt(user_input.substring(1, user_input.length()));
		}

		/* The mainloop. */
		int start = target_set.indexOf(user_input.charAt(0));
		for (int i = 0; i < decriment_count; ++i) {
			System.out.printf("%c", target_set.charAt(start  - i));
			if (start == 0) {
				/* Loop back to the start of the alphabet. */
				start = target_set.length() - 1;
			}
		}
		System.out.printf("\n");
	}
}
