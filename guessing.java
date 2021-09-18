/* 
 * License: read `license` at the root of the project.
 */

import java.util.Scanner;
import java.util.Random;

public class guessing {
	public static void main(String[] args) {
		/* Instantiate the random and scanner objects. */
		Random rand = new Random();
		Scanner in = new Scanner(System.in);

		/* Set the guesses and next the random target before the guess loop. */
		int guesses = 0, random = rand.nextInt(10);
		/* Run within a do while so the code is executed prior to the comparison expression. */
		do {
			System.out.printf("Please guess a number between 0 and 9\n");
			++guesses;
		} while (in.nextInt() != random);
		/* Display the results to the user, attempts and target. */
		System.out.printf("Guessed \'%d\' with %d attempts!\n", random, guesses);
	}
}
