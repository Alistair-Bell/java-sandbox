/*
 * License: read `license.txt` at the root of the project.
 * A simple login system whereby the system stores accounts of users and people can login.
 */

import java.util.*;
import java.io.*;

final class system {
	public static String output_file = "shadow.txt";

	public system() {
	}

	public int create_file() {
		try {
			File f = new File(output_file);
			return f.createNewFile() ? 1 : 0;
		} catch (Exception e) {
			System.out.printf("Exception thrown %s.\n", e.toString());
			return -1;
		}
	}
	public List<account> load() {
		List<account> out = new ArrayList<account>();
		try {
			Scanner s = new Scanner(new File(output_file));
			while (s.hasNextLine()) {
				out.add(new account(s.nextLine()));
			}
		} catch (Exception e) {
			System.out.printf("Exception thrown %s.\n", e.toString());
		}
		return out;
	}
	public int dump(List<account> __in) {
		try {
			FileWriter w = new FileWriter(output_file, true);
			for (account a : __in) {
				w.write(a._name + ":" + Integer.toString(a._hash) + "\n");
			}
			w.close();
		} catch (Exception e) {
			System.out.printf("Exception thrown %s.\n", e.toString());
		}
		return 0;
	}
}

final class account {
	String _name;
	int _hash;

	public account(String __raw) {
		String[] values = __raw.split(":");
		_name = values[0];
		_hash = Integer.parseInt(values[1]);
	}
	public account(String __name, String __password) {
		_name = __name;
		_hash = __password.hashCode();
	}
	public account() {
		/* Gets the user input then creates a account based on this. */
		Scanner s = new Scanner(System.in);
		System.out.println("What should the username be?");
		_name = s.nextLine();
		System.out.println("What should the password be?");
		_hash = s.nextLine().hashCode();
	}
};

class login_program {
	public static void main(String[] __argv) {
		/* Attempt to create the file. */
		system s = new system();
		switch (s.create_file()) {
			case -1: {
				return;
			}
			case 0: {
				/* This means that the file already exists. */
				for (account a : s.load()) {
					System.out.printf("[%s][%d]\n", a._name, a._hash);
				}
				break;
			}
			case 1: {
				/* Dump a single account created by the user. */
				List<account> tmp = new ArrayList<account>();
				tmp.add(new account());
				s.dump(tmp);
				break;
			}
		}
	}
}
