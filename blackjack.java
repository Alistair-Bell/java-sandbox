import java.util.*;

/* Have the card be a regular integer, this allows the use of built in algorithms. */
final class card {
	public static final int suit_mask  = 0xf;
	public static final int value_mask = 0xf0;
	public static final int alt_mask   = 0xf00;
	public static String[] suit_names  = { "diamond", "heart", "club", "spade" };

	public static int construct(int __suit, int __value) {
		/* Set the suit using the mask, not shifts currently required. */
		int return_value = __suit & suit_mask;

		/* All royality cards are set to 10. */
		if (9 < __value) {
			__value = 10;
		} else if (__value == 1) {
			/* Special rule for the ace card. */
			return_value += (11 << 8);
		}
		return_value += (__value << 4);	
		return return_value;
	}
}
final class deck {
	public int[] _cards;
	public int _drawn;
	
	public deck() {
		/* Construct the deck. */
		_cards = new int[52];
		_drawn = 0;

		int i = 0, j;	
		for (; i < 4; ++i) {
			for (j = 0; j < 13; ++j) {
				_cards[j + (i * 13)] = card.construct(i, j + 1);
			}
		}
	}
	public int draw() {
		return 0;
	}
	public void shuffle() {
	}
}

class blackjack_program {
	public static Scanner _scanner = new Scanner(System.in);
	public static void main(String[] __argv) {
		deck playing_cards = new deck();
		int i = 0;
		for (; i < playing_cards._cards.length; ++i) {
			int s = playing_cards._cards[i];
			System.out.printf("Card %2d, suit: %s, value: %2d, alt: %2d\n", i + 1, card.suit_names[s & card.suit_mask], (s & card.value_mask) >> 4, (s & card.alt_mask) >> 8);
		}
	}
}
