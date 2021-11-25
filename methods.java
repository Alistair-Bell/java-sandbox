/*
 * License: read `license.txt` at the root of the project.
 */

 import java.util.*;

 class methods_program {
	private static int match_count(int __house_count) {
		return __house_count * 6 - ((__house_count <= 1) ? 0 : __house_count - 1);
	}	
	private static int barn_legs(int __chicken_count, int __cow_count, int __pig_count) {
		return (__chicken_count * 2) + (__cow_count * 4) + (__pig_count * 4);
	}
	public static void main(String[] __argv) {
		int val = (int)(Math.random() * 100);
		System.out.printf("Number of matchsticks used to make %d houses -> %d.\n", val, match_count(val));
	}
 }
