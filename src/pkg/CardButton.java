package pkg;
import javax.swing.*;



public class CardButton extends JButton {

	

	private Card card; // the card object of the button.

	
	public CardButton() {
		super();
	}

	public CardButton(Card c) {
		super();
		card = c;
	}

	public CardButton(Icon i, Card c) {
		super(i);
		card = c;
	}

	public Card getCard() {
		return card;
	}
}
