/*
 * License: read `license` at the root of the project.
 */

import java.util.*;

class pizza_program {

	public static String[] _sizes = {
		"(s)mall",
		"(m)edium",
		"(l)arge",
	};
	public static String[] _toppings = {
		"(a)nchovies",
		"(m)ushroom",
		"(c)heese",
		"(s)ausage",
		"(o)lives",
		"(p)epper",
		"(g)arlic",
		"(n)one",
	};
	public static Scanner _in = new Scanner(System.in);

	public static void display_list(String __header, String[] __values) {
		System.out.printf("%s.\n", __header);
		for (String s : __values) {
			System.out.println(s);
		}
	}
	public static int get_list(String[] _values) {
		do {
			char search = (char)0;
			try {
				search = _in.next().charAt(0);
			}
			catch (Exception e) {
				_in.nextLine();
			}
			finally {
				for (int i = 0; i < _values.length; ++i) {
					if (_values[i].charAt(1) == search) {
						return i;
					}
				}
			}
		} while (true);
	}

	public static void main(String[] __argv) {

		long pennies = 0;
		int topping_count = 0;
		display_list("Sizes", _sizes);
		pennies += 799 + (get_list(_sizes) * 300);
		do {
			display_list("Toppings", _toppings);
			if (get_list(_toppings) == _toppings.length - 1) {
				break;
			}
			++topping_count;
		} while (true);

		if (topping_count > 2) {
			pennies += ((topping_count - 2) * 75);
		}
		System.out.printf("Total %.2f.\n", ((float)pennies / 100));
	}
}
