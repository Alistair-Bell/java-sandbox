import java.util.*;

final class card {
	public byte _value;
	public byte _suit;
	public byte _alt;
	public static String[] suit_names = { "diamonds", "hearts", "clubs", "spades" };
	
	public card(int __value, int __suit, int __alt) {
		if (__value > 9) {
			__value = 10;
		}
		_value = ((byte)__value);
		_suit  = ((byte)__suit);
		_alt   = ((byte)__alt);
	}
	public static int win(String __player, card __c1, card __c2) {
		if (__c1._value + __c2._value == 21) {
			System.out.printf("%s won, they had the winning hand to sum to 21!\n", __player);
			return 1;
		} else if ((__c1._alt + __c2._value) == 21 || (__c1._value + __c2._alt) == 21) {
			System.out.printf("%s won, their cards alternate values won them the game!\n", __player);
			return 1;
		}
		return 0;
	}
	public static boolean win(card __c1, card __c2, card __c3) {
		return false;
	}
}
final class deck {
	public card[] _cards;
	public int    _drawn;

	public void reconstruct() {
		if (_cards == null) {
			_cards = new card[52];
		}
		_drawn = 0;

		int i, j;
		for (i = 0; i < 4; ++i) {
			for (j = 0; j < 13; ++j) {
				_cards[j + (i * 13)] = new card(j + 1, i, (j == 0) ? 11 : 0);
			}
		}
	}
	public card draw() {
		if (_drawn == _cards.length) {
			return new card(0, 0, 0); 
		}
		++_drawn;
		return _cards[_drawn - 1];
	}
	public void shuffle() {
		/* This random generator is not that random, really could use some lava lamps. */
		Random random = new Random();
		for (int i = 0; i < 10; ++i) {
			for (int j = 0; j < _cards.length; ++j) {
				int swap_index = random.nextInt(_cards.length);
				card current_value = _cards[i];
				_cards[i]          = _cards[swap_index];
				_cards[swap_index] = current_value;
			}
		}
	}
	public deck() {
		reconstruct();
	}
}

class blackjack_program {
	public static void main(String[] __argv) {
		deck d = new deck();
		d.shuffle();

		card[] pcards = { d.draw(), d.draw(), new card(0, 0, 0) };
		card[] dcards = { d.draw(), d.draw(), new card(0, 0, 0) };

		System.out.printf("You drew a %d of %s, %d of %s, total %d.\n",
			pcards[0]._value, card.suit_names[pcards[0]._suit],
			pcards[1]._value, card.suit_names[pcards[1]._suit], pcards[0]._value + pcards[1]._value);
		System.out.printf("Dealer drew a %d of %s, %d of %s, total %d.\n",
			dcards[0]._value, card.suit_names[dcards[0]._suit],
			dcards[1]._value, card.suit_names[dcards[1]._suit], dcards[0]._value + dcards[1]._value);

		if (card.win("You", pcards[0], pcards[1]) == 1 || card.win("Dealer", dcards[0], dcards[1]) == 1) {
			return;
		}
	}
}
