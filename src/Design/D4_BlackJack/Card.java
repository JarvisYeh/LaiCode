package Design.D4_BlackJack;

public class Card {
	private final int faceValue; // 1 for A, 11 for J, 12 for Q, 13 for K
	private final Suit suit;

	public Card(int faceValue, Suit suit) {
		this.faceValue = faceValue;
		this.suit = suit;
	}

	public int value() {
		return faceValue;
	}

	public Suit suit() {
		return suit;
	}
}
