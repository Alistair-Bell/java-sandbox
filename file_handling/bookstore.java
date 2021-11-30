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
	public boolean write() {
		FileWriter writer;
		try {
			writer = new FileWriter(bookstore_program._file_name, false);
			writer.write(_title + ",");
			writer.write(_isbn + ",");
			writer.write("\n");
			writer.close();
		} catch (Exception e) {
			System.out.printf("Exception thrown %s.\n", e.toString());
			return false;
		}
		return true;
	}
}

class bookstore_program {
	public static String _file_name = "books.txt";
	private static File _file = new File(_file_name);
	private static Scanner _scanner = new Scanner(System.in);

	private static boolean create_file() {
		try {
			_file.createNewFile();
			return true;
		} catch (Exception e) {
			System.out.printf("Exception thrown %s.\n", e.toString());
			return false;
		}
	}
	public static void main(String[] __argv) {
		/* Create the file if it does not already exist. */
		if (!create_file()) {
			return;
		}
		/* Predefined books. */
		final book[] books = {
			new book("The C Programming Language", "9780131101630", new String[] { "Brian Kernighan", "Dennis Ritchie" }, book.genre.NON_FICTION),
			new book("The Cathedral and the Bazaar", "1-565-92724-9", new String[] { "Eric S. Raymond" }, book.genre.NON_FICTION),
		};
		books[0].write();
	}
}
