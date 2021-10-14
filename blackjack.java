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
	public void display() {
		System.out.printf("Drew %d of %s\n", _value, card.suit_names[_suit]);
	}
	public static int sum_cards(card[] __cards) {
		int acc = 0;
		for (card i : __cards) {
			acc += i._value;
		}
		return acc;
	}
	public static int best_total(card[] __cards) {
		int ace_count = 0;
		for (card i : __cards) {
			if (i._alt != 0) { ++ace_count; }
		}
		int total = sum_cards(__cards),  closest = total;
		for (int i = 0; i < ace_count; ++i) {
			if (total > 21) {
				break;
			} 
			total += 10;
			if (total < 22) {
				closest = total;
			}
		}
		return total;
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
	public static Scanner _scanner = new Scanner(System.in);
	public static void main(String[] __argv) {
		deck d = new deck();
		/* Shuffle the deck, makes sure that each draw is random(ish). */
		d.shuffle();
		d.shuffle();

		card[] pcards = new card[10];
		card[] dcards = new card[10];
		for (int i = 0; i < 10; ++i) { 
			pcards[i] = new card(0, 0, 0); 
			dcards[i] = new card(0, 0, 0); 
		}

		/* Draw the inital 2 cards. */
		for (int i = 0; i < 2; ++i) {
			pcards[i] = d.draw(); 
			dcards[i] = d.draw();
		}
		System.out.printf("Your hand:\n");
		pcards[0].display(); pcards[1].display();
		int drawn = 2;

		do {
			int b = card.best_total(pcards);
			if (21 < b) {
				System.out.printf("You loose!\n");
				break;
			} else if (b == 21) {
				System.out.printf("You win!\n");
				break;
			}
			pcards[drawn] = d.draw();
			pcards[drawn].display();
			++drawn;
		} while (true);
	}
		
}
