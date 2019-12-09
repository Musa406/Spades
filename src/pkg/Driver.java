package pkg;
import java.util.ArrayList;


public class Driver {


    public Player[] players; // number of players in total.
    String[] playerAry; // number of players(bot or human).
    public Deck deck; // the deck to be used for the game.
	public static Memory memory; // the memory to be used for the game.

	
	public Driver(int screenRes) {
		
		deck = new Deck(screenRes);
		memory = new Memory();
		playerAry = WelcomeGUI.getPlayers();
		players = new Player[4];
		
		for (int i = 0; i < playerAry.length; i++) {
			if (playerAry[i].equals("Bot"))
				players[i] = new Bot();
			else
				players[i] = new Human();
		}
		// players[0].turnToPlay = true;

	}

	
    void dealHands() {
        for (Player player : players) {
            player.setHand(deck.dealHand());
        }
    }

	
    Card determineWinner(ArrayList<Card> c) {
        
    	Card card0 = c.get(0);
        Card card1 = c.get(1);
		Card card2 = c.get(2);
		Card card3 = c.get(3);

		Card highCard = null;

		// find the highest card, if you only have spades and one other suit.
		
		if (Card.compareTo2(card0, card1) == 1) {
			highCard = new Card(card0.getSuit(), card0.getNumber());
			     
		} else {
			if (card1.getSuit() == Suit.SPADE || card1.getSuit() == card0.getSuit()) {
				highCard = new Card(card1.getSuit(), card1.getNumber());
			}
		}

		if (Card.compareTo2(highCard, card2) == -1) {
			if (card2.getSuit() == Suit.SPADE || card2.getSuit() == card0.getSuit()) {
				highCard = new Card(card2.getSuit(), card2.getNumber());
			}
		}
		if (Card.compareTo2(highCard, card3) == -1) {
			if (card3.getSuit() == Suit.SPADE || card3.getSuit() == card0.getSuit()) {
				highCard = new Card(card3.getSuit(), card3.getNumber());
			}
		}

		// ad all cards to memory
		memory.add(c.get(0));
		memory.add(c.get(1));
		memory.add(c.get(2));
		memory.add(c.get(3));

		// return the card of the winner
		return highCard;
	}

}
