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
	public book() {
	}
	public boolean write() {
		FileWriter writer;
		try {
			writer = new FileWriter(bookstore_program._file_name, true);
			/* Write all the book data. */
			writer.write(_title + ",");
			writer.write(_isbn + ",");
			writer.write(_genre.toString() + ",");
			/* Write the amount of authors expected. */
			writer.write(_author.length + ",");
			for (String s : _author) {
				writer.write(s + ",");
			}
			writer.write("\n");
			writer.close();
		} catch (Exception e) {
			System.out.printf("Exception thrown %s.\n", e.toString());
			return false;
		}
		return true;
	}
	public static List<book> read() {
		List<book> out = new ArrayList<book>();
		try {
			Scanner reader = new Scanner(bookstore_program._file);
			while (reader.hasNextLine()) {
				book construction; 
				out.add(construct_string(reader.nextLine()));
			}
		} catch (Exception e) {
			System.out.printf("Exception thrown %s.\n", e.toString());
		}
		return null;
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
		out.display();
		return out;
	}
	public void display() {
		System.out.printf("Book [%s] [%s] [%s] [%s].\n", _title, _genre.toString(), _isbn, Arrays.toString(_author));
	}
}

class bookstore_program {
	public static String _file_name = "books.txt";
	public static File _file = new File(_file_name);
	public static Scanner _scanner = new Scanner(System.in);

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
		for (book b : books) {
			b.write();
		}	

		List<book> in = book.read();
	}
}
