/* 
 * License: read `license` at the root of the project.
 */

import java.util.Scanner;
import java.util.Arrays;

final class dictionary_compression {
	/* Stores 2 packed chars. */
	short[] _segments;
	char[]  _sequence;

	public dictionary_compression(String in) {
		/* Saves writing in.length() many times.*/
		int il = in.length();		
		short[] packed;

		if (il % 2 == 0) {
			packed = new short[il / 2];
		} else {
			packed = new short[il / 2 + 1];
		}

		_segments = new short[packed.length];

		int repeat_counter = 0;
		
		int i = 1, packed_index = 0, segment_index = 0;
		for (; i < il; i += 2) {
			short insertion = (short)(in.charAt(i - 1) + (in.charAt(i) << 8));
			
			/* Check to see if _segments already has the same packed values. */
			if (Arrays.binarySearch(_segments, insertion) < 0) {
				_segments[segment_index] = insertion;
				++segment_index;
				/* Sort to make ready for the next binary search. */
				Arrays.sort(_segments);
			} else {
				++repeat_counter;
			}
			packed[packed_index] = insertion;
			++packed_index;
		}
		/* Add any stragglers. */
		if (il % 2 != 0) {
			_segments[segment_index] = (short)in.charAt(i - 1);
			packed[packed_index] = (short)in.charAt(i - 1);
		}
		
		/* Creates the exact number of chars that we need to compress all the data, excluding the table. */
		_sequence = new char[(packed.length - repeat_counter) * 2];
		int sequence_index = 0;
		for (short si : packed) {
			/* Replace the packed value with the reference within the dictionary. */
			int search_result = Arrays.binarySearch(_segments, si);
			if (search_result < 0) {
				/* Write 2 sequential bytes */
				_sequence[sequence_index] = (char)(si & 0xff);
				if ((si & 0xff00) >> 8 != 0) {
					++sequence_index;
					_sequence[sequence_index] = (char)((si & 0xff00) >> 8);
				}
			} else {
				_sequence[sequence_index] = (char)(search_result + 128);
			}
			++sequence_index;
		}
	}
	public void out() {
		System.out.printf("-- Segments --\n");
		for (short si : _segments) {
			System.out.printf("%6d\n", si);
		}
		System.out.printf("-- Data --\n");
		for (char ci : _sequence) {
			System.out.printf("%3d\n", (int)ci);
		}
	}
}

/* The compression algorithms will just dump it's result to stdout. */
class rle {

	public static void encode(String in) {
		/* Start the compress value as the first char, this ensures that it will be incrimented at least a single time. */
		char compress_value = in.charAt(0);
		int compress_count = 0, i = 0;

		for (; i < in.length(); ++i) {
			/* Stores the current char at index 'i' use of a c lang pointer loop would have been better but I'm stuck with this approach. */
			char cchar = in.charAt(i);

			/* The current char is not the same as the current series of chars being compressed. */
			if (cchar != compress_value) {
				System.out.printf("%c%d", compress_value, compress_count);
				/* Stores 1 as the new char has one currently recorded instance. */
				compress_count = 1;	
				/* Assign the new char to the compress_value. */
				compress_value = cchar;
			} else {
				/* 
				 * Just incriment the compress_count var and move onwards to the next char.
				 * A forseeable bug would be the incriment is done regardless if a overflow would occur.
				 */
				++compress_count;
			}
		}
		System.out.printf("%c%d\n", compress_value, compress_count);
	}
}

class compression_program {

	static String messages[] = {
		"GNU's not unix!",
		"Java is an island in indochina.",
	};

	public static void main(String[] argv) {
		System.out.println(1 << 2);
		Scanner in = new Scanner(System.in);
		/* Provides no authentication on user input, the user is free to put any old garbage. */
		System.out.print("Please enter some text for the encoder! \n");
		//rle.encode(in.next());
		/* Run through with some of my own examples. */
		int i = 0;
		for (; i < messages.length; ++i) {
			System.out.printf("Message before: %s\nMessage after ", messages[i]);
			rle.encode(messages[i]);
		}
		dictionary_compression d = new dictionary_compression("heheo");
		d.out();
	}
}
