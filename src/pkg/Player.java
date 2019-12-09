package pkg;

public class Player {

	
	private Hand myHand; // the hand of cards a player has.
	private boolean turnToPlay; // true if it is this players turn to play
	private String name; // the name of the player.

	
	
	public Player() {
		name = "Player";
		myHand = null;
	}

	
	public Player(String n) {
		name = n;
		myHand = null;
	}

	
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	
	public void setHand(Hand hand) {
		myHand = hand;
	}

	public Hand getHand() {
		return myHand;
	}

	
	boolean getTurnToPlay() {
		return turnToPlay;
	}

	
	void setTurnToPlay(boolean turn) {
		turnToPlay = turn;
	}

}
