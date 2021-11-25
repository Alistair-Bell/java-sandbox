/*
 * License: read `license.txt` at the root of the project.
 */

class check_digit_program { 

	public static int generate_digit(final byte[] __bytes) {
		int even_sum = 0, odd_sum = 0;
		for (int i = 0; i < __bytes.length; ++i) {
			if ((i & 1) == 0) {
				even_sum += __bytes[i];
			} else {
				odd_sum += __bytes[i];
			}
		}
		int pre = (odd_sum * 3) + even_sum;
		return (pre + (10 - pre % 10)) - pre;
	}

	public static void main(String[] __argv) {
		final byte[] bytes = { 9, 3, 1, 1, 9, 6, 0, 0, 4, 3, 7, 1 };
		System.out.printf("Check digit [%d]\n", generate_digit(bytes));
	} 
}
