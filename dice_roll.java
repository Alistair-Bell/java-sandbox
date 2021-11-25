/* 
 *  License: read `license.txt` at the root of the project.
 */

import java.util.*;

class dice_roll_program {
	public static Scanner _scanner = new Scanner(System.in);
	public static Random  _random  = new Random();

	public static void main(String[] __argv) {
		/* Get the required inputs. */
		System.out.printf("Please enter the amount of faces that the dice has.\n");
		int side_count = _scanner.nextInt();
		System.out.printf("Please enter the amount of rolls todo.\n");
		int rolls = _scanner.nextInt();

		HashMap<Integer, Integer> roll_map = new HashMap<Integer, Integer>();

		for (int i = 0; i < rolls; ++i) {
			/* Use random to get the dice roll that has occured. */
			int value = _random.nextInt(side_count) + 1;

			/* Check if the value is present within the hashmap. */
			if (roll_map.get(value) == null) {
				roll_map.put(value, 1);
			} else {
				roll_map.put(value, roll_map.get(value) + 1);
			}
		}

		/* Loop through the hashmap values displaying the number: count then percentage from rolls. */
		for (int i : roll_map.keySet()) {
			int value = roll_map.get(i);
			System.out.printf("Rolled %3d %3d times, percentage %.2f%%\n", i, value, ((float)value / rolls * 100));
		}
	}
}
