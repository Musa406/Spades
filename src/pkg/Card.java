package pkg;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;


public class Card implements Comparable<Card> {

    private Suit mySuit; // the suit of the card.
    private int number; // the number of the card.
    private ImageIcon image; // the Image of the card based on the suit and number.


    public Card(Suit theSuit, int theNum, int screenRes) {
        mySuit = theSuit;
        number = theNum;
        setImage(screenRes);
    }

 
    public Card(Suit theSuit, int theNum) {
        mySuit = theSuit;
        number = theNum;
    }


    static int compareTo2(Card firstCard, Card secondCard) {

        int suit1, suit2;

        suit1 = suitSwitch(firstCard.getSuit());

        suit2 = suitSwitch((secondCard).getSuit());

        if ((firstCard).getSuit() == (secondCard).getSuit()) {
            if ((firstCard).getNumber() == 1)
                return 1;
            else if ((secondCard).getNumber() == 1)
                return -1;

            if ((firstCard).getNumber() < (secondCard).getNumber())
                return -1;
            else
                return 1;
        }
        if (suit1 < suit2)
            return -1;
        else
            return 1;

    }

    private static int suitSwitch(Suit s) {
        switch (s) {
            case SPADE:
                return 4;
            case DIAMOND:
                return 1;
            case CLUB:
                return 1;
            case HEART:
                return 1;
            default:
                return 0;
        }
    }

    
    public Suit getSuit() {
        return mySuit;
    }

    
    public int getNumber() {
        return number;
    }

   
    public boolean equals(Object o) {
        if (getClass() != o.getClass())
            return false;
        Card otherCard = (Card) o;
        if (this.getSuit().equals(otherCard.getSuit())) {
            if (this.getNumber() == (otherCard.getNumber()))
                return true;
        }
        return false;
    }

   
    public String getName() {
        String cardName = "";

        switch (this.getNumber()) {
            case 1:
                cardName += "Ace of ";
                break;
            case 2:
                cardName += "2 of ";
                break;
            case 3:
                cardName += "3 of ";
                break;
            case 4:
                cardName += "4 of ";
                break;
            case 5:
                cardName += "5 of ";
                break;
            case 6:
                cardName += "6 of ";
                break;
            case 7:
                cardName += "7 of ";
                break;
            case 8:
                cardName += "8 of ";
                break;
            case 9:
                cardName += "9 of ";
                break;
            case 10:
                cardName += "10 of ";
                break;
            case 11:
                cardName += "Jack of ";
                break;
            case 12:
                cardName += "Queen of ";
                break;
            case 13:
                cardName += "King of ";
                break;
            default:
                break;
        }

        switch (this.getSuit()) {
            case SPADE:
                cardName += "Spades";
                break;
            case CLUB:
                cardName += "Clubs";
                break;
            case HEART:
                cardName += "Hearts";
                break;
            case DIAMOND:
                cardName += "Diamonds";
                break;
            default:
                break;
        }

        return cardName;
    }

    
    public ImageIcon getImage() {
        return image;
    }

   
    private void setImage(int screenRes) {

        String cardDef = "PNG-cards-1.3/";

        if (number == 1)
            cardDef += "ace_of_";
        else if (number == 2)
            cardDef += "2_of_";
        else if (number == 3)
            cardDef += "3_of_";
        else if (number == 4)
            cardDef += "4_of_";
        else if (number == 5)
            cardDef += "5_of_";
        else if (number == 6)
            cardDef += "6_of_";
        else if (number == 7)
            cardDef += "7_of_";
        else if (number == 8)
            cardDef += "8_of_";
        else if (number == 9)
            cardDef += "9_of_";
        else if (number == 10)
            cardDef += "10_of_";
        else if (number == 11)
            cardDef += "jack_of_";
        else if (number == 12)
            cardDef += "queen_of_";
        else if (number == 13)
            cardDef += "king_of_";

        if (mySuit == Suit.SPADE)
            cardDef += "spades.png";
        if (mySuit == Suit.CLUB)
            cardDef += "clubs.png";
        if (mySuit == Suit.DIAMOND)
            cardDef += "diamonds.png";
        if (mySuit == Suit.HEART)
            cardDef += "hearts.png";

        Image img = null;
        try {
            img = ImageIO.read(getClass().getResourceAsStream(cardDef));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image scaledImage;
        if (screenRes == 1) {
            assert img != null;
            scaledImage = img.getScaledInstance(50, 100, Image.SCALE_FAST);
        } else {
            assert img != null;
            scaledImage = img.getScaledInstance(100, 200, Image.SCALE_FAST);
        }

        image = new ImageIcon(scaledImage);
    }

    /**
     * Prints the name of the card using the getName method
     */
    public String toString() {
        return getName();
    }

	
    public void setScreenRes(int screenRes) {
        setImage(screenRes);
    }

    

    @Override
    public int compareTo(Card secondCard) {

        int suit1, suit2;

        // Set a suit order for the first
        suit1 = suitSwitch(this.getSuit());

        // set a suit order for the second
        suit2 = suitSwitch(secondCard.getSuit());

        if (getSuit() == (secondCard).getSuit()) {
            if (getNumber() < (secondCard).getNumber())
                return -1;
            else
                return 1;
        }
        if (suit1 < suit2)
            return -1;
        else
            return 1;

    }

}
