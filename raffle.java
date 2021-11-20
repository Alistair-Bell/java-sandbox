/* 
 * License: read `license` at the root of the project.
 */

import java.util.*;
import java.io.*;

final class raffle_pool {
	private List<Integer> _pool;	

	public raffle_pool(int __top_range) {
		_pool = new ArrayList<Integer>();
		for (int i = 0; i < __top_range; ++i) {
			_pool.add(i);
		}
	}
	public int pop() {
		if (empty()) {
			return -1;
		}
		int index = (int)(Math.random() * _pool.size());
		/* Get the value at the index. */
		int return_value = _pool.get(index);
		_pool.remove(index);
		return return_value;
	}
	public boolean empty() {
		return _pool.size() == 0;
	}
}

final class ticket {
	public int _value;
	public String _name;

	public ticket(int __value, String __name) {
		_value = __value;
		_name  = __name;

	}
	public boolean winner() {
		/* 0 & 1 are not prime values. */
		if (_value <= 1) {
			return false;
		}
		for (int i = 2; i < _value; ++i) {
			if (_value % i == 0) {
				return false;
			}
		}
		return true;
	}
}

class raffle_program { 
	private static BufferedReader _reader = new BufferedReader(new InputStreamReader(System.in));

	private static char get_opts(String __message, String __options) {
		char return_value = (char)0;
		do {
			try {
				System.out.println(__message);
				return_value = _reader.readLine().charAt(0);
			} catch (Exception e) {
				System.out.printf("Exception %s\n", e.toString());
			}
			finally {
				if (__options.indexOf(return_value) != -1) {
					return return_value;
				} else {
					System.out.printf("Invalid input, please input one of %s, chars!\n", __options);
				}
			}
		} while (true);
	}
	private static String get_name(String __message) {
		String return_value = "";
		do {
			try {
				System.out.println(__message);
				return_value = _reader.readLine();
			} catch (Exception e) {
				System.out.printf("Exception %s\n", e.toString());
			}
			finally {
				return return_value;
			}
		} while (true);
	}

	public static void main(String[] __argv) {
		/* Pool of left over values. */
		raffle_pool pool = new raffle_pool(100);
		/* Options to choose from. */
		final String options = "BbCcEe";

		List<ticket> bought = new ArrayList<ticket>();

		do {
			char opt = Character.toLowerCase(get_opts("Welcome to raffle system! What do you want todo?\n(B)uy\n(C)heck\n(E)xit", options));
			switch (opt) {
				case 'b': {
					int new_ticket_value;
					if ((new_ticket_value = pool.pop()) < 0) {
						System.out.println("All the raffle tickets have gone, you cannot buy anymore!");
						break;
					}
					bought.add(new ticket(new_ticket_value, get_name("What is your name?")));
					break;
				}
				case 'c': {
					if (bought.size() < 1) {
						System.out.println("No tickets have been bought!, you are not a winner till you play!");
						break;
					}
					String name = get_name("What is your name!");
					for (ticket t : bought) {
						if (t._name.equals(name)) {
							if (t.winner()) {
								System.out.printf("You are a winner!, %d is prime, you win a round of aplause!, clap clap clap clap.\n", t._value);
							} else {
								System.out.printf("Ticket %d is not a prime value, sorry.\n", t._value);
							}
						} 
					}
					break;
				}
				case 'e': {
					System.out.println("Thanks for using the raffle system!");
					return;
				}
			}
		} while (true);
	}
}
