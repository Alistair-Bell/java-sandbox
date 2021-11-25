/*
 * License: read `license.txt` at the root of the project.
 */

import java.util.*;

class hangman_program {
	/* All the words are coreutils! */
	public static String _guessing_words[] = {
		"chown",
		"chmod",
		"cp",
		"dd",
		"df",
		"dir",
		"dircolors",
		"install",
		"ln",
		"ls",
		"mkdir",
		"mv",
		"realpath",
		"rm",
		"rmdir",
		"sync",
		"touch",
		"cat",
		"cut",
		"expand",
		"fold",
		"head",
		"join",
		"shuf",
		"sort",
		"split",
		"sum",
		"tac",
		"tail",
		"tr",
		"tsort",
		"arch",
		"basename",
		"chroot",
		"date",
		"dirname",
		"echo",
		"env",
		"expr",
		"factor",
		"false",
		"groups",
		"hostid",
		"link",
		"logname",
		"nice",
		"pathchk",
		"pinky",
		"printenv",
		"printf",
		"readlink",
		"runcon",
		"sleep",
		"stat",
		"tee",
		"test",
		"timeout",
		"true",
		"uname",
		"unlink",
		"uptime",
		"users",
		"who",
		"whoami",
		"yes",
	};
	public static Scanner  _scanner = new Scanner(System.in);
	public static String[] _buff    = new String[6];
	
	public static void draw(int __stage) {
		switch (__stage) {
			case 5: {
				/* Allocate the memory. */
				for (int i = 0; i < _buff.length; ++i) { _buff[i] = new String();	}
				_buff[0] = "-----";
				_buff[1] = "|  | ";
				_buff[2] = "|    ";
				_buff[3] = "|    ";
				_buff[4] = "|    ";
				_buff[5] = "-----";
				break;
			}
			case 4: {
				_buff[2] = "|  0 ";
				break;
			}
			case 3: {
				_buff[3] = "|  | ";
				break;
			}
			case 2: {
				_buff[4] = "| / \\";
				break;
			}
			case 1: {
				_buff[3] = "| -|-";
			}
		}
		for (int i = 0; i < _buff.length; ++i) {
			System.out.println(_buff[i]);
		}
	}
	public static void main(String[] __argv) {
		String target_word = _guessing_words[(int)(Math.random() * _guessing_words.length)];
		int wrong_guesses_left = 5, correct_guesses = 0;
		char[] formation_buffer = new char[target_word.length()];
		Arrays.fill(formation_buffer, '_');

		do {
			System.out.printf("Guess, %s, charset [abcdefghijklmnopqrstuv].\n", Arrays.toString(formation_buffer));
			char guess = _scanner.next().charAt(0);
			/* Check if the guess is in the word. */
			int index = target_word.indexOf(guess);
			if (index != -1 && Arrays.asList(formation_buffer).contains(guess) == false){
				/* Loop to get all the occurances. */
				while (0 <= index) {
					++correct_guesses;
					formation_buffer[index] = guess;
					index = target_word.indexOf(guess, index + 1);
				}
				if (correct_guesses == target_word.length()) {
					System.out.printf("You win, the word was %s, you had %d wrong guesses left to get it right.\n", target_word, wrong_guesses_left);
					return;
				}
			} else {
				draw(wrong_guesses_left);
				--wrong_guesses_left;
			}
		} while (wrong_guesses_left != 0);
		System.out.printf("You loose, the word was %s, you got %d letters correct, that was %s.\n", 
			target_word, correct_guesses, (correct_guesses == target_word.length() - 1) ? "close" : "poor");
	}
}
