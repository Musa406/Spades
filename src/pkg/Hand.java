package pkg;
import java.util.ArrayList;
import java.util.Collections;


public class Hand {

	
	private final ArrayList<Card> hand; // arrayList of 13 cards.

	
	public Hand() {
		hand = new ArrayList<>();
	}

	
	public void add(Card card) {
		int i = hand.size();
		if (i < 13)
			hand.add(i, card);
		Collections.sort(hand);
	}

	
	public boolean remove(Card card) {
		return hand.remove(card);
	}

	
	public Card remove(int position) {
		
		Card returnCard = hand.get(position);
		hand.remove(returnCard);
		return returnCard;
	}

	Card getCardAtPosition(int position) {
		return hand.get(position);
	}

	
	int getNumSpades() {
		int numSpades = 0;
		for (Card aHand : hand) {
			if (aHand.getSuit().equals(Suit.SPADE)) {
				
				if (aHand.getNumber() == 1 || aHand.getNumber() == 13 )
					numSpades--;
				
				
				numSpades++;
			}
			
		}
		if(numSpades >= 3) numSpades=numSpades-2;
		else numSpades = 0;
		
		return numSpades;
	}
	
	int getNumAces() {
		int numAces = 0;
		int spadeAces=0;
		for (Card aHand : hand) {
			
			if (aHand.getSuit().equals(Suit.SPADE)) {
				if (aHand.getNumber() == 1 || aHand.getNumber() == 13) {
					numAces++;
					spadeAces++;
				}
				if (aHand.getNumber() == 12 || aHand.getNumber() == 11) {
					
					if(spadeAces>0) numAces++;
				}
				
				
			}
			
			if (aHand.getNumber()== 1 || aHand.getNumber()== 13) {
				numAces++;
			}
				
			
			
		}
		return numAces;
	}
	
	
	public String toString() {
		return hand.toString() + " Length: " + hand.size();
	}

	
	public boolean contains(Card c) {
		for (Card aHand : hand) {
			if (aHand.getNumber() == c.getNumber() && aHand.getSuit() == c.getSuit()) {
				return true;
			}
		}
		return false;
	}

	
	public int size() {
		return hand.size();
	}
}
