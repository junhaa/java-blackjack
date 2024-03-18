package domain.card;

import static domain.card.Card.CARD_POOL;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class CardDeck {

    private final Stack<Card> deck;

    public CardDeck() {
        List<Card> cards = CARD_POOL.values().stream()
                .toList();
        deck = new Stack<>();
        deck.addAll(cards);
        shuffle();
    }

    private void shuffle() {
        Collections.shuffle(deck);
    }

    public Hand initHand() {
        return new Hand(List.of(draw(), draw()));
    }

    public Card draw() {
        return deck.pop();
    }
}
