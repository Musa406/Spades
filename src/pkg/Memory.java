package pkg;
import java.util.ArrayList;




public class Memory {

	
	private final ArrayList<Card> memory; // array list of cards already played
											// this round

	public Memory() {
		memory = new ArrayList<>();
	}

	
	public void add(Card card) {
		memory.add(card);
	}

	
	public boolean contains(Card c) {
		for (Card aMemory : memory) {
			if (aMemory.getNumber() == c.getNumber() && aMemory.getSuit() == c.getSuit()) {
				return true;
			}
		}
		return false;
	}

}
