/*
 * License: read `license.txt` at the root of the project.
 */

import java.util.*;

class pizza_program {

	public static String[] _sizes = {
		"(s)mall",
		"(m)edium",
		"(l)arge",
		"(j)umbo",
		"(c)omical",
	};
	public static String[] _toppings = {
		"(a)nchovies",
		"(m)ushroom",
		"(c)heese",
		"(s)ausage",
		"(o)lives",
		"(p)epper",
		"(g)arlic",
		"(h)am",
		"(P)ineapple",
		"(P)epperoni",
		"(A)lmonds",
		"(n)one",
	};
	public static Scanner _in = new Scanner(System.in);

	public static void display_list(String __header, String[] __values) {
		System.out.printf("%s.\n", __header);
		for (String s : __values) {
			System.out.println(s);
		}
	}
	public static int get_list(String[] __values, String __header) {
		do {
			char search = (char)0;
			try {
				search = _in.next().charAt(0);
			}
			catch (Exception e) {
				_in.nextLine();
			}
			finally {
				for (int i = 0; i < __values.length; ++i) {
					if (__values[i].charAt(1) == search) {
						return i;
					}
				}
			}
			System.out.printf("Invalid input, enter a value from the list!\n");
			display_list(__header, __values);
		} while (true);
	}

	public static void main(String[] __argv) {

		long pennies = 0;
		int topping_count = 0;
		display_list("Sizes", _sizes);
		pennies += 799 + (get_list(_sizes, "Sizes") * 300);

		HashMap<Integer, Boolean> topping_map = new HashMap<Integer, Boolean>();

	 	do {
			display_list("Toppings", _toppings);
			int index = get_list(_toppings, "Toppings");
			if (index == _toppings.length - 1) {
				break;
			}
			if (topping_map.get(index) != null) {
				System.out.printf("Topping already added!, please select another.\n");
			} else {
				topping_map.put(index, true);
				++topping_count;
			}
		} while (true);

		long before_price = pennies;
		if (topping_count > 2) {
			pennies += ((topping_count - 2) * 75);
		}
		System.out.printf("Total %d great british pennies, an extra %d for %d toppings on pizza.\n", pennies, pennies - before_price, topping_count);
	}
}
