package Design.D4_BlackJack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {
	private final Random rand = new Random();

	private final List<Card> cards = new ArrayList<>();
	private int dealtIndex = 0; // 初始发牌位置

	public Deck() {
		// 每个花色，分别从1-13
		for (int i = 0; i <= 13; i++) {
			for (Suit suit : Suit.values()) {
				cards.add(new Card(i, suit));
			}
		}
	}

	public void shuffle() {
		for (int i = 0; i < cards.size() - i; i++) {
			int j = rand.nextInt(cards.size() - i) + i;
			Card card1 = cards.get(i);
			Card card2 = cards.get(j);
			//swap card i and card j
			cards.set(i, card2);
			cards.set(j, card1);
		}
	}

	private int remainingCards() {
		return cards.size() - dealtIndex;
	}

	public Card[] dealHand(int number) {
		// 剩余的牌不够发了
		if (remainingCards() < number) {
			return null;
		}
		Card[] cards = new Card[number];
		for (int i = 0; i < number; i++) {
			cards[i] = dealCard();
		}
		return cards;
	}

	public Card dealCard() {
		return remainingCards() == 0 ? null : cards.get(dealtIndex++);
	}

	public void reset() {
		dealtIndex = 0;
		shuffle();
	}

}
