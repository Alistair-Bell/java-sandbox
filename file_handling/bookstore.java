/*
 * License: read `license.txt` at the root of the project.
 * Make me a program which:
	* Stores an unspecified number of books including:
	* Book title.
	* ISBN.
	* Author.
	* Genre.
    * Store within a file.
 */

import java.util.*;
import java.io.*;

final class account {
	String _name;
	String _email;
	int _hash;
	int _perms;

	public account() {
		_name  = "root";
		_hash  = "pass".hashCode();
		_email = "root@bookstore.org";
		_perms = Integer.MAX_VALUE;
	}
	public account(int __perms) {
		Scanner s = new Scanner(System.in);
		System.out.println("What should the account name be?");
		_name = s.nextLine();
		do {
			System.out.println("What email to use?");
			_email = s.nextLine();
			System.out.println("What should the password be?");
			String passwd = s.nextLine();
			if (validate(passwd, _email) != -1) {
				_hash = passwd.hashCode();
				break;
			}
		} while (true);
		System.out.printf("Succesfully created user %s.\n", _name);
	}
	public account(String __name, String __password, String __email, int __perms) {
		_name  = __name;
		_hash  = __password.hashCode();
		_email = __email;
		_perms = __perms;
	}
	public account(String __raw) {
		String[] vals = __raw.split(":");
		_name  = vals[0];
		_hash  = Integer.valueOf(vals[1]);
		_email = vals[2];
		_perms = Integer.valueOf(vals[3]);
	}
	/* Override. */
	public String toString() {
		return _name + ":" + _hash + ":" + _email + ":" + _perms;
	}
	public int validate(String __password, String __email) {
		if (__password.length() < 6) {
			System.out.println("Password has too few characters");
			return -1;
		}
		/* Validate email. */
		if (__email.indexOf('@') == -1) {
			System.out.println("Email has no \'@\' symbol!");
			return -1;
		}
		return 1;
	}
}

final class login {
	public static final String out = "shadow.txt";
	
	private File _file;
	public login() {
		_file = new File(out);	
		try {
			_file.createNewFile();
		} catch (Exception e) {
		}
	}
	public List<account> load() {
		List<account> ret = new ArrayList<account>();
		try {
			Scanner s = new Scanner(_file);
			while (s.hasNextLine()) {
				ret.add(new account(s.nextLine()));
			}
			s.close();
		} catch (Exception e) {
			System.out.printf("Exception thrown %s.\n", e.toString());
			return null;
		}
		return ret;
	}
	public int dump(List<account> __in) {
		try	{
			FileWriter f = new FileWriter(out, false);
			for (account a : __in)
				f.write(a.toString());
			f.write("\n");
			f.close();
		} catch (Exception e) {
			System.out.printf("Exception thrown %s.\n", e.toString());
			return -1;
		}
		return 0;
	}
}


final class book {
	enum genre {
		ADVENTURE, CRIME, ROMANCE, NON_FICTION, BIOGRAPHY, HISTORICAL, ACTION
	}
	public String   _title;	
	public String   _isbn;
	public String[] _author;
	public genre    _genre;
	public static final int isbn_size = 13;

	public book(String __title, String __isbn, String[] __author, genre __genre) {
		_title  = __title;
		_isbn   = __isbn;
		_author = __author;
		_genre  = __genre;
	}
	public book() {
	}
	private static book construct_string(String __in) {
		book out = new book();
		String[] split = __in.split(",");
		out._title = split[0];	
		out._isbn = split[1];
		out._genre = book.genre.valueOf(split[2]);
		out._author = new String[Integer.parseInt(split[3])];
		
		for (int i = 4; i < split.length; ++i) {
			out._author[i - 4] = split[i];
		}
		return out;
	}
}
final class library {
	
}

class bookstore_program {
	public static String _file_name = "books.txt";
	public static File _file = new File(_file_name);
	private static login _sys = new login();
	private static Scanner _scanner = new Scanner(System.in);

	
	public static int account_index(List<account> __in, String __name) {
		for (int i = 0; i < __in.size(); ++i) {
			if (__in.get(i)._name.equals(__name)) {
				return i;
			}
		}
		return -1;
	}
	public static account login() {
		/* Load our logins. */
		List<account> accounts = _sys.load();
		if (accounts == null || accounts.size() < 1) {
			accounts = new ArrayList<account>();
			accounts.add(new account(Integer.MAX_VALUE));
			_sys.dump(accounts);
			return accounts.get(0);
		}
		
		/* Get the name from our user. */
		do {
			System.out.println("What is your name?");
			String name = _scanner.nextLine();
			int index;
			if ((index = account_index(accounts, name)) != -1) {
				System.out.println("What is the password?");
				if (_scanner.nextLine().hashCode() == accounts.get(index)._hash)
					return accounts.get(index);
				System.out.printf("Invalid password for %s.\n", name);
			} else {
				System.out.printf("Unable to find account with name %s.\n", name);
			}
		} while (true);
	}
	
	public static void main(String[] __argv) {
		account a = login();
		System.out.printf("Hello %s, %s.\n", a._name, a._email);
	}
}
