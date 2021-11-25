/*
 * License: read `license.txt` at the root of the project.
 * 
 * Lets the user type in:
 * An employee name
 * A number of hours they have worked that week
 * An hourly wage
 * 
 * The maximum number of hours to work in a week is 45
 * If the number of hours worked exceeds the maximum, the employee gets overtime pay which is 1.5*hourly wage for each additional hour.
 * Tax is 20%
 * 
 * Calculate and display:
 * How many hours pay an employee should get for that week
 * How much they get before and after paying tax.
 * 
 * Be able to do this for more than 1 employee
 */

import java.util.*;

class employee {
	public String _name;
	public double _required_hours;
	public double _hourly_wage;

	/* Related constants. */
	public static final double _tax = 0.20;
	public static final int _bonus_threshold = 45;

	/* Ignores the tax. */
	public double calculate_pay() {
		/* Check if any overtime is required. */
		if (_required_hours < employee._bonus_threshold) {
			return (_required_hours * _hourly_wage);
		}
		return (_required_hours * _hourly_wage) + (_hourly_wage * 1.5 * (_required_hours - employee._bonus_threshold));
	}
	public static employee get_from_user(Scanner __in) {
		/* Setting the required members for the program to print out and calculate stuff. */
		employee return_value = new employee();
		System.out.printf("Please enter the name for the employee!\n");
		return_value._name = __in.next();
		System.out.printf("Please the required hours for the employee!\n");
		return_value._required_hours = __in.nextDouble();
		System.out.printf("Please the hourly wage for the employee!\n");
		return_value._hourly_wage = __in.nextDouble();
		return return_value;
	}
}

class employee_program {
	public static Scanner _scanner;

	public static void main(String[] __argv) {
		_scanner = new Scanner(System.in);

		int running = 0;

		do {
			employee r = employee.get_from_user(_scanner);	
			double pay = r.calculate_pay();
			/* Display the pay and pay after tax to the end user. */
			System.out.printf("Employee %s will earn %.2f before tax and %.2f after tax a week\n", r._name, pay, pay - (pay * employee._tax));
			System.out.printf("Would you like to continue? 1 = continue, other = break?\n");
			running = _scanner.nextInt();
		} while (running == 1);
	}
}
