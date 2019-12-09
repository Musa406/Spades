package pkg;
import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    private final ArrayList<Card> deck = new ArrayList<>(); // arraylist of
	
	public Deck(int screenRes) {

		// SPADES
		for (int i = 1; i <= 13; i++) {
			deck.add(new Card(Suit.SPADE, i, screenRes));
		}
		// CLUBS
		for (int i = 1; i <= 13; i++) {
			deck.add(new Card(Suit.CLUB, i, screenRes));
		}
		// HEARTS
		for (int i = 1; i <= 13; i++) {
			deck.add(new Card(Suit.HEART, i, screenRes));
		}
		// DIAMONDS
		for (int i = 1; i <= 13; i++) {
			deck.add(new Card(Suit.DIAMOND, i, screenRes));
		}
		shuffle();
	}
	
	
    private void shuffle() {
        Collections.shuffle(deck);
    }
    
	public String toString() {
		return deck.toString();
	}


    Hand dealHand() {
        Hand myHand = new Hand();
        int index = 0;

		while (index < 13) {
			myHand.add(deck.remove(0));
			index++;
		}

		return myHand;
	}
}
